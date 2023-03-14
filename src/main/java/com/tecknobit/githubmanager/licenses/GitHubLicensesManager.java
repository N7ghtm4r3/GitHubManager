package com.tecknobit.githubmanager.licenses;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.licenses.records.CommonLicense;
import com.tecknobit.githubmanager.licenses.records.License;
import com.tecknobit.githubmanager.licenses.records.RepositoryLicense;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubLicensesManager} class is useful to manage all GitHub's licenses endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/licenses#about-licenses">
 * Licenses</a>
 * @see GitHubManager
 **/
public class GitHubLicensesManager extends GitHubManager {

    /**
     * {@code LICENSES_PATH} constant for {@code "licenses"} path
     **/
    public static final String LICENSES_PATH = "licenses";

    /**
     * {@code LICENSE_PATH} constant for {@code "/license"} path
     **/
    public static final String LICENSE_PATH = "/license";

    /**
     * Constructor to init a {@link GitHubLicensesManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubLicensesManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubLicensesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubLicensesManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubLicensesManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubLicensesManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubLicensesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubLicensesManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubLicensesManager} <br>
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
    public GitHubLicensesManager() {
        super();
    }

    /**
     * Method to get all commonly used licenses <br>
     * No-any params required
     *
     * @return commons licenses list as {@link ArrayList} of {@link CommonLicense} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/licenses#get-all-commonly-used-licenses">
     * Get all commonly used licenses</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/licenses")
    public ArrayList<CommonLicense> getAllCommonlyUsedLicenses() throws IOException {
        return getAllCommonlyUsedLicenses(LIBRARY_OBJECT);
    }

    /**
     * Method to get all commonly used licenses
     *
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return common licenses as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/licenses#get-all-commonly-used-licenses">
     * Get all commonly used licenses</a>
     **/
    @RequestPath(method = GET, path = "/licenses")
    public <T> T getAllCommonlyUsedLicenses(ReturnFormat format) throws IOException {
        return returnCommonLicenses(sendGetRequest(LICENSES_PATH), format);
    }

    /**
     * Method to get all commonly used licenses
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "featured"} -> whether the license is featured - [boolean]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return commons licenses list as {@link ArrayList} of {@link CommonLicense} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/licenses#get-all-commonly-used-licenses">
     * Get all commonly used licenses</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/licenses")
    public ArrayList<CommonLicense> getAllCommonlyUsedLicenses(Params queryParams) throws IOException {
        return getAllCommonlyUsedLicenses(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get all commonly used licenses
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "featured"} -> whether the license is featured - [boolean]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format       :              return type formatter -> {@link ReturnFormat}
     * @return common licenses as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/licenses#get-all-commonly-used-licenses">
     * Get all commonly used licenses</a>
     **/
    @RequestPath(method = GET, path = "/licenses")
    public <T> T getAllCommonlyUsedLicenses(Params queryParams, ReturnFormat format) throws IOException {
        return returnCommonLicenses(sendGetRequest(LICENSES_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a common licenses
     *
     * @param commonLicensesResponse : obtained from GitHub's response
     * @param format                 :              return type formatter -> {@link ReturnFormat}
     * @return common licenses as {@code "format"} defines
     **/
    @Returner
    private <T> T returnCommonLicenses(String commonLicensesResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(commonLicensesResponse);
            case LIBRARY_OBJECT:
                ArrayList<CommonLicense> commonLicenses = new ArrayList<>();
                JSONArray jLicenses = new JSONArray(commonLicensesResponse);
                for (int j = 0; j < jLicenses.length(); j++)
                    commonLicenses.add(new CommonLicense(jLicenses.getJSONObject(j)));
                return (T) commonLicenses;
            default:
                return (T) commonLicensesResponse;
        }
    }

    /**
     * Method to get a license
     *
     * @param license: license name to fetch
     * @return license as {@link License} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/licenses#get-a-license">
     * Get a license</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/licenses/{license}")
    public License getLicense(String license) throws IOException {
        return getLicense(license, LIBRARY_OBJECT);
    }

    /**
     * Method to get a license
     *
     * @param license: license name to fetch
     * @param format   :              return type formatter -> {@link ReturnFormat}
     * @return license as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/licenses#get-a-license">
     * Get a license</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/licenses/{license}")
    public <T> T getLicense(String license, ReturnFormat format) throws IOException {
        String licenseResponse = sendGetRequest(LICENSES_PATH + "/" + license);
        switch (format) {
            case JSON:
                return (T) new JSONObject(licenseResponse);
            case LIBRARY_OBJECT:
                return (T) new License(new JSONObject(licenseResponse));
            default:
                return (T) licenseResponse;
        }
    }

    /**
     * Method to get a license <br>
     * This method returns the contents of the repository's license file, if one is detected. <br>
     * Similar to Get repository content, this method also supports custom media types for retrieving the raw license
     * content or rendered license HTML
     *
     * @param repository: the repository from fetch its license
     * @return license as {@link License} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/licensesa#get-the-license-for-a-repository">
     * Get the license for a repository</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/license")
    public RepositoryLicense getRepositoryLicense(Repository repository) throws IOException {
        return getRepositoryLicense(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get a license <br>
     * This method returns the contents of the repository's license file, if one is detected. <br>
     * Similar to Get repository content, this method also supports custom media types for retrieving the raw license
     * content or rendered license HTML
     *
     * @param repository: the repository from fetch its license
     * @param format      :              return type formatter -> {@link ReturnFormat}
     * @return repository license as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/licensesa#get-the-license-for-a-repository">
     * Get the license for a repository</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/license")
    public <T> T getRepositoryLicense(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryLicense(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get a license <br>
     * This method returns the contents of the repository's license file, if one is detected. <br>
     * Similar to Get repository content, this method also supports custom media types for retrieving the raw license
     * content or rendered license HTML
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return license as {@link License} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/licensesa#get-the-license-for-a-repository">
     * Get the license for a repository</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/license")
    public RepositoryLicense getRepositoryLicense(String owner, String repo) throws IOException {
        return getRepositoryLicense(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get a license <br>
     * This method returns the contents of the repository's license file, if one is detected. <br>
     * Similar to Get repository content, this method also supports custom media types for retrieving the raw license
     * content or rendered license HTML
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return repository license as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/licensesa#get-the-license-for-a-repository">
     * Get the license for a repository</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/license")
    public <T> T getRepositoryLicense(String owner, String repo, ReturnFormat format) throws IOException {
        String licenseResponse = sendGetRequest(REPOS_PATH + owner + "/" + repo + LICENSE_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONObject(licenseResponse);
            case LIBRARY_OBJECT:
                return (T) new RepositoryLicense(new JSONObject(licenseResponse));
            default:
                return (T) licenseResponse;
        }
    }

}
