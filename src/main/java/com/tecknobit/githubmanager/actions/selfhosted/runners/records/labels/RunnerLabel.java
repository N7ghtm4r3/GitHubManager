package com.tecknobit.githubmanager.actions.selfhosted.runners.records.labels;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

import static com.tecknobit.githubmanager.actions.selfhosted.runners.records.labels.RunnerLabel.LabelType.read_only;

/**
 * The {@code Label} class is useful to format a GitHub's runner label
 *
 * @author N7ghtm4r3 - Tecknobit
 **/
public class RunnerLabel extends GitHubResponse {

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
     * Constructor to init a {@link RunnerLabel}
     *
     * @param id:   identifier of the label
     * @param name: the name of the label
     * @param type: type of the label
     **/
    public RunnerLabel(long id, String name, LabelType type) {
        super(null);
        this.id = id;
        this.name = name;
        this.type = type;
    }

    /**
     * Constructor to init a {@link RunnerLabel}
     *
     * @param jGitHubLabel : label details as {@link JSONObject}
     **/
    public RunnerLabel(JSONObject jGitHubLabel) {
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

    /**
     * {@code LabelType} list of available label types
     **/
    public enum LabelType {

        /**
         * {@code read_only} label type
         **/
        read_only("read-only"),

        /**
         * {@code custom} label type
         **/
        custom("custom");

        /**
         * {@code labelType} label type
         **/
        private final String labelType;

        /**
         * Constructor to init {@link LabelType}
         *
         * @param labelType: label type
         **/
        LabelType(String labelType) {
            this.labelType = labelType;
        }

        /**
         * Method to get {@link #labelType} instance <br>
         * Any params required
         *
         * @return {@link #labelType} instance as {@link String}
         **/
        @Override
        public String toString() {
            return labelType;
        }

    }

}
