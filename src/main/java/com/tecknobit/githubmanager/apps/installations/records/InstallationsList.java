package com.tecknobit.githubmanager.apps.installations.records;

import com.tecknobit.githubmanager.apps.apps.records.Installation;
import com.tecknobit.githubmanager.records.basics.GitHubList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class InstallationsList extends GitHubList {

    private final ArrayList<Installation> installations;

    /**
     * Constructor to init an {@link InstallationsList}
     **/
    public InstallationsList(ArrayList<Installation> installations) {
        super(installations.size());
        this.installations = installations;
    }

    /**
     * Constructor to init an {@link InstallationsList}
     *
     * @param totalCount : total number of the items in the list
     **/
    public InstallationsList(int totalCount, ArrayList<Installation> installations) {
        super(totalCount);
        this.installations = installations;
    }

    /**
     * Constructor to init a {@link InstallationsList}
     *
     * @param jInstallationsList : response by {@code "GitHub"} as {@link JSONObject}
     **/
    public InstallationsList(JSONObject jInstallationsList) throws Exception {
        super(jInstallationsList);
        JSONArray jInstallations = hResponse.getJSONArray("installations", new JSONArray());
        installations = new ArrayList<>();
        for (int j = 0; j < jInstallations.length(); j++)
            installations.add(new Installation(jInstallations.getJSONObject(j)));
    }

    public Collection<Installation> getInstallations() {
        return installations;
    }

}
