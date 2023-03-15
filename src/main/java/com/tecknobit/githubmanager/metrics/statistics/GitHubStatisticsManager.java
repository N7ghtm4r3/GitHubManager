package com.tecknobit.githubmanager.metrics.statistics;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.metrics.statistics.records.*;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.repositories.repositories.GitHubRepositoriesManager.CONTRIBUTORS_PATH;

/**
 * The {@code GitHubStatisticsManager} class is useful to manage all GitHub's statistics endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/statistics">
 * Repository statistics</a>
 * @see GitHubManager
 **/
public class GitHubStatisticsManager extends GitHubManager {

    /**
     * {@code STATS_PATH} constant for {@code "/stats"} path
     **/
    public static final String STATS_PATH = "/stats";

    /**
     * {@code STATS_CODE_FREQUENCY_PATH} constant for {@code "/stats/code_frequency"} path
     **/
    public static final String STATS_CODE_FREQUENCY_PATH = STATS_PATH + "/code_frequency";

    /**
     * {@code STATS_COMMIT_ACTIVITY_PATH} constant for {@code "/stats/commit_activity"} path
     **/
    public static final String STATS_COMMIT_ACTIVITY_PATH = STATS_PATH + "/commit_activity";

    /**
     * {@code STATS_CONTRIBUTORS_PATH} constant for {@code "/stats/contributors"} path
     **/
    public static final String STATS_CONTRIBUTORS_PATH = STATS_PATH + CONTRIBUTORS_PATH;

    /**
     * {@code STATS_PARTICIPATION_PATH} constant for {@code "/stats/participation"} path
     **/
    public static final String STATS_PARTICIPATION_PATH = STATS_PATH + "/participation";

    /**
     * {@code STATS_PUNCH_CARD_PATH} constant for {@code "/stats/punch_card"} path
     **/
    public static final String STATS_PUNCH_CARD_PATH = STATS_PATH + "/punch_card";

