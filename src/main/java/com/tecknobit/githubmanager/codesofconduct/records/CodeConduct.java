package com.tecknobit.githubmanager.codesofconduct.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code CodeConduct} class is useful to format a GitHub's code conduct
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/codes-of-conduct#get-all-codes-of-conduct">
 *             Get all codes of conduct</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/codes-of-conduct#get-a-code-of-conduct">
 *             Get a code of conduct</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class CodeConduct extends GitHubResponse {

    /**
     * {@code key} of the code conduct
     **/
    private final String key;

    /**
     * {@code name} of the code conduct
     **/
    private final String name;

    /**
     * {@code url} of the code conduct
     **/
    private final String url;

    /**
     * {@code body} of the code conduct
     **/
    private final String body;

    /**
     * {@code htmlUrl} html url of the code conduct
     **/
    private final String htmlUrl;

    /**
     * Constructor to init a {@link GitHubResponse}
     *
     * @param key:     key of the code conduct
     * @param name:    name of the code conduct
     * @param url:     url of the code conduct
     * @param body:    body of the code conduct
     * @param htmlUrl: html url of the code conduct
     **/
    public CodeConduct(String key, String name, String url, String body, String htmlUrl) {
        super(null);
        this.key = key;
        this.name = name;
        this.url = url;
        this.body = body;
        this.htmlUrl = htmlUrl;
    }

    /**
     * Constructor to init a {@link GitHubResponse}
     *
     * @param jCodeConduct: code of conduct details as {@link JSONObject}
     **/
    public CodeConduct(JSONObject jCodeConduct) {
        super(jCodeConduct);
        key = hResponse.getString("key");
        name = hResponse.getString("name");
        url = hResponse.getString("url");
        body = hResponse.getString("body");
        htmlUrl = hResponse.getString("html_url");
    }

    /**
     * Method to get {@link #key} instance <br>
     * Any params required
     *
     * @return {@link #key} instance as {@link String}
     **/
    public String getKey() {
        return key;
    }

    /**
     * Method to get {@link #name} instance <br>
     * Any params required
     *
     * @return {@link #name} instance as {@link String}
     **/
    public String getName() {
        return name;
    }

    /**
     * Method to get {@link #url} instance <br>
     * Any params required
     *
     * @return {@link #url} instance as {@link String}
     **/
    public String getUrl() {
        return url;
    }

    /**
     * Method to get {@link #body} instance <br>
     * Any params required
     *
     * @return {@link #body} instance as {@link String}
     **/
    public String getBody() {
        return body;
    }

    /**
     * Method to get {@link #htmlUrl} instance <br>
     * Any params required
     *
     * @return {@link #htmlUrl} instance as {@link String}
     **/
    public String getHtmlUrl() {
        return htmlUrl;
    }

}
