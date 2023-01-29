package com.tecknobit.githubmanager.branches.branches.records;

import com.tecknobit.githubmanager.commits.commits.records.Commit;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code ShortBranch} class is useful to format a GitHub's short branch
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/commits/commits#list-branches-for-head-commit">
 * List branches for HEAD commit</a>
 **/
public class ShortBranch extends GitHubResponse {

    /**
     * {@code name} of the branch
     **/
    protected final String name;

    /**
     * {@code commit} of the branch
     **/
    protected final Commit commit;

    /**
     * {@code isProtected} whether the branch is protected
     **/
    protected final boolean isProtected;

    /**
     * Constructor to init a {@link ShortBranch}
     *
     * @param name        : name of the branch
     * @param commit      : commit of the branch
     * @param isProtected : whether the branch is protected
     **/
    public ShortBranch(String name, Commit commit, boolean isProtected) {
        super(null);
        this.name = name;
        this.commit = commit;
        this.isProtected = isProtected;
    }

    /**
     * Constructor to init a {@link ShortBranch}
     *
     * @param jBranch : branch details as {@link JSONObject}
     **/
    public ShortBranch(JSONObject jBranch) {
        super(jBranch);
        name = hResponse.getString("name");
        commit = new Commit(hResponse.getJSONObject("commit", new JSONObject()));
        isProtected = hResponse.getBoolean("protected");
    }

    /**
     * Method to get {@link #name} instance <br>
     * No-any params required
     *
     * @return {@link #name} instance as {@link String}
     **/
    public String getName() {
        return name;
    }

    /**
     * Method to get {@link #commit} instance <br>
     * No-any params required
     *
     * @return {@link #commit} instance as {@link Commit}
     **/
    public Commit getCommit() {
        return commit;
    }

    /**
     * Method to get {@link #isProtected} instance <br>
     * No-any params required
     *
     * @return {@link #isProtected} instance as boolean
     **/
    public boolean isProtected() {
        return isProtected;
    }

}
