package com.tecknobit.githubmanager.actions.selfhosted.runners.records;

import com.tecknobit.githubmanager.records.basics.GitHubResponse;
import org.json.JSONObject;

public class Application extends GitHubResponse {

    private final String os;
    private final String architecture;
    private final String downloadUrl;
    private final String fileName;
    private String tempDownloadToken;
    private String sha256Checksum;

    public Application(String os, String architecture, String downloadUrl, String fileName, String tempDownloadToken,
                       String sha256Checksum) {
        super(null);
        this.os = os;
        this.architecture = architecture;
        this.downloadUrl = downloadUrl;
        this.fileName = fileName;
        this.tempDownloadToken = tempDownloadToken;
        this.sha256Checksum = sha256Checksum;
    }

    public Application(String os, String architecture, String downloadUrl, String fileName) {
        super(null);
        this.os = os;
        this.architecture = architecture;
        this.downloadUrl = downloadUrl;
        this.fileName = fileName;
    }

    /**
     * Constructor to init a {@link Application}
     *
     * @param jApplication : application details as {@link JSONObject}
     **/
    public Application(JSONObject jApplication) {
        super(jApplication);
        os = hResponse.getString("os");
        architecture = hResponse.getString("architecture");
        downloadUrl = hResponse.getString("download_url");
        fileName = hResponse.getString("filename");
        tempDownloadToken = hResponse.getString("temp_download_token");
        sha256Checksum = hResponse.getString("sha256_checksum");
    }

    public String getOs() {
        return os;
    }

    public String getArchitecture() {
        return architecture;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public String getTempDownloadToken() {
        return tempDownloadToken;
    }

    public String getSha256Checksum() {
        return sha256Checksum;
    }

}
