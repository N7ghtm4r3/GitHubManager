package com.tecknobit.githubmanager.codescanning.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code ScanningAnalysis} class is useful to format a GitHub's scanning analysis
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#upload-an-analysis-as-sarif-data">
 * Upload an analysis as SARIF data</a>
 * @see GitHubResponse
 **/
public class SARIFData extends GitHubResponse {

    /**
     * {@code id} an identifier for the upload
     **/
    private final String id;

    /**
     * {@code url} the REST API URL for checking the status of the upload
     **/
    private final String url;

    /**
     * Constructor to init a {@link SARIFData}
     *
     * @param id:  an identifier for the upload
     * @param url: the REST API URL for checking the status of the upload
     **/
    public SARIFData(String id, String url) {
        super(null);
        this.id = id;
        this.url = url;
    }

    /**
     * Constructor to init a {@link SARIFData}
     *
     * @param jSARIFData: SARIF data details a {@link JSONObject}
     **/
    public SARIFData(JSONObject jSARIFData) {
        super(jSARIFData);
        id = hResponse.getString("id");
        url = hResponse.getString("url");
    }

    /**
     * Method to get {@link #id} instance <br>
     * No-any params required
     *
     * @return {@link #id} instance as {@link String}
     **/
    public String getId() {
        return id;
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
