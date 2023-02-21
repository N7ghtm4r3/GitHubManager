package com.tecknobit.githubmanager.issues.milestones;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.records.parents.GitHubOperation.Milestone;
import com.tecknobit.githubmanager.records.parents.GitHubOperation.Milestone.MilestoneSort;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.records.parents.GitHubOperationBaseStructure.OperationState;

/**
 * The {@code GitHubMilestonesManager} class is useful to manage all GitHub's milestones endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/milestones">
 * Milestones</a>
 * @see GitHubManager
 **/
public class GitHubMilestonesManager extends GitHubManager {

    /**
     * {@code MILESTONES_PATH} constant for {@code "/milestones"} path
     **/
    public static final String MILESTONES_PATH = "/milestones";

    /**
     * Constructor to init a {@link GitHubMilestonesManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubMilestonesManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubMilestonesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubMilestonesManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubMilestonesManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubMilestonesManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubMilestonesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubMilestonesManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubMilestonesManager} <br>
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
    public GitHubMilestonesManager() {
        super();
    }

    /**
     * Method to get the list of the milestones
     *
     * @param repository: the repository from fetch the list
     * @return milestones list as {@link Collection} of {@link Milestone} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/milestones#list-milestones">
     * List milestones</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/milestones")
    public Collection<Milestone> getMilestones(Repository repository) throws IOException {
        return getMilestones(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the milestones
     *
     * @param repository: the repository from fetch the list
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return milestones list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/milestones#list-milestones">
     * List milestones</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/milestones")
    public <T> T getMilestones(Repository repository, ReturnFormat format) throws IOException {
        return getMilestones(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the list of the milestones
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return milestones list as {@link Collection} of {@link Milestone} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/milestones#list-milestones">
     * List milestones</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/milestones")
    public Collection<Milestone> getMilestones(String owner, String repo) throws IOException {
        return getMilestones(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the milestones
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return milestones list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/milestones#list-milestones">
     * List milestones</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/milestones")
    public <T> T getMilestones(String owner, String repo, ReturnFormat format) throws IOException {
        return returnMilestonesList(sendGetRequest(REPOS_PATH + owner + "/" + repo + MILESTONES_PATH), format);
    }

    /**
     * Method to get the list of the milestones
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> indicates the state of the issues to return, constants available
     *                            {@link OperationState} - [string, default open]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link MilestoneSort}
     *                            - [string, default due_on]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default asc]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return milestones list as {@link Collection} of {@link Milestone} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/milestones#list-milestones">
     * List milestones</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/milestones")
    public Collection<Milestone> getMilestones(Repository repository, Params queryParams) throws IOException {
        return getMilestones(repository.getOwner().getLogin(), repository.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the milestones
     *
     * @param repository:  the repository from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> indicates the state of the issues to return, constants available
     *                            {@link OperationState} - [string, default open]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link MilestoneSort}
     *                            - [string, default due_on]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default asc]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return milestones list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/milestones#list-milestones">
     * List milestones</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/milestones")
    public <T> T getMilestones(Repository repository, Params queryParams, ReturnFormat format) throws IOException {
        return getMilestones(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get the list of the milestones
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> indicates the state of the issues to return, constants available
     *                            {@link OperationState} - [string, default open]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link MilestoneSort}
     *                            - [string, default due_on]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default asc]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return milestones list as {@link Collection} of {@link Milestone} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/milestones#list-milestones">
     * List milestones</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/milestones")
    public Collection<Milestone> getMilestones(String owner, String repo, Params queryParams) throws IOException {
        return getMilestones(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the milestones
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "state"} -> indicates the state of the issues to return, constants available
     *                            {@link OperationState} - [string, default open]
     *                        </li>
     *                        <li>
     *                            {@code "sort"} -> what to sort results by, constants available {@link MilestoneSort}
     *                            - [string, default due_on]
     *                        </li>
     *                        <li>
     *                            {@code "direction"} -> the direction to sort the results by, constants available
     *                             {@link Directions} - [string, default asc]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return milestones list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/milestones#list-milestones">
     * List milestones</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/milestones")
    public <T> T getMilestones(String owner, String repo, Params queryParams, ReturnFormat format) throws IOException {
        return returnMilestonesList(sendGetRequest(REPOS_PATH + owner + "/" + repo
                + MILESTONES_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a milestones list
     *
     * @param milestonesListResponse : obtained from GitHub's response
     * @param format                 :              return type formatter -> {@link ReturnFormat}
     * @return milestones list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnMilestonesList(String milestonesListResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(milestonesListResponse);
            case LIBRARY_OBJECT:
                ArrayList<Milestone> milestones = new ArrayList<>();
                JSONArray jMilestones = new JSONArray(milestonesListResponse);
                for (int j = 0; j < jMilestones.length(); j++)
                    milestones.add(new Milestone(jMilestones.getJSONObject(j)));
                return (T) milestones;
            default:
                return (T) milestonesListResponse;
        }
    }

    /**
     * Method to create a milestone
     *
     * @param repository: the repository where create the milestone
     * @param title:      the title of the milestone
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "state"} -> indicates the state of the issues to return, constants available
     *                           {@link OperationState} - [string, default open]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> a description of the milestone
     *                       </li>
     *                       <li>
     *                           {@code "due_on"} -> the milestone due date. This is a timestamp in ISO 8601 format:
     *                           {@code "YYYY-MM-DDTHH:MM:SSZ"} - [string]
     *                       </li>
     *                    </ul>
     * @return milestone as {@link Milestone} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/milestones#create-a-milestone">
     * Create a milestone</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/milestones")
    public Milestone createMilestone(Repository repository, String title, Params bodyParams) throws IOException {
        return createMilestone(repository.getOwner().getLogin(), repository.getName(), title, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a milestone
     *
     * @param repository: the repository where create the milestone
     * @param title:      the title of the milestone
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "state"} -> indicates the state of the issues to return, constants available
     *                           {@link OperationState} - [string, default open]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> a description of the milestone
     *                       </li>
     *                       <li>
     *                           {@code "due_on"} -> the milestone due date. This is a timestamp in ISO 8601 format:
     *                           {@code "YYYY-MM-DDTHH:MM:SSZ"} - [string]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return milestone as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/milestones#create-a-milestone">
     * Create a milestone</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/milestones")
    public <T> T createMilestone(Repository repository, String title, Params bodyParams,
                                 ReturnFormat format) throws IOException {
        return createMilestone(repository.getOwner().getLogin(), repository.getName(), title, bodyParams, format);
    }

    /**
     * Method to create a milestone
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param title:      the title of the milestone
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "state"} -> indicates the state of the issues to return, constants available
     *                           {@link OperationState} - [string, default open]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> a description of the milestone
     *                       </li>
     *                       <li>
     *                           {@code "due_on"} -> the milestone due date. This is a timestamp in ISO 8601 format:
     *                           {@code "YYYY-MM-DDTHH:MM:SSZ"} - [string]
     *                       </li>
     *                    </ul>
     * @return milestone as {@link Milestone} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/milestones#create-a-milestone">
     * Create a milestone</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/milestones")
    public Milestone createMilestone(String owner, String repo, String title, Params bodyParams) throws IOException {
        return createMilestone(owner, repo, title, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to create a milestone
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param title:      the title of the milestone
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "state"} -> indicates the state of the issues to return, constants available
     *                           {@link OperationState} - [string, default open]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> a description of the milestone
     *                       </li>
     *                       <li>
     *                           {@code "due_on"} -> the milestone due date. This is a timestamp in ISO 8601 format:
     *                           {@code "YYYY-MM-DDTHH:MM:SSZ"} - [string]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return milestone as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/milestones#create-a-milestone">
     * Create a milestone</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/milestones")
    public <T> T createMilestone(String owner, String repo, String title, Params bodyParams,
                                 ReturnFormat format) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("title", title);
        return returnMilestone(sendPostRequest(REPOS_PATH + owner + "/" + repo + MILESTONES_PATH, bodyParams),
                format);
    }

    /**
     * Method to get a milestone
     *
     * @param repository:      the repository from fetch the milestone
     * @param milestoneNumber: the number that identifies the milestone
     * @return milestone as {@link Milestone} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/milestones#get-a-milestone">
     * Get a milestone</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/milestones/{milestone_number}")
    public Milestone getMilestone(Repository repository, long milestoneNumber) throws IOException {
        return getMilestone(repository.getOwner().getLogin(), repository.getName(), milestoneNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get a milestone
     *
     * @param repository:      the repository from fetch the milestone
     * @param milestoneNumber: the number that identifies the milestone
     * @param format           :              return type formatter -> {@link ReturnFormat}
     * @return milestone as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/milestones#get-a-milestone">
     * Get a milestone</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/milestones/{milestone_number}")
    public <T> T getMilestone(Repository repository, long milestoneNumber, ReturnFormat format) throws IOException {
        return getMilestone(repository.getOwner().getLogin(), repository.getName(), milestoneNumber, format);
    }

    /**
     * Method to get a milestone
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param milestoneNumber: the number that identifies the milestone
     * @return milestone as {@link Milestone} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/milestones#get-a-milestone">
     * Get a milestone</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/milestones/{milestone_number}")
    public Milestone getMilestone(String owner, String repo, long milestoneNumber) throws IOException {
        return getMilestone(owner, repo, milestoneNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get a milestone
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param milestoneNumber: the number that identifies the milestone
     * @param format           :              return type formatter -> {@link ReturnFormat}
     * @return milestone as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/milestones#get-a-milestone">
     * Get a milestone</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/milestones/{milestone_number}")
    public <T> T getMilestone(String owner, String repo, long milestoneNumber, ReturnFormat format) throws IOException {
        return returnMilestone(sendGetRequest(REPOS_PATH + owner + "/" + repo + MILESTONES_PATH + "/" +
                milestoneNumber), format);
    }

    /**
     * Method to update a milestone
     *
     * @param repository: the repository where update the milestone
     * @param milestone:  the milestone to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "title"} -> the title of the milestone - [string]
     *                       </li>
     *                       <li>
     *                           {@code "state"} -> indicates the state of the issues to return, constants available
     *                           {@link OperationState} - [string, default open]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> a description of the milestone
     *                       </li>
     *                       <li>
     *                           {@code "due_on"} -> the milestone due date. This is a timestamp in ISO 8601 format:
     *                           {@code "YYYY-MM-DDTHH:MM:SSZ"} - [string]
     *                       </li>
     *                    </ul>
     * @return milestone as {@link Milestone} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/milestones#update-a-milestone">
     * Update a milestone</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/milestones/{milestone_number}")
    public Milestone updateMilestone(Repository repository, Milestone milestone, Params bodyParams) throws IOException {
        return updateMilestone(repository.getOwner().getLogin(), repository.getName(), milestone.getNumber(), bodyParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to update a milestone
     *
     * @param repository: the repository where update the milestone
     * @param milestone:  the milestone to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "title"} -> the title of the milestone - [string]
     *                       </li>
     *                       <li>
     *                           {@code "state"} -> indicates the state of the issues to return, constants available
     *                           {@link OperationState} - [string, default open]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> a description of the milestone
     *                       </li>
     *                       <li>
     *                           {@code "due_on"} -> the milestone due date. This is a timestamp in ISO 8601 format:
     *                           {@code "YYYY-MM-DDTHH:MM:SSZ"} - [string]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return milestone as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/milestones#update-a-milestone">
     * Update a milestone</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/milestones/{milestone_number}")
    public <T> T updateMilestone(Repository repository, Milestone milestone, Params bodyParams,
                                 ReturnFormat format) throws IOException {
        return updateMilestone(repository.getOwner().getLogin(), repository.getName(), milestone.getNumber(), bodyParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to update a milestone
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param milestone:  the milestone to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "title"} -> the title of the milestone - [string]
     *                       </li>
     *                       <li>
     *                           {@code "state"} -> indicates the state of the issues to return, constants available
     *                           {@link OperationState} - [string, default open]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> a description of the milestone
     *                       </li>
     *                       <li>
     *                           {@code "due_on"} -> the milestone due date. This is a timestamp in ISO 8601 format:
     *                           {@code "YYYY-MM-DDTHH:MM:SSZ"} - [string]
     *                       </li>
     *                    </ul>
     * @return milestone as {@link Milestone} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/milestones#update-a-milestone">
     * Update a milestone</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/milestones/{milestone_number}")
    public Milestone updateMilestone(String owner, String repo, Milestone milestone, Params bodyParams) throws IOException {
        return updateMilestone(owner, repo, milestone.getNumber(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update a milestone
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param milestone:  the milestone to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "title"} -> the title of the milestone - [string]
     *                       </li>
     *                       <li>
     *                           {@code "state"} -> indicates the state of the issues to return, constants available
     *                           {@link OperationState} - [string, default open]
     *                       </li>
     *                       <li>
     *                           {@code "description"} -> a description of the milestone
     *                       </li>
     *                       <li>
     *                           {@code "due_on"} -> the milestone due date. This is a timestamp in ISO 8601 format:
     *                           {@code "YYYY-MM-DDTHH:MM:SSZ"} - [string]
     *                       </li>
     *                    </ul>
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return milestone as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/milestones#update-a-milestone">
     * Update a milestone</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/milestones/{milestone_number}")
    public <T> T updateMilestone(String owner, String repo, Milestone milestone, Params bodyParams,
                                 ReturnFormat format) throws IOException {
        return updateMilestone(owner, repo, milestone.getNumber(), bodyParams, format);
    }

    /**
     * Method to update a milestone
     *
     * @param repository:      the repository where update the milestone
     * @param milestoneNumber: the number that identifies the milestone
     * @param bodyParams:      extra body params not mandatory, keys accepted are:
     *                         <ul>
     *                            <li>
     *                                {@code "title"} -> the title of the milestone - [string]
     *                            </li>
     *                            <li>
     *                                {@code "state"} -> indicates the state of the issues to return, constants available
     *                                {@link OperationState} - [string, default open]
     *                            </li>
     *                            <li>
     *                                {@code "description"} -> a description of the milestone
     *                            </li>
     *                            <li>
     *                                {@code "due_on"} -> the milestone due date. This is a timestamp in ISO 8601 format:
     *                                {@code "YYYY-MM-DDTHH:MM:SSZ"} - [string]
     *                            </li>
     *                         </ul>
     * @return milestone as {@link Milestone} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/milestones#update-a-milestone">
     * Update a milestone</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/milestones/{milestone_number}")
    public Milestone updateMilestone(Repository repository, long milestoneNumber, Params bodyParams) throws IOException {
        return updateMilestone(repository.getOwner().getLogin(), repository.getName(), milestoneNumber, bodyParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to update a milestone
     *
     * @param repository:      the repository where update the milestone
     * @param milestoneNumber: the number that identifies the milestone
     * @param bodyParams:      extra body params not mandatory, keys accepted are:
     *                         <ul>
     *                            <li>
     *                                {@code "title"} -> the title of the milestone - [string]
     *                            </li>
     *                            <li>
     *                                {@code "state"} -> indicates the state of the issues to return, constants available
     *                                {@link OperationState} - [string, default open]
     *                            </li>
     *                            <li>
     *                                {@code "description"} -> a description of the milestone
     *                            </li>
     *                            <li>
     *                                {@code "due_on"} -> the milestone due date. This is a timestamp in ISO 8601 format:
     *                                {@code "YYYY-MM-DDTHH:MM:SSZ"} - [string]
     *                            </li>
     *                         </ul>
     * @param format           :              return type formatter -> {@link ReturnFormat}
     * @return milestone as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/milestones#update-a-milestone">
     * Update a milestone</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/milestones/{milestone_number}")
    public <T> T updateMilestone(Repository repository, long milestoneNumber, Params bodyParams,
                                 ReturnFormat format) throws IOException {
        return updateMilestone(repository.getOwner().getLogin(), repository.getName(), milestoneNumber, bodyParams, format);
    }

    /**
     * Method to update a milestone
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param milestoneNumber: the number that identifies the milestone
     * @param bodyParams:      extra body params not mandatory, keys accepted are:
     *                         <ul>
     *                            <li>
     *                                {@code "title"} -> the title of the milestone - [string]
     *                            </li>
     *                            <li>
     *                                {@code "state"} -> indicates the state of the issues to return, constants available
     *                                {@link OperationState} - [string, default open]
     *                            </li>
     *                            <li>
     *                                {@code "description"} -> a description of the milestone
     *                            </li>
     *                            <li>
     *                                {@code "due_on"} -> the milestone due date. This is a timestamp in ISO 8601 format:
     *                                {@code "YYYY-MM-DDTHH:MM:SSZ"} - [string]
     *                            </li>
     *                         </ul>
     * @return milestone as {@link Milestone} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/milestones#update-a-milestone">
     * Update a milestone</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/milestones/{milestone_number}")
    public Milestone updateMilestone(String owner, String repo, long milestoneNumber, Params bodyParams) throws IOException {
        return updateMilestone(owner, repo, milestoneNumber, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update a milestone
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param milestoneNumber: the number that identifies the milestone
     * @param bodyParams:      extra body params not mandatory, keys accepted are:
     *                         <ul>
     *                            <li>
     *                                {@code "title"} -> the title of the milestone - [string]
     *                            </li>
     *                            <li>
     *                                {@code "state"} -> indicates the state of the issues to return, constants available
     *                                {@link OperationState} - [string, default open]
     *                            </li>
     *                            <li>
     *                                {@code "description"} -> a description of the milestone
     *                            </li>
     *                            <li>
     *                                {@code "due_on"} -> the milestone due date. This is a timestamp in ISO 8601 format:
     *                                {@code "YYYY-MM-DDTHH:MM:SSZ"} - [string]
     *                            </li>
     *                         </ul>
     * @param format           :              return type formatter -> {@link ReturnFormat}
     * @return milestone as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/milestones#update-a-milestone">
     * Update a milestone</a>
     **/
    @RequestPath(method = PATCH, path = "/repos/{owner}/{repo}/milestones/{milestone_number}")
    public <T> T updateMilestone(String owner, String repo, long milestoneNumber, Params bodyParams,
                                 ReturnFormat format) throws IOException {
        return returnMilestone(sendPatchRequest(REPOS_PATH + owner + "/" + repo + MILESTONES_PATH
                + "/" + milestoneNumber, bodyParams), format);
    }

