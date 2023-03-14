package com.tecknobit.githubmanager.actions.oidc;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.actions.oidc.records.OIDCSubjectClaim;
import com.tecknobit.githubmanager.organizations.organizations.records.Organization;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.PUT;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.actions.oidc.records.OIDCSubjectClaim.returnClaimKeysList;

/**
 * The {@code GitHubOIDCManager} class is useful to manage all GitHub's OIDC endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/oidc">
 * GitHub Actions OIDC</a>
 * @see GitHubManager
 **/
public class GitHubOIDCManager extends GitHubManager {

    /**
     * {@code ACTIONS_OIDC_CUSTOMIZATION_SUB_PATH} constant for {@code "/actions/oidc/customization/sub"} path
     **/
    public static final String ACTIONS_OIDC_CUSTOMIZATION_SUB_PATH = ACTIONS_PATH + "oidc/customization/sub";

    /**
     * Constructor to init a {@link GitHubOIDCManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubOIDCManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubOIDCManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubOIDCManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubOIDCManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubOIDCManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubOIDCManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubOIDCManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubOIDCManager} <br>
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
    public GitHubOIDCManager() {
        super();
    }

    /**
     * Method to get the customization template for an OpenID Connect (OIDC) subject claim.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * GitHub Apps must have the {@code "organization_administration:write"} permission to use this endpoint
     *
     * @param org: the organization from fetch the OIDC subject claim
     * @return customization template for an OpenID Connect (OIDC) subject claim as {@link ArrayList} of {@link String}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/oidc#get-the-customization-template-for-an-oidc-subject-claim-for-an-organization">
     * Get the customization template for an OIDC subject claim for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/oidc/customization/sub")
    public ArrayList<String> getOrganizationOIDCSubjectClaim(Organization org) throws IOException {
        return getOrganizationOIDCSubjectClaim(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the customization template for an OpenID Connect (OIDC) subject claim.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * GitHub Apps must have the {@code "organization_administration:write"} permission to use this endpoint
     *
     * @param org:    the organization from fetch the OIDC subject claim
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return customization template for an OpenID Connect (OIDC) subject claim as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/oidc#get-the-customization-template-for-an-oidc-subject-claim-for-an-organization">
     * Get the customization template for an OIDC subject claim for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/oidc/customization/sub")
    public <T> T getOrganizationOIDCSubjectClaim(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationOIDCSubjectClaim(org.getLogin(), format);
    }

    /**
     * Method to get the customization template for an OpenID Connect (OIDC) subject claim.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * GitHub Apps must have the {@code "organization_administration:write"} permission to use this endpoint
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return customization template for an OpenID Connect (OIDC) subject claim as {@link ArrayList} of {@link String}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/oidc#get-the-customization-template-for-an-oidc-subject-claim-for-an-organization">
     * Get the customization template for an OIDC subject claim for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/oidc/customization/sub")
    public ArrayList<String> getOrganizationOIDCSubjectClaim(String org) throws IOException {
        return getOrganizationOIDCSubjectClaim(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get the customization template for an OpenID Connect (OIDC) subject claim.
     * You must authenticate using an access token with the {@code "admin:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * GitHub Apps must have the {@code "organization_administration:write"} permission to use this endpoint
     *
     * @param org:    the organization name. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return customization template for an OpenID Connect (OIDC) subject claim as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/oidc#get-the-customization-template-for-an-oidc-subject-claim-for-an-organization">
     * Get the customization template for an OIDC subject claim for an organization</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/oidc/customization/sub")
    public <T> T getOrganizationOIDCSubjectClaim(String org, ReturnFormat format) throws IOException {
        String OIDCResponse = sendGetRequest(ORGS_PATH + org + ACTIONS_OIDC_CUSTOMIZATION_SUB_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONObject(OIDCResponse);
            case LIBRARY_OBJECT:
                return (T) returnClaimKeysList(new JSONObject(OIDCResponse).getJSONArray("include_claim_keys"));
            default:
                return (T) OIDCResponse;
        }
    }

    /**
     * Method to create or update the customization template for an OpenID Connect (OIDC) subject claim.
     * You must authenticate using an access token with the {@code "write:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "admin:org"} permission to use this endpoint
     *
     * @param org:              the organization where set the OIDC subject claim
     * @param includeClaimKeys: array of unique strings. Each claim key can only contain alphanumeric characters and underscores
     *                          as {@link ArrayList} of {@link String} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/oidc#set-the-customization-template-for-an-oidc-subject-claim-for-an-organization">
     * Set the customization template for an OIDC subject claim for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/oidc/customization/sub")
    public boolean setOrganizationOIDCSubjectClaim(Organization org, ArrayList<String> includeClaimKeys) {
        return setOrganizationOIDCSubjectClaim(org.getLogin(), includeClaimKeys.toArray(new String[0]));
    }

    /**
     * Method to create or update the customization template for an OpenID Connect (OIDC) subject claim.
     * You must authenticate using an access token with the {@code "write:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "admin:org"} permission to use this endpoint
     *
     * @param org:              the organization where set the OIDC subject claim
     * @param includeClaimKeys: array of unique strings. Each claim key can only contain alphanumeric characters and underscores
     *                          as array of {@link String} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/oidc#set-the-customization-template-for-an-oidc-subject-claim-for-an-organization">
     * Set the customization template for an OIDC subject claim for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/oidc/customization/sub")
    public boolean setOrganizationOIDCSubjectClaim(Organization org, String[] includeClaimKeys) {
        return setOrganizationOIDCSubjectClaim(org.getLogin(), includeClaimKeys);
    }

    /**
     * Method to create or update the customization template for an OpenID Connect (OIDC) subject claim.
     * You must authenticate using an access token with the {@code "write:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "admin:org"} permission to use this endpoint
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param includeClaimKeys: array of unique strings. Each claim key can only contain alphanumeric characters and underscores
     *                          as {@link ArrayList} of {@link String} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/oidc#set-the-customization-template-for-an-oidc-subject-claim-for-an-organization">
     * Set the customization template for an OIDC subject claim for an organization</a>
     **/
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/oidc/customization/sub")
    public boolean setOrganizationOIDCSubjectClaim(String org, ArrayList<String> includeClaimKeys) {
        return setOrganizationOIDCSubjectClaim(org, includeClaimKeys.toArray(new String[0]));
    }