    /**
     * Constructor to init a {@link GitHubStatisticsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubStatisticsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubStatisticsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubStatisticsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubStatisticsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubStatisticsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubStatisticsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubStatisticsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubStatisticsManager} <br>
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
    public GitHubStatisticsManager() {
        super();
    }

    /**
     * Method to get a weekly aggregate of the number of additions and deletions pushed to a repository
     *
     * @param repository: the repository from fetch the list
     * @return weekly commit activity as {@link ArrayList} of {@link WeeklyCommitActivity} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/statistics#get-the-weekly-commit-activity">
     * Get the weekly commit activity</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/stats/code_frequency")
    public ArrayList<WeeklyCommitActivity> getWeeklyCommitActivity(Repository repository) throws IOException {
        return getWeeklyCommitActivity(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get a weekly aggregate of the number of additions and deletions pushed to a repository
     *
     * @param repository: the repository from fetch the list
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return weekly commit activity as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/statistics#get-the-weekly-commit-activity">
     * Get the weekly commit activity</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/stats/code_frequency")
    public <T> T getWeeklyCommitActivity(Repository repository, ReturnFormat format) throws IOException {
        return getWeeklyCommitActivity(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get a weekly aggregate of the number of additions and deletions pushed to a repository
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return weekly commit activity as {@link ArrayList} of {@link WeeklyCommitActivity} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/statistics#get-the-weekly-commit-activity">
     * Get the weekly commit activity</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/stats/code_frequency")
    public ArrayList<WeeklyCommitActivity> getWeeklyCommitActivity(String owner, String repo) throws IOException {
        return getWeeklyCommitActivity(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get a weekly aggregate of the number of additions and deletions pushed to a repository
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return weekly commit activity as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/statistics#get-the-weekly-commit-activity">
     * Get the weekly commit activity</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/stats/code_frequency")
    public <T> T getWeeklyCommitActivity(String owner, String repo, ReturnFormat format) throws IOException {
        String weeklyCommitActivityResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo
                + STATS_CODE_FREQUENCY_PATH);
        switch (format) {
            case JSON:
                try {
                    return (T) new JSONArray(weeklyCommitActivityResponse);
                } catch (JSONException e) {
                    return (T) new JSONArray();
                }
            case LIBRARY_OBJECT:
                ArrayList<WeeklyCommitActivity> weeklyCommitActivity = new ArrayList<>();
                try {
                    JSONArray jActivity = new JSONArray(weeklyCommitActivityResponse);
                    for (int j = 0; j < jActivity.length(); j++)
                        weeklyCommitActivity.add(new WeeklyCommitActivity(jActivity.getJSONArray(j)));
                } catch (JSONException ignored) {
                }
                return (T) weeklyCommitActivity;
            default:
                return (T) weeklyCommitActivityResponse;
        }
    }

    /**
     * Method to get the last year of commit activity grouped by week. The days array is a group of commits per day, starting on Sunday
     *
     * @param repository: the repository from fetch the list
     * @return last year of commit activity as {@link ArrayList} of {@link CommitActivity} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/statistics#get-the-last-year-of-commit-activity">
     * Get the last year of commit activity</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/stats/commit_activity")
    public ArrayList<CommitActivity> getLastYearCommitActivity(Repository repository) throws IOException {
        return getLastYearCommitActivity(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the last year of commit activity grouped by week. The days array is a group of commits per day, starting on Sunday
     *
     * @param repository: the repository from fetch the list
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return last year of commit activity as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/statistics#get-the-last-year-of-commit-activity">
     * Get the last year of commit activity</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/stats/commit_activity")
    public <T> T getLastYearCommitActivity(Repository repository, ReturnFormat format) throws IOException {
        return getLastYearCommitActivity(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the last year of commit activity grouped by week. The days array is a group of commits per day, starting on Sunday
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return last year of commit activity as {@link ArrayList} of {@link CommitActivity} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/statistics#get-the-last-year-of-commit-activity">
     * Get the last year of commit activity</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/stats/commit_activity")
    public ArrayList<CommitActivity> getLastYearCommitActivity(String owner, String repo) throws IOException {
        return getLastYearCommitActivity(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the last year of commit activity grouped by week. The days array is a group of commits per day, starting on Sunday
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return last year of commit activity as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/statistics#get-the-last-year-of-commit-activity">
     * Get the last year of commit activity</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/stats/commit_activity")
    public <T> T getLastYearCommitActivity(String owner, String repo, ReturnFormat format) throws IOException {
        String lastYearCommitActivityResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo
                + STATS_COMMIT_ACTIVITY_PATH);
        switch (format) {
            case JSON:
                try {
                    return (T) new JSONArray(lastYearCommitActivityResponse);
                } catch (JSONException e) {
                    return (T) new JSONArray();
                }
            case LIBRARY_OBJECT:
                ArrayList<CommitActivity> commitActivity = new ArrayList<>();
                try {
                    JSONArray jActivity = new JSONArray(lastYearCommitActivityResponse);
                    for (int j = 0; j < jActivity.length(); j++)
                        commitActivity.add(new CommitActivity(jActivity.getJSONObject(j)));
                } catch (JSONException ignored) {
                }
                return (T) commitActivity;
            default:
                return (T) lastYearCommitActivityResponse;
        }
    }

    /**
     * Method to get the total number of commits authored by the contributor. In addition, the response includes a
     * Weekly Hash (weeks array) with the following information:
     * <ul>
     *     <li>
     *         <b>w</b> - Start of the week, given as a Unix timestamp
     *     </li>
     *     <li>
     *         <b>a</b> - Number of additions
     *     </li>
     *     <li>
     *         <b>d</b> - Number of deletions
     *     </li>
     *     <li>
     *         <b>c</b> - Number of commits
     *     </li>
     * </ul>
     *
     * @param repository: the repository from fetch the list
     * @return all contributor commit activity as {@link ArrayList} of {@link ContributorActivity} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/statistics#get-all-contributor-commit-activity">
     * Get all contributor commit activity</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/stats/contributors")
    public ArrayList<ContributorActivity> getAllContributorCommitActivity(Repository repository) throws IOException {
        return getAllContributorCommitActivity(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the total number of commits authored by the contributor. In addition, the response includes a
     * Weekly Hash (weeks array) with the following information:
     * <ul>
     *     <li>
     *         <b>w</b> - Start of the week, given as a Unix timestamp
     *     </li>
     *     <li>
     *         <b>a</b> - Number of additions
     *     </li>
     *     <li>
     *         <b>d</b> - Number of deletions
     *     </li>
     *     <li>
     *         <b>c</b> - Number of commits
     *     </li>
     * </ul>
     *
     * @param repository: the repository from fetch the list
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return all contributor commit activity as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/statistics#get-all-contributor-commit-activity">
     * Get all contributor commit activity</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/stats/contributors")
    public <T> T getAllContributorCommitActivity(Repository repository, ReturnFormat format) throws IOException {
        return getAllContributorCommitActivity(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the total number of commits authored by the contributor. In addition, the response includes a
     * Weekly Hash (weeks array) with the following information:
     * <ul>
     *     <li>
     *         <b>w</b> - Start of the week, given as a Unix timestamp
     *     </li>
     *     <li>
     *         <b>a</b> - Number of additions
     *     </li>
     *     <li>
     *         <b>d</b> - Number of deletions
     *     </li>
     *     <li>
     *         <b>c</b> - Number of commits
     *     </li>
     * </ul>
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return all contributor commit activity as {@link ArrayList} of {@link ContributorActivity} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/statistics#get-all-contributor-commit-activity">
     * Get all contributor commit activity</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/stats/contributors")
    public ArrayList<ContributorActivity> getAllContributorCommitActivity(String owner, String repo) throws IOException {
        return getAllContributorCommitActivity(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the total number of commits authored by the contributor. In addition, the response includes a
     * Weekly Hash (weeks array) with the following information:
     * <ul>
     *     <li>
     *         <b>w</b> - Start of the week, given as a Unix timestamp
     *     </li>
     *     <li>
     *         <b>a</b> - Number of additions
     *     </li>
     *     <li>
     *         <b>d</b> - Number of deletions
     *     </li>
     *     <li>
     *         <b>c</b> - Number of commits
     *     </li>
     * </ul>
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return all contributor commit activity as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/statistics#get-all-contributor-commit-activity">
     * Get all contributor commit activity</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/stats/contributors")
    public <T> T getAllContributorCommitActivity(String owner, String repo, ReturnFormat format) throws IOException {
        String contributorActivityResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo
                + STATS_CONTRIBUTORS_PATH);
        switch (format) {
            case JSON:
                try {
                    return (T) new JSONArray(contributorActivityResponse);
                } catch (JSONException e) {
                    return (T) new JSONArray();
                }
            case LIBRARY_OBJECT:
                ArrayList<ContributorActivity> commitActivity = new ArrayList<>();
                try {
                    JSONArray jActivity = new JSONArray(contributorActivityResponse);
                    for (int j = 0; j < jActivity.length(); j++)
                        commitActivity.add(new ContributorActivity(jActivity.getJSONObject(j)));
                } catch (JSONException ignored) {
                }
                return (T) commitActivity;
            default:
                return (T) contributorActivityResponse;
        }
    }

    /**
     * Method to get the total commit counts for the owner and total commit counts in all. all is everyone combined,
     * including the owner in the last 52 weeks. If you'd like to get the commit counts for non-owners, you can subtract owner from all.
     * The array order is the oldest week (index 0) to most recent week
     *
     * @param repository: the repository from fetch the list
     * @return weekly commit count as {@link WeeklyCommitCount} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/statistics#get-the-weekly-commit-count">
     * Get the weekly commit count</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/stats/participation")
    public WeeklyCommitCount getWeeklyCommitCount(Repository repository) throws IOException {
        return getWeeklyCommitCount(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the total commit counts for the owner and total commit counts in all. all is everyone combined,
     * including the owner in the last 52 weeks. If you'd like to get the commit counts for non-owners, you can subtract owner from all.
     * The array order is the oldest week (index 0) to most recent week
     *
     * @param repository: the repository from fetch the list
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return weekly commit count as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/statistics#get-the-weekly-commit-count">
     * Get the weekly commit count</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/stats/participation")
    public <T> T getWeeklyCommitCount(Repository repository, ReturnFormat format) throws IOException {
        return getWeeklyCommitCount(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the total commit counts for the owner and total commit counts in all. all is everyone combined,
     * including the owner in the last 52 weeks. If you'd like to get the commit counts for non-owners, you can subtract owner from all.
     * The array order is the oldest week (index 0) to most recent week
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return weekly commit count as {@link WeeklyCommitCount} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/statistics#get-the-weekly-commit-count">
     * Get the weekly commit count</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/stats/participation")
    public WeeklyCommitCount getWeeklyCommitCount(String owner, String repo) throws IOException {
        return getWeeklyCommitCount(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the total commit counts for the owner and total commit counts in all. all is everyone combined,
     * including the owner in the last 52 weeks. If you'd like to get the commit counts for non-owners, you can subtract owner from all.
     * The array order is the oldest week (index 0) to most recent week
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return weekly commit count as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/statistics#get-the-weekly-commit-count">
     * Get the weekly commit count</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/stats/participation")
    public <T> T getWeeklyCommitCount(String owner, String repo, ReturnFormat format) throws IOException {
        String weeklyCommitCountResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo
                + STATS_PARTICIPATION_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONObject(weeklyCommitCountResponse);
            case LIBRARY_OBJECT:
                return (T) new WeeklyCommitCount(new JSONObject(weeklyCommitCountResponse));
            default:
                return (T) weeklyCommitCountResponse;
        }
    }

    /**
     * Method to get the hourly commit count for each day. <br>
     * Each array contains the day number, hour number, and number of commits:
     * <ul>
     *     <li>
     *          <b>0-6</b> Sunday - Saturday
     *     </li>
     *     <li>
     *          <b>0-23</b> Hour of day
     *     </li>
     *     <li>
     *      Number of commits
     *     </li>
     * </ul>
     * For example, [2, 14, 25] indicates that there were 25 total commits, during the 2:00pm hour on Tuesdays.
     * All times are based on the time zone of individual commits
     *
     * @param repository: the repository from fetch the list
     * @return hourly commit count for each day as {@link ArrayList} of {@link HourlyCommitCount} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/statistics#get-the-hourly-commit-count-for-each-day">
     * Get the hourly commit count for each day</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/stats/punch_card")
    public ArrayList<HourlyCommitCount> getEachDayHourlyCommitCount(Repository repository) throws IOException {
        return getEachDayHourlyCommitCount(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the hourly commit count for each day. <br>
     * Each array contains the day number, hour number, and number of commits:
     * <ul>
     *     <li>
     *          <b>0-6</b> Sunday - Saturday
     *     </li>
     *     <li>
     *          <b>0-23</b> Hour of day
     *     </li>
     *     <li>
     *      Number of commits
     *     </li>
     * </ul>
     * For example, [2, 14, 25] indicates that there were 25 total commits, during the 2:00pm hour on Tuesdays.
     * All times are based on the time zone of individual commits
     *
     * @param repository: the repository from fetch the list
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return hourly commit count for each day as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/statistics#get-the-hourly-commit-count-for-each-day">
     * Get the hourly commit count for each day</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/stats/punch_card")
    public <T> T getEachDayHourlyCommitCount(Repository repository, ReturnFormat format) throws IOException {
        return getEachDayHourlyCommitCount(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the hourly commit count for each day. <br>
     * Each array contains the day number, hour number, and number of commits:
     * <ul>
     *     <li>
     *          <b>0-6</b> Sunday - Saturday
     *     </li>
     *     <li>
     *          <b>0-23</b> Hour of day
     *     </li>
     *     <li>
     *      Number of commits
     *     </li>
     * </ul>
     * For example, [2, 14, 25] indicates that there were 25 total commits, during the 2:00pm hour on Tuesdays.
     * All times are based on the time zone of individual commits
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return hourly commit count for each day as {@link ArrayList} of {@link HourlyCommitCount} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/statistics#get-the-hourly-commit-count-for-each-day">
     * Get the hourly commit count for each day</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/stats/punch_card")
    public ArrayList<HourlyCommitCount> getEachDayHourlyCommitCount(String owner, String repo) throws IOException {
        return getEachDayHourlyCommitCount(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the hourly commit count for each day. <br>
     * Each array contains the day number, hour number, and number of commits:
     * <ul>
     *     <li>
     *          <b>0-6</b> Sunday - Saturday
     *     </li>
     *     <li>
     *          <b>0-23</b> Hour of day
     *     </li>
     *     <li>
     *      Number of commits
     *     </li>
     * </ul>
     * For example, [2, 14, 25] indicates that there were 25 total commits, during the 2:00pm hour on Tuesdays.
     * All times are based on the time zone of individual commits
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return hourly commit count for each day as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/statistics#get-the-hourly-commit-count-for-each-day">
     * Get the hourly commit count for each day</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/stats/punch_card")
    public <T> T getEachDayHourlyCommitCount(String owner, String repo, ReturnFormat format) throws IOException {
        String eachDayResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo + STATS_PUNCH_CARD_PATH);
        switch (format) {
            case JSON:
                try {
                    return (T) new JSONArray(eachDayResponse);
                } catch (JSONException e) {
                    return (T) new JSONArray();
                }
            case LIBRARY_OBJECT:
                ArrayList<HourlyCommitCount> hourlyCommitCount = new ArrayList<>();
                try {
                    JSONArray jActivity = new JSONArray(eachDayResponse);
                    for (int j = 0; j < jActivity.length(); j++)
                        hourlyCommitCount.add(new HourlyCommitCount(jActivity.getJSONArray(j)));
                } catch (JSONException ignored) {
                }
                return (T) hourlyCommitCount;
            default:
                return (T) eachDayResponse;
        }
    }

}
