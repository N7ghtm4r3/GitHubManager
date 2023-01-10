package com.tecknobit.githubmanager.codescanning.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code ScanningItem} class is useful to format a GitHub's scanning item
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/code-scanning#list-instances-of-a-code-scanning-alert">
 *             List instances of a code scanning alert</a>
 *     </li>
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
public class ScanningItem extends GitHubResponse {

    /**
     * {@code ref} the full Git reference, formatted as "refs/heads/<branch name>", "refs/pull/<number>/merge", or
     * "refs/pull/<number>/head"
     **/
    protected final String ref;

    /**
     * {@code analysisKey} identifies the configuration under which the analysis was executed.
     * For example, in GitHub Actions this includes the workflow filename and job name
     **/
    protected final String analysisKey;

    /**
     * {@code environment} identifies the variable values associated with the environment in which this analysis was performed
     **/
    protected final String environment;

    /**
     * {@code commitSha} the SHA of the commit to which the analysis you are uploading relates
     **/
    protected final String commitSha;

    /**
     * {@code category} identifies the configuration under which the analysis was executed. Used to distinguish between
     * multiple analyses for the same tool and commit, but performed on different languages or different parts of the code
     **/
    protected final String category;

    /**
     * Constructor to init a {@link ScanningItem}
     *
     * @param ref:         the full Git reference
     * @param analysisKey: identifies the configuration under which the analysis was executed
     * @param environment: identifies the variable values associated with the environment in which this analysis was performed
     * @param commitSha:   the SHA of the commit to which the analysis you are uploading relates
     * @param category:    identifies the configuration under which the analysis was executed. Used to distinguish be
     *                     multiple analyses for the same tool and commit, but performed on different languages or different parts of the code
     **/
    public ScanningItem(String ref, String analysisKey, String environment, String commitSha, String category) {
        super(null);
        this.ref = ref;
        this.analysisKey = analysisKey;
        this.environment = environment;
        this.commitSha = commitSha;
        this.category = category;
    }

    /**
     * Constructor to init a {@link ScanningItem}
     *
     * @param jItem: scanning item details as {@link JSONObject}
     **/
    public ScanningItem(JSONObject jItem) {
        super(jItem);
        ref = hResponse.getString("ref");
        analysisKey = hResponse.getString("analysis_key");
        environment = hResponse.getString("environment");
        commitSha = hResponse.getString("commit_sha");
        category = hResponse.getString("category");
    }

    /**
     * Method to get {@link #ref} instance <br>
     * Any params required
     *
     * @return {@link #ref} instance as {@link String}
     **/
    public String getRef() {
        return ref;
    }

    /**
     * Method to get {@link #analysisKey} instance <br>
     * Any params required
     *
     * @return {@link #analysisKey} instance as {@link String}
     **/
    public String getAnalysisKey() {
        return analysisKey;
    }

    /**
     * Method to get {@link #environment} instance <br>
     * Any params required
     *
     * @return {@link #environment} instance as {@link String}
     **/
    public String getEnvironment() {
        return environment;
    }

    /**
     * Method to get {@link #commitSha} instance <br>
     * Any params required
     *
     * @return {@link #commitSha} instance as {@link String}
     **/
    public String getCommitSha() {
        return commitSha;
    }

    /**
     * Method to get {@link #category} instance <br>
     * Any params required
     *
     * @return {@link #category} instance as {@link String}
     **/
    public String getCategory() {
        return category;
    }

}
