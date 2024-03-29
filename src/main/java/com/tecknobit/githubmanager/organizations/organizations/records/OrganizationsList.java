package com.tecknobit.githubmanager.organizations.organizations.records;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.records.parents.GitHubList;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The {@code OrganizationsList} class is useful to format a GitHub's organizations list
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/permissions#list-selected-organizations-enabled-for-github-actions-in-an-enterprise">
 *             List selected organizations enabled for GitHub Actions in an enterprise</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-organization-access-to-a-self-hosted-runner-group-in-an-enterprise">
 *             List organization access to a self-hosted runner group in an enterprise</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see GitHubList
 **/
public class OrganizationsList extends GitHubList {

    /**
     * {@code organizations} organizations list
     **/
    private final ArrayList<Organization> organizations;

    /**
     * Constructor to init a {@link OrganizationsList}
     *
     * @param organizations: organizations list
     **/
    public OrganizationsList(ArrayList<Organization> organizations) {
        super(organizations.size());
        this.organizations = organizations;
    }

    /**
     * Constructor to init a {@link OrganizationsList}
     *
     * @param totalCount:    total number of organizations
     * @param organizations: organizations list
     **/
    public OrganizationsList(int totalCount, ArrayList<Organization> organizations) {
        super(totalCount);
        this.organizations = organizations;
    }

    /**
     * Constructor to init a {@link OrganizationsList}
     *
     * @param jEnabledOrganizations : enabled organizations details as {@link JSONObject}
     **/
    public OrganizationsList(JSONObject jEnabledOrganizations) {
        super(jEnabledOrganizations);
        organizations = new ArrayList<>();
        JSONArray jOrganizations = hResponse.getJSONArray("organizations", new JSONArray());
        for (int j = 0; j < jOrganizations.length(); j++)
            organizations.add(new Organization(jOrganizations.getJSONObject(j)));
    }

    /**
     * Method to get {@link #organizations} instance <br>
     * No-any params required
     *
     * @return {@link #organizations} instance as {@link ArrayList} of {@link Organization}
     **/
    public ArrayList<Organization> getOrganizations() {
        return organizations;
    }

    /**
     * Method to create an organizations list for an enterprise
     *
     * @param organizationsResponse: obtained from GitHub's response
     * @param format:                return type formatter -> {@link GitHubManager.ReturnFormat}
     * @return enabled organizations list for an enterprise as {@code "format"} defines
     **/
    @Returner
    public static <T> T returnOrganizationsList(String organizationsResponse, GitHubManager.ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(organizationsResponse);
            case LIBRARY_OBJECT:
                return (T) new OrganizationsList(new JSONObject(organizationsResponse));
            default:
                return (T) organizationsResponse;
        }
    }

}
