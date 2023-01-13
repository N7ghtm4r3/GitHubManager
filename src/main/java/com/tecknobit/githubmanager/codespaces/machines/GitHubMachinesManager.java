package com.tecknobit.githubmanager.codespaces.machines;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.codespaces.machines.records.MachinesList;
import org.json.JSONObject;

/**
 * The {@code GitHubMachinesManager} class is useful to manage all GitHub's machines endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codespaces/machines">
 * Codespaces machines</a>
 * @see GitHubManager
 **/
public class GitHubMachinesManager extends GitHubManager {

    /**
     * {@code MACHINES_PATH} constant for {@code "/machines"} path
     **/
    public static final String MACHINES_PATH = "/machines";

    /**
     * Constructor to init a {@link GitHubMachinesManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubMachinesManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubMachinesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubMachinesManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubMachinesManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubMachinesManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubMachinesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubMachinesManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubMachinesManager} <br>
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
    public GitHubMachinesManager() {
        super();
    }

    /**
     * Method to create a machines list
     *
     * @param machinesListResponse: obtained from GitHub's response
     * @param format:               return type formatter -> {@link ReturnFormat}
     * @return machines list as {@code "format"} defines
     **/
    @Returner
    public static <T> T returnMachinesList(String machinesListResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(machinesListResponse);
            case LIBRARY_OBJECT:
                return (T) new MachinesList(new JSONObject(machinesListResponse));
            default:
                return (T) machinesListResponse;
        }
    }

}
