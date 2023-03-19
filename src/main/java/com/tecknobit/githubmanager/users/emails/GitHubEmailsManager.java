package com.tecknobit.githubmanager.users.emails;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.users.emails.records.GitHubEmail;
import com.tecknobit.githubmanager.users.emails.records.GitHubEmail.EmailVisibility;
import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubEmailsManager} class is useful to manage all GitHub's emails endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/emails">
 * Emails</a>
 * @see GitHubManager
 **/
public class GitHubEmailsManager extends GitHubManager {

    /**
     * {@code USER_EMAILS_PATH} constant for {@code "user/emails"} path
     **/
    public static final String USER_EMAILS_PATH = USER_PATH + "/emails";

    /**
     * Constructor to init a {@link GitHubEmailsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubEmailsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubEmailsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubEmailsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubEmailsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubEmailsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubEmailsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubEmailsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubEmailsManager} <br>
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
    public GitHubEmailsManager() {
        super();
    }

    /**
     * Method to set the visibility for your primary email addresses
     *
     * @param visibility: denotes whether an email is publicly visible
     * @return emails list as {@link ArrayList} of {@link GitHubEmail} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/emails#set-primary-email-visibility-for-the-authenticated-user">
     * Set primary email visibility for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/user/email/visibility")
    public ArrayList<GitHubEmail> setPrimaryEmailVisibility(EmailVisibility visibility) throws IOException {
        return setPrimaryEmailVisibility(visibility, LIBRARY_OBJECT);
    }

    /**
     * Method to set the visibility for your primary email addresses
     *
     * @param visibility: denotes whether an email is publicly visible
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return emails list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/emails#set-primary-email-visibility-for-the-authenticated-user">
     * Set primary email visibility for the authenticated user</a>
     **/
    @RequestPath(method = PATCH, path = "/user/email/visibility")
    public <T> T setPrimaryEmailVisibility(EmailVisibility visibility, ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("visibility", visibility.toString());
        return returnEmails(sendPatchRequest(USER_PATH + "/email/visibility", payload), format);
    }

    /**
     * Method to get the list of the all of your email addresses, and specifies which one is visible to the public.
     * This endpoint is accessible with the {@code "user:email"} scope <br>
     * No-any params required
     *
     * @return emails list as {@link ArrayList} of {@link GitHubEmail} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/emails#list-email-addresses-for-the-authenticated-user">
     * List email addresses for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/emails")
    public ArrayList<GitHubEmail> getEmailAddresses() throws IOException {
        return getEmailAddresses(LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all of your email addresses, and specifies which one is visible to the public.
     * This endpoint is accessible with the {@code "user:email"} scope
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return emails list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/emails#list-email-addresses-for-the-authenticated-user">
     * List email addresses for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/emails")
    public <T> T getEmailAddresses(ReturnFormat format) throws IOException {
        return returnEmails(sendGetRequest(USER_EMAILS_PATH), format);
    }

    /**
     * Method to get the list of the all of your email addresses, and specifies which one is visible to the public.
     * This endpoint is accessible with the {@code "user:email"} scope
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return emails list as {@link ArrayList} of {@link GitHubEmail} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/emails#list-email-addresses-for-the-authenticated-user">
     * List email addresses for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/emails")
    public ArrayList<GitHubEmail> getEmailAddresses(Params queryParams) throws IOException {
        return getEmailAddresses(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all of your email addresses, and specifies which one is visible to the public.
     * This endpoint is accessible with the {@code "user:email"} scope
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return emails list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/emails#list-email-addresses-for-the-authenticated-user">
     * List email addresses for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/emails")
    public <T> T getEmailAddresses(Params queryParams, ReturnFormat format) throws IOException {
        return returnEmails(sendGetRequest(USER_EMAILS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to add an email address for the authenticated user
     *
     * @param emails: add one or more email addresses to your GitHub account. Must contain at least one email address.
     *                Note: Alternatively, you can pass a single email address or an array of emails addresses directly,
     *                but we recommend that you pass an object using the emails key
     * @return emails list as {@link ArrayList} of {@link GitHubEmail} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/emails#add-an-email-address-for-the-authenticated-user">
     * Add an email address for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/user/emails")
    public ArrayList<GitHubEmail> addEmailAddress(String... emails) throws IOException {
        return addEmailAddress(LIBRARY_OBJECT, emails);
    }

    /**
     * Method to add an email address for the authenticated user
     *
     * @param emails: add one or more email addresses to your GitHub account. Must contain at least one email address.
     *                Note: Alternatively, you can pass a single email address or an array of emails addresses directly,
     *                but we recommend that you pass an object using the emails key
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return emails list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/emails#add-an-email-address-for-the-authenticated-user">
     * Add an email address for the authenticated user</a>
     **/
    @RequestPath(method = POST, path = "/user/emails")
    public <T> T addEmailAddress(ReturnFormat format, String... emails) throws IOException {
        Params payload = new Params();
        payload.addParam("emails", emails);
        return returnEmails(sendPostRequest(USER_EMAILS_PATH, payload), format);
    }

