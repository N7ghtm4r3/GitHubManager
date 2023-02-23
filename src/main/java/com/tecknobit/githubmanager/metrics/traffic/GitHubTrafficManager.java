package com.tecknobit.githubmanager.metrics.traffic;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.metrics.traffic.records.PageViews;
import com.tecknobit.githubmanager.metrics.traffic.records.RepositoryClones;
import com.tecknobit.githubmanager.metrics.traffic.records.TopReferralPath;
import com.tecknobit.githubmanager.metrics.traffic.records.TopReferralSource;
import com.tecknobit.githubmanager.metrics.traffic.records.TrafficItem.TrafficListItem.Per;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubTrafficManager} class is useful to manage all GitHub's traffic endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/traffic">
 * Repository traffic</a>
 * @see GitHubManager
 **/
public class GitHubTrafficManager extends GitHubManager {

    /**
     * {@code TRAFFIC_PATH} constant for {@code "/traffic"} path
     **/
    public static final String TRAFFIC_PATH = "/traffic";

    /**
     * {@code TRAFFIC_CLONES_PATH} constant for {@code "/traffic/clones"} path
     **/
    public static final String TRAFFIC_CLONES_PATH = TRAFFIC_PATH + "/clones";

    /**
     * {@code TRAFFIC_POPULAR_PATH} constant for {@code "traffic/popular/"} path
     **/
    public static final String TRAFFIC_POPULAR_PATH = TRAFFIC_PATH + "/popular/";

    /**
     * {@code TRAFFIC_POPULAR_PATHS_PATH} constant for {@code "/traffic/popular/paths"} path
     **/
    public static final String TRAFFIC_POPULAR_PATHS_PATH = TRAFFIC_POPULAR_PATH + "paths";

    /**
     * {@code TRAFFIC_POPULAR_REFERRERS_PATH} constant for {@code "/traffic/popular/referrers"} path
     **/
    public static final String TRAFFIC_POPULAR_REFERRERS_PATH = TRAFFIC_POPULAR_PATH + "referrers";

    /**
     * {@code TRAFFIC_VIEWS_PATH} constant for {@code "/traffic/views"} path
     **/
    public static final String TRAFFIC_VIEWS_PATH = TRAFFIC_PATH + "/views";

