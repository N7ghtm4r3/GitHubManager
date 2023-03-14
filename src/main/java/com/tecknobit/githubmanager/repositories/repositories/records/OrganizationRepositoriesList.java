package com.tecknobit.githubmanager.repositories.repositories.records;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.records.parents.GitHubList;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

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
     * No-any params required
     *
     * @return {@link #repositories} instance as {@link ArrayList} of {@link CompleteRepository}
     **/
    public ArrayList<CompleteRepository> getRepositories() {
        return repositories;
    }

    /**
     * Method to create a repositories list for an organization
     *
     * @param repositoriesResponse: obtained from GitHub's response
     * @param format:               return type formatter -> {@link GitHubManager.ReturnFormat}
     * @return enabled repositories list for an organization as {@code "format"} defines
     **/
    @Returner
    public static <T> T returnOrganizationRepositories(String repositoriesResponse, GitHubManager.ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(repositoriesResponse);
            case LIBRARY_OBJECT:
                return (T) new OrganizationRepositoriesList(new JSONObject(repositoriesResponse));
            default:
                return (T) repositoriesResponse;
        }
    }

}
