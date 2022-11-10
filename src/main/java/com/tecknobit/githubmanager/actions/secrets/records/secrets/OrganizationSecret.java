package com.tecknobit.githubmanager.actions.secrets.records.secrets;

import com.tecknobit.githubmanager.GitHubManager.Visibility;
import com.tecknobit.githubmanager.records.basics.GitHubResponse;
import org.json.JSONObject;

import static com.tecknobit.githubmanager.GitHubManager.Visibility.vPrivate;
import static com.tecknobit.githubmanager.GitHubManager.Visibility.valueOf;

/**
 * The {@code OrganizationSecret} class is useful to format a GitHub's organization secret
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/secrets#get-an-organization-secret">
 *             Get an organization secret</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/secrets#delete-an-organization-secret">
 *             Delete an organization secret</a>
 *     </li>
 * </ul>
 * @see Secret
 * @see GitHubResponse
 **/
public class OrganizationSecret extends Secret {

    /**
     * {@code visibility} visibility of a secret
     **/
    private final Visibility visibility;

    /**
     * {@code selectedRepositoriesUrl} selected repositories url value
     **/
    private final String selectedRepositoriesUrl;

    /**
     * Constructor to init a {@link OrganizationSecret}
     *
     * @param name:                    the name of the secret
     * @param createdAt:               created at value
     * @param updatedAt:               updated at value
     * @param visibility:              visibility of a secret
     * @param selectedRepositoriesUrl: selected repositories url value
     **/
    public OrganizationSecret(String name, String createdAt, String updatedAt, Visibility visibility,
                              String selectedRepositoriesUrl) {
        super(name, createdAt, updatedAt);
        this.visibility = visibility;
        this.selectedRepositoriesUrl = selectedRepositoriesUrl;
    }

    /**
     * Constructor to init a {@link OrganizationSecret}
     *
     * @param jOrganizationSecret: organization secret details as {@link JSONObject}
     **/
    public OrganizationSecret(JSONObject jOrganizationSecret) {
        super(jOrganizationSecret);
        String visibilityKey = hResponse.getString("visibility", vPrivate.name());
        if (visibilityKey.equals(vPrivate.toString()))
            visibility = vPrivate;
        else
            visibility = valueOf(visibilityKey);
        selectedRepositoriesUrl = hResponse.getString("selected_repositories_url");
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
