package com.tecknobit.githubmanager.pages;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.pages.records.PagesBuild;
import com.tecknobit.githubmanager.pages.records.PagesDeployment;
import com.tecknobit.githubmanager.pages.records.PagesHealthCheck;
import com.tecknobit.githubmanager.pages.records.PagesSite;
import com.tecknobit.githubmanager.pages.records.PagesSite.BuildType;
import com.tecknobit.githubmanager.pages.records.PagesSite.Source;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubPagesManager} class is useful to manage all GitHub's pages managers endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages">
 * Pages</a>
 * @see GitHubManager
 **/
public class GitHubPagesManager extends GitHubManager {

    /**
     * {@code PAGES_PATH} constant for {@code "/pages"} path
     **/
    public static final String PAGES_PATH = "/pages";

    /**
     * {@code PAGES_BUILDS_PATH} constant for {@code "/pages/builds"} path
     **/
    public static final String PAGES_BUILDS_PATH = "/pages/builds";

    /**
     * {@code PAGES_BUILDS_LATEST_PATH} constant for {@code "/pages/builds/latest"} path
     **/
    public static final String PAGES_BUILDS_LATEST_PATH = "/pages/builds/latest";

    /**
     * {@code PAGES_DEPLOYMENT_PATH} constant for {@code "/pages/deployment"} path
     **/
    public static final String PAGES_DEPLOYMENT_PATH = "/pages/deployment";

    /**
     * {@code PAGES_HEALTH_PATH} constant for {@code "/pages/health"} path
     **/
    public static final String PAGES_HEALTH_PATH = "/pages/health";

