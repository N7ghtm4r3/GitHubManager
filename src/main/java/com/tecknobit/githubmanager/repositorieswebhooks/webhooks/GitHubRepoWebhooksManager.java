package com.tecknobit.githubmanager.repositorieswebhooks.webhooks;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.apps.webhooks.records.Webhook;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import com.tecknobit.githubmanager.repositorieswebhooks.webhooks.records.RepositoryWebhook;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.organizations.webhooks.GitHubOrganizationWebhooksManager.HOOKS_PATH;
import static com.tecknobit.githubmanager.organizations.webhooks.GitHubOrganizationWebhooksManager.PINGS_PATH;

/**
 * The {@code GitHubRepoWebhooksManager} class is useful to manage all GitHub's repository webhook deliveries endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos">
 * Repository Webhooks</a>
 * @see GitHubManager
 **/
public class GitHubRepoWebhooksManager extends GitHubManager {

    /**
     * {@code TESTS_PATH} constant for {@code "/tests"} path
     **/
    public static final String TESTS_PATH = "/tests";

    /**
     * Constructor to init a {@link GitHubRepoWebhooksManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubRepoWebhooksManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubRepoWebhooksManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubRepoWebhooksManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubRepoWebhooksManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubRepoWebhooksManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubRepoWebhooksManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubRepoWebhooksManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubRepoWebhooksManager} <br>
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
    public GitHubRepoWebhooksManager() {
        super();
    }

    /**
     * Method to get the list of the webhooks for a repository. last response may return null if there have not been any
     * deliveries within 30 days
     *
     * @param repository: the repository from fetch the list
     * @return repository webhooks list as {@link ArrayList} of {@link RepositoryWebhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#list-repository-webhooks">
     * List repository webhooks</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/hooks")
    public ArrayList<RepositoryWebhook> getRepositoryWebhooks(Repository repository) throws IOException {
        return getRepositoryWebhooks(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the webhooks for a repository. last response may return null if there have not been any
     * deliveries within 30 days
     *
     * @param repository: the repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository webhooks list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#list-repository-webhooks">
     * List repository webhooks</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/hooks")
    public <T> T getRepositoryWebhooks(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryWebhooks(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list of the webhooks for a repository. last response may return null if there have not been any
     * deliveries within 30 days
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return repository webhooks list as {@link ArrayList} of {@link RepositoryWebhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#list-repository-webhooks">
     * List repository webhooks</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/hooks")
    public ArrayList<RepositoryWebhook> getRepositoryWebhooks(String owner, String repo) throws IOException {
        return getRepositoryWebhooks(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the webhooks for a repository. last response may return null if there have not been any
     * deliveries within 30 days
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repository webhooks list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#list-repository-webhooks">
     * List repository webhooks</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/hooks")
    public <T> T getRepositoryWebhooks(String owner, String repo, ReturnFormat format) throws IOException {
        return returnRepositoryWebhooks(sendGetRequest(REPOS_PATH + owner + "/" + repo + HOOKS_PATH), format);
    }

    /**
     * Method to get the list of the webhooks for a repository. last response may return null if there have not been any
     * deliveries within 30 days
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
     * @return repository webhooks list as {@link ArrayList} of {@link RepositoryWebhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#list-repository-webhooks">
     * List repository webhooks</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/hooks")
    public ArrayList<RepositoryWebhook> getRepositoryWebhooks(Repository repository, Params queryParams) throws IOException {
        return getRepositoryWebhooks(repository.getOwner().getLogin(), repository.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the webhooks for a repository. last response may return null if there have not been any
     * deliveries within 30 days
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
     * @return repository webhooks list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#list-repository-webhooks">
     * List repository webhooks</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/hooks")
    public <T> T getRepositoryWebhooks(Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return getRepositoryWebhooks(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the list of the webhooks for a repository. last response may return null if there have not been any
     * deliveries within 30 days
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
     * @return repository webhooks list as {@link ArrayList} of {@link RepositoryWebhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#list-repository-webhooks">
     * List repository webhooks</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/hooks")
    public ArrayList<RepositoryWebhook> getRepositoryWebhooks(String owner, String repo,
                                                              Params queryParams) throws IOException {
        return getRepositoryWebhooks(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the webhooks for a repository. last response may return null if there have not been any
     * deliveries within 30 days
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
     * @return repository webhooks list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#list-repository-webhooks">
     * List repository webhooks</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/hooks")
    public <T> T getRepositoryWebhooks(String owner, String repo, Params queryParams,
                                       ReturnFormat format) throws IOException {
        return returnRepositoryWebhooks(sendGetRequest(REPOS_PATH + owner + "/" + repo + HOOKS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a repository webhooks list
     *
     * @param repositoryWebhooksResponse: obtained from GitHub's response
     * @param format:                     return type formatter -> {@link ReturnFormat}
     * @return repository webhooks list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnRepositoryWebhooks(String repositoryWebhooksResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(repositoryWebhooksResponse);
            case LIBRARY_OBJECT:
                ArrayList<RepositoryWebhook> repositoryWebhooks = new ArrayList<>();
                JSONArray jRepositoryWebhooks = new JSONArray(repositoryWebhooksResponse);
                for (int j = 0; j < jRepositoryWebhooks.length(); j++)
                    repositoryWebhooks.add(new RepositoryWebhook(jRepositoryWebhooks.getJSONObject(j)));
                return (T) repositoryWebhooks;
            default:
                return (T) repositoryWebhooksResponse;
        }
    }

    /**
     * Method to create a repository webhook
     *
     * @param repository: the repository where create the webhook
     * @param config:     settings for this webhook
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "events"} -> determines what events the hook is triggered for. Set to ["*"] to
     *                           receive all possible events - [array of strings, default ["push"]]
     *                       </li>
     *                       <li>
     *                           {@code "active"} -> determines if notifications are sent when the webhook is triggered.
     *                           Set to {@code "true"} to send notifications - [boolean, default true]
     *                       </li>
     *                    </ul>
     * @return repository webhook as {@link RepositoryWebhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#create-a-repository-webhook">
     * Create a repository webhook</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/hooks")
    public RepositoryWebhook createRepositoryWebhook(Repository repository, Webhook config,
                                                     Params bodyParams) throws IOException {
        return createRepositoryWebhook(repository.getOwner().getLogin(), repository.getName(), config, bodyParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a repository webhook
     *
     * @param repository: the repository where create the webhook
     * @param config:     settings for this webhook
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "events"} -> determines what events the hook is triggered for. Set to ["*"] to
     *                           receive all possible events - [array of strings, default ["push"]]
     *                       </li>
     *                       <li>
     *                           {@code "active"} -> determines if notifications are sent when the webhook is triggered.
     *                           Set to {@code "true"} to send notifications - [boolean, default true]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository webhook as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#create-a-repository-webhook">
     * Create a repository webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/hooks")
    public <T> T createRepositoryWebhook(Repository repository, Webhook config, Params bodyParams,
                                         ReturnFormat format) throws IOException {
        return createRepositoryWebhook(repository.getOwner().getLogin(), repository.getName(), config, bodyParams, format);
    }

    /**
     * Method to create a repository webhook
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param config:     settings for this webhook
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "events"} -> determines what events the hook is triggered for. Set to ["*"] to
     *                           receive all possible events - [array of strings, default ["push"]]
     *                       </li>
     *                       <li>
     *                           {@code "active"} -> determines if notifications are sent when the webhook is triggered.
     *                           Set to {@code "true"} to send notifications - [boolean, default true]
     *                       </li>
     *                    </ul>
     * @return repository webhook as {@link RepositoryWebhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#create-a-repository-webhook">
     * Create a repository webhook</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/hooks")
    public RepositoryWebhook createRepositoryWebhook(String owner, String repo, Webhook config,
                                                     Params bodyParams) throws IOException {
        return createRepositoryWebhook(owner, repo, config, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a repository webhook
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param config:     settings for this webhook
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "events"} -> determines what events the hook is triggered for. Set to ["*"] to
     *                           receive all possible events - [array of strings, default ["push"]]
     *                       </li>
     *                       <li>
     *                           {@code "active"} -> determines if notifications are sent when the webhook is triggered.
     *                           Set to {@code "true"} to send notifications - [boolean, default true]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository webhook as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#create-a-repository-webhook">
     * Create a repository webhook</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/hooks")
    public <T> T createRepositoryWebhook(String owner, String repo, Webhook config, Params bodyParams,
                                         ReturnFormat format) throws IOException {
        return returnRepositoryWebhook(sendPostRequest(REPOS_PATH + owner + "/" + repo + HOOKS_PATH, bodyParams),
                format);
    }

    /**
     * Method to return a webhook configured in a repository. To get only the webhook config properties, see
     * "Get a webhook configuration for a repository."
     *
     * @param repository: the repository from fetch the webhook
     * @param hookId:     the unique identifier of the hook
     * @return repository webhook as {@link RepositoryWebhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#get-a-repository-webhook">
     * Get a repository webhook</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/hooks/{hook_id}")
    public RepositoryWebhook getRepositoryWebhook(Repository repository, long hookId) throws IOException {
        return getRepositoryWebhook(repository.getOwner().getLogin(), repository.getName(), hookId, LIBRARY_OBJECT);
    }

    /**
     * Method to return a webhook configured in a repository. To get only the webhook config properties, see
     * "Get a webhook configuration for a repository."
     *
     * @param repository: the repository from fetch the webhook
     * @param hookId:     the unique identifier of the hook
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository webhook as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#get-a-repository-webhook">
     * Get a repository webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/hooks/{hook_id}")
    public <T> T getRepositoryWebhook(Repository repository, long hookId, ReturnFormat format) throws IOException {
        return getRepositoryWebhook(repository.getOwner().getLogin(), repository.getName(), hookId, format);
    }

    /**
     * Method to return a webhook configured in a repository. To get only the webhook config properties, see
     * "Get a webhook configuration for a repository."
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param hookId: the unique identifier of the hook
     * @return repository webhook as {@link RepositoryWebhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#get-a-repository-webhook">
     * Get a repository webhook</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/hooks/{hook_id}")
    public RepositoryWebhook getRepositoryWebhook(String owner, String repo, long hookId) throws IOException {
        return getRepositoryWebhook(owner, repo, hookId, LIBRARY_OBJECT);
    }

    /**
     * Method to return a webhook configured in a repository. To get only the webhook config properties, see
     * "Get a webhook configuration for a repository."
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param hookId: the unique identifier of the hook
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return repository webhook as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#get-a-repository-webhook">
     * Get a repository webhook</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/hooks/{hook_id}")
    public <T> T getRepositoryWebhook(String owner, String repo, long hookId, ReturnFormat format) throws IOException {
        return returnRepositoryWebhook(sendGetRequest(REPOS_PATH + owner + "/" + repo + HOOKS_PATH
                + "/" + hookId), format);
    }

    /**
     * Method to update a webhook configured in a repository. If you previously had a secret set, you must provide the
     * same secret or set a new secret or the secret will be removed. If you are only updating individual webhook config
     * properties, use "Update a webhook configuration for a repository."
     *
     * @param repository: the repository where update the webhook
     * @param hook:       the hook to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "config"} -> config webhook - [{@link Webhook}]
     *                       </li>
     *                       <li>
     *                           {@code "events"} -> determines what events the hook is triggered for. Set to ["*"] to
     *                           receive all possible events - [array of strings, default ["push"]]
     *                       </li>
     *                       <li>
     *                           {@code "add_events"} -> determines a list of events to be added to the list of events
     *                           that the Hook triggers for - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "remove_events"} -> determines a list of events to be removed from the list of
     *                           events that the Hook triggers for - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "active"} -> determines if notifications are sent when the webhook is triggered.
     *                           Set to {@code "true"} to send notifications - [boolean, default true]
     *                       </li>
     *                    </ul>
     * @return repository webhook as {@link RepositoryWebhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#update-a-repository-webhook">
     * Update a repository webhook</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/hooks/{hook_id}")
    public RepositoryWebhook updateRepositoryWebhook(Repository repository, RepositoryWebhook hook,
                                                     Params bodyParams) throws IOException {
        return updateRepositoryWebhook(repository.getOwner().getLogin(), repository.getName(), hook.getId(), bodyParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to update a webhook configured in a repository. If you previously had a secret set, you must provide the
     * same secret or set a new secret or the secret will be removed. If you are only updating individual webhook config
     * properties, use "Update a webhook configuration for a repository."
     *
     * @param repository: the repository where update the webhook
     * @param hook:       the hook to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "config"} -> config webhook - [{@link Webhook}]
     *                       </li>
     *                       <li>
     *                           {@code "events"} -> determines what events the hook is triggered for. Set to ["*"] to
     *                           receive all possible events - [array of strings, default ["push"]]
     *                       </li>
     *                       <li>
     *                           {@code "add_events"} -> determines a list of events to be added to the list of events
     *                           that the Hook triggers for - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "remove_events"} -> determines a list of events to be removed from the list of
     *                           events that the Hook triggers for - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "active"} -> determines if notifications are sent when the webhook is triggered.
     *                           Set to {@code "true"} to send notifications - [boolean, default true]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository webhook as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#update-a-repository-webhook">
     * Update a repository webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/hooks/{hook_id}")
    public <T> T updateRepositoryWebhook(Repository repository, RepositoryWebhook hook, Params bodyParams,
                                         ReturnFormat format) throws IOException {
        return updateRepositoryWebhook(repository.getOwner().getLogin(), repository.getName(), hook.getId(), bodyParams,
                format);
    }

    /**
     * Method to update a webhook configured in a repository. If you previously had a secret set, you must provide the
     * same secret or set a new secret or the secret will be removed. If you are only updating individual webhook config
     * properties, use "Update a webhook configuration for a repository."
     *
     * @param repository: the repository where update the webhook
     * @param hookId:     the unique identifier of the hook
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "config"} -> config webhook - [{@link Webhook}]
     *                       </li>
     *                       <li>
     *                           {@code "events"} -> determines what events the hook is triggered for. Set to ["*"] to
     *                           receive all possible events - [array of strings, default ["push"]]
     *                       </li>
     *                       <li>
     *                           {@code "add_events"} -> determines a list of events to be added to the list of events
     *                           that the Hook triggers for - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "remove_events"} -> determines a list of events to be removed from the list of
     *                           events that the Hook triggers for - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "active"} -> determines if notifications are sent when the webhook is triggered.
     *                           Set to {@code "true"} to send notifications - [boolean, default true]
     *                       </li>
     *                    </ul>
     * @return repository webhook as {@link RepositoryWebhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#update-a-repository-webhook">
     * Update a repository webhook</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/hooks/{hook_id}")
    public RepositoryWebhook updateRepositoryWebhook(Repository repository, long hookId,
                                                     Params bodyParams) throws IOException {
        return updateRepositoryWebhook(repository.getOwner().getLogin(), repository.getName(), hookId, bodyParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to update a webhook configured in a repository. If you previously had a secret set, you must provide the
     * same secret or set a new secret or the secret will be removed. If you are only updating individual webhook config
     * properties, use "Update a webhook configuration for a repository."
     *
     * @param repository: the repository where update the webhook
     * @param hookId:     the unique identifier of the hook
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "config"} -> config webhook - [{@link Webhook}]
     *                       </li>
     *                       <li>
     *                           {@code "events"} -> determines what events the hook is triggered for. Set to ["*"] to
     *                           receive all possible events - [array of strings, default ["push"]]
     *                       </li>
     *                       <li>
     *                           {@code "add_events"} -> determines a list of events to be added to the list of events
     *                           that the Hook triggers for - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "remove_events"} -> determines a list of events to be removed from the list of
     *                           events that the Hook triggers for - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "active"} -> determines if notifications are sent when the webhook is triggered.
     *                           Set to {@code "true"} to send notifications - [boolean, default true]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository webhook as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#update-a-repository-webhook">
     * Update a repository webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/hooks/{hook_id}")
    public <T> T updateRepositoryWebhook(Repository repository, long hookId, Params bodyParams,
                                         ReturnFormat format) throws IOException {
        return updateRepositoryWebhook(repository.getOwner().getLogin(), repository.getName(), hookId, bodyParams, format);
    }

    /**
     * Method to update a webhook configured in a repository. If you previously had a secret set, you must provide the
     * same secret or set a new secret or the secret will be removed. If you are only updating individual webhook config
     * properties, use "Update a webhook configuration for a repository."
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param hook:       the hook to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "config"} -> config webhook - [{@link Webhook}]
     *                       </li>
     *                       <li>
     *                           {@code "events"} -> determines what events the hook is triggered for. Set to ["*"] to
     *                           receive all possible events - [array of strings, default ["push"]]
     *                       </li>
     *                       <li>
     *                           {@code "add_events"} -> determines a list of events to be added to the list of events
     *                           that the Hook triggers for - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "remove_events"} -> determines a list of events to be removed from the list of
     *                           events that the Hook triggers for - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "active"} -> determines if notifications are sent when the webhook is triggered.
     *                           Set to {@code "true"} to send notifications - [boolean, default true]
     *                       </li>
     *                    </ul>
     * @return repository webhook as {@link RepositoryWebhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#update-a-repository-webhook">
     * Update a repository webhook</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/hooks/{hook_id}")
    public RepositoryWebhook updateRepositoryWebhook(String owner, String repo, RepositoryWebhook hook,
                                                     Params bodyParams) throws IOException {
        return updateRepositoryWebhook(owner, repo, hook.getId(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update a webhook configured in a repository. If you previously had a secret set, you must provide the
     * same secret or set a new secret or the secret will be removed. If you are only updating individual webhook config
     * properties, use "Update a webhook configuration for a repository."
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param hook:       the hook to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "config"} -> config webhook - [{@link Webhook}]
     *                       </li>
     *                       <li>
     *                           {@code "events"} -> determines what events the hook is triggered for. Set to ["*"] to
     *                           receive all possible events - [array of strings, default ["push"]]
     *                       </li>
     *                       <li>
     *                           {@code "add_events"} -> determines a list of events to be added to the list of events
     *                           that the Hook triggers for - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "remove_events"} -> determines a list of events to be removed from the list of
     *                           events that the Hook triggers for - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "active"} -> determines if notifications are sent when the webhook is triggered.
     *                           Set to {@code "true"} to send notifications - [boolean, default true]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository webhook as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#update-a-repository-webhook">
     * Update a repository webhook</a>
     **/
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/hooks/{hook_id}")
    public <T> T updateRepositoryWebhook(String owner, String repo, RepositoryWebhook hook, Params bodyParams,
                                         ReturnFormat format) throws IOException {
        return updateRepositoryWebhook(owner, repo, hook.getId(), bodyParams, format);
    }

