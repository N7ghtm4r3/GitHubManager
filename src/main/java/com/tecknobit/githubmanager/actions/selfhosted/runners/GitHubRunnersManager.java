package com.tecknobit.githubmanager.actions.selfhosted.runners;

import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.actions.selfhosted.records.Runner;
import com.tecknobit.githubmanager.actions.selfhosted.records.RunnersList;
import com.tecknobit.githubmanager.actions.selfhosted.runners.records.Application;
import com.tecknobit.githubmanager.actions.selfhosted.runners.records.GitHubToken;
import com.tecknobit.githubmanager.actions.selfhosted.runners.records.labels.GitHubLabel;
import com.tecknobit.githubmanager.actions.selfhosted.runners.records.labels.LabelsList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubRunnersManager} class is useful to manage all GitHub's runners endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/self-hosted-runners">
 * About the Self-hosted runners API</a>
 * @see GitHubManager
 **/
public class GitHubRunnersManager extends GitHubManager {

    /**
     * {@code ACTIONS_RUNNERS_PATH} constant for {@code "/actions/runners"} path
     **/
    public static final String ACTIONS_RUNNERS_PATH = ACTIONS_PATH + "runners";

    /**
     * {@code DOWNLOADS_PATH} constant for {@code "downloads"} path
     **/
    public static final String DOWNLOADS_PATH = ACTIONS_PATH + "downloads";

    /**
     * {@code REGISTRATION_TOKEN_PATH} constant for {@code "registration-token"} path
     **/
    public static final String REGISTRATION_TOKEN_PATH = "registration-token";

    /**
     * {@code REMOVE_TOKEN_PATH} constant for {@code "remove-token"} path
     **/
    public static final String REMOVE_TOKEN_PATH = "remove-token";

    /**
     * {@code LABELS_PATH} constant for {@code "/labels"} path
     **/
    public static final String LABELS_PATH = "/labels";