    /**
     * Constructor to init a {@link GitHubTrafficManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubTrafficManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubTrafficManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubTrafficManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubTrafficManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubTrafficManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubTrafficManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubTrafficManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubTrafficManager} <br>
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
    public GitHubTrafficManager() {
        super();
    }

    /**
     * Method to get the total number of clones and breakdown per day or week for the last 14 days.
     * Timestamps are aligned to UTC midnight of the beginning of the day or week. Week begins on Monday
     *
     * @param repository: the repository from fetch the list
     * @return number of clones as {@link RepositoryClones} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/traffic#get-repository-clones">
     * Get repository clones</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/traffic/clones")
    public RepositoryClones getRepositoryClones(Repository repository) throws IOException {
        return getRepositoryClones(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the total number of clones and breakdown per day or week for the last 14 days.
     * Timestamps are aligned to UTC midnight of the beginning of the day or week. Week begins on Monday
     *
     * @param repository: the repository from fetch the list
     * @return number of clones as {@link RepositoryClones} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/traffic#get-repository-clones">
     * Get repository clones</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/traffic/clones")
    public <T> T getRepositoryClones(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryClones(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the total number of clones and breakdown per day or week for the last 14 days.
     * Timestamps are aligned to UTC midnight of the beginning of the day or week. Week begins on Monday
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return number of clones as {@link RepositoryClones} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/traffic#get-repository-clones">
     * Get repository clones</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/traffic/clones")
    public RepositoryClones getRepositoryClones(String owner, String repo) throws IOException {
        return getRepositoryClones(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the total number of clones and breakdown per day or week for the last 14 days.
     * Timestamps are aligned to UTC midnight of the beginning of the day or week. Week begins on Monday
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return number of clones as {@link RepositoryClones} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/traffic#get-repository-clones">
     * Get repository clones</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/traffic/clones")
    public <T> T getRepositoryClones(String owner, String repo, ReturnFormat format) throws IOException {
        return returnRepositoryClones(sendGetRequest(REPOS_PATH + owner + "/" + repo + TRAFFIC_CLONES_PATH),
                format);
    }

    /**
     * Method to get the total number of clones and breakdown per day or week for the last 14 days.
     * Timestamps are aligned to UTC midnight of the beginning of the day or week. Week begins on Monday
     *
     * @param repository: the repository from fetch the list
     * @param per:        the time frame to display results for
     * @return number of clones as {@link RepositoryClones} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/traffic#get-repository-clones">
     * Get repository clones</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/traffic/clones")
    public RepositoryClones getRepositoryClones(Repository repository, Per per) throws IOException {
        return getRepositoryClones(repository.getOwner().getLogin(), repository.getName(), per, LIBRARY_OBJECT);
    }

    /**
     * Method to get the total number of clones and breakdown per day or week for the last 14 days.
     * Timestamps are aligned to UTC midnight of the beginning of the day or week. Week begins on Monday
     *
     * @param repository: the repository from fetch the list
     * @param per:        the time frame to display results for
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return repository clones as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/traffic#get-repository-clones">
     * Get repository clones</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/traffic/clones")
    public <T> T getRepositoryClones(Repository repository, Per per, ReturnFormat format) throws IOException {
        return getRepositoryClones(repository.getOwner().getLogin(), repository.getName(), per, format);
    }

    /**
     * Method to get the total number of clones and breakdown per day or week for the last 14 days.
     * Timestamps are aligned to UTC midnight of the beginning of the day or week. Week begins on Monday
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param per:   the time frame to display results for
     * @return number of clones as {@link RepositoryClones} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/traffic#get-repository-clones">
     * Get repository clones</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/traffic/clones")
    public RepositoryClones getRepositoryClones(String owner, String repo, Per per) throws IOException {
        return getRepositoryClones(owner, repo, per, LIBRARY_OBJECT);
    }

    /**
     * Method to get the total number of clones and breakdown per day or week for the last 14 days.
     * Timestamps are aligned to UTC midnight of the beginning of the day or week. Week begins on Monday
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param per:   the time frame to display results for
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return repository clones as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/traffic#get-repository-clones">
     * Get repository clones</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/traffic/clones")
    public <T> T getRepositoryClones(String owner, String repo, Per per, ReturnFormat format) throws IOException {
        return returnRepositoryClones(sendGetRequest(REPOS_PATH + owner + "/" + repo + TRAFFIC_CLONES_PATH
                + "?per=" + per), format);
    }

    /**
     * Method to create a repository clones
     *
     * @param repositoryClonesResponse : obtained from GitHub's response
     * @param format                   :              return type formatter -> {@link ReturnFormat}
     * @return repository clones as {@code "format"} defines
     **/
    @Returner
    private <T> T returnRepositoryClones(String repositoryClonesResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(repositoryClonesResponse);
            case LIBRARY_OBJECT:
                return (T) new RepositoryClones(new JSONObject(repositoryClonesResponse));
            default:
                return (T) repositoryClonesResponse;
        }
    }

    /**
     * Method to get the top 10 popular contents over the last 14 days
     *
     * @param repository: the repository from fetch the list
     * @return top 10 popular contents as {@link ArrayList} of {@link TopReferralPath} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/traffic#get-top-referral-paths">
     * Get top referral paths</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/traffic/popular/paths")
    public ArrayList<TopReferralPath> getTopReferralPaths(Repository repository) throws IOException {
        return getTopReferralPaths(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the top 10 popular contents over the last 14 days
     *
     * @param repository: the repository from fetch the list
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return top 10 popular contents as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/traffic#get-top-referral-paths">
     * Get top referral paths</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/traffic/popular/paths")
    public <T> T getTopReferralPaths(Repository repository, ReturnFormat format) throws IOException {
        return getTopReferralPaths(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the top 10 popular contents over the last 14 days
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return top 10 popular contents as {@link ArrayList} of {@link TopReferralPath} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/traffic#get-top-referral-paths">
     * Get top referral paths</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/traffic/popular/paths")
    public ArrayList<TopReferralPath> getTopReferralPaths(String owner, String repo) throws IOException {
        return getTopReferralPaths(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the top 10 popular contents over the last 14 days
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return top 10 popular contents as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/traffic#get-top-referral-paths">
     * Get top referral paths</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/traffic/popular/paths")
    public <T> T getTopReferralPaths(String owner, String repo, ReturnFormat format) throws IOException {
        String referralPathsResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo
                + TRAFFIC_POPULAR_PATHS_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONArray(referralPathsResponse);
            case LIBRARY_OBJECT:
                ArrayList<TopReferralPath> topReferralPaths = new ArrayList<>();
                JSONArray jTopReferralPaths = new JSONArray(referralPathsResponse);
                for (int j = 0; j < jTopReferralPaths.length(); j++)
                    topReferralPaths.add(new TopReferralPath(jTopReferralPaths.getJSONObject(j)));
                return (T) topReferralPaths;
            default:
                return (T) referralPathsResponse;
        }
    }

    /**
     * Method to get the top 10 referrers over the last 14 days
     *
     * @param repository: the repository from fetch the list
     * @return top 10 referrers as {@link ArrayList} of {@link TopReferralSource} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/traffic#get-top-referral-sources">
     * Get top referral sources</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/traffic/popular/referrers")
    public ArrayList<TopReferralSource> getTopReferralSources(Repository repository) throws IOException {
        return getTopReferralSources(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the top 10 referrers over the last 14 days
     *
     * @param repository: the repository from fetch the list
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return top 10 popular contents as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/traffic#get-top-referral-sources">
     * Get top referral sources</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/traffic/popular/referrers")
    public <T> T getTopReferralSources(Repository repository, ReturnFormat format) throws IOException {
        return getTopReferralSources(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the top 10 referrers over the last 14 days
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return top 10 referrers as {@link ArrayList} of {@link TopReferralSource} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/traffic#get-top-referral-sources">
     * Get top referral sources</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/traffic/popular/referrers")
    public ArrayList<TopReferralSource> getTopReferralSources(String owner, String repo) throws IOException {
        return getTopReferralSources(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the top 10 referrers over the last 14 days
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return top 10 popular contents as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/traffic#get-top-referral-sources">
     * Get top referral sources</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/traffic/popular/referrers")
    public <T> T getTopReferralSources(String owner, String repo, ReturnFormat format) throws IOException {
        String referralSourcesResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo
                + TRAFFIC_POPULAR_REFERRERS_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONArray(referralSourcesResponse);
            case LIBRARY_OBJECT:
                ArrayList<TopReferralSource> topReferralSources = new ArrayList<>();
                JSONArray jTopReferralSources = new JSONArray(referralSourcesResponse);
                for (int j = 0; j < jTopReferralSources.length(); j++)
                    topReferralSources.add(new TopReferralSource(jTopReferralSources.getJSONObject(j)));
                return (T) topReferralSources;
            default:
                return (T) referralSourcesResponse;
        }
    }

    /**
     * Method to get the total number of views and breakdown per day or week for the last 14 days.
     * Timestamps are aligned to UTC midnight of the beginning of the day or week. Week begins on Monday
     *
     * @param repository: the repository from fetch the list
     * @return number of views as {@link PageViews} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/traffic#get-page-views">
     * Get page views</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/traffic/views")
    public PageViews getPageViews(Repository repository) throws IOException {
        return getPageViews(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the total number of views and breakdown per day or week for the last 14 days.
     * Timestamps are aligned to UTC midnight of the beginning of the day or week. Week begins on Monday
     *
     * @param repository: the repository from fetch the list
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return page views list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/traffic#get-page-views">
     * Get page views</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/traffic/views")
    public <T> T getPageViews(Repository repository, ReturnFormat format) throws IOException {
        return getPageViews(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get the total number of views and breakdown per day or week for the last 14 days.
     * Timestamps are aligned to UTC midnight of the beginning of the day or week. Week begins on Monday
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return number of views as {@link PageViews} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/traffic#get-page-views">
     * Get page views</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/traffic/views")
    public PageViews getPageViews(String owner, String repo) throws IOException {
        return getPageViews(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get the total number of views and breakdown per day or week for the last 14 days.
     * Timestamps are aligned to UTC midnight of the beginning of the day or week. Week begins on Monday
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return page views list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/traffic#get-page-views">
     * Get page views</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/traffic/views")
    public <T> T getPageViews(String owner, String repo, ReturnFormat format) throws IOException {
        return returnPageViews(sendGetRequest(REPOS_PATH + owner + "/" + repo + TRAFFIC_VIEWS_PATH),
                format);
    }

    /**
     * Method to get the total number of views and breakdown per day or week for the last 14 days.
     * Timestamps are aligned to UTC midnight of the beginning of the day or week. Week begins on Monday
     *
     * @param repository: the repository from fetch the list
     * @param per:        the time frame to display results for
     * @return number of views as {@link PageViews} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/traffic#get-page-views">
     * Get page views</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/traffic/views")
    public PageViews getPageViews(Repository repository, Per per) throws IOException {
        return getPageViews(repository.getOwner().getLogin(), repository.getName(), per, LIBRARY_OBJECT);
    }

    /**
     * Method to get the total number of views and breakdown per day or week for the last 14 days.
     * Timestamps are aligned to UTC midnight of the beginning of the day or week. Week begins on Monday
     *
     * @param repository: the repository from fetch the list
     * @param per:        the time frame to display results for
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return page views list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/traffic#get-page-views">
     * Get page views</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/traffic/views")
    public <T> T getPageViews(Repository repository, Per per, ReturnFormat format) throws IOException {
        return getPageViews(repository.getOwner().getLogin(), repository.getName(), per, format);
    }

    /**
     * Method to get the total number of views and breakdown per day or week for the last 14 days.
     * Timestamps are aligned to UTC midnight of the beginning of the day or week. Week begins on Monday
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param per:   the time frame to display results for
     * @return number of views as {@link PageViews} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/traffic#get-page-views">
     * Get page views</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/traffic/views")
    public PageViews getPageViews(String owner, String repo, Per per) throws IOException {
        return getPageViews(owner, repo, per, LIBRARY_OBJECT);
    }

    /**
     * Method to get the total number of views and breakdown per day or week for the last 14 days.
     * Timestamps are aligned to UTC midnight of the beginning of the day or week. Week begins on Monday
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param per:   the time frame to display results for
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return page views list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/traffic#get-page-views">
     * Get page views</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/traffic/views")
    public <T> T getPageViews(String owner, String repo, Per per, ReturnFormat format) throws IOException {
        return returnPageViews(sendGetRequest(REPOS_PATH + owner + "/" + repo + TRAFFIC_VIEWS_PATH
                + "?per=" + per), format);
    }

    /**
     * Method to create a page views list
     *
     * @param pageViewsResponse : obtained from GitHub's response
     * @param format            :              return type formatter -> {@link ReturnFormat}
     * @return page views list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnPageViews(String pageViewsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(pageViewsResponse);
            case LIBRARY_OBJECT:
                return (T) new PageViews(new JSONObject(pageViewsResponse));
            default:
                return (T) pageViewsResponse;
        }
    }

}
