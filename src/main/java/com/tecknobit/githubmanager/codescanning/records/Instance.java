package com.tecknobit.githubmanager.codescanning.records;

import com.tecknobit.githubmanager.records.generic.Location;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.githubmanager.codescanning.records.ScanningAlert.State;

/**
 * The {@code Instance} class is useful to format a GitHub's instance
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#list-instances-of-a-code-scanning-alert">
 * List instances of a code scanning alert</a>
 * @see GitHubResponse
 * @see ScanningItem
 **/
public class Instance extends ScanningItem {

    /**
     * {@code state} state of a code scanning alert
     **/
    private final State state;

    /**
     * {@code location} describe a region within a file for the alert
     **/
    private final Location location;

    /**
     * {@code htmlUrl} html url value
     **/
    private final String htmlUrl;

    /**
     * {@code classifications} classifications that have been applied to the file that triggered the alert.
     * For example identifying it as documentation, or a generated file
     **/
    private final ArrayList<Classification> classifications;

    /**
     * Constructor to init a {@link Instance}
     *
     * @param ref:             the full Git reference
     * @param analysisKey:     identifies the configuration under which the analysis was executed
     * @param environment:     identifies the variable values associated with the environment in which this analysis was performed
     * @param commitSha:       the SHA of the commit to which the analysis you are uploading relates
     * @param category:        identifies the configuration under which the analysis was executed. Used to distinguish be
     *                         multiple analyses for the same tool and commit, but performed on different languages or different parts of the code
     * @param state:           state of a code scanning alert
     * @param message:         message of the instance
     * @param location:        describe a region within a file for the alert
     * @param htmlUrl:         html url value
     * @param classifications: classifications that have been applied to the file that triggered the alert.
     *                         For example identifying it as documentation, or a generated file
     **/
    public Instance(String ref, String analysisKey, String environment, String commitSha, String category, State state,
                    String message, Location location, String htmlUrl, ArrayList<Classification> classifications) {
        super(ref, analysisKey, environment, commitSha, category);
        this.state = state;
        this.message = message;
        this.location = location;
        this.htmlUrl = htmlUrl;
        this.classifications = classifications;
    }

    /**
     * Constructor to init a {@link Instance}
     *
     * @param jInstance: instance details as {@link JSONObject}
     **/
    public Instance(JSONObject jInstance) {
        super(jInstance);
        state = State.valueOf(hResponse.getString("state"));
        message = hResponse.getJsonHelper("message").getString("text");
        location = new Location(hResponse.getJSONObject("location", new JSONObject()));
        htmlUrl = hResponse.getString("html_url");
        classifications = new ArrayList<>();
        JSONArray jClassifications = hResponse.getJSONArray("classifications", new JSONArray());
        for (int j = 0; j < jClassifications.length(); j++)
            classifications.add(Classification.valueOf(jClassifications.getString(j)));
    }

    /**
     * Method to get {@link #state} instance <br>
     * Any params required
     *
     * @return {@link #state} instance as {@link State}
     **/
    public State getState() {
        return state;
    }

    /**
     * Method to get {@link #location} instance <br>
     * Any params required
     *
     * @return {@link #location} instance as {@link Location}
     **/
    public Location getLocation() {
        return location;
    }

    /**
     * Method to get {@link #htmlUrl} instance <br>
     * Any params required
     *
     * @return {@link #htmlUrl} instance as {@link String}
     **/
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * Method to get {@link #classifications} instance <br>
     * Any params required
     *
     * @return {@link #classifications} instance as {@link Collection} of {@link Classification}
     **/
    public Collection<Classification> getClassifications() {
        return classifications;
    }

    /**
     * {@code Classification} list of available classifications
     **/
    public enum Classification {

        /**
         * {@code "source"} classification
         **/
        source,

        /**
         * {@code "generated"} classification
         **/
        generated,

        /**
         * {@code "test"} classification
         **/
        test,

        /**
         * {@code "library"} classification
         **/
        library

    }

}
