package com.tecknobit.githubmanager.actions.secrets.records;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.GitHubManager.Visibility;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;
import static com.tecknobit.githubmanager.GitHubManager.Visibility.vPrivate;
import static com.tecknobit.githubmanager.GitHubManager.Visibility.valueOf;

/**
 * The {@code Secret} class is useful to format a GitHub's organization secret
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
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/secrets#get-an-organization-secret">
 *             Get an organization secret</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/secrets#delete-an-organization-secret">
 *             Delete an organization secret</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class Secret extends GitHubResponse {

    /**
     * {@code name} the name of the secret
     **/
    private final String name;

    /**
     * {@code createdAt} created at value
     **/
    private final String createdAt;

    /**
     * {@code updatedAt} updated at value
     **/
    private final String updatedAt;

    /**
     * {@code visibility} visibility of a secret
     **/
    private final Visibility visibility;

    /**
     * {@code selectedRepositoriesUrl} selected repositories url value
     **/
    private final String selectedRepositoriesUrl;

    /**
     * Constructor to init a {@link Secret}
     *
     * @param name:                    the name of the secret
     * @param createdAt:               created at value
     * @param updatedAt:               updated at value
     * @param visibility:              visibility of a secret
     * @param selectedRepositoriesUrl: selected repositories url value
     **/
    public Secret(String name, String createdAt, String updatedAt, Visibility visibility,
                  String selectedRepositoriesUrl) {
        super(null);
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.visibility = visibility;
        this.selectedRepositoriesUrl = selectedRepositoriesUrl;
    }

    /**
     * Constructor to init a {@link Secret}
     *
     * @param jOrganizationSecret: organization secret details as {@link JSONObject}
     **/
    public Secret(JSONObject jOrganizationSecret) {
        super(jOrganizationSecret);
        name = hResponse.getString("name");
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
        String visibilityKey = hResponse.getString("visibility", vPrivate.name());
        if (visibilityKey.equals(vPrivate.toString()))
            visibility = vPrivate;
        else
            visibility = valueOf(visibilityKey);
        selectedRepositoriesUrl = hResponse.getString("selected_repositories_url");
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

    /**
     * Method to create a secret
     *
     * @param secretResponse: obtained from GitHub's response
     * @param format:         return type formatter -> {@link GitHubManager.ReturnFormat}
     * @return secret as {@code "format"} defines
     **/
    @Returner
    public static <T> T returnSecret(String secretResponse, GitHubManager.ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(secretResponse);
            case LIBRARY_OBJECT:
                return (T) new Secret(new JSONObject(secretResponse));
            default:
                return (T) secretResponse;
        }
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
     * Method to get {@link #updatedAt} instance <br>
     * Any params required
     *
     * @return {@link #updatedAt} instance as {@link String}
     **/
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Method to get {@link #visibility} instance <br>
     * Any params required
     *
     * @return {@link #visibility} instance as {@link Visibility}
     **/
    public Visibility getVisibility() {
        return visibility;
    }

    /**
     * Method to get {@link #selectedRepositoriesUrl} instance <br>
     * Any params required
     *
     * @return {@link #selectedRepositoriesUrl} instance as {@link String}
     **/
    public String getSelectedRepositoriesUrl() {
        return selectedRepositoriesUrl;
    }

}
