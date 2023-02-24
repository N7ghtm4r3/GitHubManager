package com.tecknobit.githubmanager.migrations.users;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.migrations.records.Migration;
import com.tecknobit.githubmanager.records.repository.CompleteRepository;
import com.tecknobit.githubmanager.records.repository.RepositoriesList;
import com.tecknobit.githubmanager.records.repository.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.apimanager.apis.APIRequest.downloadFile;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.issues.issues.GitHubIssuesManager.LOCK_PATH;
import static com.tecknobit.githubmanager.migrations.organizations.GitHubOrganizationsMigrationsManager.ARCHIVE_PATH;
import static com.tecknobit.githubmanager.migrations.organizations.GitHubOrganizationsMigrationsManager.MIGRATIONS_PATH;
import static com.tecknobit.githubmanager.migrations.records.Migration.returnMigration;
import static com.tecknobit.githubmanager.migrations.records.Migration.returnMigrations;
import static com.tecknobit.githubmanager.records.repository.CompleteRepository.returnCompleteRepositoriesList;

/**
 * The {@code GitHubUsersMigrationsManager} class is useful to manage all GitHub's user migrations endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users">
 * User migrations</a>
 * @see GitHubManager
 **/
public class GitHubUsersMigrationsManager extends GitHubManager {

    /**
     * {@code USER_MIGRATIONS_PATH} constant for {@code "user/migrations"} path
     **/
    public static final String USER_MIGRATIONS_PATH = USER_PATH + MIGRATIONS_PATH;

    /**
     * Constructor to init a {@link GitHubUsersMigrationsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubUsersMigrationsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubUsersMigrationsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubUsersMigrationsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubUsersMigrationsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubUsersMigrationsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubUsersMigrationsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubUsersMigrationsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubUsersMigrationsManager} <br>
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
    public GitHubUsersMigrationsManager() {
        super();
    }

    /**
     * Method to get the list of the all migrations a user has started <br>
     * No-Any params required
     *
     * @return migrations as {@link ArrayList} of {@link Migration} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#list-user-migrations">
     * List user migrations</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/migrations")
    public ArrayList<Migration> getUserMigrations() throws IOException {
        return getUserMigrations(LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all migrations a user has started <br>
     * No-Any params required
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return migrations list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#list-user-migrations">
     * List user migrations</a>
     **/
    @RequestPath(method = GET, path = "/user/migrations")
    public <T> T getUserMigrations(ReturnFormat format) throws IOException {
        return returnMigrations(sendGetRequest(USER_MIGRATIONS_PATH), format);
    }

