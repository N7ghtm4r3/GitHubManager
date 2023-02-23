package com.tecknobit.githubmanager.meta;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.meta.records.GitHubAPIRoot;
import com.tecknobit.githubmanager.meta.records.GitHubMetaInformation;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.records.parents.GitHubResponse.returnStringsList;

/**
 * The {@code GitHubMetaManager} class is useful to manage all GitHub's meta endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/meta">
 * Meta</a>
 * @see GitHubManager
 **/
public class GitHubMetaManager extends GitHubManager {

    /**
     * {@code META_PATH} constant for {@code "meta"} path
     **/
    public static final String META_PATH = "meta";

    /**
     * {@code OCTOCAT_PATH} constant for {@code "octocat"} path
     **/
    public static final String OCTOCAT_PATH = "octocat";

    /**
     * {@code VERSIONS_PATH} constant for {@code "octocat"} path
     **/
    public static final String VERSIONS_PATH = "versions";

    /**
     * {@code ZEN_PATH} constant for {@code "zen"} path
     **/
    public static final String ZEN_PATH = "zen";

    /**
     * Constructor to init a {@link GitHubMetaManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubMetaManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubMetaManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubMetaManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubMetaManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubMetaManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubMetaManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubMetaManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubMetaManager} <br>
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
    public GitHubMetaManager() {
        super();
    }

    /**
     * Method to get GitHub API Root <br>
     * No-any params required
     *
     * @return GitHub API root as {@link GitHubAPIRoot} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/meta#github-api-root">
     * GitHub API Root</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/")
    public GitHubAPIRoot getGitHubAPIRoot() throws IOException {
        return getGitHubAPIRoot(LIBRARY_OBJECT);
    }

    /**
     * Method to get GitHub API Root
     *
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return GitHub API root as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/meta#github-api-root">
     * GitHub API Root</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/")
    public <T> T getGitHubAPIRoot(ReturnFormat format) throws IOException {
        String APIRootResponse = sendGetRequest("");
        switch (format) {
            case JSON:
                return (T) new JSONObject(APIRootResponse);
            case LIBRARY_OBJECT:
                return (T) new GitHubAPIRoot(new JSONObject(APIRootResponse));
            default:
                return (T) APIRootResponse;
        }
    }

    /**
     * Method to get meta information about GitHub, including a list of GitHub's IP addresses. For more information,
     * see "About GitHub's IP addresses." <br>
     * No-any params required
     *
     * @return GitHub meta information as {@link GitHubMetaInformation} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/meta#get-github-meta-information">
     * Get GitHub meta information</a>
     * @implNote <ul>
     * <li>
     * This endpoint returns both IPv4 and IPv6 addresses. However, not all features support IPv6.
     * You should refer to the specific documentation for each feature to determine if IPv6 is supported
     * </li>
     * <li>
     * The IP addresses shown in the documentation's response are only example values. You must always query
     * the API directly to get the latest list of IP addresses
     * </li>
     * </ul>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/meta")
    public GitHubMetaInformation getGitHubMetaInformation() throws IOException {
        return getGitHubMetaInformation(LIBRARY_OBJECT);
    }

    /**
     * Method to get meta information about GitHub, including a list of GitHub's IP addresses. For more information,
     * see "About GitHub's IP addresses."
     *
     * @param format :              return type formatter -> {@link ReturnFormat}
     * @return GitHub meta information as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/meta#get-github-meta-information">
     * Get GitHub meta information</a>
     * @implNote <ul>
     * <li>
     * This endpoint returns both IPv4 and IPv6 addresses. However, not all features support IPv6.
     * You should refer to the specific documentation for each feature to determine if IPv6 is supported
     * </li>
     * <li>
     * The IP addresses shown in the documentation's response are only example values. You must always query
     * the API directly to get the latest list of IP addresses
     * </li>
     * </ul>
     **/
    @Returner
    @RequestPath(method = GET, path = "/meta")
    public <T> T getGitHubMetaInformation(ReturnFormat format) throws IOException {
        String APIRootResponse = sendGetRequest(META_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONObject(APIRootResponse);
            case LIBRARY_OBJECT:
                return (T) new GitHubMetaInformation(new JSONObject(APIRootResponse));
            default:
                return (T) APIRootResponse;
        }
    }

