package com.tecknobit.githubmanager.interactions.repository;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.interactions.records.Interaction;
import com.tecknobit.githubmanager.interactions.records.Interaction.Expiry;
import com.tecknobit.githubmanager.interactions.records.Interaction.Limit;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;

import java.io.IOException;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.interactions.organization.GitHubOrganizationInteractionsManager.INTERACTION_LIMITS_PATH;
import static com.tecknobit.githubmanager.interactions.records.Interaction.returnInteraction;

/**
 * The {@code GitHubRepositoryInteractionsManager} class is useful to manage all GitHub's repository interactions endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/repos">
 * Repository interactions</a>
 * @see GitHubManager
 **/
public class GitHubRepositoryInteractionsManager extends GitHubManager {

    /**
     * Constructor to init a {@link GitHubRepositoryInteractionsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubRepositoryInteractionsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubRepositoryInteractionsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubRepositoryInteractionsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubRepositoryInteractionsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubRepositoryInteractionsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubRepositoryInteractionsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubRepositoryInteractionsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubRepositoryInteractionsManager} <br>
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
    public GitHubRepositoryInteractionsManager() {
        super();
    }

    /**
     * Method to show which type of GitHub user can interact with this repository and when the restriction expires.
     * If there are no restrictions, you will see an empty response (null object)
     *
     * @param repository: the repository from fetch the restrictions
     * @return interaction restrictions as {@link Interaction} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/repos#get-interaction-restrictions-for-a-repository">
     * Get interaction restrictions for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/interaction-limits")
    public Interaction getInteractionRestrictions(Repository repository) throws IOException {
        return getInteractionRestrictions(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to show which type of GitHub user can interact with this repository and when the restriction expires.
     * If there are no restrictions, you will see an empty response (null object)
     *
     * @param repository: the repository from fetch the restrictions
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return interaction restrictions as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/repos#get-interaction-restrictions-for-a-repository">
     * Get interaction restrictions for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/interaction-limits")
    public <T> T getInteractionRestrictions(Repository repository, ReturnFormat format) throws IOException {
        return getInteractionRestrictions(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to show which type of GitHub user can interact with this repository and when the restriction expires.
     * If there are no restrictions, you will see an empty response (null object)
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return interaction restrictions as {@link Interaction} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/repos#get-interaction-restrictions-for-a-repository">
     * Get interaction restrictions for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/interaction-limits")
    public Interaction getInteractionRestrictions(String owner, String repo) throws IOException {
        return getInteractionRestrictions(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to show which type of GitHub user can interact with this repository and when the restriction expires.
     * If there are no restrictions, you will see an empty response (null object)
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return interaction restrictions as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/repos#get-interaction-restrictions-for-a-repository">
     * Get interaction restrictions for a repository</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/interaction-limits")
    public <T> T getInteractionRestrictions(String owner, String repo, ReturnFormat format) throws IOException {
        return returnInteraction(sendGetRequest(REPOS_PATH + owner + "/" + repo
                + INTERACTION_LIMITS_PATH), format);
    }

    /**
     * Method to temporarily restricts interactions to a certain type of GitHub user within the given repository.
     * You must have owner or admin access to set these restrictions.
     * If an interaction limit is set for the user or organization that owns this repository, you will receive a 409 Conflict
     * response and will not be able to use this endpoint to change the interaction limit for a single repository
     *
     * @param repository: the repository where set the restrictions
     * @param limit:      the type of GitHub user that can comment, open issues, or create pull requests while the interaction
     *                    limit is in effect
     * @return interaction restrictions as {@link Interaction} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/repos#set-interaction-restrictions-for-a-repository">
     * Set interaction restrictions for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/interaction-limits")
    public Interaction setInteractionRestrictions(Repository repository, Limit limit) throws IOException {
        return setInteractionRestrictions(repository.getOwner().getLogin(), repository.getName(), limit, LIBRARY_OBJECT);
    }

    /**
     * Method to temporarily restricts interactions to a certain type of GitHub user within the given repository.
     * You must have owner or admin access to set these restrictions.
     * If an interaction limit is set for the user or organization that owns this repository, you will receive a 409 Conflict
     * response and will not be able to use this endpoint to change the interaction limit for a single repository
     *
     * @param repository: the repository where set the restrictions
     * @param limit:      the type of GitHub user that can comment, open issues, or create pull requests while the interaction
     *                    limit is in effect
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return interaction restrictions as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/repos#set-interaction-restrictions-for-a-repository">
     * Set interaction restrictions for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/interaction-limits")
    public <T> T setInteractionRestrictions(Repository repository, Limit limit, ReturnFormat format) throws IOException {
        return setInteractionRestrictions(repository.getOwner().getLogin(), repository.getName(), limit, format);
    }

    /**
     * Method to temporarily restricts interactions to a certain type of GitHub user within the given repository.
     * You must have owner or admin access to set these restrictions.
     * If an interaction limit is set for the user or organization that owns this repository, you will receive a 409 Conflict
     * response and will not be able to use this endpoint to change the interaction limit for a single repository
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param limit: the type of GitHub user that can comment, open issues, or create pull requests while the interaction
     *               limit is in effect
     * @return interaction restrictions as {@link Interaction} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/repos#set-interaction-restrictions-for-a-repository">
     * Set interaction restrictions for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/interaction-limits")
    public Interaction setInteractionRestrictions(String owner, String repo, Limit limit) throws IOException {
        return setInteractionRestrictions(owner, repo, limit, LIBRARY_OBJECT);
    }

    /**
     * Method to temporarily restricts interactions to a certain type of GitHub user within the given repository.
     * You must have owner or admin access to set these restrictions.
     * If an interaction limit is set for the user or organization that owns this repository, you will receive a 409 Conflict
     * response and will not be able to use this endpoint to change the interaction limit for a single repository
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param limit: the type of GitHub user that can comment, open issues, or create pull requests while the interaction
     *               limit is in effect
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return interaction restrictions as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/repos#set-interaction-restrictions-for-a-repository">
     * Set interaction restrictions for a repository</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/interaction-limits")
    public <T> T setInteractionRestrictions(String owner, String repo, Limit limit, ReturnFormat format) throws IOException {
        return setInteractionRestrictions(owner, repo, limit, null, format);
    }

    /**
     * Method to temporarily restricts interactions to a certain type of GitHub user within the given repository.
     * You must have owner or admin access to set these restrictions.
     * If an interaction limit is set for the user or organization that owns this repository, you will receive a 409 Conflict
     * response and will not be able to use this endpoint to change the interaction limit for a single repository
     *
     * @param repository: the repository where set the restrictions
     * @param limit:      the type of GitHub user that can comment, open issues, or create pull requests while the interaction
     *                    limit is in effect
     * @param expiry:     the duration of the interaction restriction
     * @return interaction restrictions as {@link Interaction} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/repos#set-interaction-restrictions-for-a-repository">
     * Set interaction restrictions for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/interaction-limits")
    public Interaction setInteractionRestrictions(Repository repository, Limit limit, Expiry expiry) throws IOException {
        return setInteractionRestrictions(repository.getOwner().getLogin(), repository.getName(), limit, expiry,
                LIBRARY_OBJECT);
    }

    /**
     * Method to temporarily restricts interactions to a certain type of GitHub user within the given repository.
     * You must have owner or admin access to set these restrictions.
     * If an interaction limit is set for the user or organization that owns this repository, you will receive a 409 Conflict
     * response and will not be able to use this endpoint to change the interaction limit for a single repository
     *
     * @param repository: the repository where set the restrictions
     * @param limit:      the type of GitHub user that can comment, open issues, or create pull requests while the interaction
     *                    limit is in effect
     * @param expiry:     the duration of the interaction restriction
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return interaction restrictions as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/repos#set-interaction-restrictions-for-a-repository">
     * Set interaction restrictions for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/interaction-limits")
    public <T> T setInteractionRestrictions(Repository repository, Limit limit, Expiry expiry,
                                            ReturnFormat format) throws IOException {
        return setInteractionRestrictions(repository.getOwner().getLogin(), repository.getName(), limit, expiry, format);
    }

    /**
     * Method to temporarily restricts interactions to a certain type of GitHub user within the given repository.
     * You must have owner or admin access to set these restrictions.
     * If an interaction limit is set for the user or organization that owns this repository, you will receive a 409 Conflict
     * response and will not be able to use this endpoint to change the interaction limit for a single repository
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param limit:  the type of GitHub user that can comment, open issues, or create pull requests while the interaction
     *                limit is in effect
     * @param expiry: the duration of the interaction restriction
     * @return interaction restrictions as {@link Interaction} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/repos#set-interaction-restrictions-for-a-repository">
     * Set interaction restrictions for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/interaction-limits")
    public Interaction setInteractionRestrictions(String owner, String repo, Limit limit, Expiry expiry) throws IOException {
        return setInteractionRestrictions(owner, repo, limit, expiry, LIBRARY_OBJECT);
    }

    /**
     * Method to temporarily restricts interactions to a certain type of GitHub user within the given repository.
     * You must have owner or admin access to set these restrictions.
     * If an interaction limit is set for the user or organization that owns this repository, you will receive a 409 Conflict
     * response and will not be able to use this endpoint to change the interaction limit for a single repository
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param limit:  the type of GitHub user that can comment, open issues, or create pull requests while the interaction
     *                limit is in effect
     * @param expiry: the duration of the interaction restriction
     * @param format  :              return type formatter -> {@link ReturnFormat}
     * @return interaction restrictions as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/repos#set-interaction-restrictions-for-a-repository">
     * Set interaction restrictions for a repository</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/interaction-limits")
    public <T> T setInteractionRestrictions(String owner, String repo, Limit limit, Expiry expiry,
                                            ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("limit", limit);
        if (expiry != null)
            payload.addParam("expiry", expiry);
        return returnInteraction(sendPutRequest(REPOS_PATH + owner + "/" + repo + INTERACTION_LIMITS_PATH,
                payload), format);
    }

    /**
     * Method to remove all interaction restrictions from the given repository. You must have owner or admin access to
     * remove restrictions. If the interaction limit is set for the user or organization that owns this repository,
     * you will receive a 409 Conflict response and will not be able to use this endpoint to change the interaction
     * limit for a single repository
     *
     * @param repository: the repository where delete the restrictions
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/repos#remove-interaction-restrictions-for-a-repository">
     * Remove interaction restrictions for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/interaction-limits")
    public boolean removeInteractionRestrictions(Repository repository) {
        return removeInteractionRestrictions(repository.getOwner().getLogin(), repository.getName());
    }

    /**
     * Method to remove all interaction restrictions from the given repository. You must have owner or admin access to
     * remove restrictions. If the interaction limit is set for the user or organization that owns this repository,
     * you will receive a 409 Conflict response and will not be able to use this endpoint to change the interaction
     * limit for a single repository
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/repos#remove-interaction-restrictions-for-a-repository">
     * Remove interaction restrictions for a repository</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/interaction-limits")
    public boolean removeInteractionRestrictions(String owner, String repo) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + INTERACTION_LIMITS_PATH);
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