    /**
     * Method to get the list of the all migrations a user has started
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
     * @return migrations as {@link ArrayList} of {@link Migration} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#list-user-migrations">
     * List user migrations</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/migrations")
    public ArrayList<Migration> getUserMigrations(Params queryParams) throws IOException {
        return getUserMigrations(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all migrations a user has started
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
     * @return migrations list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#list-user-migrations">
     * List user migrations</a>
     **/
    @RequestPath(method = GET, path = "/user/migrations")
    public <T> T getUserMigrations(Params queryParams, ReturnFormat format) throws IOException {
        return returnMigrations(sendGetRequest(USER_MIGRATIONS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to initiate the generation of a user migration archive
     *
     * @param repositories: a list of arrays indicating which repositories should be migrated
     * @return migration as {@link Migration} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#start-a-user-migration">
     * Start a user migration</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/user/migrations")
    public Migration startUserMigration(RepositoriesList repositories) throws IOException {
        return startUserMigration(LIBRARY_OBJECT, repositories.getNames().toArray(new String[0]));
    }

    /**
     * Method to initiate the generation of a user migration archive
     *
     * @param repositories: a list of arrays indicating which repositories should be migrated
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return migration as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#start-a-user-migration">
     * Start a user migration</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/user/migrations")
    public <T> T startUserMigration(RepositoriesList repositories, ReturnFormat format) throws IOException {
        return startUserMigration(format, repositories.getNames().toArray(new String[0]));
    }

    /**
     * Method to initiate the generation of a user migration archive
     *
     * @param repositories: a list of arrays indicating which repositories should be migrated
     * @return migration as {@link Migration} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#start-a-user-migration">
     * Start a user migration</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/user/migrations")
    public Migration startUserMigration(String... repositories) throws IOException {
        return startUserMigration(LIBRARY_OBJECT, repositories);
    }

    /**
     * Method to initiate the generation of a user migration archive
     *
     * @param repositories: a list of arrays indicating which repositories should be migrated
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return migration as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#start-a-user-migration">
     * Start a user migration</a>
     **/
    @RequestPath(method = POST, path = "/user/migrations")
    public <T> T startUserMigration(ReturnFormat format, String... repositories) throws IOException {
        return startUserMigration(null, format, repositories);
    }

    /**
     * Method to initiate the generation of a user migration archive
     *
     * @param repositories: a list of arrays indicating which repositories should be migrated
     * @param bodyParams:   extra body params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "lock_repositories"} -> lock the repositories being migrated at the start of
     *                             the migration - [boolean]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_metadata"} -> indicates whether metadata should be excluded and only git
     *                             source should be included for the migration - [boolean]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_git_data"} -> indicates whether the repository git data should be excluded
     *                             from the migration - [boolean]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_attachments"} -> do not include attachments in the migration - [boolean]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_releases"} -> do not include releases in the migration - [boolean]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_owner_projects"} -> indicates whether projects owned by the organization
     *                             or users should be excluded - [boolean]
     *                         </li>
     *                         <li>
     *                             {@code "org_metadata_only"} -> indicates whether this should only include organization
     *                             metadata (repositories array should be empty and will ignore other flags)
     *                             - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude"} -> exclude related items from being returned in the response in order
     *                             to improve performance of the request. The array can include any of: "repositories"
     *                             - [array of strings]
     *                         </li>
     *                      </ul>
     * @return migration as {@link Migration} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#start-a-user-migration">
     * Start a user migration</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/user/migrations")
    public Migration startUserMigration(RepositoriesList repositories, Params bodyParams) throws IOException {
        return startUserMigration(bodyParams, LIBRARY_OBJECT, repositories.getNames().toArray(new String[0]));
    }

    /**
     * Method to initiate the generation of a user migration archive
     *
     * @param repositories: a list of arrays indicating which repositories should be migrated
     * @param bodyParams:   extra body params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "lock_repositories"} -> lock the repositories being migrated at the start of
     *                             the migration - [boolean]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_metadata"} -> indicates whether metadata should be excluded and only git
     *                             source should be included for the migration - [boolean]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_git_data"} -> indicates whether the repository git data should be excluded
     *                             from the migration - [boolean]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_attachments"} -> do not include attachments in the migration - [boolean]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_releases"} -> do not include releases in the migration - [boolean]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_owner_projects"} -> indicates whether projects owned by the organization
     *                             or users should be excluded - [boolean]
     *                         </li>
     *                         <li>
     *                             {@code "org_metadata_only"} -> indicates whether this should only include organization
     *                             metadata (repositories array should be empty and will ignore other flags)
     *                             - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude"} -> exclude related items from being returned in the response in order
     *                             to improve performance of the request. The array can include any of: "repositories"
     *                             - [array of strings]
     *                         </li>
     *                      </ul>
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return migration as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#start-a-user-migration">
     * Start a user migration</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/user/migrations")
    public <T> T startUserMigration(RepositoriesList repositories, Params bodyParams, ReturnFormat format) throws IOException {
        return startUserMigration(bodyParams, format, repositories.getNames().toArray(new String[0]));
    }

    /**
     * Method to initiate the generation of a user migration archive
     *
     * @param repositories: a list of arrays indicating which repositories should be migrated
     * @param bodyParams:   extra body params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "lock_repositories"} -> lock the repositories being migrated at the start of
     *                             the migration - [boolean]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_metadata"} -> indicates whether metadata should be excluded and only git
     *                             source should be included for the migration - [boolean]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_git_data"} -> indicates whether the repository git data should be excluded
     *                             from the migration - [boolean]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_attachments"} -> do not include attachments in the migration - [boolean]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_releases"} -> do not include releases in the migration - [boolean]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_owner_projects"} -> indicates whether projects owned by the organization
     *                             or users should be excluded - [boolean]
     *                         </li>
     *                         <li>
     *                             {@code "org_metadata_only"} -> indicates whether this should only include organization
     *                             metadata (repositories array should be empty and will ignore other flags)
     *                             - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude"} -> exclude related items from being returned in the response in order
     *                             to improve performance of the request. The array can include any of: "repositories"
     *                             - [array of strings]
     *                         </li>
     *                      </ul>
     * @return migration as {@link Migration} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#start-a-user-migration">
     * Start a user migration</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/user/migrations")
    public Migration startUserMigration(Params bodyParams, String... repositories) throws IOException {
        return startUserMigration(bodyParams, LIBRARY_OBJECT, repositories);
    }

    /**
     * Method to initiate the generation of a user migration archive
     *
     * @param repositories: a list of arrays indicating which repositories should be migrated
     * @param bodyParams:   extra body params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "lock_repositories"} -> lock the repositories being migrated at the start of
     *                             the migration - [boolean]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_metadata"} -> indicates whether metadata should be excluded and only git
     *                             source should be included for the migration - [boolean]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_git_data"} -> indicates whether the repository git data should be excluded
     *                             from the migration - [boolean]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_attachments"} -> do not include attachments in the migration - [boolean]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_releases"} -> do not include releases in the migration - [boolean]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_owner_projects"} -> indicates whether projects owned by the organization
     *                             or users should be excluded - [boolean]
     *                         </li>
     *                         <li>
     *                             {@code "org_metadata_only"} -> indicates whether this should only include organization
     *                             metadata (repositories array should be empty and will ignore other flags)
     *                             - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude"} -> exclude related items from being returned in the response in order
     *                             to improve performance of the request. The array can include any of: "repositories"
     *                             - [array of strings]
     *                         </li>
     *                      </ul>
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return migration as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#start-a-user-migration">
     * Start a user migration</a>
     **/
    @RequestPath(method = POST, path = "/user/migrations")
    public <T> T startUserMigration(Params bodyParams, ReturnFormat format, String... repositories) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("repositories", repositories);
        return returnMigration(sendPostRequest(USER_MIGRATIONS_PATH, bodyParams), format);
    }

    /**
     * Method to fetch a single user migration. The response includes the state of the migration, which can be one of
     * the following values:
     * <ul>
     *     <li>
     *         <b>pending</b> the migration hasn't started yet
     *     </li>
     *     <li>
     *         <b>exporting</b> the migration is in progress
     *     </li>
     *     <li>
     *         <b>exported</b> the migration finished successfully
     *     </li>
     *     <li>
     *         <b>failed</b> the migration failed
     *     </li>
     * </ul>
     * Once the migration has been exported you can download the migration archive.
     *
     * @param migrationId: the unique identifier of the migration
     * @return migration as {@link Migration} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#get-a-user-migration-status">
     * Get a user migration status</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/migrations/{migration_id}")
    public Migration getUserMigrationStatus(long migrationId) throws IOException {
        return getUserMigrationStatus(migrationId, LIBRARY_OBJECT);
    }

    /**
     * Method to fetch a single user migration. The response includes the state of the migration, which can be one of
     * the following values:
     * <ul>
     *     <li>
     *         <b>pending</b> the migration hasn't started yet
     *     </li>
     *     <li>
     *         <b>exporting</b> the migration is in progress
     *     </li>
     *     <li>
     *         <b>exported</b> the migration finished successfully
     *     </li>
     *     <li>
     *         <b>failed</b> the migration failed
     *     </li>
     * </ul>
     * Once the migration has been exported you can download the migration archive.
     *
     * @param migrationId: the unique identifier of the migration
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return migration as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#get-a-user-migration-status">
     * Get a user migration status</a>
     **/
    @RequestPath(method = GET, path = "/user/migrations/{migration_id}")
    public <T> T getUserMigrationStatus(long migrationId, ReturnFormat format) throws IOException {
        return returnMigration(sendGetRequest(USER_MIGRATIONS_PATH + "/" + migrationId), format);
    }

    /**
     * Method to fetch a single user migration. The response includes the state of the migration, which can be one of
     * the following values:
     * <ul>
     *     <li>
     *         <b>pending</b> the migration hasn't started yet
     *     </li>
     *     <li>
     *         <b>exporting</b> the migration is in progress
     *     </li>
     *     <li>
     *         <b>exported</b> the migration finished successfully
     *     </li>
     *     <li>
     *         <b>failed</b> the migration failed
     *     </li>
     * </ul>
     * Once the migration has been exported you can download the migration archive.
     *
     * @param migrationId: the unique identifier of the migration
     * @param exclude:     exclude attributes from the API response to improve performance
     * @return migration as {@link Migration} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#get-a-user-migration-status">
     * Get a user migration status</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/migrations/{migration_id}")
    public <T> Migration getUserMigrationStatus(long migrationId, T exclude) throws IOException {
        return (Migration) getUserMigrationStatus(migrationId, exclude, LIBRARY_OBJECT);
    }

    /**
     * Method to fetch a single user migration. The response includes the state of the migration, which can be one of
     * the following values:
     * <ul>
     *     <li>
     *         <b>pending</b> the migration hasn't started yet
     *     </li>
     *     <li>
     *         <b>exporting</b> the migration is in progress
     *     </li>
     *     <li>
     *         <b>exported</b> the migration finished successfully
     *     </li>
     *     <li>
     *         <b>failed</b> the migration failed
     *     </li>
     * </ul>
     * Once the migration has been exported you can download the migration archive.
     *
     * @param migrationId: the unique identifier of the migration
     * @param exclude:     exclude attributes from the API response to improve performance
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return migration as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#get-a-user-migration-status">
     * Get a user migration status</a>
     **/
    @RequestPath(method = GET, path = "/user/migrations/{migration_id}")
    public <T> T getUserMigrationStatus(long migrationId, T exclude, ReturnFormat format) throws IOException {
        return returnMigration(sendGetRequest(USER_MIGRATIONS_PATH + "/" + migrationId + "?exclude=" + exclude),
                format);
    }

    /**
     * Method to fetch the URL to a migration archive and download it
     *
     * @param migration:   the migration where fetch the URL
     * @param archiveName: path name for the archive file
     * @param save:        flag whether save the file, if is set to {@code "false"} will be created a temporary file
     *                     that will be deleted on exit
     * @return migration archive as {@link File}
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#download-a-user-migration-archive">
     * Download a user migration archive</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/user/migrations/{migration_id}/archive")
    public File downloadUserMigrationArchive(Migration migration, String archiveName, boolean save) {
        return downloadUserMigrationArchive(migration.getId(), archiveName, save);
    }

    /**
     * Method to fetch the URL to a migration archive and download it
     *
     * @param migrationId: the unique identifier of the migration
     * @param archiveName: path name for the archive file
     * @param save:        flag whether save the file, if is set to {@code "false"} will be created a temporary file
     *                     that will be deleted on exit
     * @return migration archive as {@link File}
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#download-a-user-migration-archive">
     * Download a user migration archive</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/user/migrations/{migration_id}/archive")
    public File downloadUserMigrationArchive(long migrationId, String archiveName, boolean save) {
        try {
            String archiveUrl = sendGetRequest(USER_MIGRATIONS_PATH + "/" + migrationId + ARCHIVE_PATH);
            if (apiRequest.getResponseStatusCode() != 302) {
                printErrorResponse();
                return null;
            } else
                return downloadFile(archiveUrl, archiveName, save);
        } catch (IOException e) {
            printErrorResponse();
            return null;
        }
    }

    /**
     * Method to delete a previous migration archive. Downloadable migration archives are automatically deleted after
     * seven days. Migration metadata, which is returned in the List user migrations and Get a user migration status endpoints,
     * will continue to be available even after an archive is deleted
     *
     * @param migration: the migration where delete the archive
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#delete-a-user-migration-archive">
     * Delete a user migration archive</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/user/migrations/{migration_id}/archive")
    public boolean deleteUserMigrationArchive(Migration migration) {
        return deleteUserMigrationArchive(migration.getId());
    }

    /**
     * Method to delete a previous migration archive. Downloadable migration archives are automatically deleted after
     * seven days. Migration metadata, which is returned in the List user migrations and Get a user migration status endpoints,
     * will continue to be available even after an archive is deleted
     *
     * @param migrationId: the unique identifier of the migration
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#delete-a-user-migration-archive">
     * Delete a user migration archive</a>
     **/
    @RequestPath(method = DELETE, path = "/user/migrations/{migration_id}/archive")
    public boolean deleteUserMigrationArchive(long migrationId) {
        try {
            sendDeleteRequest(USER_MIGRATIONS_PATH + "/" + migrationId + ARCHIVE_PATH);
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
     * Method to unlock a repository. You can lock repositories when you start a user migration. Once the migration is
     * complete you can unlock each repository to begin using it again or delete the repository if you no longer need
     * the source data. Returns a status of 404 Not Found if the repository is not locked
     *
     * @param migration:  the migration where unlock the repository
     * @param repository: the repository to unlock
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#unlock-a-user-repository">
     * Unlock a user repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/user/migrations/{migration_id}/repos/{repo_name}/lock")
    public boolean unlockUserRepository(Migration migration, Repository repository) {
        return unlockUserRepository(migration.getId(), repository.getName());
    }

    /**
     * Method to unlock a repository. You can lock repositories when you start a user migration. Once the migration is
     * complete you can unlock each repository to begin using it again or delete the repository if you no longer need
     * the source data. Returns a status of 404 Not Found if the repository is not locked
     *
     * @param migration: the migration where unlock the repository
     * @param repoName:  repo_name parameter
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#unlock-a-user-repository">
     * Unlock a user repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/user/migrations/{migration_id}/repos/{repo_name}/lock")
    public boolean unlockUserRepository(Migration migration, String repoName) {
        return unlockUserRepository(migration.getId(), repoName);
    }

    /**
     * Method to unlock a repository. You can lock repositories when you start a user migration. Once the migration is
     * complete you can unlock each repository to begin using it again or delete the repository if you no longer need
     * the source data. Returns a status of 404 Not Found if the repository is not locked
     *
     * @param migrationId: the unique identifier of the migration
     * @param repository:  the repository to unlock
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#unlock-a-user-repository">
     * Unlock a user repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/user/migrations/{migration_id}/repos/{repo_name}/lock")
    public boolean unlockUserRepository(long migrationId, Repository repository) {
        return unlockUserRepository(migrationId, repository.getName());
    }

    /**
     * Method to unlock a repository. You can lock repositories when you start a user migration. Once the migration is
     * complete you can unlock each repository to begin using it again or delete the repository if you no longer need
     * the source data. Returns a status of 404 Not Found if the repository is not locked
     *
     * @param migrationId: the unique identifier of the migration
     * @param repoName:    repo_name parameter
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#unlock-a-user-repository">
     * Unlock a user repository</a>
     **/
    @RequestPath(method = DELETE, path = "/user/migrations/{migration_id}/repos/{repo_name}/lock")
    public boolean unlockUserRepository(long migrationId, String repoName) {
        try {
            sendDeleteRequest(USER_MIGRATIONS_PATH + "/" + migrationId + "/" + REPOS_PATH + repoName + LOCK_PATH);
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
     * Method to get the list of the all the repositories for this user migration
     *
     * @param migration: the migration from fetch the list
     * @return repositories in a user migration as {@link ArrayList} of {@link CompleteRepository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#list-repositories-for-a-user-migration">
     * List repositories for a user migration</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/user/migrations/{migration_id}/repositories")
    public ArrayList<CompleteRepository> getUserMigrationRepositories(Migration migration) throws IOException {
        return getUserMigrationRepositories(migration.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all the repositories for this user migration
     *
     * @param migration: the migration from fetch the list
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return complete repositories list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#list-repositories-for-a-user-migration">
     * List repositories for a user migration</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/user/migrations/{migration_id}/repositories")
    public <T> T getUserMigrationRepositories(Migration migration, ReturnFormat format) throws IOException {
        return getUserMigrationRepositories(migration.getId(), format);
    }

    /**
     * Method to get the list of the all the repositories for this user migration
     *
     * @param migrationId: the unique identifier of the migration
     * @return repositories in a user migration as {@link ArrayList} of {@link CompleteRepository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#list-repositories-for-a-user-migration">
     * List repositories for a user migration</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/migrations/{migration_id}/repositories")
    public ArrayList<CompleteRepository> getUserMigrationRepositories(long migrationId) throws IOException {
        return getUserMigrationRepositories(migrationId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all the repositories for this user migration
     *
     * @param migrationId: the unique identifier of the migration
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return complete repositories list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#list-repositories-for-a-user-migration">
     * List repositories for a user migration</a>
     **/
    @RequestPath(method = GET, path = "/user/migrations/{migration_id}/repositories")
    public <T> T getUserMigrationRepositories(long migrationId, ReturnFormat format) throws IOException {
        return returnCompleteRepositoriesList(sendGetRequest(USER_MIGRATIONS_PATH + "/" + migrationId
                + REPOSITORIES_PATH), format);
    }

    /**
     * Method to get the list of the all the repositories for this user migration
     *
     * @param migration:   the migration from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return repositories in a user migration as {@link ArrayList} of {@link CompleteRepository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#list-repositories-for-a-user-migration">
     * List repositories for a user migration</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/user/migrations/{migration_id}/repositories")
    public ArrayList<CompleteRepository> getUserMigrationRepositories(Migration migration,
                                                                      Params queryParams) throws IOException {
        return getUserMigrationRepositories(migration.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all the repositories for this user migration
     *
     * @param migration:   the migration from fetch the list
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
     * @return complete repositories list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#list-repositories-for-a-user-migration">
     * List repositories for a user migration</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/user/migrations/{migration_id}/repositories")
    public <T> T getUserMigrationRepositories(Migration migration, Params queryParams,
                                              ReturnFormat format) throws IOException {
        return getUserMigrationRepositories(migration.getId(), queryParams, format);
    }

    /**
     * Method to get the list of the all the repositories for this user migration
     *
     * @param migrationId: the unique identifier of the migration
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return repositories in a user migration as {@link ArrayList} of {@link CompleteRepository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#list-repositories-for-a-user-migration">
     * List repositories for a user migration</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/migrations/{migration_id}/repositories")
    public ArrayList<CompleteRepository> getUserMigrationRepositories(long migrationId,
                                                                      Params queryParams) throws IOException {
        return getUserMigrationRepositories(migrationId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all the repositories for this user migration
     *
     * @param migrationId: the unique identifier of the migration
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
     * @return complete repositories list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/users#list-repositories-for-a-user-migration">
     * List repositories for a user migration</a>
     **/
    @RequestPath(method = GET, path = "/user/migrations/{migration_id}/repositories")
    public <T> T getUserMigrationRepositories(long migrationId, Params queryParams, ReturnFormat format) throws IOException {
        return returnCompleteRepositoriesList(sendGetRequest(USER_MIGRATIONS_PATH + "/" + migrationId
                + REPOSITORIES_PATH + queryParams.createQueryString()), format);
    }

}
