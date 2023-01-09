package com.tecknobit.githubmanager.codescanning.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class SARIFUpload extends GitHubResponse {

    private final ProcessingStatus processingStatus;
    private final String analysesUrl;
    private final ArrayList<String> errors;
    public SARIFUpload(ProcessingStatus processingStatus, String analysesUrl, ArrayList<String> errors) {
        super(null);
        this.processingStatus = processingStatus;
        this.analysesUrl = analysesUrl;
        this.errors = errors;
    }

    public SARIFUpload(JSONObject jSarifUpload) {
        super(jSarifUpload);
        processingStatus = ProcessingStatus.valueOf(hResponse.getString("processing_status"));
        analysesUrl = hResponse.getString("analyses_url");
        errors = returnStringsList(hResponse.getJSONArray("errors"));
    }

    public ProcessingStatus getProcessingStatus() {
        return processingStatus;
    }

    public String getAnalysesUrl() {
        return analysesUrl;
    }

    public Collection<String> getErrors() {
        return errors;
    }

    public enum ProcessingStatus {

        pending,
        complete,
        failed;

    }

}
