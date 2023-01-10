package com.tecknobit.githubmanager.codescanning.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code ScanningAnalysisDeletion} class is useful to format a GitHub's scanning analysis deletion
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#delete-a-code-scanning-analysis-from-a-repository">
 * Delete a code scanning analysis from a repository</a>
 * @see GitHubResponse
 **/
public class ScanningAnalysisDeletion extends GitHubResponse {

    /**
     * {@code nextAnalysisUrl} next deletable analysis in chain, without last analysis deletion confirmation
     **/
    private final String nextAnalysisUrl;

    /**
     * {@code confirmDeleteUrl} next deletable analysis in chain, with last analysis deletion confirmation
     **/
    private final String confirmDeleteUrl;

    /**
     * Constructor to init a {@link ScanningAnalysisDeletion}
     *
     * @param nextAnalysisUrl  : next deletable analysis in chain, without last analysis deletion confirmation
     * @param confirmDeleteUrl :  next deletable analysis in chain, with last analysis deletion confirmation
     **/
    public ScanningAnalysisDeletion(String nextAnalysisUrl, String confirmDeleteUrl) {
        super(null);
        this.nextAnalysisUrl = nextAnalysisUrl;
        this.confirmDeleteUrl = confirmDeleteUrl;
    }

    /**
     * Constructor to init a {@link ScanningAnalysisDeletion}
     *
     * @param jDeletion : scanning analysis deletion details as {@link JSONObject}
     **/
    public ScanningAnalysisDeletion(JSONObject jDeletion) {
        super(jDeletion);
        nextAnalysisUrl = hResponse.getString("next_analysis_url");
        confirmDeleteUrl = hResponse.getString("confirm_delete_url");
    }

    /**
     * Method to get {@link #nextAnalysisUrl} instance <br>
     * Any params required
     *
     * @return {@link #nextAnalysisUrl} instance as {@link String}
     **/
    public String getNextAnalysisUrl() {
        return nextAnalysisUrl;
    }

    /**
     * Method to get {@link #confirmDeleteUrl} instance <br>
     * Any params required
     *
     * @return {@link #confirmDeleteUrl} instance as {@link String}
     **/
    public String getConfirmDeleteUrl() {
        return confirmDeleteUrl;
    }

}
