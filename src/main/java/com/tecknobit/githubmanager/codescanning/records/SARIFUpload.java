package com.tecknobit.githubmanager.codescanning.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The {@code SARIFUpload} class is useful to format a GitHub's SARIF upload
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/code-scanning#get-information-about-a-sarif-upload">
 * Get information about a SARIF upload</a>
 * @see GitHubResponse
 **/
public class SARIFUpload extends GitHubResponse {

    /**
     * {@code processingStatus} {@code "pending"} files have not yet been processed, while {@code "complete"} means results from the SARIF
     * have been stored. {@code "failed"} files have either not been processed at all, or could only be partially processed
     **/
    private final ProcessingStatus processingStatus;

    /**
     * {@code analysesUrl} the REST API URL for getting the analyses associated with the upload
     **/
    private final String analysesUrl;

    /**
     * {@code errors} any errors that occurred during processing of the delivery
     **/
    private final ArrayList<String> errors;

    /**
     * Constructor to init a {@link SARIFUpload}
     *
     * @param processingStatus : processing status
     * @param analysesUrl      :  the REST API URL for getting the analyses associated with the upload
     * @param errors           :   any errors that occurred during processing of the delivery
     **/
    public SARIFUpload(ProcessingStatus processingStatus, String analysesUrl, ArrayList<String> errors) {
        super(null);
        this.processingStatus = processingStatus;
        this.analysesUrl = analysesUrl;
        this.errors = errors;
    }

    /**
     * Constructor to init a {@link SARIFUpload}
     *
     * @param jSarifUpload : SARIF upload details as {@link JSONObject}
     **/
    public SARIFUpload(JSONObject jSarifUpload) {
        super(jSarifUpload);
        processingStatus = ProcessingStatus.valueOf(hResponse.getString("processing_status"));
        analysesUrl = hResponse.getString("analyses_url");
        errors = returnStringsList(hResponse.getJSONArray("errors"));
    }

    /**
     * Method to get {@link #processingStatus} instance <br>
     * No-any params required
     *
     * @return {@link #processingStatus} instance as {@link ProcessingStatus}
     **/
    public ProcessingStatus getProcessingStatus() {
        return processingStatus;
    }

    /**
     * Method to get {@link #analysesUrl} instance <br>
     * No-any params required
     *
     * @return {@link #analysesUrl} instance as {@link String}
     **/
    public String getAnalysesUrl() {
        return analysesUrl;
    }

    /**
     * Method to get {@link #errors} instance <br>
     * No-any params required
     *
     * @return {@link #errors} instance as {@link ArrayList} of {@link String}
     **/
    public ArrayList<String> getErrors() {
        return errors;
    }

    /**
     * {@code ProcessingStatus} list of available processing statuses
     **/
    public enum ProcessingStatus {

        /**
         * {@code pending} status
         **/
        pending,

        /**
         * {@code complete} status
         **/
        complete,

        /**
         * {@code failed} status
         **/
        failed

    }

}
