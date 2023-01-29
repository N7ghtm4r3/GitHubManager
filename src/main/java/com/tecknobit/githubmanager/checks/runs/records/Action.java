package com.tecknobit.githubmanager.checks.runs.records;

import org.json.JSONObject;

/**
 * The {@code Action} class is useful to format a GitHub's action for a {@link CheckRun}
 *
 * @author N7ghtm4r3 - Tecknobit
 **/
public class Action {

    /**
     * {@code label} the text to be displayed on a button in the web UI. The maximum size is 20 characters
     **/
    private final String label;

    /**
     * {@code description} a short explanation of what this action would do. The maximum size is 40 characters
     **/
    private final String description;

    /**
     * {@code identifier} reference for the action on the integrator's system. The maximum size is 20 characters
     **/
    private final String identifier;

    /**
     * Constructor to init a {@link Action}
     *
     * @param label       : the text to be displayed on a button in the web UI. The maximum size is 20 characters
     * @param description : a short explanation of what this action would do. The maximum size is 40 characters
     * @param identifier  : reference for the action on the integrator's system. The maximum size is 20 characters
     **/
    public Action(String label, String description, String identifier) {
        this.label = label;
        this.description = description;
        this.identifier = identifier;
    }

    /**
     * Method to get {@link #label} instance <br>
     * No-any params required
     *
     * @return {@link #label} instance as {@link String}
     **/
    public String getLabel() {
        return label;
    }

    /**
     * Method to get {@link #description} instance <br>
     * No-any params required
     *
     * @return {@link #description} instance as {@link String}
     **/
    public String getDescription() {
        return description;
    }

    /**
     * Method to get {@link #identifier} instance <br>
     * No-any params required
     *
     * @return {@link #identifier} instance as {@link String}
     **/
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Returns a string representation of the object <br>
     * No-any params required
     *
     * @return a string representation of the object as {@link String}
     */
    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

}