    /**
     * Method to create or update the customization template for an OpenID Connect (OIDC) subject claim.
     * You must authenticate using an access token with the {@code "write:org"} scope to use this endpoint -> <b>
     * this step is automatically made by this library. </b> <br>
     * {@code "GitHub Apps"} must have the {@code "admin:org"} permission to use this endpoint
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param includeClaimKeys: array of unique strings. Each claim key can only contain alphanumeric characters and underscores
     *                          as array of {@link String} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/oidc#set-the-customization-template-for-an-oidc-subject-claim-for-an-organization">
     * Set the customization template for an OIDC subject claim for an organization</a>
     **/
    @RequestPath(method = PUT, path = "/orgs/{org}/actions/oidc/customization/sub")
    public boolean setOrganizationOIDCSubjectClaim(String org, String[] includeClaimKeys) {
        Params payload = new Params();
        payload.addParam("include_claim_keys", includeClaimKeys);
        try {
            sendPutRequest(ORGS_PATH + org + ACTIONS_OIDC_CUSTOMIZATION_SUB_PATH, payload);
            if (apiRequest.getResponseStatusCode() != 201) {
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
     * Method to get the customization template for an OpenID Connect (OIDC) subject claim.
     * You must authenticate using an access token with the repo scope to use this endpoint.  -> <b>
     * this step is automatically made by this library. </b> <br>
     * GitHub Apps must have the {@code "organization_administration:read"} permission to use this endpoint
     *
     * @param repository: the repository from fetch the OIDC subject claim
     * @return customization template for an OpenID Connect (OIDC) subject claim as {@link OIDCSubjectClaim} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/oidc#get-the-customization-template-for-an-oidc-subject-claim-for-a-repository>
     * Get the customization template for an OIDC subject claim for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/oidc/customization/sub")
    public OIDCSubjectClaim getRepositoryOIDCSubjectClaim(Repository repository) throws IOException {
        return getRepositoryOIDCSubjectClaim(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the customization template for an OpenID Connect (OIDC) subject claim.
     * You must authenticate using an access token with the repo scope to use this endpoint.  -> <b>
     * this step is automatically made by this library. </b> <br>
     * GitHub Apps must have the {@code "organization_administration:read"} permission to use this endpoint
     *
     * @param repository: the repository from fetch the OIDC subject claim
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return customization template for an OpenID Connect (OIDC) subject claim as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/oidc#get-the-customization-template-for-an-oidc-subject-claim-for-a-repository>
     * Get the customization template for an OIDC subject claim for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/oidc/customization/sub")
    public <T> T getRepositoryOIDCSubjectClaim(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryOIDCSubjectClaim(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the customization template for an OpenID Connect (OIDC) subject claim.
     * You must authenticate using an access token with the repo scope to use this endpoint.  -> <b>
     * this step is automatically made by this library. </b> <br>
     * GitHub Apps must have the {@code "organization_administration:read"} permission to use this endpoint
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return customization template for an OpenID Connect (OIDC) subject claim as {@link OIDCSubjectClaim} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/oidc#get-the-customization-template-for-an-oidc-subject-claim-for-a-repository>
     * Get the customization template for an OIDC subject claim for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/oidc/customization/sub")
    public OIDCSubjectClaim getRepositoryOIDCSubjectClaim(String owner, String repo) throws IOException {
        return getRepositoryOIDCSubjectClaim(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the customization template for an OpenID Connect (OIDC) subject claim.
     * You must authenticate using an access token with the repo scope to use this endpoint.  -> <b>
     * this step is automatically made by this library. </b> <br>
     * GitHub Apps must have the {@code "organization_administration:read"} permission to use this endpoint
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return customization template for an OpenID Connect (OIDC) subject claim as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/oidc#get-the-customization-template-for-an-oidc-subject-claim-for-a-repository>
     * Get the customization template for an OIDC subject claim for a repository</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/actions/oidc/customization/sub")
    public <T> T getRepositoryOIDCSubjectClaim(String owner, String repo, ReturnFormat format) throws IOException {
        String OIDCResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_OIDC_CUSTOMIZATION_SUB_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONObject(OIDCResponse);
            case LIBRARY_OBJECT:
                return (T) new OIDCSubjectClaim(new JSONObject(OIDCResponse));
            default:
                return (T) OIDCResponse;
        }
    }

    /**
     * Method to set the customization template and opt-in or opt-out flag for an OpenID Connect (OIDC) subject claim for a repository.
     * You must authenticate using an access token with the repo scope to use this endpoint.  -> <b>
     * this step is automatically made by this library. </b> <br>
     * GitHub Apps must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository: the repository where set the OIDC subject claim
     * @param useDefault: whether to use the default template or not. If {@code "true"}, the {@code "include_claim_keys"}
     *                    field is ignored
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/oidc#set-the-customization-template-for-an-oidc-subject-claim-for-a-repository">
     * Set the customization template for an OIDC subject claim for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/oidc/customization/sub")
    public boolean setRepositoryOIDCSubjectClaim(Repository repository, boolean useDefault) {
        return setRepositoryOIDCSubjectClaim(repository.getOwner().getLogin(), repository.getName(), useDefault);
    }

    /**
     * Method to set the customization template and opt-in or opt-out flag for an OpenID Connect (OIDC) subject claim for a repository.
     * You must authenticate using an access token with the repo scope to use this endpoint.  -> <b>
     * this step is automatically made by this library. </b> <br>
     * GitHub Apps must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param useDefault: whether to use the default template or not. If {@code "true"}, the {@code "include_claim_keys"}
     *                    field is ignored
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/oidc#set-the-customization-template-for-an-oidc-subject-claim-for-a-repository">
     * Set the customization template for an OIDC subject claim for a repository</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/oidc/customization/sub")
    public boolean setRepositoryOIDCSubjectClaim(String owner, String repo, boolean useDefault) {
        Params payload = new Params();
        payload.addParam("use_default", useDefault);
        try {
            sendPutRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_OIDC_CUSTOMIZATION_SUB_PATH, payload);
            if (apiRequest.getResponseStatusCode() != 201) {
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
     * Method to set the customization template and opt-in or opt-out flag for an OpenID Connect (OIDC) subject claim for a repository.
     * You must authenticate using an access token with the repo scope to use this endpoint.  -> <b>
     * this step is automatically made by this library. </b> <br>
     * GitHub Apps must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository: the repository where set the OIDC subject claim
     * @param useDefault: whether to use the default template or not. If {@code "true"}, the {@code "include_claim_keys"}
     *                    field is ignored
     * @param claimKeys:  array of unique strings. Each claim key can only contain alphanumeric characters and underscores
     *                    as {@link ArrayList} of {@link String} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/oidc#set-the-customization-template-for-an-oidc-subject-claim-for-a-repository">
     * Set the customization template for an OIDC subject claim for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/oidc/customization/sub")
    public boolean setRepositoryOIDCSubjectClaim(Repository repository, boolean useDefault, ArrayList<String> claimKeys) {
        return setRepositoryOIDCSubjectClaim(repository.getOwner().getLogin(), repository.getName(), useDefault,
                claimKeys.toArray(new String[0]));
    }

    /**
     * Method to set the customization template and opt-in or opt-out flag for an OpenID Connect (OIDC) subject claim for a repository.
     * You must authenticate using an access token with the repo scope to use this endpoint.  -> <b>
     * this step is automatically made by this library. </b> <br>
     * GitHub Apps must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param useDefault: whether to use the default template or not. If {@code "true"}, the {@code "include_claim_keys"}
     *                    field is ignored
     * @param claimKeys:  array of unique strings. Each claim key can only contain alphanumeric characters and underscores
     *                    as {@link ArrayList} of {@link String} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/oidc#set-the-customization-template-for-an-oidc-subject-claim-for-a-repository">
     * Set the customization template for an OIDC subject claim for a repository</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/oidc/customization/sub")
    public boolean setRepositoryOIDCSubjectClaim(String owner, String repo, boolean useDefault,
                                                 ArrayList<String> claimKeys) {
        return setRepositoryOIDCSubjectClaim(owner, repo, useDefault, claimKeys.toArray(new String[0]));
    }

    /**
     * Method to set the customization template and opt-in or opt-out flag for an OpenID Connect (OIDC) subject claim for a repository.
     * You must authenticate using an access token with the repo scope to use this endpoint.  -> <b>
     * this step is automatically made by this library. </b> <br>
     * GitHub Apps must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param repository: the repository where set the OIDC subject claim
     * @param useDefault: whether to use the default template or not. If {@code "true"}, the {@code "include_claim_keys"}
     *                    field is ignored
     * @param claimKeys:  array of unique strings. Each claim key can only contain alphanumeric characters and underscores
     *                    as array of {@link String} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/oidc#set-the-customization-template-for-an-oidc-subject-claim-for-a-repository">
     * Set the customization template for an OIDC subject claim for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/oidc/customization/sub")
    public boolean setRepositoryOIDCSubjectClaim(Repository repository, boolean useDefault, String[] claimKeys) {
        return setRepositoryOIDCSubjectClaim(repository.getOwner().getLogin(), repository.getName(), useDefault,
                claimKeys);
    }

    /**
     * Method to set the customization template and opt-in or opt-out flag for an OpenID Connect (OIDC) subject claim for a repository.
     * You must authenticate using an access token with the repo scope to use this endpoint.  -> <b>
     * this step is automatically made by this library. </b> <br>
     * GitHub Apps must have the {@code "actions:write"} permission to use this endpoint
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param useDefault: whether to use the default template or not. If {@code "true"}, the {@code "include_claim_keys"}
     *                    field is ignored
     * @param claimKeys:  array of unique strings. Each claim key can only contain alphanumeric characters and underscores
     *                    as array of {@link String} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/oidc#set-the-customization-template-for-an-oidc-subject-claim-for-a-repository">
     * Set the customization template for an OIDC subject claim for a repository</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/actions/oidc/customization/sub")
    public boolean setRepositoryOIDCSubjectClaim(String owner, String repo, boolean useDefault, String[] claimKeys) {
        Params payload = new Params();
        payload.addParam("use_default", useDefault);
        payload.addParam("include_claim_keys", claimKeys);
        try {
            sendPutRequest(REPOS_PATH + owner + "/" + repo + ACTIONS_OIDC_CUSTOMIZATION_SUB_PATH, payload);
            if (apiRequest.getResponseStatusCode() != 201) {
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