    /**
     * Method to get the octocat as ASCII art <br>
     * No-any params required
     *
     * @return octocat as ASCII art as {@link String}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/meta#get-octocat">
     * Get Octocat</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/octocat")
    public String getOctocat() throws IOException {
        return getOctocat(null);
    }

    /**
     * Method to get the octocat as ASCII art
     *
     * @param s: the words to show in Octocat's speech bubble
     * @return octocat as ASCII art as {@link String}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/meta#get-octocat">
     * Get Octocat</a>
     **/
    @RequestPath(method = GET, path = "/octocat")
    public String getOctocat(String s) throws IOException {
        int sLength;
        if (s == null)
            s = getGitHubZen();
        if (s.contains("\n")) {
            int maxLength = 0;
            String[] sSplit = s.split("\n");
            for (String item : sSplit) {
                int itemLength = item.length();
                if (itemLength > maxLength)
                    maxLength = itemLength;
            }
            sLength = maxLength;
            StringBuilder sAssembler = new StringBuilder();
            int lineCounter = 0;
            for (String pS : sSplit) {
                int pSLength = pS.length();
                int end = maxLength - pSLength + 1;
                if (pSLength == maxLength) {
                    if (lineCounter == 0)
                        sAssembler.append("| ").append(pS).append(" |\n");
                    else {
                        sAssembler.append("                                       | ").append(pS).append(" ".repeat(end))
                                .append("|\n");
                    }
                } else {
                    if (lineCounter > 0) {
                        sAssembler.append("             MMMMMMMMMMMMMMMMMMMMMMM   | ").append(pS).append(" ".repeat(end))
                                .append("|\n");
                    } else
                        sAssembler.append("| ").append(pS).append(" ".repeat(end)).append("|\n");
                }
                lineCounter++;
            }
            s = sAssembler.toString();
        } else {
            sLength = s.length();
            s = "| " + s + " |\n";
        }
        int top = sLength + 2;
        int bottom = sLength - 2;
        int initialBottom = 3;
        if (bottom < 0) {
            bottom = 0;
            initialBottom = 2;
        }
        return "               MMM.           .MMM\n" +
                "               MMMMMMMMMMMMMMMMMMM\n" +
                "               MMMMMMMMMMMMMMMMMMM      " + "_".repeat(top) + "\n" +
                "              MMMMMMMMMMMMMMMMMMMMM    |" + " ".repeat(top) + "|\n" +
                "             MMMMMMMMMMMMMMMMMMMMMMM   " + s +
                "            MMMMMMMMMMMMMMMMMMMMMMMM   |_" + " ".repeat(initialBottom) + "_".repeat(bottom) + "|\n" +
                "            MMMM::- -:::::::- -::MMMM    |/\n" +
                "             MM~:~ 00~:::::~ 00~:~MM\n" +
                "        .. MMMMM::.00:::+:::.00::MMMMM ..\n" +
                "              .MM::::: ._. :::::MM.\n" +
                "                 MMMM;:::::;MMMM\n" +
                "          -MM        MMMMMMM\n" +
                "          ^  M+     MMMMMMMMM\n" +
                "              MMMMMMM MM MM MM\n" +
                "                   MM MM MM MM\n" +
                "                   MM MM MM MM\n" +
                "                .~~MM~MM~MM~MM~~.\n" +
                "             ~~~~MM:~MM~~~MM~:MM~~~~\n" +
                "            ~~~~~~==~==~~~==~==~~~~~~\n" +
                "             ~~~~~~==~==~==~==~~~~~~\n" +
                "                 :~==~==~==~==~~";
    }

    /**
     * Method to get all supported GitHub API versions <br>
     * No-any params required
     *
     * @return api versions as {@link ArrayList} of {@link String}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/meta#get-all-api-versions">
     * Get all API versions</a>
     **/
    @RequestPath(method = GET, path = "/versions")
    public ArrayList<String> getAllApiVersions() throws IOException {
        return returnStringsList(new JSONArray(sendGetRequest(VERSIONS_PATH)));
    }

    /**
     * Method to get a random sentence from the Zen of GitHub <br>
     * No-any params required
     *
     * @return random sentence from the Zen of GitHub as {@link String}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/meta#get-the-zen-of-github">
     * Get the Zen of GitHub</a>
     **/
    @RequestPath(method = GET, path = "/zen")
    public String getGitHubZen() throws IOException {
        return sendGetRequest(ZEN_PATH);
    }

}
