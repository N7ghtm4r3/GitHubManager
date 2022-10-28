package com.tecknobit.githubmanager.actions.artifacts.records;

import com.tecknobit.githubmanager.GitHubManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ArtifactsList extends GitHubManager.GitHubResponse {

    private final int totalCount;
    private final ArrayList<Artifact> artifacts;

    public ArtifactsList(int totalCount, ArrayList<Artifact> artifacts) {
        super(null);
        this.totalCount = totalCount;
        this.artifacts = artifacts;
    }

    public ArtifactsList(JSONObject jArtifactsList) {
        super(jArtifactsList);
        totalCount = hResponse.getInt("total_count", 0);
        artifacts = new ArrayList<>();
        JSONArray list = hResponse.getJSONArray("artifacts", new JSONArray());
        for (int j = 0; j < list.length(); j++)
            artifacts.add(new Artifact(list.getJSONObject(j)));
    }

    public int getTotalCount() {
        return totalCount;
    }

    public ArrayList<Artifact> getArtifacts() {
        return artifacts;
    }

}
