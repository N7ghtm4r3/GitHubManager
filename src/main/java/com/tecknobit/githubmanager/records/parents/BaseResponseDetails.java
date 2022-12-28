package com.tecknobit.githubmanager.records.parents;

import com.tecknobit.githubmanager.GitHubManager;
import org.json.JSONObject;

/**
 * The {@code BaseResponseDetails} class is useful to format all GitHub's responses giving basics methods
 * for others {@link GitHubManager.ReturnFormat#LIBRARY_OBJECT} and basics details like: {@code "id"}, {@code "name"}
 * and {@code "url"}
 *
 * @author N7ghtm4r3 - Tecknobit
 * @see GitHubResponse
 **/
public class BaseResponseDetails extends GitHubResponse {

    /**
     * {@code id} identifier value
     **/
    protected final long id;

    /**
     * {@code name} the name of the repository
     **/
    protected final String name;

    /**
     * {@code "url"} value
     **/
    protected final String url;

    /**
     * Constructor to init a {@link BaseResponseDetails}
     *
     * @param id   : identifier value
     * @param name : the name of the item
     * @param url: url value
     **/
    public BaseResponseDetails(long id, String name, String url) {
        super(null);
        this.id = id;
        this.name = name;
        this.url = url;
    }

    /**
     * Constructor to init a {@link BaseResponseDetails}
     *
     * @param jBasicsResponseDetails : basics response details as {@link JSONObject}
     **/
    public BaseResponseDetails(JSONObject jBasicsResponseDetails) {
        super(jBasicsResponseDetails);
        id = hResponse.getLong("id", 0);
        name = hResponse.getString("name");
        url = hResponse.getString("url");
    }

    /**
     * Method to get {@link #id} instance <br>
     * Any params required
     *
     * @return {@link #id} instance as long
     **/
    public long getId() {
        return id;
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

}
