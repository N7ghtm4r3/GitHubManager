package com.tecknobit.githubmanager.repositories.repositories;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.organizations.organizations.records.Organization;
import com.tecknobit.githubmanager.records.organization.Team;
import com.tecknobit.githubmanager.records.parents.User;
import com.tecknobit.githubmanager.repositories.repositories.records.CodeOwnersError;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import com.tecknobit.githubmanager.repositories.repositories.records.RepositoryLanguages;
import com.tecknobit.githubmanager.repositories.repositories.records.RepositoryTag;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.actions.workflow.GitHubWorkflowsManager.DISPATCHES_PATH;
import static com.tecknobit.githubmanager.records.organization.Team.returnTeamsList;
import static com.tecknobit.githubmanager.records.parents.GitHubResponse.returnStringsList;
import static com.tecknobit.githubmanager.records.parents.User.returnUsersList;
import static com.tecknobit.githubmanager.repositories.repositories.records.RepositoriesList.returnRepositoriesList;

/**
 * The {@code GitHubRepositoriesManager} class is useful to manage all GitHub's repositories endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos">
 * Repositories</a>
 * @see GitHubManager
 **/
public class GitHubRepositoriesManager extends GitHubManager {

    /**
     * {@code REPOS_QUERY_PATH} constant for {@code "/repos"} path
     **/
    public static final String REPOS_QUERY_PATH = "/repos";

    /**
     * {@code AUTOMATED_SECURITY_FIXES_PATH} constant for {@code "/automated-security-fixes"} path
     **/
    public static final String AUTOMATED_SECURITY_FIXES_PATH = "/automated-security-fixes";

    /**
     * {@code CODEOWNERS_ERRORS_PATH} constant for {@code "/codeowners/errors"} path
     **/
    public static final String CODEOWNERS_ERRORS_PATH = "/codeowners/errors";

    /**
     * {@code CONTRIBUTORS_PATH} constant for {@code "/contributors"} path
     **/
    public static final String CONTRIBUTORS_PATH = "/contributors";

    /**
     * {@code LANGUAGES_PATH} constant for {@code "/languages"} path
     **/
    public static final String LANGUAGES_PATH = "/languages";

    /**
     * {@code TOPICS_PATH} constant for {@code "/topics"} path
     **/
    public static final String TOPICS_PATH = "/topics";

    /**
     * {@code TRANSFER_PATH} constant for {@code "/transfer"} path
     **/
    public static final String TRANSFER_PATH = "/transfer";

    /**
     * {@code VULNERABILITY_ALERTS_PATH} constant for {@code "/vulnerability-alerts"} path
     **/
    public static final String VULNERABILITY_ALERTS_PATH = "/vulnerability-alerts";

    /**
     * {@code GENERATE_PATH} constant for {@code "/generate"} path
     **/
    public static final String GENERATE_PATH = "/generate";

    /**
     * {@code USER_REPOS_PATH} constant for {@code "user/repos"} path
     **/
    public static final String USER_REPOS_PATH = USER_PATH + REPOS_QUERY_PATH;

