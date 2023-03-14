package com.tecknobit.githubmanager.releases.releaseassets;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.records.repository.Repository;
import com.tecknobit.githubmanager.releases.releaseassets.records.ReleaseAsset;
import com.tecknobit.githubmanager.releases.releases.records.Release;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.releases.releaseassets.records.ReleaseAsset.returnReleaseAssets;
import static com.tecknobit.githubmanager.releases.releases.GitHubReleasesManager.RELEASES_PATH;
import static com.tecknobit.githubmanager.releases.releases.GitHubReleasesManager.RELEASES_QUERY_PATH;

/**
 * The {@code GitHubReleaseAssetsManager} class is useful to manage all GitHub's release assets endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/assets">
 * Release Assets</a>
 * @see GitHubManager
 **/
public class GitHubReleaseAssetsManager extends GitHubManager {

    /**
     * {@code ASSETS_PATH} constant for {@code "/assets"} path
     **/
    public static final String ASSETS_PATH = "/assets";

    /**
     * {@code RELEASES_ASSETS_PATH} constant for {@code "/releases/assets"} path
     **/
    public static final String RELEASES_ASSETS_PATH = RELEASES_PATH + ASSETS_PATH + "/";

    /**
     * Constructor to init a {@link GitHubReleaseAssetsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubReleaseAssetsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubReleaseAssetsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubReleaseAssetsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubReleaseAssetsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubReleaseAssetsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubReleaseAssetsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubReleaseAssetsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubReleaseAssetsManager} <br>
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
    public GitHubReleaseAssetsManager() {
        super();
    }

    /**
     * Method to get a release asset
     *
     * @param repository: the repository from fetch the release asset
     * @param assetId:    the unique identifier of the asset
     * @return release asset as {@link ReleaseAsset} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/assets#get-a-release-asset">
     * Get a release asset</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/assets/{asset_id}")
    public ReleaseAsset getReleaseAsset(Repository repository, long assetId) throws IOException {
        return getReleaseAsset(repository.getOwner().getLogin(), repository.getName(), assetId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a release asset
     *
     * @param repository: the repository from fetch the release asset
     * @param assetId:    the unique identifier of the asset
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return release asset as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/assets#get-a-release-asset">
     * Get a release asset</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/assets/{asset_id}")
    public <T> T getReleaseAsset(Repository repository, long assetId, ReturnFormat format) throws IOException {
        return getReleaseAsset(repository.getOwner().getLogin(), repository.getName(), assetId, format);
    }

    /**
     * Method to get a release asset
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param assetId: the unique identifier of the asset
     * @return release asset as {@link ReleaseAsset} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/assets#get-a-release-asset">
     * Get a release asset</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/assets/{asset_id}")
    public ReleaseAsset getReleaseAsset(String owner, String repo, long assetId) throws IOException {
        return getReleaseAsset(owner, repo, assetId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a release asset
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param assetId: the unique identifier of the asset
     * @param format   :              return type formatter -> {@link ReturnFormat}
     * @return release asset as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/assets#get-a-release-asset">
     * Get a release asset</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/assets/{asset_id}")
    public <T> T getReleaseAsset(String owner, String repo, long assetId, ReturnFormat format) throws IOException {
        return returnReleaseAsset(sendGetRequest(REPOS_PATH + owner + "/" + repo + RELEASES_ASSETS_PATH
                + assetId), format);
    }

    /**
     * Method to update a release asset
     *
     * @param repository: the repository where update the release asset
     * @param asset:      the asset to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "name"} -> the file name of the asset - [string]
     *                       </li>
     *                       <li>
     *                           {@code "label"} -> an alternate short description of the asset. Used in place of the filename - [string]
     *                       </li>
     *                       <li>
     *                           {@code "state"} -> state of the asset - [string]
     *                       </li>
     *                    </ul>
     * @return release asset as {@link ReleaseAsset} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/assets#update-a-release-asset">
     * Update a release asset</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/releases/assets/{asset_id}")
    public ReleaseAsset updateReleaseAsset(Repository repository, ReleaseAsset asset, Params bodyParams) throws IOException {
        return updateReleaseAsset(repository.getOwner().getLogin(), repository.getName(), asset.getId(), bodyParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to update a release asset
     *
     * @param repository: the repository where update the release asset
     * @param asset:      the asset to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "name"} -> the file name of the asset - [string]
     *                       </li>
     *                       <li>
     *                           {@code "label"} -> an alternate short description of the asset. Used in place of the filename - [string]
     *                       </li>
     *                       <li>
     *                           {@code "state"} -> state of the asset - [string]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return release asset as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/assets#update-a-release-asset">
     * Update a release asset</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/releases/assets/{asset_id}")
    public <T> T updateReleaseAsset(Repository repository, ReleaseAsset asset, Params bodyParams,
                                    ReturnFormat format) throws IOException {
        return updateReleaseAsset(repository.getOwner().getLogin(), repository.getName(), asset.getId(), bodyParams,
                format);
    }

    /**
     * Method to update a release asset
     *
     * @param repository: the repository where update the release asset
     * @param assetId:    the unique identifier of the asset
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "name"} -> the file name of the asset - [string]
     *                       </li>
     *                       <li>
     *                           {@code "label"} -> an alternate short description of the asset. Used in place of the filename - [string]
     *                       </li>
     *                       <li>
     *                           {@code "state"} -> state of the asset - [string]
     *                       </li>
     *                    </ul>
     * @return release asset as {@link ReleaseAsset} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/assets#update-a-release-asset">
     * Update a release asset</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/releases/assets/{asset_id}")
    public ReleaseAsset updateReleaseAsset(Repository repository, long assetId, Params bodyParams) throws IOException {
        return updateReleaseAsset(repository.getOwner().getLogin(), repository.getName(), assetId, bodyParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to update a release asset
     *
     * @param repository: the repository where update the release asset
     * @param assetId:    the unique identifier of the asset
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "name"} -> the file name of the asset - [string]
     *                       </li>
     *                       <li>
     *                           {@code "label"} -> an alternate short description of the asset. Used in place of the filename - [string]
     *                       </li>
     *                       <li>
     *                           {@code "state"} -> state of the asset - [string]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return release asset as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/assets#update-a-release-asset">
     * Update a release asset</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/releases/assets/{asset_id}")
    public <T> T updateReleaseAsset(Repository repository, long assetId, Params bodyParams,
                                    ReturnFormat format) throws IOException {
        return updateReleaseAsset(repository.getOwner().getLogin(), repository.getName(), assetId, bodyParams, format);
    }

    /**
     * Method to update a release asset
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param asset:      the asset to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "name"} -> the file name of the asset - [string]
     *                       </li>
     *                       <li>
     *                           {@code "label"} -> an alternate short description of the asset. Used in place of the filename - [string]
     *                       </li>
     *                       <li>
     *                           {@code "state"} -> state of the asset - [string]
     *                       </li>
     *                    </ul>
     * @return release asset as {@link ReleaseAsset} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/assets#update-a-release-asset">
     * Update a release asset</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/releases/assets/{asset_id}")
    public ReleaseAsset updateReleaseAsset(String owner, String repo, ReleaseAsset asset,
                                           Params bodyParams) throws IOException {
        return updateReleaseAsset(owner, repo, asset.getId(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update a release asset
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param asset:      the asset to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "name"} -> the file name of the asset - [string]
     *                       </li>
     *                       <li>
     *                           {@code "label"} -> an alternate short description of the asset. Used in place of the filename - [string]
     *                       </li>
     *                       <li>
     *                           {@code "state"} -> state of the asset - [string]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return release asset as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/assets#update-a-release-asset">
     * Update a release asset</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/releases/assets/{asset_id}")
    public <T> T updateReleaseAsset(String owner, String repo, ReleaseAsset asset, Params bodyParams,
                                    ReturnFormat format) throws IOException {
        return updateReleaseAsset(owner, repo, asset.getId(), bodyParams, format);
    }

    /**
     * Method to update a release asset
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param assetId:    the unique identifier of the asset
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "name"} -> the file name of the asset - [string]
     *                       </li>
     *                       <li>
     *                           {@code "label"} -> an alternate short description of the asset. Used in place of the filename - [string]
     *                       </li>
     *                       <li>
     *                           {@code "state"} -> state of the asset - [string]
     *                       </li>
     *                    </ul>
     * @return release asset as {@link ReleaseAsset} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/assets#update-a-release-asset">
     * Update a release asset</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/releases/assets/{asset_id}")
    public ReleaseAsset updateReleaseAsset(String owner, String repo, long assetId, Params bodyParams) throws IOException {
        return updateReleaseAsset(owner, repo, assetId, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update a release asset
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param assetId:    the unique identifier of the asset
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "name"} -> the file name of the asset - [string]
     *                       </li>
     *                       <li>
     *                           {@code "label"} -> an alternate short description of the asset. Used in place of the filename - [string]
     *                       </li>
     *                       <li>
     *                           {@code "state"} -> state of the asset - [string]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return release asset as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/assets#update-a-release-asset">
     * Update a release asset</a>
     **/
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/releases/assets/{asset_id}")
    public <T> T updateReleaseAsset(String owner, String repo, long assetId, Params bodyParams,
                                    ReturnFormat format) throws IOException {
        return returnReleaseAsset(sendPatchRequest(REPOS_PATH + owner + "/" + repo + RELEASES_ASSETS_PATH
                + assetId, bodyParams), format);
    }

