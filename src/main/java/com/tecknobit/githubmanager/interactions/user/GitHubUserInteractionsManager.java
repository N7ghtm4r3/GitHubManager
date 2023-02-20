package com.tecknobit.githubmanager.interactions.user;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.interactions.records.Interaction;
import com.tecknobit.githubmanager.interactions.records.Interaction.Expiry;
import com.tecknobit.githubmanager.interactions.records.Interaction.Limit;

import java.io.IOException;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.interactions.organization.GitHubOrganizationInteractionsManager.INTERACTION_LIMITS_PATH;
import static com.tecknobit.githubmanager.interactions.records.Interaction.returnInteraction;

/**
 * The {@code GitHubUserInteractionsManager} class is useful to manage all GitHub's user interactions endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/user">
 * User interactions</a>
 * @see GitHubManager
 **/
public class GitHubUserInteractionsManager extends GitHubManager {

    /**
     * {@code USER_INTERACTIONS_LIMITS_PATH} constant for {@code "user/interaction-limits"} path
     **/
    public static final String USER_INTERACTIONS_LIMITS_PATH = USER_PATH + INTERACTION_LIMITS_PATH;

    /**
     * Constructor to init a {@link GitHubUserInteractionsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubUserInteractionsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubUserInteractionsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubUserInteractionsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubUserInteractionsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubUserInteractionsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubUserInteractionsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubUserInteractionsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubUserInteractionsManager} <br>
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
    public GitHubUserInteractionsManager() {
        super();
    }

    /**
     * Method to show which type of GitHub user can interact with your public repositories and when the restriction expires
     * (null object). <br>
     * No-any params required
     *
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/user#get-interaction-restrictions-for-your-public-repositories">
     * Get interaction restrictions for your public repositories</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/interaction-limits")
    public Interaction getInteractionRestrictions() throws IOException {
        return getInteractionRestrictions(LIBRARY_OBJECT);
    }

    /**
     * Method to show which type of GitHub user can interact with your public repositories and when the restriction expires
     * (null object). <br>
     * No-any params required
     *
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/user#get-interaction-restrictions-for-your-public-repositories">
     * Get interaction restrictions for your public repositories</a>
     **/
    @RequestPath(method = GET, path = "/user/interaction-limits")
    public <T> T getInteractionRestrictions(ReturnFormat format) throws IOException {
        return returnInteraction(sendGetRequest(USER_INTERACTIONS_LIMITS_PATH), format);
    }

    /**
     * Method to temporarily restricts which type of GitHub user can interact with your public repositories. Setting the
     * interaction limit at the user level will overwrite any interaction limits that are set for individual repositories
     * owned by the user
     *
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/user#get-interaction-restrictions-for-your-public-repositories">
     * Set interaction restrictions for your public repositories</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/user/interaction-limits")
    public Interaction setInteractionRestrictions(Limit limit) throws IOException {
        return setInteractionRestrictions(limit, LIBRARY_OBJECT);
    }

    /**
     * Method to temporarily restricts which type of GitHub user can interact with your public repositories. Setting the
     * interaction limit at the user level will overwrite any interaction limits that are set for individual repositories
     * owned by the user
     *
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/user#get-interaction-restrictions-for-your-public-repositories">
     * Set interaction restrictions for your public repositories</a>
     **/
    @RequestPath(method = PUT, path = "/user/interaction-limits")
    public <T> T setInteractionRestrictions(Limit limit, ReturnFormat format) throws IOException {
        return setInteractionRestrictions(limit, null, format);
    }

    /**
     * Method to temporarily restricts which type of GitHub user can interact with your public repositories. Setting the
     * interaction limit at the user level will overwrite any interaction limits that are set for individual repositories
     * owned by the user
     *
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/user#get-interaction-restrictions-for-your-public-repositories">
     * Set interaction restrictions for your public repositories</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/user/interaction-limits")
    public Interaction setInteractionRestrictions(Limit limit, Expiry expiry) throws IOException {
        return setInteractionRestrictions(limit, expiry, LIBRARY_OBJECT);
    }

    /**
     * Method to temporarily restricts which type of GitHub user can interact with your public repositories. Setting the
     * interaction limit at the user level will overwrite any interaction limits that are set for individual repositories
     * owned by the user
     *
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/user#get-interaction-restrictions-for-your-public-repositories">
     * Set interaction restrictions for your public repositories</a>
     **/
    @RequestPath(method = PUT, path = "/user/interaction-limits")
    public <T> T setInteractionRestrictions(Limit limit, Expiry expiry, ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("limit", limit);
        if (expiry != null)
            payload.addParam("expiry", expiry);
        return returnInteraction(sendPutRequest(USER_INTERACTIONS_LIMITS_PATH, payload), format);
    }

    /**
     * Method to remove any interaction restrictions from your public repositories
     * No-any params required
     *
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/user#remove-interaction-restrictions-from-your-public-repositories">
     * Remove interaction restrictions from your public repositories</a>
     **/
    @RequestPath(method = DELETE, path = "/user/interaction-limits")
    public boolean removeInteractionRestrictions() {
        try {
            sendDeleteRequest(USER_INTERACTIONS_LIMITS_PATH);
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
