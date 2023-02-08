package com.tecknobit.githubmanager.emojis;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.apimanager.apis.APIRequest.downloadFile;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubEmojisManager} class is useful to manage all GitHub's emojis endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/emojis">
 * Emojis</a>
 * @see GitHubManager
 **/
public class GitHubEmojisManager extends GitHubManager {

    /**
     * {@code EMOJIS_PATH} constant for {@code "emojis"} path
     **/
    public static final String EMOJIS_PATH = "emojis";

    /**
     * Constructor to init a {@link GitHubEmojisManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubEmojisManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubEmojisManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubEmojisManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubEmojisManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubEmojisManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubEmojisManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubEmojisManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubEmojisManager} <br>
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
    public GitHubEmojisManager() {
        super();
    }

    /**
     * Method to get all the emojis available to use on GitHub
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @param emojis: query emojis to fetch
     * @return emojis list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/emojis#get-emojis">
     * Get emojis</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/emojis")
    public <T> T getEmojis(ReturnFormat format, String... emojis) throws IOException {
        return returnEmojis(null, format, emojis);
    }

    /**
     * Method to get all the emojis available to use on GitHub
     *
     * @param suffix: suffix to save the emoji files
     * @param emojis: query emojis to fetch
     * @return emojis list as array of {@link File}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/emojis#get-emojis">
     * Get emojis</a>
     * @implNote the files will not be stored permanently
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/emojis")
    public File[] getEmojis(String suffix, String... emojis) throws IOException {
        return getEmojis(suffix, LIBRARY_OBJECT, emojis);
    }

    /**
     * Method to get all the emojis available to use on GitHub
     *
     * @param suffix: suffix to save the emoji files
     * @param format: return type formatter -> {@link ReturnFormat}
     * @param emojis: query emojis to fetch
     * @return emojis list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/emojis#get-emojis">
     * Get emojis</a>
     * @implNote the files will not be stored permanently
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/emojis")
    public <T> T getEmojis(String suffix, ReturnFormat format, String... emojis) throws IOException {
        return returnEmojis(suffix, format, emojis);
    }

    /**
     * Method to create an emojis list
     *
     * @param suffix: suffix to save the emoji files
     * @param format: return type formatter -> {@link ReturnFormat}
     * @param emojis: query emojis to fetch
     * @return emojis list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnEmojis(String suffix, ReturnFormat format, String... emojis) throws IOException {
        JSONObject emojisResponse = new JSONObject(sendGetRequest(EMOJIS_PATH));
        if (emojis.length > 0) {
            if (suffix != null && !suffix.startsWith("."))
                suffix = "." + suffix;
            for (int j = 0; j < emojis.length; j++)
                emojis[j] = emojis[j].toLowerCase();
            ArrayList<String> lEmojis = new ArrayList<>(Arrays.stream(emojis).toList());
            for (Iterator<String> it = emojisResponse.keys(); it.hasNext(); ) {
                String key = it.next();
                if (!lEmojis.contains(key))
                    it.remove();
            }
        }
        switch (format) {
            case JSON:
                return (T) emojisResponse;
            case LIBRARY_OBJECT:
                File[] files = new File[emojis.length];
                for (int j = 0; j < emojis.length; j++) {
                    String emoji = emojis[j];
                    files[j] = downloadFile(emojisResponse.getString(emoji), emoji + suffix, false);
                }
                return (T) files;
            default:
                return (T) emojisResponse.toString();
        }
    }

}