    /**
     * Constructor to init a {@link GitHubPagesManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubPagesManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubPagesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubPagesManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubPagesManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubPagesManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubPagesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubPagesManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubPagesManager} <br>
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
    public GitHubPagesManager() {
        super();
    }

    /**
     * Method to get a GitHub Pages site
     *
     * @param repository: the repository from fetch the pages site
     * @return pages site as {@link PagesSite} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#get-a-github-pages-site">
     * Get a GitHub Pages site</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pages")
    public PagesSite getGitHubPagesSite(Repository repository) throws IOException {
        return getGitHubPagesSite(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get a GitHub Pages site
     *
     * @param repository: the repository from fetch the pages site
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pages site as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#get-a-github-pages-site">
     * Get a GitHub Pages site</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pages")
    public <T> T getGitHubPagesSite(Repository repository, ReturnFormat format) throws IOException {
        return getGitHubPagesSite(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get a GitHub Pages site
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return pages site as {@link PagesSite} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#get-a-github-pages-site">
     * Get a GitHub Pages site</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pages")
    public PagesSite getGitHubPagesSite(String owner, String repo) throws IOException {
        return getGitHubPagesSite(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get a GitHub Pages site
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return pages site as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#get-a-github-pages-site">
     * Get a GitHub Pages site</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pages")
    public <T> T getGitHubPagesSite(String owner, String repo, ReturnFormat format) throws IOException {
        return returnPagesSite(sendGetRequest(REPOS_PATH + owner + "/" + repo + PAGES_PATH), format);
    }

    /**
     * Method to configure a GitHub Pages site. For more information, see "About GitHub Pages." <br>
     * To use this endpoint, you must be a repository administrator, maintainer, or have the
     * 'manage GitHub Pages settings' permission. A token with the repo scope or Pages write permission is required.
     * GitHub Apps must have the {@code "administration:write"} and {@code "pages:write"} permissions
     *
     * @param repository: the repository where create the pages site
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "build_type"} -> the process in which the Page will be built, constants
     *                           available {@link BuildType} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "source"} -> the source branch and directory used to publish your Pages site,
     *                           you can use the {@link Source} custom object as argument - [object]
     *                       </li>
     *                    </ul>
     * @return pages site as {@link PagesSite} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#create-a-github-pages-site">
     * Create a GitHub Pages site</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pages")
    public PagesSite createGitHubPagesSite(Repository repository, Params bodyParams) throws IOException {
        return createGitHubPagesSite(repository.getOwner().getLogin(), repository.getName(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to configure a GitHub Pages site. For more information, see "About GitHub Pages." <br>
     * To use this endpoint, you must be a repository administrator, maintainer, or have the
     * 'manage GitHub Pages settings' permission. A token with the repo scope or Pages write permission is required.
     * GitHub Apps must have the {@code "administration:write"} and {@code "pages:write"} permissions
     *
     * @param repository: the repository where create the pages site
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "build_type"} -> the process in which the Page will be built, constants
     *                           available {@link BuildType} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "source"} -> the source branch and directory used to publish your Pages site,
     *                           you can use the {@link Source} custom object as argument - [object]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pages site as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#create-a-github-pages-site">
     * Create a GitHub Pages site</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pages")
    public <T> T createGitHubPagesSite(Repository repository, Params bodyParams, ReturnFormat format) throws IOException {
        return createGitHubPagesSite(repository.getOwner().getLogin(), repository.getName(), bodyParams, format);
    }

    /**
     * Method to configure a GitHub Pages site. For more information, see "About GitHub Pages." <br>
     * To use this endpoint, you must be a repository administrator, maintainer, or have the
     * 'manage GitHub Pages settings' permission. A token with the repo scope or Pages write permission is required.
     * GitHub Apps must have the {@code "administration:write"} and {@code "pages:write"} permissions
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "build_type"} -> the process in which the Page will be built, constants
     *                           available {@link BuildType} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "source"} -> the source branch and directory used to publish your Pages site,
     *                           you can use the {@link Source} custom object as argument - [object]
     *                       </li>
     *                    </ul>
     * @return pages site as {@link PagesSite} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#create-a-github-pages-site">
     * Create a GitHub Pages site</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pages")
    public PagesSite createGitHubPagesSite(String owner, String repo, Params bodyParams) throws IOException {
        return createGitHubPagesSite(owner, repo, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to configure a GitHub Pages site. For more information, see "About GitHub Pages." <br>
     * To use this endpoint, you must be a repository administrator, maintainer, or have the
     * 'manage GitHub Pages settings' permission. A token with the repo scope or Pages write permission is required.
     * GitHub Apps must have the {@code "administration:write"} and {@code "pages:write"} permissions
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "build_type"} -> the process in which the Page will be built, constants
     *                           available {@link BuildType} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "source"} -> the source branch and directory used to publish your Pages site,
     *                           you can use the {@link Source} custom object as argument - [object]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pages site as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#create-a-github-pages-site">
     * Create a GitHub Pages site</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pages")
    public <T> T createGitHubPagesSite(String owner, String repo, Params bodyParams, ReturnFormat format) throws IOException {
        return returnPagesSite(sendPostRequest(REPOS_PATH + owner + "/" + repo + PAGES_PATH, bodyParams), format);
    }

    /**
     * Method to create a pages site
     *
     * @param pagesSiteResponse: obtained from GitHub's response
     * @param format:            return type formatter -> {@link ReturnFormat}
     * @return pages site as {@code "format"} defines
     **/
    @Returner
    private <T> T returnPagesSite(String pagesSiteResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(pagesSiteResponse);
            case LIBRARY_OBJECT:
                return (T) new PagesSite(new JSONObject(pagesSiteResponse));
            default:
                return (T) pagesSiteResponse;
        }
    }

    /**
     * Method to update a GitHub Pages site. For more information, see "About GitHub Pages." <br>
     * To use this endpoint, you must be a repository administrator, maintainer, or have the
     * 'manage GitHub Pages settings' permission. A token with the repo scope or Pages write permission is required.
     * GitHub Apps must have the {@code "administration:write"} and {@code "pages:write"} permissions
     *
     * @param repository: the repository where update the pages site
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "cname"} -> specify a custom domain for the repository. Sending a null value
     *                           will remove the custom domain. For more about custom domains, see "Using a custom
     *                           domain with GitHub Pages." - [string or null]
     *                       </li>
     *                       <li>
     *                           {@code "https_enforced"} -> specify whether HTTPS should be enforced for the
     *                           repository - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "build_type"} -> the process in which the Page will be built, constants
     *                           available {@link BuildType} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "source"} -> the source branch and directory used to publish your Pages site,
     *                           you can use the {@link Source} custom object as argument - [object]
     *                       </li>
     *                    </ul>
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#update-information-about-a-github-pages-site">
     * Update information about a GitHub Pages site</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pages")
    public boolean updateGitHubPagesSiteInformation(Repository repository, Params bodyParams) {
        return updateGitHubPagesSiteInformation(repository.getOwner().getLogin(), repository.getName(), bodyParams);
    }

    /**
     * Method to update a GitHub Pages site. For more information, see "About GitHub Pages." <br>
     * To use this endpoint, you must be a repository administrator, maintainer, or have the
     * 'manage GitHub Pages settings' permission. A token with the repo scope or Pages write permission is required.
     * GitHub Apps must have the {@code "administration:write"} and {@code "pages:write"} permissions
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "cname"} -> specify a custom domain for the repository. Sending a null value
     *                           will remove the custom domain. For more about custom domains, see "Using a custom
     *                           domain with GitHub Pages." - [string or null]
     *                       </li>
     *                       <li>
     *                           {@code "https_enforced"} -> specify whether HTTPS should be enforced for the
     *                           repository - [boolean]
     *                       </li>
     *                       <li>
     *                           {@code "build_type"} -> the process in which the Page will be built, constants
     *                           available {@link BuildType} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "source"} -> the source branch and directory used to publish your Pages site,
     *                           you can use the {@link Source} custom object as argument - [object]
     *                       </li>
     *                    </ul>
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#update-information-about-a-github-pages-site">
     * Update information about a GitHub Pages site</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/pages")
    public boolean updateGitHubPagesSiteInformation(String owner, String repo, Params bodyParams) {
        try {
            sendPutRequest(REPOS_PATH + owner + "/" + repo + PAGES_PATH, bodyParams);
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
     * Method to delete a GitHub Pages site. For more information, see "About GitHub Pages." <br>
     * To use this endpoint, you must be a repository administrator, maintainer, or have the
     * 'manage GitHub Pages settings' permission. A token with the repo scope or Pages write permission is required.
     * GitHub Apps must have the {@code "administration:write"} and {@code "pages:write"} permissions
     *
     * @param repository: the repository from delete the pages site
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#delete-a-github-pages-site">
     * Delete a GitHub Pages site</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pages")
    public boolean deleteGitHubPagesSite(Repository repository) {
        return deleteGitHubPagesSite(repository.getOwner().getLogin(), repository.getName());
    }

    /**
     * Method to delete a GitHub Pages site. For more information, see "About GitHub Pages." <br>
     * To use this endpoint, you must be a repository administrator, maintainer, or have the
     * 'manage GitHub Pages settings' permission. A token with the repo scope or Pages write permission is required.
     * GitHub Apps must have the {@code "administration:write"} and {@code "pages:write"} permissions
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#delete-a-github-pages-site">
     * Delete a GitHub Pages site</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pages")
    public boolean deleteGitHubPagesSite(String owner, String repo) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + PAGES_PATH);
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
     * Method to get the list of the GitHub Pages builds
     *
     * @param repository: the repository from fetch the list
     * @return pages build list as {@link ArrayList} of {@link PagesBuild} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-packages-for-an-organization">
     * List GitHub Pages builds</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pages/builds")
    public ArrayList<PagesBuild> getGitHubPagesBuilds(Repository repository) throws IOException {
        return getGitHubPagesBuilds(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the GitHub Pages builds
     *
     * @param repository: the repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pages builds list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-packages-for-an-organization">
     * List GitHub Pages builds</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pages/builds")
    public <T> T getGitHubPagesBuilds(Repository repository, ReturnFormat format) throws IOException {
        return getGitHubPagesBuilds(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list of the GitHub Pages builds
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return pages build list as {@link ArrayList} of {@link PagesBuild} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-packages-for-an-organization">
     * List GitHub Pages builds</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pages/builds")
    public ArrayList<PagesBuild> getGitHubPagesBuilds(String owner, String repo) throws IOException {
        return getGitHubPagesBuilds(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the GitHub Pages builds
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return pages builds list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-packages-for-an-organization">
     * List GitHub Pages builds</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pages/builds")
    public <T> T getGitHubPagesBuilds(String owner, String repo, ReturnFormat format) throws IOException {
        return returnPagesBuilds(sendGetRequest(REPOS_PATH + owner + "/" + repo + PAGES_BUILDS_PATH), format);
    }

    /**
     * Method to get the list of the GitHub Pages builds
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
     * @return pages build list as {@link ArrayList} of {@link PagesBuild} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-packages-for-an-organization">
     * List GitHub Pages builds</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pages/builds")
    public ArrayList<PagesBuild> getGitHubPagesBuilds(Repository repository, Params queryParams) throws IOException {
        return getGitHubPagesBuilds(repository.getOwner().getLogin(), repository.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the GitHub Pages builds
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
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pages builds list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-packages-for-an-organization">
     * List GitHub Pages builds</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pages/builds")
    public <T> T getGitHubPagesBuilds(Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return getGitHubPagesBuilds(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the list of the GitHub Pages builds
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
     * @return pages build list as {@link ArrayList} of {@link PagesBuild} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-packages-for-an-organization">
     * List GitHub Pages builds</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pages/builds")
    public ArrayList<PagesBuild> getGitHubPagesBuilds(String owner, String repo, Params queryParams) throws IOException {
        return getGitHubPagesBuilds(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the GitHub Pages builds
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
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return pages builds list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-packages-for-an-organization">
     * List GitHub Pages builds</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pages/builds")
    public <T> T getGitHubPagesBuilds(String owner, String repo, Params queryParams, ReturnFormat format) throws IOException {
        return returnPagesBuilds(sendGetRequest(REPOS_PATH + owner + "/" + repo + PAGES_BUILDS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a pages builds list
     *
     * @param pagesBuildsResponse: obtained from GitHub's response
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return pages builds list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnPagesBuilds(String pagesBuildsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(pagesBuildsResponse);
            case LIBRARY_OBJECT:
                ArrayList<PagesBuild> pagesBuilds = new ArrayList<>();
                JSONArray jPagesBuilds = new JSONArray(pagesBuildsResponse);
                for (int j = 0; j < jPagesBuilds.length(); j++)
                    pagesBuilds.add(new PagesBuild(jPagesBuilds.getJSONObject(j)));
                return (T) pagesBuilds;
            default:
                return (T) pagesBuildsResponse;
        }
    }

    /**
     * Method to request a GitHub Pages build. <br>
     * You can request that your site be built from the latest revision on the default branch. This has the same effect
     * as pushing a commit to your default branch, but does not require an additional commit. Manually triggering page
     * builds can be helpful when diagnosing build warnings and failures.
     * Build requests are limited to one concurrent build per repository and one concurrent build per requester.
     * If you request a build while another is still in progress, the second request will be queued until the first
     * completes
     *
     * @param repository: the repository from request the list
     * @return pages build as {@link PagesBuild} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#request-a-github-pages-build">
     * Request a GitHub Pages build</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pages/builds")
    public PagesBuild requestGitHubPagesBuild(Repository repository) throws IOException {
        return requestGitHubPagesBuild(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to request a GitHub Pages build. <br>
     * You can request that your site be built from the latest revision on the default branch. This has the same effect
     * as pushing a commit to your default branch, but does not require an additional commit. Manually triggering page
     * builds can be helpful when diagnosing build warnings and failures.
     * Build requests are limited to one concurrent build per repository and one concurrent build per requester.
     * If you request a build while another is still in progress, the second request will be queued until the first
     * completes
     *
     * @param repository: the repository from request the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pages build as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#request-a-github-pages-build">
     * Request a GitHub Pages build</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pages/builds")
    public <T> T requestGitHubPagesBuild(Repository repository, ReturnFormat format) throws IOException {
        return requestGitHubPagesBuild(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to request a GitHub Pages build. <br>
     * You can request that your site be built from the latest revision on the default branch. This has the same effect
     * as pushing a commit to your default branch, but does not require an additional commit. Manually triggering page
     * builds can be helpful when diagnosing build warnings and failures.
     * Build requests are limited to one concurrent build per repository and one concurrent build per requester.
     * If you request a build while another is still in progress, the second request will be queued until the first
     * completes
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return pages build as {@link PagesBuild} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#request-a-github-pages-build">
     * Request a GitHub Pages build</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pages/builds")
    public PagesBuild requestGitHubPagesBuild(String owner, String repo) throws IOException {
        return requestGitHubPagesBuild(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to request a GitHub Pages build. <br>
     * You can request that your site be built from the latest revision on the default branch. This has the same effect
     * as pushing a commit to your default branch, but does not require an additional commit. Manually triggering page
     * builds can be helpful when diagnosing build warnings and failures.
     * Build requests are limited to one concurrent build per repository and one concurrent build per requester.
     * If you request a build while another is still in progress, the second request will be queued until the first
     * completes
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return pages build as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#request-a-github-pages-build">
     * Request a GitHub Pages build</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pages/builds")
    public <T> T requestGitHubPagesBuild(String owner, String repo, ReturnFormat format) throws IOException {
        return returnPagesBuild(sendPostRequest(REPOS_PATH + owner + "/" + repo + PAGES_BUILDS_PATH, null), format);
    }

    /**
     * Method to get the latest Pages build
     *
     * @param repository: the repository from fetch the pages build
     * @return pages build as {@link PagesBuild} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#get-latest-pages-build">
     * Get latest Pages build</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pages/builds/latest")
    public PagesBuild getLatestGitHubPagesBuild(Repository repository) throws IOException {
        return getLatestGitHubPagesBuild(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the latest Pages build
     *
     * @param repository: the repository from fetch the pages build
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pages build as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#get-latest-pages-build">
     * Get latest Pages build</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pages/builds/latest")
    public <T> T getLatestGitHubPagesBuild(Repository repository, ReturnFormat format) throws IOException {
        return getLatestGitHubPagesBuild(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the latest Pages build
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return pages build as {@link PagesBuild} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#get-latest-pages-build">
     * Get latest Pages build</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pages/builds/latest")
    public PagesBuild getLatestGitHubPagesBuild(String owner, String repo) throws IOException {
        return getLatestGitHubPagesBuild(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the latest Pages build
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return pages build as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#get-latest-pages-build">
     * Get latest Pages build</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pages/builds/latest")
    public <T> T getLatestGitHubPagesBuild(String owner, String repo, ReturnFormat format) throws IOException {
        return returnPagesBuild(sendGetRequest(REPOS_PATH + owner + "/" + repo + PAGES_BUILDS_LATEST_PATH), format);
    }

    /**
     * Method to get GitHub Pages build
     *
     * @param repository: the repository from fetch the pages build
     * @param buildId:    the build identifier
     * @return pages build as {@link PagesBuild} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#get-github-pages-build">
     * Get GitHub Pages build</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pages/builds/{build_id}")
    public PagesBuild getGitHubPagesBuild(Repository repository, long buildId) throws IOException {
        return getGitHubPagesBuild(repository.getOwner().getLogin(), repository.getName(), buildId, LIBRARY_OBJECT);
    }

    /**
     * Method to get GitHub Pages build
     *
     * @param repository: the repository from fetch the pages build
     * @param buildId:    the build identifier
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pages build as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#get-github-pages-build">
     * Get GitHub Pages build</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pages/builds/{build_id}")
    public <T> T getGitHubPagesBuild(Repository repository, long buildId, ReturnFormat format) throws IOException {
        return getGitHubPagesBuild(repository.getOwner().getLogin(), repository.getName(), buildId, format);
    }

    /**
     * Method to get GitHub Pages build
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param buildId: the build identifier
     * @return pages build as {@link PagesBuild} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#get-github-pages-build">
     * Get GitHub Pages build</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pages/builds/{build_id}")
    public PagesBuild getGitHubPagesBuild(String owner, String repo, long buildId) throws IOException {
        return getGitHubPagesBuild(owner, repo, buildId, LIBRARY_OBJECT);
    }

    /**
     * Method to get GitHub Pages build
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param buildId: the build identifier
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return pages build as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#get-github-pages-build">
     * Get GitHub Pages build</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pages/builds/{build_id}")
    public <T> T getGitHubPagesBuild(String owner, String repo, long buildId, ReturnFormat format) throws IOException {
        return returnPagesBuild(sendGetRequest(REPOS_PATH + owner + "/" + repo + PAGES_BUILDS_PATH + "/"
                + buildId), format);
    }

    /**
     * Method to create a pages build
     *
     * @param pagesBuildResponse: obtained from GitHub's response
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return pages build as {@code "format"} defines
     **/
    @Returner
    private <T> T returnPagesBuild(String pagesBuildResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(pagesBuildResponse);
            case LIBRARY_OBJECT:
                return (T) new PagesBuild(new JSONObject(pagesBuildResponse));
            default:
                return (T) pagesBuildResponse;
        }
    }

    /**
     * Method to create a GitHub Pages deployment for a repository. <br>
     * Users must have write permissions. GitHub Apps must have the {@code "pages:write"} permission to use this endpoint
     *
     * @param repository:        the repository where create the pages site
     * @param artifactUrl:       the URL of an artifact that contains the .zip or .tar of static assets to deploy. The artifact
     *                           belongs to the repository
     * @param pagesBuildVersion: unique string that represents the version of the build for this deployment
     * @param oidcToken:         the OIDC token issued by GitHub Actions certifying the origin of the deployment
     * @return pages deployment as {@link PagesDeployment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#create-a-github-pages-deployment">
     * Create a GitHub Pages deployment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pages/deployment")
    public PagesDeployment createGitHubPagesDeployment(Repository repository, String artifactUrl,
                                                       String pagesBuildVersion, String oidcToken) throws IOException {
        return createGitHubPagesDeployment(repository.getOwner().getLogin(), repository.getName(), artifactUrl,
                pagesBuildVersion, oidcToken, LIBRARY_OBJECT);
    }

    /**
     * Method to create a GitHub Pages deployment for a repository. <br>
     * Users must have write permissions. GitHub Apps must have the {@code "pages:write"} permission to use this endpoint
     *
     * @param repository:        the repository where create the pages site
     * @param artifactUrl:       the URL of an artifact that contains the .zip or .tar of static assets to deploy. The artifact
     *                           belongs to the repository
     * @param pagesBuildVersion: unique string that represents the version of the build for this deployment
     * @param oidcToken:         the OIDC token issued by GitHub Actions certifying the origin of the deployment
     * @param format:            return type formatter -> {@link ReturnFormat}
     * @return pages deployment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#create-a-github-pages-deployment">
     * Create a GitHub Pages deployment</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pages/deployment")
    public <T> T createGitHubPagesDeployment(Repository repository, String artifactUrl, String pagesBuildVersion,
                                             String oidcToken, ReturnFormat format) throws IOException {
        return createGitHubPagesDeployment(repository.getOwner().getLogin(), repository.getName(), artifactUrl,
                pagesBuildVersion, oidcToken, format);
    }

    /**
     * Method to create a GitHub Pages deployment for a repository. <br>
     * Users must have write permissions. GitHub Apps must have the {@code "pages:write"} permission to use this endpoint
     *
     * @param owner:             the account owner of the repository. The name is not case-sensitive
     * @param repo:              the name of the repository. The name is not case-sensitive
     * @param artifactUrl:       the URL of an artifact that contains the .zip or .tar of static assets to deploy. The artifact
     *                           belongs to the repository
     * @param pagesBuildVersion: unique string that represents the version of the build for this deployment
     * @param oidcToken:         the OIDC token issued by GitHub Actions certifying the origin of the deployment
     * @return pages deployment as {@link PagesDeployment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#create-a-github-pages-deployment">
     * Create a GitHub Pages deployment</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pages/deployment")
    public PagesDeployment createGitHubPagesDeployment(String owner, String repo, String artifactUrl,
                                                       String pagesBuildVersion, String oidcToken) throws IOException {
        return createGitHubPagesDeployment(owner, repo, artifactUrl, pagesBuildVersion, oidcToken, LIBRARY_OBJECT);
    }

    /**
     * Method to create a GitHub Pages deployment for a repository. <br>
     * Users must have write permissions. GitHub Apps must have the {@code "pages:write"} permission to use this endpoint
     *
     * @param owner:             the account owner of the repository. The name is not case-sensitive
     * @param repo:              the name of the repository. The name is not case-sensitive
     * @param artifactUrl:       the URL of an artifact that contains the .zip or .tar of static assets to deploy. The artifact
     *                           belongs to the repository
     * @param pagesBuildVersion: unique string that represents the version of the build for this deployment
     * @param oidcToken:         the OIDC token issued by GitHub Actions certifying the origin of the deployment
     * @param format:            return type formatter -> {@link ReturnFormat}
     * @return pages deployment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#create-a-github-pages-deployment">
     * Create a GitHub Pages deployment</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pages/deployment")
    public <T> T createGitHubPagesDeployment(String owner, String repo, String artifactUrl, String pagesBuildVersion,
                                             String oidcToken, ReturnFormat format) throws IOException {
        return createGitHubPagesDeployment(owner, repo, artifactUrl, pagesBuildVersion, oidcToken, null,
                format);
    }

    /**
     * Method to create a GitHub Pages deployment for a repository. <br>
     * Users must have write permissions. GitHub Apps must have the {@code "pages:write"} permission to use this endpoint
     *
     * @param repository:        the repository where create the pages site
     * @param artifactUrl:       the URL of an artifact that contains the .zip or .tar of static assets to deploy. The artifact
     *                           belongs to the repository
     * @param pagesBuildVersion: unique string that represents the version of the build for this deployment
     * @param oidcToken:         the OIDC token issued by GitHub Actions certifying the origin of the deployment
     * @param environment:       the target environment for this GitHub Pages deployment
     * @return pages deployment as {@link PagesDeployment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#create-a-github-pages-deployment">
     * Create a GitHub Pages deployment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pages/deployment")
    public PagesDeployment createGitHubPagesDeployment(Repository repository, String artifactUrl, String pagesBuildVersion,
                                                       String oidcToken, String environment) throws IOException {
        return createGitHubPagesDeployment(repository.getOwner().getLogin(), repository.getName(), artifactUrl,
                pagesBuildVersion, oidcToken, environment, LIBRARY_OBJECT);
    }

    /**
     * Method to create a GitHub Pages deployment for a repository. <br>
     * Users must have write permissions. GitHub Apps must have the {@code "pages:write"} permission to use this endpoint
     *
     * @param repository:        the repository where create the pages site
     * @param artifactUrl:       the URL of an artifact that contains the .zip or .tar of static assets to deploy. The artifact
     *                           belongs to the repository
     * @param pagesBuildVersion: unique string that represents the version of the build for this deployment
     * @param oidcToken:         the OIDC token issued by GitHub Actions certifying the origin of the deployment
     * @param environment:       the target environment for this GitHub Pages deployment
     * @param format:            return type formatter -> {@link ReturnFormat}
     * @return pages deployment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#create-a-github-pages-deployment">
     * Create a GitHub Pages deployment</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pages/deployment")
    public <T> T createGitHubPagesDeployment(Repository repository, String artifactUrl, String pagesBuildVersion,
                                             String oidcToken, String environment, ReturnFormat format) throws IOException {
        return createGitHubPagesDeployment(repository.getOwner().getLogin(), repository.getName(), artifactUrl,
                pagesBuildVersion, oidcToken, environment, format);
    }

    /**
     * Method to create a GitHub Pages deployment for a repository. <br>
     * Users must have write permissions. GitHub Apps must have the {@code "pages:write"} permission to use this endpoint
     *
     * @param owner:             the account owner of the repository. The name is not case-sensitive
     * @param repo:              the name of the repository. The name is not case-sensitive
     * @param artifactUrl:       the URL of an artifact that contains the .zip or .tar of static assets to deploy. The artifact
     *                           belongs to the repository
     * @param pagesBuildVersion: unique string that represents the version of the build for this deployment
     * @param oidcToken:         the OIDC token issued by GitHub Actions certifying the origin of the deployment
     * @param environment:       the target environment for this GitHub Pages deployment
     * @return pages deployment as {@link PagesDeployment} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#create-a-github-pages-deployment">
     * Create a GitHub Pages deployment</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pages/deployment")
    public PagesDeployment createGitHubPagesDeployment(String owner, String repo, String artifactUrl,
                                                       String pagesBuildVersion, String oidcToken,
                                                       String environment) throws IOException {
        return createGitHubPagesDeployment(owner, repo, artifactUrl, pagesBuildVersion, oidcToken, environment,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a GitHub Pages deployment for a repository. <br>
     * Users must have write permissions. GitHub Apps must have the {@code "pages:write"} permission to use this endpoint
     *
     * @param owner:             the account owner of the repository. The name is not case-sensitive
     * @param repo:              the name of the repository. The name is not case-sensitive
     * @param artifactUrl:       the URL of an artifact that contains the .zip or .tar of static assets to deploy. The artifact
     *                           belongs to the repository
     * @param pagesBuildVersion: unique string that represents the version of the build for this deployment
     * @param oidcToken:         the OIDC token issued by GitHub Actions certifying the origin of the deployment
     * @param environment:       the target environment for this GitHub Pages deployment
     * @param format:            return type formatter -> {@link ReturnFormat}
     * @return pages deployment as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#create-a-github-pages-deployment">
     * Create a GitHub Pages deployment</a>
     **/
    @Returner
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pages/deployment")
    public <T> T createGitHubPagesDeployment(String owner, String repo, String artifactUrl, String pagesBuildVersion,
                                             String oidcToken, String environment, ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("artifact_url", artifactUrl);
        payload.addParam("pages_build_version", pagesBuildVersion);
        payload.addParam("oidc_token", oidcToken);
        if (environment != null)
            payload.addParam("environment", environment);
        String deploymentResponse = sendPostRequest(REPOS_PATH + owner + "/" + repo + PAGES_DEPLOYMENT_PATH,
                payload);
        switch (format) {
            case JSON:
                return (T) new JSONObject(deploymentResponse);
            case LIBRARY_OBJECT:
                return (T) new PagesDeployment(new JSONObject(deploymentResponse));
            default:
                return (T) deploymentResponse;
        }
    }

    /**
     * Method to get a health check of the DNS settings for the CNAME record configured for a repository's GitHub Pages <br>
     * The first request to this endpoint returns a 202 Accepted status and starts an asynchronous background task to get
     * the results for the domain. After the background task completes, subsequent requests to this endpoint return a
     * 200 OK status with the health check results in the response.
     * To use this endpoint, you must be a repository administrator, maintainer, or have the 'manage GitHub Pages settings'
     * permission. A token with the repo scope or Pages write permission is required.
     * GitHub Apps must have the {@code "administrative:write"} and {@code "pages:write"} permissions.
     *
     * @param repository: the repository from fetch the DNS health
     * @return pages health check as {@link PagesHealthCheck} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#get-a-dns-health-check-for-github-pages">
     * Get a DNS health check for GitHub Pages</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pages/health")
    public PagesHealthCheck getDNSHealthCheck(Repository repository) throws IOException {
        return getDNSHealthCheck(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get a health check of the DNS settings for the CNAME record configured for a repository's GitHub Pages <br>
     * The first request to this endpoint returns a 202 Accepted status and starts an asynchronous background task to get
     * the results for the domain. After the background task completes, subsequent requests to this endpoint return a
     * 200 OK status with the health check results in the response.
     * To use this endpoint, you must be a repository administrator, maintainer, or have the 'manage GitHub Pages settings'
     * permission. A token with the repo scope or Pages write permission is required.
     * GitHub Apps must have the {@code "administrative:write"} and {@code "pages:write"} permissions.
     *
     * @param repository: the repository from fetch the DNS health
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return pages health check as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#get-a-dns-health-check-for-github-pages">
     * Get a DNS health check for GitHub Pages</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pages/health")
    public <T> T getDNSHealthCheck(Repository repository, ReturnFormat format) throws IOException {
        return getDNSHealthCheck(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get a health check of the DNS settings for the CNAME record configured for a repository's GitHub Pages <br>
     * The first request to this endpoint returns a 202 Accepted status and starts an asynchronous background task to get
     * the results for the domain. After the background task completes, subsequent requests to this endpoint return a
     * 200 OK status with the health check results in the response.
     * To use this endpoint, you must be a repository administrator, maintainer, or have the 'manage GitHub Pages settings'
     * permission. A token with the repo scope or Pages write permission is required.
     * GitHub Apps must have the {@code "administrative:write"} and {@code "pages:write"} permissions.
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return pages health check as {@link PagesHealthCheck} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#get-a-dns-health-check-for-github-pages">
     * Get a DNS health check for GitHub Pages</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pages/health")
    public PagesHealthCheck getDNSHealthCheck(String owner, String repo) throws IOException {
        return getDNSHealthCheck(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get a health check of the DNS settings for the CNAME record configured for a repository's GitHub Pages <br>
     * The first request to this endpoint returns a 202 Accepted status and starts an asynchronous background task to get
     * the results for the domain. After the background task completes, subsequent requests to this endpoint return a
     * 200 OK status with the health check results in the response.
     * To use this endpoint, you must be a repository administrator, maintainer, or have the 'manage GitHub Pages settings'
     * permission. A token with the repo scope or Pages write permission is required.
     * GitHub Apps must have the {@code "administrative:write"} and {@code "pages:write"} permissions.
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return pages health check as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#get-a-dns-health-check-for-github-pages">
     * Get a DNS health check for GitHub Pages</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pages/health")
    public <T> T getDNSHealthCheck(String owner, String repo, ReturnFormat format) throws IOException {
        String healthResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo + PAGES_HEALTH_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONObject(healthResponse);
            case LIBRARY_OBJECT:
                return (T) new PagesHealthCheck(new JSONObject(healthResponse));
            default:
                return (T) healthResponse;
        }
    }

}
