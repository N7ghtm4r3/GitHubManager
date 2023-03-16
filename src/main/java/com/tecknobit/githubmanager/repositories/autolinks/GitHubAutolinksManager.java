package com.tecknobit.githubmanager.repositories.autolinks;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.repositories.autolinks.records.Autolink;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubAutolinksManager} class is useful to manage all GitHub's autolinks endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/autolinks">
 * Repository autolinks</a>
 * @see GitHubManager
 **/
public class GitHubAutolinksManager extends GitHubManager {

    /**
     * {@code AUTOLINKS_PATH} constant for {@code "/autolinks"} path
     **/
    public static final String AUTOLINKS_PATH = "/autolinks";

    /**
     * Constructor to init a {@link GitHubAutolinksManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubAutolinksManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubAutolinksManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubAutolinksManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubAutolinksManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubAutolinksManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubAutolinksManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubAutolinksManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubAutolinksManager} <br>
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
    public GitHubAutolinksManager() {
        super();
    }

    /**
     * Method to get a list of autolinks configured for the given repository. <br>
     * Information about autolinks are only available to repository administrators.
     *
     * @param repository: the repository from fetch the list
     * @return autolinks list as {@link ArrayList} of {@link Autolink} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/autolinks#list-all-autolinks-of-a-repository">
     * List all autolinks of a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/autolinks")
    public ArrayList<Autolink> getAllRepositoryAutolinks(Repository repository) throws IOException {
        return getAllRepositoryAutolinks(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of autolinks configured for the given repository. <br>
     * Information about autolinks are only available to repository administrators.
     *
     * @param repository: the repository from fetch the list
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return autolinks list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/autolinks#list-all-autolinks-of-a-repository">
     * List all autolinks of a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/autolinks")
    public <T> T getAllRepositoryAutolinks(Repository repository, ReturnFormat format) throws IOException {
        return getAllRepositoryAutolinks(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get a list of autolinks configured for the given repository. <br>
     * Information about autolinks are only available to repository administrators.
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return autolinks list as {@link ArrayList} of {@link Autolink} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/autolinks#list-all-autolinks-of-a-repository">
     * List all autolinks of a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/autolinks")
    public ArrayList<Autolink> getAllRepositoryAutolinks(String owner, String repo) throws IOException {
        return getAllRepositoryAutolinks(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of autolinks configured for the given repository. <br>
     * Information about autolinks are only available to repository administrators.
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return autolinks list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/autolinks#list-all-autolinks-of-a-repository">
     * List all autolinks of a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/autolinks")
    public <T> T getAllRepositoryAutolinks(String owner, String repo, ReturnFormat format) throws IOException {
        return getAllRepositoryAutolinks(owner, repo, -1, format);
    }

    /**
     * Method to get a list of autolinks configured for the given repository. <br>
     * Information about autolinks are only available to repository administrators.
     *
     * @param repository: the repository from fetch the list
     * @param page:       page number of the results to fetch
     * @return autolinks list as {@link ArrayList} of {@link Autolink} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/autolinks#list-all-autolinks-of-a-repository">
     * List all autolinks of a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/autolinks")
    public ArrayList<Autolink> getAllRepositoryAutolinks(Repository repository, int page) throws IOException {
        return getAllRepositoryAutolinks(repository.getOwner().getLogin(), repository.getName(), page, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of autolinks configured for the given repository. <br>
     * Information about autolinks are only available to repository administrators.
     *
     * @param repository: the repository from fetch the list
     * @param page:       page number of the results to fetch
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return autolinks list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/autolinks#list-all-autolinks-of-a-repository">
     * List all autolinks of a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/autolinks")
    public <T> T getAllRepositoryAutolinks(Repository repository, int page, ReturnFormat format) throws IOException {
        return getAllRepositoryAutolinks(repository.getOwner().getLogin(), repository.getName(), page, format);
    }

    /**
     * Method to get a list of autolinks configured for the given repository. <br>
     * Information about autolinks are only available to repository administrators.
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param page:  page number of the results to fetch
     * @return autolinks list as {@link ArrayList} of {@link Autolink} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/autolinks#list-all-autolinks-of-a-repository">
     * List all autolinks of a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/autolinks")
    public ArrayList<Autolink> getAllRepositoryAutolinks(String owner, String repo, int page) throws IOException {
        return getAllRepositoryAutolinks(owner, repo, page, LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of autolinks configured for the given repository. <br>
     * Information about autolinks are only available to repository administrators.
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param page:  page number of the results to fetch
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return autolinks list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/autolinks#list-all-autolinks-of-a-repository">
     * List all autolinks of a repository</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/autolinks")
    public <T> T getAllRepositoryAutolinks(String owner, String repo, int page, ReturnFormat format) throws IOException {
        String reqUrl = REPOS_PATH + owner + "/" + repo + AUTOLINKS_PATH;
        if (page != -1)
            reqUrl += "?page=" + page;
        String autolinksResponse = sendGetRequest(reqUrl);
        switch (format) {
            case JSON:
                return (T) new JSONArray(autolinksResponse);
            case LIBRARY_OBJECT:
                ArrayList<Autolink> autolinks = new ArrayList<>();
                JSONArray jAutolinks = new JSONArray(autolinksResponse);
                for (int j = 0; j < jAutolinks.length(); j++)
                    autolinks.add(new Autolink(jAutolinks.getJSONObject(j)));
                return (T) autolinks;
            default:
                return (T) autolinksResponse;
        }
    }

    /**
     * Method to create an autolink reference for a repository
     *
     * @param repository:     the repository where create the autolink
     * @param keyPrefix:      this prefix appended by certain characters will generate a link any time it is found in an issue,
     *                        pull request, or commit
     * @param urlTemplate:    the URL must contain <num> for the reference number. <num> matches different characters
     *                        depending on the value of is_alphanumeric
     * @param isAlphanumeric: whether this autolink reference matches alphanumeric characters. If true, the <num> parameter
     *                        of the {@code "url_template"} matches alphanumeric characters A-Z (case insensitive), 0-9, and -.
     *                        If false, this autolink reference only matches numeric characters
     * @return autolink as {@link Autolink} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/autolinks#create-an-autolink-reference-for-a-repository">
     * Create an autolink reference for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/autolinks")
    public Autolink createRepositoryAutolinkReference(Repository repository, String keyPrefix, String urlTemplate,
                                                      boolean isAlphanumeric) throws IOException {
        return createRepositoryAutolinkReference(repository.getOwner().getLogin(), repository.getName(), keyPrefix,
                urlTemplate, isAlphanumeric, LIBRARY_OBJECT);
    }

    /**
     * Method to create an autolink reference for a repository
     *
     * @param repository:     the repository where create the autolink
     * @param keyPrefix:      this prefix appended by certain characters will generate a link any time it is found in an issue,
     *                        pull request, or commit
     * @param urlTemplate:    the URL must contain <num> for the reference number. <num> matches different characters
     *                        depending on the value of is_alphanumeric
     * @param isAlphanumeric: whether this autolink reference matches alphanumeric characters. If true, the <num> parameter
     *                        of the {@code "url_template"} matches alphanumeric characters A-Z (case insensitive), 0-9, and -.
     *                        If false, this autolink reference only matches numeric characters
     * @param format          :              return type formatter -> {@link ReturnFormat}
     * @return autolink as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/autolinks#create-an-autolink-reference-for-a-repository">
     * Create an autolink reference for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/autolinks")
    public <T> T createRepositoryAutolinkReference(Repository repository, String keyPrefix, String urlTemplate,
                                                   boolean isAlphanumeric, ReturnFormat format) throws IOException {
        return createRepositoryAutolinkReference(repository.getOwner().getLogin(), repository.getName(), keyPrefix,
                urlTemplate, isAlphanumeric, format);
    }

    /**
     * Method to create an autolink reference for a repository
     *
     * @param owner:          the account owner of the repository. The name is not case-sensitive
     * @param repo:           the name of the repository. The name is not case-sensitive
     * @param keyPrefix:      this prefix appended by certain characters will generate a link any time it is found in an issue,
     *                        pull request, or commit
     * @param urlTemplate:    the URL must contain <num> for the reference number. <num> matches different characters
     *                        depending on the value of is_alphanumeric
     * @param isAlphanumeric: whether this autolink reference matches alphanumeric characters. If true, the <num> parameter
     *                        of the {@code "url_template"} matches alphanumeric characters A-Z (case insensitive), 0-9, and -.
     *                        If false, this autolink reference only matches numeric characters
     * @return autolink as {@link Autolink} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/autolinks#create-an-autolink-reference-for-a-repository">
     * Create an autolink reference for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/autolinks")
    public Autolink createRepositoryAutolinkReference(String owner, String repo, String keyPrefix, String urlTemplate,
                                                      boolean isAlphanumeric) throws IOException {
        return createRepositoryAutolinkReference(owner, repo, keyPrefix, urlTemplate, isAlphanumeric, LIBRARY_OBJECT);
    }

    /**
     * Method to create an autolink reference for a repository
     *
     * @param owner:          the account owner of the repository. The name is not case-sensitive
     * @param repo:           the name of the repository. The name is not case-sensitive
     * @param keyPrefix:      this prefix appended by certain characters will generate a link any time it is found in an issue,
     *                        pull request, or commit
     * @param urlTemplate:    the URL must contain <num> for the reference number. <num> matches different characters
     *                        depending on the value of is_alphanumeric
     * @param isAlphanumeric: whether this autolink reference matches alphanumeric characters. If true, the <num> parameter
     *                        of the {@code "url_template"} matches alphanumeric characters A-Z (case insensitive), 0-9, and -.
     *                        If false, this autolink reference only matches numeric characters
     * @param format          :              return type formatter -> {@link ReturnFormat}
     * @return autolink as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/autolinks#create-an-autolink-reference-for-a-repository">
     * Create an autolink reference for a repository</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/autolinks")
    public <T> T createRepositoryAutolinkReference(String owner, String repo, String keyPrefix, String urlTemplate,
                                                   boolean isAlphanumeric, ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("key_prefix", keyPrefix);
        payload.addParam("url_template", urlTemplate);
        payload.addParam("is_alphanumeric", isAlphanumeric);
        return returnAutolink(sendPostRequest(REPOS_PATH + owner + "/" + repo + AUTOLINKS_PATH, payload), format);
    }

    /**
     * Method to get an autolink reference by ID that was configured for the given repository. <br>
     * Information about autolinks are only available to repository administrators
     *
     * @param repository: the repository from fetch the autolink
     * @param autolinkId: the unique identifier of the autolink
     * @return autolink as {@link Autolink} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/autolinks#get-an-autolink-reference-of-a-repository">
     * Get an autolink reference of a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/autolinks/{autolink_id}")
    public Autolink getRepositoryAutolinkReference(Repository repository, long autolinkId) throws IOException {
        return getRepositoryAutolinkReference(repository.getOwner().getLogin(), repository.getName(), autolinkId,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get an autolink reference by ID that was configured for the given repository. <br>
     * Information about autolinks are only available to repository administrators
     *
     * @param repository: the repository from fetch the autolink
     * @param autolinkId: the unique identifier of the autolink
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return autolink as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/autolinks#get-an-autolink-reference-of-a-repository">
     * Get an autolink reference of a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/autolinks/{autolink_id}")
    public <T> T getRepositoryAutolinkReference(Repository repository, long autolinkId,
                                                ReturnFormat format) throws IOException {
        return getRepositoryAutolinkReference(repository.getOwner().getLogin(), repository.getName(), autolinkId, format);
    }

    /**
     * Method to get an autolink reference by ID that was configured for the given repository. <br>
     * Information about autolinks are only available to repository administrators
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param autolinkId: the unique identifier of the autolink
     * @return autolink as {@link Autolink} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/autolinks#get-an-autolink-reference-of-a-repository">
     * Get an autolink reference of a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/autolinks/{autolink_id}")
    public Autolink getRepositoryAutolinkReference(String owner, String repo, long autolinkId) throws IOException {
        return getRepositoryAutolinkReference(owner, repo, autolinkId, LIBRARY_OBJECT);
    }

    /**
     * Method to get an autolink reference by ID that was configured for the given repository. <br>
     * Information about autolinks are only available to repository administrators
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param autolinkId: the unique identifier of the autolink
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return autolink as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/autolinks#get-an-autolink-reference-of-a-repository">
     * Get an autolink reference of a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/autolinks/{autolink_id}")
    public <T> T getRepositoryAutolinkReference(String owner, String repo, long autolinkId,
                                                ReturnFormat format) throws IOException {
        return returnAutolink(sendGetRequest(REPOS_PATH + owner + "/" + repo + AUTOLINKS_PATH + "/" + autolinkId),
                format);
    }

    /**
     * Method to create an autolink
     *
     * @param autolinkResponse : obtained from GitHub's response
     * @param format           :              return type formatter -> {@link ReturnFormat}
     * @return autolink as {@code "format"} defines
     **/
    @Returner
    private <T> T returnAutolink(String autolinkResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(autolinkResponse);
            case LIBRARY_OBJECT:
                return (T) new Autolink(new JSONObject(autolinkResponse));
            default:
                return (T) autolinkResponse;
        }
    }

    /**
     * Method to delete a single autolink reference by ID that was configured for the given repository. <br>
     * Information about autolinks are only available to repository administrators
     *
     * @param repository: the repository where delete the autolink
     * @param autolink:   the autolink to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/autolinks#delete-an-autolink-reference-from-a-repository">
     * Delete an autolink reference from a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/autolinks/{autolink_id}")
    public boolean deleteRepositoryAutolinkReference(Repository repository, Autolink autolink) {
        return deleteRepositoryAutolinkReference(repository.getOwner().getLogin(), repository.getName(), autolink.getId());
    }

    /**
     * Method to delete a single autolink reference by ID that was configured for the given repository. <br>
     * Information about autolinks are only available to repository administrators
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param autolink: the autolink to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/autolinks#delete-an-autolink-reference-from-a-repository">
     * Delete an autolink reference from a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/autolinks/{autolink_id}")
    public boolean deleteRepositoryAutolinkReference(String owner, String repo, Autolink autolink) {
        return deleteRepositoryAutolinkReference(owner, repo, autolink.getId());
    }

    /**
     * Method to delete a single autolink reference by ID that was configured for the given repository. <br>
     * Information about autolinks are only available to repository administrators
     *
     * @param repository: the repository where delete the autolink
     * @param autolinkId: the unique identifier of the autolink
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/autolinks#delete-an-autolink-reference-from-a-repository">
     * Delete an autolink reference from a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/autolinks/{autolink_id}")
    public boolean deleteRepositoryAutolinkReference(Repository repository, long autolinkId) {
        return deleteRepositoryAutolinkReference(repository.getOwner().getLogin(), repository.getName(), autolinkId);
    }

    /**
     * Method to delete a single autolink reference by ID that was configured for the given repository. <br>
     * Information about autolinks are only available to repository administrators
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param autolinkId: the unique identifier of the autolink
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/autolinks#delete-an-autolink-reference-from-a-repository">
     * Delete an autolink reference from a repository</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/autolinks/{autolink_id}")
    public boolean deleteRepositoryAutolinkReference(String owner, String repo, long autolinkId) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + AUTOLINKS_PATH + "/" + autolinkId);
            if (apiRequest.getResponseStatusCode() != 204) {
                printErrorResponse();
                return false;
            }
            return false;
        } catch (IOException e) {
            printErrorResponse();
            return false;
        }
    }

}
