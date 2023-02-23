package com.tecknobit.githubmanager.apps.oauthapps.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.actions.selfhosted.runners.records.GitHubToken;
import com.tecknobit.githubmanager.apps.apps.records.Installation;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code ScopedAccessToken} class is useful to format a GitHub's scoped access token
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/apps/oauth-applications#check-a-token">Check a token</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/apps/oauth-applications#reset-a-token">Reset a token</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/apps/oauth-applications#create-a-scoped-access-token">
 *             Create a scoped access token</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see GitHubToken
 **/
public class ScopedAccessToken extends GitHubToken {

    /**
     * {@code id} the id of the scoped access token
     **/
    private final long id;

    /**
     * {@code url} the url of the scoped access token
     **/
    private final String url;

    /**
     * {@code scopes} list of scopes that this authorization is in
     **/
    private final ArrayList<String> scopes;

    /**
     * {@code tokenLastEight} the token last eight of the scoped access token
     **/
    private final String tokenLastEight;

    /**
     * {@code hashedToken} the hashed token of the scoped access token
     **/
    private final String hashedToken;

    /**
     * {@code id} the app of the scoped access token
     **/
    private final App app;

    /**
     * {@code note} the note the scoped access token
     **/
    private final String note;

    /**
     * {@code noteUrl} the note url of the scoped access token
     **/
    private final String noteUrl;

    /**
     * {@code updatedAt} the update time of the scoped access token
     **/
    private final String updatedAt;

    /**
     * {@code createdAt} the creation time of the scoped access token
     **/
    private final String createdAt;

    /**
     * {@code fingerprint} the fingerprint of the scoped access token
     **/
    private final String fingerprint;

    /**
     * {@code user} the user of the scoped access token
     **/
    private final User user;

    /**
     * {@code installation} the installation of the scoped access token
     **/
    private final Installation installation;

    /**
     * Constructor to init a {@link ScopedAccessToken}
     *
     * @param token          : the token used for authentication
     * @param expiresAt      :the time this token expires
     * @param id             : the id of the scoped access token
     * @param url            :the url of the scoped access token
     * @param scopes         : the token used for authentication
     * @param tokenLastEight :the token last eight of the scoped access token
     * @param hashedToken    : the hashed token of the scoped access token
     * @param app            : the app of the scoped access token
     * @param note           : the note of the scoped access token
     * @param noteUrl        :the note url of the scoped access token
     * @param updatedAt      :the update time of the scoped access token
     * @param createdAt      : the creation time of the scoped access token
     * @param fingerprint    : the fingerprint of the scoped access token
     * @param user           : the user of the scoped access token
     * @param installation:  the installation of the scoped access token
     **/
    public ScopedAccessToken(String token, String expiresAt, long id, String url, ArrayList<String> scopes,
                             String tokenLastEight, String hashedToken, App app, String note, String noteUrl,
                             String updatedAt, String createdAt, String fingerprint, User user, Installation installation) {
        super(token, expiresAt);
        this.id = id;
        this.url = url;
        this.scopes = scopes;
        this.tokenLastEight = tokenLastEight;
        this.hashedToken = hashedToken;
        this.app = app;
        this.note = note;
        this.noteUrl = noteUrl;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.fingerprint = fingerprint;
        this.user = user;
        this.installation = installation;
    }

    /**
     * Constructor to init a {@link ScopedAccessToken}
     *
     * @param jScopedAccessToken : scoped access token details as {@link JSONObject}
     **/
    public ScopedAccessToken(JSONObject jScopedAccessToken) throws Exception {
        super(jScopedAccessToken);
        id = hResponse.getLong("id", 0);
        url = hResponse.getString("url");
        scopes = returnStringsList(hResponse.getJSONArray("scopes"));
        tokenLastEight = hResponse.getString("token_last_eight");
        hashedToken = hResponse.getString("hashed_token");
        app = new App(hResponse.getJSONObject("app", new JSONObject()));
        note = hResponse.getString("note");
        noteUrl = hResponse.getString("note_url");
        updatedAt = hResponse.getString("updated_at");
        createdAt = hResponse.getString("created_at");
        fingerprint = hResponse.getString("fingerprint");
        user = new User(hResponse.getJSONObject("user", new JSONObject()));
        installation = new Installation(hResponse.getJSONObject("installation", new JSONObject()));
    }

    /**
     * Method to get {@link #id} instance <br>
     * No-any params required
     *
     * @return {@link #id} instance as long
     **/
    public long getId() {
        return id;
    }

