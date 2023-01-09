package com.tecknobit.githubmanager.codescanning.records;

import com.tecknobit.githubmanager.codescanning.records.ScanningAlert.Tool;
import org.json.JSONObject;

public class ScanningAnalysis extends ScanningItem {

    private final String error;
    private final String createdAt;
    private final int resultsCount;
    private final int rulesCount;
    private final long id;
    private final String url;
    private final long sarifId;
    private final Tool tool;
    private final boolean deletable;
    private final String warning;

    public ScanningAnalysis(String ref, String analysisKey, String environment, String commitSha, String category,
                            String error, String createdAt, int resultsCount, int rulesCount, long id, String url,
                            long sarifId, Tool tool, boolean deletable, String warning) {
        super(ref, analysisKey, environment, commitSha, category);
        this.error = error;
        this.createdAt = createdAt;
        this.resultsCount = resultsCount;
        this.rulesCount = rulesCount;
        this.id = id;
        this.url = url;
        this.sarifId = sarifId;
        this.tool = tool;
        this.deletable = deletable;
        this.warning = warning;
    }

    public ScanningAnalysis(JSONObject jAnalysis) {
        super(jAnalysis);
        error = hResponse.getString("error");
        createdAt = hResponse.getString("created_at");
        resultsCount = hResponse.getInt("results_count", 0);
        rulesCount = hResponse.getInt("rules_count", 0);
        id = hResponse.getLong("id", 0);
        url = hResponse.getString("url");
        sarifId = hResponse.getLong("sarif_id", 0);
        tool = new Tool(hResponse.getJSONObject("tool", new JSONObject()));
        deletable = hResponse.getBoolean("deletable");
        warning = hResponse.getString("warning");
    }

    public String getError() {
        return error;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public int getResultsCount() {
        return resultsCount;
    }

    public int getRulesCount() {
        return rulesCount;
    }

    public long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public long getSarifId() {
        return sarifId;
    }

    public Tool getTool() {
        return tool;
    }

    public boolean isDeletable() {
        return deletable;
    }

    public String getWarning() {
        return warning;
    }

}
