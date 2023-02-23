package com.tecknobit.githubmanager.gitignore;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.gitignore.records.GitignoreTemplate;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubGitignoreManager} class is useful to manage all GitHub's gitignore endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gitignore">
 * Gitignore</a>
 * @see GitHubManager
 **/
public class GitHubGitignoreManager extends GitHubManager {

    /**
     * {@code GITIGNORE_TEMPLATES_PATH} constant for {@code "gitignore/templates"} path
     **/
    public static final String GITIGNORE_TEMPLATES_PATH = "gitignore/templates";

    /**
     * Constructor to init a {@link GitHubManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubGitignoreManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubGitignoreManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubGitignoreManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubGitignoreManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubManager} <br>
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
    public GitHubGitignoreManager() {
        super();
    }

    /**
     * Method to get the list of the all templates available to pass as an option when creating a repository <br>
     * No-any params required
     *
     * @return templates list as {@link ArrayList} of {@link String}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gitignore#get-all-gitignore-templates">
     * Get all gitignore templates</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/gitignore/templates")
    public ArrayList<String> getAllGitignoreTemplates() throws IOException {
        return getAllGitignoreTemplates(LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all templates available to pass as an option when creating a repository
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return templates list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gitignore#get-all-gitignore-templates">
     * Get all gitignore templates</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/gitignore/templates")
    public <T> T getAllGitignoreTemplates(ReturnFormat format) throws IOException {
        String templatesResponse = sendGetRequest(GITIGNORE_TEMPLATES_PATH);
        switch (format) {
            case JSON:
                return (T) new JSONArray(templatesResponse);
            case LIBRARY_OBJECT:
                ArrayList<String> templates = new ArrayList<>();
                JSONArray jTemplates = new JSONArray(templatesResponse);
                for (int j = 0; j < jTemplates.length(); j++)
                    templates.add(jTemplates.getString(j));
                return (T) templates;
            default:
                return (T) templatesResponse;
        }
    }

    /**
     * Method to get a gitignore template <br>
     * The API also allows fetching the source of a single template. Use the raw media type to get the raw contents
     *
     * @param name: name of the template to fetch
     * @return template as {@link ArrayList} of {@link GitignoreTemplate} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gitignore#get-a-gitignore-template">
     * Get a gitignore template</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/gitignore/templates/{name}")
    public GitignoreTemplate getGitignoreTemplate(String name) throws IOException {
        return getGitignoreTemplate(name, LIBRARY_OBJECT);
    }

    /**
     * Method to get a gitignore template <br>
     * The API also allows fetching the source of a single template. Use the raw media type to get the raw contents
     *
     * @param name:   name of the template to fetch
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return template as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/gitignore#get-a-gitignore-template">
     * Get a gitignore template</a>
     **/
    @Returner
    @RequestPath(method = GET, path = "/gitignore/templates/{name}")
    public <T> T getGitignoreTemplate(String name, ReturnFormat format) throws IOException {
        String templateResponse = sendGetRequest(GITIGNORE_TEMPLATES_PATH + "/" + name);
        switch (format) {
            case JSON:
                return (T) new JSONObject(templateResponse);
            case LIBRARY_OBJECT:
                return (T) new GitignoreTemplate(new JSONObject(templateResponse));
            default:
                return (T) templateResponse;
        }
    }

}
