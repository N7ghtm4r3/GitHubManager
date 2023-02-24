package com.tecknobit.githubmanager.migrations.organizations;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.migrations.records.Migration;
import com.tecknobit.githubmanager.records.organization.Organization;
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
import static com.tecknobit.githubmanager.migrations.records.Migration.returnMigration;
import static com.tecknobit.githubmanager.migrations.records.Migration.returnMigrations;
import static com.tecknobit.githubmanager.records.repository.CompleteRepository.returnCompleteRepositoriesList;

/**
 * The {@code GitHubOrganizationsMigrationsManager} class is useful to manage all GitHub's organization migrations endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs">
 * Organization migrations</a>
 * @see GitHubManager
 **/
public class GitHubOrganizationsMigrationsManager extends GitHubManager {

    /**
     * {@code MIGRATIONS_PATH} constant for {@code "/migrations"} path
     **/
    public static final String MIGRATIONS_PATH = "/migrations";

    /**
     * {@code ARCHIVE_PATH} constant for {@code "/archive"} path
     **/
    public static final String ARCHIVE_PATH = "/archive";

    /**
     * Constructor to init a {@link GitHubOrganizationsMigrationsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubOrganizationsMigrationsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubOrganizationsMigrationsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubOrganizationsMigrationsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubOrganizationsMigrationsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubOrganizationsMigrationsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubOrganizationsMigrationsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubOrganizationsMigrationsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubOrganizationsMigrationsManager} <br>
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
    public GitHubOrganizationsMigrationsManager() {
        super();
    }

    /**
     * Method to get the list of the most recent migrations, including both exports (which can be started through the REST API)
     * and imports (which cannot be started using the REST API). <br>
     * A list of repositories is only returned for export migrations
     *
     * @param org: the organization from fetch the list
     * @return most recent migrations as {@link ArrayList} of {@link Migration} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#list-organization-migrations">
     * List organization migrations</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/migrations")
    public ArrayList<Migration> getOrganizationMigrations(Organization org) throws IOException {
        return getOrganizationMigrations(org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the most recent migrations, including both exports (which can be started through the REST API)
     * and imports (which cannot be started using the REST API). <br>
     * A list of repositories is only returned for export migrations
     *
     * @param org:   the organization from fetch the list
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return organization migrations list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#list-organization-migrations">
     * List organization migrations</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/migrations")
    public <T> T getOrganizationMigrations(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationMigrations(org.getLogin(), format);
    }

    /**
     * Method to get the list of the most recent migrations, including both exports (which can be started through the REST API)
     * and imports (which cannot be started using the REST API). <br>
     * A list of repositories is only returned for export migrations
     *
     * @param org: the organization name. The name is not case-sensitive
     * @return most recent migrations as {@link ArrayList} of {@link Migration} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#list-organization-migrations">
     * List organization migrations</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/migrations")
    public ArrayList<Migration> getOrganizationMigrations(String org) throws IOException {
        return getOrganizationMigrations(org, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the most recent migrations, including both exports (which can be started through the REST API)
     * and imports (which cannot be started using the REST API). <br>
     * A list of repositories is only returned for export migrations
     *
     * @param org:   the organization name. The name is not case-sensitive
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return organization migrations list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#list-organization-migrations">
     * List organization migrations</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/migrations")
    public <T> T getOrganizationMigrations(String org, ReturnFormat format) throws IOException {
        return returnMigrations(sendGetRequest(ORGS_PATH + org + MIGRATIONS_PATH), format);
    }

    /**
     * Method to get the list of the most recent migrations, including both exports (which can be started through the REST API)
     * and imports (which cannot be started using the REST API). <br>
     * A list of repositories is only returned for export migrations
     *
     * @param org:         the organization from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "exclude"} -> exclude attributes from the API response to improve performance
     *                            - [array]
     *                        </li>
     *                     </ul>
     * @return most recent migrations as {@link ArrayList} of {@link Migration} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#list-organization-migrations">
     * List organization migrations</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/migrations")
    public ArrayList<Migration> getOrganizationMigrations(Organization org, Params queryParams) throws IOException {
        return getOrganizationMigrations(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the most recent migrations, including both exports (which can be started through the REST API)
     * and imports (which cannot be started using the REST API). <br>
     * A list of repositories is only returned for export migrations
     *
     * @param org:         the organization from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "exclude"} -> exclude attributes from the API response to improve performance
     *                            - [array]
     *                        </li>
     *                     </ul>
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return organization migrations list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#list-organization-migrations">
     * List organization migrations</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/migrations")
    public <T> T getOrganizationMigrations(Organization org, Params queryParams, ReturnFormat format) throws IOException {
        return getOrganizationMigrations(org.getLogin(), queryParams, format);
    }

    /**
     * Method to get the list of the most recent migrations, including both exports (which can be started through the REST API)
     * and imports (which cannot be started using the REST API). <br>
     * A list of repositories is only returned for export migrations
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "exclude"} -> exclude attributes from the API response to improve performance
     *                            - [array]
     *                        </li>
     *                     </ul>
     * @return most recent migrations as {@link ArrayList} of {@link Migration} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#list-organization-migrations">
     * List organization migrations</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/migrations")
    public ArrayList<Migration> getOrganizationMigrations(String org, Params queryParams) throws IOException {
        return getOrganizationMigrations(org, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the most recent migrations, including both exports (which can be started through the REST API)
     * and imports (which cannot be started using the REST API). <br>
     * A list of repositories is only returned for export migrations
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "exclude"} -> exclude attributes from the API response to improve performance
     *                            - [array]
     *                        </li>
     *                     </ul>
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return organization migrations list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#list-organization-migrations">
     * List organization migrations</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/migrations")
    public <T> T getOrganizationMigrations(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnMigrations(sendGetRequest(ORGS_PATH + org + MIGRATIONS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to initiate the generation of a migration archive
     *
     * @param org:          the organization where start the organization migration
     * @param repositories: a list of arrays indicating which repositories should be migrated
     * @return organization migration as {@link Migration} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#start-an-organization-migration">
     * Start an organization migration</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/migrations")
    public Migration startOrganizationMigration(Organization org,
                                                RepositoriesList repositories) throws IOException {
        return startOrganizationMigration(org.getLogin(), LIBRARY_OBJECT, repositories.getNames().toArray(new String[0]));
    }

    /**
     * Method to initiate the generation of a migration archive
     *
     * @param org:          the organization where start the organization migration
     * @param repositories: a list of arrays indicating which repositories should be migrated
     * @param format        :              return type formatter -> {@link ReturnFormat}
     * @return organization migration as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#start-an-organization-migration">
     * Start an organization migration</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/migrations")
    public <T> T startOrganizationMigration(Organization org, RepositoriesList repositories,
                                            ReturnFormat format) throws IOException {
        return startOrganizationMigration(org.getLogin(), format, repositories.getNames().toArray(new String[0]));
    }

    /**
     * Method to initiate the generation of a migration archive
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param repositories: a list of arrays indicating which repositories should be migrated
     * @return organization migration as {@link Migration} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#start-an-organization-migration">
     * Start an organization migration</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/migrations")
    public Migration startOrganizationMigration(String org, RepositoriesList repositories) throws IOException {
        return startOrganizationMigration(org, LIBRARY_OBJECT, repositories.getNames().toArray(new String[0]));
    }

    /**
     * Method to initiate the generation of a migration archive
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param repositories: a list of arrays indicating which repositories should be migrated
     * @param format        :              return type formatter -> {@link ReturnFormat}
     * @return organization migration as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#start-an-organization-migration">
     * Start an organization migration</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/migrations")
    public <T> T startOrganizationMigration(String org, RepositoriesList repositories, ReturnFormat format) throws IOException {
        return startOrganizationMigration(org, format, repositories.getNames().toArray(new String[0]));
    }

    /**
     * Method to initiate the generation of a migration archive
     *
     * @param org:          the organization where start the organization migration
     * @param repositories: a list of arrays indicating which repositories should be migrated
     * @return organization migration as {@link Migration} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#start-an-organization-migration">
     * Start an organization migration</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/migrations")
    public Migration startOrganizationMigration(Organization org, String... repositories) throws IOException {
        return startOrganizationMigration(org.getLogin(), LIBRARY_OBJECT, repositories);
    }

    /**
     * Method to initiate the generation of a migration archive
     *
     * @param org:          the organization where start the organization migration
     * @param repositories: a list of arrays indicating which repositories should be migrated
     * @param format        :              return type formatter -> {@link ReturnFormat}
     * @return organization migration as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#start-an-organization-migration">
     * Start an organization migration</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/migrations")
    public <T> T startOrganizationMigration(Organization org, ReturnFormat format, String... repositories) throws IOException {
        return startOrganizationMigration(org.getLogin(), format, repositories);
    }

    /**
     * Method to initiate the generation of a migration archive
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param repositories: a list of arrays indicating which repositories should be migrated
     * @return organization migration as {@link Migration} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#start-an-organization-migration">
     * Start an organization migration</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/orgs/{org}/migrations")
    public Migration startOrganizationMigration(String org, String... repositories) throws IOException {
        return startOrganizationMigration(org, LIBRARY_OBJECT, repositories);
    }

    /**
     * Method to initiate the generation of a migration archive
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param repositories: a list of arrays indicating which repositories should be migrated
     * @param format        :              return type formatter -> {@link ReturnFormat}
     * @return organization migration as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#start-an-organization-migration">
     * Start an organization migration</a>
     **/
    @RequestPath(method = POST, path = "/orgs/{org}/migrations")
    public <T> T startOrganizationMigration(String org, ReturnFormat format, String... repositories) throws IOException {
        return startOrganizationMigration(org, null, format, repositories);
    }

