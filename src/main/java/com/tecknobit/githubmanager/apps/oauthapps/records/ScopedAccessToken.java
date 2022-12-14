package com.tecknobit.githubmanager.apps.oauthapps.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.actions.selfhosted.runners.records.GitHubToken;
import com.tecknobit.githubmanager.apps.apps.records.Installation;
import com.tecknobit.githubmanager.records.basics.User;
import org.json.JSONObject;

import java.util.ArrayList;

public class ScopedAccessToken extends GitHubToken {

    private final long id;
    private final String url;
    private final ArrayList<String> scopes;
    private final String tokenLastEight;
    private final String hashedToken;
    private final App app;
    private final String note;
    private final String noteUrl;
    private final String updatedAt;
    private final String createdAt;
    private final String fingerprint;
    private final User user;
    private final Installation installation;

    /**
     * Constructor to init a {@link ScopedAccessToken}
     *
     * @param token     : the token used for authentication
     * @param expiresAt :the time this token expires
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
        scopes = returnStringList(hResponse.getJSONArray("scopes"));
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

    public long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public ArrayList<String> getScopes() {
        return scopes;
    }

    public String getTokenLastEight() {
        return tokenLastEight;
    }

    public String getHashedToken() {
        return hashedToken;
    }

    public App getApp() {
        return app;
    }

    public String getNote() {
        return note;
    }

    public String getNoteUrl() {
        return noteUrl;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public User getUser() {
        return user;
    }

    public Installation getInstallation() {
        return installation;
    }

    public static class App {

        private final String url;
        private final String name;
        private final String clientId;

        public App(String url, String name, String clientId) {
            this.url = url;
            this.name = name;
            this.clientId = clientId;
        }

        public App(JSONObject jApp) {
            JsonHelper hApp = new JsonHelper(jApp);
            url = hApp.getString("url");
            name = hApp.getString("name");
            clientId = hApp.getString("client_id");
        }

        public String getUrl() {
            return url;
        }

        public String getName() {
            return name;
        }

        public String getClientId() {
            return clientId;
        }

        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

    }

}
