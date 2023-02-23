package com.tecknobit.githubmanager.licenses.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The {@code License} class is useful to format a GitHub's license
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/licenses#get-a-license">
 * Get a license</a>
 * @see GitHubResponse
 * @see CommonLicense
 **/
public class License extends CommonLicense {

    /**
     * {@code "description"} value
     **/
    private final String description;

    /**
     * {@code "implementation"} value
     **/
    private final String implementation;

    /**
     * {@code "permissions"} list
     **/
    private final ArrayList<String> permissions;

    /**
     * {@code "conditions"} list
     **/
    private final ArrayList<String> conditions;

    /**
     * {@code "limitations"} list
     **/
    private final ArrayList<String> limitations;

    /**
     * {@code "body"} value
     **/
    private final String body;

    /**
     * {@code featured} whether the license is featured
     **/
    private final boolean featured;

    /**
     * Constructor to init a {@link License}
     *
     * @param key            :     key value
     * @param name           :    name value
     * @param url            :     url value
     * @param spdxId         :  spdx identifier value
     * @param nodeId         :  identifier of the node value
     * @param htmlUrl        : html url value
     * @param description    :  description value
     * @param implementation : implementation value
     * @param permissions    : permissions   list
     * @param conditions     :   conditions  list
     * @param limitations    : limitations list
     * @param body           :  body value
     * @param featured       : whether the license is featured
     **/
    public License(String key, String name, String url, String spdxId, String nodeId, String htmlUrl, String description,
                   String implementation, ArrayList<String> permissions, ArrayList<String> conditions,
                   ArrayList<String> limitations, String body, boolean featured) {
        super(key, name, url, spdxId, nodeId, htmlUrl);
        this.description = description;
        this.implementation = implementation;
        this.permissions = permissions;
        this.conditions = conditions;
        this.limitations = limitations;
        this.body = body;
        this.featured = featured;
    }

    /**
     * Constructor to init a {@link License}
     *
     * @param jLicense : license details as {@link JSONObject}
     **/
    public License(JSONObject jLicense) {
        super(jLicense);
        description = hResponse.getString("description");
        implementation = hResponse.getString("implementation");
        permissions = returnStringsList(hResponse.getJSONArray("permissions"));
        conditions = returnStringsList(hResponse.getJSONArray("conditions"));
        limitations = returnStringsList(hResponse.getJSONArray("limitations"));
        body = hResponse.getString("body");
        featured = hResponse.getBoolean("featured");
    }

    /**
     * Method to get {@link #description} instance <br>
     * No-any params required
     *
     * @return {@link #description} instance as {@link String}
     **/
    public String getDescription() {
        return description;
    }

    /**
     * Method to get {@link #implementation} instance <br>
     * No-any params required
     *
     * @return {@link #implementation} instance as {@link String}
     **/
    public String getImplementation() {
        return implementation;
    }

    /**
     * Method to get {@link #permissions} instance <br>
     * No-any params required
     *
     * @return {@link #permissions} instance as {@link ArrayList} of {@link String}
     **/
    public ArrayList<String> getPermissions() {
        return permissions;
    }

    /**
     * Method to get {@link #conditions} instance <br>
     * No-any params required
     *
     * @return {@link #conditions} instance as {@link ArrayList} of {@link String}
     **/
    public ArrayList<String> getConditions() {
        return conditions;
    }

    /**
     * Method to get {@link #limitations} instance <br>
     * No-any params required
     *
     * @return {@link #limitations} instance as {@link ArrayList} of {@link String}
     **/
    public ArrayList<String> getLimitations() {
        return limitations;
    }

    /**
     * Method to get {@link #body} instance <br>
     * No-any params required
     *
     * @return {@link #body} instance as {@link String}
     **/
    public String getBody() {
        return body;
    }

    /**
     * Method to get {@link #featured} instance <br>
     * No-any params required
     *
     * @return {@link #featured} instance as boolean
     **/
    public boolean isFeatured() {
        return featured;
    }

}