    /**
     * Constructor to init a {@link GitHubRunnersManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubRunnersManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubRunnersManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubRunnersManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubRunnersManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubRunnersManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubRunnersManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubRunnersManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubRunnersManager} <br>
     * Any params required
     *
     * @throws IllegalArgumentException when a parameterized constructor has not been called before this constructor
     * @apiNote this constructor is useful to instantiate a new {@link GitHubRunnersManager}'s manager without re-insert
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
    public GitHubRunnersManager() {
        super();
    }

    public RunnersList getEnterpriseRunnersList(String enterprise) throws IOException {
        return returnRunnersList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH),
                LIBRARY_OBJECT);
    }

    public <T> T getEnterpriseRunnersList(String enterprise, ReturnFormat format) throws IOException {
        return returnRunnersList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH),
                format);
    }

    public RunnersList getEnterpriseRunnersList(String enterprise, Params queryParams) throws IOException {
        return returnRunnersList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH +
                queryParams.createQueryString()), LIBRARY_OBJECT);
    }

    public <T> T getEnterpriseRunnersList(String enterprise, Params queryParams, ReturnFormat format) throws IOException {
        return returnRunnersList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH +
                queryParams.createQueryString()), format);
    }

    public Collection<Application> getEnterpriseApplicationsList(String enterprise) throws IOException {
        return returnApplicationsList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH +
                DOWNLOADS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getEnterpriseApplicationsList(String enterprise, ReturnFormat format) throws IOException {
        return returnApplicationsList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH +
                DOWNLOADS_PATH), format);
    }

    private <T> T returnApplicationsList(String applicationsResponse, ReturnFormat format) {
        try {
            switch (format) {
                case JSON:
                    return (T) new JSONArray(applicationsResponse);
                case LIBRARY_OBJECT:
                    ArrayList<Application> applications = new ArrayList<>();
                    JSONArray jApplications = new JSONArray(applicationsResponse);
                    for (int j = 0; j < jApplications.length(); j++)
                        applications.add(new Application(jApplications.getJSONObject(j)));
                    return (T) applications;
                default:
                    return (T) applicationsResponse;
            }
        } catch (JSONException e) {
            printErrorResponse();
            return null;
        }
    }

    public GitHubToken createEnterpriseRegistrationToken(String enterprise) throws IOException {
        return returnGitHubToken(sendPostRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/"
                + REGISTRATION_TOKEN_PATH, null), LIBRARY_OBJECT);
    }

    public <T> T createEnterpriseRegistrationToken(String enterprise, ReturnFormat format) throws IOException {
        return returnGitHubToken(sendPostRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/"
                + REGISTRATION_TOKEN_PATH, null), format);
    }

    public GitHubToken createEnterpriseRemoveToken(String enterprise) throws IOException {
        return returnGitHubToken(sendPostRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/"
                + REMOVE_TOKEN_PATH, null), LIBRARY_OBJECT);
    }

    public <T> T createEnterpriseRemoveToken(String enterprise, ReturnFormat format) throws IOException {
        return returnGitHubToken(sendPostRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/"
                + REMOVE_TOKEN_PATH, null), format);
    }

    private <T> T returnGitHubToken(String tokenResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(tokenResponse);
            case LIBRARY_OBJECT:
                return (T) new GitHubToken(new JSONObject(tokenResponse));
            default:
                return (T) tokenResponse;
        }
    }

    public Runner getEnterpriseRunner(String enterprise, long runnerId) throws IOException {
        return returnRunner(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" +
                runnerId), LIBRARY_OBJECT);
    }

    public <T> T getEnterpriseRunner(String enterprise, long runnerId, ReturnFormat format) throws IOException {
        return returnRunner(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" +
                runnerId), format);
    }

    private <T> T returnRunner(String runnerResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(runnerResponse);
            case LIBRARY_OBJECT:
                return (T) new Runner(new JSONObject(runnerResponse));
            default:
                return (T) runnerResponse;
        }
    }

    public boolean deleteEnterpriseRunner(String enterprise, Runner runner) {
        return deleteEnterpriseRunner(enterprise, runner.getId());
    }

    public boolean deleteEnterpriseRunner(String enterprise, long runnerId) {
        try {
            sendDeleteRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" + runnerId);
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

    public LabelsList getEnterpriseLabelsList(String enterprise, Runner runner) throws IOException {
        return returnLabelsList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" +
                runner.getId() + LABELS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getEnterpriseLabelsList(String enterprise, Runner runner, ReturnFormat format) throws IOException {
        return returnLabelsList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" +
                runner.getId() + LABELS_PATH), format);
    }

    public LabelsList getEnterpriseLabelsList(String enterprise, long runnerId) throws IOException {
        return returnLabelsList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH), LIBRARY_OBJECT);
    }

    public <T> T getEnterpriseLabelsList(String enterprise, long runnerId, ReturnFormat format) throws IOException {
        return returnLabelsList(sendGetRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH), format);
    }

    public LabelsList addCustomEnterpriseLabels(String enterprise, Runner runner, LabelsList labels) throws IOException {
        return addCustomEnterpriseLabels(enterprise, runner.getId(), labels, LIBRARY_OBJECT);
    }

    public <T> T addCustomEnterpriseLabels(String enterprise, Runner runner, LabelsList labels,
                                           ReturnFormat format) throws IOException {
        return addCustomEnterpriseLabels(enterprise, runner.getId(), labels, format);
    }

    public LabelsList addCustomEnterpriseLabels(String enterprise, long runnerId, LabelsList labels) throws IOException {
        return addCustomEnterpriseLabels(enterprise, runnerId, labels, LIBRARY_OBJECT);
    }

    public <T> T addCustomEnterpriseLabels(String enterprise, long runnerId, LabelsList labels,
                                           ReturnFormat format) throws IOException {
        ArrayList<String> customLabels = new ArrayList<>();
        for (GitHubLabel label : labels.getLabels())
            customLabels.add(label.getName());
        return addCustomEnterpriseLabels(enterprise, runnerId, customLabels, format);
    }

    public LabelsList addCustomEnterpriseLabels(String enterprise, Runner runner,
                                                Collection<String> labels) throws IOException {
        return addCustomEnterpriseLabels(enterprise, runner.getId(), labels, LIBRARY_OBJECT);
    }

    public <T> T addCustomEnterpriseLabels(String enterprise, Runner runner, Collection<String> labels,
                                           ReturnFormat format) throws IOException {
        return addCustomEnterpriseLabels(enterprise, runner.getId(), labels, format);
    }

    public LabelsList addCustomEnterpriseLabels(String enterprise, long runnerId,
                                                Collection<String> labels) throws IOException {
        return addCustomEnterpriseLabels(enterprise, runnerId, labels, LIBRARY_OBJECT);
    }

    public <T> T addCustomEnterpriseLabels(String enterprise, long runnerId, Collection<String> labels,
                                           ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("labels", labels.stream().toList());
        return returnLabelsList(sendPostRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH, payload), format);
    }

    public LabelsList addCustomEnterpriseLabels(String enterprise, Runner runner, String[] labels) throws IOException {
        return addCustomEnterpriseLabels(enterprise, runner.getId(), labels, LIBRARY_OBJECT);
    }

    public <T> T addCustomEnterpriseLabels(String enterprise, Runner runner, String[] labels,
                                           ReturnFormat format) throws IOException {
        return addCustomEnterpriseLabels(enterprise, runner.getId(), labels, format);
    }

    public LabelsList addCustomEnterpriseLabels(String enterprise, long runnerId, String[] labels) throws IOException {
        return addCustomEnterpriseLabels(enterprise, runnerId, labels, LIBRARY_OBJECT);
    }

    public <T> T addCustomEnterpriseLabels(String enterprise, long runnerId, String[] labels,
                                           ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("labels", Arrays.stream(labels).toList());
        return returnLabelsList(sendPostRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH, payload), format);
    }

    public LabelsList setCustomEnterpriseLabels(String enterprise, Runner runner, LabelsList labels) throws IOException {
        return setCustomEnterpriseLabels(enterprise, runner.getId(), labels, LIBRARY_OBJECT);
    }

    public <T> T setCustomEnterpriseLabels(String enterprise, Runner runner, LabelsList labels,
                                           ReturnFormat format) throws IOException {
        return setCustomEnterpriseLabels(enterprise, runner.getId(), labels, LIBRARY_OBJECT);
    }

    public LabelsList setCustomEnterpriseLabels(String enterprise, long runnerId, LabelsList labels) throws IOException {
        return setCustomEnterpriseLabels(enterprise, runnerId, labels, LIBRARY_OBJECT);
    }

    public <T> T setCustomEnterpriseLabels(String enterprise, long runnerId, LabelsList labels,
                                           ReturnFormat format) throws IOException {
        ArrayList<String> customLabels = new ArrayList<>();
        for (GitHubLabel label : labels.getLabels())
            customLabels.add(label.getName());
        return setCustomEnterpriseLabels(enterprise, runnerId, customLabels, format);
    }

    public LabelsList setCustomEnterpriseLabels(String enterprise, Runner runner,
                                                Collection<String> labels) throws IOException {
        return setCustomEnterpriseLabels(enterprise, runner.getId(), labels, LIBRARY_OBJECT);
    }

    public <T> T setCustomEnterpriseLabels(String enterprise, Runner runner, Collection<String> labels,
                                           ReturnFormat format) throws IOException {
        return setCustomEnterpriseLabels(enterprise, runner.getId(), labels, format);
    }

    public LabelsList setCustomEnterpriseLabels(String enterprise, long runnerId,
                                                Collection<String> labels) throws IOException {
        return setCustomEnterpriseLabels(enterprise, runnerId, labels, LIBRARY_OBJECT);
    }

    public <T> T setCustomEnterpriseLabels(String enterprise, long runnerId, Collection<String> labels,
                                           ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("labels", labels.stream().toList());
        return returnLabelsList(sendPutRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH, payload), format);
    }

    public LabelsList setCustomEnterpriseLabels(String enterprise, Runner runner, String[] labels) throws IOException {
        return setCustomEnterpriseLabels(enterprise, runner.getId(), labels, LIBRARY_OBJECT);
    }

    public <T> T setCustomEnterpriseLabels(String enterprise, Runner runner, String[] labels,
                                           ReturnFormat format) throws IOException {
        return setCustomEnterpriseLabels(enterprise, runner.getId(), labels, LIBRARY_OBJECT);
    }

    public LabelsList setCustomEnterpriseLabels(String enterprise, long runnerId, String[] labels) throws IOException {
        return setCustomEnterpriseLabels(enterprise, runnerId, labels, LIBRARY_OBJECT);
    }

    public <T> T setCustomEnterpriseLabels(String enterprise, long runnerId, String[] labels,
                                           ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("labels", Arrays.stream(labels).toList());
        return returnLabelsList(sendPutRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH, payload), format);
    }

    public LabelsList removeAllCustomEnterpriseLabels(String enterprise, Runner runner) throws IOException {
        return returnLabelsList(sendDeleteRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" +
                runner.getId() + LABELS_PATH), LIBRARY_OBJECT);
    }

    public <T> T removeAllCustomEnterpriseLabels(String enterprise, Runner runner, ReturnFormat format) throws IOException {
        return returnLabelsList(sendDeleteRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" +
                runner.getId() + LABELS_PATH), format);
    }

    public LabelsList removeAllCustomEnterpriseLabels(String enterprise, long runnerId) throws IOException {
        return returnLabelsList(sendDeleteRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH), LIBRARY_OBJECT);
    }

    public <T> T removeAllCustomEnterpriseLabels(String enterprise, long runnerId, ReturnFormat format) throws IOException {
        return returnLabelsList(sendDeleteRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH), format);
    }

    public LabelsList removeCustomEnterpriseLabel(String enterprise, Runner runner, GitHubLabel label) throws IOException {
        return returnLabelsList(sendDeleteRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" +
                runner.getId() + LABELS_PATH + "/" + label.getName()), LIBRARY_OBJECT);
    }

    public <T> T removeCustomEnterpriseLabel(String enterprise, Runner runner, GitHubLabel label,
                                             ReturnFormat format) throws IOException {
        return returnLabelsList(sendDeleteRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" +
                runner.getId() + LABELS_PATH + "/" + label.getName()), format);
    }

    public LabelsList removeCustomEnterpriseLabel(String enterprise, Runner runner, String name) throws IOException {
        return returnLabelsList(sendDeleteRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" +
                runner.getId() + LABELS_PATH + "/" + name), LIBRARY_OBJECT);
    }

    public <T> T removeCustomEnterpriseLabel(String enterprise, Runner runner, String name,
                                             ReturnFormat format) throws IOException {
        return returnLabelsList(sendDeleteRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" +
                runner.getId() + LABELS_PATH + "/" + name), format);
    }

    public LabelsList removeCustomEnterpriseLabel(String enterprise, long runnerId, GitHubLabel label) throws IOException {
        return returnLabelsList(sendDeleteRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH + "/" + label.getName()), LIBRARY_OBJECT);
    }

    public <T> T removeCustomEnterpriseLabel(String enterprise, long runnerId, GitHubLabel label,
                                             ReturnFormat format) throws IOException {
        return returnLabelsList(sendDeleteRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH + "/" + label.getName()), format);
    }

    public LabelsList removeCustomEnterpriseLabel(String enterprise, long runnerId, String name) throws IOException {
        return returnLabelsList(sendDeleteRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH + "/" + name), LIBRARY_OBJECT);
    }

    public <T> T removeCustomEnterpriseLabel(String enterprise, long runnerId, String name,
                                             ReturnFormat format) throws IOException {
        return returnLabelsList(sendDeleteRequest(ENTERPRISES_PATH + enterprise + ACTIONS_RUNNERS_PATH + "/" +
                runnerId + LABELS_PATH + "/" + name), format);
    }

    private <T> T returnLabelsList(String labelsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(labelsResponse);
            case LIBRARY_OBJECT:
                return (T) new LabelsList(new JSONObject(labelsResponse));
            default:
                return (T) labelsResponse;
        }
    }


}
