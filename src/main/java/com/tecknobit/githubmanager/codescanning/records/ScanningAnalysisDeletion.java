package com.tecknobit.githubmanager.codescanning.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

public class ScanningAnalysisDeletion extends GitHubResponse {

    private final String nextAnalysisUrl;
    private final String confirmDeleteUrl;

    public ScanningAnalysisDeletion(String nextAnalysisUrl, String confirmDeleteUrl) {
        super(null);
        this.nextAnalysisUrl = nextAnalysisUrl;
        this.confirmDeleteUrl = confirmDeleteUrl;
    }

    public ScanningAnalysisDeletion(JSONObject jDeletion) {
        super(jDeletion);
        nextAnalysisUrl = hResponse.getString("next_analysis_url");
        confirmDeleteUrl = hResponse.getString("confirm_delete_url");
    }

    public String getNextAnalysisUrl() {
        return nextAnalysisUrl;
    }

    public String getConfirmDeleteUrl() {
        return confirmDeleteUrl;
    }

}
