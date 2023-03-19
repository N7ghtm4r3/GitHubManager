package com.tecknobit.githubmanager.users.socialaccounts.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code SocialAccount} class is useful to format a GitHub's social account
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/users/social-accounts#list-social-accounts-for-the-authenticated-user">
 *             List social accounts for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/users/social-accounts#add-social-accounts-for-the-authenticated-user">
 *             Add social accounts for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/users/social-accounts#list-social-accounts-for-a-user">
 *             List social accounts for a user</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class SocialAccount extends GitHubResponse {

    /**
     * {@code provider} of the social account
     **/
    private final String provider;

    /**
     * {@code url} of the social account
     **/
    private final String url;

    /**
     * Constructor to init a {@link SocialAccount}
     *
     * @param provider : provider of the social account
     * @param url      : url of the social account
     **/
    public SocialAccount(String provider, String url) {
        super(null);
        this.provider = provider;
        this.url = url;
    }

    /**
     * Constructor to init a {@link SocialAccount}
     *
     * @param jSocialAccount : social account details as {@link JSONObject}
     **/
    public SocialAccount(JSONObject jSocialAccount) {
        super(jSocialAccount);
        provider = hResponse.getString("provider");
        url = hResponse.getString("url");
    }

    /**
     * Method to get {@link #provider} instance <br>
     * No-any params required
     *
     * @return {@link #provider} instance as {@link String}
     **/
    public String getProvider() {
        return provider;
    }

    /**
     * Method to get {@link #url} instance <br>
     * No-any params required
     *
     * @return {@link #url} instance as {@link String}
     **/
    public String getUrl() {
        return url;
    }

}
