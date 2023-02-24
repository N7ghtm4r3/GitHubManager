package com.tecknobit.githubmanager.migrations.sourceimports;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.migrations.sourceimports.records.CommitAuthor;
import com.tecknobit.githubmanager.migrations.sourceimports.records.Import;
import com.tecknobit.githubmanager.migrations.sourceimports.records.Import.LFS;
import com.tecknobit.githubmanager.migrations.sourceimports.records.Import.VCS;
import com.tecknobit.githubmanager.migrations.sourceimports.records.LargeFile;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubOrganizationsMigrationsManager} class is useful to manage all GitHub's source imports endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports">
 * Source imports</a>
 * @see GitHubManager
 **/
public class GitHubSourceImportsManager extends GitHubManager {

    /**
     * {@code IMPORT_PATH} constant for {@code "/import"} path
     **/
    public static final String IMPORT_PATH = "/import";

    /**
     * {@code IMPORT_AUTHORS_PATH} constant for {@code "/import/authors"} path
     **/
    public static final String IMPORT_AUTHORS_PATH = "/import/authors";

    /**
     * {@code IMPORT_LARGE_FILES_PATH} constant for {@code "/import/large_files"} path
     **/
    public static final String IMPORT_LARGE_FILES_PATH = "/import/large_files";

    /**
     * {@code IMPORT_LFS_PATH} constant for {@code "/import/lfs"} path
     **/
    public static final String IMPORT_LFS_PATH = "/import/lfs";

