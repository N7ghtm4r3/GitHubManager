package com.tecknobit.githubmanager.apps.apps.records;

import com.tecknobit.githubmanager.actions.selfhosted.runners.records.GitHubToken;
import com.tecknobit.githubmanager.records.repository.CompleteRepository;
import com.tecknobit.githubmanager.records.repository.Repository.RepositorySelection;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.githubmanager.records.repository.Repository.RepositorySelection.all;

public class InstallationAccessToken extends GitHubToken {

    private final AppPermissions permissions;
    private final RepositorySelection repositorySelection;
    private final ArrayList<CompleteRepository> repositories;

    /**
     * Constructor to init a {@link GitHubToken}
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
     * Constructor to init a {@link GitHubToken}
     *
     * @param jRegistrationToken : registration token details as {@link JSONObject}
     **/
    public InstallationAccessToken(JSONObject jRegistrationToken) throws Exception {
        super(jRegistrationToken);
        permissions = new AppPermissions(hResponse.getJSONObject("permissions", new JSONObject()));
        repositorySelection = RepositorySelection.valueOf(hResponse.getString("repository_selection", all.name()));
        repositories = new ArrayList<>();
        JSONArray jRepositories = hResponse.getJSONArray("repositories", new JSONArray());
        for (int j = 0; j < jRepositories.length(); j++)
            repositories.add(new CompleteRepository(jRepositories.getJSONObject(j)));
    }

    public AppPermissions getPermissions() {
        return permissions;
    }

    public RepositorySelection getRepositorySelection() {
        return repositorySelection;
    }

    public Collection<CompleteRepository> getRepositories() {
        return repositories;
    }

}
