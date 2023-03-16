package com.tecknobit.githubmanager.repositorieswebhooks.configuration;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.apps.webhooks.records.Webhook;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;

import java.io.IOException;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.PATCH;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.apps.webhooks.records.Webhook.returnWebhook;
import static com.tecknobit.githubmanager.organizations.webhooks.GitHubOrganizationWebhooksManager.CONFIG_PATH;
import static com.tecknobit.githubmanager.organizations.webhooks.GitHubOrganizationWebhooksManager.HOOKS_PATH;

/**
 * The {@code GitHubRepoWebHookConfiguration} class is useful to manage all GitHub's repository webhook configuration
 * endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repo-config">
 * Repository Webhook Configuration</a>
 * @see GitHubManager
 **/
public class GitHubRepoWebHookConfiguration extends GitHubManager {

    /**
     * Constructor to init a {@link GitHubRepoWebHookConfiguration}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubRepoWebHookConfiguration(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubRepoWebHookConfiguration}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubRepoWebHookConfiguration(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubRepoWebHookConfiguration}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubRepoWebHookConfiguration(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubRepoWebHookConfiguration}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubRepoWebHookConfiguration(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubRepoWebHookConfiguration} <br>
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
    public GitHubRepoWebHookConfiguration() {
        super();
    }

    /**
     * Method to return the webhook configuration for a repository. To get more information about the webhook, including
     * the active state and events, use "Get a repository webhook."
     * Access tokens must have the {@code "read:repo_hook"} or repo scope, and GitHub Apps must have the
     * {@code "repository_hooks:read"} permission
     *
     * @param repository: the repository from fetch the webhook
     * @param hookId:     the unique identifier of the hook
     * @return webhook as {@link Webhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repo-config#get-a-webhook-configuration-for-a-repository">
     * Get a webhook configuration for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/hooks/{hook_id}/config")
    public Webhook getRepositoryWebhookConfiguration(Repository repository, long hookId) throws IOException {
        return getRepositoryWebhookConfiguration(repository.getOwner().getLogin(), repository.getName(), hookId,
                LIBRARY_OBJECT);
    }

    /**
     * Method to return the webhook configuration for a repository. To get more information about the webhook, including
     * the active state and events, use "Get a repository webhook."
     * Access tokens must have the {@code "read:repo_hook"} or repo scope, and GitHub Apps must have the
     * {@code "repository_hooks:read"} permission
     *
     * @param repository: the repository from fetch the webhook
     * @param hookId:     the unique identifier of the hook
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return webhook as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repo-config#get-a-webhook-configuration-for-a-repository">
     * Get a webhook configuration for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/hooks/{hook_id}/config")
    public <T> T getRepositoryWebhookConfiguration(Repository repository, long hookId,
                                                   ReturnFormat format) throws IOException {
        return getRepositoryWebhookConfiguration(repository.getOwner().getLogin(), repository.getName(), hookId, format);
    }

    /**
     * Method to return the webhook configuration for a repository. To get more information about the webhook, including
     * the active state and events, use "Get a repository webhook."
     * Access tokens must have the {@code "read:repo_hook"} or repo scope, and GitHub Apps must have the
     * {@code "repository_hooks:read"} permission
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param hookId: the unique identifier of the hook
     * @return webhook as {@link Webhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repo-config#get-a-webhook-configuration-for-a-repository">
     * Get a webhook configuration for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/hooks/{hook_id}/config")
    public Webhook getRepositoryWebhookConfiguration(String owner, String repo, long hookId) throws IOException {
        return getRepositoryWebhookConfiguration(owner, repo, hookId, LIBRARY_OBJECT);
    }

    /**
     * Method to return the webhook configuration for a repository. To get more information about the webhook, including
     * the active state and events, use "Get a repository webhook."
     * Access tokens must have the {@code "read:repo_hook"} or repo scope, and GitHub Apps must have the
     * {@code "repository_hooks:read"} permission
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param hookId: the unique identifier of the hook
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return webhook as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repo-config#get-a-webhook-configuration-for-a-repository">
     * Get a webhook configuration for a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/hooks/{hook_id}/config")
    public <T> T getRepositoryWebhookConfiguration(String owner, String repo, long hookId,
                                                   ReturnFormat format) throws IOException {
        return returnWebhook(sendGetRequest(REPOS_PATH + owner + "/" + repo + HOOKS_PATH + "/" +
                hookId + CONFIG_PATH), format);
    }

    /**
     * Method to return the webhook configuration for a repository. To get more information about the webhook, including
     * the active state and events, use "Get a repository webhook."
     * Access tokens must have the {@code "read:repo_hook"} or repo scope, and GitHub Apps must have the
     * {@code "repository_hooks:read"} permission
     *
     * @param repository: the repository from fetch the webhook
     * @param hookId:     the unique identifier of the hook
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "url"} -> the URL to which the payloads will be delivered - [string]
     *                       </li>
     *                       <li>
     *                           {@code "content_type"} -> the media type used to serialize the payloads.
     *                           Supported values include json and form - [string, default form]
     *                       </li>
     *                       <li>
     *                           {@code "secret"} -> if provided, the secret will be used as the key to generate the
     *                           HMAC hex digest value for delivery signature headers - [string]
     *                       </li>
     *                       <li>
     *                           {@code "insecure_ssl"} -> determines whether the SSL certificate of the host for {@code "url"} will be verified
     *                           when delivering payloads. Supported values include {@code "0"} (verification is performed) and {@code "1"}
     *                           (verification is not performed). The default is {@code "0"}. <br>
     *                           <strong>Strongly recommendation not setting this to {@code "1"} as you are subject to
     *                           man-in-the-middle and other attacks </strong> - [string or number, default 0]
     *                       </li>
     *                    </ul>
     * @return webhook as {@link Webhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repo-config#update-a-webhook-configuration-for-a-repository">
     * Update a webhook configuration for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/hooks/{hook_id}/config")
    public Webhook updateRepositoryWebhookConfiguration(Repository repository, long hookId,
                                                        Params bodyParams) throws IOException {
        return updateRepositoryWebhookConfiguration(repository.getOwner().getLogin(), repository.getName(), hookId,
                bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to return the webhook configuration for a repository. To get more information about the webhook, including
     * the active state and events, use "Get a repository webhook."
     * Access tokens must have the {@code "read:repo_hook"} or repo scope, and GitHub Apps must have the
     * {@code "repository_hooks:read"} permission
     *
     * @param repository: the repository from fetch the webhook
     * @param hookId:     the unique identifier of the hook
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "url"} -> the URL to which the payloads will be delivered - [string]
     *                       </li>
     *                       <li>
     *                           {@code "content_type"} -> the media type used to serialize the payloads.
     *                           Supported values include json and form - [string, default form]
     *                       </li>
     *                       <li>
     *                           {@code "secret"} -> if provided, the secret will be used as the key to generate the
     *                           HMAC hex digest value for delivery signature headers - [string]
     *                       </li>
     *                       <li>
     *                           {@code "insecure_ssl"} -> determines whether the SSL certificate of the host for {@code "url"} will be verified
     *                           when delivering payloads. Supported values include {@code "0"} (verification is performed) and {@code "1"}
     *                           (verification is not performed). The default is {@code "0"}. <br>
     *                           <strong>Strongly recommendation not setting this to {@code "1"} as you are subject to
     *                           man-in-the-middle and other attacks </strong> - [string or number, default 0]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return webhook as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repo-config#update-a-webhook-configuration-for-a-repository">
     * Update a webhook configuration for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/hooks/{hook_id}/config")
    public <T> T updateRepositoryWebhookConfiguration(Repository repository, long hookId, Params bodyParams,
                                                      ReturnFormat format) throws IOException {
        return updateRepositoryWebhookConfiguration(repository.getOwner().getLogin(), repository.getName(), hookId,
                bodyParams, format);
    }

    /**
     * Method to return the webhook configuration for a repository. To get more information about the webhook, including
     * the active state and events, use "Get a repository webhook."
     * Access tokens must have the {@code "read:repo_hook"} or repo scope, and GitHub Apps must have the
     * {@code "repository_hooks:read"} permission
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param hookId:     the unique identifier of the hook
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "url"} -> the URL to which the payloads will be delivered - [string]
     *                       </li>
     *                       <li>
     *                           {@code "content_type"} -> the media type used to serialize the payloads.
     *                           Supported values include json and form - [string, default form]
     *                       </li>
     *                       <li>
     *                           {@code "secret"} -> if provided, the secret will be used as the key to generate the
     *                           HMAC hex digest value for delivery signature headers - [string]
     *                       </li>
     *                       <li>
     *                           {@code "insecure_ssl"} -> determines whether the SSL certificate of the host for {@code "url"} will be verified
     *                           when delivering payloads. Supported values include {@code "0"} (verification is performed) and {@code "1"}
     *                           (verification is not performed). The default is {@code "0"}. <br>
     *                           <strong>Strongly recommendation not setting this to {@code "1"} as you are subject to
     *                           man-in-the-middle and other attacks </strong> - [string or number, default 0]
     *                       </li>
     *                    </ul>
     * @return webhook as {@link Webhook} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repo-config#update-a-webhook-configuration-for-a-repository">
     * Update a webhook configuration for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/hooks/{hook_id}/config")
    public Webhook updateRepositoryWebhookConfiguration(String owner, String repo, long hookId,
                                                        Params bodyParams) throws IOException {
        return updateRepositoryWebhookConfiguration(owner, repo, hookId, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to return the webhook configuration for a repository. To get more information about the webhook, including
     * the active state and events, use "Get a repository webhook."
     * Access tokens must have the {@code "read:repo_hook"} or repo scope, and GitHub Apps must have the
     * {@code "repository_hooks:read"} permission
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param hookId:     the unique identifier of the hook
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "url"} -> the URL to which the payloads will be delivered - [string]
     *                       </li>
     *                       <li>
     *                           {@code "content_type"} -> the media type used to serialize the payloads.
     *                           Supported values include json and form - [string, default form]
     *                       </li>
     *                       <li>
     *                           {@code "secret"} -> if provided, the secret will be used as the key to generate the
     *                           HMAC hex digest value for delivery signature headers - [string]
     *                       </li>
     *                       <li>
     *                           {@code "insecure_ssl"} -> determines whether the SSL certificate of the host for {@code "url"} will be verified
     *                           when delivering payloads. Supported values include {@code "0"} (verification is performed) and {@code "1"}
     *                           (verification is not performed). The default is {@code "0"}. <br>
     *                           <strong>Strongly recommendation not setting this to {@code "1"} as you are subject to
     *                           man-in-the-middle and other attacks </strong> - [string or number, default 0]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return webhook as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/webhooks/repo-config#update-a-webhook-configuration-for-a-repository">
     * Update a webhook configuration for a repository</a>
     **/
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/hooks/{hook_id}/config")
    public <T> T updateRepositoryWebhookConfiguration(String owner, String repo, long hookId, Params bodyParams,
                                                      ReturnFormat format) throws IOException {
        return returnWebhook(sendPatchRequest(REPOS_PATH + owner + "/" + repo + HOOKS_PATH + "/" +
                hookId + CONFIG_PATH, bodyParams), format);
    }

}
