package com.tecknobit.githubmanager.records;

import com.tecknobit.githubmanager.GitHubManager;
import org.json.JSONObject;

/**
 * The {@code GitHubList} class is useful to format all GitHub's list responses giving basics methods
 * for others {@link GitHubManager.ReturnFormat#LIBRARY_OBJECT}
 *
 * @author N7ghtm4r3 - Tecknobit
 **/
public abstract class GitHubList extends GitHubResponse {

    /**
     * {@code totalCount} total number of items
     **/
    protected final int totalCount;

    /**
     * Constructor to init an {@link GitHubList}
     *
     * @param totalCount: total number of the items in the list
     **/
    public GitHubList(int totalCount) {
        super(null);
        this.totalCount = totalCount;
    }

    /**
     * Constructor to init a {@link GitHubList}
     *
     * @param jResponse : response by {@code "GitHub"} as {@link JSONObject}
     **/
    public GitHubList(JSONObject jResponse) {
        super(jResponse);
        totalCount = hResponse.getInt("total_count", 0);
    }

    /**
     * Method to get {@link #totalCount} instance <br>
     * Any params required
     *
     * @return {@link #totalCount} instance as int
     **/
    public int getTotalCount() {
        return totalCount;
    }

}
