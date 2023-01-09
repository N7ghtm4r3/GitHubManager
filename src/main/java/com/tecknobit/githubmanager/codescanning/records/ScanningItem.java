package com.tecknobit.githubmanager.codescanning.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

public class ScanningItem extends GitHubResponse {

    protected final String ref;
    protected final String analysisKey;
    protected final String environment;
    protected final String commitSha;
    protected final String category;

    public ScanningItem(String ref, String analysisKey, String environment, String commitSha, String category) {
        super(null);
        this.ref = ref;
        this.analysisKey = analysisKey;
        this.environment = environment;
        this.commitSha = commitSha;
        this.category = category;
    }

    public ScanningItem(JSONObject jItem) {
        super(jItem);
        ref = hResponse.getString("ref");
        analysisKey = hResponse.getString("analysis_key");
        environment = hResponse.getString("environment");
        commitSha = hResponse.getString("commit_sha");
        category = hResponse.getString("category");
    }

    public String getRef() {
        return ref;
    }

    public String getAnalysisKey() {
        return analysisKey;
    }

    public String getEnvironment() {
        return environment;
    }

    public String getCommitSha() {
        return commitSha;
    }

    public String getCategory() {
        return category;
    }

}
