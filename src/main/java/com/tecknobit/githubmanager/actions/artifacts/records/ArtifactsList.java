package com.tecknobit.githubmanager.actions.artifacts.records;

import com.tecknobit.githubmanager.GitHubManager.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@code ArtifactsList} class is useful to format a list of GitHub's artifact
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *          <a href="https://docs.github.com/en/rest/actions/artifacts#list-artifacts-for-a-repository">
 *              List artifacts for a repository</a>
 *     </li>
 *     <li>
 *          <a href="https://docs.github.com/en/rest/actions/artifacts#list-workflow-run-artifacts">
 *              List workflow run artifacts</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class ArtifactsList extends GitHubResponse {

    /**
     * {@code totalCount} total count of artifacts
     **/
    private final int totalCount;

    /**
     * {@code artifacts} artifacts list
     **/
    private final ArrayList<Artifact> artifacts;

    /**
     * Constructor to init an {@link Artifact}
     *
     * @param totalCount: total count of artifacts
     * @param artifacts:  artifacts list
     **/
    public ArtifactsList(int totalCount, ArrayList<Artifact> artifacts) {
        super(null);
        this.totalCount = totalCount;
        this.artifacts = artifacts;
    }

    /**
     * Constructor to init an {@link ArtifactsList}
     *
     * @param jArtifactsList: artifact list details as {@link JSONObject}
     **/
    public ArtifactsList(JSONObject jArtifactsList) {
        super(jArtifactsList);
        totalCount = hResponse.getInt("total_count", 0);
        artifacts = new ArrayList<>();
        JSONArray list = hResponse.getJSONArray("artifacts", new JSONArray());
        for (int j = 0; j < list.length(); j++)
            artifacts.add(new Artifact(list.getJSONObject(j)));
    }

    /**
     * Method to get {@link #totalCount} instance <br>
     * Any params required
     *
     * @return {@link #totalCount} instance as int
     **/
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * Method to get {@link #artifacts} instance <br>
     * Any params required
     *
     * @return {@link #artifacts} instance as {@link Collection} of {@link Artifact}
     **/
    public Collection<Artifact> getArtifacts() {
        return artifacts;
    }

}
