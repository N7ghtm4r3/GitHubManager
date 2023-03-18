package com.tecknobit.githubmanager.secretscanning.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.users.users.records.User;
import org.json.JSONObject;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code SecretScanningAlert} class is useful to format a GitHub's secret scanning alert
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *          <a href="https://docs.github.com/en/rest/secret-scanning#list-secret-scanning-alerts-for-an-enterprise">
 *              List secret scanning alerts for an enterprise</a>
 *     </li>
 *     <li>
 *          <a href="https://docs.github.com/en/rest/secret-scanning#list-secret-scanning-alerts-for-an-organization">
 *              List secret scanning alerts for an organization</a>
 *     </li>
 *     <li>
 *          <a href="https://docs.github.com/en/rest/secret-scanning#list-secret-scanning-alerts-for-a-repository">
 *              List secret scanning alerts for a repository</a>
 *     </li>
 *     <li>
 *          <a href="https://docs.github.com/en/rest/secret-scanning#get-a-secret-scanning-alert">
 *              Get a secret scanning alert</a>
 *     </li>
 *     <li>
 *          <a href="https://docs.github.com/en/rest/secret-scanning#update-a-secret-scanning-alert">
 *              Update a secret scanning alert</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class SecretScanningAlert extends GitHubResponse {

    /**
     * {@code SecretScanningAlertState} list of available secret scanning alert states
     **/
    public enum SecretScanningAlertState {

        /**
         * {@code open} secret scanning alert state
         **/
        open,

        /**
         * {@code resolved} secret scanning alert state
         **/
        resolved

    }

    /**
     * {@code Resolution} list of available secret scanning alert resolutions
     **/
    public enum Resolution {

        /**
         * {@code false_positive} secret scanning alert resolution
         **/
        false_positive,

        /**
         * {@code wont_fix} secret scanning alert resolution
         **/
        wont_fix,

        /**
         * {@code revoked} secret scanning alert resolution
         **/
        revoked,

        /**
         * {@code used_in_tests} secret scanning alert resolution
         **/
        used_in_tests,

        /**
         * {@code pattern_edited} secret scanning alert resolution
         **/
        pattern_edited,

        /**
         * {@code pattern_deleted} secret scanning alert resolution
         **/
        pattern_deleted

    }

    /**
     * {@code number} the security alert number
     **/
    private final long number;

    /**
     * {@code createdAt} the time that the alert was created in ISO 8601 format: {@code "YYYY-MM-DDTHH:MM:SSZ"}
     **/
    private final String createdAt;

    /**
     * {@code updatedAt} the time that the alert was last updated in ISO 8601 format: {@code "YYYY-MM-DDTHH:MM:SSZ"}
     **/
    private final String updatedAt;

    /**
     * {@code url} the REST API URL of the alert resource
     **/
    private final String url;

    /**
     * {@code htmlUrl} the GitHub URL of the alert resource
     **/
    private final String htmlUrl;

    /**
     * {@code locationsUrl} the REST API URL of the code locations for this alert
     **/
    private final String locationsUrl;

    /**
     * {@code state} the state of the secret scanning alert
     **/
    private final SecretScanningAlertState state;

    /**
     * {@code resolution} the reason for resolving the alert
     **/
    private final Resolution resolution;

    /**
     * {@code false_positive} the time that the alert was resolved in ISO 8601 format: {@code "YYYY-MM-DDTHH:MM:SSZ"}
     **/
    private final String resolvedAt;

    /**
     * {@code resolvedBy} who resolve the secret scanning alert
     **/
    private final User resolvedBy;

    /**
     * {@code resolutionComment} an optional comment to resolve an alert
     **/
    private final String resolutionComment;

    /**
     * {@code secretType} the type of secret that secret scanning detected
     **/
    private final String secretType;

    /**
     * {@code secretTypeDisplayName} user-friendly name for the detected secret
     **/
    private final String secretTypeDisplayName;

    /**
     * {@code secret} the secret that was detected
     **/
    private final String secret;

    /**
     * {@code pushProtectionBypassed} whether push protection was bypassed for the detected secret
     **/
    private final boolean pushProtectionBypassed;

    /**
     * {@code pushProtectionBypassedBy} who bypassed the push protection
     **/
    private final User pushProtectionBypassedBy;

    /**
     * {@code pushProtectionBypassedAt} the time that push protection was bypassed in ISO 8601 format:
     * {@code "YYYY-MM-DDTHH:MM:SSZ"}
     **/
    private final String pushProtectionBypassedAt;

    /**
     * Constructor to init a {@link SecretScanningAlert}
     *
     * @param number:                   the security alert number
     * @param createdAt:                the time that the alert was created in ISO 8601 format: {@code "YYYY-MM-DDTHH:MM:SSZ"}
     * @param updatedAt:                the time that the alert was last updated in ISO 8601 format: {@code "YYYY-MM-DDTHH:MM:SSZ"}
     * @param url:                      the REST API URL of the alert resource
     * @param htmlUrl:                  the GitHub URL of the alert resource
     * @param locationsUrl:             the REST API URL of the code locations for this alert
     * @param state:                    the state of the secret scanning alert
     * @param resolution:               the reason for resolving the alert
     * @param resolvedAt:               the time that the alert was resolved in ISO 8601 format: {@code "YYYY-MM-DDTHH:MM:SSZ"}
     * @param resolvedBy:               who resolve the secret scanning alert
     * @param resolutionComment:        an optional comment to resolve an alert
     * @param secretType:               the type of secret that secret scanning detected
     * @param secretTypeDisplayName:    user-friendly name for the detected secret
     * @param secret:                   the secret that was detected
     * @param pushProtectionBypassed:   whether push protection was bypassed for the detected secret
     * @param pushProtectionBypassedBy: who bypassed the push protection
     * @param pushProtectionBypassedAt: the time that push protection was bypassed in ISO 8601 format:
     *                                  {@code "YYYY-MM-DDTHH:MM:SSZ"}
     **/
    public SecretScanningAlert(long number, String createdAt, String updatedAt, String url, String htmlUrl,
                               String locationsUrl, SecretScanningAlertState state, Resolution resolution,
                               String resolvedAt, User resolvedBy, String resolutionComment, String secretType,
                               String secretTypeDisplayName, String secret, boolean pushProtectionBypassed,
                               User pushProtectionBypassedBy, String pushProtectionBypassedAt) {
        super(null);
        this.number = number;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.url = url;
        this.htmlUrl = htmlUrl;
        this.locationsUrl = locationsUrl;
        this.state = state;
        this.resolution = resolution;
        this.resolvedAt = resolvedAt;
        this.resolvedBy = resolvedBy;
        this.resolutionComment = resolutionComment;
        this.secretType = secretType;
        this.secretTypeDisplayName = secretTypeDisplayName;
        this.secret = secret;
        this.pushProtectionBypassed = pushProtectionBypassed;
        this.pushProtectionBypassedBy = pushProtectionBypassedBy;
        this.pushProtectionBypassedAt = pushProtectionBypassedAt;
    }

    /**
     * Constructor to init a {@link SecretScanningAlert}
     *
     * @param jSecretScanningAlert: secret scanning alert details as {@link JSONObject}
     **/
    public SecretScanningAlert(JSONObject jSecretScanningAlert) {
        super(jSecretScanningAlert);
        number = hResponse.getLong("number", 0);
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
        url = hResponse.getString("url");
        htmlUrl = hResponse.getString("html_url");
        locationsUrl = hResponse.getString("locations_url");
        state = SecretScanningAlertState.valueOf(hResponse.getString("state"));
        String sResolution = hResponse.getString("resolution");
        if(sResolution != null)
            resolution = Resolution.valueOf(sResolution);
        else
            resolution = null;
        resolvedAt = hResponse.getString("resolved_at");
        JSONObject jUser = hResponse.getJSONObject("resolved_by");
        if(jUser != null)
            resolvedBy = new User(jUser);
        else
            resolvedBy = null;
        resolutionComment = hResponse.getString("resolution_comment");
        secretType = hResponse.getString("secret_type");
        secretTypeDisplayName = hResponse.getString("secret_type_display_name");
        secret = hResponse.getString("secret");
        pushProtectionBypassed = hResponse.getBoolean("push_protection_bypassed");
        jUser = hResponse.getJSONObject("push_protection_bypassed_by");
        if (jUser != null)
            pushProtectionBypassedBy = new User(jUser);
        else
            pushProtectionBypassedBy = null;
        pushProtectionBypassedAt = hResponse.getString("push_protection_bypassed_at");
    }

    /**
     * Method to get {@link #number} instance <br>
     * No-any params required
     *
     * @return {@link #number} instance as long
     **/
    public long getNumber() {
        return number;
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
     * Method to get {@link #updatedAt} instance <br>
     * No-any params required
     *
     * @return {@link #updatedAt} instance as {@link String}
     **/
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Method to get {@link #createdAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #createdAt} timestamp as long
     **/
    public long getUpdatedAtTimestamp() {
        return getDateTimestamp(createdAt);
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
     * Method to get {@link #htmlUrl} instance <br>
     * No-any params required
     *
     * @return {@link #htmlUrl} instance as {@link String}
     **/
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * Method to get {@link #locationsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #locationsUrl} instance as {@link String}
     **/
    public String getLocationsUrl() {
        return locationsUrl;
    }

    /**
     * Method to get {@link #state} instance <br>
     * No-any params required
     *
     * @return {@link #state} instance as {@link SecretScanningAlertState}
     **/
    public SecretScanningAlertState getState() {
        return state;
    }

    /**
     * Method to get {@link #resolution} instance <br>
     * No-any params required
     *
     * @return {@link #resolution} instance as {@link Resolution}
     **/
    public Resolution getResolution() {
        return resolution;
    }

    /**
     * Method to get {@link #resolvedAt} instance <br>
     * No-any params required
     *
     * @return {@link #resolvedAt} instance as {@link String}
     **/
    public String getResolvedAt() {
        return resolvedAt;
    }

    /**
     * Method to get {@link #resolvedAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #resolvedAt} timestamp as long
     **/
    public long getResolvedAtTimestamp() {
        return getDateTimestamp(resolvedAt);
    }

    /**
     * Method to get {@link #resolvedBy} instance <br>
     * No-any params required
     *
     * @return {@link #resolvedBy} instance as {@link User}
     **/
    public User getResolvedBy() {
        return resolvedBy;
    }

    /**
     * Method to get {@link #resolutionComment} instance <br>
     * No-any params required
     *
     * @return {@link #resolutionComment} instance as {@link String}
     **/
    public String getResolutionComment() {
        return resolutionComment;
    }

    /**
     * Method to get {@link #secretType} instance <br>
     * No-any params required
     *
     * @return {@link #secretType} instance as {@link String}
     **/
    public String getSecretType() {
        return secretType;
    }

    /**
     * Method to get {@link #secretTypeDisplayName} instance <br>
     * No-any params required
     *
     * @return {@link #secretTypeDisplayName} instance as {@link String}
     **/
    public String getSecretTypeDisplayName() {
        return secretTypeDisplayName;
    }

    /**
     * Method to get {@link #secret} instance <br>
     * No-any params required
     *
     * @return {@link #secret} instance as {@link String}
     **/
    public String getSecret() {
        return secret;
    }

    /**
     * Method to get {@link #pushProtectionBypassed} instance <br>
     * No-any params required
     *
     * @return {@link #pushProtectionBypassed} instance as boolean
     **/
    public boolean isPushProtectionBypassed() {
        return pushProtectionBypassed;
    }

    /**
     * Method to get {@link #pushProtectionBypassedBy} instance <br>
     * No-any params required
     *
     * @return {@link #pushProtectionBypassedBy} instance as {@link User}
     **/
    public User getPushProtectionBypassedBy() {
        return pushProtectionBypassedBy;
    }

    /**
     * Method to get {@link #pushProtectionBypassedAt} instance <br>
     * No-any params required
     *
     * @return {@link #pushProtectionBypassedAt} instance as {@link String}
     **/
    public String getPushProtectionBypassedAt() {
        return pushProtectionBypassedAt;
    }

}
