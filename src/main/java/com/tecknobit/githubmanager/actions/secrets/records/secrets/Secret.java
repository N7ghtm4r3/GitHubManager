package com.tecknobit.githubmanager.actions.secrets.records.secrets;

import com.tecknobit.githubmanager.records.basics.GitHubResponse;
import org.json.JSONObject;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code Secret} class is useful to format a GitHub's secret
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/secrets#get-a-repository-secret">
 *             Get a repository secret</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/secrets#delete-a-repository-secret">
 *             Delete a repository secret</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/secrets#get-an-environment-secret">
 *             Get an environment secret</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/secrets#delete-an-environment-secret">
 *             Delete an environment secret</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class Secret extends GitHubResponse {

    /**
     * {@code name} the name of the secret
     **/
    protected final String name;

    /**
     * {@code createdAt} created at value
     **/
    protected final String createdAt;

    /**
     * {@code updatedAt} updated at value
     **/
    protected final String updatedAt;

    /**
     * Constructor to init a {@link Secret}
     *
     * @param name:      the name of the secret
     * @param createdAt: created at value
     * @param updatedAt: updated at value
     **/
    public Secret(String name, String createdAt, String updatedAt) {
        super(null);
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Constructor to init a {@link Secret}
     *
     * @param jSecret: secret details as {@link JSONObject}
     **/
    public Secret(JSONObject jSecret) {
        super(jSecret);
        name = hResponse.getString("name");
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
    }

    /**
     * Method to get {@link #name} instance <br>
     * Any params required
     *
     * @return {@link #name} instance as {@link String}
     **/
    public String getName() {
        return name;
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

}
