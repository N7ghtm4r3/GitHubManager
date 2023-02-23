package com.tecknobit.githubmanager.commits.commitstatuses.records;

import com.tecknobit.githubmanager.commits.commitstatuses.records.CommitStatus.CommitStatusState;
import com.tecknobit.githubmanager.records.parents.GitHubList;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The {@code CombinedStatus} class is useful to format a GitHub's combined status
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/statuses#get-the-combined-status-for-a-specific-reference">
 * Get the combined status for a specific reference</a>
 * @see GitHubResponse
 * @see GitHubList
 **/
public class CombinedStatus extends GitHubList {

    /**
     * {@code state} of combined status
     **/
    private final CommitStatusState state;

    /**
     * {@code statuses} of combined status
     **/
    private final ArrayList<CommitStatus> statuses;

    /**
     * {@code sha} of combined status
     **/
    private final String sha;

    /**
     * {@code repository} of combined status
     **/
    private final Repository repository;

    /**
     * {@code commitUrl} of combined status
     **/
    private final String commitUrl;

    /**
     * {@code url} of combined status
     **/
    private final String url;

    /**
     * Constructor to init an {@link CombinedStatus}
     *
     * @param state      :state of combined status
     * @param statuses   : statuses of combined status
     * @param sha        :sha of combined status
     * @param repository : repository of combined status
     * @param url        : url of combined status
     **/
    public CombinedStatus(CommitStatusState state, ArrayList<CommitStatus> statuses, String sha, Repository repository,
                          String commitUrl, String url) {
        super(statuses.size());
        this.state = state;
        this.statuses = statuses;
        this.sha = sha;
        this.repository = repository;
        this.commitUrl = commitUrl;
        this.url = url;
    }

    /**
     * Constructor to init an {@link CombinedStatus}
     *
     * @param totalCount : total number of the items in the list
     * @param state      :state of combined status
     * @param statuses   : statuses of combined status
     * @param sha        :sha of combined status
     * @param repository : repository of combined status
     * @param url        : url of combined status
     **/
    public CombinedStatus(int totalCount, CommitStatusState state, ArrayList<CommitStatus> statuses, String sha,
                          Repository repository, String commitUrl, String url) {
        super(totalCount);
        this.state = state;
        this.statuses = statuses;
        this.sha = sha;
        this.repository = repository;
        this.commitUrl = commitUrl;
        this.url = url;
    }

    /**
     * Constructor to init a {@link CombinedStatus}
     *
     * @param jCombinedStatus : response by {@code "GitHub"} as {@link JSONObject}
     **/
    public CombinedStatus(JSONObject jCombinedStatus) {
        super(jCombinedStatus);
        state = CommitStatusState.valueOf(hResponse.getString("state"));
        statuses = new ArrayList<>();
        JSONArray jStatuses = hResponse.getJSONArray("statuses", new JSONArray());
        for (int j = 0; j < jStatuses.length(); j++)
            statuses.add(new CommitStatus(jStatuses.getJSONObject(j)));
        sha = hResponse.getString("sha");
        repository = new Repository(hResponse.getJSONObject("repository", new JSONObject()));
        commitUrl = hResponse.getString("commit_url");
        url = hResponse.getString("url");
    }

    /**
     * Method to get {@link #state} instance <br>
     * No-any params required
     *
     * @return {@link #state} instance as {@link CommitStatusState}
     **/
    public CommitStatusState getState() {
        return state;
    }

    /**
     * Method to get {@link #statuses} instance <br>
     * No-any params required
     *
     * @return {@link #statuses} instance as {@link ArrayList} of {@link CommitStatus}
     **/
    public ArrayList<CommitStatus> getStatuses() {
        return statuses;
    }

    /**
     * Method to get {@link #sha} instance <br>
     * No-any params required
     *
     * @return {@link #sha} instance as {@link String}
     **/
    public String getSha() {
        return sha;
    }

    /**
     * Method to get {@link #repository} instance <br>
     * No-any params required
     *
     * @return {@link #repository} instance as {@link Repository}
     **/
    public Repository getRepository() {
        return repository;
    }

    /**
     * Method to get {@link #commitUrl} instance <br>
     * No-any params required
     *
     * @return {@link #commitUrl} instance as {@link String}
     **/
    public String getCommitUrl() {
        return commitUrl;
    }

    /**
     * Method to get {@link #url} instance <br>
     * No-any params required
     *
     * @return {@link #url} instance as {@link String}
     **/
    public String getUrl() {
        return url;
    }

}
