package com.tecknobit.githubmanager.interactions.organization;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.interactions.records.Interaction;
import com.tecknobit.githubmanager.interactions.records.Interaction.Expiry;
import com.tecknobit.githubmanager.interactions.records.Interaction.Limit;
import com.tecknobit.githubmanager.organizations.organizations.records.Organization;

import java.io.IOException;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.interactions.records.Interaction.returnInteraction;

/**
 * The {@code GitHubOrganizationInteractionsManager} class is useful to manage all GitHub's organization interactions endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/orgs">
 * Organization interactions</a>
 * @see GitHubManager
 **/
public class GitHubOrganizationInteractionsManager extends GitHubManager {

    /**
     * {@code INTERACTION_LIMITS_PATH} constant for {@code "/interaction-limits"} path
     **/
    public static final String INTERACTION_LIMITS_PATH = "/interaction-limits";

    /**
     * Constructor to init a {@link GitHubOrganizationInteractionsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubOrganizationInteractionsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubOrganizationInteractionsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubOrganizationInteractionsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubOrganizationInteractionsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubOrganizationInteractionsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubOrganizationInteractionsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubOrganizationInteractionsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubOrganizationInteractionsManager} <br>
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
    public GitHubOrganizationInteractionsManager() {
        super();
    }

    /**
     * Method to show which type of GitHub user can interact with this organization and when the restriction expires.
     * If there is no restrictions, you will see an empty response (null object)
     *
     * @param org: the organization from fetch the restrictions
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/orgs#get-interaction-restrictions-for-an-organization">
     * Get interaction restrictions for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/interaction-limits")
    public Interaction getInteractionRestrictions(Organization org) throws IOException {
        return getInteractionRestrictions(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to show which type of GitHub user can interact with this organization and when the restriction expires.
     * If there is no restrictions, you will see an empty response (null object)
     *
     * @param org:   the organization from fetch the restrictions
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/orgs#get-interaction-restrictions-for-an-organization">
     * Get interaction restrictions for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/interaction-limits")
    public <T> T getInteractionRestrictions(Organization org, ReturnFormat format) throws IOException {
        return getInteractionRestrictions(org.getLogin(), format);
    }

    /**
     * Method to show which type of GitHub user can interact with this organization and when the restriction expires.
     * If there is no restrictions, you will see an empty response (null object)
     *
     * @param org: the organization name. The name is not case-sensitive
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/orgs#get-interaction-restrictions-for-an-organization">
     * Get interaction restrictions for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/interaction-limits")
    public Interaction getInteractionRestrictions(String org) throws IOException {
        return getInteractionRestrictions(org, LIBRARY_OBJECT);
    }

    /**
     * Method to show which type of GitHub user can interact with this organization and when the restriction expires.
     * If there is no restrictions, you will see an empty response (null object)
     *
     * @param org:   the organization name. The name is not case-sensitive
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/orgs#get-interaction-restrictions-for-an-organization">
     * Get interaction restrictions for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/interaction-limits")
    public <T> T getInteractionRestrictions(String org, ReturnFormat format) throws IOException {
        return returnInteraction(sendGetRequest(ORGS_PATH + org + INTERACTION_LIMITS_PATH), format);
    }

    /**
     * Method to temporarily restricts interactions to a certain type of GitHub user in any public repository in the given
     * organization. You must be an organization owner to set these restrictions.
     * Setting the interaction limit at the organization level will overwrite any interaction limits that are set for
     * individual repositories owned by the organization
     *
     * @param org:   the organization where set the restrictions
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/orgs#set-interaction-restrictions-for-an-organization">
     * Set interaction restrictions for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/interaction-limits")
    public Interaction setInteractionRestrictions(Organization org, Limit limit) throws IOException {
        return setInteractionRestrictions(org.getLogin(), limit, LIBRARY_OBJECT);
    }

    /**
     * Method to temporarily restricts interactions to a certain type of GitHub user in any public repository in the given
     * organization. You must be an organization owner to set these restrictions.
     * Setting the interaction limit at the organization level will overwrite any interaction limits that are set for
     * individual repositories owned by the organization
     *
     * @param org:   the organization where set the restrictions
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/orgs#set-interaction-restrictions-for-an-organization">
     * Set interaction restrictions for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/interaction-limits")
    public <T> T setInteractionRestrictions(Organization org, Limit limit, ReturnFormat format) throws IOException {
        return setInteractionRestrictions(org.getLogin(), limit, format);
    }

    /**
     * Method to temporarily restricts interactions to a certain type of GitHub user in any public repository in the given
     * organization. You must be an organization owner to set these restrictions.
     * Setting the interaction limit at the organization level will overwrite any interaction limits that are set for
     * individual repositories owned by the organization
     *
     * @param org:   the organization name. The name is not case-sensitive
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/orgs#set-interaction-restrictions-for-an-organization">
     * Set interaction restrictions for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/orgs/{org}/interaction-limits")
    public Interaction setInteractionRestrictions(String org, Limit limit) throws IOException {
        return setInteractionRestrictions(org, limit, LIBRARY_OBJECT);
    }

    /**
     * Method to temporarily restricts interactions to a certain type of GitHub user in any public repository in the given
     * organization. You must be an organization owner to set these restrictions.
     * Setting the interaction limit at the organization level will overwrite any interaction limits that are set for
     * individual repositories owned by the organization
     *
     * @param org:   the organization name. The name is not case-sensitive
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/orgs#set-interaction-restrictions-for-an-organization">
     * Set interaction restrictions for an organization</a>
     **/
    @RequestPath(method = PUT, path = "/orgs/{org}/interaction-limits")
    public <T> T setInteractionRestrictions(String org, Limit limit, ReturnFormat format) throws IOException {
        return setInteractionRestrictions(org, limit, null, format);
    }