    /**
     * Method to initiate the generation of a migration archive
     *
     * @param org:          the organization where start the organization migration
     * @param repositories: a list of arrays indicating which repositories should be migrated
     * @param bodyParams:   extra body params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "lock_repositories"} -> indicates whether repositories should be locked
     *                             (to prevent manipulation) while migrating data - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_metadata"} -> indicates whether metadata should be excluded and only git
     *                             source should be included for the migration - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_git_data"} -> indicates whether the repository git data should be
     *                             excluded from the migration - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_attachments"} -> indicates whether attachments should be excluded from
     *                             the migration (to reduce migration archive file size) - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_releases"} -> indicates whether releases should be excluded from the
     *                             migration (to reduce migration archive file size) - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_owner_projects"} -> indicates whether projects owned by the organization
     *                             or users should be excluded. from the migration - [boolean, default false]
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
     * @return organization migration as {@link Migration} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#start-an-organization-migration">
     * Start an organization migration</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/migrations")
    public Migration startOrganizationMigration(Organization org, RepositoriesList repositories,
                                                Params bodyParams) throws IOException {
        return startOrganizationMigration(org.getLogin(), bodyParams, LIBRARY_OBJECT,
                repositories.getNames().toArray(new String[0]));
    }

    /**
     * Method to initiate the generation of a migration archive
     *
     * @param org:          the organization where start the organization migration
     * @param repositories: a list of arrays indicating which repositories should be migrated
     * @param bodyParams:   extra body params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "lock_repositories"} -> indicates whether repositories should be locked
     *                             (to prevent manipulation) while migrating data - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_metadata"} -> indicates whether metadata should be excluded and only git
     *                             source should be included for the migration - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_git_data"} -> indicates whether the repository git data should be
     *                             excluded from the migration - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_attachments"} -> indicates whether attachments should be excluded from
     *                             the migration (to reduce migration archive file size) - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_releases"} -> indicates whether releases should be excluded from the
     *                             migration (to reduce migration archive file size) - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_owner_projects"} -> indicates whether projects owned by the organization
     *                             or users should be excluded. from the migration - [boolean, default false]
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
     * @param format        :              return type formatter -> {@link ReturnFormat}
     * @return organization migration as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#start-an-organization-migration">
     * Start an organization migration</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/migrations")
    public <T> T startOrganizationMigration(Organization org, RepositoriesList repositories, Params bodyParams,
                                            ReturnFormat format) throws IOException {
        return startOrganizationMigration(org.getLogin(), bodyParams, format,
                repositories.getNames().toArray(new String[0]));
    }

    /**
     * Method to initiate the generation of a migration archive
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param repositories: a list of arrays indicating which repositories should be migrated
     * @param bodyParams:   extra body params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "lock_repositories"} -> indicates whether repositories should be locked
     *                             (to prevent manipulation) while migrating data - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_metadata"} -> indicates whether metadata should be excluded and only git
     *                             source should be included for the migration - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_git_data"} -> indicates whether the repository git data should be
     *                             excluded from the migration - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_attachments"} -> indicates whether attachments should be excluded from
     *                             the migration (to reduce migration archive file size) - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_releases"} -> indicates whether releases should be excluded from the
     *                             migration (to reduce migration archive file size) - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_owner_projects"} -> indicates whether projects owned by the organization
     *                             or users should be excluded. from the migration - [boolean, default false]
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
     * @return organization migration as {@link Migration} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#start-an-organization-migration">
     * Start an organization migration</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/migrations")
    public Migration startOrganizationMigration(String org, RepositoriesList repositories,
                                                Params bodyParams) throws IOException {
        return startOrganizationMigration(org, bodyParams, LIBRARY_OBJECT, repositories.getNames().toArray(new String[0]));
    }

    /**
     * Method to initiate the generation of a migration archive
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param repositories: a list of arrays indicating which repositories should be migrated
     * @param bodyParams:   extra body params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "lock_repositories"} -> indicates whether repositories should be locked
     *                             (to prevent manipulation) while migrating data - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_metadata"} -> indicates whether metadata should be excluded and only git
     *                             source should be included for the migration - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_git_data"} -> indicates whether the repository git data should be
     *                             excluded from the migration - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_attachments"} -> indicates whether attachments should be excluded from
     *                             the migration (to reduce migration archive file size) - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_releases"} -> indicates whether releases should be excluded from the
     *                             migration (to reduce migration archive file size) - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_owner_projects"} -> indicates whether projects owned by the organization
     *                             or users should be excluded. from the migration - [boolean, default false]
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
     * @param format        :              return type formatter -> {@link ReturnFormat}
     * @return organization migration as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#start-an-organization-migration">
     * Start an organization migration</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/migrations")
    public <T> T startOrganizationMigration(String org, RepositoriesList repositories, Params bodyParams,
                                            ReturnFormat format) throws IOException {
        return startOrganizationMigration(org, bodyParams, format, repositories.getNames().toArray(new String[0]));
    }

    /**
     * Method to initiate the generation of a migration archive
     *
     * @param org:          the organization where start the organization migration
     * @param repositories: a list of arrays indicating which repositories should be migrated
     * @param bodyParams:   extra body params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "lock_repositories"} -> indicates whether repositories should be locked
     *                             (to prevent manipulation) while migrating data - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_metadata"} -> indicates whether metadata should be excluded and only git
     *                             source should be included for the migration - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_git_data"} -> indicates whether the repository git data should be
     *                             excluded from the migration - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_attachments"} -> indicates whether attachments should be excluded from
     *                             the migration (to reduce migration archive file size) - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_releases"} -> indicates whether releases should be excluded from the
     *                             migration (to reduce migration archive file size) - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_owner_projects"} -> indicates whether projects owned by the organization
     *                             or users should be excluded. from the migration - [boolean, default false]
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
     * @return organization migration as {@link Migration} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#start-an-organization-migration">
     * Start an organization migration</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/migrations")
    public Migration startOrganizationMigration(Organization org, Params bodyParams, String... repositories) throws IOException {
        return startOrganizationMigration(org.getLogin(), bodyParams, LIBRARY_OBJECT, repositories);
    }

    /**
     * Method to initiate the generation of a migration archive
     *
     * @param org:          the organization where start the organization migration
     * @param repositories: a list of arrays indicating which repositories should be migrated
     * @param bodyParams:   extra body params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "lock_repositories"} -> indicates whether repositories should be locked
     *                             (to prevent manipulation) while migrating data - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_metadata"} -> indicates whether metadata should be excluded and only git
     *                             source should be included for the migration - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_git_data"} -> indicates whether the repository git data should be
     *                             excluded from the migration - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_attachments"} -> indicates whether attachments should be excluded from
     *                             the migration (to reduce migration archive file size) - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_releases"} -> indicates whether releases should be excluded from the
     *                             migration (to reduce migration archive file size) - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_owner_projects"} -> indicates whether projects owned by the organization
     *                             or users should be excluded. from the migration - [boolean, default false]
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
     * @param format        :              return type formatter -> {@link ReturnFormat}
     * @return organization migration as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#start-an-organization-migration">
     * Start an organization migration</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/migrations")
    public <T> T startOrganizationMigration(Organization org, Params bodyParams, ReturnFormat format,
                                            String... repositories) throws IOException {
        return startOrganizationMigration(org.getLogin(), bodyParams, format, repositories);
    }

    /**
     * Method to initiate the generation of a migration archive
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param repositories: a list of arrays indicating which repositories should be migrated
     * @param bodyParams:   extra body params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "lock_repositories"} -> indicates whether repositories should be locked
     *                             (to prevent manipulation) while migrating data - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_metadata"} -> indicates whether metadata should be excluded and only git
     *                             source should be included for the migration - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_git_data"} -> indicates whether the repository git data should be
     *                             excluded from the migration - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_attachments"} -> indicates whether attachments should be excluded from
     *                             the migration (to reduce migration archive file size) - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_releases"} -> indicates whether releases should be excluded from the
     *                             migration (to reduce migration archive file size) - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_owner_projects"} -> indicates whether projects owned by the organization
     *                             or users should be excluded. from the migration - [boolean, default false]
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
     * @return organization migration as {@link Migration} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#start-an-organization-migration">
     * Start an organization migration</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/orgs/{org}/migrations")
    public Migration startOrganizationMigration(String org, Params bodyParams, String... repositories) throws IOException {
        return startOrganizationMigration(org, bodyParams, LIBRARY_OBJECT, repositories);
    }

    /**
     * Method to initiate the generation of a migration archive
     *
     * @param org:          the organization name. The name is not case-sensitive
     * @param repositories: a list of arrays indicating which repositories should be migrated
     * @param bodyParams:   extra body params not mandatory, keys accepted are:
     *                      <ul>
     *                         <li>
     *                             {@code "lock_repositories"} -> indicates whether repositories should be locked
     *                             (to prevent manipulation) while migrating data - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_metadata"} -> indicates whether metadata should be excluded and only git
     *                             source should be included for the migration - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_git_data"} -> indicates whether the repository git data should be
     *                             excluded from the migration - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_attachments"} -> indicates whether attachments should be excluded from
     *                             the migration (to reduce migration archive file size) - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_releases"} -> indicates whether releases should be excluded from the
     *                             migration (to reduce migration archive file size) - [boolean, default false]
     *                         </li>
     *                         <li>
     *                             {@code "exclude_owner_projects"} -> indicates whether projects owned by the organization
     *                             or users should be excluded. from the migration - [boolean, default false]
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
     * @param format        :              return type formatter -> {@link ReturnFormat}
     * @return organization migration as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#start-an-organization-migration">
     * Start an organization migration</a>
     **/
    @RequestPath(method = POST, path = "/orgs/{org}/migrations")
    public <T> T startOrganizationMigration(String org, Params bodyParams, ReturnFormat format,
                                            String... repositories) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("repositories", repositories);
        return returnMigration(sendPostRequest(ORGS_PATH + org + MIGRATIONS_PATH, bodyParams),
                format);
    }

    /**
     * Method to get the status of a migration
     *
     * @param org:         the organization where fetch the status of a migration
     * @param migrationId: the unique identifier of the migration
     * @return organization migration as {@link Migration} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#get-an-organization-migration-status">
     * Get an organization migration status</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/migrations/{migration_id}")
    public Migration getOrganizationMigration(Organization org, long migrationId) throws IOException {
        return getOrganizationMigration(org.getLogin(), migrationId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the status of a migration
     *
     * @param org:         the organization where fetch the status of a migration
     * @param migrationId: the unique identifier of the migration
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return organization migration as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#get-an-organization-migration-status">
     * Get an organization migration status</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/migrations/{migration_id}")
    public <T> T getOrganizationMigration(Organization org, long migrationId, ReturnFormat format) throws IOException {
        return getOrganizationMigration(org.getLogin(), migrationId, format);
    }

    /**
     * Method to get the status of a migration
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param migrationId: the unique identifier of the migration
     * @return organization migration as {@link Migration} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#get-an-organization-migration-status">
     * Get an organization migration status</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/migrations/{migration_id}")
    public Migration getOrganizationMigration(String org, long migrationId) throws IOException {
        return getOrganizationMigration(org, migrationId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the status of a migration
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param migrationId: the unique identifier of the migration
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return organization migration as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#get-an-organization-migration-status">
     * Get an organization migration status</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/migrations/{migration_id}")
    public <T> T getOrganizationMigration(String org, long migrationId, ReturnFormat format) throws IOException {
        return returnMigration(sendGetRequest(ORGS_PATH + org + MIGRATIONS_PATH + "/" + migrationId),
                format);
    }

    /**
     * Method to get the status of a migration
     *
     * @param org:         the organization where fetch the status of a migration
     * @param migrationId: the unique identifier of the migration
     * @param exclude:     exclude attributes from the API response to improve performance
     * @return organization migration as {@link Migration} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#get-an-organization-migration-status">
     * Get an organization migration status</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/migrations/{migration_id}")
    public <T> Migration getOrganizationMigration(Organization org, long migrationId, T exclude) throws IOException {
        return (Migration) getOrganizationMigration(org.getLogin(), migrationId, exclude, LIBRARY_OBJECT);
    }

    /**
     * Method to get the status of a migration
     *
     * @param org:         the organization where fetch the status of a migration
     * @param migrationId: the unique identifier of the migration
     * @param exclude:     exclude attributes from the API response to improve performance
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return organization migration as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#get-an-organization-migration-status">
     * Get an organization migration status</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/migrations/{migration_id}")
    public <T> T getOrganizationMigration(Organization org, long migrationId, T exclude, ReturnFormat format) throws IOException {
        return getOrganizationMigration(org.getLogin(), migrationId, exclude, format);
    }

    /**
     * Method to get the status of a migration
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param migrationId: the unique identifier of the migration
     * @param exclude:     exclude attributes from the API response to improve performance
     * @return organization migration as {@link Migration} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#get-an-organization-migration-status">
     * Get an organization migration status</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/migrations/{migration_id}")
    public <T> Migration getOrganizationMigration(String org, long migrationId, T exclude) throws IOException {
        return (Migration) getOrganizationMigration(org, migrationId, exclude, LIBRARY_OBJECT);
    }

    /**
     * Method to get the status of a migration
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param migrationId: the unique identifier of the migration
     * @param exclude:     exclude attributes from the API response to improve performance
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return organization migration as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#get-an-organization-migration-status">
     * Get an organization migration status</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/migrations/{migration_id}")
    public <T> T getOrganizationMigration(String org, long migrationId, T exclude, ReturnFormat format) throws IOException {
        return returnMigration(sendGetRequest(ORGS_PATH + org + MIGRATIONS_PATH + "/" + migrationId
                + "?exclude=" + exclude), format);
    }

    /**
     * Method to fetch the URL to a migration archive and download it
     *
     * @param org:         the organization where fetch the URL
     * @param migration:   the migration where fetch the URL
     * @param archiveName: path name for the archive file
     * @param save:        flag whether save the file, if is set to {@code "false"} will be created a temporary file
     *                     that will be deleted on exit
     * @return migration archive as {@link File}
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#download-an-organization-migration-archive">
     * Download an organization migration archive</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/migrations/{migration_id}/archive")
    public File downloadOrganizationMigrationArchive(Organization org, Migration migration,
                                                     String archiveName, boolean save) {
        return downloadOrganizationMigrationArchive(org.getLogin(), migration.getId(), archiveName, save);
    }

    /**
     * Method to fetch the URL to a migration archive and download it
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param migration:   the migration where fetch the URL
     * @param archiveName: path name for the archive file
     * @param save:        flag whether save the file, if is set to {@code "false"} will be created a temporary file
     *                     that will be deleted on exit
     * @return migration archive as {@link File}
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#download-an-organization-migration-archive">
     * Download an organization migration archive</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/migrations/{migration_id}/archive")
    public File downloadOrganizationMigrationArchive(String org, Migration migration,
                                                     String archiveName, boolean save) {
        return downloadOrganizationMigrationArchive(org, migration.getId(), archiveName, save);
    }

    /**
     * Method to fetch the URL to a migration archive and download it
     *
     * @param org:         the organization where fetch the URL
     * @param migrationId: the unique identifier of the migration
     * @param archiveName: path name for the archive file
     * @param save:        flag whether save the file, if is set to {@code "false"} will be created a temporary file
     *                     that will be deleted on exit
     * @return migration archive as {@link File}
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#download-an-organization-migration-archive">
     * Download an organization migration archive</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/migrations/{migration_id}/archive")
    public File downloadOrganizationMigrationArchive(Organization org, long migrationId, String archiveName,
                                                     boolean save) {
        return downloadOrganizationMigrationArchive(org.getLogin(), migrationId, archiveName, save);
    }

    /**
     * Method to fetch the URL to a migration archive and download it
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param migrationId: the unique identifier of the migration
     * @param archiveName: path name for the archive file
     * @param save:        flag whether save the file, if is set to {@code "false"} will be created a temporary file
     *                     that will be deleted on exit
     * @return migration archive as {@link File}
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#download-an-organization-migration-archive">
     * Download an organization migration archive</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/migrations/{migration_id}/archive")
    public File downloadOrganizationMigrationArchive(String org, long migrationId, String archiveName, boolean save) {
        try {
            String archiveUrl = sendGetRequest(ORGS_PATH + org + MIGRATIONS_PATH + "/" + migrationId
                    + ARCHIVE_PATH);
            if (apiRequest.getResponseStatusCode() != 302) {
                printErrorResponse();
                return null;
            }
            return downloadFile(archiveUrl, archiveName, save);
        } catch (IOException e) {
            printErrorResponse();
            return null;
        }
    }

    /**
     * Method to delete a previous migration archive. <br>
     * Migration archives are automatically deleted after seven days
     *
     * @param org:       the organization where delete the archive
     * @param migration: the migration where delete the archive
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#delete-an-organization-migration-archive">
     * Delete an organization migration archive</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/migrations/{migration_id}/archive")
    public boolean deleteOrganizationMigrationArchive(Organization org, Migration migration) {
        return deleteOrganizationMigrationArchive(org.getLogin(), migration.getId());
    }

    /**
     * Method to delete a previous migration archive. <br>
     * Migration archives are automatically deleted after seven days
     *
     * @param org:       the organization name. The name is not case-sensitive
     * @param migration: the migration where delete the archive
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#delete-an-organization-migration-archive">
     * Delete an organization migration archive</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/migrations/{migration_id}/archive")
    public boolean deleteOrganizationMigrationArchive(String org, Migration migration) {
        return deleteOrganizationMigrationArchive(org, migration.getId());
    }

    /**
     * Method to delete a previous migration archive. <br>
     * Migration archives are automatically deleted after seven days
     *
     * @param org:         the organization where delete the archive
     * @param migrationId: the unique identifier of the migration
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#delete-an-organization-migration-archive">
     * Delete an organization migration archive</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/migrations/{migration_id}/archive")
    public boolean deleteOrganizationMigrationArchive(Organization org, long migrationId) {
        return deleteOrganizationMigrationArchive(org.getLogin(), migrationId);
    }

    /**
     * Method to delete a previous migration archive. <br>
     * Migration archives are automatically deleted after seven days
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param migrationId: the unique identifier of the migration
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#delete-an-organization-migration-archive">
     * Delete an organization migration archive</a>
     **/
    @RequestPath(method = DELETE, path = "/orgs/{org}/migrations/{migration_id}/archive")
    public boolean deleteOrganizationMigrationArchive(String org, long migrationId) {
        try {
            sendDeleteRequest(ORGS_PATH + org + MIGRATIONS_PATH + "/" + migrationId + ARCHIVE_PATH);
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
     * Method to unlock a repository that was locked for migration. <br>
     * You should unlock each migrated repository and delete them when the migration is complete and you no longer need
     * the source data
     *
     * @param org:        the organization where unlock the repository
     * @param migration:  the migration where unlock the repository
     * @param repository: the repository to unlock
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#unlock-an-organization-repository">
     * Unlock an organization repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/migrations/{migration_id}/repos/{repo_name}/lock")
    public boolean unlockOrganizationRepository(Organization org, Migration migration, Repository repository) {
        return unlockOrganizationRepository(org.getLogin(), migration.getId(), repository.getName());
    }

    /**
     * Method to unlock a repository that was locked for migration. <br>
     * You should unlock each migrated repository and delete them when the migration is complete and you no longer need
     * the source data
     *
     * @param org:         the organization where unlock the repository
     * @param migrationId: the unique identifier of the migration
     * @param repository:  the repository to unlock
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#unlock-an-organization-repository">
     * Unlock an organization repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/migrations/{migration_id}/repos/{repo_name}/lock")
    public boolean unlockOrganizationRepository(Organization org, long migrationId, Repository repository) {
        return unlockOrganizationRepository(org.getLogin(), migrationId, repository.getName());
    }

    /**
     * Method to unlock a repository that was locked for migration. <br>
     * You should unlock each migrated repository and delete them when the migration is complete and you no longer need
     * the source data
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param migrationId: the unique identifier of the migration
     * @param repository:  the repository to unlock
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#unlock-an-organization-repository">
     * Unlock an organization repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/migrations/{migration_id}/repos/{repo_name}/lock")
    public boolean unlockOrganizationRepository(String org, long migrationId, Repository repository) {
        return unlockOrganizationRepository(org, migrationId, repository.getName());
    }

    /**
     * Method to unlock a repository that was locked for migration. <br>
     * You should unlock each migrated repository and delete them when the migration is complete and you no longer need
     * the source data
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param migration:  the migration where unlock the repository
     * @param repository: the repository to unlock
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#unlock-an-organization-repository">
     * Unlock an organization repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/migrations/{migration_id}/repos/{repo_name}/lock")
    public boolean unlockOrganizationRepository(String org, Migration migration, Repository repository) {
        return unlockOrganizationRepository(org, migration.getId(), repository.getName());
    }

    /**
     * Method to unlock a repository that was locked for migration. <br>
     * You should unlock each migrated repository and delete them when the migration is complete and you no longer need
     * the source data
     *
     * @param org:       the organization name. The name is not case-sensitive
     * @param migration: the migration where unlock the repository
     * @param repoName:  repo_name parameter
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#unlock-an-organization-repository">
     * Unlock an organization repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/migrations/{migration_id}/repos/{repo_name}/lock")
    public boolean unlockOrganizationRepository(String org, Migration migration, String repoName) {
        return unlockOrganizationRepository(org, migration.getId(), repoName);
    }

    /**
     * Method to unlock a repository that was locked for migration. <br>
     * You should unlock each migrated repository and delete them when the migration is complete and you no longer need
     * the source data
     *
     * @param org:       the organization where unlock the repository
     * @param migration: the migration where unlock the repository
     * @param repoName:  repo_name parameter
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#unlock-an-organization-repository">
     * Unlock an organization repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/migrations/{migration_id}/repos/{repo_name}/lock")
    public boolean unlockOrganizationRepository(Organization org, Migration migration, String repoName) {
        return unlockOrganizationRepository(org.getLogin(), migration.getId(), repoName);
    }

    /**
     * Method to unlock a repository that was locked for migration. <br>
     * You should unlock each migrated repository and delete them when the migration is complete and you no longer need
     * the source data
     *
     * @param org:         the organization where unlock the repository
     * @param migrationId: the unique identifier of the migration
     * @param repoName:    repo_name parameter
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#unlock-an-organization-repository">
     * Unlock an organization repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/migrations/{migration_id}/repos/{repo_name}/lock")
    public boolean unlockOrganizationRepository(Organization org, long migrationId, String repoName) {
        return unlockOrganizationRepository(org.getLogin(), migrationId, repoName);
    }

    /**
     * Method to unlock a repository that was locked for migration. <br>
     * You should unlock each migrated repository and delete them when the migration is complete and you no longer need
     * the source data
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param migrationId: the unique identifier of the migration
     * @param repoName:    repo_name parameter
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#unlock-an-organization-repository">
     * Unlock an organization repository</a>
     **/
    @RequestPath(method = DELETE, path = "/orgs/{org}/migrations/{migration_id}/repos/{repo_name}/lock")
    public boolean unlockOrganizationRepository(String org, long migrationId, String repoName) {
        try {
            sendDeleteRequest(ORGS_PATH + org + MIGRATIONS_PATH + "/" + migrationId + "/" + REPOS_PATH
                    + repoName + LOCK_PATH);
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
     * Method to get the list of the all the repositories for this organization migration
     *
     * @param org:       the organization from fetch the list
     * @param migration: the migration from fetch the list
     * @return repositories in an organization migration as {@link ArrayList} of {@link CompleteRepository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#list-repositories-in-an-organization-migration">
     * List repositories in an organization migration</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/migrations/{migration_id}/repositories")
    public ArrayList<CompleteRepository> getOrganizationMigrationRepositories(Organization org,
                                                                              Migration migration) throws IOException {
        return getOrganizationMigrationRepositories(org.getLogin(), migration.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all the repositories for this organization migration
     *
     * @param org:       the organization from fetch the list
     * @param migration: the migration from fetch the list
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return repositories in an organization migration as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#list-repositories-in-an-organization-migration">
     * List repositories in an organization migration</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/migrations/{migration_id}/repositories")
    public <T> T getOrganizationMigrationRepositories(Organization org, Migration migration,
                                                      ReturnFormat format) throws IOException {
        return getOrganizationMigrationRepositories(org.getLogin(), migration.getId(), format);
    }

    /**
     * Method to get the list of the all the repositories for this organization migration
     *
     * @param org:       the organization name. The name is not case-sensitive
     * @param migration: the migration from fetch the list
     * @return repositories in an organization migration as {@link ArrayList} of {@link CompleteRepository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#list-repositories-in-an-organization-migration">
     * List repositories in an organization migration</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/migrations/{migration_id}/repositories")
    public ArrayList<CompleteRepository> getOrganizationMigrationRepositories(String org,
                                                                              Migration migration) throws IOException {
        return getOrganizationMigrationRepositories(org, migration.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all the repositories for this organization migration
     *
     * @param org:       the organization name. The name is not case-sensitive
     * @param migration: the migration from fetch the list
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return repositories in an organization migration as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#list-repositories-in-an-organization-migration">
     * List repositories in an organization migration</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/migrations/{migration_id}/repositories")
    public <T> T getOrganizationMigrationRepositories(String org, Migration migration,
                                                      ReturnFormat format) throws IOException {
        return getOrganizationMigrationRepositories(org, migration.getId(), format);
    }

    /**
     * Method to get the list of the all the repositories for this organization migration
     *
     * @param org:         the organization from fetch the list
     * @param migrationId: the unique identifier of the migration
     * @return repositories in an organization migration as {@link ArrayList} of {@link CompleteRepository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#list-repositories-in-an-organization-migration">
     * List repositories in an organization migration</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/migrations/{migration_id}/repositories")
    public ArrayList<CompleteRepository> getOrganizationMigrationRepositories(Organization org,
                                                                              long migrationId) throws IOException {
        return getOrganizationMigrationRepositories(org.getLogin(), migrationId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all the repositories for this organization migration
     *
     * @param org:         the organization from fetch the list
     * @param migrationId: the unique identifier of the migration
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return repositories in an organization migration as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#list-repositories-in-an-organization-migration">
     * List repositories in an organization migration</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/migrations/{migration_id}/repositories")
    public <T> T getOrganizationMigrationRepositories(Organization org, long migrationId,
                                                      ReturnFormat format) throws IOException {
        return getOrganizationMigrationRepositories(org.getLogin(), migrationId, format);
    }

    /**
     * Method to get the list of the all the repositories for this organization migration
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param migrationId: the unique identifier of the migration
     * @return repositories in an organization migration as {@link ArrayList} of {@link CompleteRepository} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#list-repositories-in-an-organization-migration">
     * List repositories in an organization migration</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/migrations/{migration_id}/repositories")
    public ArrayList<CompleteRepository> getOrganizationMigrationRepositories(String org, long migrationId) throws IOException {
        return getOrganizationMigrationRepositories(org, migrationId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all the repositories for this organization migration
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param migrationId: the unique identifier of the migration
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return repositories in an organization migration as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/orgs#list-repositories-in-an-organization-migration">
     * List repositories in an organization migration</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/migrations/{migration_id}/repositories")
    public <T> T getOrganizationMigrationRepositories(String org, long migrationId, ReturnFormat format) throws IOException {
        return returnCompleteRepositoriesList(sendGetRequest(ORGS_PATH + org + MIGRATIONS_PATH + "/"
                + migrationId + REPOSITORIES_PATH), format);
    }

}
