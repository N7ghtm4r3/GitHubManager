package com.tecknobit.githubmanager.pages.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONObject;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code PagesBuild} class is useful to format a GitHub's pages build
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/pages#list-github-pages-builds">
 *             List GitHub Pages builds</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/pages#request-a-github-pages-build">
 *             Request a GitHub Pages build</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/pages#get-latest-pages-build">
 *             Get latest Pages build</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/pages#get-github-pages-build">
 *             Get GitHub Pages build</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class PagesBuild extends GitHubResponse {

    /**
     * {@code url} of the pages build
     **/
    private final String url;

    /**
     * {@code status} of the pages build
     **/
    private final String status;

    /**
     * {@code error} of the pages build
     **/
    private final String error;

    /**
     * {@code pusher} of the pages build
     **/
    private final User pusher;

    /**
     * {@code commit} of the pages build
     **/
    private final String commit;

    /**
     * {@code duration} of the pages build
     **/
    private final int duration;

    /**
     * {@code createdAt} creation date of the pages build
     **/
    private final String createdAt;

    /**
     * {@code updatedAt} update date of the pages build
     **/
    private final String updatedAt;

    /**
     * Constructor to init a {@link PagesBuild}
     *
     * @param url       : url of the pages build
     * @param status    : status of the pages build
     * @param error     : error of the pages build
     * @param pusher    : pusher of the pages build
     * @param commit    : commit of the pages build
     * @param duration  : duration of the pages build
     * @param createdAt : creation date of the pages build
     * @param updatedAt : update date of the pages build
     **/
    public PagesBuild(String url, String status, String error, User pusher, String commit, int duration, String createdAt,
                      String updatedAt) {
        super(null);
        this.url = url;
        this.status = status;
        this.error = error;
        this.pusher = pusher;
        this.commit = commit;
        this.duration = duration;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Constructor to init a {@link PagesBuild}
     *
     * @param jPagesBuild : pages build as details as {@link JSONObject}
     **/
    public PagesBuild(JSONObject jPagesBuild) {
        super(jPagesBuild);
        url = hResponse.getString("url");
        status = hResponse.getString("status");
        error = hResponse.getString("message");
        pusher = new User(hResponse.getJSONObject("pusher"));
        commit = hResponse.getString("commit");
        duration = hResponse.getInt("duration", 0);
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
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
     * Method to get {@link #status} instance <br>
     * No-any params required
     *
     * @return {@link #status} instance as {@link String}
     **/
    public String getStatus() {
        return status;
    }

    /**
     * Method to get {@link #error} instance <br>
     * No-any params required
     *
     * @return {@link #error} instance as {@link String}
     **/
    public String getError() {
        return error;
    }

    /**
     * Method to get {@link #pusher} instance <br>
     * No-any params required
     *
     * @return {@link #pusher} instance as {@link User}
     **/
    public User getPusher() {
        return pusher;
    }

    /**
     * Method to get {@link #commit} instance <br>
     * No-any params required
     *
     * @return {@link #commit} instance as {@link String}
     **/
    public String getCommit() {
        return commit;
    }

    /**
     * Method to get {@link #duration} instance <br>
     * No-any params required
     *
     * @return {@link #duration} instance as int
     **/
    public int getDuration() {
        return duration;
    }

    /**
     * Method to get {@link #createdAt} instance <br>
     * No-any params required
     *
     * @return {@link #createdAt} instance as {@link String}
     **/
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Method to get {@link #createdAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #createdAt} timestamp as long
     **/
    public long getCreatedAtTimestamp() {
        return getDateTimestamp(createdAt);
    }

    /**
     * Method to get {@link #updatedAt} instance <br>
     * No-any params required
     *
     * @return {@link #updatedAt} instance as {@link String}
     **/
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Method to get {@link #updatedAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #updatedAt} timestamp as long
     **/
    public long getUpdatedAtTimestamp() {
        return getDateTimestamp(updatedAt);
    }

}