    /**
     * Method to delete an email address for the authenticated user
     *
     * @param emails: email addresses associated with the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} method if not successful
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/emails#add-an-email-address-for-the-authenticated-user">
     * Delete an email address for the authenticated user</a>
     **/
    @RequestPath(method = DELETE, path = "/user/emails")
    public boolean deleteEmailAddress(String... emails) throws IOException {
        Params payload = new Params();
        payload.addParam("emails", emails);
        HashMap<String, Object> result = sendDeleteRequest(USER_EMAILS_PATH, payload);
        Object success = result.get("success");
        if (success != null)
            return true;
        else
            throw new IOException(result.get("error").toString());
    }

    /**
     * Method to get the list of your publicly visible email address, which you can set with the Set primary email
     * visibility for the authenticated user endpoint. This endpoint is accessible with the {@code "user:email"} scope <br>
     * No-any params required
     *
     * @return emails list as {@link ArrayList} of {@link GitHubEmail} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/emails#list-public-email-addresses-for-the-authenticated-user">
     * List public email addresses for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/public_emails")
    public ArrayList<GitHubEmail> getPublicEmailAddresses() throws IOException {
        return getPublicEmailAddresses(LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of your publicly visible email address, which you can set with the Set primary email
     * visibility for the authenticated user endpoint. This endpoint is accessible with the {@code "user:email"} scope
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return emails list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/emails#list-public-email-addresses-for-the-authenticated-user">
     * List public email addresses for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/public_emails")
    public <T> T getPublicEmailAddresses(ReturnFormat format) throws IOException {
        return returnEmails(sendGetRequest(USER_EMAILS_PATH), format);
    }

    /**
     * Method to get the list of your publicly visible email address, which you can set with the Set primary email
     * visibility for the authenticated user endpoint. This endpoint is accessible with the {@code "user:email"} scope
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return emails list as {@link ArrayList} of {@link GitHubEmail} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/emails#list-public-email-addresses-for-the-authenticated-user">
     * List public email addresses for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/public_emails")
    public ArrayList<GitHubEmail> getPublicEmailAddresses(Params queryParams) throws IOException {
        return getPublicEmailAddresses(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of your publicly visible email address, which you can set with the Set primary email
     * visibility for the authenticated user endpoint. This endpoint is accessible with the {@code "user:email"} scope
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return emails list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/emails#list-public-email-addresses-for-the-authenticated-user">
     * List public email addresses for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/public_emails")
    public <T> T getPublicEmailAddresses(Params queryParams, ReturnFormat format) throws IOException {
        return returnEmails(sendGetRequest(USER_PATH + "/public_emails"), format);
    }

    /**
     * Method to create an emails list
     *
     * @param emailsResponse: obtained from GitHub's response
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return emails list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnEmails(String emailsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(emailsResponse);
            case LIBRARY_OBJECT:
                ArrayList<GitHubEmail> emails = new ArrayList<>();
                JSONArray jEmails = new JSONArray(emailsResponse);
                for (int j = 0; j < jEmails.length(); j++)
                    emails.add(new GitHubEmail(jEmails.getJSONObject(j)));
                return (T) emails;
            default:
                return (T) emailsResponse;
        }
    }

}
