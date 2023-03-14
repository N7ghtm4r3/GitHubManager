package com.tecknobit.githubmanager.repositories.tags.records;

import com.tecknobit.githubmanager.records.parents.BaseResponseDetails;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code TagProtection} class is useful to format a GitHub's tag protection
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/repos/tags#list-tag-protection-states-for-a-repository">
 *             List tag protection states for a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/repos/tags#create-a-tag-protection-state-for-a-repository">
 *             Create a tag protection state for a repository</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see BaseResponseDetails
 **/
public class TagProtection extends GitHubResponse {

    /**
     * {@code id} of the tag protection
     **/
    private final long id;

    /**
     * {@code createdAt} creation time of the tag protection
     **/
    private final String createdAt;

    /**
     * {@code updatedAt} update time of the tag protection
     **/
    private final String updatedAt;

    /**
     * {@code enabled} whether the tag protection is enabled
     **/
    private final boolean enabled;

    /**
     * {@code pattern} of the tag protection
     **/
    private final String pattern;

    /**
     * Constructor to init a {@link TagProtection}
     *
     * @param id        : id of the tag protection
     * @param createdAt : creation time of the tag protection
     * @param updatedAt : update time of the tag protection
     * @param enabled   :  whether the tag protection is enabled
     * @param pattern   : pattern of the tag protection
     **/
    public TagProtection(long id, String createdAt, String updatedAt, boolean enabled, String pattern) {
        super(null);
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.enabled = enabled;
        this.pattern = pattern;
    }

    /**
     * Constructor to init a {@link TagProtection}
     *
     * @param jTagProtection: tag protection details as {@link TagProtection}
     **/
    public TagProtection(JSONObject jTagProtection) {
        super(jTagProtection);
        id = hResponse.getLong("id", 0);
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
        enabled = hResponse.getBoolean("enabled");
        pattern = hResponse.getString("pattern");
    }

    /**
     * Method to get {@link #id} instance <br>
     * No-any params required
     *
     * @return {@link #id} instance as long
     **/
    public long getId() {
        return id;
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
     * Method to get {@link #enabled} instance <br>
     * No-any params required
     *
     * @return {@link #enabled} instance as boolean
     **/
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Method to get {@link #pattern} instance <br>
     * No-any params required
     *
     * @return {@link #pattern} instance as {@link String}
     **/
    public String getPattern() {
        return pattern;
    }

}
