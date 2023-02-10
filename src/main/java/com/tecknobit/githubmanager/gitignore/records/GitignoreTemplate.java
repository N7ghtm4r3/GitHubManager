package com.tecknobit.githubmanager.gitignore.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code GitignoreTemplate} class is useful to format a GitHub's gitignore template
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/gitignore#get-all-gitignore-templates">
 *             Get all gitignore templates</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/gitignore#get-a-gitignore-template">
 *             Get a gitignore template</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class GitignoreTemplate extends GitHubResponse {

    /**
     * {@code name} of the template
     **/
    private final String name;

    /**
     * {@code source} of the template
     **/
    private final String source;

    /**
     * Constructor to init a {@link GitignoreTemplate}
     *
     * @param name   : name of the template
     * @param source : source of the template
     **/
    public GitignoreTemplate(String name, String source) {
        super(null);
        this.name = name;
        this.source = source;
    }

    /**
     * Constructor to init a {@link GitignoreTemplate}
     *
     * @param jTemplate : template details as {@link JSONObject}
     **/
    public GitignoreTemplate(JSONObject jTemplate) {
        super(jTemplate);
        name = hResponse.getString("name");
        source = hResponse.getString("source");
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
     * Method to get {@link #source} instance <br>
     * No-any params required
     *
     * @return {@link #source} instance as {@link String}
     **/
    public String getSource() {
        return source;
    }

}
