package com.tecknobit.githubmanager.actions.selfhosted.runners.records;

import com.tecknobit.githubmanager.records.basics.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code Application} class is useful to format a GitHub's application
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-runner-applications-for-an-enterprise">
 *             List runner applications for an enterprise</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-runner-applications-for-an-organization">
 *             List runner applications for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-runner-applications-for-a-repository">
 *             List runner applications for a repository</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class Application extends GitHubResponse {

    /**
     * {@code os} operating system value
     **/
    private final String os;

    /**
     * {@code architecture} architecture value
     **/
    private final String architecture;

    /**
     * {@code downloadUrl} download url value
     **/
    private final String downloadUrl;

    /**
     * {@code fileName} file name value
     **/
    private final String fileName;

    /**
     * {@code tempDownloadToken} a short-lived bearer token used to download the runner, if needed
     **/
    private String tempDownloadToken;

    /**
     * {@code sha256Checksum} sha256 checksum value
     **/
    private String sha256Checksum;

    /**
     * Constructor to init a {@link Application}
     *
     * @param os                : operating system value
     * @param architecture      :architecture value
     * @param downloadUrl       : download url value
     * @param fileName          : file name value
     * @param tempDownloadToken : a short-lived bearer token used to download the runner, if needed
     * @param sha256Checksum    :sha256 checksum value
     **/
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

    /**
     * Constructor to init a {@link Application}
     *
     * @param os           : operating system value
     * @param architecture :architecture value
     * @param downloadUrl  : download url value
     * @param fileName     : file name value
     **/
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

    /**
     * Method to get {@link #os} instance <br>
     * Any params required
     *
     * @return {@link #os} instance as {@link String}
     **/
    public String getOs() {
        return os;
    }

    /**
     * Method to get {@link #architecture} instance <br>
     * Any params required
     *
     * @return {@link #architecture} instance as {@link String}
     **/
    public String getArchitecture() {
        return architecture;
    }

    /**
     * Method to get {@link #downloadUrl} instance <br>
     * Any params required
     *
     * @return {@link #downloadUrl} instance as {@link String}
     **/
    public String getDownloadUrl() {
        return downloadUrl;
    }

    /**
     * Method to get {@link #fileName} instance <br>
     * Any params required
     *
     * @return {@link #fileName} instance as {@link String}
     **/
    public String getFileName() {
        return fileName;
    }

    /**
     * Method to get {@link #tempDownloadToken} instance <br>
     * Any params required
     *
     * @return {@link #tempDownloadToken} instance as {@link String}
     **/
    public String getTempDownloadToken() {
        return tempDownloadToken;
    }

    /**
     * Method to get {@link #sha256Checksum} instance <br>
     * Any params required
     *
     * @return {@link #sha256Checksum} instance as {@link String}
     **/
    public String getSha256Checksum() {
        return sha256Checksum;
    }

}