    /**
     * Constructor to init a {@link GitHubSourceImportsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubSourceImportsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubSourceImportsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubSourceImportsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubSourceImportsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubSourceImportsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubSourceImportsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubSourceImportsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubSourceImportsManager} <br>
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
    public GitHubSourceImportsManager() {
        super();
    }

    /**
     * Method to view the progress of an import
     *
     * @param repository: the repository from fetch the import
     * @return import as {@link Import} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#get-an-import-status">
     * Get an import status</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/import")
    public Import getImportStatus(Repository repository) throws IOException {
        return getImportStatus(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to view the progress of an import
     *
     * @param repository: the repository from fetch the import
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return import as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#get-an-import-status">
     * Get an import status</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/import")
    public <T> T getImportStatus(Repository repository, ReturnFormat format) throws IOException {
        return getImportStatus(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to view the progress of an import
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return import as {@link Import} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#get-an-import-status">
     * Get an import status</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/import")
    public Import getImportStatus(String owner, String repo) throws IOException {
        return getImportStatus(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to view the progress of an import
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return import as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#get-an-import-status">
     * Get an import status</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/import")
    public <T> T getImportStatus(String owner, String repo, ReturnFormat format) throws IOException {
        return returnImport(sendGetRequest(REPOS_PATH + owner + "/" + repo + IMPORT_PATH), format);
    }

    /**
     * Method to start a source import to a GitHub repository using GitHub Importer. Importing into a GitHub repository
     * with GitHub Actions enabled is not supported and will return a status 422 Processable Entity response
     *
     * @param repository: the repository where start the import
     * @param vcsUrl:     the URL of the originating repository
     * @return import as {@link Import} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#start-an-import">
     * Start an import</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/import")
    public Import startImport(Repository repository, String vcsUrl) throws IOException {
        return startImport(repository.getOwner().getLogin(), vcsUrl, repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to start a source import to a GitHub repository using GitHub Importer. Importing into a GitHub repository
     * with GitHub Actions enabled is not supported and will return a status 422 Processable Entity response
     *
     * @param repository: the repository where start the import
     * @param vcsUrl:     the URL of the originating repository
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return import as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#start-an-import">
     * Start an import</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/import")
    public <T> T startImport(Repository repository, String vcsUrl, ReturnFormat format) throws IOException {
        return startImport(repository.getOwner().getLogin(), repository.getName(), vcsUrl, format);
    }

    /**
     * Method to start a source import to a GitHub repository using GitHub Importer. Importing into a GitHub repository
     * with GitHub Actions enabled is not supported and will return a status 422 Processable Entity response
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param vcsUrl: the URL of the originating repository
     * @return import as {@link Import} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#start-an-import">
     * Start an import</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/import")
    public Import startImport(String owner, String repo, String vcsUrl) throws IOException {
        return startImport(owner, repo, vcsUrl, LIBRARY_OBJECT);
    }

    /**
     * Method to start a source import to a GitHub repository using GitHub Importer. Importing into a GitHub repository
     * with GitHub Actions enabled is not supported and will return a status 422 Processable Entity response
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param vcsUrl: the URL of the originating repository
     * @param format  :              return type formatter -> {@link ReturnFormat}
     * @return import as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#start-an-import">
     * Start an import</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/import")
    public <T> T startImport(String owner, String repo, String vcsUrl, ReturnFormat format) throws IOException {
        return startImport(owner, repo, vcsUrl, null, format);
    }

    /**
     * Method to start a source import to a GitHub repository using GitHub Importer. Importing into a GitHub repository
     * with GitHub Actions enabled is not supported and will return a status 422 Processable Entity response
     *
     * @param repository: the repository where start the import
     * @param vcsUrl:     the URL of the originating repository
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "vcs"} -> the originating VCS type. Without this parameter, the import
     *                           job will take additional time to detect the VCS type before beginning the import.
     *                           This detection step will be reflected in the response, constants {@link VCS} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "vcs_username"} -> if authentication is required, the username to provide to
     *                           {@code "vcs_url"} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "vcs_password"} -> if authentication is required, the username to provide to
     *                           {@code "vcs_url"} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "tfvc_project"} -> for a tfvc import, the name of the project that is being
     *                           imported - [string]
     *                       </li>
     *                    </ul>
     * @return import as {@link Import} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#start-an-import">
     * Start an import</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/import")
    public Import startImport(Repository repository, String vcsUrl, Params bodyParams) throws IOException {
        return startImport(repository.getOwner().getLogin(), vcsUrl, repository.getName(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to start a source import to a GitHub repository using GitHub Importer. Importing into a GitHub repository
     * with GitHub Actions enabled is not supported and will return a status 422 Processable Entity response
     *
     * @param repository: the repository where start the import
     * @param vcsUrl:     the URL of the originating repository
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "vcs"} -> the originating VCS type. Without this parameter, the import
     *                           job will take additional time to detect the VCS type before beginning the import.
     *                           This detection step will be reflected in the response, constants {@link VCS} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "vcs_username"} -> if authentication is required, the username to provide to
     *                           {@code "vcs_url"} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "vcs_password"} -> if authentication is required, the username to provide to
     *                           {@code "vcs_url"} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "tfvc_project"} -> for a tfvc import, the name of the project that is being
     *                           imported - [string]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return import as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#start-an-import">
     * Start an import</a>
     **/
    @WrappedRequest
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/import")
    public <T> T startImport(Repository repository, String vcsUrl, Params bodyParams, ReturnFormat format) throws IOException {
        return startImport(repository.getOwner().getLogin(), repository.getName(), vcsUrl, bodyParams, format);
    }

