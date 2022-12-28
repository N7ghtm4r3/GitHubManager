package com.tecknobit.githubmanager.actions.selfhosted.runners.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code GitHubToken} class is useful to format a GitHub's token
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#create-a-registration-token-for-an-enterprise">
 *             Create a registration token for an enterprise</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#create-a-remove-token-for-an-enterprise">
 *             Create a remove token for an enterprise</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#create-a-registration-token-for-an-organization">
 *             Create a registration token for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#create-a-remove-token-for-an-organization">
 *            Create a remove token for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#create-a-registration-token-for-a-repository">
 *             Create a registration token for a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#create-a-remove-token-for-a-repository">
 *             Create a remove token for a repository</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class GitHubToken extends GitHubResponse {

    /**
     * {@code token} the token used for authentication
     **/
    protected final String token;

    /**
     * {@code expiresAt} the time this token expires
     **/
    protected final String expiresAt;

    /**
     * Constructor to init a {@link GitHubToken}
     *
     * @param token     : the token used for authentication
     * @param expiresAt :the time this token expires
     **/
    public GitHubToken(String token, String expiresAt) {
        super(null);
        this.token = token;
        this.expiresAt = expiresAt;
    }

    /**
     * Constructor to init a {@link GitHubToken}
     *
     * @param jToken : token token details as {@link JSONObject}
     **/
    public GitHubToken(JSONObject jToken) {
        super(jToken);
        token = hResponse.getString("token");
        expiresAt = hResponse.getString("expires_at");
    }

    /**
     * Method to get {@link #token} instance <br>
     * Any params required
     *
     * @return {@link #token} instance as {@link String}
     **/
    public String getToken() {
        return token;
    }

    /**
     * Method to get {@link #expiresAt} instance <br>
     * Any params required
     *
     * @return {@link #expiresAt} instance as {@link String}
     **/
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
        return getDateTimestamp(expiresAt);
    }

}