    /**
     * Method to temporarily restricts interactions to a certain type of GitHub user in any public repository in the given
     * organization. You must be an organization owner to set these restrictions.
     * Setting the interaction limit at the organization level will overwrite any interaction limits that are set for
     * individual repositories owned by the organization
     *
     * @param org:    the organization where set the restrictions
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/orgs#set-interaction-restrictions-for-an-organization">
     * Set interaction restrictions for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/interaction-limits")
    public Interaction setInteractionRestrictions(Organization org, Limit limit, Expiry expiry) throws IOException {
        return setInteractionRestrictions(org.getLogin(), limit, expiry, LIBRARY_OBJECT);
    }

    /**
     * Method to temporarily restricts interactions to a certain type of GitHub user in any public repository in the given
     * organization. You must be an organization owner to set these restrictions.
     * Setting the interaction limit at the organization level will overwrite any interaction limits that are set for
     * individual repositories owned by the organization
     *
     * @param org:    the organization where set the restrictions
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/orgs#set-interaction-restrictions-for-an-organization">
     * Set interaction restrictions for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/interaction-limits")
    public <T> T setInteractionRestrictions(Organization org, Limit limit, Expiry expiry,
                                            ReturnFormat format) throws IOException {
        return setInteractionRestrictions(org.getLogin(), limit, expiry, format);
    }

    /**
     * Method to temporarily restricts interactions to a certain type of GitHub user in any public repository in the given
     * organization. You must be an organization owner to set these restrictions.
     * Setting the interaction limit at the organization level will overwrite any interaction limits that are set for
     * individual repositories owned by the organization
     *
     * @param org:    the organization name. The name is not case-sensitive
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/orgs#set-interaction-restrictions-for-an-organization">
     * Set interaction restrictions for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/orgs/{org}/interaction-limits")
    public Interaction setInteractionRestrictions(String org, Limit limit, Expiry expiry) throws IOException {
        return setInteractionRestrictions(org, limit, expiry, LIBRARY_OBJECT);
    }

    /**
     * Method to temporarily restricts interactions to a certain type of GitHub user in any public repository in the given
     * organization. You must be an organization owner to set these restrictions.
     * Setting the interaction limit at the organization level will overwrite any interaction limits that are set for
     * individual repositories owned by the organization
     *
     * @param org:    the organization name. The name is not case-sensitive
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/orgs#set-interaction-restrictions-for-an-organization">
     * Set interaction restrictions for an organization</a>
     **/
    @RequestPath(method = PUT, path = "/orgs/{org}/interaction-limits")
    public <T> T setInteractionRestrictions(String org, Limit limit, Expiry expiry, ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("limit", limit);
        if (expiry != null)
            payload.addParam("expiry", expiry);
        return returnInteraction(sendPutRequest(ORGS_PATH + org + INTERACTION_LIMITS_PATH, payload), format);
    }

    /**
     * Method to remove all interaction restrictions from public repositories in the given organization.
     * You must be an organization owner to remove restrictions
     *
     * @param org: the organization where delete the restrictions
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/orgs#remove-interaction-restrictions-for-an-organization">
     * Remove interaction restrictions for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/interaction-limits")
    public boolean removeInteractionRestrictions(Organization org) {
        return removeInteractionRestrictions(org.getLogin());
    }

    /**
     * Method to remove all interaction restrictions from public repositories in the given organization.
     * You must be an organization owner to remove restrictions
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/interactions/orgs#remove-interaction-restrictions-for-an-organization">
     * Remove interaction restrictions for an organization</a>
     **/
    @RequestPath(method = DELETE, path = "/orgs/{org}/interaction-limits")
    public boolean removeInteractionRestrictions(String org) {
        try {
            sendDeleteRequest(ORGS_PATH + org + INTERACTION_LIMITS_PATH);
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
