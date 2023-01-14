package com.tecknobit.githubmanager.collaborators.invitations;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.collaborators.records.Invitation;
import com.tecknobit.githubmanager.collaborators.records.Invitation.CollaboratorPermission;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.collaborators.records.Invitation.returnInvitation;

/**
 * The {@code GitHubInvitationsManager} class is useful to manage all GitHub's invitations endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/invitations">
 * Repository invitations</a>
 * @see GitHubManager
 **/
public class GitHubInvitationsManager extends GitHubManager {

    /**
     * {@code INVITATIONS_PATH} constant for {@code "/invitations"} path
     **/
    public static final String INVITATIONS_PATH = "/invitations";

    /**
     * {@code USER_REPOSITORY_INVITATIONS_PATH} constant for {@code "user/repository_invitations"} path
     **/
    public static final String USER_REPOSITORY_INVITATIONS_PATH = USER_PATH + "/repository_invitations";

    /**
     * Constructor to init a {@link GitHubInvitationsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubInvitationsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubInvitationsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubInvitationsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubInvitationsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubInvitationsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubInvitationsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubInvitationsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubInvitationsManager} <br>
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
    public GitHubInvitationsManager() {
        super();
    }

    /**
     * When authenticating as a user with admin rights to a repository, this method will list all currently open repository invitations
     *
     * @param repository: the repository from fetch the list
     * @return invitations list as {@link Collection} of {@link Invitation} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/invitations#list-repository-invitations">
     * List repository invitations</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/invitations")
    public Collection<Invitation> getRepositoryInvitations(Repository repository) throws IOException {
        return getRepositoryInvitations(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * When authenticating as a user with admin rights to a repository, this method will list all currently open repository invitations
     *
     * @param repository: the repository from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return invitations list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/invitations#list-repository-invitations">
     * List repository invitations</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/invitations")
    public <T> T getRepositoryInvitations(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryInvitations(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * When authenticating as a user with admin rights to a repository, this method will list all currently open repository invitations
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return invitations list as {@link Collection} of {@link Invitation} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/invitations#list-repository-invitations">
     * List repository invitations</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/invitations")
    public Collection<Invitation> getRepositoryInvitations(String owner, String repo) throws IOException {
        return getRepositoryInvitations(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * When authenticating as a user with admin rights to a repository, this method will list all currently open repository invitations
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return invitations list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/invitations#list-repository-invitations">
     * List repository invitations</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/invitations")
    public <T> T getRepositoryInvitations(String owner, String repo, ReturnFormat format) throws IOException {
        return returnInvitationsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + INVITATIONS_PATH), format);
    }

    /**
     * When authenticating as a user with admin rights to a repository, this method will list all currently open repository invitations
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
     * @return invitations list as {@link Collection} of {@link Invitation} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/invitations#list-repository-invitations">
     * List repository invitations</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/invitations")
    public Collection<Invitation> getRepositoryInvitations(Repository repository, Params queryParams) throws IOException {
        return getRepositoryInvitations(repository.getOwner().getLogin(), repository.getName(), queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * When authenticating as a user with admin rights to a repository, this method will list all currently open repository invitations
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
     * @return invitations list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/invitations#list-repository-invitations">
     * List repository invitations</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/invitations")
    public <T> T getRepositoryInvitations(Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return getRepositoryInvitations(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * When authenticating as a user with admin rights to a repository, this method will list all currently open repository invitations
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
     * @return invitations list as {@link Collection} of {@link Invitation} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/invitations#list-repository-invitations">
     * List repository invitations</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/invitations")
    public Collection<Invitation> getRepositoryInvitations(String owner, String repo, Params queryParams) throws IOException {
        return getRepositoryInvitations(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * When authenticating as a user with admin rights to a repository, this method will list all currently open repository invitations
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
     * @return invitations list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/invitations#list-repository-invitations">
     * List repository invitations</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/invitations")
    public <T> T getRepositoryInvitations(String owner, String repo, Params queryParams,
                                          ReturnFormat format) throws IOException {
        return returnInvitationsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + INVITATIONS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to update an invitation
     *
     * @param repository:  the repository of the invitation
     * @param invitation:  the invitation to update
     * @param permissions: the permissions that the associated user will have on the repository
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/invitations#update-a-repository-invitation">
     * Update a repository invitation</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/invitations/{invitation_id}")
    public Invitation updateRepositoryInvitation(Repository repository, Invitation invitation,
                                                 CollaboratorPermission permissions) throws IOException {
        return updateRepositoryInvitation(repository.getOwner().getLogin(), repository.getName(), invitation.getId(),
                permissions, LIBRARY_OBJECT);
    }

    /**
     * Method to update an invitation
     *
     * @param repository:  the repository of the invitation
     * @param invitation:  the invitation to update
     * @param permissions: the permissions that the associated user will have on the repository
     * @param format:      return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/invitations#update-a-repository-invitation">
     * Update a repository invitation</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/invitations/{invitation_id}")
    public <T> T updateRepositoryInvitation(Repository repository, Invitation invitation,
                                            CollaboratorPermission permissions, ReturnFormat format) throws IOException {
        return updateRepositoryInvitation(repository.getOwner().getLogin(), repository.getName(), invitation.getId(),
                permissions, format);
    }

    /**
     * Method to update an invitation
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param invitation:  the invitation to update
     * @param permissions: the permissions that the associated user will have on the repository
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/invitations#update-a-repository-invitation">
     * Update a repository invitation</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/invitations/{invitation_id}")
    public Invitation updateRepositoryInvitation(String owner, String repo, Invitation invitation,
                                                 CollaboratorPermission permissions) throws IOException {
        return updateRepositoryInvitation(owner, repo, invitation.getId(), permissions, LIBRARY_OBJECT);
    }

    /**
     * Method to update an invitation
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param invitation:  the invitation to update
     * @param permissions: the permissions that the associated user will have on the repository
     * @param format:      return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/invitations#update-a-repository-invitation">
     * Update a repository invitation</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/invitations/{invitation_id}")
    public <T> T updateRepositoryInvitation(String owner, String repo, Invitation invitation,
                                            CollaboratorPermission permissions, ReturnFormat format) throws IOException {
        return updateRepositoryInvitation(owner, repo, invitation.getId(), permissions, format);
    }

    /**
     * Method to update an invitation
     *
     * @param repository:   the repository of the invitation
     * @param invitationId: the unique identifier of the invitation
     * @param permissions:  the permissions that the associated user will have on the repository
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/invitations#update-a-repository-invitation">
     * Update a repository invitation</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/invitations/{invitation_id}")
    public Invitation updateRepositoryInvitation(Repository repository, long invitationId,
                                                 CollaboratorPermission permissions) throws IOException {
        return updateRepositoryInvitation(repository.getOwner().getLogin(), repository.getName(), invitationId, permissions,
                LIBRARY_OBJECT);
    }

    /**
     * Method to update an invitation
     *
     * @param repository:   the repository of the invitation
     * @param invitationId: the unique identifier of the invitation
     * @param permissions:  the permissions that the associated user will have on the repository
     * @param format:       return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/invitations#update-a-repository-invitation">
     * Update a repository invitation</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/invitations/{invitation_id}")
    public <T> T updateRepositoryInvitation(Repository repository, long invitationId, CollaboratorPermission permissions,
                                            ReturnFormat format) throws IOException {
        return updateRepositoryInvitation(repository.getOwner().getLogin(), repository.getName(), invitationId, permissions,
                format);
    }

    /**
     * Method to update an invitation
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param invitationId: the unique identifier of the invitation
     * @param permissions:  the permissions that the associated user will have on the repository
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/invitations#update-a-repository-invitation">
     * Update a repository invitation</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/invitations/{invitation_id}")
    public Invitation updateRepositoryInvitation(String owner, String repo, long invitationId,
                                                 CollaboratorPermission permissions) throws IOException {
        return updateRepositoryInvitation(owner, repo, invitationId, permissions, LIBRARY_OBJECT);
    }

    /**
     * Method to update an invitation
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param invitationId: the unique identifier of the invitation
     * @param permissions:  the permissions that the associated user will have on the repository
     * @param format:       return type formatter -> {@link ReturnFormat}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/invitations#update-a-repository-invitation">
     * Update a repository invitation</a>
     **/
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/invitations/{invitation_id}")
    public <T> T updateRepositoryInvitation(String owner, String repo, long invitationId, CollaboratorPermission permissions,
                                            ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("permissions", permissions);
        return returnInvitation(sendPatchRequest(REPOS_PATH + owner + "/" + repo + INVITATIONS_PATH
                + "/" + invitationId, payload), format);
    }

