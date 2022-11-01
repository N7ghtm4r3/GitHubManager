package com.tecknobit.githubmanager.actions.permissions.records;

import com.tecknobit.githubmanager.records.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@code AARW} class is useful to format a GitHub's allowed actions and reusable workflows
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote AARW means Allowed Actions and Reusable Workflows, you can see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/permissions#get-allowed-actions-and-reusable-workflows-for-an-enterprise">
 *             Get allowed actions and reusable workflows for an enterprise</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/permissions#set-allowed-actions-and-reusable-workflows-for-an-enterprise">
 *             Set allowed actions and reusable workflows for an enterprise</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/permissions#get-allowed-actions-and-reusable-workflows-for-an-organization">
 *             Get allowed actions and reusable workflows for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/permissions#set-allowed-actions-and-reusable-workflows-for-an-organization">
 *             Set allowed actions and reusable workflows for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/permissions#get-allowed-actions-and-reusable-workflows-for-a-repository">
 *             Get allowed actions and reusable workflows for a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/permissions#set-allowed-actions-and-reusable-workflows-for-a-repository">
 *             Set allowed actions and reusable workflows for a repository</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class AARW extends GitHubResponse {

    /**
     * {@code githubOwnedAllowed} whether {@code "GitHub-owned"} actions are allowed. For example, this includes the actions in the
     * {@code "actions"} organization
     **/
    private final boolean githubOwnedAllowed;

    /**
     * {@code verifiedAllowed} whether actions from {@code "GitHub Marketplace"} verified creators are allowed.
     * Set to {@code "true"} to allow all actions by {@code "GitHub Marketplace"} verified creators
     **/
    private final boolean verifiedAllowed;

    /**
     * {@code patternsAllowed} specifies a list of string-matching patterns to allow specific action(s) and reusable workflow(s).
     * Wildcards, tags, and SHAs are allowed. For example, monalisa/octocat@*, monalisa/octocat@v2, monalisa/*.
     **/
    private final ArrayList<String> patternsAllowed;

    /**
     * Constructor to init a {@link AARW}
     *
     * @param githubOwnedAllowed:       whether {@code "GitHub-owned"} actions are allowed. For example, this includes the actions in the
     *                                  {@code "actions"} organization
     * @param verifiedAllowed:          whether actions from {@code "GitHub Marketplace"} verified creators are allowed.
     *                                  Set to {@code "true"} to allow all actions by {@code "GitHub Marketplace"} verified creators
     * @param patternsAllowed:specifies a list of string-matching patterns to allow specific action(s) and reusable workflow(s).
     *                                  Wildcards, tags, and SHAs are allowed. For example, monalisa/octocat@*, monalisa/octocat@v2, monalisa/*.
     **/
    public AARW(boolean githubOwnedAllowed, boolean verifiedAllowed, ArrayList<String> patternsAllowed) {
        super(null);
        this.githubOwnedAllowed = githubOwnedAllowed;
        this.verifiedAllowed = verifiedAllowed;
        this.patternsAllowed = patternsAllowed;
    }

    /**
     * Constructor to init a {@link AARW}
     *
     * @param jEnterpriseAARW : allowed actions and reusable workflows for an enterprise details as {@link JSONObject}
     **/
    public AARW(JSONObject jEnterpriseAARW) {
        super(jEnterpriseAARW);
        githubOwnedAllowed = hResponse.getBoolean("github_owned_allowed");
        verifiedAllowed = hResponse.getBoolean("verified_allowed");
        patternsAllowed = new ArrayList<>();
        JSONArray jPatternsAllowed = hResponse.getJSONArray("patterns_allowed", new JSONArray());
        for (int j = 0; j < jPatternsAllowed.length(); j++)
            patternsAllowed.add(jPatternsAllowed.getString(j));
    }

    /**
     * Method to get {@link #githubOwnedAllowed} instance <br>
     * Any params required
     *
     * @return {@link #githubOwnedAllowed} instance as boolean
     **/
    public boolean isGithubOwnedAllowed() {
        return githubOwnedAllowed;
    }

    /**
     * Method to get {@link #verifiedAllowed} instance <br>
     * Any params required
     *
     * @return {@link #verifiedAllowed} instance as boolean
     **/
    public boolean isVerifiedAllowed() {
        return verifiedAllowed;
    }

    /**
     * Method to get {@link #patternsAllowed} instance <br>
     * Any params required
     *
     * @return {@link #patternsAllowed} instance as {@link Collection} of {@link String}
     **/
    public Collection<String> getPatternsAllowed() {
        return patternsAllowed;
    }

}
