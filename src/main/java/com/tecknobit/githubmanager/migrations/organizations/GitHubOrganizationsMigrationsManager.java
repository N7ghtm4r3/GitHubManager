package com.tecknobit.githubmanager.migrations.organizations;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.migrations.organizations.records.OrganizationMigration;
import com.tecknobit.githubmanager.records.organization.Organization;
import com.tecknobit.githubmanager.records.repository.RepositoriesList;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.downloadFile;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.issues.issues.GitHubIssuesManager.LOCK_PATH;

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

    public ArrayList<OrganizationMigration> getOrganizationMigrations(Organization org) throws IOException {
        return getOrganizationMigrations(org.getLogin(), LIBRARY_OBJECT);
    }

    public <T> T getOrganizationMigrations(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationMigrations(org.getLogin(), format);
    }

    public ArrayList<OrganizationMigration> getOrganizationMigrations(String org) throws IOException {
        return getOrganizationMigrations(org, LIBRARY_OBJECT);
    }

    public <T> T getOrganizationMigrations(String org, ReturnFormat format) throws IOException {
        return returnOrganizationMigrations(sendGetRequest(ORGS_PATH + org + MIGRATIONS_PATH), format);
    }

    public ArrayList<OrganizationMigration> getOrganizationMigrations(Organization org, Params queryParams) throws IOException {
        return getOrganizationMigrations(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    public <T> T getOrganizationMigrations(Organization org, Params queryParams, ReturnFormat format) throws IOException {
        return getOrganizationMigrations(org.getLogin(), queryParams, format);
    }

    public ArrayList<OrganizationMigration> getOrganizationMigrations(String org, Params queryParams) throws IOException {
        return getOrganizationMigrations(org, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getOrganizationMigrations(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnOrganizationMigrations(sendGetRequest(ORGS_PATH + org + MIGRATIONS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create an organization migrations list
     *
     * @param organizationMigrationsResponse : obtained from GitHub's response
     * @param format                         :              return type formatter -> {@link ReturnFormat}
     * @return organization migrations list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnOrganizationMigrations(String organizationMigrationsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(organizationMigrationsResponse);
            case LIBRARY_OBJECT:
                ArrayList<OrganizationMigration> organizationMigrations = new ArrayList<>();
                JSONArray jMigrations = new JSONArray(organizationMigrationsResponse);
                for (int j = 0; j < jMigrations.length(); j++)
                    organizationMigrations.add(new OrganizationMigration(jMigrations.getJSONObject(j)));
                return (T) organizationMigrations;
            default:
                return (T) organizationMigrationsResponse;
        }
    }

    public OrganizationMigration startOrganizationMigration(Organization org, RepositoriesList repositories) throws IOException {
        return startOrganizationMigration(org.getLogin(), LIBRARY_OBJECT, repositories.getNames().toArray(new String[0]));
    }

    public <T> T startOrganizationMigration(Organization org, RepositoriesList repositories,
                                            ReturnFormat format) throws IOException {
        return startOrganizationMigration(org.getLogin(), format, repositories.getNames().toArray(new String[0]));
    }

    public OrganizationMigration startOrganizationMigration(String org, RepositoriesList repositories) throws IOException {
        return startOrganizationMigration(org, LIBRARY_OBJECT, repositories.getNames().toArray(new String[0]));
    }

    public <T> T startOrganizationMigration(String org, RepositoriesList repositories, ReturnFormat format) throws IOException {
        return startOrganizationMigration(org, format, repositories.getNames().toArray(new String[0]));
    }

    public OrganizationMigration startOrganizationMigration(Organization org, String... repositories) throws IOException {
        return startOrganizationMigration(org.getLogin(), LIBRARY_OBJECT, repositories);
    }

    public <T> T startOrganizationMigration(Organization org, ReturnFormat format, String... repositories) throws IOException {
        return startOrganizationMigration(org.getLogin(), format, repositories);
    }

    public OrganizationMigration startOrganizationMigration(String org, String... repositories) throws IOException {
        return startOrganizationMigration(org, LIBRARY_OBJECT, repositories);
    }

    public <T> T startOrganizationMigration(String org, ReturnFormat format, String... repositories) throws IOException {
        return startOrganizationMigration(org, null, format, repositories);
    }

    public OrganizationMigration startOrganizationMigration(Organization org, RepositoriesList repositories,
                                                            Params bodyParams) throws IOException {
        return startOrganizationMigration(org.getLogin(), bodyParams, LIBRARY_OBJECT,
                repositories.getNames().toArray(new String[0]));
    }

    public <T> T startOrganizationMigration(Organization org, RepositoriesList repositories, Params bodyParams,
                                            ReturnFormat format) throws IOException {
        return startOrganizationMigration(org.getLogin(), bodyParams, format,
                repositories.getNames().toArray(new String[0]));
    }

    public OrganizationMigration startOrganizationMigration(String org, RepositoriesList repositories,
                                                            Params bodyParams) throws IOException {
        return startOrganizationMigration(org, bodyParams, LIBRARY_OBJECT, repositories.getNames().toArray(new String[0]));
    }

    public <T> T startOrganizationMigration(String org, RepositoriesList repositories, Params bodyParams,
                                            ReturnFormat format) throws IOException {
        return startOrganizationMigration(org, bodyParams, format, repositories.getNames().toArray(new String[0]));
    }

    public OrganizationMigration startOrganizationMigration(Organization org, Params bodyParams,
                                                            String... repositories) throws IOException {
        return startOrganizationMigration(org.getLogin(), bodyParams, LIBRARY_OBJECT, repositories);
    }

    public <T> T startOrganizationMigration(Organization org, Params bodyParams, ReturnFormat format,
                                            String... repositories) throws IOException {
        return startOrganizationMigration(org.getLogin(), bodyParams, format, repositories);
    }

    public OrganizationMigration startOrganizationMigration(String org, Params bodyParams,
                                                            String... repositories) throws IOException {
        return startOrganizationMigration(org, bodyParams, LIBRARY_OBJECT, repositories);
    }

    public <T> T startOrganizationMigration(String org, Params bodyParams, ReturnFormat format,
                                            String... repositories) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("repositories", repositories);
        return returnOrganizationMigration(sendPostRequest(ORGS_PATH + org + MIGRATIONS_PATH, bodyParams),
                format);
    }

    public OrganizationMigration getOrganizationMigration(Organization org, long migrationId) throws IOException {
        return getOrganizationMigration(org.getLogin(), migrationId, LIBRARY_OBJECT);
    }

    public <T> T getOrganizationMigration(Organization org, long migrationId, ReturnFormat format) throws IOException {
        return getOrganizationMigration(org.getLogin(), migrationId, format);
    }

    public OrganizationMigration getOrganizationMigration(String org, long migrationId) throws IOException {
        return getOrganizationMigration(org, migrationId, LIBRARY_OBJECT);
    }

    public <T> T getOrganizationMigration(String org, long migrationId, ReturnFormat format) throws IOException {
        return returnOrganizationMigration(sendGetRequest(ORGS_PATH + org + MIGRATIONS_PATH + "/" + migrationId),
                format);
    }

    public <T> OrganizationMigration getOrganizationMigration(Organization org, long migrationId,
                                                              T exclude) throws IOException {
        return (OrganizationMigration) getOrganizationMigration(org.getLogin(), migrationId, exclude, LIBRARY_OBJECT);
    }

    public <T> T getOrganizationMigration(Organization org, long migrationId, T exclude,
                                          ReturnFormat format) throws IOException {
        return getOrganizationMigration(org.getLogin(), migrationId, exclude, format);
    }

    public <T> OrganizationMigration getOrganizationMigration(String org, long migrationId, T exclude) throws IOException {
        return (OrganizationMigration) getOrganizationMigration(org, migrationId, exclude, LIBRARY_OBJECT);
    }

    public <T> T getOrganizationMigration(String org, long migrationId, T exclude, ReturnFormat format) throws IOException {
        return returnOrganizationMigration(sendGetRequest(ORGS_PATH + org + MIGRATIONS_PATH + "/" + migrationId
                + "?exclude=" + exclude), format);
    }

    /**
     * Method to create an organization migration
     *
     * @param organizationMigrationResponse : obtained from GitHub's response
     * @param format                        :              return type formatter -> {@link ReturnFormat}
     * @return organization migration as {@code "format"} defines
     **/
    @Returner
    private <T> T returnOrganizationMigration(String organizationMigrationResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(organizationMigrationResponse);
            case LIBRARY_OBJECT:
                return (T) new OrganizationMigration(new JSONObject(organizationMigrationResponse));
            default:
                return (T) organizationMigrationResponse;
        }
    }

    public File downloadOrganizationMigrationArchive(Organization org, OrganizationMigration migration,
                                                     String archiveName, boolean save) {
        return downloadOrganizationMigrationArchive(org.getLogin(), migration.getId(), archiveName, save);
    }

    public File downloadOrganizationMigrationArchive(String org, OrganizationMigration migration,
                                                     String archiveName, boolean save) {
        return downloadOrganizationMigrationArchive(org, migration.getId(), archiveName, save);
    }

    public File downloadOrganizationMigrationArchive(Organization org, long migrationId, String archiveName,
                                                     boolean save) {
        return downloadOrganizationMigrationArchive(org.getLogin(), migrationId, archiveName, save);
    }

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

    public boolean deleteOrganizationMigrationArchive(Organization org, OrganizationMigration migration) {
        return deleteOrganizationMigrationArchive(org.getLogin(), migration.getId());
    }

    public boolean deleteOrganizationMigrationArchive(String org, OrganizationMigration migration) {
        return deleteOrganizationMigrationArchive(org, migration.getId());
    }

    public boolean deleteOrganizationMigrationArchive(Organization org, long migrationId) {
        return deleteOrganizationMigrationArchive(org.getLogin(), migrationId);
    }

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

    public boolean unlockOrganizationRepository(Organization org, OrganizationMigration migration, Repository repository) {
        return unlockOrganizationRepository(org.getLogin(), migration.getId(), repository.getName());
    }

    public boolean unlockOrganizationRepository(Organization org, long migrationId, Repository repository) {
        return unlockOrganizationRepository(org.getLogin(), migrationId, repository.getName());
    }

    public boolean unlockOrganizationRepository(String org, long migrationId, Repository repository) {
        return unlockOrganizationRepository(org, migrationId, repository.getName());
    }

    public boolean unlockOrganizationRepository(String org, OrganizationMigration migration, Repository repository) {
        return unlockOrganizationRepository(org, migration.getId(), repository.getName());
    }

    public boolean unlockOrganizationRepository(String org, OrganizationMigration migration, String repoName) {
        return unlockOrganizationRepository(org, migration.getId(), repoName);
    }

    public boolean unlockOrganizationRepository(Organization org, OrganizationMigration migration, String repoName) {
        return unlockOrganizationRepository(org.getLogin(), migration.getId(), repoName);
    }

    public boolean unlockOrganizationRepository(Organization org, long migrationId, String repoName) {
        return unlockOrganizationRepository(org.getLogin(), migrationId, repoName);
    }

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

}
