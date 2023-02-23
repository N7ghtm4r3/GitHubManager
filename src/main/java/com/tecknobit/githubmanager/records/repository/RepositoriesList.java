package com.tecknobit.githubmanager.records.repository;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.records.parents.GitHubList;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat;

/**
 * The {@code RepositoriesList} class is useful to format a GitHub's repositories list
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/secrets#list-selected-repositories-for-an-organization-secret">
 *             List selected repositories for an organization secret</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/apps/installations#list-repositories-accessible-to-the-app-installation">
 *             List repositories accessible to the app installation</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/apps/installations#list-repositories-accessible-to-the-user-access-token">
 *             List repositories accessible to the user access token</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#list-selected-repositories-for-an-organization-secret">
 *             List selected repositories for an organization secret</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/codespaces/secrets#list-selected-repositories-for-a-user-secret">
 *             List selected repositories for a user secret</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/dependabot/secrets#list-selected-repositories-for-an-organization-secret">
 *             List selected repositories for an organization secret</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/dependabot/secrets#list-repository-secrets">
 *             List repository secrets</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see GitHubList
 **/
public class RepositoriesList extends GitHubList {

    /**
     * {@code repositories} repositories list
     **/
    private final ArrayList<Repository> repositories;

    /**
     * Constructor to init an {@link GitHubList}
     *
     * @param repositories: repositories list
     **/
    public RepositoriesList(ArrayList<Repository> repositories) {
        super(repositories.size());
        this.repositories = repositories;
    }

    /**
     * Constructor to init an {@link GitHubList}
     *
     * @param totalCount    : total number of the repositories in the list
     * @param repositories: repositories list
     **/
    public RepositoriesList(int totalCount, ArrayList<Repository> repositories) {
        super(totalCount);
        this.repositories = repositories;
    }

    /**
     * Constructor to init a {@link GitHubList}
     *
     * @param jRepositoriesList : repositories list details by {@code "GitHub"} as {@link JSONObject}
     **/
    public RepositoriesList(JSONObject jRepositoriesList) {
        super(jRepositoriesList);
        JSONArray jRepositories = hResponse.getJSONArray("repositories", new JSONArray());
        repositories = new ArrayList<>();
        for (int j = 0; j < jRepositories.length(); j++)
            repositories.add(new Repository(jRepositories.getJSONObject(j)));
    }

    /**
     * Method to get {@link #repositories} instance <br>
     * No-any params required
     *
     * @return {@link #repositories} instance as {@link ArrayList} of {@link Repository}
     **/
    public ArrayList<Repository> getRepositories() {
        return repositories;
    }

    /**
     * Method to get the ids from the {@link #repositories} instance <br>
     * No-any params required
     *
     * @return ids from {@link #repositories} instance as {@link ArrayList} of {@link Long}
     **/
    public ArrayList<Long> getIds() {
        ArrayList<Long> ids = new ArrayList<>();
        for (Repository repository : repositories)
            ids.add(repository.getId());
        return ids;
    }

    /**
     * Method to get the names from the {@link #repositories} instance <br>
     * No-any params required
     *
     * @return names from {@link #repositories} instance as {@link ArrayList} of {@link String}
     **/
    public ArrayList<String> getNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Repository repository : repositories)
            names.add(repository.getName());
        return names;
    }

    /**
     * Method to create a repositories list
     *
     * @param repositoriesResponse: obtained from GitHub's response
     * @param format:               return type formatter -> {@link ReturnFormat}
     * @return repositories list as {@code "format"} defines
     **/
    @Returner
    public static <T> T returnRepositoriesList(String repositoriesResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(repositoriesResponse);
            case LIBRARY_OBJECT:
                return (T) new RepositoriesList(new JSONObject(repositoriesResponse));
            default:
                return (T) repositoriesResponse;
        }
    }

}
