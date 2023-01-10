package com.tecknobit.githubmanager.codescanning.records;

import com.tecknobit.githubmanager.records.parents.BaseResponseDetails;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONObject;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;
import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code CodeQL} class is useful to format a GitHub's CodeQL
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/code-scanning#list-codeql-databases-for-a-repository">
 *             List CodeQL databases for a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/code-scanning#get-a-codeql-database-for-a-repository">
 *             Get a CodeQL database for a repository</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see BaseResponseDetails
 **/
public class CodeQL extends BaseResponseDetails {

    /**
     * {@code language} the language of the CodeQL database
     **/
    private final String language;

    /**
     * {@code uploader} user
     **/
    private final User uploader;

    /**
     * {@code contentType} the MIME type of the CodeQL database file
     **/
    private final String contentType;

    /**
     * {@code size} the size of the CodeQL database file in bytes
     **/
    private final double size;

    /**
     * {@code createdAt} the date and time at which the CodeQL database was created, in ISO 8601 format: "YYYY-MM-DDTHH:MM:SSZ"
     **/
    private final String createdAt;

    /**
     * {@code updatedAt} the date and time at which the CodeQL database was last updated, in ISO 8601 format: "YYYY-MM-DDTHH:MM:SSZ"
     **/
    private final String updatedAt;

    /**
     * Constructor to init a {@link CodeQL}
     *
     * @param id          : the ID of the CodeQL database
     * @param name        : the name of the CodeQL database
     * @param url         : the URL at which to download the CodeQL database
     * @param language    : the language of the CodeQL database
     * @param uploader    : uploader user
     * @param contentType : the MIME type of the CodeQL database file
     * @param size:       the size of the CodeQL database file in bytes
     * @param createdAt   : the date and time at which the CodeQL database was created, in ISO 8601 format: "YYYY-MM-DDTHH:MM:SSZ"
     * @param updatedAt   :  the date and time at which the CodeQL database was last updated, in ISO 8601 format: "YYYY-MM-DDTHH:MM:SSZ"
     **/
    public CodeQL(long id, String name, String url, String language, User uploader, String contentType, double size,
                  String createdAt, String updatedAt) {
        super(id, name, url);
        this.language = language;
        this.uploader = uploader;
        this.contentType = contentType;
        this.size = size;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Constructor to init a {@link CodeQL}
     *
     * @param jCodeQL : CodeQL details as {@link JSONObject}
     **/
    public CodeQL(JSONObject jCodeQL) {
        super(jCodeQL);
        language = hResponse.getString("language");
        uploader = new User(hResponse.getJSONObject("uploader", new JSONObject()));
        contentType = hResponse.getString("content_type");
        size = hResponse.getDouble("size", 0);
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
    }

    /**
     * Method to get {@link #language} instance <br>
     * Any params required
     *
     * @return {@link #language} instance as {@link String}
     **/
    public String getLanguage() {
        return language;
    }

    /**
     * Method to get {@link #uploader} instance <br>
     * Any params required
     *
     * @return {@link #uploader} instance as {@link User}
     **/
    public User getUploader() {
        return uploader;
    }

    /**
     * Method to get {@link #contentType} instance <br>
     * Any params required
     *
     * @return {@link #contentType} instance as {@link String}
     **/
    public String getContentType() {
        return contentType;
    }

    /**
     * Method to get {@link #size} instance <br>
     * Any params required
     *
     * @return {@link #size} instance as double
     **/
    public double getSize() {
        return size;
    }

    /**
     * Method to get {@link #size} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #size} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getSize(int decimals) {
        return roundValue(size, decimals);
    }

    /**
     * Method to get {@link #createdAt} instance <br>
     * Any params required
     *
     * @return {@link #createdAt} instance as {@link String}
     **/
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Method to get {@link #createdAt} timestamp <br>
     * Any params required
     *
     * @return {@link #createdAt} timestamp as long
     **/
    public long getCreatedAtTimestamp() {
        return getDateTimestamp(createdAt);
    }

    /**
     * Method to get {@link #updatedAt} instance <br>
     * Any params required
     *
     * @return {@link #updatedAt} instance as {@link String}
     **/
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Method to get {@link #updatedAt} timestamp <br>
     * Any params required
     *
     * @return {@link #updatedAt} timestamp as long
     **/
    public long getUpdatedAtTimestamp() {
        return getDateTimestamp(updatedAt);
    }

}