    /**
     * Method to start a source import to a GitHub repository using GitHub Importer. Importing into a GitHub repository
     * with GitHub Actions enabled is not supported and will return a status 422 Processable Entity response
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param vcsUrl:     the URL of the originating repository
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "vcs"} -> the originating VCS type. Without this parameter, the import
     *                           job will take additional time to detect the VCS type before beginning the import.
     *                           This detection step will be reflected in the response, constants {@link VCS} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "vcs_username"} -> if authentication is required, the username to provide to
     *                           {@code "vcs_url"} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "vcs_password"} -> if authentication is required, the username to provide to
     *                           {@code "vcs_url"} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "tfvc_project"} -> for a tfvc import, the name of the project that is being
     *                           imported - [string]
     *                       </li>
     *                    </ul>
     * @return import as {@link Import} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#start-an-import">
     * Start an import</a>
     **/
    @Wrapper
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/import")
    public Import startImport(String owner, String repo, String vcsUrl, Params bodyParams) throws IOException {
        return startImport(owner, repo, vcsUrl, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to start a source import to a GitHub repository using GitHub Importer. Importing into a GitHub repository
     * with GitHub Actions enabled is not supported and will return a status 422 Processable Entity response
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param vcsUrl:     the URL of the originating repository
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "vcs"} -> the originating VCS type. Without this parameter, the import
     *                           job will take additional time to detect the VCS type before beginning the import.
     *                           This detection step will be reflected in the response, constants {@link VCS} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "vcs_username"} -> if authentication is required, the username to provide to
     *                           {@code "vcs_url"} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "vcs_password"} -> if authentication is required, the username to provide to
     *                           {@code "vcs_url"} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "tfvc_project"} -> for a tfvc import, the name of the project that is being
     *                           imported - [string]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return import as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#start-an-import">
     * Start an import</a>
     **/
    @RequestPath(method = PUT, path = "/repos/{owner}/{repo}/import")
    public <T> T startImport(String owner, String repo, String vcsUrl, Params bodyParams,
                             ReturnFormat format) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("vcs_url", vcsUrl);
        return returnImport(sendPutRequest(REPOS_PATH + owner + "/" + repo + IMPORT_PATH, bodyParams), format);
    }

    /**
     * Method to update an import. <br>
     * An import can be updated with credentials or a project choice by passing in the appropriate parameters in this API
     * request. If no parameters are provided, the import will be restarted. <br>
     * Some servers (e.g. TFS servers) can have several projects at a single URL. In those cases the import progress will
     * have the status {@code "detection_found_multiple"} and the Import Progress response will include
     * a {@code "project_choices"} array.
     * You can select the project to import by providing one of the objects in the {@code "project_choices"} array in
     * the update request
     *
     * @param repository: the repository where update the import
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "vcs_username"} -> if authentication is required, the username to provide to
     *                           {@code "vcs_url"} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "vcs_password"} -> if authentication is required, the username to provide to
     *                           {@code "vcs_url"} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "vcs"} -> the originating VCS type. Without this parameter, the import
     *                           job will take additional time to detect the VCS type before beginning the import.
     *                           This detection step will be reflected in the response, constants {@link VCS} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "tfvc_project"} -> for a tfvc import, the name of the project that is being
     *                           imported - [string]
     *                       </li>
     *                    </ul>
     * @return import as {@link Import} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#update-an-import">
     * Update an import</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/import")
    public Import updateImport(Repository repository, Params bodyParams) throws IOException {
        return updateImport(repository.getOwner().getLogin(), repository.getName(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update an import. <br>
     * An import can be updated with credentials or a project choice by passing in the appropriate parameters in this API
     * request. If no parameters are provided, the import will be restarted. <br>
     * Some servers (e.g. TFS servers) can have several projects at a single URL. In those cases the import progress will
     * have the status {@code "detection_found_multiple"} and the Import Progress response will include
     * a {@code "project_choices"} array.
     * You can select the project to import by providing one of the objects in the {@code "project_choices"} array in
     * the update request
     *
     * @param repository: the repository where update the import
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "vcs_username"} -> if authentication is required, the username to provide to
     *                           {@code "vcs_url"} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "vcs_password"} -> if authentication is required, the username to provide to
     *                           {@code "vcs_url"} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "vcs"} -> the originating VCS type. Without this parameter, the import
     *                           job will take additional time to detect the VCS type before beginning the import.
     *                           This detection step will be reflected in the response, constants {@link VCS} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "tfvc_project"} -> for a tfvc import, the name of the project that is being
     *                           imported - [string]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return import as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#update-an-import">
     * Update an import</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/import")
    public <T> T updateImport(Repository repository, Params bodyParams, ReturnFormat format) throws IOException {
        return updateImport(repository.getOwner().getLogin(), repository.getName(), bodyParams, format);
    }

    /**
     * Method to update an import. <br>
     * An import can be updated with credentials or a project choice by passing in the appropriate parameters in this API
     * request. If no parameters are provided, the import will be restarted. <br>
     * Some servers (e.g. TFS servers) can have several projects at a single URL. In those cases the import progress will
     * have the status {@code "detection_found_multiple"} and the Import Progress response will include
     * a {@code "project_choices"} array.
     * You can select the project to import by providing one of the objects in the {@code "project_choices"} array in
     * the update request
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "vcs_username"} -> if authentication is required, the username to provide to
     *                           {@code "vcs_url"} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "vcs_password"} -> if authentication is required, the username to provide to
     *                           {@code "vcs_url"} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "vcs"} -> the originating VCS type. Without this parameter, the import
     *                           job will take additional time to detect the VCS type before beginning the import.
     *                           This detection step will be reflected in the response, constants {@link VCS} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "tfvc_project"} -> for a tfvc import, the name of the project that is being
     *                           imported - [string]
     *                       </li>
     *                    </ul>
     * @return import as {@link Import} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#update-an-import">
     * Update an import</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/import")
    public Import updateImport(String owner, String repo, Params bodyParams) throws IOException {
        return updateImport(owner, repo, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update an import. <br>
     * An import can be updated with credentials or a project choice by passing in the appropriate parameters in this API
     * request. If no parameters are provided, the import will be restarted. <br>
     * Some servers (e.g. TFS servers) can have several projects at a single URL. In those cases the import progress will
     * have the status {@code "detection_found_multiple"} and the Import Progress response will include
     * a {@code "project_choices"} array.
     * You can select the project to import by providing one of the objects in the {@code "project_choices"} array in
     * the update request
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "vcs_username"} -> if authentication is required, the username to provide to
     *                           {@code "vcs_url"} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "vcs_password"} -> if authentication is required, the username to provide to
     *                           {@code "vcs_url"} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "vcs"} -> the originating VCS type. Without this parameter, the import
     *                           job will take additional time to detect the VCS type before beginning the import.
     *                           This detection step will be reflected in the response, constants {@link VCS} - [string]
     *                       </li>
     *                       <li>
     *                           {@code "tfvc_project"} -> for a tfvc import, the name of the project that is being
     *                           imported - [string]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return import as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#update-an-import">
     * Update an import</a>
     **/
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/import")
    public <T> T updateImport(String owner, String repo, Params bodyParams, ReturnFormat format) throws IOException {
        return returnImport(sendPatchRequest(REPOS_PATH + owner + "/" + repo + IMPORT_PATH, bodyParams), format);
    }

    /**
     * Method to stop an import for a repository
     *
     * @param repository: the repository of the import to cancel
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#cancel-an-import">
     * Cancel an import</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/import")
    public boolean cancelImport(Repository repository) {
        return cancelImport(repository.getOwner().getLogin(), repository.getName());
    }

    /**
     * Method to stop an import for a repository
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#cancel-an-import">
     * Cancel an import</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/import")
    public boolean cancelImport(String owner, String repo) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + IMPORT_PATH);
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
     * Method to get commit authors <br>
     * Each type of source control system represents authors in a different way. For example, a Git commit author has a
     * display name and an email address, but a Subversion commit author just has a username.
     * The GitHub Importer will make the author information valid, but the author might not be correct.
     * For example, it will change the bare Subversion username hubot into something like hubot
     * {@code "hubot@12341234-abab-fefe-8787-fedcba987654"}.
     * This endpoint and the Map a commit author endpoint allow you to provide correct Git author information
     *
     * @param repository: the repository from fetch the list
     * @return commit authors as {@link ArrayList} of {@link CommitAuthor} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#get-commit-authors">
     * Get commit authors</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/import/authors")
    public ArrayList<CommitAuthor> getCommitAuthors(Repository repository) throws IOException {
        return getCommitAuthors(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get commit authors <br>
     * Each type of source control system represents authors in a different way. For example, a Git commit author has a
     * display name and an email address, but a Subversion commit author just has a username.
     * The GitHub Importer will make the author information valid, but the author might not be correct.
     * For example, it will change the bare Subversion username hubot into something like hubot
     * {@code "hubot@12341234-abab-fefe-8787-fedcba987654"}.
     * This endpoint and the Map a commit author endpoint allow you to provide correct Git author information
     *
     * @param repository: the repository from fetch the list
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return commit authors list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#get-commit-authors">
     * Get commit authors</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/import/authors")
    public <T> T getCommitAuthors(Repository repository, ReturnFormat format) throws IOException {
        return getCommitAuthors(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get commit authors <br>
     * Each type of source control system represents authors in a different way. For example, a Git commit author has a
     * display name and an email address, but a Subversion commit author just has a username.
     * The GitHub Importer will make the author information valid, but the author might not be correct.
     * For example, it will change the bare Subversion username hubot into something like hubot
     * {@code "hubot@12341234-abab-fefe-8787-fedcba987654"}.
     * This endpoint and the Map a commit author endpoint allow you to provide correct Git author information
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return commit authors as {@link ArrayList} of {@link CommitAuthor} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#get-commit-authors">
     * Get commit authors</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/import/authors")
    public ArrayList<CommitAuthor> getCommitAuthors(String owner, String repo) throws IOException {
        return getCommitAuthors(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get commit authors <br>
     * Each type of source control system represents authors in a different way. For example, a Git commit author has a
     * display name and an email address, but a Subversion commit author just has a username.
     * The GitHub Importer will make the author information valid, but the author might not be correct.
     * For example, it will change the bare Subversion username hubot into something like hubot
     * {@code "hubot@12341234-abab-fefe-8787-fedcba987654"}.
     * This endpoint and the Map a commit author endpoint allow you to provide correct Git author information
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return commit authors list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#get-commit-authors">
     * Get commit authors</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/import/authors")
    public <T> T getCommitAuthors(String owner, String repo, ReturnFormat format) throws IOException {
        return returnCommitAuthors(sendGetRequest(REPOS_PATH + owner + "/" + repo + IMPORT_AUTHORS_PATH), format);
    }

    /**
     * Method to get commit authors <br>
     * Each type of source control system represents authors in a different way. For example, a Git commit author has a
     * display name and an email address, but a Subversion commit author just has a username.
     * The GitHub Importer will make the author information valid, but the author might not be correct.
     * For example, it will change the bare Subversion username hubot into something like hubot
     * {@code "hubot@12341234-abab-fefe-8787-fedcba987654"}.
     * This endpoint and the Map a commit author endpoint allow you to provide correct Git author information
     *
     * @param repository: the repository from fetch the list
     * @param since:      a user ID. Only return users with an ID greater than this ID
     * @return commit authors as {@link ArrayList} of {@link CommitAuthor} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#get-commit-authors">
     * Get commit authors</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/import/authors")
    public ArrayList<CommitAuthor> getCommitAuthors(Repository repository, long since) throws IOException {
        return getCommitAuthors(repository.getOwner().getLogin(), repository.getName(), since, LIBRARY_OBJECT);
    }

    /**
     * Method to get commit authors <br>
     * Each type of source control system represents authors in a different way. For example, a Git commit author has a
     * display name and an email address, but a Subversion commit author just has a username.
     * The GitHub Importer will make the author information valid, but the author might not be correct.
     * For example, it will change the bare Subversion username hubot into something like hubot
     * {@code "hubot@12341234-abab-fefe-8787-fedcba987654"}.
     * This endpoint and the Map a commit author endpoint allow you to provide correct Git author information
     *
     * @param repository: the repository from fetch the list
     * @param since:      a user ID. Only return users with an ID greater than this ID
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return commit authors list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#get-commit-authors">
     * Get commit authors</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/import/authors")
    public <T> T getCommitAuthors(Repository repository, long since, ReturnFormat format) throws IOException {
        return getCommitAuthors(repository.getOwner().getLogin(), repository.getName(), since, format);
    }

    /**
     * Method to get commit authors <br>
     * Each type of source control system represents authors in a different way. For example, a Git commit author has a
     * display name and an email address, but a Subversion commit author just has a username.
     * The GitHub Importer will make the author information valid, but the author might not be correct.
     * For example, it will change the bare Subversion username hubot into something like hubot
     * {@code "hubot@12341234-abab-fefe-8787-fedcba987654"}.
     * This endpoint and the Map a commit author endpoint allow you to provide correct Git author information
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param since: a user ID. Only return users with an ID greater than this ID
     * @return commit authors as {@link ArrayList} of {@link CommitAuthor} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#get-commit-authors">
     * Get commit authors</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/import/authors")
    public ArrayList<CommitAuthor> getCommitAuthors(String owner, String repo, long since) throws IOException {
        return getCommitAuthors(owner, repo, since, LIBRARY_OBJECT);
    }

    /**
     * Method to get commit authors <br>
     * Each type of source control system represents authors in a different way. For example, a Git commit author has a
     * display name and an email address, but a Subversion commit author just has a username.
     * The GitHub Importer will make the author information valid, but the author might not be correct.
     * For example, it will change the bare Subversion username hubot into something like hubot
     * {@code "hubot@12341234-abab-fefe-8787-fedcba987654"}.
     * This endpoint and the Map a commit author endpoint allow you to provide correct Git author information
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param since: a user ID. Only return users with an ID greater than this ID
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return commit authors list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#get-commit-authors">
     * Get commit authors</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/import/authors")
    public <T> T getCommitAuthors(String owner, String repo, long since, ReturnFormat format) throws IOException {
        return returnCommitAuthors(sendGetRequest(REPOS_PATH + owner + "/" + repo + IMPORT_AUTHORS_PATH
                + "?since=" + since), format);
    }

    /**
     * Method to create a commit authors list
     *
     * @param commitAuthorsResponse : obtained from GitHub's response
     * @param format                :              return type formatter -> {@link ReturnFormat}
     * @return commit authors list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnCommitAuthors(String commitAuthorsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(commitAuthorsResponse);
            case LIBRARY_OBJECT:
                ArrayList<CommitAuthor> commitAuthors = new ArrayList<>();
                JSONArray jCommitAuthors = new JSONArray(commitAuthorsResponse);
                for (int j = 0; j < jCommitAuthors.length(); j++)
                    commitAuthors.add(new CommitAuthor(jCommitAuthors.getJSONObject(j)));
                return (T) commitAuthors;
            default:
                return (T) commitAuthorsResponse;
        }
    }

    /**
     * Method to update an author's identity for the import. Your application can continue updating authors any time before
     * you push new commits to the repository
     *
     * @param repository: the repository where map the commit author
     * @param author:     the author to map
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "email"} -> the new Git author email - [string]
     *                       </li>
     *                       <li>
     *                           {@code "name"} -> the new Git author name - [string]
     *                       </li>
     *                    </ul>
     * @return commit author as {@link CommitAuthor} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#map-a-commit-author">
     * Map a commit author</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/import/authors/{author_id}")
    public CommitAuthor mapCommitAuthor(Repository repository, CommitAuthor author, Params bodyParams) throws IOException {
        return mapCommitAuthor(repository.getOwner().getLogin(), repository.getName(), author.getId(), bodyParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to update an author's identity for the import. Your application can continue updating authors any time before
     * you push new commits to the repository
     *
     * @param repository: the repository where map the commit author
     * @param author:     the author to map
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "email"} -> the new Git author email - [string]
     *                       </li>
     *                       <li>
     *                           {@code "name"} -> the new Git author name - [string]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return commit author as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#map-a-commit-author">
     * Map a commit author</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/import/authors/{author_id}")
    public <T> T mapCommitAuthor(Repository repository, CommitAuthor author, Params bodyParams,
                                 ReturnFormat format) throws IOException {
        return mapCommitAuthor(repository.getOwner().getLogin(), repository.getName(), author.getId(), bodyParams, format);
    }

    /**
     * Method to update an author's identity for the import. Your application can continue updating authors any time before
     * you push new commits to the repository
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param author:     the author to map
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "email"} -> the new Git author email - [string]
     *                       </li>
     *                       <li>
     *                           {@code "name"} -> the new Git author name - [string]
     *                       </li>
     *                    </ul>
     * @return commit author as {@link CommitAuthor} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#map-a-commit-author">
     * Map a commit author</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/import/authors/{author_id}")
    public CommitAuthor mapCommitAuthor(String owner, String repo, CommitAuthor author, Params bodyParams) throws IOException {
        return mapCommitAuthor(owner, repo, author.getId(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update an author's identity for the import. Your application can continue updating authors any time before
     * you push new commits to the repository
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param author:     the author to map
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "email"} -> the new Git author email - [string]
     *                       </li>
     *                       <li>
     *                           {@code "name"} -> the new Git author name - [string]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return commit author as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#map-a-commit-author">
     * Map a commit author</a>
     **/
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/import/authors/{author_id}")
    public <T> T mapCommitAuthor(String owner, String repo, CommitAuthor author, Params bodyParams,
                                 ReturnFormat format) throws IOException {
        return mapCommitAuthor(owner, repo, author.getId(), bodyParams, format);
    }

    /**
     * Method to update an author's identity for the import. Your application can continue updating authors any time before
     * you push new commits to the repository
     *
     * @param repository: the repository where map the commit author
     * @param authorId:   the author id
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "email"} -> the new Git author email - [string]
     *                       </li>
     *                       <li>
     *                           {@code "name"} -> the new Git author name - [string]
     *                       </li>
     *                    </ul>
     * @return commit author as {@link CommitAuthor} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#map-a-commit-author">
     * Map a commit author</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/import/authors/{author_id}")
    public CommitAuthor mapCommitAuthor(Repository repository, long authorId, Params bodyParams) throws IOException {
        return mapCommitAuthor(repository.getOwner().getLogin(), repository.getName(), authorId, bodyParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to update an author's identity for the import. Your application can continue updating authors any time before
     * you push new commits to the repository
     *
     * @param repository: the repository where map the commit author
     * @param authorId:   the author id
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "email"} -> the new Git author email - [string]
     *                       </li>
     *                       <li>
     *                           {@code "name"} -> the new Git author name - [string]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return commit author as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#map-a-commit-author">
     * Map a commit author</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/import/authors/{author_id}")
    public <T> T mapCommitAuthor(Repository repository, long authorId, Params bodyParams,
                                 ReturnFormat format) throws IOException {
        return mapCommitAuthor(repository.getOwner().getLogin(), repository.getName(), authorId, bodyParams, format);
    }

    /**
     * Method to update an author's identity for the import. Your application can continue updating authors any time before
     * you push new commits to the repository
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param authorId:   the author id
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "email"} -> the new Git author email - [string]
     *                       </li>
     *                       <li>
     *                           {@code "name"} -> the new Git author name - [string]
     *                       </li>
     *                    </ul>
     * @return commit author as {@link CommitAuthor} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#map-a-commit-author">
     * Map a commit author</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/import/authors/{author_id}")
    public CommitAuthor mapCommitAuthor(String owner, String repo, long authorId, Params bodyParams) throws IOException {
        return mapCommitAuthor(owner, repo, authorId, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update an author's identity for the import. Your application can continue updating authors any time before
     * you push new commits to the repository
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param authorId:   the author id
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "email"} -> the new Git author email - [string]
     *                       </li>
     *                       <li>
     *                           {@code "name"} -> the new Git author name - [string]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return commit author as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#map-a-commit-author">
     * Map a commit author</a>
     **/
    @Returner
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/import/authors/{author_id}")
    public <T> T mapCommitAuthor(String owner, String repo, long authorId, Params bodyParams,
                                 ReturnFormat format) throws IOException {
        String commitAuthorResponse = sendPatchRequest(REPOS_PATH + owner + "/" + repo + IMPORT_AUTHORS_PATH
                + "/" + authorId, bodyParams);
        switch (format) {
            case JSON:
                return (T) new JSONObject(commitAuthorResponse);
            case LIBRARY_OBJECT:
                return (T) new CommitAuthor(new JSONObject(commitAuthorResponse));
            default:
                return (T) commitAuthorResponse;
        }
    }

    /**
     * Method to get the list of the files larger than 100MB found during the import
     *
     * @param repository: the repository from fetch the list
     * @return large files as {@link ArrayList} of {@link LargeFile} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#get-large-files">
     * Get large files</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/import/large_files")
    public ArrayList<LargeFile> getLargeFiles(Repository repository) throws IOException {
        return getLargeFiles(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the files larger than 100MB found during the import
     *
     * @param repository: the repository from fetch the list
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return large files as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#get-large-files">
     * Get large files</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/import/large_files")
    public <T> T getLargeFiles(Repository repository, ReturnFormat format) throws IOException {
        return getLargeFiles(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list of the files larger than 100MB found during the import
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return large files as {@link ArrayList} of {@link LargeFile} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#get-large-files">
     * Get large files</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/import/large_files")
    public ArrayList<LargeFile> getLargeFiles(String owner, String repo) throws IOException {
        return getLargeFiles(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the files larger than 100MB found during the import
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return large files as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#get-large-files">
     * Get large files</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/import/large_files")
    public <T> T getLargeFiles(String owner, String repo, ReturnFormat format) throws IOException {
        String largeFilesResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo + IMPORT_LARGE_FILES_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONArray(largeFilesResponse);
            case LIBRARY_OBJECT:
                ArrayList<LargeFile> largeFiles = new ArrayList<>();
                JSONArray jLargeFiles = new JSONArray(largeFilesResponse);
                for (int j = 0; j < jLargeFiles.length(); j++)
                    largeFiles.add(new LargeFile(jLargeFiles.getJSONObject(j)));
                return (T) largeFiles;
            default:
                return (T) largeFilesResponse;
        }
    }

    /**
     * Method to update Git LFS preference. <br>
     * You can import repositories from Subversion, Mercurial, and TFS that include files larger than 100MB. This ability
     * is powered by Git LFS. You can learn more about our LFS feature and working with large files on our help site
     *
     * @param repository: the repository where update the Git LFS preference
     * @param lfs:        whether to store large files during the import. {@code "opt_in"} means large files will be stored using
     *                    Git LFS. {@code "opt_out"} means large files will be removed during the import
     * @return import as {@link Import} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#update-git-lfs-preference">
     * Update Git LFS preference</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/import/lfs")
    public Import updateGitLFSPreference(Repository repository, LFS lfs) throws IOException {
        return updateGitLFSPreference(repository.getOwner().getLogin(), repository.getName(), lfs, LIBRARY_OBJECT);
    }

    /**
     * Method to update Git LFS preference. <br>
     * You can import repositories from Subversion, Mercurial, and TFS that include files larger than 100MB. This ability
     * is powered by Git LFS. You can learn more about our LFS feature and working with large files on our help site
     *
     * @param repository: the repository where update the Git LFS preference
     * @param lfs:        whether to store large files during the import. {@code "opt_in"} means large files will be stored using
     *                    Git LFS. {@code "opt_out"} means large files will be removed during the import
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return import as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#update-git-lfs-preference">
     * Update Git LFS preference</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/import/lfs")
    public <T> T updateGitLFSPreference(Repository repository, LFS lfs, ReturnFormat format) throws IOException {
        return updateGitLFSPreference(repository.getOwner().getLogin(), repository.getName(), lfs, LIBRARY_OBJECT);
    }

    /**
     * Method to update Git LFS preference. <br>
     * You can import repositories from Subversion, Mercurial, and TFS that include files larger than 100MB. This ability
     * is powered by Git LFS. You can learn more about our LFS feature and working with large files on our help site
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param lfs:   whether to store large files during the import. {@code "opt_in"} means large files will be stored using
     *               Git LFS. {@code "opt_out"} means large files will be removed during the import
     * @return import as {@link Import} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#update-git-lfs-preference">
     * Update Git LFS preference</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/import/lfs")
    public Import updateGitLFSPreference(String owner, String repo, LFS lfs) throws IOException {
        return updateGitLFSPreference(owner, repo, lfs, LIBRARY_OBJECT);
    }

    /**
     * Method to update Git LFS preference. <br>
     * You can import repositories from Subversion, Mercurial, and TFS that include files larger than 100MB. This ability
     * is powered by Git LFS. You can learn more about our LFS feature and working with large files on our help site
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param lfs:   whether to store large files during the import. {@code "opt_in"} means large files will be stored using
     *               Git LFS. {@code "opt_out"} means large files will be removed during the import
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return import as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#update-git-lfs-preference">
     * Update Git LFS preference</a>
     **/
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/import/lfs")
    public <T> T updateGitLFSPreference(String owner, String repo, LFS lfs, ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("use_lfs", lfs);
        return returnImport(sendPatchRequest(REPOS_PATH + owner + "/" + repo + IMPORT_LFS_PATH, payload), format);
    }

    /**
     * Method to create an import
     *
     * @param importResponse : obtained from GitHub's response
     * @param format         :              return type formatter -> {@link ReturnFormat}
     * @return import as {@code "format"} defines
     **/
    @Returner
    private <T> T returnImport(String importResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(importResponse);
            case LIBRARY_OBJECT:
                return (T) new Import(new JSONObject(importResponse));
            default:
                return (T) importResponse;
        }
    }

}