    /**
     * Method to create a milestone
     *
     * @param milestoneResponse : obtained from GitHub's response
     * @param format            :              return type formatter -> {@link ReturnFormat}
     * @return milestone as {@code "format"} defines
     **/
    @Returner
    private <T> T returnMilestone(String milestoneResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(milestoneResponse);
            case LIBRARY_OBJECT:
                return (T) new Milestone(new JSONObject(milestoneResponse));
            default:
                return (T) milestoneResponse;
        }
    }

    /**
     * Method to delete a milestone
     *
     * @param repository: the repository where delete the comment
     * @param milestone:  the milestone to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/milestones#delete-a-milestone">
     * Delete a milestone</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/milestones/{milestone_number}")
    public boolean deleteMilestone(Repository repository, Milestone milestone) {
        return deleteMilestone(repository.getOwner().getLogin(), repository.getName(), milestone.getNumber());
    }

    /**
     * Method to delete a milestone
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param milestone: the milestone to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/milestones#delete-a-milestone">
     * Delete a milestone</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/milestones/{milestone_number}")
    public boolean deleteMilestone(String owner, String repo, Milestone milestone) {
        return deleteMilestone(owner, repo, milestone.getNumber());
    }

    /**
     * Method to delete a milestone
     *
     * @param repository:      the repository where delete the comment
     * @param milestoneNumber: the number that identifies the milestone
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/milestones#delete-a-milestone">
     * Delete a milestone</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/milestones/{milestone_number}")
    public boolean deleteMilestone(Repository repository, long milestoneNumber) {
        return deleteMilestone(repository.getOwner().getLogin(), repository.getName(), milestoneNumber);
    }

    /**
     * Method to delete a milestone
     *
     * @param owner:           the account owner of the repository. The name is not case-sensitive
     * @param repo:            the name of the repository. The name is not case-sensitive
     * @param milestoneNumber: the number that identifies the milestone
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/issues/milestones#delete-a-milestone">
     * Delete a milestone</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/milestones/{milestone_number}")
    public boolean deleteMilestone(String owner, String repo, long milestoneNumber) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + MILESTONES_PATH + "/" + milestoneNumber);
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
