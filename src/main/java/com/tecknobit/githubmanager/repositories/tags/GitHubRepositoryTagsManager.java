package com.tecknobit.githubmanager.repositories.tags;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import com.tecknobit.githubmanager.repositories.tags.records.TagProtection;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubRepositoryTagsManager} class is useful to manage all GitHub's repositories tags endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/tags">
 * Repository tags</a>
 * @see GitHubManager
 **/
public class GitHubRepositoryTagsManager extends GitHubManager {

    /**
     * {@code TAGS_PROTECTION_PATH} constant for {@code "/tags/protection"} path
     **/
    public static final String TAGS_PROTECTION_PATH = TAGS_PATH + "/protection";

    /**
     * Constructor to init a {@link GitHubRepositoryTagsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubRepositoryTagsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubRepositoryTagsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubRepositoryTagsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubRepositoryTagsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubRepositoryTagsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubRepositoryTagsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubRepositoryTagsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubRepositoryTagsManager} <br>
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
    public GitHubRepositoryTagsManager() {
        super();
    }

    /**
     * Method to get the list of the tag protection states for a repository <br>
     * This returns the tag protection states of a repository. <br>
     * This information is only available to repository administrators
     *
     * @param repository: the repository from fetch the list
     * @return tag protections list as {@link ArrayList} of {@link TagProtection} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/tags#list-tag-protection-states-for-a-repository">
     * List tag protection states for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/tags/protection")
    public ArrayList<TagProtection> getRepositoryTagProtectionStates(Repository repository) throws IOException {
        return getRepositoryTagProtectionStates(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the tag protection states for a repository <br>
     * This returns the tag protection states of a repository. <br>
     * This information is only available to repository administrators
     *
     * @param repository: the repository from fetch the list
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return tag protections list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/tags#list-tag-protection-states-for-a-repository">
     * List tag protection states for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/tags/protection")
    public <T> T getRepositoryTagProtectionStates(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryTagProtectionStates(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the tag protection states for a repository <br>
     * This returns the tag protection states of a repository. <br>
     * This information is only available to repository administrators
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return tag protections list as {@link ArrayList} of {@link TagProtection} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/tags#list-tag-protection-states-for-a-repository">
     * List tag protection states for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/tags/protection")
    public ArrayList<TagProtection> getRepositoryTagProtectionStates(String owner, String repo) throws IOException {
        return getRepositoryTagProtectionStates(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the tag protection states for a repository <br>
     * This returns the tag protection states of a repository. <br>
     * This information is only available to repository administrators
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return tag protections list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/tags#list-tag-protection-states-for-a-repository">
     * List tag protection states for a repository</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/tags/protection")
    public <T> T getRepositoryTagProtectionStates(String owner, String repo, ReturnFormat format) throws IOException {
        String tagProtectionStatesResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo
                + TAGS_PROTECTION_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONArray(tagProtectionStatesResponse);
            case LIBRARY_OBJECT:
                ArrayList<TagProtection> tagProtections = new ArrayList<>();
                JSONArray jTags = new JSONArray(tagProtections);
                for (int j = 0; j < jTags.length(); j++)
                    tagProtections.add(new TagProtection(jTags.getJSONObject(j)));
                return (T) tagProtections;
            default:
                return (T) tagProtectionStatesResponse;
        }
    }

    /**
     * Method to create a tag protection state for a repository.
     * This endpoint is only available to repository administrators
     *
     * @param repository: the repository where create the tag protection
     * @param pattern:    an optional glob pattern to match against when enforcing tag protection
     * @return tag protection as {@link TagProtection} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/tags#create-a-tag-protection-state-for-a-repository">
     * Create a tag protection state for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/tags/protection")
    public TagProtection createRepositoryTagProtectionState(Repository repository, String pattern) throws IOException {
        return createRepositoryTagProtectionState(repository.getOwner().getLogin(), repository.getName(), pattern,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a tag protection state for a repository.
     * This endpoint is only available to repository administrators
     *
     * @param repository: the repository where create the tag protection
     * @param pattern:    an optional glob pattern to match against when enforcing tag protection
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return tag protection as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/tags#create-a-tag-protection-state-for-a-repository">
     * Create a tag protection state for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/tags/protection")
    public <T> T createRepositoryTagProtectionState(Repository repository, String pattern,
                                                    ReturnFormat format) throws IOException {
        return createRepositoryTagProtectionState(repository.getOwner().getLogin(), repository.getName(), pattern, format);
    }

    /**
     * Method to create a tag protection state for a repository.
     * This endpoint is only available to repository administrators
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param pattern: an optional glob pattern to match against when enforcing tag protection
     * @return tag protection as {@link TagProtection} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/tags#create-a-tag-protection-state-for-a-repository">
     * Create a tag protection state for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/tags/protection")
    public TagProtection createRepositoryTagProtectionState(String owner, String repo, String pattern) throws IOException {
        return createRepositoryTagProtectionState(owner, repo, pattern, LIBRARY_OBJECT);
    }

    /**
     * Method to create a tag protection state for a repository.
     * This endpoint is only available to repository administrators
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param pattern: an optional glob pattern to match against when enforcing tag protection
     * @param format   :              return type formatter -> {@link ReturnFormat}
     * @return tag protection as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/tags#create-a-tag-protection-state-for-a-repository">
     * Create a tag protection state for a repository</a>
     **/
    @Returner
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/tags/protection")
    public <T> T createRepositoryTagProtectionState(String owner, String repo, String pattern,
                                                    ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("pattern", pattern);
        String tagProtectionStateResponse = sendPostRequest(REPOS_PATH + owner + "/" + repo
                + TAGS_PROTECTION_PATH, payload);
        switch (format) {
            case JSON:
                return (T) new JSONObject(tagProtectionStateResponse);
            case LIBRARY_OBJECT:
                return (T) new TagProtection(new JSONObject(tagProtectionStateResponse));
            default:
                return (T) tagProtectionStateResponse;
        }
    }

