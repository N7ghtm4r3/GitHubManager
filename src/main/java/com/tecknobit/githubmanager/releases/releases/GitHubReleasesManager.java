package com.tecknobit.githubmanager.releases.releases;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.records.repository.Repository;
import com.tecknobit.githubmanager.releases.releases.records.Release;
import com.tecknobit.githubmanager.releases.releases.records.ReleaseNotesContent;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubReleasesManager} class is useful to manage all GitHub's releases endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases">
 * Releases</a>
 * @see GitHubManager
 **/
public class GitHubReleasesManager extends GitHubManager {

    /**
     * {@code RELEASES_PATH} constant for {@code "/releases"} path
     **/
    public static final String RELEASES_PATH = "/releases";

    /**
     * {@code RELEASES_QUERY_PATH} constant for {@code "/releases/"} path
     **/
    public static final String RELEASES_QUERY_PATH = RELEASES_PATH + "/";

    /**
     * {@code RELEASES_GENERATE_NOTES_PATH} constant for {@code "/releases/generate-notes"} path
     **/
    public static final String RELEASES_GENERATE_NOTES_PATH = RELEASES_QUERY_PATH + "generate-notes";

    /**
     * {@code RELEASES_LATEST_PATH} constant for {@code "/releases/latest"} path
     **/
    public static final String RELEASES_LATEST_PATH = RELEASES_QUERY_PATH + "latest";

    /**
     * {@code RELEASES_TAGS_PATH} constant for {@code "/releases/tags/"} path
     **/
    public static final String RELEASES_TAGS_PATH = RELEASES_QUERY_PATH + "tags/";

