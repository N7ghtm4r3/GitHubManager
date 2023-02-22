package com.tecknobit.githubmanager.markdown;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;

import java.io.IOException;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.POST;

/**
 * The {@code GitHubMarkdownManager} class is useful to manage all GitHub's markdown endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/markdown">
 * Markdown</a>
 * @see GitHubManager
 **/
public class GitHubMarkdownManager extends GitHubManager {

    /**
     * {@code MARKDOWN_PATH} constant for {@code "markdown"} path
     **/
    public static final String MARKDOWN_PATH = "markdown";

    /**
     * {@code MARKDOWN_RAW_PATH} constant for {@code "markdown/raw"} path
     **/
    public static final String MARKDOWN_RAW_PATH = MARKDOWN_PATH + "/raw";

    /**
     * Constructor to init a {@link GitHubMarkdownManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubMarkdownManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubMarkdownManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubMarkdownManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubMarkdownManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubMarkdownManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubMarkdownManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubMarkdownManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubMarkdownManager} <br>
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
    public GitHubMarkdownManager() {
        super();
    }

    /**
     * Method to render a Markdown document
     *
     * @param text: the Markdown text to render in HTML
     * @return document rendered as {@link String}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/markdown#render-a-markdown-document">
     * Render a Markdown document</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/markdown")
    public String renderMarkdownDocument(String text) throws IOException {
        return renderMarkdownDocument(text, null);
    }

    /**
     * Method to render a Markdown document
     *
     * @param text:       the Markdown text to render in HTML
     * @param bodyParams: extra query params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "mode"} -> the rendering mode, constants available {@link RenderingMode}
     *                           - [string, default markdown]
     *                       </li>
     *                       <li>
     *                           {@code "context"} -> the repository context to use when creating references in gfm mode.
     *                           For example, setting context to octo-org/octo-repo will change the text #42 into an HTML
     *                           link to issue 42 in the octo-org/octo-repo repository - [string]
     *                       </li>
     *                    </ul>
     * @return document rendered as {@link String}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/markdown#render-a-markdown-document">
     * Render a Markdown document</a>
     **/
    @RequestPath(method = POST, path = "/markdown")
    public String renderMarkdownDocument(String text, Params bodyParams) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("text", text);
        return sendPostRequest(MARKDOWN_PATH, bodyParams);
    }

    /**
     * Method to render a Markdown document in raw mode
     *
     * @param text: the Markdown text to render in HTML
     * @return document rendered as {@link String}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/markdown#render-a-markdown-document-in-raw-mode">
     * Render a Markdown document in raw mode</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/markdown/raw")
    public String renderMarkdownRawDocument(String text) throws IOException {
        return renderMarkdownRawDocument(text, null);
    }

    /**
     * Method to render a Markdown document in raw mode
     *
     * @param text:       the Markdown text to render in HTML
     * @param bodyParams: extra query params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "mode"} -> the rendering mode, constants available {@link RenderingMode}
     *                           - [string, default markdown]
     *                       </li>
     *                       <li>
     *                           {@code "context"} -> the repository context to use when creating references in gfm mode.
     *                           For example, setting context to octo-org/octo-repo will change the text #42 into an HTML
     *                           link to issue 42 in the octo-org/octo-repo repository - [string]
     *                       </li>
     *                    </ul>
     * @return document rendered as {@link String}
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/markdown#render-a-markdown-document-in-raw-mode">
     * Render a Markdown document in raw mode</a>
     **/
    @RequestPath(method = POST, path = "/markdown/raw")
    public String renderMarkdownRawDocument(String text, Params bodyParams) throws IOException {
        if (bodyParams == null)
            bodyParams = new Params();
        bodyParams.addParam("text", text);
        mainHeaders.addHeader("Content-Type", "text/plain");
        return sendPostRequest(MARKDOWN_RAW_PATH, bodyParams);
    }

    /**
     * {@code RenderingMode} list of available rendering modes
     **/
    public enum RenderingMode {

        /**
         * {@code markdown} rendering mode
         **/
        markdown,

        /**
         * {@code gfm} rendering mode
         **/
        gfm

    }

}
