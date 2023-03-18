package com.tecknobit.githubmanager.users.users.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code ContextualInformation} class is useful to format a GitHub's contextual information
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/users/users#get-contextual-information-for-a-user">
 * Get contextual information for a user</a>
 * @see GitHubResponse
 **/
public class ContextualInformation extends GitHubResponse {

    /**
     * {@code octicon} value
     **/
    private final String octicon;

    /**
     * Constructor to init a {@link ContextualInformation}
     *
     * @param octicon: octicon value
     **/
    public ContextualInformation(String octicon) {
        super(null);
        this.octicon = octicon;
    }

    /**
     * Constructor to init a {@link ContextualInformation}
     *
     * @param jContextualInformation: contextual information details as {@link JSONObject}
     **/
    public ContextualInformation(JSONObject jContextualInformation) {
        super(jContextualInformation);
        octicon = hResponse.getString("octicon");
    }

    /**
     * Method to get {@link #octicon} instance <br>
     * No-any params required
     *
     * @return {@link #octicon} instance as {@link String}
     **/
    public String getOcticon() {
        return octicon;
    }

}