    /**
     * Constructor to init a {@link GitHubReleasesManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubReleasesManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubReleasesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubReleasesManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubReleasesManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubReleasesManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubReleasesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubReleasesManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubReleasesManager} <br>
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
    public GitHubReleasesManager() {
        super();
    }

    /**
     * Method to get the list of the releases, which does not include regular Git tags that have not been
     * associated with a release. To get a list of Git tags, use the Repository Tags API
     *
     * @param repository: the repository from fetch the list
     * @return releases list as {@link ArrayList} of {@link Release} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#list-releases">
     * List releases</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases")
    public ArrayList<Release> getReleases(Repository repository) throws IOException {
        return getReleases(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the releases, which does not include regular Git tags that have not been
     * associated with a release. To get a list of Git tags, use the Repository Tags API
     *
     * @param repository: the repository from fetch the list
     * @return releases list as {@link ArrayList} of {@link Release} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#list-releases">
     * List releases</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases")
    public <T> T getReleases(Repository repository, ReturnFormat format) throws IOException {
        return getReleases(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list of the releases, which does not include regular Git tags that have not been
     * associated with a release. To get a list of Git tags, use the Repository Tags API
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return releases list as {@link ArrayList} of {@link Release} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#list-releases">
     * List releases</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases")
    public ArrayList<Release> getReleases(String owner, String repo) throws IOException {
        return getReleases(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the releases, which does not include regular Git tags that have not been
     * associated with a release. To get a list of Git tags, use the Repository Tags API
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return releases list as {@link ArrayList} of {@link Release} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#list-releases">
     * List releases</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases")
    public <T> T getReleases(String owner, String repo, ReturnFormat format) throws IOException {
        return returnReleases(sendGetRequest(REPOS_PATH + owner + "/" + repo + RELEASES_PATH), format);
    }

    /**
     * Method to get the list of the releases, which does not include regular Git tags that have not been
     * associated with a release. To get a list of Git tags, use the Repository Tags API
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
     * @return releases list as {@link ArrayList} of {@link Release} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#list-releases">
     * List releases</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases")
    public ArrayList<Release> getReleases(Repository repository, Params queryParams) throws IOException {
        return getReleases(repository.getOwner().getLogin(), repository.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the releases, which does not include regular Git tags that have not been
     * associated with a release. To get a list of Git tags, use the Repository Tags API
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
     * @return releases list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#list-releases">
     * List releases</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases")
    public <T> T getReleases(Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return getReleases(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the list of the releases, which does not include regular Git tags that have not been
     * associated with a release. To get a list of Git tags, use the Repository Tags API
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
     * @return releases list as {@link ArrayList} of {@link Release} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#list-releases">
     * List releases</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases")
    public ArrayList<Release> getReleases(String owner, String repo, Params queryParams) throws IOException {
        return getReleases(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the releases, which does not include regular Git tags that have not been
     * associated with a release. To get a list of Git tags, use the Repository Tags API
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
     * @return releases list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#list-releases">
     * List releases</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases")
    public <T> T getReleases(String owner, String repo, Params queryParams, ReturnFormat format) throws IOException {
        return returnReleases(sendGetRequest(REPOS_PATH + owner + "/" + repo + RELEASES_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a releases list
     *
     * @param releasesResponse : obtained from GitHub's response
     * @param format           :              return type formatter -> {@link ReturnFormat}
     * @return releases list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnReleases(String releasesResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(releasesResponse);
            case LIBRARY_OBJECT:
                ArrayList<Release> releases = new ArrayList<>();
                JSONArray jReleases = new JSONArray(releasesResponse);
                for (int j = 0; j < jReleases.length(); j++)
                    releases.add(new Release(jReleases.getJSONObject(j)));
                return (T) releases;
            default:
                return (T) releasesResponse;
        }
    }

    /**
     * Method to create a release
     *
     * @param repository: the repository where create the release
     * @param tagName:    the name of the tag
     * @return release as of {@link Release} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#create-a-release">
     * Create a release</a>
     * @implNote this endpoint triggers notifications. Creating content too quickly using this endpoint may result in
     * secondary rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/releases")
    public Release createRelease(Repository repository, String tagName) throws IOException {
        return createRelease(repository.getOwner().getLogin(), repository.getName(), tagName, LIBRARY_OBJECT);
    }

    /**
     * Method to create a release
     *
     * @param repository: the repository where create the release
     * @param tagName:    the name of the tag
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return release as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#create-a-release">
     * Create a release</a>
     * @implNote this endpoint triggers notifications. Creating content too quickly using this endpoint may result in
     * secondary rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/releases")
    public <T> T createRelease(Repository repository, String tagName, ReturnFormat format) throws IOException {
        return createRelease(repository.getOwner().getLogin(), repository.getName(), tagName, format);
    }

    /**
     * Method to create a release
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param tagName: the name of the tag
     * @return release as of {@link Release} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#create-a-release">
     * Create a release</a>
     * @implNote this endpoint triggers notifications. Creating content too quickly using this endpoint may result in
     * secondary rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/releases")
    public Release createRelease(String owner, String repo, String tagName) throws IOException {
        return createRelease(owner, repo, tagName, LIBRARY_OBJECT);
    }

    /**
     * Method to create a release
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param tagName: the name of the tag
     * @param format   :              return type formatter -> {@link ReturnFormat}
     * @return release as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#create-a-release">
     * Create a release</a>
     * @implNote this endpoint triggers notifications. Creating content too quickly using this endpoint may result in
     * secondary rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/releases")
    public <T> T createRelease(String owner, String repo, String tagName, ReturnFormat format) throws IOException {
        return createRelease(owner, repo, tagName, null, format);
    }

    /**
     * Method to create a release
     *
     * @param repository: the repository where create the release
     * @param tagName:    the name of the tag
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "target_commitish"} -> specifies the commitish value that determines where the
     *                           Git tag is created from. Can be any branch or commit SHA. Unused if the Git tag already
     *                           exists. Default: the repository's default branch (usually master) - [string]
     *                       </li>
     *                       <li>
     *                           {@code "name"} -> the name of the release - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> text describing the contents of the tag - [string]
     *                       </li>
     *                       <li>
     *                           {@code "draft"} -> {@code "true"} to create a draft (unpublished) release,
     *                           {@code "false"} to create a published one - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "prerelease"} -> {@code "true"} to identify the release as a prerelease.
     *                           {@code "false"} to identify the release as a full release - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "discussion_category_name"} -> if specified, a discussion of the specified
     *                           category is created and linked to the release. The value must be a category that already
     *                           exists in the repository. For more information, see "Managing categories for
     *                           discussions in your repository." - [string]
     *                       </li>
     *                       <li>
     *                           {@code "generate_release_notes"} -> whether to automatically generate the name and body
     *                           for this release. If name is specified, the specified name will be used; otherwise,
     *                           a name will be automatically generated. If body is specified, the body will be pre-pended
     *                           to the automatically generated notes - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "make_latest"} -> specifies whether this release should be set as the latest
     *                           release for the repository. Drafts and predeceases cannot be set as latest. Defaults
     *                           to true for newly published releases. legacy specifies that the latest release should
     *                           be determined based on the release creation date and higher semantic
     *                           version - [string, default true]
     *                       </li>
     *                    </ul>
     * @return release as of {@link Release} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#create-a-release">
     * Create a release</a>
     * @implNote this endpoint triggers notifications. Creating content too quickly using this endpoint may result in
     * secondary rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/releases")
    public Release createRelease(Repository repository, String tagName, Params bodyParams) throws IOException {
        return createRelease(repository.getOwner().getLogin(), repository.getName(), tagName, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a release
     *
     * @param repository: the repository where create the release
     * @param tagName:    the name of the tag
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "target_commitish"} -> specifies the commitish value that determines where the
     *                           Git tag is created from. Can be any branch or commit SHA. Unused if the Git tag already
     *                           exists. Default: the repository's default branch (usually master) - [string]
     *                       </li>
     *                       <li>
     *                           {@code "name"} -> the name of the release - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> text describing the contents of the tag - [string]
     *                       </li>
     *                       <li>
     *                           {@code "draft"} -> {@code "true"} to create a draft (unpublished) release,
     *                           {@code "false"} to create a published one - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "prerelease"} -> {@code "true"} to identify the release as a prerelease.
     *                           {@code "false"} to identify the release as a full release - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "discussion_category_name"} -> if specified, a discussion of the specified
     *                           category is created and linked to the release. The value must be a category that already
     *                           exists in the repository. For more information, see "Managing categories for
     *                           discussions in your repository." - [string]
     *                       </li>
     *                       <li>
     *                           {@code "generate_release_notes"} -> whether to automatically generate the name and body
     *                           for this release. If name is specified, the specified name will be used; otherwise,
     *                           a name will be automatically generated. If body is specified, the body will be pre-pended
     *                           to the automatically generated notes - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "make_latest"} -> specifies whether this release should be set as the latest
     *                           release for the repository. Drafts and predeceases cannot be set as latest. Defaults
     *                           to true for newly published releases. legacy specifies that the latest release should
     *                           be determined based on the release creation date and higher semantic
     *                           version - [string, default true]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return release as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#create-a-release">
     * Create a release</a>
     * @implNote this endpoint triggers notifications. Creating content too quickly using this endpoint may result in
     * secondary rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/releases")
    public <T> T createRelease(Repository repository, String tagName, Params bodyParams,
                               ReturnFormat format) throws IOException {
        return createRelease(repository.getOwner().getLogin(), repository.getName(), tagName, bodyParams, format);
    }

    /**
     * Method to create a release
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param tagName:    the name of the tag
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "target_commitish"} -> specifies the commitish value that determines where the
     *                           Git tag is created from. Can be any branch or commit SHA. Unused if the Git tag already
     *                           exists. Default: the repository's default branch (usually master) - [string]
     *                       </li>
     *                       <li>
     *                           {@code "name"} -> the name of the release - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> text describing the contents of the tag - [string]
     *                       </li>
     *                       <li>
     *                           {@code "draft"} -> {@code "true"} to create a draft (unpublished) release,
     *                           {@code "false"} to create a published one - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "prerelease"} -> {@code "true"} to identify the release as a prerelease.
     *                           {@code "false"} to identify the release as a full release - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "discussion_category_name"} -> if specified, a discussion of the specified
     *                           category is created and linked to the release. The value must be a category that already
     *                           exists in the repository. For more information, see "Managing categories for
     *                           discussions in your repository." - [string]
     *                       </li>
     *                       <li>
     *                           {@code "generate_release_notes"} -> whether to automatically generate the name and body
     *                           for this release. If name is specified, the specified name will be used; otherwise,
     *                           a name will be automatically generated. If body is specified, the body will be pre-pended
     *                           to the automatically generated notes - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "make_latest"} -> specifies whether this release should be set as the latest
     *                           release for the repository. Drafts and predeceases cannot be set as latest. Defaults
     *                           to true for newly published releases. legacy specifies that the latest release should
     *                           be determined based on the release creation date and higher semantic
     *                           version - [string, default true]
     *                       </li>
     *                    </ul>
     * @return release as of {@link Release} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#create-a-release">
     * Create a release</a>
     * @implNote this endpoint triggers notifications. Creating content too quickly using this endpoint may result in
     * secondary rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/releases")
    public Release createRelease(String owner, String repo, String tagName, Params bodyParams) throws IOException {
        return createRelease(owner, repo, tagName, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a release
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param tagName:    the name of the tag
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "target_commitish"} -> specifies the commitish value that determines where the
     *                           Git tag is created from. Can be any branch or commit SHA. Unused if the Git tag already
     *                           exists. Default: the repository's default branch (usually master) - [string]
     *                       </li>
     *                       <li>
     *                           {@code "name"} -> the name of the release - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> text describing the contents of the tag - [string]
     *                       </li>
     *                       <li>
     *                           {@code "draft"} -> {@code "true"} to create a draft (unpublished) release,
     *                           {@code "false"} to create a published one - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "prerelease"} -> {@code "true"} to identify the release as a prerelease.
     *                           {@code "false"} to identify the release as a full release - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "discussion_category_name"} -> if specified, a discussion of the specified
     *                           category is created and linked to the release. The value must be a category that already
     *                           exists in the repository. For more information, see "Managing categories for
     *                           discussions in your repository." - [string]
     *                       </li>
     *                       <li>
     *                           {@code "generate_release_notes"} -> whether to automatically generate the name and body
     *                           for this release. If name is specified, the specified name will be used; otherwise,
     *                           a name will be automatically generated. If body is specified, the body will be pre-pended
     *                           to the automatically generated notes - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "make_latest"} -> specifies whether this release should be set as the latest
     *                           release for the repository. Drafts and predeceases cannot be set as latest. Defaults
     *                           to true for newly published releases. legacy specifies that the latest release should
     *                           be determined based on the release creation date and higher semantic
     *                           version - [string, default true]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return release as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#create-a-release">
     * Create a release</a>
     * @implNote this endpoint triggers notifications. Creating content too quickly using this endpoint may result in
     * secondary rate limiting. See "Secondary rate limits" and "Dealing with secondary rate limits" for details.
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/releases")
    public <T> T createRelease(String owner, String repo, String tagName, Params bodyParams,
                               ReturnFormat format) throws IOException {
        return returnRelease(sendPostRequest(REPOS_PATH + owner + "/" + repo + RELEASES_PATH,
                createReleasePayload(tagName, bodyParams)), format);
    }

    /**
     * Method to generate a name and body describing a release. The body content will be markdown formatted and contain
     * information like the changes since last release and users who contributed. The generated release notes are not
     * saved anywhere. They are intended to be generated and used when creating a new release
     *
     * @param repository: the repository where create the release notes content
     * @param tagName:    the tag name for the release. This can be an existing tag or a new one
     * @return release notes content as of {@link ReleaseNotesContent} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#generate-release-notes-content-for-a-release">
     * Generate release notes content for a release</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/releases/generate-notes")
    public ReleaseNotesContent generateReleaseNotesContent(Repository repository, String tagName) throws IOException {
        return generateReleaseNotesContent(repository.getOwner().getLogin(), repository.getName(), tagName,
                LIBRARY_OBJECT);
    }

    /**
     * Method to generate a name and body describing a release. The body content will be markdown formatted and contain
     * information like the changes since last release and users who contributed. The generated release notes are not
     * saved anywhere. They are intended to be generated and used when creating a new release
     *
     * @param repository: the repository where create the release notes content
     * @param tagName:    the tag name for the release. This can be an existing tag or a new one
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return release notes content as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#generate-release-notes-content-for-a-release">
     * Generate release notes content for a release</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/releases/generate-notes")
    public <T> T generateReleaseNotesContent(Repository repository, String tagName, ReturnFormat format) throws IOException {
        return generateReleaseNotesContent(repository.getOwner().getLogin(), repository.getName(), tagName, format);
    }

    /**
     * Method to generate a name and body describing a release. The body content will be markdown formatted and contain
     * information like the changes since last release and users who contributed. The generated release notes are not
     * saved anywhere. They are intended to be generated and used when creating a new release
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param tagName: the tag name for the release. This can be an existing tag or a new one
     * @return release notes content as of {@link ReleaseNotesContent} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#generate-release-notes-content-for-a-release">
     * Generate release notes content for a release</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/releases/generate-notes")
    public ReleaseNotesContent generateReleaseNotesContent(String owner, String repo, String tagName) throws IOException {
        return generateReleaseNotesContent(owner, repo, tagName, LIBRARY_OBJECT);
    }

    /**
     * Method to generate a name and body describing a release. The body content will be markdown formatted and contain
     * information like the changes since last release and users who contributed. The generated release notes are not
     * saved anywhere. They are intended to be generated and used when creating a new release
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param tagName: the tag name for the release. This can be an existing tag or a new one
     * @param format   :              return type formatter -> {@link ReturnFormat}
     * @return release notes content as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#generate-release-notes-content-for-a-release">
     * Generate release notes content for a release</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/releases/generate-notes")
    public <T> T generateReleaseNotesContent(String owner, String repo, String tagName,
                                             ReturnFormat format) throws IOException {
        return generateReleaseNotesContent(owner, repo, tagName, null, format);
    }

    /**
     * Method to generate a name and body describing a release. The body content will be markdown formatted and contain
     * information like the changes since last release and users who contributed. The generated release notes are not
     * saved anywhere. They are intended to be generated and used when creating a new release
     *
     * @param repository: the repository where create the release notes content
     * @param tagName:    the tag name for the release. This can be an existing tag or a new one
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "target_commitish"} -> specifies the commitish value that determines where the
     *                           Git tag is created from. Can be any branch or commit SHA. Unused if the Git tag already
     *                           exists. Default: the repository's default branch (usually master) - [string]
     *                       </li>
     *                       <li>
     *                           {@code "previous_tag_name"} -> the name of the previous tag to use as the starting
     *                           point for the release notes. Use to manually specify the range for the set of changes
     *                           considered as part this release - [string]
     *                       </li>
     *                       <li>
     *                           {@code "configuration_file_path"} -> specifies a path to a file in the repository
     *                           containing configuration settings used for generating the release notes. If unspecified,
     *                           the configuration file located in the repository at {@code ".github/release.yml"} or
     *                           {@code ".github/release.yaml} will be used. If that is not present, the default
     *                           configuration will be used - [string]
     *                       </li>
     *                    </ul>
     * @return release notes content as of {@link ReleaseNotesContent} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#generate-release-notes-content-for-a-release">
     * Generate release notes content for a release</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/releases/generate-notes")
    public ReleaseNotesContent generateReleaseNotesContent(Repository repository, String tagName,
                                                           Params bodyParams) throws IOException {
        return generateReleaseNotesContent(repository.getOwner().getLogin(), repository.getName(), tagName, bodyParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to generate a name and body describing a release. The body content will be markdown formatted and contain
     * information like the changes since last release and users who contributed. The generated release notes are not
     * saved anywhere. They are intended to be generated and used when creating a new release
     *
     * @param repository: the repository where create the release notes content
     * @param tagName:    the tag name for the release. This can be an existing tag or a new one
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "target_commitish"} -> specifies the commitish value that determines where the
     *                           Git tag is created from. Can be any branch or commit SHA. Unused if the Git tag already
     *                           exists. Default: the repository's default branch (usually master) - [string]
     *                       </li>
     *                       <li>
     *                           {@code "previous_tag_name"} -> the name of the previous tag to use as the starting
     *                           point for the release notes. Use to manually specify the range for the set of changes
     *                           considered as part this release - [string]
     *                       </li>
     *                       <li>
     *                           {@code "configuration_file_path"} -> specifies a path to a file in the repository
     *                           containing configuration settings used for generating the release notes. If unspecified,
     *                           the configuration file located in the repository at {@code ".github/release.yml"} or
     *                           {@code ".github/release.yaml} will be used. If that is not present, the default
     *                           configuration will be used - [string]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return release notes content as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#generate-release-notes-content-for-a-release">
     * Generate release notes content for a release</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/releases/generate-notes")
    public <T> T generateReleaseNotesContent(Repository repository, String tagName, Params bodyParams,
                                             ReturnFormat format) throws IOException {
        return generateReleaseNotesContent(repository.getOwner().getLogin(), repository.getName(), tagName, bodyParams,
                format);
    }

    /**
     * Method to generate a name and body describing a release. The body content will be markdown formatted and contain
     * information like the changes since last release and users who contributed. The generated release notes are not
     * saved anywhere. They are intended to be generated and used when creating a new release
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param tagName:    the tag name for the release. This can be an existing tag or a new one
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "target_commitish"} -> specifies the commitish value that determines where the
     *                           Git tag is created from. Can be any branch or commit SHA. Unused if the Git tag already
     *                           exists. Default: the repository's default branch (usually master) - [string]
     *                       </li>
     *                       <li>
     *                           {@code "previous_tag_name"} -> the name of the previous tag to use as the starting
     *                           point for the release notes. Use to manually specify the range for the set of changes
     *                           considered as part this release - [string]
     *                       </li>
     *                       <li>
     *                           {@code "configuration_file_path"} -> specifies a path to a file in the repository
     *                           containing configuration settings used for generating the release notes. If unspecified,
     *                           the configuration file located in the repository at {@code ".github/release.yml"} or
     *                           {@code ".github/release.yaml} will be used. If that is not present, the default
     *                           configuration will be used - [string]
     *                       </li>
     *                    </ul>
     * @return release notes content as of {@link ReleaseNotesContent} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#generate-release-notes-content-for-a-release">
     * Generate release notes content for a release</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/releases/generate-notes")
    public ReleaseNotesContent generateReleaseNotesContent(String owner, String repo, String tagName,
                                                           Params bodyParams) throws IOException {
        return generateReleaseNotesContent(owner, repo, tagName, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to generate a name and body describing a release. The body content will be markdown formatted and contain
     * information like the changes since last release and users who contributed. The generated release notes are not
     * saved anywhere. They are intended to be generated and used when creating a new release
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param tagName:    the tag name for the release. This can be an existing tag or a new one
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "target_commitish"} -> specifies the commitish value that determines where the
     *                           Git tag is created from. Can be any branch or commit SHA. Unused if the Git tag already
     *                           exists. Default: the repository's default branch (usually master) - [string]
     *                       </li>
     *                       <li>
     *                           {@code "previous_tag_name"} -> the name of the previous tag to use as the starting
     *                           point for the release notes. Use to manually specify the range for the set of changes
     *                           considered as part this release - [string]
     *                       </li>
     *                       <li>
     *                           {@code "configuration_file_path"} -> specifies a path to a file in the repository
     *                           containing configuration settings used for generating the release notes. If unspecified,
     *                           the configuration file located in the repository at {@code ".github/release.yml"} or
     *                           {@code ".github/release.yaml} will be used. If that is not present, the default
     *                           configuration will be used - [string]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return release notes content as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#generate-release-notes-content-for-a-release">
     * Generate release notes content for a release</a>
     **/
    @Returner
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/releases/generate-notes")
    public <T> T generateReleaseNotesContent(String owner, String repo, String tagName, Params bodyParams,
                                             ReturnFormat format) throws IOException {
        String notesContentResponse = sendPostRequest(REPOS_PATH + owner + "/" + repo
                + RELEASES_GENERATE_NOTES_PATH, createReleasePayload(tagName, bodyParams));
        switch (format) {
            case JSON:
                return (T) new JSONObject(notesContentResponse);
            case LIBRARY_OBJECT:
                return (T) new ReleaseNotesContent(new JSONObject(notesContentResponse));
            default:
                return (T) notesContentResponse;
        }
    }

