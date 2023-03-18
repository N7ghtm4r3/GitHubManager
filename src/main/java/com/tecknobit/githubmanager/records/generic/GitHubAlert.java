package com.tecknobit.githubmanager.records.generic;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import com.tecknobit.githubmanager.users.users.records.User;
import org.json.JSONObject;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code GitHubAlert} class is useful to format a GitHub's alert
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-alerts-for-an-enterprise">
 *             List code scanning alerts for an enterprise</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-alerts-for-an-organization">
 *             List code scanning alerts for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-alerts-for-a-repository">
 *             List code scanning alerts for a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/code-scanning#get-a-code-scanning-alert">
 *             Get a code scanning alert</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/code-scanning#update-a-code-scanning-alert">
 *             Update a code scanning alert</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/dependabot/alerts#list-dependabot-alerts-for-an-enterprise">
 *             List Dependabot alerts for an enterprise</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/dependabot/alerts#list-dependabot-alerts-for-an-organization">
 *             List Dependabot alerts for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/dependabot/alerts#list-dependabot-alerts-for-a-repository">
 *             List Dependabot alerts for a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/dependabot/alerts#get-a-dependabot-alert">
 *             Get a Dependabot alert</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/dependabot/alerts#update-a-dependabot-alert">
 *             Update a Dependabot alert</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class GitHubAlert extends GitHubResponse {

    /**
     * {@code number} the security alert number
     **/
    protected final long number;

    /**
     * {@code createdAt} the time that the alert was created in ISO 8601 format: "YYYY-MM-DDTHH:MM:SSZ"
     **/
    protected final String createdAt;

    /**
     * {@code updatedAt} the time that the alert was updated in ISO 8601 format: "YYYY-MM-DDTHH:MM:SSZ"
     **/
    protected final String updatedAt;

    /**
     * {@code url} the REST API URL of the alert resource
     **/
    protected final String url;

    /**
     * {@code htmlUrl} the GitHub URL of the alert resource
     **/
    protected final String htmlUrl;

    /**
     * {@code fixedAt} the time that the alert was no longer detected and was considered fixed in ISO 8601 format:
     * "YYYY-MM-DDTHH:MM:SSZ"
     **/
    protected final String fixedAt;

    /**
     * {@code dismissedBy} dismissed by user
     **/
    protected final User dismissedBy;

    /**
     * {@code dismissedAt} the time that the alert was dismissed in ISO 8601 format: "YYYY-MM-DDTHH:MM:SSZ"
     **/
    protected final String dismissedAt;

    /**
     * {@code dismissedComment} the dismissal comment associated with the dismissal of the alert
     **/
    protected final String dismissedComment;

    /**
     * {@code repository} of the alert
     **/
    protected final Repository repository;

    /**
     * Constructor to init a {@link GitHubAlert}
     *
     * @param number           : the security alert number
     * @param createdAt        : the time that the alert was created in ISO 8601 format: "YYYY-MM-DDTHH:MM:SSZ"
     * @param updatedAt        : the time that the alert was updated in ISO 8601 format: "YYYY-MM-DDTHH:MM:SSZ"
     * @param url              : the REST API URL of the alert resource
     * @param htmlUrl          : the GitHub URL of the alert resource
     * @param fixedAt          : the time that the alert was no longer detected and was considered fixed in ISO 8601 format: "YYYY-MM-DDTHH:MM:SSZ"
     * @param dismissedBy      : dismissed by user
     * @param dismissedAt      : the time that the alert was dismissed in ISO 8601 format: "YYYY-MM-DDTHH:MM:SSZ"
     * @param dismissedComment : the dismissal comment associated with the dismissal of the alert
     * @param repository       : repository of the alert
     **/
    public GitHubAlert(long number, String createdAt, String updatedAt, String url, String htmlUrl, String fixedAt,
                       User dismissedBy, String dismissedAt, String dismissedComment, Repository repository) {
        super(null);
        this.number = number;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.url = url;
        this.htmlUrl = htmlUrl;
        this.fixedAt = fixedAt;
        this.dismissedBy = dismissedBy;
        this.dismissedAt = dismissedAt;
        this.dismissedComment = dismissedComment;
        this.repository = repository;
    }

    /**
     * Constructor to init a {@link GitHubAlert}
     *
     * @param jGitHubAlert : alert details as {@link JSONObject}
     **/
    public GitHubAlert(JSONObject jGitHubAlert) {
        super(jGitHubAlert);
        number = hResponse.getLong("number");
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
        url = hResponse.getString("url");
        htmlUrl = hResponse.getString("html_url");
        fixedAt = hResponse.getString("fixed_at");
        dismissedBy = new User(hResponse.getJSONObject("dismissed_by", new JSONObject()));
        dismissedAt = hResponse.getString("dismissed_at");
        dismissedComment = hResponse.getString("dismissed_comment");
        repository = new Repository(hResponse.getJSONObject("repository", new JSONObject()));
    }

    /**
     * Method to get {@link #number} instance <br>
     * No-any params required
     *
     * @return {@link #number} instance as long
     **/
    public long getNumber() {
        return number;
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
     * Method to get {@link #htmlUrl} instance <br>
     * No-any params required
     *
     * @return {@link #htmlUrl} instance as {@link String}
     **/
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * Method to get {@link #fixedAt} instance <br>
     * No-any params required
     *
     * @return {@link #fixedAt} instance as {@link String}
     **/
    public String getFixedAt() {
        return fixedAt;
    }

    /**
     * Method to get {@link #updatedAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #updatedAt} timestamp as long
     **/
    public long getFixedAtTimestamp() {
        return getDateTimestamp(fixedAt);
    }

    /**
     * Method to get {@link #dismissedBy} instance <br>
     * No-any params required
     *
     * @return {@link #dismissedBy} instance as {@link User}
     **/
    public User getDismissedBy() {
        return dismissedBy;
    }

    /**
     * Method to get {@link #dismissedAt} instance <br>
     * No-any params required
     *
     * @return {@link #dismissedAt} instance as {@link String}
     **/
    public String getDismissedAt() {
        return dismissedAt;
    }

    /**
     * Method to get {@link #dismissedAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #dismissedAt} timestamp as long
     **/
    public long getDismissedAtTimestamp() {
        return getDateTimestamp(dismissedAt);
    }

    /**
     * Method to get {@link #dismissedComment} instance <br>
     * No-any params required
     *
     * @return {@link #dismissedComment} instance as {@link String}
     **/
    public String getDismissedComment() {
        return dismissedComment;
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

}
