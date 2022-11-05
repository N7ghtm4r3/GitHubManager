package com.tecknobit.githubmanager.actions.selfhosted.runners.records;

import com.tecknobit.githubmanager.records.basics.GitHubResponse;
import org.json.JSONObject;

import java.text.ParseException;

import static com.tecknobit.githubmanager.actions.artifacts.records.Artifact.dateFormatter;

public class GitHubToken extends GitHubResponse {

    private final String token;
    private final String expiresAt;

    public GitHubToken(String token, String expiresAt) {
        super(null);
        this.token = token;
        this.expiresAt = expiresAt;
    }

    /**
     * Constructor to init a {@link GitHubToken}
     *
     * @param jRegistrationToken : registration token details as {@link JSONObject}
     **/
    public GitHubToken(JSONObject jRegistrationToken) {
        super(jRegistrationToken);
        token = hResponse.getString("token");
        expiresAt = hResponse.getString("expires_at");
    }

    public String getToken() {
        return token;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    /**
     * Method to get {@link #expiresAt} timestamp <br>
     * Any params required
     *
     * @return {@link #expiresAt} timestamp as long
     **/
    public long getExpiresAtTimestamp() {
        try {
            return dateFormatter.parse(expiresAt).getTime();
        } catch (ParseException e) {
            return -1;
        }
    }

}