    /**
     * Method to view the latest published full release for the repository. <br>
     * The latest release is the most recent non-prerelease, non-draft release, sorted by the {@code "created_at"} attribute.
     * The {@code "created_at"} attribute is the date of the commit used for the release, and not the date when the
     * release was drafted or published
     *
     * @param repository: the repository from fetch the release
     * @return release as of {@link Release} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#create-a-release">
     * Get the latest release</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/latest")
    public Release getLatestRelease(Repository repository) throws IOException {
        return getLatestRelease(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to view the latest published full release for the repository. <br>
     * The latest release is the most recent non-prerelease, non-draft release, sorted by the {@code "created_at"} attribute.
     * The {@code "created_at"} attribute is the date of the commit used for the release, and not the date when the
     * release was drafted or published
     *
     * @param repository: the repository from fetch the release
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return release as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#create-a-release">
     * Get the latest release</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/latest")
    public <T> T getLatestRelease(Repository repository, ReturnFormat format) throws IOException {
        return getLatestRelease(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to view the latest published full release for the repository. <br>
     * The latest release is the most recent non-prerelease, non-draft release, sorted by the {@code "created_at"} attribute.
     * The {@code "created_at"} attribute is the date of the commit used for the release, and not the date when the
     * release was drafted or published
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return release as of {@link Release} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#create-a-release">
     * Get the latest release</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/latest")
    public Release getLatestRelease(String owner, String repo) throws IOException {
        return getLatestRelease(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to view the latest published full release for the repository. <br>
     * The latest release is the most recent non-prerelease, non-draft release, sorted by the {@code "created_at"} attribute.
     * The {@code "created_at"} attribute is the date of the commit used for the release, and not the date when the
     * release was drafted or published
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return release as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#create-a-release">
     * Get the latest release</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/latest")
    public <T> T getLatestRelease(String owner, String repo, ReturnFormat format) throws IOException {
        return returnRelease(sendGetRequest(REPOS_PATH + owner + "/" + repo + RELEASES_LATEST_PATH), format);
    }

    /**
     * Method to get a published release with the specified tag
     *
     * @param repository: the repository from fetch the release
     * @param tag:        tag parameter
     * @return release as of {@link Release} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#get-a-release-by-tag-name">
     * Get a release by tag name</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/tags/{tag}")
    public Release getReleaseByTagName(Repository repository, String tag) throws IOException {
        return getReleaseByTagName(repository.getOwner().getLogin(), repository.getName(), tag, LIBRARY_OBJECT);
    }

    /**
     * Method to get a published release with the specified tag
     *
     * @param repository: the repository from fetch the release
     * @param tag:        tag parameter
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return release as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#get-a-release-by-tag-name">
     * Get a release by tag name</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/tags/{tag}")
    public <T> T getReleaseByTagName(Repository repository, String tag, ReturnFormat format) throws IOException {
        return getReleaseByTagName(repository.getOwner().getLogin(), repository.getName(), tag, format);
    }

    /**
     * Method to get a published release with the specified tag
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param tag:   tag parameter
     * @return release as of {@link Release} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#get-a-release-by-tag-name">
     * Get a release by tag name</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/tags/{tag}")
    public Release getReleaseByTagName(String owner, String repo, String tag) throws IOException {
        return getReleaseByTagName(owner, repo, tag, LIBRARY_OBJECT);
    }

    /**
     * Method to get a published release with the specified tag
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param tag:   tag parameter
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return release as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#get-a-release-by-tag-name">
     * Get a release by tag name</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/tags/{tag}")
    public <T> T getReleaseByTagName(String owner, String repo, String tag, ReturnFormat format) throws IOException {
        return returnRelease(sendGetRequest(REPOS_PATH + owner + "/" + repo + RELEASES_TAGS_PATH + tag), format);
    }

    /**
     * Method to get a release
     *
     * @param repository: the repository from fetch the release
     * @param releaseId:  the unique identifier of the release
     * @return release as of {@link Release} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#get-a-release">
     * Get a release</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/{release_id}")
    public Release getRelease(Repository repository, long releaseId) throws IOException {
        return getRelease(repository.getOwner().getLogin(), repository.getName(), releaseId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a release
     *
     * @param repository: the repository from fetch the release
     * @param releaseId:  the unique identifier of the release
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return release as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#get-a-release">
     * Get a release</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/{release_id}")
    public <T> T getRelease(Repository repository, long releaseId, ReturnFormat format) throws IOException {
        return getRelease(repository.getOwner().getLogin(), repository.getName(), releaseId, format);
    }

    /**
     * Method to get a release
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param releaseId: the unique identifier of the release
     * @return release as of {@link Release} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#get-a-release">
     * Get a release</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/{release_id}")
    public Release getRelease(String owner, String repo, long releaseId) throws IOException {
        return getRelease(owner, repo, releaseId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a release
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param releaseId: the unique identifier of the release
     * @param format     :              return type formatter -> {@link ReturnFormat}
     * @return release as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#get-a-release">
     * Get a release</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/{release_id}")
    public <T> T getRelease(String owner, String repo, long releaseId, ReturnFormat format) throws IOException {
        return returnRelease(sendGetRequest(REPOS_PATH + owner + "/" + repo + RELEASES_QUERY_PATH + releaseId),
                format);
    }

    /**
     * Method to update a release
     *
     * @param repository: the repository where update the release
     * @param release:    the release to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "tag_name"} -> the name of the tag - [string]
     *                       </li>
     *                       <li>
     *                           {@code "target_commitish"} -> specifies the commitish value that determines where the
     *                           Git tag is created from. Can be any branch or commit SHA. Unused if the Git tag already
     *                           exists. Default: the repository's default branch (usually master) - [string]
     *                       </li>
     *                       <li>
     *                           {@code "name"} -> the name of the release - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> text describing the contents of the tag - [string]
     *                       </li>
     *                       <li>
     *                           {@code "draft"} -> {@code "true"} to create a draft (unpublished) release,
     *                           {@code "false"} to create a published one - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "prerelease"} -> {@code "true"} to identify the release as a prerelease.
     *                           {@code "false"} to identify the release as a full release - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "discussion_category_name"} -> if specified, a discussion of the specified
     *                           category is created and linked to the release. The value must be a category that already
     *                           exists in the repository. For more information, see "Managing categories for
     *                           discussions in your repository." - [string]
     *                       </li>
     *                       <li>
     *                           {@code "generate_release_notes"} -> whether to automatically generate the name and body
     *                           for this release. If name is specified, the specified name will be used; otherwise,
     *                           a name will be automatically generated. If body is specified, the body will be pre-pended
     *                           to the automatically generated notes - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "make_latest"} -> specifies whether this release should be set as the latest
     *                           release for the repository. Drafts and predeceases cannot be set as latest. Defaults
     *                           to true for newly published releases. legacy specifies that the latest release should
     *                           be determined based on the release creation date and higher semantic
     *                           version - [string, default true]
     *                       </li>
     *                    </ul>
     * @return release as of {@link Release} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#update-a-release">
     * Update a release</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/releases/{release_id}")
    public Release updateRelease(Repository repository, Release release, Params bodyParams) throws IOException {
        return updateRelease(repository.getOwner().getLogin(), repository.getName(), release.getId(), bodyParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to update a release
     *
     * @param repository: the repository where update the release
     * @param release:    the release to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "tag_name"} -> the name of the tag - [string]
     *                       </li>
     *                       <li>
     *                           {@code "target_commitish"} -> specifies the commitish value that determines where the
     *                           Git tag is created from. Can be any branch or commit SHA. Unused if the Git tag already
     *                           exists. Default: the repository's default branch (usually master) - [string]
     *                       </li>
     *                       <li>
     *                           {@code "name"} -> the name of the release - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> text describing the contents of the tag - [string]
     *                       </li>
     *                       <li>
     *                           {@code "draft"} -> {@code "true"} to create a draft (unpublished) release,
     *                           {@code "false"} to create a published one - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "prerelease"} -> {@code "true"} to identify the release as a prerelease.
     *                           {@code "false"} to identify the release as a full release - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "discussion_category_name"} -> if specified, a discussion of the specified
     *                           category is created and linked to the release. The value must be a category that already
     *                           exists in the repository. For more information, see "Managing categories for
     *                           discussions in your repository." - [string]
     *                       </li>
     *                       <li>
     *                           {@code "generate_release_notes"} -> whether to automatically generate the name and body
     *                           for this release. If name is specified, the specified name will be used; otherwise,
     *                           a name will be automatically generated. If body is specified, the body will be pre-pended
     *                           to the automatically generated notes - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "make_latest"} -> specifies whether this release should be set as the latest
     *                           release for the repository. Drafts and predeceases cannot be set as latest. Defaults
     *                           to true for newly published releases. legacy specifies that the latest release should
     *                           be determined based on the release creation date and higher semantic
     *                           version - [string, default true]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return release as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#update-a-release">
     * Update a release</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/releases/{release_id}")
    public <T> T updateRelease(Repository repository, Release release, Params bodyParams,
                               ReturnFormat format) throws IOException {
        return updateRelease(repository.getOwner().getLogin(), repository.getName(), release.getId(), bodyParams, format);
    }

    /**
     * Method to update a release
     *
     * @param repository: the repository where update the release
     * @param releaseId:  the unique identifier of the release
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "tag_name"} -> the name of the tag - [string]
     *                       </li>
     *                       <li>
     *                           {@code "target_commitish"} -> specifies the commitish value that determines where the
     *                           Git tag is created from. Can be any branch or commit SHA. Unused if the Git tag already
     *                           exists. Default: the repository's default branch (usually master) - [string]
     *                       </li>
     *                       <li>
     *                           {@code "name"} -> the name of the release - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> text describing the contents of the tag - [string]
     *                       </li>
     *                       <li>
     *                           {@code "draft"} -> {@code "true"} to create a draft (unpublished) release,
     *                           {@code "false"} to create a published one - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "prerelease"} -> {@code "true"} to identify the release as a prerelease.
     *                           {@code "false"} to identify the release as a full release - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "discussion_category_name"} -> if specified, a discussion of the specified
     *                           category is created and linked to the release. The value must be a category that already
     *                           exists in the repository. For more information, see "Managing categories for
     *                           discussions in your repository." - [string]
     *                       </li>
     *                       <li>
     *                           {@code "generate_release_notes"} -> whether to automatically generate the name and body
     *                           for this release. If name is specified, the specified name will be used; otherwise,
     *                           a name will be automatically generated. If body is specified, the body will be pre-pended
     *                           to the automatically generated notes - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "make_latest"} -> specifies whether this release should be set as the latest
     *                           release for the repository. Drafts and predeceases cannot be set as latest. Defaults
     *                           to true for newly published releases. legacy specifies that the latest release should
     *                           be determined based on the release creation date and higher semantic
     *                           version - [string, default true]
     *                       </li>
     *                    </ul>
     * @return release as of {@link Release} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#update-a-release">
     * Update a release</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/releases/{release_id}")
    public Release updateRelease(Repository repository, long releaseId, Params bodyParams) throws IOException {
        return updateRelease(repository.getOwner().getLogin(), repository.getName(), releaseId, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update a release
     *
     * @param repository: the repository where update the release
     * @param releaseId:  the unique identifier of the release
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "tag_name"} -> the name of the tag - [string]
     *                       </li>
     *                       <li>
     *                           {@code "target_commitish"} -> specifies the commitish value that determines where the
     *                           Git tag is created from. Can be any branch or commit SHA. Unused if the Git tag already
     *                           exists. Default: the repository's default branch (usually master) - [string]
     *                       </li>
     *                       <li>
     *                           {@code "name"} -> the name of the release - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> text describing the contents of the tag - [string]
     *                       </li>
     *                       <li>
     *                           {@code "draft"} -> {@code "true"} to create a draft (unpublished) release,
     *                           {@code "false"} to create a published one - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "prerelease"} -> {@code "true"} to identify the release as a prerelease.
     *                           {@code "false"} to identify the release as a full release - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "discussion_category_name"} -> if specified, a discussion of the specified
     *                           category is created and linked to the release. The value must be a category that already
     *                           exists in the repository. For more information, see "Managing categories for
     *                           discussions in your repository." - [string]
     *                       </li>
     *                       <li>
     *                           {@code "generate_release_notes"} -> whether to automatically generate the name and body
     *                           for this release. If name is specified, the specified name will be used; otherwise,
     *                           a name will be automatically generated. If body is specified, the body will be pre-pended
     *                           to the automatically generated notes - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "make_latest"} -> specifies whether this release should be set as the latest
     *                           release for the repository. Drafts and predeceases cannot be set as latest. Defaults
     *                           to true for newly published releases. legacy specifies that the latest release should
     *                           be determined based on the release creation date and higher semantic
     *                           version - [string, default true]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return release as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#update-a-release">
     * Update a release</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/releases/{release_id}")
    public <T> T updateRelease(Repository repository, long releaseId, Params bodyParams,
                               ReturnFormat format) throws IOException {
        return updateRelease(repository.getOwner().getLogin(), repository.getName(), releaseId, bodyParams, format);
    }

    /**
     * Method to update a release
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param release:    the release to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "tag_name"} -> the name of the tag - [string]
     *                       </li>
     *                       <li>
     *                           {@code "target_commitish"} -> specifies the commitish value that determines where the
     *                           Git tag is created from. Can be any branch or commit SHA. Unused if the Git tag already
     *                           exists. Default: the repository's default branch (usually master) - [string]
     *                       </li>
     *                       <li>
     *                           {@code "name"} -> the name of the release - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> text describing the contents of the tag - [string]
     *                       </li>
     *                       <li>
     *                           {@code "draft"} -> {@code "true"} to create a draft (unpublished) release,
     *                           {@code "false"} to create a published one - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "prerelease"} -> {@code "true"} to identify the release as a prerelease.
     *                           {@code "false"} to identify the release as a full release - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "discussion_category_name"} -> if specified, a discussion of the specified
     *                           category is created and linked to the release. The value must be a category that already
     *                           exists in the repository. For more information, see "Managing categories for
     *                           discussions in your repository." - [string]
     *                       </li>
     *                       <li>
     *                           {@code "generate_release_notes"} -> whether to automatically generate the name and body
     *                           for this release. If name is specified, the specified name will be used; otherwise,
     *                           a name will be automatically generated. If body is specified, the body will be pre-pended
     *                           to the automatically generated notes - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "make_latest"} -> specifies whether this release should be set as the latest
     *                           release for the repository. Drafts and predeceases cannot be set as latest. Defaults
     *                           to true for newly published releases. legacy specifies that the latest release should
     *                           be determined based on the release creation date and higher semantic
     *                           version - [string, default true]
     *                       </li>
     *                    </ul>
     * @return release as of {@link Release} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#update-a-release">
     * Update a release</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/releases/{release_id}")
    public Release updateRelease(String owner, String repo, Release release, Params bodyParams) throws IOException {
        return updateRelease(owner, repo, release.getId(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update a release
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param release:    the release to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "tag_name"} -> the name of the tag - [string]
     *                       </li>
     *                       <li>
     *                           {@code "target_commitish"} -> specifies the commitish value that determines where the
     *                           Git tag is created from. Can be any branch or commit SHA. Unused if the Git tag already
     *                           exists. Default: the repository's default branch (usually master) - [string]
     *                       </li>
     *                       <li>
     *                           {@code "name"} -> the name of the release - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> text describing the contents of the tag - [string]
     *                       </li>
     *                       <li>
     *                           {@code "draft"} -> {@code "true"} to create a draft (unpublished) release,
     *                           {@code "false"} to create a published one - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "prerelease"} -> {@code "true"} to identify the release as a prerelease.
     *                           {@code "false"} to identify the release as a full release - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "discussion_category_name"} -> if specified, a discussion of the specified
     *                           category is created and linked to the release. The value must be a category that already
     *                           exists in the repository. For more information, see "Managing categories for
     *                           discussions in your repository." - [string]
     *                       </li>
     *                       <li>
     *                           {@code "generate_release_notes"} -> whether to automatically generate the name and body
     *                           for this release. If name is specified, the specified name will be used; otherwise,
     *                           a name will be automatically generated. If body is specified, the body will be pre-pended
     *                           to the automatically generated notes - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "make_latest"} -> specifies whether this release should be set as the latest
     *                           release for the repository. Drafts and predeceases cannot be set as latest. Defaults
     *                           to true for newly published releases. legacy specifies that the latest release should
     *                           be determined based on the release creation date and higher semantic
     *                           version - [string, default true]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return release as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#update-a-release">
     * Update a release</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/releases/{release_id}")
    public <T> T updateRelease(String owner, String repo, Release release, Params bodyParams,
                               ReturnFormat format) throws IOException {
        return updateRelease(owner, repo, release.getId(), bodyParams, format);
    }

    /**
     * Method to update a release
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param releaseId:  the unique identifier of the release
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "tag_name"} -> the name of the tag - [string]
     *                       </li>
     *                       <li>
     *                           {@code "target_commitish"} -> specifies the commitish value that determines where the
     *                           Git tag is created from. Can be any branch or commit SHA. Unused if the Git tag already
     *                           exists. Default: the repository's default branch (usually master) - [string]
     *                       </li>
     *                       <li>
     *                           {@code "name"} -> the name of the release - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> text describing the contents of the tag - [string]
     *                       </li>
     *                       <li>
     *                           {@code "draft"} -> {@code "true"} to create a draft (unpublished) release,
     *                           {@code "false"} to create a published one - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "prerelease"} -> {@code "true"} to identify the release as a prerelease.
     *                           {@code "false"} to identify the release as a full release - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "discussion_category_name"} -> if specified, a discussion of the specified
     *                           category is created and linked to the release. The value must be a category that already
     *                           exists in the repository. For more information, see "Managing categories for
     *                           discussions in your repository." - [string]
     *                       </li>
     *                       <li>
     *                           {@code "generate_release_notes"} -> whether to automatically generate the name and body
     *                           for this release. If name is specified, the specified name will be used; otherwise,
     *                           a name will be automatically generated. If body is specified, the body will be pre-pended
     *                           to the automatically generated notes - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "make_latest"} -> specifies whether this release should be set as the latest
     *                           release for the repository. Drafts and predeceases cannot be set as latest. Defaults
     *                           to true for newly published releases. legacy specifies that the latest release should
     *                           be determined based on the release creation date and higher semantic
     *                           version - [string, default true]
     *                       </li>
     *                    </ul>
     * @return release as of {@link Release} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#update-a-release">
     * Update a release</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/releases/{release_id}")
    public Release updateRelease(String owner, String repo, long releaseId, Params bodyParams) throws IOException {
        return updateRelease(owner, repo, releaseId, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update a release
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param releaseId:  the unique identifier of the release
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "tag_name"} -> the name of the tag - [string]
     *                       </li>
     *                       <li>
     *                           {@code "target_commitish"} -> specifies the commitish value that determines where the
     *                           Git tag is created from. Can be any branch or commit SHA. Unused if the Git tag already
     *                           exists. Default: the repository's default branch (usually master) - [string]
     *                       </li>
     *                       <li>
     *                           {@code "name"} -> the name of the release - [string]
     *                       </li>
     *                       <li>
     *                           {@code "body"} -> text describing the contents of the tag - [string]
     *                       </li>
     *                       <li>
     *                           {@code "draft"} -> {@code "true"} to create a draft (unpublished) release,
     *                           {@code "false"} to create a published one - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "prerelease"} -> {@code "true"} to identify the release as a prerelease.
     *                           {@code "false"} to identify the release as a full release - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "discussion_category_name"} -> if specified, a discussion of the specified
     *                           category is created and linked to the release. The value must be a category that already
     *                           exists in the repository. For more information, see "Managing categories for
     *                           discussions in your repository." - [string]
     *                       </li>
     *                       <li>
     *                           {@code "generate_release_notes"} -> whether to automatically generate the name and body
     *                           for this release. If name is specified, the specified name will be used; otherwise,
     *                           a name will be automatically generated. If body is specified, the body will be pre-pended
     *                           to the automatically generated notes - [boolean, default false]
     *                       </li>
     *                       <li>
     *                           {@code "make_latest"} -> specifies whether this release should be set as the latest
     *                           release for the repository. Drafts and predeceases cannot be set as latest. Defaults
     *                           to true for newly published releases. legacy specifies that the latest release should
     *                           be determined based on the release creation date and higher semantic
     *                           version - [string, default true]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return release as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#update-a-release">
     * Update a release</a>
     **/
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/releases/{release_id}")
    public <T> T updateRelease(String owner, String repo, long releaseId, Params bodyParams,
                               ReturnFormat format) throws IOException {
        return returnRelease(sendPatchRequest(REPOS_PATH + owner + "/" + repo + RELEASES_QUERY_PATH
                + releaseId, bodyParams), format);
    }

