package com.tecknobit.githubmanager.metrics.traffic.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The {@code PageViews} class is useful to format a GitHub's page views
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/traffic#get-page-views">
 * Get page views</a>
 * @see GitHubResponse
 * @see TrafficItem
 **/
public class PageViews extends TrafficItem {

    /**
     * {@code views} list
     **/
    private final ArrayList<TrafficListItem> views;

    /**
     * Constructor to init a {@link PageViews}
     *
     * @param count:   count value
     * @param uniques: uniques value
     * @param views:   views list
     **/
    public PageViews(int count, int uniques, ArrayList<TrafficListItem> views) {
        super(count, uniques);
        this.views = views;
    }

    /**
     * Constructor to init a {@link PageViews}
     *
     * @param jPageViews: pages views details as {@link JSONObject}
     **/
    public PageViews(JSONObject jPageViews) {
        super(jPageViews);
        views = new ArrayList<>();
        JSONArray jViews = hResponse.getJSONArray("views", new JSONArray());
        for (int j = 0; j < jViews.length(); j++)
            views.add(new TrafficListItem(jViews.getJSONObject(j)));
    }

    /**
     * Method to get {@link #views} instance <br>
     * No-any params required
     *
     * @return {@link #views} instance as {@link ArrayList} of {@link TrafficListItem}
     **/
    public ArrayList<TrafficListItem> getViews() {
        return views;
    }

}
