package com.tecknobit.githubmanager.codespaces.codespaces.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;



/**
 * The {@code Codespace} class is useful to format a GitHub's codespace
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/codespaces/codespaces#export-a-codespace-for-the-authenticated-user">
 *             Export a codespace for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/codespaces/codespaces#get-details-about-a-codespace-export">
 *             Get details about a codespace export</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class CodespaceExportDetails extends GitHubResponse {

    /**
     * {@code state} of the latest export
     **/
    private final String state;

    /**
     * {@code completedAt} completion time of the last export operation
     **/
    private final String completedAt;

    /**
     * {@code branch} name of the exported branch
     **/
    private final String branch;

    /**
     * {@code sha} Git commit SHA of the exported branch
     **/
    private final String sha;

    /**
     * {@code id} for the export details
     **/
    private final String id;

    /**
     * {@code exportUrl} url for fetching export details
     **/
    private final String exportUrl;

    /**
     * {@code htmlUrl} web url for the exported branch
     **/
    private final String htmlUrl;

    /**
     * Constructor to init a {@link CodespaceExportDetails}
     *
     * @param state       : state of the latest export
     * @param completedAt : completion time of the last export operation
     * @param branch      : name of the exported branch
     * @param sha         : Git commit SHA of the exported branch
     * @param id          : id for the export details
     * @param exportUrl   : url for fetching export details
     * @param htmlUrl     : web url for the exported branch
     **/
    public CodespaceExportDetails(String state, String completedAt, String branch, String sha, String id, String exportUrl,
                                  String htmlUrl) {
        super(null);
        this.state = state;
        this.completedAt = completedAt;
        this.branch = branch;
        this.sha = sha;
        this.id = id;
        this.exportUrl = exportUrl;
        this.htmlUrl = htmlUrl;
    }

    /**
     * Constructor to init a {@link CodespaceExportDetails}
     *
     * @param jCodespaceExportDetails : codespace export details as {@link JSONObject}
     **/
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

    /**
     * Method to get {@link #state} instance <br>
     * No-any params required
     *
     * @return {@link #state} instance as {@link String}
     **/
    public String getState() {
        return state;
    }

    /**
     * Method to get {@link #completedAt} instance <br>
     * No-any params required
     *
     * @return {@link #completedAt} instance as {@link String}
     **/
    public String getCompletedAt() {
        return completedAt;
    }

    /**
     * Method to get {@link #completedAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #completedAt} timestamp as long
     **/
    public long getCompletedAtTimestamp() {
        return timeFormatter.formatAsTimestamp(completedAt);
    }

    /**
     * Method to get {@link #branch} instance <br>
     * No-any params required
     *
     * @return {@link #branch} instance as {@link String}
     **/
    public String getBranch() {
        return branch;
    }

    /**
     * Method to get {@link #sha} instance <br>
     * No-any params required
     *
     * @return {@link #sha} instance as {@link String}
     **/
    public String getSha() {
        return sha;
    }

    /**
     * Method to get {@link #id} instance <br>
     * No-any params required
     *
     * @return {@link #id} instance as {@link String}
     **/
    public String getId() {
        return id;
    }

    /**
     * Method to get {@link #exportUrl} instance <br>
     * No-any params required
     *
     * @return {@link #exportUrl} instance as {@link String}
     **/
    public String getExportUrl() {
        return exportUrl;
    }

    /**
     * Method to get {@link #htmlUrl} instance <br>
     * No-any params required
     *
     * @return {@link #htmlUrl} instance as {@link String}
     **/
    public String getHtmlUrl() {
        return htmlUrl;
    }

}