    /**
     * Method to update a webhook configured in a repository. If you previously had a secret set, you must provide the
     * same secret or set a new secret or the secret will be removed. If you are only updating individual webhook config
     * properties, use "Update a webhook configuration for a repository."
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param hookId:     the unique identifier of the hook
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "config"} -> config webhook - [{@link Webhook}]
     *                       </li>
     *                       <li>
     *                           {@code "events"} -> determines what events the hook is triggered for. Set to ["*"] to
     *                           receive all possible events - [array of strings, default ["push"]]
     *                       </li>
     *                       <li>
     *                           {@code "add_events"} -> determines a list of events to be added to the list of events
     *                           that the Hook triggers for - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "remove_events"} -> determines a list of events to be removed from the list of
     *                           events that the Hook triggers for - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "active"} -> determines if notifications are sent when the webhook is triggered.
     *                           Set to {@code "true"} to send notifications - [boolean, default true]
     *                       </li>
     *                    </ul>
     * @return repository webhook as {@link RepositoryWebhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#update-a-repository-webhook">
     * Update a repository webhook</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/hooks/{hook_id}")
    public RepositoryWebhook updateRepositoryWebhook(String owner, String repo, long hookId,
                                                     Params bodyParams) throws IOException {
        return updateRepositoryWebhook(owner, repo, hookId, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update a webhook configured in a repository. If you previously had a secret set, you must provide the
     * same secret or set a new secret or the secret will be removed. If you are only updating individual webhook config
     * properties, use "Update a webhook configuration for a repository."
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param hookId:     the unique identifier of the hook
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "config"} -> config webhook - [{@link Webhook}]
     *                       </li>
     *                       <li>
     *                           {@code "events"} -> determines what events the hook is triggered for. Set to ["*"] to
     *                           receive all possible events - [array of strings, default ["push"]]
     *                       </li>
     *                       <li>
     *                           {@code "add_events"} -> determines a list of events to be added to the list of events
     *                           that the Hook triggers for - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "remove_events"} -> determines a list of events to be removed from the list of
     *                           events that the Hook triggers for - [array of strings]
     *                       </li>
     *                       <li>
     *                           {@code "active"} -> determines if notifications are sent when the webhook is triggered.
     *                           Set to {@code "true"} to send notifications - [boolean, default true]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository webhook as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#update-a-repository-webhook">
     * Update a repository webhook</a>
     **/
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/hooks/{hook_id}")
    public <T> T updateRepositoryWebhook(String owner, String repo, long hookId, Params bodyParams,
                                         ReturnFormat format) throws IOException {
        return returnRepositoryWebhook(sendPatchRequest(REPOS_PATH + owner + "/" + repo + HOOKS_PATH
                + "/" + hookId, bodyParams), format);
    }

