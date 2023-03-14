package com.tecknobit.githubmanager.metrics.community;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.metrics.community.records.CommunityProfile;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import org.json.JSONObject;

import java.io.IOException;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubCommunityManager} class is useful to manage all GitHub's community endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/community">
 * Community metrics</a>
 * @see GitHubManager
 **/
public class GitHubCommunityManager extends GitHubManager {

    /**
     * {@code COMMUNITY_PROFILE_PATH} constant for {@code "/community/profile"} path
     **/
    public static final String COMMUNITY_PROFILE_PATH = "/community/profile";

    /**
     * Constructor to init a {@link GitHubCommunityManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubCommunityManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubCommunityManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubCommunityManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubCommunityManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubCommunityManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubCommunityManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubCommunityManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubCommunityManager} <br>
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
    public GitHubCommunityManager() {
        super();
    }

    /**
     * Method to get all the community profile metrics for a repository. The repository cannot be a fork.
     * The returned metrics include an overall health score, the repository description, the presence of documentation,
     * the detected code of conduct, the detected license, and the presence of ISSUE_TEMPLATE, PULL_REQUEST_TEMPLATE,
     * README, and CONTRIBUTING files.
     * The {@code "health_percentage"} score is defined as a percentage of how many of these four documents are present: README,
     * CONTRIBUTING, LICENSE, and CODE_OF_CONDUCT. For example, if all four documents are present, then the
     * {@code "health_percentage"} is 100. If only one is present, then the {@code "health_percentage"} is 25.
     * {@code "content_reports_enabled"} is only returned for organization-owned repositories
     *
     * @param repository: the repository from fetch the community profile metrics
     * @return community profile as {@link CommunityProfile} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/community">
     * Get community profile metrics</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/community/profile")
    public CommunityProfile getCommunityProfileMetrics(Repository repository) throws IOException {
        return getCommunityProfileMetrics(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get all the community profile metrics for a repository. The repository cannot be a fork.
     * The returned metrics include an overall health score, the repository description, the presence of documentation,
     * the detected code of conduct, the detected license, and the presence of ISSUE_TEMPLATE, PULL_REQUEST_TEMPLATE,
     * README, and CONTRIBUTING files.
     * The {@code "health_percentage"} score is defined as a percentage of how many of these four documents are present: README,
     * CONTRIBUTING, LICENSE, and CODE_OF_CONDUCT. For example, if all four documents are present, then the
     * {@code "health_percentage"} is 100. If only one is present, then the {@code "health_percentage"} is 25.
     * {@code "content_reports_enabled"} is only returned for organization-owned repositories
     *
     * @param repository: the repository from fetch the community profile metrics
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return community profile metrics as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/community">
     * Get community profile metrics</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/community/profile")
    public <T> T getCommunityProfileMetrics(Repository repository, ReturnFormat format) throws IOException {
        return getCommunityProfileMetrics(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get all the community profile metrics for a repository. The repository cannot be a fork.
     * The returned metrics include an overall health score, the repository description, the presence of documentation,
     * the detected code of conduct, the detected license, and the presence of ISSUE_TEMPLATE, PULL_REQUEST_TEMPLATE,
     * README, and CONTRIBUTING files.
     * The {@code "health_percentage"} score is defined as a percentage of how many of these four documents are present: README,
     * CONTRIBUTING, LICENSE, and CODE_OF_CONDUCT. For example, if all four documents are present, then the
     * {@code "health_percentage"} is 100. If only one is present, then the {@code "health_percentage"} is 25.
     * {@code "content_reports_enabled"} is only returned for organization-owned repositories
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return community profile as {@link CommunityProfile} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/community">
     * Get community profile metrics</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/community/profile")
    public CommunityProfile getCommunityProfileMetrics(String owner, String repo) throws IOException {
        return getCommunityProfileMetrics(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get all the community profile metrics for a repository. The repository cannot be a fork.
     * The returned metrics include an overall health score, the repository description, the presence of documentation,
     * the detected code of conduct, the detected license, and the presence of ISSUE_TEMPLATE, PULL_REQUEST_TEMPLATE,
     * README, and CONTRIBUTING files.
     * The {@code "health_percentage"} score is defined as a percentage of how many of these four documents are present: README,
     * CONTRIBUTING, LICENSE, and CODE_OF_CONDUCT. For example, if all four documents are present, then the
     * {@code "health_percentage"} is 100. If only one is present, then the {@code "health_percentage"} is 25.
     * {@code "content_reports_enabled"} is only returned for organization-owned repositories
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return community profile metrics as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/community">
     * Get community profile metrics</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/community/profile")
    public <T> T getCommunityProfileMetrics(String owner, String repo, ReturnFormat format) throws IOException {
        String communityProfileResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo + COMMUNITY_PROFILE_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONObject(communityProfileResponse);
            case LIBRARY_OBJECT:
                return (T) new CommunityProfile(new JSONObject(communityProfileResponse));
            default:
                return (T) communityProfileResponse;
        }
    }

}
