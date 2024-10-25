package com.tecknobit.githubmanager.interactions.records;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.GitHubManager.ReturnFormat;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;



/**
 * The {@code Interaction} class is useful to format a GitHub's interaction
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/interactions/orgs#get-interaction-restrictions-for-an-organization">
 *             Get interaction restrictions for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/interactions/orgs#set-interaction-restrictions-for-an-organization">
 *             Set interaction restrictions for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/interactions/orgs#remove-interaction-restrictions-for-an-organization">
 *             Remove interaction restrictions for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/interactions/repos#get-interaction-restrictions-for-a-repository">
 *             Get interaction restrictions for a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/interactions/repos#set-interaction-restrictions-for-a-repository">
 *             Set interaction restrictions for a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/interactions/repos#remove-interaction-restrictions-for-a-repository">
 *             Remove interaction restrictions for a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/interactions/user#get-interaction-restrictions-for-your-public-repositories">
 *             Get interaction restrictions for your public repositories</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/interactions/user#set-interaction-restrictions-for-your-public-repositories">
 *             Set interaction restrictions for your public repositories</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/interactions/user#remove-interaction-restrictions-from-your-public-repositories">
 *             Remove interaction restrictions from your public repositories</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class Interaction extends GitHubResponse {

    /**
     * {@code Limit} list of available limits for an interaction
     **/
    public enum Limit {

        /**
         * {@code existing_users} limit for an interaction
         **/
        existing_users,

        /**
         * {@code contributors_only} limit for an interaction
         **/
        contributors_only,

        /**
         * {@code collaborators_only} limit for an interaction
         **/
        collaborators_only

    }

    /**
     * {@code Expiry} list of available expires for an interaction
     **/
    public enum Expiry {

        /**
         * {@code one_day} expiry for an interaction
         **/
        one_day,

        /**
         * {@code three_days} expiry for an interaction
         **/
        three_days,

        /**
         * {@code one_week} expiry for an interaction
         **/
        one_week,

        /**
         * {@code one_month} expiry for an interaction
         **/
        one_month,

        /**
         * {@code six_months} expiry for an interaction
         **/
        six_months

    }

    /**
     * {@code limit} the type of GitHub user that can comment, open issues, or create pull requests while the interaction limit is in effect
     **/
    private final Limit limit;

    /**
     * {@code origin} the origin of the interaction
     **/
    private final String origin;

    /**
     * {@code expiresAt} the expiry date of the interaction
     **/
    private final String expiresAt;

    /**
     * Constructor to init a {@link Interaction}
     *
     * @param limit     : the type of GitHub user that can comment, open issues, or create pull requests while the interaction limit is in effect
     * @param origin    : the origin of the interaction
     * @param expiresAt : the expiry date of the interaction
     **/
    public Interaction(Limit limit, String origin, String expiresAt) {
        super(null);
        this.limit = limit;
        this.origin = origin;
        this.expiresAt = expiresAt;
    }

    /**
     * Constructor to init a {@link Interaction}
     *
     * @param jInteraction : interaction details as {@link JSONObject}
     **/
    public Interaction(JSONObject jInteraction) {
        super(jInteraction);
        limit = Limit.valueOf(hResponse.getString("limit"));
        origin = hResponse.getString("origin");
        expiresAt = hResponse.getString("expires_at");
    }

    /**
     * Method to get {@link #limit} instance <br>
     * No-any params required
     *
     * @return {@link #limit} instance as {@link Limit}
     **/
    public Limit getLimit() {
        return limit;
    }

    /**
     * Method to get {@link #origin} instance <br>
     * No-any params required
     *
     * @return {@link #origin} instance as {@link String}
     **/
    public String getOrigin() {
        return origin;
    }

    /**
     * Method to get {@link #expiresAt} instance <br>
     * No-any params required
     *
     * @return {@link #expiresAt} instance as {@link String}
     **/
    public String getExpiresAt() {
        return expiresAt;
    }

    /**
     * Method to get {@link #expiresAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #expiresAt} timestamp as long
     **/
    public long getExpiresAtTimestamp() {
        return timeFormatter.formatAsTimestamp(expiresAt);
    }

    /**
     * Method to create an interaction
     *
     * @param interactionResponse : obtained from GitHub's response
     * @param format              :              return type formatter -> {@link ReturnFormat}
     * @return interaction restrictions as {@code "format"} defines
     **/
    @Returner
    public static <T> T returnInteraction(String interactionResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(interactionResponse);
            case LIBRARY_OBJECT:
                JSONObject jInteraction = new JSONObject(interactionResponse);
                if (!jInteraction.isEmpty())
                    return (T) new Interaction(jInteraction);
                else
                    return null;
            default:
                return (T) interactionResponse;
        }
    }

}