    /**
     * Method to delete a repository invitation
     *
     * @param repository: the repository from delete the invitation
     * @param invitation: the invitation to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/invitations#delete-a-repository-invitation">
     * Delete a repository invitation</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/invitations/{invitation_id}")
    public boolean deleteRepositoryInvitation(Repository repository, Invitation invitation) {
        return deleteRepositoryInvitation(repository.getOwner().getLogin(), repository.getName(), invitation.getId());
    }

    /**
     * Method to delete a repository invitation
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param invitation: the invitation to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/invitations#delete-a-repository-invitation">
     * Delete a repository invitation</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/invitations/{invitation_id}")
    public boolean deleteRepositoryInvitation(String owner, String repo, Invitation invitation) {
        return deleteRepositoryInvitation(owner, repo, invitation.getId());
    }

    /**
     * Method to delete a repository invitation
     *
     * @param repository:   the repository from delete the invitation
     * @param invitationId: the unique identifier of the invitation
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/invitations#delete-a-repository-invitation">
     * Delete a repository invitation</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/invitations/{invitation_id}")
    public boolean deleteRepositoryInvitation(Repository repository, long invitationId) {
        return deleteRepositoryInvitation(repository.getOwner().getLogin(), repository.getName(), invitationId);
    }

    /**
     * Method to delete a repository invitation
     *
     * @param owner:        the account owner of the repository. The name is not case-sensitive
     * @param repo:         the name of the repository. The name is not case-sensitive
     * @param invitationId: the unique identifier of the invitation
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/invitations#delete-a-repository-invitation">
     * Delete a repository invitation</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/invitations/{invitation_id}")
    public boolean deleteRepositoryInvitation(String owner, String repo, long invitationId) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + INVITATIONS_PATH + "/" + invitationId);
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
     * When authenticating as a user, this method will list all currently open repository invitations for that user <br>
     * Any params required
     *
     * @return invitations list as {@link Collection} of {@link Invitation} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/invitations#list-repository-invitations-for-the-authenticated-user">
     * List repository invitations for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/repository_invitations")
    public Collection<Invitation> getUserRepositoryInvitations() throws IOException {
        return getUserRepositoryInvitations(LIBRARY_OBJECT);
    }

    /**
     * When authenticating as a user, this method will list all currently open repository invitations for that user
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return invitations list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/invitations#list-repository-invitations-for-the-authenticated-user">
     * List repository invitations for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/repository_invitations")
    public <T> T getUserRepositoryInvitations(ReturnFormat format) throws IOException {
        return returnInvitationsList(sendGetRequest(USER_REPOSITORY_INVITATIONS_PATH), format);
    }

    /**
     * When authenticating as a user, this method will list all currently open repository invitations for that user
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return invitations list as {@link Collection} of {@link Invitation} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/invitations#list-repository-invitations-for-the-authenticated-user">
     * List repository invitations for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/repository_invitations")
    public Collection<Invitation> getUserRepositoryInvitations(Params queryParams) throws IOException {
        return getUserRepositoryInvitations(queryParams, LIBRARY_OBJECT);
    }

    /**
     * When authenticating as a user, this method will list all currently open repository invitations for that user
     *
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
     * @return invitations list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/invitations#list-repository-invitations-for-the-authenticated-user">
     * List repository invitations for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/repository_invitations")
    public <T> T getUserRepositoryInvitations(Params queryParams, ReturnFormat format) throws IOException {
        return returnInvitationsList(sendGetRequest(USER_REPOSITORY_INVITATIONS_PATH + queryParams.createQueryString()),
                format);
    }

    /**
     * Method to create an invitations list
     *
     * @param invitationsListResponse: obtained from GitHub's response
     * @param format:                  return type formatter -> {@link ReturnFormat}
     * @return invitations list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnInvitationsList(String invitationsListResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(invitationsListResponse);
            case LIBRARY_OBJECT:
                ArrayList<Invitation> invitations = new ArrayList<>();
                JSONArray jInvitations = new JSONArray(invitationsListResponse);
                for (int j = 0; j < jInvitations.length(); j++)
                    invitations.add(new Invitation(jInvitations.getJSONObject(j)));
                return (T) invitations;
            default:
                return (T) invitationsListResponse;
        }
    }

    /**
     * Method to accept a repository invitation
     *
     * @param invitation: the invitation to accept
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/invitations#accept-a-repository-invitation">
     * Accept a repository invitation</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/user/repository_invitations/{invitation_id}")
    public boolean acceptRepositoryInvitation(Invitation invitation) {
        return acceptRepositoryInvitation(invitation.getId());
    }

    /**
     * Method to accept a repository invitation
     *
     * @param invitationId: the unique identifier of the invitation
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/invitations#accept-a-repository-invitation">
     * Accept a repository invitation</a>
     **/
    @RequestPath(method = PATCH, path = "/user/repository_invitations/{invitation_id}")
    public boolean acceptRepositoryInvitation(long invitationId) {
        try {
            sendPatchRequest(USER_REPOSITORY_INVITATIONS_PATH + "/" + invitationId, null);
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
     * Method to decline a repository invitation
     *
     * @param invitation: the invitation to decline
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/invitations#decline-a-repository-invitation">
     * Decline a repository invitation</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/user/repository_invitations/{invitation_id}")
    public boolean declineRepositoryInvitation(Invitation invitation) {
        return declineRepositoryInvitation(invitation.getId());
    }

    /**
     * Method to decline a repository invitation
     *
     * @param invitationId: the unique identifier of the invitation
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/invitations#decline-a-repository-invitation">
     * Decline a repository invitation</a>
     **/
    @RequestPath(method = DELETE, path = "/user/repository_invitations/{invitation_id}")
    public boolean declineRepositoryInvitation(long invitationId) {
        try {
            sendDeleteRequest(USER_REPOSITORY_INVITATIONS_PATH + "/" + invitationId);
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
