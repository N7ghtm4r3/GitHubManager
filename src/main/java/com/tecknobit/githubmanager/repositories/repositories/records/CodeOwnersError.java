package com.tecknobit.githubmanager.repositories.repositories.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code CodeOwnersError} class is useful to format a GitHub's code owners error
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-codeowners-errors">
 * List CODEOWNERS errors</a>
 * @see GitHubResponse
 **/
public class CodeOwnersError extends GitHubResponse {

    /**
     * {@code line} the line number where these errors occur
     **/
    private final int line;

    /**
     * {@code column} the column number where these errors occur
     **/
    private final int column;

    /**
     * {@code source} the contents of the line where the error occurs
     **/
    private final String source;

    /**
     * {@code kind} the type of error
     **/
    private final String kind;

    /**
     * {@code suggestion} suggested action to fix the error
     **/
    private final String suggestion;

    /**
     * {@code path} the path of the file where the error occurred
     **/
    private final String path;

    /**
     * Constructor to init a {@link CodeOwnersError}
     *
     * @param line       : the line number where these errors occur
     * @param column     : the column number where these errors occur
     * @param source     : the contents of the line where the error occurs
     * @param kind       : the type of error
     * @param suggestion : suggested action to fix the error
     * @param path       : the path of the file where the error occurred
     **/
    public CodeOwnersError(int line, int column, String source, String kind, String suggestion, String path) {
        super(null);
        this.line = line;
        this.column = column;
        this.source = source;
        this.kind = kind;
        this.suggestion = suggestion;
        this.path = path;
    }

    /**
     * Constructor to init a {@link CodeOwnersError}
     *
     * @param jCodeOwnersError : code owners error details as {@link JSONObject}
     **/
    public CodeOwnersError(JSONObject jCodeOwnersError) {
        super(jCodeOwnersError);
        line = hResponse.getInt("line", 0);
        column = hResponse.getInt("column", 0);
        source = hResponse.getString("source");
        kind = hResponse.getString("kind");
        suggestion = hResponse.getString("suggestion");
        path = hResponse.getString("path");
    }

    /**
     * Method to get {@link #line} instance <br>
     * No-any params required
     *
     * @return {@link #line} instance as int
     **/
    public int getLine() {
        return line;
    }

    /**
     * Method to get {@link #column} instance <br>
     * No-any params required
     *
     * @return {@link #column} instance as int
     **/
    public int getColumn() {
        return column;
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

    /**
     * Method to get {@link #kind} instance <br>
     * No-any params required
     *
     * @return {@link #kind} instance as {@link String}
     **/
    public String getKind() {
        return kind;
    }

    /**
     * Method to get {@link #suggestion} instance <br>
     * No-any params required
     *
     * @return {@link #suggestion} instance as {@link String}
     **/
    public String getSuggestion() {
        return suggestion;
    }

    /**
     * Method to get {@link #path} instance <br>
     * No-any params required
     *
     * @return {@link #path} instance as {@link String}
     **/
    public String getPath() {
        return path;
    }

}
