package com.tecknobit.githubmanager.collaborators.collaborators;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.collaborators.collaborators.records.Collaborator;
import com.tecknobit.githubmanager.collaborators.collaborators.records.Collaborator.Affiliation;
import com.tecknobit.githubmanager.collaborators.collaborators.records.RepositoryPermissions;
import com.tecknobit.githubmanager.collaborators.records.Invitation;
import com.tecknobit.githubmanager.collaborators.records.Invitation.CollaboratorPermission;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.collaborators.records.Invitation.returnInvitation;

/**
 * The {@code GitHubCollaboratorsManager} class is useful to manage all GitHub's collaborators endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/collaborators">
 * Collaborators</a>
 * @see GitHubManager
 **/
public class GitHubCollaboratorsManager extends GitHubManager {

    /**
     * {@code COLLABORATORS_PATH} constant for {@code "/collaborators"} path
     **/
    public static final String COLLABORATORS_PATH = "/collaborators";

    /**
     * {@code PERMISSION_PATH} constant for {@code "/permission"} path
     **/
    public static final String PERMISSION_PATH = "/permission";

    /**
     * Constructor to init a {@link GitHubCollaboratorsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubCollaboratorsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubCollaboratorsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubCollaboratorsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubCollaboratorsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubCollaboratorsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubCollaboratorsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubCollaboratorsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubCollaboratorsManager} <br>
     * Any params required
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
    public GitHubCollaboratorsManager() {
        super();
    }

    /**
     * Method to get the collaborators list. <br>
     * For organization-owned repositories, the list of collaborators includes outside collaborators, organization members
     * that are direct collaborators, organization members with access through team memberships, organization members
     * with access through default organization permissions, and organization owners.  <br>
     * Organization members with write, maintain, or admin privileges on the organization-owned repository can use this endpoint. <br>
     * Team members will include the members of child teams. <br>
     * You must authenticate using an access token with the {@code "read:org"} and repo scopes with push access to use
     * this endpoint. <br>
     * GitHub Apps must have the members organization permission and metadata repository permission to use this endpoint <br>
     *
     * @param repository: the repository from fetch the list
     * @return collaborators list as {@link Collection} of {@link Collaborator} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/collaborators#list-repository-collaborators">
     * List repository collaborators</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/collaborators")
    public Collection<Collaborator> getRepositoryCollaborators(Repository repository) throws IOException {
        return getRepositoryCollaborators(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the collaborators list. <br>
     * For organization-owned repositories, the list of collaborators includes outside collaborators, organization members
     * that are direct collaborators, organization members with access through team memberships, organization members
     * with access through default organization permissions, and organization owners.  <br>
     * Organization members with write, maintain, or admin privileges on the organization-owned repository can use this endpoint. <br>
     * Team members will include the members of child teams. <br>
     * You must authenticate using an access token with the {@code "read:org"} and repo scopes with push access to use
     * this endpoint. <br>
     * GitHub Apps must have the members organization permission and metadata repository permission to use this endpoint <br>
     *
     * @param repository: the repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return collaborators list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/collaborators#list-repository-collaborators">
     * List repository collaborators</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/collaborators")
    public <T> T getRepositoryCollaborators(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryCollaborators(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the collaborators list. <br>
     * For organization-owned repositories, the list of collaborators includes outside collaborators, organization members
     * that are direct collaborators, organization members with access through team memberships, organization members
     * with access through default organization permissions, and organization owners.  <br>
     * Organization members with write, maintain, or admin privileges on the organization-owned repository can use this endpoint. <br>
     * Team members will include the members of child teams. <br>
     * You must authenticate using an access token with the {@code "read:org"} and repo scopes with push access to use
     * this endpoint. <br>
     * GitHub Apps must have the members organization permission and metadata repository permission to use this endpoint <br>
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return collaborators list as {@link Collection} of {@link Collaborator} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/collaborators#list-repository-collaborators">
     * List repository collaborators</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/collaborators")
    public Collection<Collaborator> getRepositoryCollaborators(String owner, String repo) throws IOException {
        return getRepositoryCollaborators(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the collaborators list. <br>
     * For organization-owned repositories, the list of collaborators includes outside collaborators, organization members
     * that are direct collaborators, organization members with access through team memberships, organization members
     * with access through default organization permissions, and organization owners.  <br>
     * Organization members with write, maintain, or admin privileges on the organization-owned repository can use this endpoint. <br>
     * Team members will include the members of child teams. <br>
     * You must authenticate using an access token with the {@code "read:org"} and repo scopes with push access to use
     * this endpoint. <br>
     * GitHub Apps must have the members organization permission and metadata repository permission to use this endpoint <br>
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return collaborators list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/collaborators#list-repository-collaborators">
     * List repository collaborators</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/collaborators")
    public <T> T getRepositoryCollaborators(String owner, String repo, ReturnFormat format) throws IOException {
        return returnCollaboratorsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + COLLABORATORS_PATH),
                format);
    }

    /**
     * Method to get the collaborators list. <br>
     * For organization-owned repositories, the list of collaborators includes outside collaborators, organization members
     * that are direct collaborators, organization members with access through team memberships, organization members
     * with access through default organization permissions, and organization owners.  <br>
     * Organization members with write, maintain, or admin privileges on the organization-owned repository can use this endpoint. <br>
     * Team members will include the members of child teams. <br>
     * You must authenticate using an access token with the {@code "read:org"} and repo scopes with push access to use
     * this endpoint. <br>
     * GitHub Apps must have the members organization permission and metadata repository permission to use this endpoint <br>
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "affiliation"} -> filter collaborators returned by their affiliation. outside
     *                            means all outside collaborators of an organization-owned repository.
     *                            direct means all collaborators with permissions to an organization-owned repository,
     *                            regardless of organization membership status. all means all collaborators the
     *                            authenticated user can see, constants available {@link Affiliation} - [string, default all]
     *                        </li>
     *                        <li>
     *                            {@code "permission"} -> filter collaborators by the permissions they have on the repository
     *                            . If not specified, all collaborators will be returned, constants available
     *                            {@link CollaboratorPermission} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return collaborators list as {@link Collection} of {@link Collaborator} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/collaborators#list-repository-collaborators">
     * List repository collaborators</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/collaborators")
    public Collection<Collaborator> getRepositoryCollaborators(Repository repository, Params queryParams) throws IOException {
        return getRepositoryCollaborators(repository.getOwner().getLogin(), repository.getName(), queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the collaborators list. <br>
     * For organization-owned repositories, the list of collaborators includes outside collaborators, organization members
     * that are direct collaborators, organization members with access through team memberships, organization members
     * with access through default organization permissions, and organization owners.  <br>
     * Organization members with write, maintain, or admin privileges on the organization-owned repository can use this endpoint. <br>
     * Team members will include the members of child teams. <br>
     * You must authenticate using an access token with the {@code "read:org"} and repo scopes with push access to use
     * this endpoint. <br>
     * GitHub Apps must have the members organization permission and metadata repository permission to use this endpoint <br>
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "affiliation"} -> filter collaborators returned by their affiliation. outside
     *                            means all outside collaborators of an organization-owned repository.
     *                            direct means all collaborators with permissions to an organization-owned repository,
     *                            regardless of organization membership status. all means all collaborators the
     *                            authenticated user can see, constants available {@link Affiliation} - [string, default all]
     *                        </li>
     *                        <li>
     *                            {@code "permission"} -> filter collaborators by the permissions they have on the repository
     *                            . If not specified, all collaborators will be returned, constants available
     *                            {@link CollaboratorPermission} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return collaborators list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/collaborators#list-repository-collaborators">
     * List repository collaborators</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/collaborators")
    public <T> T getRepositoryCollaborators(Repository repository, Params queryParams,
                                            ReturnFormat format) throws IOException {
        return getRepositoryCollaborators(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the collaborators list. <br>
     * For organization-owned repositories, the list of collaborators includes outside collaborators, organization members
     * that are direct collaborators, organization members with access through team memberships, organization members
     * with access through default organization permissions, and organization owners.  <br>
     * Organization members with write, maintain, or admin privileges on the organization-owned repository can use this endpoint. <br>
     * Team members will include the members of child teams. <br>
     * You must authenticate using an access token with the {@code "read:org"} and repo scopes with push access to use
     * this endpoint. <br>
     * GitHub Apps must have the members organization permission and metadata repository permission to use this endpoint <br>
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "affiliation"} -> filter collaborators returned by their affiliation. outside
     *                            means all outside collaborators of an organization-owned repository.
     *                            direct means all collaborators with permissions to an organization-owned repository,
     *                            regardless of organization membership status. all means all collaborators the
     *                            authenticated user can see, constants available {@link Affiliation} - [string, default all]
     *                        </li>
     *                        <li>
     *                            {@code "permission"} -> filter collaborators by the permissions they have on the repository
     *                            . If not specified, all collaborators will be returned, constants available
     *                            {@link CollaboratorPermission} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return collaborators list as {@link Collection} of {@link Collaborator} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/collaborators#list-repository-collaborators">
     * List repository collaborators</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/collaborators")
    public Collection<Collaborator> getRepositoryCollaborators(String owner, String repo,
                                                               Params queryParams) throws IOException {
        return getRepositoryCollaborators(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the collaborators list. <br>
     * For organization-owned repositories, the list of collaborators includes outside collaborators, organization members
     * that are direct collaborators, organization members with access through team memberships, organization members
     * with access through default organization permissions, and organization owners.  <br>
     * Organization members with write, maintain, or admin privileges on the organization-owned repository can use this endpoint. <br>
     * Team members will include the members of child teams. <br>
     * You must authenticate using an access token with the {@code "read:org"} and repo scopes with push access to use
     * this endpoint. <br>
     * GitHub Apps must have the members organization permission and metadata repository permission to use this endpoint <br>
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "affiliation"} -> filter collaborators returned by their affiliation. outside
     *                            means all outside collaborators of an organization-owned repository.
     *                            direct means all collaborators with permissions to an organization-owned repository,
     *                            regardless of organization membership status. all means all collaborators the
     *                            authenticated user can see, constants available {@link Affiliation} - [string, default all]
     *                        </li>
     *                        <li>
     *                            {@code "permission"} -> filter collaborators by the permissions they have on the repository
     *                            . If not specified, all collaborators will be returned, constants available
     *                            {@link CollaboratorPermission} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return collaborators list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/collaborators#list-repository-collaborators">
     * List repository collaborators</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/collaborators")
    public <T> T getRepositoryCollaborators(String owner, String repo, Params queryParams,
                                            ReturnFormat format) throws IOException {
        return returnCollaboratorsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + COLLABORATORS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a collaborators suites list
     *
     * @param collaboratorsListResponse: obtained from GitHub's response
     * @param format:                    return type formatter -> {@link ReturnFormat}
     * @return collaborators list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnCollaboratorsList(String collaboratorsListResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(collaboratorsListResponse);
            case LIBRARY_OBJECT:
                ArrayList<Collaborator> collaborators = new ArrayList<>();
                JSONArray jCollaborators = new JSONArray(collaboratorsListResponse);
                for (int j = 0; j < jCollaborators.length(); j++)
                    collaborators.add(new Collaborator(jCollaborators.getJSONObject(j)));
                return (T) collaborators;
            default:
                return (T) collaboratorsListResponse;
        }
    }

    /**
     * Method to check if a user is a collaborator of the repository <br>
     * For organization-owned repositories, the list of collaborators includes outside collaborators, organization members
     * that are direct collaborators, organization members with access through team memberships, organization members
     * with access through default organization permissions, and organization owners.  <br>
     * Organization members with write, maintain, or admin privileges on the organization-owned repository can use this endpoint. <br>
     * Team members will include the members of child teams. <br>
     * You must authenticate using an access token with the {@code "read:org"} and repo scopes with push access to use
     * this endpoint. <br>
     * GitHub Apps must have the members organization permission and metadata repository permission to use this endpoint <br>
     *
     * @param repository: the repository where check the collaborator
     * @param username:   the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/collaboratorsa#check-if-a-user-is-a-repository-collaborator">
     * Check if a user is a repository collaborator</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/collaborators/{username}")
    public boolean checkRepositoryCollaborator(Repository repository, String username) {
        return checkRepositoryCollaborator(repository.getOwner().getLogin(), repository.getName(), username);
    }

    /**
     * Method to check if a user is a collaborator of the repository <br>
     * For organization-owned repositories, the list of collaborators includes outside collaborators, organization members
     * that are direct collaborators, organization members with access through team memberships, organization members
     * with access through default organization permissions, and organization owners.  <br>
     * Organization members with write, maintain, or admin privileges on the organization-owned repository can use this endpoint. <br>
     * Team members will include the members of child teams. <br>
     * You must authenticate using an access token with the {@code "read:org"} and repo scopes with push access to use
     * this endpoint. <br>
     * GitHub Apps must have the members organization permission and metadata repository permission to use this endpoint <br>
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/collaboratorsa#check-if-a-user-is-a-repository-collaborator">
     * Check if a user is a repository collaborator</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/collaborators/{username}")
    public boolean checkRepositoryCollaborator(String owner, String repo, String username) {
        try {
            sendGetRequest(REPOS_PATH + owner + "/" + repo + COLLABORATORS_PATH + "/" + username);
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
     * Method to add a repository collaborator
     *
     * @param repository: the repository where add the collaborator
     * @param username:   the handle for the GitHub user account
     * @return invitation as {@link Invitation} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/collaborators#add-a-repository-collaborator">
     * Add a repository collaborator</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/collaborators/{username}")
    public Invitation addRepositoryCollaborator(Repository repository, String username) throws IOException {
        return addRepositoryCollaborator(repository.getOwner().getLogin(), repository.getName(), username, LIBRARY_OBJECT);
    }

    /**
     * Method to add a repository collaborator
     *
     * @param repository: the repository where add the collaborator
     * @param username:   the handle for the GitHub user account
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return invitation as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/collaborators#add-a-repository-collaborator">
     * Add a repository collaborator</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/collaborators/{username}")
    public <T> T addRepositoryCollaborator(Repository repository, String username, ReturnFormat format) throws IOException {
        return addRepositoryCollaborator(repository.getOwner().getLogin(), repository.getName(), username, format);
    }

    /**
     * Method to add a repository collaborator
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param username: the handle for the GitHub user account
     * @return invitation as {@link Invitation} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/collaborators#add-a-repository-collaborator">
     * Add a repository collaborator</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/collaborators/{username}")
    public Invitation addRepositoryCollaborator(String owner, String repo, String username) throws IOException {
        return addRepositoryCollaborator(owner, repo, username, LIBRARY_OBJECT);
    }

    /**
     * Method to add a repository collaborator
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return invitation as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/collaborators#add-a-repository-collaborator">
     * Add a repository collaborator</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/collaborators/{username}")
    public <T> T addRepositoryCollaborator(String owner, String repo, String username,
                                           ReturnFormat format) throws IOException {
        return returnInvitation(sendPutRequest(REPOS_PATH + owner + "/" + repo + COLLABORATORS_PATH + "/"
                + username, null), format);
    }

    /**
     * Method to add a repository collaborator
     *
     * @param repository: the repository where add the collaborator
     * @param username:   the handle for the GitHub user account
     * @param permission: the permission to grant the collaborator
     * @return invitation as {@link Invitation} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/collaborators#add-a-repository-collaborator">
     * Add a repository collaborator</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/collaborators/{username}")
    public Invitation addRepositoryCollaborator(Repository repository, String username,
                                                CollaboratorPermission permission) throws IOException {
        return addRepositoryCollaborator(repository.getOwner().getLogin(), repository.getName(), username, permission,
                LIBRARY_OBJECT);
    }

    /**
     * Method to add a repository collaborator
     *
     * @param repository: the repository where add the collaborator
     * @param username:   the handle for the GitHub user account
     * @param permission: the permission to grant the collaborator
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return invitation as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/collaborators#add-a-repository-collaborator">
     * Add a repository collaborator</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/collaborators/{username}")
    public <T> T addRepositoryCollaborator(Repository repository, String username, CollaboratorPermission permission,
                                           ReturnFormat format) throws IOException {
        return addRepositoryCollaborator(repository.getOwner().getLogin(), repository.getName(), username, permission,
                format);
    }

    /**
     * Method to add a repository collaborator
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param username:   the handle for the GitHub user account
     * @param permission: the permission to grant the collaborator
     * @return invitation as {@link Invitation} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/collaborators#add-a-repository-collaborator">
     * Add a repository collaborator</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/collaborators/{username}")
    public Invitation addRepositoryCollaborator(String owner, String repo, String username,
                                                CollaboratorPermission permission) throws IOException {
        return addRepositoryCollaborator(owner, repo, username, permission, LIBRARY_OBJECT);
    }

    /**
     * Method to add a repository collaborator
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param username:   the handle for the GitHub user account
     * @param permission: the permission to grant the collaborator
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return invitation as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/collaborators#add-a-repository-collaborator">
     * Add a repository collaborator</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/collaborators/{username}")
    public <T> T addRepositoryCollaborator(String owner, String repo, String username, CollaboratorPermission permission,
                                           ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("permission", permission);
        return returnInvitation(sendPutRequest(REPOS_PATH + owner + "/" + repo + COLLABORATORS_PATH + "/"
                + username, payload), format);
    }

    /**
     * Method to remove a repository collaborator
     *
     * @param repository: the repository from remove the collaborator
     * @param username:   the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/collaborators#remove-a-repository-collaborator">
     * Remove a repository collaborator</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/collaborators/{username}")
    public boolean removeRepositoryCollaborator(Repository repository, String username) {
        return removeRepositoryCollaborator(repository.getOwner().getLogin(), repository.getName(), username);
    }

    /**
     * Method to remove a repository collaborator
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param username: the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/collaborators#remove-a-repository-collaborator">
     * Remove a repository collaborator</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/collaborators/{username}")
    public boolean removeRepositoryCollaborator(String owner, String repo, String username) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + COLLABORATORS_PATH + "/" + username);
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
     * Method to check the repository permission of a collaborator
     *
     * @param repository: the repository from fetch the permissions
     * @param username:   the handle for the GitHub user account
     * @return repository permissions as {@link RepositoryPermissions} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/collaborators#get-repository-permissions-for-a-user">
     * Get repository permissions for a user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/collaborators/{username}/permission")
    public RepositoryPermissions getRepositoryPermissions(Repository repository, String username) throws IOException {
        return getRepositoryPermissions(repository.getOwner().getLogin(), repository.getName(), username, LIBRARY_OBJECT);
    }

    /**
     * Method to check the repository permission of a collaborator
     *
     * @param repository: the repository from fetch the permissions
     * @param username:   the handle for the GitHub user account
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return repository permissions as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/collaborators#get-repository-permissions-for-a-user">
     * Get repository permissions for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/collaborators/{username}/permission")
    public <T> T getRepositoryPermissions(Repository repository, String username, ReturnFormat format) throws IOException {
        return getRepositoryPermissions(repository.getOwner().getLogin(), repository.getName(), username, format);
    }

    /**
     * Method to check the repository permission of a collaborator
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param username: the handle for the GitHub user account
     * @return repository permissions as {@link RepositoryPermissions} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/collaborators#get-repository-permissions-for-a-user">
     * Get repository permissions for a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/collaborators/{username}/permission")
    public RepositoryPermissions getRepositoryPermissions(String owner, String repo, String username) throws IOException {
        return getRepositoryPermissions(owner, repo, username, LIBRARY_OBJECT);
    }

    /**
     * Method to check the repository permission of a collaborator
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param username: the handle for the GitHub user account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return repository permissions as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/collaborators#get-repository-permissions-for-a-user">
     * Get repository permissions for a user</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/collaborators/{username}/permission")
    public <T> T getRepositoryPermissions(String owner, String repo, String username,
                                          ReturnFormat format) throws IOException {
        String repositoryPermissionsResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo + COLLABORATORS_PATH
                + "/" + username + PERMISSION_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONObject(repositoryPermissionsResponse);
            case LIBRARY_OBJECT:
                return (T) new RepositoryPermissions(new JSONObject(repositoryPermissionsResponse));
            default:
                return (T) repositoryPermissionsResponse;
        }
    }

}
