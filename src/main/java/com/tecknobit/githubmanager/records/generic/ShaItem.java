package com.tecknobit.githubmanager.records.generic;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat;

/**
 * The {@code ShaItem} class is useful to format a GitHub's sha item
 *
 * @author N7ghtm4r3 - Tecknobit
 * @see GitHubResponse
 **/
public class ShaItem extends GitHubResponse {

    /**
     * {@code sha} of the item
     **/
    protected final String sha;

    /**
     * {@code url} of the item
     **/
    protected final String url;

    /**
     * Constructor to init a {@link ShaItem}
     *
     * @param sha : sha of the item
     * @param url : url of the item
     **/
    public ShaItem(String sha, String url) {
        super(null);
        this.sha = sha;
        this.url = url;
    }

    /**
     * Constructor to init a {@link ShaItem}
     *
     * @param jShaItem : sha item details as {@link JSONObject}
     **/
    public ShaItem(JSONObject jShaItem) {
        super(jShaItem);
        sha = hResponse.getString("sha");
        url = hResponse.getString("url");
    }

    /**
     * Method to get {@link #sha} instance <br>
     * No-any params required
     *
     * @return {@link #sha} instance as {@link String}
     **/
    public String getSha() {
        return sha;
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

    /**
     * Method to create a sha item
     *
     * @param shaItemResponse: obtained from GitHub's response
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return sha item as {@code "format"} defines
     **/
    @Returner
    public static <T> T returnShaItem(String shaItemResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(shaItemResponse);
            case LIBRARY_OBJECT:
                return (T) new ShaItem(new JSONObject(shaItemResponse));
            default:
                return (T) shaItemResponse;
        }
    }

}
