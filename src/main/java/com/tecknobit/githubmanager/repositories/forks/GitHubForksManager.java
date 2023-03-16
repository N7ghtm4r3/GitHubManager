package com.tecknobit.githubmanager.repositories.forks;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository.ForkSort;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.POST;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.repositories.repositories.records.Repository.returnRepositories;
import static com.tecknobit.githubmanager.repositories.repositories.records.Repository.returnRepository;

/**
 * The {@code GitHubForksManager} class is useful to manage all GitHub's forks endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/forks">
 * Forks</a>
 * @see GitHubManager
 **/
public class GitHubForksManager extends GitHubManager {

    /**
     * {@code FORKS_PATH} constant for {@code "/forks"} path
     **/
    public static final String FORKS_PATH = "/forks";

    /**
     * Constructor to init a {@link GitHubForksManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubForksManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubForksManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubForksManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubForksManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubForksManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubForksManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubForksManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubForksManager} <br>
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
    public GitHubForksManager() {
        super();
    }

    /**
     * Method to get the list of the forks
     *
     * @param repository: the repository from fetch the list
     * @return repositories list as {@link ArrayList} of {@link Repository} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/forks#list-forks">
     * List forks</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/forks")
    public ArrayList<Repository> getForks(Repository repository) throws IOException {
        return getForks(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the forks
     *
     * @param repository: the repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repositories list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/forks#list-forks">
     * List forks</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/forks")
    public <T> T getForks(Repository repository, ReturnFormat format) throws IOException {
        return getForks(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list of the forks
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return repositories list as {@link ArrayList} of {@link Repository} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/forks#list-forks">
     * List forks</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/forks")
    public ArrayList<Repository> getForks(String owner, String repo) throws IOException {
        return getForks(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the forks
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repositories list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/forks#list-forks">
     * List forks</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/forks")
    public <T> T getForks(String owner, String repo, ReturnFormat format) throws IOException {
        return returnRepositories(sendGetRequest(REPOS_PATH + owner + "/" + repo + FORKS_PATH), format);
    }

    /**
     * Method to get the list of the forks
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> the sort order. stargazers will sort by star count, constants available
     *                            {@link ForkSort} - [string, default newest]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return repositories list as {@link ArrayList} of {@link Repository} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/forks#list-forks">
     * List forks</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/forks")
    public ArrayList<Repository> getForks(Repository repository, Params queryParams) throws IOException {
        return getForks(repository.getOwner().getLogin(), repository.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the forks
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> the sort order. stargazers will sort by star count, constants available
     *                            {@link ForkSort} - [string, default newest]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return repositories list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/forks#list-forks">
     * List forks</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/forks")
    public <T> T getForks(Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return getForks(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the list of the forks
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> the sort order. stargazers will sort by star count, constants available
     *                            {@link ForkSort} - [string, default newest]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return repositories list as {@link ArrayList} of {@link Repository} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/forks#list-forks">
     * List forks</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/forks")
    public ArrayList<Repository> getForks(String owner, String repo, Params queryParams) throws IOException {
        return getForks(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the forks
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "sort"} -> the sort order. stargazers will sort by star count, constants available
     *                            {@link ForkSort} - [string, default newest]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return repositories list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/forks#list-forks">
     * List forks</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/forks")
    public <T> T getForks(String owner, String repo, Params queryParams, ReturnFormat format) throws IOException {
        return returnRepositories(sendGetRequest(REPOS_PATH + owner + "/" + repo + FORKS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a fork for the authenticated user
     *
     * @param repository: the repository from create the fork
     * @return repository as {@link Repository} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/forks#create-a-fork">
     * Create a fork</a>
     * @implNote Forking a Repository happens asynchronously. You may have to wait a short period of time before you can
     * access the git objects. If this takes longer than 5 minutes, be sure to contact GitHub Support.
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/forks")
    public Repository createFork(Repository repository) throws IOException {
        return createFork(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to create a fork for the authenticated user
     *
     * @param repository: the repository from create the fork
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/forks#create-a-fork">
     * Create a fork</a>
     * @implNote Forking a Repository happens asynchronously. You may have to wait a short period of time before you can
     * access the git objects. If this takes longer than 5 minutes, be sure to contact GitHub Support.
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/forks")
    public <T> T createFork(Repository repository, ReturnFormat format) throws IOException {
        return createFork(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to create a fork for the authenticated user
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return repository as {@link Repository} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/forks#create-a-fork">
     * Create a fork</a>
     * @implNote Forking a Repository happens asynchronously. You may have to wait a short period of time before you can
     * access the git objects. If this takes longer than 5 minutes, be sure to contact GitHub Support.
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/forks")
    public Repository createFork(String owner, String repo) throws IOException {
        return createFork(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to create a fork for the authenticated user
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repository as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/forks#create-a-fork">
     * Create a fork</a>
     * @implNote Forking a Repository happens asynchronously. You may have to wait a short period of time before you can
     * access the git objects. If this takes longer than 5 minutes, be sure to contact GitHub Support.
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/forks")
    public <T> T createFork(String owner, String repo, ReturnFormat format) throws IOException {
        return createFork(owner, repo, null, format);
    }

    /**
     * Method to create a fork for the authenticated user
     *
     * @param repository: the repository from create the fork
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "organization"} -> optional parameter to specify the organization name if forking into
     *                           an organization - [string]
     *                       </li>
     *                       <li>
     *                           {@code "name"} -> when forking from an existing repository, a new name for
     *                           the fork - [string]
     *                       </li>
     *                       <li>
     *                           {@code "default_branch_only"} -> when forking from an existing repository, fork with
     *                           only the default branch - [boolean]
     *                       </li>
     *                    </ul>
     * @return repository as {@link Repository} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/forks#create-a-fork">
     * Create a fork</a>
     * @implNote Forking a Repository happens asynchronously. You may have to wait a short period of time before you can
     * access the git objects. If this takes longer than 5 minutes, be sure to contact GitHub Support.
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/forks")
    public Repository createFork(Repository repository, Params bodyParams) throws IOException {
        return createFork(repository.getOwner().getLogin(), repository.getName(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a fork for the authenticated user
     *
     * @param repository: the repository from create the fork
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "organization"} -> optional parameter to specify the organization name if forking into
     *                           an organization - [string]
     *                       </li>
     *                       <li>
     *                           {@code "name"} -> when forking from an existing repository, a new name for
     *                           the fork - [string]
     *                       </li>
     *                       <li>
     *                           {@code "default_branch_only"} -> when forking from an existing repository, fork with
     *                           only the default branch - [boolean]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/forks#create-a-fork">
     * Create a fork</a>
     * @implNote Forking a Repository happens asynchronously. You may have to wait a short period of time before you can
     * access the git objects. If this takes longer than 5 minutes, be sure to contact GitHub Support.
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/forks")
    public <T> T createFork(Repository repository, Params bodyParams, ReturnFormat format) throws IOException {
        return createFork(repository.getOwner().getLogin(), repository.getName(), bodyParams, format);
    }

    /**
     * Method to create a fork for the authenticated user
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "organization"} -> optional parameter to specify the organization name if forking into
     *                           an organization - [string]
     *                       </li>
     *                       <li>
     *                           {@code "name"} -> when forking from an existing repository, a new name for
     *                           the fork - [string]
     *                       </li>
     *                       <li>
     *                           {@code "default_branch_only"} -> when forking from an existing repository, fork with
     *                           only the default branch - [boolean]
     *                       </li>
     *                    </ul>
     * @return repository as {@link Repository} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/forks#create-a-fork">
     * Create a fork</a>
     * @implNote Forking a Repository happens asynchronously. You may have to wait a short period of time before you can
     * access the git objects. If this takes longer than 5 minutes, be sure to contact GitHub Support.
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/forks")
    public Repository createFork(String owner, String repo, Params bodyParams) throws IOException {
        return createFork(owner, repo, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a fork for the authenticated user
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "organization"} -> optional parameter to specify the organization name if forking into
     *                           an organization - [string]
     *                       </li>
     *                       <li>
     *                           {@code "name"} -> when forking from an existing repository, a new name for
     *                           the fork - [string]
     *                       </li>
     *                       <li>
     *                           {@code "default_branch_only"} -> when forking from an existing repository, fork with
     *                           only the default branch - [boolean]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/forks#create-a-fork">
     * Create a fork</a>
     * @implNote Forking a Repository happens asynchronously. You may have to wait a short period of time before you can
     * access the git objects. If this takes longer than 5 minutes, be sure to contact GitHub Support.
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/forks")
    public <T> T createFork(String owner, String repo, Params bodyParams, ReturnFormat format) throws IOException {
        return returnRepository(sendPostRequest(REPOS_PATH + owner + "/" + repo + FORKS_PATH, bodyParams), format);
    }

}
