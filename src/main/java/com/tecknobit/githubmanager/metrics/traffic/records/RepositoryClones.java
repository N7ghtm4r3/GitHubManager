package com.tecknobit.githubmanager.metrics.traffic.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The {@code RepositoryClones} class is useful to format a GitHub's repository clones
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/traffic#get-repository-clones">
 * Get repository clones</a>
 * @see GitHubResponse
 * @see TrafficItem
 **/
public class RepositoryClones extends TrafficItem {

    /**
     * {@code clones} list
     **/
    private final ArrayList<TrafficListItem> clones;

    /**
     * Constructor to init a {@link RepositoryClones}
     *
     * @param count:   count value
     * @param uniques: uniques value
     * @param clones:  clones list
     **/
    public RepositoryClones(int count, int uniques, ArrayList<TrafficListItem> clones) {
        super(count, uniques);
        this.clones = clones;
    }

    /**
     * Constructor to init a {@link RepositoryClones}
     *
     * @param jRepositoryClones: repository clones details as {@link JSONObject}
     **/
    public RepositoryClones(JSONObject jRepositoryClones) {
        super(jRepositoryClones);
        clones = new ArrayList<>();
        JSONArray jClones = hResponse.getJSONArray("clones", new JSONArray());
        for (int j = 0; j < jClones.length(); j++)
            clones.add(new TrafficListItem(jClones.getJSONObject(j)));
    }

    /**
     * Method to get {@link #clones} instance <br>
     * No-any params required
     *
     * @return {@link #clones} instance as {@link ArrayList} of {@link TrafficListItem}
     **/
    public ArrayList<TrafficListItem> getClones() {
        return clones;
    }

}