    /**
     * Method to delete a release asset
     *
     * @param repository: the repository where delete the release asset
     * @param asset:      the release asset to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/assets#delete-a-release-asset">
     * Delete a release asset</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/releases/assets/{asset_id}")
    public boolean deleteReleaseAsset(Repository repository, ReleaseAsset asset) {
        return deleteReleaseAsset(repository.getOwner().getLogin(), repository.getName(), asset.getId());
    }

    /**
     * Method to delete a release asset
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param asset: the release asset to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/assets#delete-a-release-asset">
     * Delete a release asset</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/releases/assets/{asset_id}")
    public boolean deleteReleaseAsset(String owner, String repo, ReleaseAsset asset) {
        return deleteReleaseAsset(owner, repo, asset.getId());
    }

    /**
     * Method to delete a release asset
     *
     * @param repository: the repository where delete the release asset
     * @param assetId:    the unique identifier of the asset
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/assets#delete-a-release-asset">
     * Delete a release asset</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/releases/assets/{asset_id}")
    public boolean deleteReleaseAsset(Repository repository, long assetId) {
        return deleteReleaseAsset(repository.getOwner().getLogin(), repository.getName(), assetId);
    }

    /**
     * Method to delete a release asset
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param assetId: the unique identifier of the asset
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/assets#delete-a-release-asset">
     * Delete a release asset</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/releases/assets/{asset_id}")
    public boolean deleteReleaseAsset(String owner, String repo, long assetId) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + RELEASES_ASSETS_PATH + assetId);
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

    public ArrayList<ReleaseAsset> getReleaseAssets(Repository repository, Release release) throws IOException {
        return getReleaseAssets(repository.getOwner().getLogin(), repository.getName(), release.getId(), LIBRARY_OBJECT);
    }

    public <T> T getReleaseAssets(Repository repository, Release release, ReturnFormat format) throws IOException {
        return getReleaseAssets(repository.getOwner().getLogin(), repository.getName(), release.getId(), format);
    }

    public ArrayList<ReleaseAsset> getReleaseAssets(String owner, String repo, Release release) throws IOException {
        return getReleaseAssets(owner, repo, release.getId(), LIBRARY_OBJECT);
    }

    public <T> T getReleaseAssets(String owner, String repo, Release release, ReturnFormat format) throws IOException {
        return getReleaseAssets(owner, repo, release.getId(), format);
    }

    public ArrayList<ReleaseAsset> getReleaseAssets(Repository repository, long releaseId) throws IOException {
        return getReleaseAssets(repository.getOwner().getLogin(), repository.getName(), releaseId, LIBRARY_OBJECT);
    }

    public <T> T getReleaseAssets(Repository repository, long releaseId, ReturnFormat format) throws IOException {
        return getReleaseAssets(repository.getOwner().getLogin(), repository.getName(), releaseId, format);
    }

    public ArrayList<ReleaseAsset> getReleaseAssets(String owner, String repo, long releaseId) throws IOException {
        return getReleaseAssets(owner, repo, releaseId, LIBRARY_OBJECT);
    }

    public <T> T getReleaseAssets(String owner, String repo, long releaseId, ReturnFormat format) throws IOException {
        return returnReleaseAssets(sendGetRequest(REPOS_PATH + owner + "/" + repo + RELEASES_QUERY_PATH
                + releaseId + ASSETS_PATH), format);
    }

    public ArrayList<ReleaseAsset> getReleaseAssets(Repository repository, Release release,
                                                    Params queryParams) throws IOException {
        return getReleaseAssets(repository.getOwner().getLogin(), repository.getName(), release.getId(), queryParams,
                LIBRARY_OBJECT);
    }

    public <T> T getReleaseAssets(Repository repository, Release release, Params queryParams,
                                  ReturnFormat format) throws IOException {
        return getReleaseAssets(repository.getOwner().getLogin(), repository.getName(), release.getId(), queryParams,
                format);
    }

    public ArrayList<ReleaseAsset> getReleaseAssets(String owner, String repo, Release release,
                                                    Params queryParams) throws IOException {
        return getReleaseAssets(owner, repo, release.getId(), queryParams, LIBRARY_OBJECT);
    }

    public <T> T getReleaseAssets(String owner, String repo, Release release, Params queryParams,
                                  ReturnFormat format) throws IOException {
        return getReleaseAssets(owner, repo, release.getId(), queryParams, format);
    }

    public ArrayList<ReleaseAsset> getReleaseAssets(Repository repository, long releaseId,
                                                    Params queryParams) throws IOException {
        return getReleaseAssets(repository.getOwner().getLogin(), repository.getName(), releaseId, queryParams,
                LIBRARY_OBJECT);
    }

    public <T> T getReleaseAssets(Repository repository, long releaseId, Params queryParams,
                                  ReturnFormat format) throws IOException {
        return getReleaseAssets(repository.getOwner().getLogin(), repository.getName(), releaseId, queryParams, format);
    }

    public ArrayList<ReleaseAsset> getReleaseAssets(String owner, String repo, long releaseId,
                                                    Params queryParams) throws IOException {
        return getReleaseAssets(owner, repo, releaseId, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getReleaseAssets(String owner, String repo, long releaseId, Params queryParams,
                                  ReturnFormat format) throws IOException {
        return returnReleaseAssets(sendGetRequest(REPOS_PATH + owner + "/" + repo + RELEASES_QUERY_PATH
                + releaseId + ASSETS_PATH + queryParams.createQueryString()), format);
    }

    public ReleaseAsset uploadReleaseAsset(Repository repository, Release release, String name) throws IOException {
        return uploadReleaseAsset(repository.getOwner().getLogin(), repository.getName(), release.getId(), name,
                LIBRARY_OBJECT);
    }

    public <T> T uploadReleaseAsset(Repository repository, Release release, String name,
                                    ReturnFormat format) throws IOException {
        return uploadReleaseAsset(repository.getOwner().getLogin(), repository.getName(), release.getId(), name, format);
    }

    public ReleaseAsset uploadReleaseAsset(String owner, String repo, Release release, String name) throws IOException {
        return uploadReleaseAsset(owner, repo, release.getId(), name, LIBRARY_OBJECT);
    }

    public <T> T uploadReleaseAsset(String owner, String repo, Release release, String name,
                                    ReturnFormat format) throws IOException {
        return uploadReleaseAsset(owner, repo, release.getId(), name, format);
    }

    public ReleaseAsset uploadReleaseAsset(Repository repository, long releaseId, String name) throws IOException {
        return uploadReleaseAsset(repository.getOwner().getLogin(), repository.getName(), releaseId, name, LIBRARY_OBJECT);
    }

    public <T> T uploadReleaseAsset(Repository repository, long releaseId, String name,
                                    ReturnFormat format) throws IOException {
        return uploadReleaseAsset(repository.getOwner().getLogin(), repository.getName(), releaseId, name, format);
    }

    public ReleaseAsset uploadReleaseAsset(String owner, String repo, long releaseId, String name) throws IOException {
        return uploadReleaseAsset(owner, repo, releaseId, name, LIBRARY_OBJECT);
    }

    public <T> T uploadReleaseAsset(String owner, String repo, long releaseId, String name,
                                    ReturnFormat format) throws IOException {
        return uploadReleaseAsset(owner, repo, releaseId, name, null, format);
    }

    public ReleaseAsset uploadReleaseAsset(Repository repository, Release release, String name,
                                           String label) throws IOException {
        return uploadReleaseAsset(repository.getOwner().getLogin(), repository.getName(), release.getId(), name, label,
                LIBRARY_OBJECT);
    }

    public <T> T uploadReleaseAsset(Repository repository, Release release, String name, String label,
                                    ReturnFormat format) throws IOException {
        return uploadReleaseAsset(repository.getOwner().getLogin(), repository.getName(), release.getId(), name, label,
                format);
    }

    public ReleaseAsset uploadReleaseAsset(String owner, String repo, Release release, String name,
                                           String label) throws IOException {
        return uploadReleaseAsset(owner, repo, release.getId(), name, label, LIBRARY_OBJECT);
    }

    public <T> T uploadReleaseAsset(String owner, String repo, Release release, String name, String label,
                                    ReturnFormat format) throws IOException {
        return uploadReleaseAsset(owner, repo, release.getId(), name, label, format);
    }

    public ReleaseAsset uploadReleaseAsset(Repository repository, long releaseId, String name,
                                           String label) throws IOException {
        return uploadReleaseAsset(repository.getOwner().getLogin(), repository.getName(), releaseId, name, label,
                LIBRARY_OBJECT);
    }

    public <T> T uploadReleaseAsset(Repository repository, long releaseId, String name, String label,
                                    ReturnFormat format) throws IOException {
        return uploadReleaseAsset(repository.getOwner().getLogin(), repository.getName(), releaseId, name, label,
                format);
    }

    public ReleaseAsset uploadReleaseAsset(String owner, String repo, long releaseId, String name,
                                           String label) throws IOException {
        return uploadReleaseAsset(owner, repo, releaseId, name, label, LIBRARY_OBJECT);
    }

    public <T> T uploadReleaseAsset(String owner, String repo, long releaseId, String name, String label,
                                    ReturnFormat format) throws IOException {
        Params query = new Params();
        query.addParam("name", name);
        if (label != null)
            query.addParam("label", label);
        return returnReleaseAsset(sendPostRequest(REPOS_PATH + owner + "/" + repo + RELEASES_QUERY_PATH
                + releaseId + ASSETS_PATH + query.createQueryString(), null), format);
    }

    /**
     * Method to create a release asset
     *
     * @param releaseAssetResponse : obtained from GitHub's response
     * @param format               :              return type formatter -> {@link ReturnFormat}
     * @return release asset as {@code "format"} defines
     **/
    @Returner
    private <T> T returnReleaseAsset(String releaseAssetResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(releaseAssetResponse);
            case LIBRARY_OBJECT:
                return (T) new ReleaseAsset(new JSONObject(releaseAssetResponse));
            default:
                return (T) releaseAssetResponse;
        }
    }

}
