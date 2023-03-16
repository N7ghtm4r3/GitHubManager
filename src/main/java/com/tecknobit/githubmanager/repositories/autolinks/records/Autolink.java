package com.tecknobit.githubmanager.repositories.autolinks.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code Autolink} class is useful to format a GitHub's autolink
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/repos/autolinks#list-all-autolinks-of-a-repository">
 *             List all autolinks of a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/repos/autolinks#create-an-autolink-reference-for-a-repository">
 *             Create an autolink reference for a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/repos/autolinks#get-an-autolink-reference-of-a-repository">
 *             Get an autolink reference of a repository</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class Autolink extends GitHubResponse {

    /**
     * {@code id} of the autolink
     **/
    private final long id;

    /**
     * {@code keyPrefix} the prefix of a key that is linkified
     **/
    private final String keyPrefix;

    /**
     * {@code urlTemplate} a template for the target URL that is generated if a key was found
     **/
    private final String urlTemplate;

    /**
     * {@code isAlphanumeric} whether this autolink reference matches alphanumeric characters. If false,
     * this autolink reference only matches numeric characters
     **/
    private final boolean isAlphanumeric;

    /**
     * Constructor to init a {@link Autolink}
     *
     * @param id             : id of the autolink
     * @param keyPrefix      : the prefix of a key that is linkified
     * @param urlTemplate    : a template for the target URL that is generated if a key was found
     * @param isAlphanumeric : whether this autolink reference matches alphanumeric characters
     **/
    public Autolink(long id, String keyPrefix, String urlTemplate, boolean isAlphanumeric) {
        super(null);
        this.id = id;
        this.keyPrefix = keyPrefix;
        this.urlTemplate = urlTemplate;
        this.isAlphanumeric = isAlphanumeric;
    }

    /**
     * Constructor to init a {@link Autolink}
     *
     * @param jAutolink : autolink details as {@link JSONObject}
     **/
    public Autolink(JSONObject jAutolink) {
        super(jAutolink);
        id = hResponse.getLong("id");
        keyPrefix = hResponse.getString("key_prefix");
        urlTemplate = hResponse.getString("url_template");
        isAlphanumeric = hResponse.getBoolean("is_alphanumeric");
    }

    /**
     * Method to get {@link #id} instance <br>
     * No-any params required
     *
     * @return {@link #id} instance as boolean
     **/
    public long getId() {
        return id;
    }

    /**
     * Method to get {@link #keyPrefix} instance <br>
     * No-any params required
     *
     * @return {@link #keyPrefix} instance as {@link String}
     **/
    public String getKeyPrefix() {
        return keyPrefix;
    }

    /**
     * Method to get {@link #urlTemplate} instance <br>
     * No-any params required
     *
     * @return {@link #urlTemplate} instance as {@link String}
     **/
    public String getUrlTemplate() {
        return urlTemplate;
    }

    /**
     * Method to get {@link #isAlphanumeric} instance <br>
     * No-any params required
     *
     * @return {@link #isAlphanumeric} instance as boolean
     **/
    public boolean isAlphanumeric() {
        return isAlphanumeric;
    }

}
