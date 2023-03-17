package com.tecknobit.githubmanager.secretscanning.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONObject;

public class SecretScanningAlert extends GitHubResponse {

    public enum SecretScanningAlertState {

        open,
        resolved

    }

    public enum Resolution {

        false_positive,
        wont_fix,
        revoked,
        used_in_tests

    }

    private final long number;
    private final String createdAt;
    private final String updatedAt;
    private final String url;
    private final String htmlUrl;
    private final String locationsUrl;
    private final SecretScanningAlertState state;
    private final Resolution resolution;
    private final String resolvedAt;
    private final User resolvedBy;
    private final String resolutionComment;
    private final String secretType;
    private final String secretTypeDisplayName;
    private final String secret;
    private final boolean pushProtectionBypassed;
    private final User pushProtectionBypassedBy;
    private final String pushProtectionBypassedAt;

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
        if (sResolution != null)
            resolution = Resolution.valueOf(sResolution);
        else
            resolution = null;
        resolvedAt = hResponse.getString("resolved_at");
        JSONObject jUser = hResponse.getJSONObject("resolved_by");
        if (jUser != null)
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

    public long getNumber() {
        return number;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getUrl() {
        return url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getLocationsUrl() {
        return locationsUrl;
    }

    public SecretScanningAlertState getState() {
        return state;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public String getResolvedAt() {
        return resolvedAt;
    }

    public User getResolvedBy() {
        return resolvedBy;
    }

    public String getResolutionComment() {
        return resolutionComment;
    }

    public String getSecretType() {
        return secretType;
    }

    public String getSecretTypeDisplayName() {
        return secretTypeDisplayName;
    }

    public String getSecret() {
        return secret;
    }

    public boolean isPushProtectionBypassed() {
        return pushProtectionBypassed;
    }

    public User getPushProtectionBypassedBy() {
        return pushProtectionBypassedBy;
    }

    public String getPushProtectionBypassedAt() {
        return pushProtectionBypassedAt;
    }

}