    /**
     * Method to create a release payload
     *
     * @param tagName  : the name of the tag
     * @param payload: payload with extra params
     * @return release payloads as {@link Params}
     **/
    private Params createReleasePayload(String tagName, Params payload) {
        if (payload == null)
            payload = new Params();
        payload.addParam("tag_name", tagName);
        return payload;
    }

    /**
     * Method to create a release
     *
     * @param releaseResponse : obtained from GitHub's response
     * @param format          :              return type formatter -> {@link ReturnFormat}
     * @return release as {@code "format"} defines
     **/
    @Returner
    private <T> T returnRelease(String releaseResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(releaseResponse);
            case LIBRARY_OBJECT:
                return (T) new Release(new JSONObject(releaseResponse));
            default:
                return (T) releaseResponse;
        }
    }

    /**
     * Method to delete a release
     *
     * @param repository: the repository where delete the release
     * @param release:    the release to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#delete-a-release">
     * Delete a release</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/releases/{release_id}")
    public boolean deleteRelease(Repository repository, Release release) {
        return deleteRelease(repository.getOwner().getLogin(), repository.getName(), release.getId());
    }

    /**
     * Method to delete a release
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param release: the release to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#delete-a-release">
     * Delete a release</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/releases/{release_id}")
    public boolean deleteRelease(String owner, String repo, Release release) {
        return deleteRelease(owner, repo, release.getId());
    }

    /**
     * Method to delete a release
     *
     * @param repository: the repository where delete the release
     * @param releaseId:  the unique identifier of the release
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#delete-a-release">
     * Delete a release</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/releases/{release_id}")
    public boolean deleteRelease(Repository repository, long releaseId) {
        return deleteRelease(repository.getOwner().getLogin(), repository.getName(), releaseId);
    }

    /**
     * Method to delete a release
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param releaseId: the unique identifier of the release
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#delete-a-release">
     * Delete a release</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/releases/{release_id}")
    public boolean deleteRelease(String owner, String repo, long releaseId) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + RELEASES_QUERY_PATH + releaseId);
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
