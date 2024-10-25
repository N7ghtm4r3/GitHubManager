package com.tecknobit.githubmanager.gists.gists.records;

import com.tecknobit.githubmanager.commits.commits.records.Commit.Stats;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.users.users.records.User;
import org.json.JSONObject;



/**
 * The {@code GistComment} class is useful to format a GitHub's gist commit
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/gists/gists#list-gist-commits">
 *             List gist commits</a>
 *     </li>
 *     <li>
 *          <a href="https://docs.github.com/en/rest/gists/gists#list-gist-forks">
 *              List gist forks</a>
 *     </li>
 *     <li>
 *          <a href="https://docs.github.com/en/rest/gists/gists#get-a-gist-revision">
 *              Get a gist revision</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class GistCommit extends GitHubResponse {

    /**
     * {@code url} the url of the gist commit
     **/
    private final String url;

    /**
     * {@code version} the version of the gist commit
     **/
    private final String version;

    /**
     * {@code user} the user of the gist commit
     **/
    private final User user;

    /**
     * {@code changeStatus} the change status of the gist commit
     **/
    private final Stats changeStatus;

    /**
     * {@code committedAt} the committing date of the gist commit
     **/
    private final String committedAt;

    /**
     * Constructor to init a {@link GistCommit}
     *
     * @param url          : the url of the gist commit
     * @param version      : the version of the gist commit
     * @param user         : the user of the gist commit
     * @param changeStatus : the change status of the gist commit
     * @param committedAt  : the committing date of the gist commit
     **/
    public GistCommit(String url, String version, User user, Stats changeStatus, String committedAt) {
        super(null);
        this.url = url;
        this.version = version;
        this.user = user;
        this.changeStatus = changeStatus;
        this.committedAt = committedAt;
    }

    /**
     * Constructor to init a {@link GistCommit}
     *
     * @param jGistCommit : gist commit details as {@link JSONObject}
     **/
    public GistCommit(JSONObject jGistCommit) {
        super(jGistCommit);
        url = hResponse.getString("url");
        version = hResponse.getString("version");
        user = new User(hResponse.getJSONObject("user", new JSONObject()));
        changeStatus = new Stats(hResponse.getJSONObject("change_status", new JSONObject()));
        committedAt = hResponse.getString("committed_at");
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

    /**
     * Method to get {@link #version} instance <br>
     * No-any params required
     *
     * @return {@link #version} instance as {@link String}
     **/
    public String getVersion() {
        return version;
    }

    /**
     * Method to get {@link #user} instance <br>
     * No-any params required
     *
     * @return {@link #user} instance as {@link User}
     **/
    public User getUser() {
        return user;
    }

    /**
     * Method to get {@link #changeStatus} instance <br>
     * No-any params required
     *
     * @return {@link #changeStatus} instance as {@link Stats}
     **/
    public Stats getChangeStatus() {
        return changeStatus;
    }

    /**
     * Method to get {@link #committedAt} instance <br>
     * No-any params required
     *
     * @return {@link #committedAt} instance as {@link String}
     **/
    public String getCommittedAt() {
        return committedAt;
    }

    /**
     * Method to get {@link #committedAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #committedAt} timestamp as long
     **/
    public long getCommittedAtTimestamp() {
        return timeFormatter.formatAsTimestamp(committedAt);
    }

}
