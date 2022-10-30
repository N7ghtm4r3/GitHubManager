package com.tecknobit.githubmanager.actions.permissions.records;

import com.tecknobit.githubmanager.records.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// TODO: 30/10/2022 SET WHAT Acronym MEANS
public class EnterpriseAARW extends GitHubResponse {

    private final boolean githubOwnedAllowed;
    private final boolean verifiedAllowed;
    private final ArrayList<String> patternsAllowed;

    public EnterpriseAARW(boolean githubOwnedAllowed, boolean verifiedAllowed, ArrayList<String> patternsAllowed) {
        super(null);
        this.githubOwnedAllowed = githubOwnedAllowed;
        this.verifiedAllowed = verifiedAllowed;
        this.patternsAllowed = patternsAllowed;
    }

    /**
     * Constructor to init a {@link EnterpriseAARW}
     *
     * @param jEnterpriseAARW : allowed actions and reusable workflows for an enterprise details as {@link JSONObject}
     **/
    public EnterpriseAARW(JSONObject jEnterpriseAARW) {
        super(jEnterpriseAARW);
        githubOwnedAllowed = hResponse.getBoolean("github_owned_allowed");
        verifiedAllowed = hResponse.getBoolean("verified_allowed");
        patternsAllowed = new ArrayList<>();
        JSONArray jPatternsAllowed = hResponse.getJSONArray("patterns_allowed", new JSONArray());
        for (int j = 0; j < jPatternsAllowed.length(); j++)
            patternsAllowed.add(jPatternsAllowed.getString(j));
    }

    public boolean isGithubOwnedAllowed() {
        return githubOwnedAllowed;
    }

    public boolean isVerifiedAllowed() {
        return verifiedAllowed;
    }

    public ArrayList<String> getPatternsAllowed() {
        return patternsAllowed;
    }

}
