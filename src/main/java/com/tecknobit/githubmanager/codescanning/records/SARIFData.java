package com.tecknobit.githubmanager.codescanning.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

public class SARIFData extends GitHubResponse {

    private final String id;
    private final String url;

    public SARIFData(String id, String url) {
        super(null);
        this.id = id;
        this.url = url;
    }

    public SARIFData(JSONObject jSarifData) {
        super(jSarifData);
        id = hResponse.getString("id");
        url = hResponse.getString("url");
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

}
