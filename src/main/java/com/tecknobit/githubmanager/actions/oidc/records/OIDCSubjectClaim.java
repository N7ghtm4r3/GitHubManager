package com.tecknobit.githubmanager.actions.oidc.records;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@code OIDCSubjectClaim} class is useful to format a GitHub's customization template for an OIDC subject claim
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/oidc#get-the-customization-template-for-an-oidc-subject-claim-for-a-repository">
 * Get the customization template for an OIDC subject claim for a repository</a>
 * @see GitHubResponse
 **/
public class OIDCSubjectClaim extends GitHubResponse {

    /**
     * {@code useDefault} whether to use the default template or not. If {@code "true"}, the {@code "include_claim_keys"}
     * field is ignored
     **/
    private final boolean useDefault;

    /**
     * {@code includeClaimKeys} array of unique strings. Each claim key can only contain alphanumeric characters and underscores
     **/
    private final ArrayList<String> includeClaimKeys;

    /**
     * Constructor to init a {@link OIDCSubjectClaim}
     *
     * @param useDefault       : whether to use the default template or not. If {@code "true"}, the {@code "include_claim_keys"}
     *                         field is ignored
     * @param includeClaimKeys : array of unique strings. Each claim key can only contain alphanumeric characters and underscores
     **/
    public OIDCSubjectClaim(boolean useDefault, ArrayList<String> includeClaimKeys) {
        super(null);
        this.useDefault = useDefault;
        this.includeClaimKeys = includeClaimKeys;
    }

    /**
     * Constructor to init a {@link OIDCSubjectClaim}
     *
     * @param jClaim : OIDC subject claim as {@link JSONObject} details
     **/
    public OIDCSubjectClaim(JSONObject jClaim) {
        super(jClaim);
        useDefault = hResponse.getBoolean("use_default");
        includeClaimKeys = returnClaimKeysList(hResponse.getJSONArray("include_claim_keys"));
    }

    /**
     * Method to create a claim keys list
     *
     * @param jKeys: list of keys in {@link JSONArray} format
     * @return claim keys list as {@link ArrayList} of {@link String}
     **/
    @Returner
    public static ArrayList<String> returnClaimKeysList(JSONArray jKeys) {
        ArrayList<String> claimKeys = new ArrayList<>();
        if (jKeys != null)
            for (int j = 0; j < jKeys.length(); j++)
                claimKeys.add(jKeys.getString(j));
        return claimKeys;
    }

    /**
     * Method to get {@link #useDefault} instance <br>
     * Any params required
     *
     * @return {@link #useDefault} instance as boolean
     **/
    public boolean isDefaultUse() {
        return useDefault;
    }

    /**
     * Method to get {@link #includeClaimKeys} instance <br>
     * Any params required
     *
     * @return {@link #includeClaimKeys} instance as {@link Collection} of {@link String}
     **/
    public Collection<String> getIncludeClaimKeys() {
        return includeClaimKeys;
    }

}
