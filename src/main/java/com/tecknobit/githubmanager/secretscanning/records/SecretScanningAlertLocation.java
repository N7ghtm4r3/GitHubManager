package com.tecknobit.githubmanager.secretscanning.records;

import com.tecknobit.githubmanager.records.generic.Location;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code SecretScanningAlertLocation} class is useful to format a GitHub's secret scanning alert location
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/secret-scanning#list-locations-for-a-secret-scanning-alert">
 * List locations for a secret scanning alert</a>
 * @see GitHubResponse
 **/
public class SecretScanningAlertLocation extends GitHubResponse {

    /**
     * {@code LocationType} list of available location types
     **/
    public enum LocationType {

        /**
         * {@code commit} location type
         **/
        commit,

        /**
         * {@code issue_title} location type
         **/
        issue_title,

        /**
         * {@code issue_body} location type
         **/
        issue_body,

        /**
         * {@code issue_comment} location type
         **/
        issue_comment

    }

    /**
     * {@code type} of the location
     **/
    private final LocationType type;

    /**
     * {@code details} of the location
     **/
    private final Location details;

    /**
     * Constructor to init a {@link SecretScanningAlertLocation}
     *
     * @param type:    type of the location
     * @param details: details of the location
     **/
    public SecretScanningAlertLocation(LocationType type, Location details) {
        super(null);
        this.type = type;
        this.details = details;
    }

    /**
     * Constructor to init a {@link SecretScanningAlertLocation}
     *
     * @param jSecretScanningAlertLocation: secret scanning alert location details as {@link JSONObject}
     **/
    public SecretScanningAlertLocation(JSONObject jSecretScanningAlertLocation) {
        super(jSecretScanningAlertLocation);
        type = LocationType.valueOf(hResponse.getString("type"));
        details = new Location(hResponse.getJSONObject("details"));
    }

    /**
     * Method to get {@link #type} instance <br>
     * No-any params required
     *
     * @return {@link #type} instance as {@link LocationType}
     **/
    public LocationType getType() {
        return type;
    }

    /**
     * Method to get {@link #details} instance <br>
     * No-any params required
     *
     * @return {@link #details} instance as {@link Location}
     **/
    public Location getDetails() {
        return details;
    }

}
