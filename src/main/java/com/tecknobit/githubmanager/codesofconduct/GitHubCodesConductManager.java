package com.tecknobit.githubmanager.codesofconduct;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.codesofconduct.records.CodeConduct;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubCodesConductManager} class is useful to manage all GitHub's codes of conduct endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codes-of-conduct">
 * Codes of conduct</a>
 * @see GitHubManager
 **/
public class GitHubCodesConductManager extends GitHubManager {

    /**
     * {@code CODES_OF_CONDUCT_PATH} constant for {@code "codes_of_conduct"} path
     **/
    public static final String CODES_OF_CONDUCT_PATH = "codes_of_conduct";

    /**
     * Constructor to init a {@link GitHubCodesConductManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubCodesConductManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubCodesConductManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubCodesConductManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubCodesConductManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubCodesConductManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubCodesConductManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubCodesConductManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubCodesConductManager} <br>
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
    public GitHubCodesConductManager() {
        super();
    }

    /**
     * Method to get all codes of conduct <br>
     * Any params required
     *
     * @return codes of conduct list as {@link Collection} of {@link CodeConduct} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codes-of-conduct#get-all-codes-of-conduct">
     * Get all codes of conduct</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/codes_of_conduct")
    public Collection<CodeConduct> getAllCodesOfConduct() throws IOException {
        return getAllCodesOfConduct(LIBRARY_OBJECT);
    }

    /**
     * Method to get all codes of conduct
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return codes of conduct list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codes-of-conduct#get-all-codes-of-conduct">
     * Get all codes of conduct</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/codes_of_conduct")
    public <T> T getAllCodesOfConduct(ReturnFormat format) throws IOException {
        String codesList = sendGetRequest(CODES_OF_CONDUCT_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONArray(codesList);
            case LIBRARY_OBJECT:
                ArrayList<CodeConduct> codes = new ArrayList<>();
                JSONArray jCodes = new JSONArray(codesList);
                for (int j = 0; j < jCodes.length(); j++)
                    codes.add(new CodeConduct(jCodes.getJSONObject(j)));
                return (T) codes;
            default:
                return (T) codesList;
        }
    }

    /**
     * Method to get a single code of conduct
     *
     * @param key: key of the code of conduct
     * @return code of conduct as {@link CodeConduct} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codes-of-conduct#get-a-code-of-conduct">
     * Get a code of conduct</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/codes_of_conduct/{key}")
    public CodeConduct getSingleCodeOfConduct(String key) throws IOException {
        return getSingleCodeOfConduct(key, LIBRARY_OBJECT);
    }

    /**
     * Method to get a single code of conduct
     *
     * @param key:    key of the code of conduct
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return code of conduct as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/codes-of-conduct#get-a-code-of-conduct">
     * Get a code of conduct</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/codes_of_conduct/{key}")
    public <T> T getSingleCodeOfConduct(String key, ReturnFormat format) throws IOException {
        String codeResponse = sendGetRequest(CODES_OF_CONDUCT_PATH + "/" + key);
        switch (format) {
            case JSON:
                return (T) new JSONObject(codeResponse);
            case LIBRARY_OBJECT:
                return (T) new CodeConduct(new JSONObject(codeResponse));
            default:
                return (T) codeResponse;
        }
    }

}