    /**
     * Method to get {@link #url} instance <br>
     * No-any params required
     *
     * @return {@link #url} instance as {@link String}
     **/
    public String getUrl() {
        return url;
    }

    /**
     * Method to get {@link #scopes} instance <br>
     * No-any params required
     *
     * @return {@link #scopes} instance as {@link ArrayList} of {@link String}
     **/
    public ArrayList<String> getScopes() {
        return scopes;
    }

    /**
     * Method to get {@link #tokenLastEight} instance <br>
     * No-any params required
     *
     * @return {@link #tokenLastEight} instance as {@link String}
     **/
    public String getTokenLastEight() {
        return tokenLastEight;
    }

    /**
     * Method to get {@link #hashedToken} instance <br>
     * No-any params required
     *
     * @return {@link #hashedToken} instance as {@link String}
     **/
    public String getHashedToken() {
        return hashedToken;
    }

    /**
     * Method to get {@link #app} instance <br>
     * No-any params required
     *
     * @return {@link #app} instance as {@link App}
     **/
    public App getApp() {
        return app;
    }

    /**
     * Method to get {@link #note} instance <br>
     * No-any params required
     *
     * @return {@link #note} instance as {@link String}
     **/
    public String getNote() {
        return note;
    }

    /**
     * Method to get {@link #noteUrl} instance <br>
     * No-any params required
     *
     * @return {@link #noteUrl} instance as {@link String}
     **/
    public String getNoteUrl() {
        return noteUrl;
    }

    /**
     * Method to get {@link #updatedAt} instance <br>
     * No-any params required
     *
     * @return {@link #updatedAt} instance as {@link String}
     **/
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Method to get {@link #updatedAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #updatedAt} timestamp as long
     **/
    public long getUpdatedAtTimestamp() {
        return getDateTimestamp(updatedAt);
    }

    /**
     * Method to get {@link #createdAt} instance <br>
     * No-any params required
     *
     * @return {@link #createdAt} instance as {@link String}
     **/
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Method to get {@link #createdAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #createdAt} timestamp as long
     **/
    public long getCreatedAtTimestamp() {
        return getDateTimestamp(createdAt);
    }

    /**
     * Method to get {@link #fingerprint} instance <br>
     * No-any params required
     *
     * @return {@link #fingerprint} instance as {@link String}
     **/
    public String getFingerprint() {
        return fingerprint;
    }

    /**
     * Method to get {@link #user} instance <br>
     * No-any params required
     *
     * @return {@link #user} instance as {@link User}
     **/
    public User getUser() {
        return user;
    }

    /**
     * Method to get {@link #installation} instance <br>
     * No-any params required
     *
     * @return {@link #installation} instance as {@link Installation}
     **/
    public Installation getInstallation() {
        return installation;
    }

    /**
     * The {@code App} class is useful to format a GitHub's app for a scoped access token
     *
     * @author N7ghtm4r3 - Tecknobit
     **/
    public static class App {

        /**
         * {@code url} the url of the app
         **/
        private final String url;

        /**
         * {@code name} the name of the app
         **/
        private final String name;

        /**
         * {@code clientId} the client id of the app
         **/
        private final String clientId;

        /**
         * Constructor to init a {@link App}
         *
         * @param url      : the url of the app
         * @param name     : the name of the app
         * @param clientId : the client id of the app
         **/
        public App(String url, String name, String clientId) {
            this.url = url;
            this.name = name;
            this.clientId = clientId;
        }

        /**
         * Constructor to init a {@link App}
         *
         * @param jApp : app details as {@link JSONObject}
         **/
        public App(JSONObject jApp) {
            JsonHelper hApp = new JsonHelper(jApp);
            url = hApp.getString("url");
            name = hApp.getString("name");
            clientId = hApp.getString("client_id");
        }

        /**
         * Method to get {@link #url} instance <br>
         * No-any params required
         *
         * @return {@link #url} instance as {@link String}
         **/
        public String getUrl() {
            return url;
        }

        /**
         * Method to get {@link #name} instance <br>
         * No-any params required
         *
         * @return {@link #name} instance as {@link String}
         **/
        public String getName() {
            return name;
        }

        /**
         * Method to get {@link #clientId} instance <br>
         * No-any params required
         *
         * @return {@link #clientId} instance as {@link String}
         **/
        public String getClientId() {
            return clientId;
        }

        /**
         * Returns a string representation of the object <br>
         * No-any params required
         *
         * @return a string representation of the object as {@link String}
         */
        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

    }

}
