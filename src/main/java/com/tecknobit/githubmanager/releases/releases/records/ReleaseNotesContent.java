package com.tecknobit.githubmanager.releases.releases.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code ReleaseNotesContent} class is useful to format a GitHub's release notes content
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/releases/releases#generate-release-notes-content-for-a-release">
 * Generate release notes content for a release</a>
 * @see GitHubResponse
 **/
public class ReleaseNotesContent extends GitHubResponse {

    /**
     * {@code name} the generated name of the release
     **/
    private final String name;

    /**
     * {@code body} the generated body describing the contents of the release supporting Markdown formatting
     **/
    private final String body;

    /**
     * Constructor to init a {@link ReleaseNotesContent}
     *
     * @param name : the generated name of the release
     * @param body : the generated body describing the contents of the release supporting Markdown formatting
     **/
    public ReleaseNotesContent(String name, String body) {
        super(null);
        this.name = name;
        this.body = body;
    }

    /**
     * Constructor to init a {@link ReleaseNotesContent}
     *
     * @param jReleaseNotesContent : release notes content details as {@link JSONObject}
     **/
    public ReleaseNotesContent(JSONObject jReleaseNotesContent) {
        super(jReleaseNotesContent);
        name = hResponse.getString("name");
        body = hResponse.getString("body");
    }

    /**
     * Method to get {@link #name} instance <br>
     * No-any params required
     *
     * @return {@link #name} instance as {@link String}
     **/
    public String getName() {
        return name;
    }

    /**
     * Method to get {@link #body} instance <br>
     * No-any params required
     *
     * @return {@link #body} instance as {@link String}
     **/
    public String getBody() {
        return body;
    }

}
