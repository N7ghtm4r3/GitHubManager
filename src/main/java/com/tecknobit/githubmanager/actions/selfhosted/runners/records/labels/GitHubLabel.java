package com.tecknobit.githubmanager.actions.selfhosted.runners.records.labels;

import com.tecknobit.githubmanager.records.basics.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code Label} class is useful to format a GitHub's label
 *
 * @author N7ghtm4r3 - Tecknobit
 **/
public class GitHubLabel extends GitHubResponse {

    /**
     * {@code id} identifier of the label
     **/
    private final long id;

    /**
     * {@code name} identifier of the label
     **/
    private final String name;

    /**
     * {@code type} type of the label
     **/
    private final String type;

    /**
     * Constructor to init a {@link GitHubLabel}
     *
     * @param id:   identifier of the label
     * @param name: the name of the label
     * @param type: type of the label
     **/
    public GitHubLabel(long id, String name, String type) {
        super(null);
        this.id = id;
        this.name = name;
        this.type = type;
    }

    /**
     * Constructor to init a {@link GitHubLabel}
     *
     * @param jGitHubLabel : label details as {@link JSONObject}
     **/
    public GitHubLabel(JSONObject jGitHubLabel) {
        super(jGitHubLabel);
        id = hResponse.getLong("id", 0);
        name = hResponse.getString("name");
        type = hResponse.getString("type");
    }

    /**
     * Method to get {@link #id} instance <br>
     * Any params required
     *
     * @return {@link #id} instance as long
     **/
    public long getId() {
        return id;
    }

    /**
     * Method to get {@link #name} instance <br>
     * Any params required
     *
     * @return {@link #name} instance as {@link String}
     **/
    public String getName() {
        return name;
    }

    /**
     * Method to get {@link #type} instance <br>
     * Any params required
     *
     * @return {@link #type} instance as {@link String}
     **/
    public String getType() {
        return type;
    }

}
