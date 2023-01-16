package com.tecknobit.githubmanager.commits.commits.records.pullrequests;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code Commit} class is useful to format a GitHub's commit
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/commits/commits#list-pull-requests-associated-with-a-commith">
 * List pull requests associated with a commit</a>
 * @see GitHubResponse
 **/
public class PullRequestStructure extends GitHubResponse {

    /**
     * {@code url} of the pull request
     **/
    protected final String url;

    /**
     * {@code htmlUrl} html url of the pull request
     **/
    protected final String htmlUrl;

    /**
     * {@code id} id of the pull request
     **/
    protected final long id;

    /**
     * {@code nodeId} node identifier of the pull request
     **/
    protected final String nodeId;

    /**
     * {@code number} of the pull request
     **/
    protected final long number;

    /**
     * {@code state} of the pull request
     **/
    protected final PullRequestState state;

    /**
     * {@code title} of the pull request
     **/
    protected final String title;

    /**
     * {@code createdAt} creation time of the pull request
     **/
    protected final String createdAt;

    /**
     * {@code updatedAt} update time of the pull request
     **/
    protected final String updatedAt;

    /**
     * {@code closedAt} close time of the pull request
     **/
    protected final String closedAt;

    /**
     * Constructor to init a {@link PullRequestStructure}
     *
     * @param url       : url of the pull request
     * @param htmlUrl   : html url of the pull request
     * @param id        : id of the pull request
     * @param nodeId    : node identifier of the pull request
     * @param number    : number of the pull request
     * @param state     : state of the pull request
     * @param title     : title of the pull request
     * @param createdAt : creation time of the pull request
     * @param updatedAt : update time of the pull request
     * @param closedAt  :  close time of the pull request
     **/
    public PullRequestStructure(String url, String htmlUrl, long id, String nodeId, long number, PullRequestState state,
                                String title, String createdAt, String updatedAt, String closedAt) {
        super(null);
        this.url = url;
        this.htmlUrl = htmlUrl;
        this.id = id;
        this.nodeId = nodeId;
        this.number = number;
        this.state = state;
        this.title = title;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.closedAt = closedAt;
    }

    /**
     * Constructor to init a {@link PullRequestStructure}
     *
     * @param jPullRequestStructure : pull request structure details as {@link JSONObject}
     **/
    public PullRequestStructure(JSONObject jPullRequestStructure) {
        super(jPullRequestStructure);
        url = hResponse.getString("url");
        htmlUrl = hResponse.getString("html_url");
        id = hResponse.getLong("id", 0);
        nodeId = hResponse.getString("node_id");
        number = hResponse.getLong("number", 0);
        state = PullRequestState.valueOf(hResponse.getString("state"));
        title = hResponse.getString("title");
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
        closedAt = hResponse.getString("closed_at");
    }

    /**
     * Method to get {@link #url} instance <br>
     * Any params required
     *
     * @return {@link #url} instance as {@link String}
     **/
    public String getUrl() {
        return url;
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
     * Method to get {@link #id} instance <br>
     * Any params required
     *
     * @return {@link #id} instance as long
     **/
    public long getId() {
        return id;
    }

    /**
     * Method to get {@link #nodeId} instance <br>
     * Any params required
     *
     * @return {@link #nodeId} instance as {@link String}
     **/
    public String getNodeId() {
        return nodeId;
    }

    /**
     * Method to get {@link #number} instance <br>
     * Any params required
     *
     * @return {@link #number} instance as long
     **/
    public long getNumber() {
        return number;
    }

    /**
     * Method to get {@link #state} instance <br>
     * Any params required
     *
     * @return {@link #state} instance as {@link PullRequestState}
     **/
    public PullRequestState getState() {
        return state;
    }

    /**
     * Method to get {@link #title} instance <br>
     * Any params required
     *
     * @return {@link #title} instance as {@link String}
     **/
    public String getTitle() {
        return title;
    }

    /**
     * Method to get {@link #createdAt} instance <br>
     * Any params required
     *
     * @return {@link #createdAt} instance as {@link String}
     **/
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Method to get {@link #createdAt} timestamp <br>
     * Any params required
     *
     * @return {@link #createdAt} timestamp as long
     **/
    public long getCreatedAtTimestamp() {
        return getDateTimestamp(createdAt);
    }

    /**
     * Method to get {@link #updatedAt} instance <br>
     * Any params required
     *
     * @return {@link #updatedAt} instance as {@link String}
     **/
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Method to get {@link #updatedAt} timestamp <br>
     * Any params required
     *
     * @return {@link #updatedAt} timestamp as long
     **/
    public long getUpdatedAtTimestamp() {
        return getDateTimestamp(updatedAt);
    }

    /**
     * Method to get {@link #closedAt} instance <br>
     * Any params required
     *
     * @return {@link #closedAt} instance as {@link String}
     **/
    public String getClosedAt() {
        return closedAt;
    }

    /**
     * Method to get {@link #closedAt} timestamp <br>
     * Any params required
     *
     * @return {@link #closedAt} timestamp as long
     **/
    public long getClosedAtTimestamp() {
        return getDateTimestamp(closedAt);
    }

    /**
     * {@code PullRequestState} list of available pull request states
     **/
    public enum PullRequestState {

        /**
         * {@code open} pull request status
         **/
        open,

        /**
         * {@code closed} pull request status
         **/
        closed

    }

}
