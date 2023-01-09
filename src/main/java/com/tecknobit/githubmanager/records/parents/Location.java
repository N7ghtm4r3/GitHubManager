package com.tecknobit.githubmanager.records.parents;

import org.json.JSONObject;

/**
 * The {@code Location} class is useful to format a GitHub's location
 *
 * @author N7ghtm4r3 - Tecknobit
 * @see GitHubResponse
 **/
public class Location extends GitHubResponse {

    /**
     * {@code path} of the annotation
     **/
    protected final String path;

    /**
     * {@code startLine} start line of the annotation
     **/
    protected final int startLine;

    /**
     * {@code endLine} end line of the annotation
     **/
    protected final int endLine;

    /**
     * {@code startColumn} start column of the annotation
     **/
    protected final int startColumn;

    /**
     * {@code endColumn} end column of the annotation
     **/
    protected final int endColumn;

    /**
     * Constructor to init a {@link Location}
     *
     * @param path        : path of the annotation
     * @param startLine   : start line of the annotation
     * @param endLine     : end line of the annotation
     * @param startColumn : start column of the annotation
     * @param endColumn   : end column of the annotation
     **/
    public Location(String path, int startLine, int endLine, int startColumn, int endColumn) {
        super(null);
        this.path = path;
        this.startLine = startLine;
        this.endLine = endLine;
        this.startColumn = startColumn;
        this.endColumn = endColumn;
    }

    /**
     * Constructor to init a {@link Location}
     *
     * @param jLocation : location details as {@link JSONObject}
     **/
    public Location(JSONObject jLocation) {
        super(jLocation);
        path = hResponse.getString("path");
        startLine = hResponse.getInt("start_line", 0);
        endLine = hResponse.getInt("end_line", 0);
        startColumn = hResponse.getInt("start_column", 0);
        endColumn = hResponse.getInt("end_column", 0);
    }

    /**
     * Method to get {@link #path} instance <br>
     * Any params required
     *
     * @return {@link #path} instance as {@link String}
     **/
    public String getPath() {
        return path;
    }

    /**
     * Method to get {@link #startLine} instance <br>
     * Any params required
     *
     * @return {@link #startLine} instance as int
     **/
    public int getStartLine() {
        return startLine;
    }

    /**
     * Method to get {@link #endLine} instance <br>
     * Any params required
     *
     * @return {@link #endLine} instance as int
     **/
    public int getEndLine() {
        return endLine;
    }

    /**
     * Method to get {@link #startColumn} instance <br>
     * Any params required
     *
     * @return {@link #startColumn} instance as int
     **/
    public int getStartColumn() {
        return startColumn;
    }

    /**
     * Method to get {@link #endColumn} instance <br>
     * Any params required
     *
     * @return {@link #endColumn} instance as int
     **/
    public int getEndColumn() {
        return endColumn;
    }

}
