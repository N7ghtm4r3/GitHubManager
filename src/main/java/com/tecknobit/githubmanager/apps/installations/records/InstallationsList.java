package com.tecknobit.githubmanager.apps.installations.records;

import com.tecknobit.githubmanager.apps.apps.records.Installation;
import com.tecknobit.githubmanager.records.parents.GitHubList;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@code InstallationsList} class is useful to format a GitHub's installations list
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/installations#list-app-installations-accessible-to-the-user-access-token">
 * List app installations accessible to the user access token</a>
 * @see GitHubResponse
 * @see GitHubList
 **/
public class InstallationsList extends GitHubList {

    /**
     * {@code installations} list of installations
     **/
    private final ArrayList<Installation> installations;

    /**
     * Constructor to init an {@link InstallationsList}
     *
     * @param installations: list of installations
     **/
    public InstallationsList(ArrayList<Installation> installations) {
        super(installations.size());
        this.installations = installations;
    }

    /**
     * Constructor to init an {@link InstallationsList}
     *
     * @param totalCount     : total number of the installations in the list
     * @param installations: list of installations
     **/
    public InstallationsList(int totalCount, ArrayList<Installation> installations) {
        super(totalCount);
        this.installations = installations;
    }

    /**
     * Constructor to init a {@link InstallationsList}
     *
     * @param jInstallationsList : installations list details by {@code "GitHub"} as {@link JSONObject}
     **/
    public InstallationsList(JSONObject jInstallationsList) throws Exception {
        super(jInstallationsList);
        JSONArray jInstallations = hResponse.getJSONArray("installations", new JSONArray());
        installations = new ArrayList<>();
        for (int j = 0; j < jInstallations.length(); j++)
            installations.add(new Installation(jInstallations.getJSONObject(j)));
    }

    /**
     * Method to get {@link #installations} instance <br>
     * Any params required
     *
     * @return {@link #installations} instance as {@link Collection} of {@link Installation}
     **/
    public Collection<Installation> getInstallations() {
        return installations;
    }

}
