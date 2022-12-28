package com.tecknobit.githubmanager.actions.selfhosted.runners.records.labels;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

import static com.tecknobit.githubmanager.actions.selfhosted.runners.records.labels.GitHubLabel.LabelType.read_only;

/**
 * The {@code Label} class is useful to format a GitHub's label
 *
 * @author N7ghtm4r3 - Tecknobit
 **/
public class GitHubLabel extends GitHubResponse {

    /**
     * {@code type} type of the label
     **/
    private final LabelType type;

    /**
     * {@code id} identifier of the label
     **/
    private final long id;

    /**
     * {@code name} identifier of the label
     **/
    private final String name;

    /**
     * Constructor to init a {@link GitHubLabel}
     *
     * @param id:   identifier of the label
     * @param name: the name of the label
     * @param type: type of the label
     **/
    public GitHubLabel(long id, String name, LabelType type) {
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
        type = LabelType.valueOf(hResponse.getString("type", read_only.name()));
    }

    /**
     * Method to get {@link #type} instance <br>
     * Any params required
     *
     * @return {@link #type} instance as {@link String}
     **/
    public LabelType getType() {
        return type;
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

    public enum LabelType {

        read_only("read-only"),
        custom("custom");

        private final String labelType;

        LabelType(String labelType) {
            this.labelType = labelType;
        }

        @Override
        public String toString() {
            return labelType;
        }

    }

}
