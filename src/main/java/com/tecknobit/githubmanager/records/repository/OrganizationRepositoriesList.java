package com.tecknobit.githubmanager.records.repository;

import com.tecknobit.githubmanager.records.basics.GitHubList;
import com.tecknobit.githubmanager.records.basics.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@code RepositoriesList} class is useful to format a GitHub's repositories list
 *
 * @author N7ghtm4r3 - Tecknobit
 * /**
 * The {@code OrganizationsList} class is useful to format a GitHub's organizations list
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/permissions#list-selected-repositories-enabled-for-github-actions-in-an-organization">
 *             List selected repositories enabled for GitHub Actions in an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-repository-access-to-a-self-hosted-runner-group-in-an-organization">
 *             List repository access to a self-hosted runner group in an organization</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see GitHubList
 **/
public class OrganizationRepositoriesList extends GitHubList {

    /**
     * {@code organizations} repositories list
     **/
    private final ArrayList<CompleteRepository> repositories;

    /**
     * Constructor to init an {@link OrganizationRepositoriesList}
     *
     * @param repositories: repositories list
     **/
    public OrganizationRepositoriesList(ArrayList<CompleteRepository> repositories) {
        super(repositories.size());
        this.repositories = repositories;
    }

    /**
     * Constructor to init an {@link OrganizationRepositoriesList}
     *
     * @param totalCount    : total number of the repositories in the list
     * @param repositories: repositories list
     **/
    public OrganizationRepositoriesList(int totalCount, ArrayList<CompleteRepository> repositories) {
        super(totalCount);
        this.repositories = repositories;
    }

    /**
     * Constructor to init a {@link OrganizationRepositoriesList}
     *
     * @param jOrganizationRepositoriesList : organization repositories list details by {@code "GitHub"} as {@link JSONObject}
     **/
    public OrganizationRepositoriesList(JSONObject jOrganizationRepositoriesList) {
        super(jOrganizationRepositoriesList);
        JSONArray jRepositories = hResponse.getJSONArray("repositories", new JSONArray());
        repositories = new ArrayList<>();
        for (int j = 0; j < jRepositories.length(); j++)
            repositories.add(new CompleteRepository(jRepositories.getJSONObject(j)));
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