    /**
     * Constructor to init a {@link GitHubRepositoriesManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubRepositoriesManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubRepositoriesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubRepositoriesManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubRepositoriesManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubRepositoriesManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubRepositoriesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubRepositoriesManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubRepositoriesManager} <br>
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
    public GitHubRepositoriesManager() {
        super();
    }

    public ArrayList<Repository> getOrganizationRepositories(Organization org) throws IOException {
        return getOrganizationRepositories(org.getLogin(), LIBRARY_OBJECT);
    }

    public <T> T getOrganizationRepositories(Organization org, ReturnFormat format) throws IOException {
        return getOrganizationRepositories(org.getLogin(), format);
    }

    public ArrayList<Repository> getOrganizationRepositories(String org) throws IOException {
        return getOrganizationRepositories(org, LIBRARY_OBJECT);
    }

    public <T> T getOrganizationRepositories(String org, ReturnFormat format) throws IOException {
        return returnRepositories(sendGetRequest(ORGS_PATH + org + REPOS_QUERY_PATH), format);
    }

    public ArrayList<Repository> getOrganizationRepositories(Organization org, Params queryParams) throws IOException {
        return getOrganizationRepositories(org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    public <T> T getOrganizationRepositories(Organization org, Params queryParams, ReturnFormat format) throws IOException {
        return getOrganizationRepositories(org.getLogin(), queryParams, format);
    }

    public ArrayList<Repository> getOrganizationRepositories(String org, Params queryParams) throws IOException {
        return getOrganizationRepositories(org, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getOrganizationRepositories(String org, Params queryParams, ReturnFormat format) throws IOException {
        return returnRepositories(sendGetRequest(ORGS_PATH + org + REPOS_QUERY_PATH
                + queryParams.createQueryString()), format);
    }

    public Repository createOrganizationRepository(Organization org, String name) throws IOException {
        return createOrganizationRepository(org.getLogin(), name, LIBRARY_OBJECT);
    }

    public <T> T createOrganizationRepository(Organization org, String name, ReturnFormat format) throws IOException {
        return createOrganizationRepository(org.getLogin(), name, format);
    }

    public Repository createOrganizationRepository(String org, String name) throws IOException {
        return createOrganizationRepository(org, name, LIBRARY_OBJECT);
    }

    public <T> T createOrganizationRepository(String org, String name, ReturnFormat format) throws IOException {
        return createOrganizationRepository(org, name, null, format);
    }

    public Repository createOrganizationRepository(Organization org, String name, Params bodyParams) throws IOException {
        return createOrganizationRepository(org.getLogin(), name, bodyParams, LIBRARY_OBJECT);
    }

    public <T> T createOrganizationRepository(Organization org, String name, Params bodyParams,
                                              ReturnFormat format) throws IOException {
        return createOrganizationRepository(org.getLogin(), name, bodyParams, format);
    }

    public Repository createOrganizationRepository(String org, String name, Params bodyParams) throws IOException {
        return createOrganizationRepository(org, name, bodyParams, LIBRARY_OBJECT);
    }

    public <T> T createOrganizationRepository(String org, String name, Params bodyParams,
                                              ReturnFormat format) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("name", name);
        return returnRepository(sendPostRequest(ORGS_PATH + org + REPOS_QUERY_PATH, bodyParams), format);
    }

    public Repository getRepository(String owner, String repo) throws IOException {
        return getRepository(owner, repo, LIBRARY_OBJECT);
    }

    public <T> T getRepository(String owner, String repo, ReturnFormat format) throws IOException {
        return returnRepository(sendGetRequest(REPOS_PATH + owner + "/" + repo), format);
    }

    public Repository updateRepository(Repository repository, Params bodyParams) throws IOException {
        return updateRepository(repository.getOwner().getLogin(), repository.getName(), bodyParams, LIBRARY_OBJECT);
    }

    public <T> T updateRepository(Repository repository, Params bodyParams, ReturnFormat format) throws IOException {
        return updateRepository(repository.getOwner().getLogin(), repository.getName(), bodyParams, format);
    }

    public Repository updateRepository(String owner, String repo, Params bodyParams) throws IOException {
        return updateRepository(owner, repo, bodyParams, LIBRARY_OBJECT);
    }

    public <T> T updateRepository(String owner, String repo, Params bodyParams, ReturnFormat format) throws IOException {
        return returnRepository(sendPatchRequest(REPOS_PATH + owner + "/" + repo, bodyParams), format);
    }

    public boolean deleteRepository(Repository repository) {
        return deleteRepository(repository.getOwner().getLogin(), repository.getName());
    }

    public boolean deleteRepository(String owner, String repo) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo);
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

    public boolean enableAutomatedSecurityFixes(Repository repository) {
        return enableAutomatedSecurityFixes(repository.getOwner().getLogin(), repository.getName());
    }

    public boolean enableAutomatedSecurityFixes(String owner, String repo) {
        try {
            sendPutRequest(REPOS_PATH + owner + "/" + repo + AUTOMATED_SECURITY_FIXES_PATH, null);
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

    public boolean disableAutomatedSecurityFixes(Repository repository) {
        return disableAutomatedSecurityFixes(repository.getOwner().getLogin(), repository.getName());
    }

    public boolean disableAutomatedSecurityFixes(String owner, String repo) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + AUTOMATED_SECURITY_FIXES_PATH);
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

    public ArrayList<CodeOwnersError> getCodeOwnersErrors(Repository repository) throws IOException {
        return getCodeOwnersErrors(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    public <T> T getCodeOwnersErrors(Repository repository, ReturnFormat format) throws IOException {
        return getCodeOwnersErrors(repository.getOwner().getLogin(), repository.getName(), format);
    }

    public ArrayList<CodeOwnersError> getCodeOwnersErrors(String owner, String repo) throws IOException {
        return getCodeOwnersErrors(owner, repo, LIBRARY_OBJECT);
    }

    public <T> T getCodeOwnersErrors(String owner, String repo, ReturnFormat format) throws IOException {
        return getCodeOwnersErrors(owner, repo, null, format);
    }

    public ArrayList<CodeOwnersError> getCodeOwnersErrors(Repository repository, String ref) throws IOException {
        return getCodeOwnersErrors(repository.getOwner().getLogin(), repository.getName(), ref, LIBRARY_OBJECT);
    }

    public <T> T getCodeOwnersErrors(Repository repository, String ref, ReturnFormat format) throws IOException {
        return getCodeOwnersErrors(repository.getOwner().getLogin(), repository.getName(), ref, format);
    }

    public ArrayList<CodeOwnersError> getCodeOwnersErrors(String owner, String repo, String ref) throws IOException {
        return getCodeOwnersErrors(owner, repo, ref, LIBRARY_OBJECT);
    }

    public <T> T getCodeOwnersErrors(String owner, String repo, String ref, ReturnFormat format) throws IOException {
        String reqUrl = REPOS_PATH + owner + "/" + repo + CODEOWNERS_ERRORS_PATH;
        if (ref != null)
            reqUrl += "?ref=" + ref;
        String codeOwnersErrorsResponse = sendGetRequest(reqUrl);
        switch (format) {
            case JSON:
                return (T) new JSONObject(codeOwnersErrorsResponse);
            case LIBRARY_OBJECT:
                ArrayList<CodeOwnersError> codeOwnersErrors = new ArrayList<>();
                JSONArray jCodeOwnersErrors = new JSONObject(codeOwnersErrorsResponse).getJSONArray("errors");
                for (int j = 0; j < jCodeOwnersErrors.length(); j++)
                    codeOwnersErrors.add(new CodeOwnersError(jCodeOwnersErrors.getJSONObject(j)));
                return (T) codeOwnersErrors;
            default:
                return (T) codeOwnersErrorsResponse;
        }
    }

    public ArrayList<User> getRepositoryContributors(Repository repository) throws IOException {
        return getRepositoryContributors(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    public <T> T getRepositoryContributors(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryContributors(repository.getOwner().getLogin(), repository.getName(), format);
    }

    public ArrayList<User> getRepositoryContributors(String owner, String repo) throws IOException {
        return getRepositoryContributors(owner, repo, LIBRARY_OBJECT);
    }

    public <T> T getRepositoryContributors(String owner, String repo, ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(REPOS_PATH + owner + "/" + repo + CONTRIBUTORS_PATH), format);
    }

    public ArrayList<User> getRepositoryContributors(Repository repository, Params queryParams) throws IOException {
        return getRepositoryContributors(repository.getOwner().getLogin(), repository.getName(), queryParams,
                LIBRARY_OBJECT);
    }

    public <T> T getRepositoryContributors(Repository repository, Params queryParams,
                                           ReturnFormat format) throws IOException {
        return getRepositoryContributors(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    public ArrayList<User> getRepositoryContributors(String owner, String repo, Params queryParams) throws IOException {
        return getRepositoryContributors(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getRepositoryContributors(String owner, String repo, Params queryParams,
                                           ReturnFormat format) throws IOException {
        return returnUsersList(sendGetRequest(REPOS_PATH + owner + "/" + repo + CONTRIBUTORS_PATH
                + queryParams.createQueryString()), format);
    }

    public boolean createRepositoryDispatchEvent(Repository repository, String eventType) {
        return createRepositoryDispatchEvent(repository.getOwner().getLogin(), repository.getName(), eventType);
    }

    public boolean createRepositoryDispatchEvent(String owner, String repo, String eventType) {
        return createRepositoryDispatchEvent(owner, repo, eventType, null);
    }

    public <T> boolean createRepositoryDispatchEvent(Repository repository, String eventType, T clientPayload) {
        return createRepositoryDispatchEvent(repository.getOwner().getLogin(), repository.getName(), eventType,
                clientPayload);
    }

    public <T> boolean createRepositoryDispatchEvent(String owner, String repo, String eventType, T clientPayload) {
        try {
            Params payload = new Params();
            payload.addParam("event_type", eventType);
            if (clientPayload != null)
                payload.addParam("client_payload", clientPayload);
            sendPostRequest(REPOS_PATH + owner + "/" + repo + DISPATCHES_PATH, payload);
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

    public RepositoryLanguages getRepositoryLanguages(Repository repository) throws IOException {
        return getRepositoryLanguages(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    public <T> T getRepositoryLanguages(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryLanguages(repository.getOwner().getLogin(), repository.getName(), format);
    }

    public RepositoryLanguages getRepositoryLanguages(String owner, String repo) throws IOException {
        return getRepositoryLanguages(owner, repo, LIBRARY_OBJECT);
    }

    public <T> T getRepositoryLanguages(String owner, String repo, ReturnFormat format) throws IOException {
        String repositoryLanguagesResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo + LANGUAGES_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONObject(repositoryLanguagesResponse);
            case LIBRARY_OBJECT:
                return (T) new RepositoryLanguages(new JSONObject(repositoryLanguagesResponse));
            default:
                return (T) repositoryLanguagesResponse;
        }
    }

    public ArrayList<RepositoryTag> getRepositoryTags(Repository repository) throws IOException {
        return getRepositoryTags(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    public <T> T getRepositoryTags(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryTags(repository.getOwner().getLogin(), repository.getName(), format);
    }

    public ArrayList<RepositoryTag> getRepositoryTags(String owner, String repo) throws IOException {
        return getRepositoryTags(owner, repo, LIBRARY_OBJECT);
    }

    public <T> T getRepositoryTags(String owner, String repo, ReturnFormat format) throws IOException {
        return returnRepositoryTags(sendGetRequest(REPOS_PATH + owner + "/" + repo + TAGS_PATH), format);
    }

    public ArrayList<RepositoryTag> getRepositoryTags(Repository repository, Params queryParams) throws IOException {
        return getRepositoryTags(repository.getOwner().getLogin(), repository.getName(), queryParams, LIBRARY_OBJECT);
    }

    public <T> T getRepositoryTags(Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return getRepositoryTags(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    public ArrayList<RepositoryTag> getRepositoryTags(String owner, String repo, Params queryParams) throws IOException {
        return getRepositoryTags(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getRepositoryTags(String owner, String repo, Params queryParams, ReturnFormat format) throws IOException {
        return returnRepositoryTags(sendGetRequest(REPOS_PATH + owner + "/" + repo + TAGS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a repository tags list
     *
     * @param repositoryTagsResponse: obtained from GitHub's response
     * @param format:                 return type formatter -> {@link ReturnFormat}
     * @return repository tags list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnRepositoryTags(String repositoryTagsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(repositoryTagsResponse);
            case LIBRARY_OBJECT:
                ArrayList<RepositoryTag> repositoryTags = new ArrayList<>();
                JSONArray jRepositoryTags = new JSONArray(repositoryTagsResponse);
                for (int j = 0; j < jRepositoryTags.length(); j++)
                    repositoryTags.add(new RepositoryTag(jRepositoryTags.getJSONObject(j)));
                return (T) repositoryTags;
            default:
                return (T) repositoryTagsResponse;
        }
    }

    public ArrayList<Team> getRepositoryTeams(Repository repository) throws IOException {
        return getRepositoryTeams(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    public <T> T getRepositoryTeams(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryTeams(repository.getOwner().getLogin(), repository.getName(), format);
    }

    public ArrayList<Team> getRepositoryTeams(String owner, String repo) throws IOException {
        return getRepositoryTeams(owner, repo, LIBRARY_OBJECT);
    }

    public <T> T getRepositoryTeams(String owner, String repo, ReturnFormat format) throws IOException {
        return returnTeamsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + TEAMS_PATH), format);
    }

    public ArrayList<Team> getRepositoryTeams(Repository repository, Params queryParams) throws IOException {
        return getRepositoryTeams(repository.getOwner().getLogin(), repository.getName(), queryParams,
                LIBRARY_OBJECT);
    }

    public <T> T getRepositoryTeams(Repository repository, Params queryParams,
                                    ReturnFormat format) throws IOException {
        return getRepositoryTeams(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    public ArrayList<Team> getRepositoryTeams(String owner, String repo, Params queryParams) throws IOException {
        return getRepositoryTeams(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getRepositoryTeams(String owner, String repo, Params queryParams,
                                    ReturnFormat format) throws IOException {
        return returnTeamsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + TEAMS_PATH
                + queryParams.createQueryString()), format);
    }

    public ArrayList<String> getAllRepositoryTopics(Repository repository) throws IOException {
        return getAllRepositoryTopics(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    public <T> T getAllRepositoryTopics(Repository repository, ReturnFormat format) throws IOException {
        return getAllRepositoryTopics(repository.getOwner().getLogin(), repository.getName(), format);
    }

    public ArrayList<String> getAllRepositoryTopics(String owner, String repo) throws IOException {
        return getAllRepositoryTopics(owner, repo, LIBRARY_OBJECT);
    }

    public <T> T getAllRepositoryTopics(String owner, String repo, ReturnFormat format) throws IOException {
        return returnRepositoryTopics(sendGetRequest(REPOS_PATH + owner + "/" + repo + TOPICS_PATH), format);
    }

    public ArrayList<String> getAllRepositoryTopics(Repository repository, Params queryParams) throws IOException {
        return getAllRepositoryTopics(repository.getOwner().getLogin(), repository.getName(), queryParams,
                LIBRARY_OBJECT);
    }

    public <T> T getAllRepositoryTopics(Repository repository, Params queryParams,
                                        ReturnFormat format) throws IOException {
        return getAllRepositoryTopics(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    public ArrayList<String> getAllRepositoryTopics(String owner, String repo, Params queryParams) throws IOException {
        return getAllRepositoryTopics(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getAllRepositoryTopics(String owner, String repo, Params queryParams,
                                        ReturnFormat format) throws IOException {
        return returnRepositoryTopics(sendGetRequest(REPOS_PATH + owner + "/" + repo + TOPICS_PATH
                + queryParams.createQueryString()), format);
    }

    public ArrayList<String> replaceAllRepositoryTopics(Repository repository, String... topics) throws IOException {
        return replaceAllRepositoryTopics(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT,
                topics);
    }

    public <T> T replaceAllRepositoryTopics(Repository repository, ReturnFormat format, String... topics) throws IOException {
        return replaceAllRepositoryTopics(repository.getOwner().getLogin(), repository.getName(), format, topics);
    }

    public ArrayList<String> replaceAllRepositoryTopics(String owner, String repo, String... topics) throws IOException {
        return replaceAllRepositoryTopics(owner, repo, LIBRARY_OBJECT, topics);
    }

    public <T> T replaceAllRepositoryTopics(String owner, String repo, ReturnFormat format,
                                            String... topics) throws IOException {
        Params payload = new Params();
        payload.addParam("names", topics);
        return returnRepositoryTopics(sendPutRequest(REPOS_PATH + owner + "/" + repo + TOPICS_PATH, payload),
                format);
    }

    /**
     * Method to create a repository topics list
     *
     * @param repositoryTopicsResponse: obtained from GitHub's response
     * @param format:                   return type formatter -> {@link ReturnFormat}
     * @return repository topics list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnRepositoryTopics(String repositoryTopicsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(repositoryTopicsResponse);
            case LIBRARY_OBJECT:
                return (T) returnStringsList(new JSONObject(repositoryTopicsResponse).getJSONArray("names"));
            default:
                return (T) repositoryTopicsResponse;
        }
    }

    public Repository transferRepository(Repository repository, String newOwner) throws IOException {
        return transferRepository(repository.getOwner().getLogin(), repository.getName(), newOwner, LIBRARY_OBJECT);
    }

    public <T> T transferRepository(Repository repository, String newOwner, ReturnFormat format) throws IOException {
        return transferRepository(repository.getOwner().getLogin(), repository.getName(), newOwner, format);
    }

    public Repository transferRepository(String owner, String repo, String newOwner) throws IOException {
        return transferRepository(owner, repo, newOwner, LIBRARY_OBJECT);
    }

    public <T> T transferRepository(String owner, String repo, String newOwner, ReturnFormat format) throws IOException {
        return transferRepository(owner, repo, newOwner, null, format);
    }

    public Repository transferRepository(Repository repository, String newOwner, Params bodyParams) throws IOException {
        return transferRepository(repository.getOwner().getLogin(), repository.getName(), newOwner, bodyParams,
                LIBRARY_OBJECT);
    }

    public <T> T transferRepository(Repository repository, String newOwner, Params bodyParams,
                                    ReturnFormat format) throws IOException {
        return transferRepository(repository.getOwner().getLogin(), repository.getName(), newOwner, bodyParams, format);
    }

    public Repository transferRepository(String owner, String repo, String newOwner, Params bodyParams) throws IOException {
        return transferRepository(owner, repo, newOwner, bodyParams, LIBRARY_OBJECT);
    }

    public <T> T transferRepository(String owner, String repo, String newOwner, Params bodyParams,
                                    ReturnFormat format) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("new_owner", newOwner);
        return returnRepository(sendPostRequest(REPOS_PATH + owner + "/" + repo + TRANSFER_PATH, bodyParams),
                format);
    }

    public boolean checkIfVulnerabilityAlertsAreEnabled(Repository repository) {
        return checkIfVulnerabilityAlertsAreEnabled(repository.getOwner().getLogin(), repository.getName());
    }

    public boolean checkIfVulnerabilityAlertsAreEnabled(String owner, String repo) {
        try {
            sendGetRequest(REPOS_PATH + owner + "/" + repo + VULNERABILITY_ALERTS_PATH);
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

    public boolean enableVulnerabilityAlerts(Repository repository) {
        return enableVulnerabilityAlerts(repository.getOwner().getLogin(), repository.getName());
    }

    public boolean enableVulnerabilityAlerts(String owner, String repo) {
        try {
            sendPutRequest(REPOS_PATH + owner + "/" + repo + VULNERABILITY_ALERTS_PATH, null);
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

    public boolean disableVulnerabilityAlerts(Repository repository) {
        return disableVulnerabilityAlerts(repository.getOwner().getLogin(), repository.getName());
    }

    public boolean disableVulnerabilityAlerts(String owner, String repo) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + VULNERABILITY_ALERTS_PATH);
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

    public Repository createRepositoryUsingTemplate(String templateOwner, String templateRepo,
                                                    String name) throws IOException {
        return createRepositoryUsingTemplate(templateOwner, templateRepo, name, LIBRARY_OBJECT);
    }

    public <T> T createRepositoryUsingTemplate(String templateOwner, String templateRepo, String name,
                                               ReturnFormat format) throws IOException {
        return createRepositoryUsingTemplate(templateOwner, templateRepo, name, null, format);
    }

    public Repository createRepositoryUsingTemplate(String templateOwner, String templateRepo, String name,
                                                    Params bodyParams) throws IOException {
        return createRepositoryUsingTemplate(templateOwner, templateRepo, name, bodyParams, LIBRARY_OBJECT);
    }

    public <T> T createRepositoryUsingTemplate(String templateOwner, String templateRepo, String name, Params bodyParams,
                                               ReturnFormat format) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("name", name);
        return returnRepository(sendPostRequest(REPOS_PATH + templateOwner + "/" + templateRepo + GENERATE_PATH,
                bodyParams), format);
    }

    public ArrayList<Repository> getPublicRepositories() throws IOException {
        return getPublicRepositories(LIBRARY_OBJECT);
    }

    public <T> T getPublicRepositories(ReturnFormat format) throws IOException {
        return getPublicRepositories(-1, format);
    }

    public ArrayList<Repository> getPublicRepositories(long since) throws IOException {
        return getPublicRepositories(since, LIBRARY_OBJECT);
    }

    public <T> T getPublicRepositories(long since, ReturnFormat format) throws IOException {
        String reqUrl = "repositories";
        if (since != -1)
            reqUrl += "?since=" + since;
        return returnRepositories(sendGetRequest(reqUrl), format);
    }

    public ArrayList<Repository> getAuthenticatedUserRepositories() throws IOException {
        return getAuthenticatedUserRepositories(LIBRARY_OBJECT);
    }

    public <T> T getAuthenticatedUserRepositories(ReturnFormat format) throws IOException {
        return returnRepositories(sendGetRequest(USER_REPOS_PATH), format);
    }

    public ArrayList<Repository> getAuthenticatedUserRepositories(Params queryParams) throws IOException {
        return getAuthenticatedUserRepositories(queryParams, LIBRARY_OBJECT);
    }

    public <T> T getAuthenticatedUserRepositories(Params queryParams, ReturnFormat format) throws IOException {
        return returnRepositories(sendGetRequest(USER_REPOS_PATH + queryParams.createQueryString()),
                format);
    }

    public Repository createAuthenticatedUserRepository(String name) throws IOException {
        return createAuthenticatedUserRepository(name, LIBRARY_OBJECT);
    }

    public <T> T createAuthenticatedUserRepository(String name, ReturnFormat format) throws IOException {
        return createAuthenticatedUserRepository(name, null, format);
    }

    public Repository createAuthenticatedUserRepository(String name, Params bodyParams) throws IOException {
        return createAuthenticatedUserRepository(name, bodyParams, LIBRARY_OBJECT);
    }

    public <T> T createAuthenticatedUserRepository(String name, Params bodyParams, ReturnFormat format) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("name", name);
        return returnRepository(sendPostRequest(USER_REPOS_PATH, bodyParams), format);
    }

    /**
     * Method to create a repository
     *
     * @param repositoryResponse: obtained from GitHub's response
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return repository as {@code "format"} defines
     **/
    @Returner
    private <T> T returnRepository(String repositoryResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(repositoryResponse);
            case LIBRARY_OBJECT:
                return (T) new Repository(new JSONObject(repositoryResponse));
            default:
                return (T) repositoryResponse;
        }
    }

    public ArrayList<Repository> getUserRepositories(String username) throws IOException {
        return getUserRepositories(username, LIBRARY_OBJECT);
    }

    public <T> T getUserRepositories(String username, ReturnFormat format) throws IOException {
        return returnRepositories(sendGetRequest(USERS_PATH + username + REPOS_QUERY_PATH), format);
    }

    public ArrayList<Repository> getUserRepositories(String username, Params queryParams) throws IOException {
        return getUserRepositories(username, queryParams, LIBRARY_OBJECT);
    }

    public <T> T getUserRepositories(String username, Params queryParams, ReturnFormat format) throws IOException {
        return returnRepositories(sendGetRequest(USERS_PATH + username + REPOS_QUERY_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a repositories list
     *
     * @param repositoriesResponse: obtained from GitHub's response
     * @param format:               return type formatter -> {@link ReturnFormat}
     * @return repositories list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnRepositories(String repositoriesResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(repositoriesResponse);
            case LIBRARY_OBJECT:
                return (T) returnRepositoriesList(new JSONArray(repositoriesResponse));
            default:
                return (T) repositoriesResponse;
        }
    }

}
