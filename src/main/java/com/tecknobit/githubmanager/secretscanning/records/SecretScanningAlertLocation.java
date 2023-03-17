package com.tecknobit.githubmanager.secretscanning.records;

import com.tecknobit.githubmanager.records.generic.Location;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

public class SecretScanningAlertLocation extends GitHubResponse {

    public enum LocationType {

        commit,
        issue_title,
        issue_body,
        issue_comment

    }

    private final LocationType type;
    private final Location details;

    public SecretScanningAlertLocation(LocationType type, Location details) {
        super(null);
        this.type = type;
        this.details = details;
    }

    public SecretScanningAlertLocation(JSONObject jSecretScanningAlertLocation) {
        super(jSecretScanningAlertLocation);
        type = LocationType.valueOf(hResponse.getString("type"));
        details = new Location(hResponse.getJSONObject("details"));
    }

    public LocationType getType() {
        return type;
    }

    public Location getDetails() {
        return details;
    }

}
