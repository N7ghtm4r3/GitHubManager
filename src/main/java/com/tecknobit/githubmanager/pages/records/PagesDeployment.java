package com.tecknobit.githubmanager.pages.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code PagesDeployment} class is useful to format a GitHub's pages deployment
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#create-a-github-pages-deployment">
 * Create a GitHub Pages deployment</a>
 * @see GitHubResponse
 **/
public class PagesDeployment extends GitHubResponse {

    /**
     * {@code statusUrl} the URI to monitor GitHub Pages deployment status
     **/
    private final String statusUrl;

    /**
     * {@code pageUrl} the URI to the deployed GitHub Pages
     **/
    private final String pageUrl;

    /**
     * {@code previewUrl} the URI to the deployed GitHub Pages preview
     **/
    private final String previewUrl;

    /**
     * Constructor to init a {@link PagesDeployment}
     *
     * @param statusUrl  : the URI to monitor GitHub Pages deployment status
     * @param pageUrl    : the URI to the deployed GitHub Pages
     * @param previewUrl : the URI to the deployed GitHub Pages preview
     **/
    public PagesDeployment(String statusUrl, String pageUrl, String previewUrl) {
        super(null);
        this.statusUrl = statusUrl;
        this.pageUrl = pageUrl;
        this.previewUrl = previewUrl;
    }

    /**
     * Constructor to init a {@link PagesDeployment}
     *
     * @param jPagesDeployment: pages deployment details as {@link JSONObject}
     **/
    public PagesDeployment(JSONObject jPagesDeployment) {
        super(jPagesDeployment);
        statusUrl = hResponse.getString("status_url");
        pageUrl = hResponse.getString("page_url");
        previewUrl = hResponse.getString("preview_url");
    }

    /**
     * Method to get {@link #statusUrl} instance <br>
     * No-any params required
     *
     * @return {@link #statusUrl} instance as {@link String}
     **/
    public String getStatusUrl() {
        return statusUrl;
    }

    /**
     * Method to get {@link #pageUrl} instance <br>
     * No-any params required
     *
     * @return {@link #pageUrl} instance as {@link String}
     **/
    public String getPageUrl() {
        return pageUrl;
    }

    /**
     * Method to get {@link #previewUrl} instance <br>
     * No-any params required
     *
     * @return {@link #previewUrl} instance as {@link String}
     **/
    public String getPreviewUrl() {
        return previewUrl;
    }

}
