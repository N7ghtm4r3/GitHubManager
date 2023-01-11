package com.tecknobit.githubmanager.codespaces.codespaces.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

public class CodespaceExportDetails extends GitHubResponse {

    private final String state;
    private final String completedAt;
    private final String branch;
    private final String sha;
    private final String id;
    private final String exportUrl;
    private final String htmlUrl;

    public CodespaceExportDetails(JSONObject jResponse, String state, String completedAt, String branch, String sha,
                                  String id, String exportUrl, String htmlUrl) {
        super(jResponse);
        this.state = state;
        this.completedAt = completedAt;
        this.branch = branch;
        this.sha = sha;
        this.id = id;
        this.exportUrl = exportUrl;
        this.htmlUrl = htmlUrl;
    }

    public CodespaceExportDetails(JSONObject jCodespaceExportDetails) {
        super(jCodespaceExportDetails);
        state = hResponse.getString("state");
        completedAt = hResponse.getString("completed_at");
        branch = hResponse.getString("branch");
        sha = hResponse.getString("sha");
        id = hResponse.getString("id");
        exportUrl = hResponse.getString("export_url");
        htmlUrl = hResponse.getString("html_url");
    }

    public String getState() {
        return state;
    }

    public String getCompletedAt() {
        return completedAt;
    }

    public String getBranch() {
        return branch;
    }

    public String getSha() {
        return sha;
    }

    public String getId() {
        return id;
    }

    public String getExportUrl() {
        return exportUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

}
