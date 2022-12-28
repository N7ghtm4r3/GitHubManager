package com.tecknobit.githubmanager.apps.apps.records;

import com.tecknobit.githubmanager.actions.selfhosted.runners.records.GitHubToken;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.repository.CompleteRepository;
import com.tecknobit.githubmanager.records.repository.Repository.RepositorySelection;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.githubmanager.records.repository.Repository.RepositorySelection.all;

/**
 * The {@code InstallationAccessToken} class is useful to format a GitHub's installation access token
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/apps#create-an-installation-access-token-for-an-app">
 * Create an installation access token for an app</a>
 * @see GitHubResponse
 * @see GitHubToken
 **/
public class InstallationAccessToken extends GitHubToken {

    /**
     * {@code permissions} the permissions of the installation access token
     **/
    private final AppPermissions permissions;

    /**
     * {@code repositorySelection} the repository selection of the installation access token
     **/
    private final RepositorySelection repositorySelection;

    /**
     * {@code repositories} list of the repositories of the installation access token
     **/
    private final ArrayList<CompleteRepository> repositories;

    /**
     * Constructor to init a {@link InstallationAccessToken}
     *
     * @param token     : the token used for authentication
     * @param expiresAt :the time this token expires
     **/
    public InstallationAccessToken(String token, String expiresAt, AppPermissions permissions,
                                   RepositorySelection repositorySelection, ArrayList<CompleteRepository> repositories) {
        super(token, expiresAt);
        this.permissions = permissions;
        this.repositorySelection = repositorySelection;
        this.repositories = repositories;
    }

    /**
     * Constructor to init a {@link InstallationAccessToken}
     *
     * @param jInstallationAccessToken : installation access token details as {@link JSONObject}
     **/
    public InstallationAccessToken(JSONObject jInstallationAccessToken) throws Exception {
        super(jInstallationAccessToken);
        permissions = new AppPermissions(hResponse.getJSONObject("permissions", new JSONObject()));
        repositorySelection = RepositorySelection.valueOf(hResponse.getString("repository_selection", all.name()));
        repositories = new ArrayList<>();
        JSONArray jRepositories = hResponse.getJSONArray("repositories", new JSONArray());
        for (int j = 0; j < jRepositories.length(); j++)
            repositories.add(new CompleteRepository(jRepositories.getJSONObject(j)));
    }

    /**
     * Method to get {@link #permissions} instance <br>
     * Any params required
     *
     * @return {@link #permissions} instance as {@link AppPermissions}
     **/
    public AppPermissions getPermissions() {
        return permissions;
    }

    /**
     * Method to get {@link #repositorySelection} instance <br>
     * Any params required
     *
     * @return {@link #repositorySelection} instance as {@link RepositorySelection}
     **/
    public RepositorySelection getRepositorySelection() {
        return repositorySelection;
    }

    /**
     * Method to get {@link #repositories} instance <br>
     * Any params required
     *
     * @return {@link #repositories} instance as {@link Collection} of {@link CompleteRepository}
     **/
    public Collection<CompleteRepository> getRepositories() {
        return repositories;
    }

}
