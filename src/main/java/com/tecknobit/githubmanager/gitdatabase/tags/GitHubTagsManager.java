package com.tecknobit.githubmanager.gitdatabase.tags;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.gitdatabase.tags.records.Tag;
import com.tecknobit.githubmanager.records.generic.CommitData.CommitProfile;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import org.json.JSONObject;

import java.io.IOException;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.POST;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.gitdatabase.blobs.GitHubBlobsManager.GIT_PATH;
import static com.tecknobit.githubmanager.gitdatabase.references.records.GitReference.RefObject.ObjectType;

/**
 * The {@code GitHubRepositoryTagsManager} class is useful to manage all GitHub's tags endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/tags">
 * Git tags</a>
 * @see GitHubManager
 **/
public class GitHubTagsManager extends GitHubManager {

    /**
     * {@code GIT_TAGS_PATH} constant for {@code "git/tags"} path
     **/
    public static final String GIT_TAGS_PATH = GIT_PATH + "tags";

    /**
     * Constructor to init a {@link GitHubTagsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubTagsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubTagsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubTagsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubTagsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubTagsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubTagsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubTagsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubTagsManager} <br>
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
    public GitHubTagsManager() {
        super();
    }

    /**
     * Method to create a tag object
     *
     * @param repository: the repository where create the tag
     * @param tag:        the tag's name. This is typically a version (e.g., "v0.0.1")
     * @param message:    the tag message
     * @param object:     the SHA of the git object this is tagging
     * @param type:       the type of the object we're tagging. Normally this is a commit, but it can also be a tree or a blob
     * @return tag as {@link Tag} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/tags#create-a-tag-object">
     * Create a tag object</a>
     * @implNote creating a tag object does not create the reference that makes a tag in Git. If you want to create
     * an annotated tag in Git, you have to do this call to create the tag object, and then create the refs/tags/[tag] reference.
     * If you want to create a lightweight tag, you only have to create the tag reference - this call would be unnecessary
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/tags")
    public Tag createTag(Repository repository, String tag, String message, String object, ObjectType type) throws IOException {
        return createTag(repository.getOwner().getLogin(), repository.getName(), tag, message, object, type,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a tag object
     *
     * @param repository: the repository where create the tag
     * @param tag:        the tag's name. This is typically a version (e.g., "v0.0.1")
     * @param message:    the tag message
     * @param object:     the SHA of the git object this is tagging
     * @param type:       the type of the object we're tagging. Normally this is a commit, but it can also be a tree or a blob
     * @param format      :               return type formatter -> {@link ReturnFormat}
     * @return tag {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/tags#create-a-tag-object">
     * Create a tag object</a>
     * @implNote creating a tag object does not create the reference that makes a tag in Git. If you want to create
     * an annotated tag in Git, you have to do this call to create the tag object, and then create the refs/tags/[tag] reference.
     * If you want to create a lightweight tag, you only have to create the tag reference - this call would be unnecessary
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/tags")
    public <T> T createTag(Repository repository, String tag, String message, String object, ObjectType type,
                           ReturnFormat format) throws IOException {
        return createTag(repository.getOwner().getLogin(), repository.getName(), tag, message, object, type, format);
    }

    /**
     * Method to create a tag object
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param tag:     the tag's name. This is typically a version (e.g., "v0.0.1")
     * @param message: the tag message
     * @param object:  the SHA of the git object this is tagging
     * @param type:    the type of the object we're tagging. Normally this is a commit, but it can also be a tree or a blob
     * @return tag as {@link Tag} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/tags#create-a-tag-object">
     * Create a tag object</a>
     * @implNote creating a tag object does not create the reference that makes a tag in Git. If you want to create
     * an annotated tag in Git, you have to do this call to create the tag object, and then create the refs/tags/[tag] reference.
     * If you want to create a lightweight tag, you only have to create the tag reference - this call would be unnecessary
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/tags")
    public Tag createTag(String owner, String repo, String tag, String message, String object,
                         ObjectType type) throws IOException {
        return createTag(owner, repo, tag, message, object, type, LIBRARY_OBJECT);
    }

    /**
     * Method to create a tag object
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param tag:     the tag's name. This is typically a version (e.g., "v0.0.1")
     * @param message: the tag message
     * @param object:  the SHA of the git object this is tagging
     * @param type:    the type of the object we're tagging. Normally this is a commit, but it can also be a tree or a blob
     * @param format   :               return type formatter -> {@link ReturnFormat}
     * @return tag {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/tags#create-a-tag-object">
     * Create a tag object</a>
     * @implNote creating a tag object does not create the reference that makes a tag in Git. If you want to create
     * an annotated tag in Git, you have to do this call to create the tag object, and then create the refs/tags/[tag] reference.
     * If you want to create a lightweight tag, you only have to create the tag reference - this call would be unnecessary
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/tags")
    public <T> T createTag(String owner, String repo, String tag, String message, String object, ObjectType type,
                           ReturnFormat format) throws IOException {
        return createTag(owner, repo, tag, message, object, type, null, format);
    }

    /**
     * Method to create a tag object
     *
     * @param repository: the repository where create the tag
     * @param tag:        the tag's name. This is typically a version (e.g., "v0.0.1")
     * @param message:    the tag message
     * @param object:     the SHA of the git object this is tagging
     * @param type:       the type of the object we're tagging. Normally this is a commit, but it can also be a tree or a blob
     * @param tagger:     an object with information about the individual creating the tag
     * @return tag as {@link Tag} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/tags#create-a-tag-object">
     * Create a tag object</a>
     * @implNote creating a tag object does not create the reference that makes a tag in Git. If you want to create
     * an annotated tag in Git, you have to do this call to create the tag object, and then create the refs/tags/[tag] reference.
     * If you want to create a lightweight tag, you only have to create the tag reference - this call would be unnecessary
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/tags")
    public Tag createTag(Repository repository, String tag, String message, String object, ObjectType type,
                         CommitProfile tagger) throws IOException {
        return createTag(repository.getOwner().getLogin(), repository.getName(), tag, message, object, type, tagger,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a tag object
     *
     * @param repository: the repository where create the tag
     * @param tag:        the tag's name. This is typically a version (e.g., "v0.0.1")
     * @param message:    the tag message
     * @param object:     the SHA of the git object this is tagging
     * @param type:       the type of the object we're tagging. Normally this is a commit, but it can also be a tree or a blob
     * @param tagger:     an object with information about the individual creating the tag
     * @param format      :               return type formatter -> {@link ReturnFormat}
     * @return tag {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/tags#create-a-tag-object">
     * Create a tag object</a>
     * @implNote creating a tag object does not create the reference that makes a tag in Git. If you want to create
     * an annotated tag in Git, you have to do this call to create the tag object, and then create the refs/tags/[tag] reference.
     * If you want to create a lightweight tag, you only have to create the tag reference - this call would be unnecessary
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/tags")
    public <T> T createTag(Repository repository, String tag, String message, String object, ObjectType type,
                           CommitProfile tagger, ReturnFormat format) throws IOException {
        return createTag(repository.getOwner().getLogin(), repository.getName(), tag, message, object, type, tagger,
                format);
    }

    /**
     * Method to create a tag object
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param tag:     the tag's name. This is typically a version (e.g., "v0.0.1")
     * @param message: the tag message
     * @param object:  the SHA of the git object this is tagging
     * @param type:    the type of the object we're tagging. Normally this is a commit, but it can also be a tree or a blob
     * @param tagger:  an object with information about the individual creating the tag
     * @return tag as {@link Tag} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/tags#create-a-tag-object">
     * Create a tag object</a>
     * @implNote creating a tag object does not create the reference that makes a tag in Git. If you want to create
     * an annotated tag in Git, you have to do this call to create the tag object, and then create the refs/tags/[tag] reference.
     * If you want to create a lightweight tag, you only have to create the tag reference - this call would be unnecessary
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/tags")
    public Tag createTag(String owner, String repo, String tag, String message, String object, CommitProfile tagger,
                         ObjectType type) throws IOException {
        return createTag(owner, repo, tag, message, object, type, tagger, LIBRARY_OBJECT);
    }

    /**
     * Method to create a tag object
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param tag:     the tag's name. This is typically a version (e.g., "v0.0.1")
     * @param message: the tag message
     * @param object:  the SHA of the git object this is tagging
     * @param type:    the type of the object we're tagging. Normally this is a commit, but it can also be a tree or a blob
     * @param tagger:  an object with information about the individual creating the tag
     * @param format   :               return type formatter -> {@link ReturnFormat}
     * @return tag {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/tags#create-a-tag-object">
     * Create a tag object</a>
     * @implNote creating a tag object does not create the reference that makes a tag in Git. If you want to create
     * an annotated tag in Git, you have to do this call to create the tag object, and then create the refs/tags/[tag] reference.
     * If you want to create a lightweight tag, you only have to create the tag reference - this call would be unnecessary
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/git/tags")
    public <T> T createTag(String owner, String repo, String tag, String message, String object, ObjectType type,
                           CommitProfile tagger, ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("tag", tag);
        payload.addParam("message", message);
        payload.addParam("object", object);
        payload.addParam("type", type);
        if (tagger != null)
            payload.addParam("tagger", tagger);
        return returnTag(sendPostRequest(REPOS_PATH + owner + "/" + repo + "/" + GIT_TAGS_PATH, payload), format);
    }

    /**
     * Method to get a tag
     *
     * @param repository: the repository from fetch the tag
     * @param tagSha:     tag sha
     * @return tag as {@link Tag} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/tags#get-a-tag">
     * Get a tag</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/git/tags/{tag_sha}")
    public Tag getTag(Repository repository, String tagSha) throws IOException {
        return getTag(repository.getOwner().getLogin(), repository.getName(), tagSha, LIBRARY_OBJECT);
    }

    /**
     * Method to get a tag
     *
     * @param repository: the repository from fetch the tag
     * @param tagSha:     tag sha
     * @param format      :               return type formatter -> {@link ReturnFormat}
     * @return tag {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/tags#get-a-tag">
     * Get a tag</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/git/tags/{tag_sha}")
    public <T> T getTag(Repository repository, String tagSha, ReturnFormat format) throws IOException {
        return getTag(repository.getOwner().getLogin(), repository.getName(), tagSha, LIBRARY_OBJECT);
    }

    /**
     * Method to get a tag
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param tagSha: tag sha
     * @return tag as {@link Tag} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/tags#get-a-tag">
     * Get a tag</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/git/tags/{tag_sha}")
    public Tag getTag(String owner, String repo, String tagSha) throws IOException {
        return getTag(owner, repo, tagSha, LIBRARY_OBJECT);
    }

    /**
     * Method to get a tag
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param tagSha: tag sha
     * @param format  :               return type formatter -> {@link ReturnFormat}
     * @return tag {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/tags#get-a-tag">
     * Get a tag</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/git/tags/{tag_sha}")
    public <T> T getTag(String owner, String repo, String tagSha, ReturnFormat format) throws IOException {
        return returnTag(sendGetRequest(REPOS_PATH + owner + "/" + repo + "/" + GIT_TAGS_PATH + "/" + tagSha),
                format);
    }

    /**
     * Method to create a tag
     *
     * @param tagResponse : obtained from GitHub's response
     * @param format      :               return type formatter -> {@link ReturnFormat}
     * @return tag {@code "format"} defines
     **/
    @Returner
    private <T> T returnTag(String tagResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(tagResponse);
            case LIBRARY_OBJECT:
                return (T) new Tag(new JSONObject(tagResponse));
            default:
                return (T) tagResponse;
        }
    }

}
