package com.tecknobit.githubmanager.search.records;

import com.tecknobit.githubmanager.records.generic.ShaItem;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;
import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code Code} class is useful to format a GitHub's code
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-code">
 * Search code</a>
 * @see GitHubResponse
 * @see ShaItem
 **/
public class Code extends ShaItem {

    /**
     * {@code name} of the code
     **/
    private final String name;

    /**
     * {@code path} of the code
     **/
    private final String path;

    /**
     * {@code gitUrl} git url of the code
     **/
    private final String gitUrl;

    /**
     * {@code htmlUrl} html url of the code
     **/
    private final String htmlUrl;

    /**
     * {@code repository} of the code
     **/
    private final Repository repository;

    /**
     * {@code fileSize} file size of the code
     **/
    private final double fileSize;

    /**
     * {@code language} of the code
     **/
    private final String language;

    /**
     * {@code lastModifiedAt} last modify date of the code
     **/
    private final String lastModifiedAt;

    /**
     * {@code lineNumbers} line numbers of the code
     **/
    private final ArrayList<Integer> lineNumbers;

    /**
     * Constructor to init a {@link Code}
     *
     * @param name : name of the code
     * @param path : path of the code
     * @param gitUrl : git url of the code
     * @param htmlUrl : html url of the code
     * @param repository : repository of the code
     * @param fileSize : file size of the code
     * @param language : language of the code
     * @param lastModifiedAt : last modify date of the code
     * @param lineNumbers : line numbers of the code
     **/
    public Code(String name, String path, String gitUrl, String htmlUrl, Repository repository, double fileSize,
                String language, String lastModifiedAt, ArrayList<Integer> lineNumbers) {
        super(null);
        this.name = name;
        this.path = path;
        this.gitUrl = gitUrl;
        this.htmlUrl = htmlUrl;
        this.repository = repository;
        this.fileSize = fileSize;
        this.language = language;
        this.lastModifiedAt = lastModifiedAt;
        this.lineNumbers = lineNumbers;
    }

    /**
     * Constructor to init a {@link Code}
     *
     * @param jCode : code details as {@link JSONObject}
     **/
    public Code(JSONObject jCode) {
        super(jCode);
        name = hResponse.getString("name");
        path = hResponse.getString("path");
        gitUrl = hResponse.getString("git_url");
        htmlUrl = hResponse.getString("html_url");
        repository = new Repository(hResponse.getJSONObject("repository"));
        fileSize = hResponse.getDouble("file_size", 0);
        language = hResponse.getString("language");
        lastModifiedAt = hResponse.getString("last_modified_at");
        lineNumbers = returnIntegersList(hResponse.getJSONArray("line_numbers"));
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
     * Method to get {@link #path} instance <br>
     * No-any params required
     *
     * @return {@link #path} instance as {@link String}
     **/
    public String getPath() {
        return path;
    }

    /**
     * Method to get {@link #gitUrl} instance <br>
     * No-any params required
     *
     * @return {@link #gitUrl} instance as {@link String}
     **/
    public String getGitUrl() {
        return gitUrl;
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

    /**
     * Method to get {@link #repository} instance <br>
     * No-any params required
     *
     * @return {@link #repository} instance as {@link Repository}
     **/
    public Repository getRepository() {
        return repository;
    }

    /**
     * Method to get {@link #fileSize} instance <br>
     * No-any params required
     *
     * @return {@link #fileSize} instance as int
     **/
    public double getFileSize() {
        return fileSize;
    }

    /**
     * Method to get {@link #fileSize} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #fileSize} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getFileSize(int decimals) {
        return roundValue(fileSize, decimals);
    }

    /**
     * Method to get {@link #language} instance <br>
     * No-any params required
     *
     * @return {@link #language} instance as {@link String}
     **/
    public String getLanguage() {
        return language;
    }

    /**
     * Method to get {@link #lastModifiedAt} instance <br>
     * No-any params required
     *
     * @return {@link #lastModifiedAt} instance as {@link String}
     **/
    public String getLastModifiedAt() {
        return lastModifiedAt;
    }

    /**
     * Method to get {@link #lastModifiedAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #lastModifiedAt} timestamp as long
     **/
    public long getLastModifiedAtTimestamp() {
        return getDateTimestamp(lastModifiedAt);
    }

    /**
     * Method to get {@link #lineNumbers} instance <br>
     * No-any params required
     *
     * @return {@link #lineNumbers} instance as {@link ArrayList} of {@link Integer}
     **/
    public ArrayList<Integer> getLineNumbers() {
        return lineNumbers;
    }

}
