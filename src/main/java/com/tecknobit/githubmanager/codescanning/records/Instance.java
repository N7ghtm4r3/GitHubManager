package com.tecknobit.githubmanager.codescanning.records;

import com.tecknobit.githubmanager.records.parents.Location;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class Instance extends ScanningItem {
    private final ScanningAlert.State state;
    private final Location location;
    private final String htmlUrl;
    private final ArrayList<Classification> classifications;

    public Instance(String ref, String analysisKey, String environment, String category, ScanningAlert.State state,
                    String commitSha, String message, Location location, String htmlUrl,
                    ArrayList<Classification> classifications) {
        super(ref, analysisKey, environment, commitSha, category);
        this.state = state;
        this.location = location;
        this.htmlUrl = htmlUrl;
        this.classifications = classifications;
    }

    public Instance(JSONObject jInstance) {
        super(jInstance);
        state = ScanningAlert.State.valueOf(hResponse.getString("state"));
        location = new Location(hResponse.getJSONObject("location", new JSONObject()));
        htmlUrl = hResponse.getString("html_url");
        classifications = new ArrayList<>();
        JSONArray jClassifications = hResponse.getJSONArray("classifications", new JSONArray());
        for (int j = 0; j < jClassifications.length(); j++)
            classifications.add(Classification.valueOf(jClassifications.getString(j)));
    }

    public ScanningAlert.State getState() {
        return state;
    }

    @Override
    public String getMessage() {
        return hResponse.getJsonHelper("message").getString("text");
    }

    public Location getLocation() {
        return location;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public Collection<Classification> getClassifications() {
        return classifications;
    }

    public enum Classification {

        source,
        generated,
        test,
        library

    }

}
