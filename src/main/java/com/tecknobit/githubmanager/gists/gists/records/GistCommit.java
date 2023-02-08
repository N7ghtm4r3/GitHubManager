package com.tecknobit.githubmanager.gists.gists.records;

import com.tecknobit.githubmanager.commits.commits.records.Commit.Stats;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONObject;

public class GistCommit extends GitHubResponse {

    private final String url;
    private final String version;
    private final User user;
    private final Stats changeStatus;
    private final String committedAt;

    public GistCommit(String url, String version, User user, Stats changeStatus, String committedAt) {
        super(null);
        this.url = url;
        this.version = version;
        this.user = user;
        this.changeStatus = changeStatus;
        this.committedAt = committedAt;
    }

    public GistCommit(JSONObject jGistCommit) {
        super(jGistCommit);
        url = hResponse.getString("url");
        version = hResponse.getString("version");
        user = new User(hResponse.getJSONObject("user", new JSONObject()));
        changeStatus = new Stats(hResponse.getJSONObject("change_status", new JSONObject()));
        committedAt = hResponse.getString("committed_at");
    }

    public String getUrl() {
        return url;
    }

    public String getVersion() {
        return version;
    }

    public User getUser() {
        return user;
    }

    public Stats getChangeStatus() {
        return changeStatus;
    }

    public String getCommittedAt() {
        return committedAt;
    }

}