    /**
     * Method to create a repository webhook
     *
     * @param repositoryWebhookResponse: obtained from GitHub's response
     * @param format:                    return type formatter -> {@link ReturnFormat}
     * @return repository webhook as {@code "format"} defines
     **/
    @Returner
    private <T> T returnRepositoryWebhook(String repositoryWebhookResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(repositoryWebhookResponse);
            case LIBRARY_OBJECT:
                return (T) new RepositoryWebhook(new JSONObject(repositoryWebhookResponse));
            default:
                return (T) repositoryWebhookResponse;
        }
    }

    /**
     * Method to delete a repository webhook
     *
     * @param repository: the repository where delete the webhook
     * @param hook:       the hook to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#delete-a-repository-webhook">
     * Delete a repository webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/hooks/{hook_id}")
    public boolean deleteRepositoryWebhook(Repository repository, RepositoryWebhook hook) {
        return deleteRepositoryWebhook(repository.getOwner().getLogin(), repository.getName(), hook.getId());
    }

    /**
     * Method to delete a repository webhook
     *
     * @param repository: the repository where delete the webhook
     * @param hookId:     the unique identifier of the hook
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#delete-a-repository-webhook">
     * Delete a repository webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/hooks/{hook_id}")
    public boolean deleteRepositoryWebhook(Repository repository, long hookId) {
        return deleteRepositoryWebhook(repository.getOwner().getLogin(), repository.getName(), hookId);
    }

    /**
     * Method to delete a repository webhook
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param hook:  the hook to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#delete-a-repository-webhook">
     * Delete a repository webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/hooks/{hook_id}")
    public boolean deleteRepositoryWebhook(String owner, String repo, RepositoryWebhook hook) {
        return deleteRepositoryWebhook(owner, repo, hook.getId());
    }

    /**
     * Method to delete a repository webhook
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param hookId: the unique identifier of the hook
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#delete-a-repository-webhook">
     * Delete a repository webhook</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/hooks/{hook_id}")
    public boolean deleteRepositoryWebhook(String owner, String repo, long hookId) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + HOOKS_PATH + "/" + hookId);
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
     * Method to ping a repository webhook <br>
     * This will trigger a ping event to be sent to the hook
     *
     * @param repository: the repository where ping the webhook
     * @param hook:       the hook to ping
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#ping-a-repository-webhook">
     * Ping a repository webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/hooks/{hook_id}/pings")
    public boolean pingRepositoryWebhook(Repository repository, RepositoryWebhook hook) {
        return pingRepositoryWebhook(repository.getOwner().getLogin(), repository.getName(), hook.getId());
    }

    /**
     * Method to ping a repository webhook <br>
     * This will trigger a ping event to be sent to the hook
     *
     * @param repository: the repository where ping the webhook
     * @param hookId:     the unique identifier of the hook
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#ping-a-repository-webhook">
     * Ping a repository webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/hooks/{hook_id}/pings")
    public boolean pingRepositoryWebhook(Repository repository, long hookId) {
        return pingRepositoryWebhook(repository.getOwner().getLogin(), repository.getName(), hookId);
    }

    /**
     * Method to ping a repository webhook <br>
     * This will trigger a ping event to be sent to the hook
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param hook:  the hook to ping
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#ping-a-repository-webhook">
     * Ping a repository webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/hooks/{hook_id}/pings")
    public boolean pingRepositoryWebhook(String owner, String repo, RepositoryWebhook hook) {
        return pingRepositoryWebhook(owner, repo, hook.getId());
    }

    /**
     * Method to ping a repository webhook <br>
     * This will trigger a ping event to be sent to the hook
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param hookId: the unique identifier of the hook
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#ping-a-repository-webhook">
     * Ping a repository webhook</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/hooks/{hook_id}/pings")
    public boolean pingRepositoryWebhook(String owner, String repo, long hookId) {
        try {
            sendPostRequest(REPOS_PATH + owner + "/" + repo + HOOKS_PATH + "/" + hookId + PINGS_PATH, null);
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
     * Method to test the push repository webhook <br>
     * This will trigger the hook with the latest push to the current repository if the hook is subscribed to push events.
     * If the hook is not subscribed to push events, the server will respond with 204 but no test POST will be generated
     *
     * @param repository: the repository where test the push the webhook
     * @param hook:       the hook to test the push
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#test-the-push-repository-webhook">
     * Test the push repository webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/hooks/{hook_id}/tests")
    public boolean testPushRepositoryWebhook(Repository repository, RepositoryWebhook hook) {
        return testPushRepositoryWebhook(repository.getOwner().getLogin(), repository.getName(), hook.getId());
    }

    /**
     * Method to test the push repository webhook <br>
     * This will trigger the hook with the latest push to the current repository if the hook is subscribed to push events.
     * If the hook is not subscribed to push events, the server will respond with 204 but no test POST will be generated
     *
     * @param repository: the repository where test the webhook
     * @param hookId:     the unique identifier of the hook
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#test-the-push-repository-webhook">
     * Test the push repository webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/hooks/{hook_id}/tests")
    public boolean testPushRepositoryWebhook(Repository repository, long hookId) {
        return testPushRepositoryWebhook(repository.getOwner().getLogin(), repository.getName(), hookId);
    }

    /**
     * Method to test the push repository webhook <br>
     * This will trigger the hook with the latest push to the current repository if the hook is subscribed to push events.
     * If the hook is not subscribed to push events, the server will respond with 204 but no test POST will be generated
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param hook:  the hook to test the push
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#test-the-push-repository-webhook">
     * Test the push repository webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/hooks/{hook_id}/tests")
    public boolean testPushRepositoryWebhook(String owner, String repo, RepositoryWebhook hook) {
        return testPushRepositoryWebhook(owner, repo, hook.getId());
    }

    /**
     * Method to test the push repository webhook <br>
     * This will trigger the hook with the latest push to the current repository if the hook is subscribed to push events.
     * If the hook is not subscribed to push events, the server will respond with 204 but no test POST will be generated
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param hookId: the unique identifier of the hook
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repos#test-the-push-repository-webhook">
     * Test the push repository webhook</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/hooks/{hook_id}/tests")
    public boolean testPushRepositoryWebhook(String owner, String repo, long hookId) {
        try {
            sendPostRequest(REPOS_PATH + owner + "/" + repo + HOOKS_PATH + "/" + hookId + TESTS_PATH, null);
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