    /**
     * Method to delete a tag protection state for a repository. <br>
     * This endpoint is only available to repository administrators
     *
     * @param repository:    the repository where delete the tag protection
     * @param tagProtection: the tag protection to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/tags#delete-a-tag-protection-state-for-a-repository">
     * Delete a tag protection state for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/tags/protection/{tag_protection_id}")
    public boolean deleteRepositoryTagProtectionState(Repository repository, TagProtection tagProtection) {
        return deleteRepositoryTagProtectionState(repository.getOwner().getLogin(), repository.getName(),
                tagProtection.getId());
    }

    /**
     * Method to delete a tag protection state for a repository. <br>
     * This endpoint is only available to repository administrators
     *
     * @param owner:         the account owner of the repository. The name is not case-sensitive
     * @param repo:          the name of the repository. The name is not case-sensitive
     * @param tagProtection: the tag protection to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/tags#delete-a-tag-protection-state-for-a-repository">
     * Delete a tag protection state for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/tags/protection/{tag_protection_id}")
    public boolean deleteRepositoryTagProtectionState(String owner, String repo, TagProtection tagProtection) {
        return deleteRepositoryTagProtectionState(owner, repo, tagProtection.getId());
    }

    /**
     * Method to delete a tag protection state for a repository. <br>
     * This endpoint is only available to repository administrators
     *
     * @param repository:      the repository where delete the tag protection
     * @param tagProtectionId: the unique identifier of the tag protection
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/tags#delete-a-tag-protection-state-for-a-repository">
     * Delete a tag protection state for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/tags/protection/{tag_protection_id}")
    public boolean deleteRepositoryTagProtectionState(Repository repository, long tagProtectionId) {
        return deleteRepositoryTagProtectionState(repository.getOwner().getLogin(), repository.getName(),
                tagProtectionId);
    }

    /**
     * Method to delete a tag protection state for a repository. <br>
     * This endpoint is only available to repository administrators
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param tagProtectionId: the unique identifier of the tag protection
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/tags#delete-a-tag-protection-state-for-a-repository">
     * Delete a tag protection state for a repository</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/tags/protection/{tag_protection_id}")
    public boolean deleteRepositoryTagProtectionState(String owner, String repo, long tagProtectionId) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + TAGS_PROTECTION_PATH + "/" + tagProtectionId);
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
