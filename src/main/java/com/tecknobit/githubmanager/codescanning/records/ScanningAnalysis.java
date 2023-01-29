package com.tecknobit.githubmanager.codescanning.records;

import com.tecknobit.githubmanager.codescanning.records.ScanningAlert.Tool;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code ScanningAnalysis} class is useful to format a GitHub's scanning analysis
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-analyses-for-a-repository">
 *             List code scanning analyses for a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/code-scanning#get-a-code-scanning-analysis-for-a-repository">
 *             Get a code scanning analysis for a repository</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see ScanningItem
 **/
public class ScanningAnalysis extends ScanningItem {

    /**
     * {@code error} value
     **/
    private final String error;

    /**
     * {@code createdAt} the time that the analysis was created in ISO 8601 format: "YYYY-MM-DDTHH:MM:SSZ"
     **/
    private final String createdAt;

    /**
     * {@code resultsCount} the total number of results in the analysis
     **/
    private final int resultsCount;

    /**
     * {@code rulesCount} the total number of rules used in the analysis
     **/
    private final int rulesCount;

    /**
     * {@code id} unique identifier for this analysis
     **/
    private final long id;

    /**
     * {@code url} the REST API URL of the analysis resource
     **/
    private final String url;

    /**
     * {@code sarifId} an identifier for the upload
     **/
    private final long sarifId;

    /**
     * {@code tool} the name of the tool used to generate the code scanning analysis
     **/
    private final Tool tool;

    /**
     * {@code deletable} whether the analysis is deletable
     **/
    private final boolean deletable;

    /**
     * {@code warning} generated when processing the analysis
     **/
    private final String warning;

    /**
     * Constructor to init a {@link ScanningAnalysis}
     *
     * @param ref:          the full Git reference
     * @param analysisKey:  identifies the configuration under which the analysis was executed
     * @param environment:  identifies the variable values associated with the environment in which this analysis was performed
     * @param commitSha:    the SHA of the commit to which the analysis you are uploading relates
     * @param category:     identifies the configuration under which the analysis was executed. Used to distinguish be
     *                      multiple analyses for the same tool and commit, but performed on different languages or different parts of the code
     * @param error:        error value
     * @param createdAt:    the time that the analysis was created in ISO 8601 format: "YYYY-MM-DDTHH:MM:SSZ"
     * @param resultsCount: the total number of results in the analysis
     * @param rulesCount:   the total number of rules used in the analysis
     * @param id:           unique identifier for this analysis
     * @param url:          the REST API URL of the analysis resource
     * @param sarifId:      an identifier for the upload
     * @param tool:         the name of the tool used to generate the code scanning analysis
     * @param deletable:    whether the analysis is deletable
     * @param warning:      warning generated when processing the analysis
     **/
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

    /**
     * Constructor to init a {@link ScanningAnalysis}
     *
     * @param jAnalysis: analysis details as {@link JSONObject}
     **/
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

    /**
     * Method to get {@link #error} instance <br>
     * No-any params required
     *
     * @return {@link #error} instance as {@link String}
     **/
    public String getError() {
        return error;
    }

    /**
     * Method to get {@link #createdAt} instance <br>
     * No-any params required
     *
     * @return {@link #createdAt} instance as {@link String}
     **/
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Method to get {@link #createdAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #createdAt} timestamp as long
     **/
    public long getCreatedAtTimestamp() {
        return getDateTimestamp(createdAt);
    }

    /**
     * Method to get {@link #resultsCount} instance <br>
     * No-any params required
     *
     * @return {@link #resultsCount} instance as int
     **/
    public int getResultsCount() {
        return resultsCount;
    }

    /**
     * Method to get {@link #rulesCount} instance <br>
     * No-any params required
     *
     * @return {@link #rulesCount} instance as int
     **/
    public int getRulesCount() {
        return rulesCount;
    }

    /**
     * Method to get {@link #id} instance <br>
     * No-any params required
     *
     * @return {@link #id} instance as long
     **/
    public long getId() {
        return id;
    }

    /**
     * Method to get {@link #url} instance <br>
     * No-any params required
     *
     * @return {@link #url} instance as {@link String}
     **/
    public String getUrl() {
        return url;
    }

    /**
     * Method to get {@link #sarifId} instance <br>
     * No-any params required
     *
     * @return {@link #sarifId} instance as long
     **/
    public long getSarifId() {
        return sarifId;
    }

    /**
     * Method to get {@link #tool} instance <br>
     * No-any params required
     *
     * @return {@link #tool} instance as {@link Tool}
     **/
    public Tool getTool() {
        return tool;
    }

    /**
     * Method to get {@link #deletable} instance <br>
     * No-any params required
     *
     * @return {@link #deletable} instance as boolean
     **/
    public boolean isDeletable() {
        return deletable;
    }

    /**
     * Method to get {@link #warning} instance <br>
     * No-any params required
     *
     * @return {@link #warning} instance as {@link String}
     **/
    public String getWarning() {
        return warning;
    }

}
