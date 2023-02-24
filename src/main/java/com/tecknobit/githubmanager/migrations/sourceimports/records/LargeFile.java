package com.tecknobit.githubmanager.migrations.sourceimports.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code LargeFile} class is useful to format a GitHub's large file
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/migrations/source-imports#get-large-files">
 * Get large files</a>
 * @see GitHubResponse
 **/
public class LargeFile extends GitHubResponse {

    /**
     * {@code refName} ref name of the large file
     **/
    private final String refName;

    /**
     * {@code path} of the large file
     **/
    private final String path;

    /**
     * {@code oid} of the large file
     **/
    private final String oid;

    /**
     * {@code size} of the large file
     **/
    private final double size;

    /**
     * Constructor to init a {@link LargeFile}
     *
     * @param refName : ref name of the large file
     * @param path    :path of the large file
     * @param oid     : oid of the large file
     * @param size    :size of the large file
     **/
    public LargeFile(String refName, String path, String oid, double size) {
        super(null);
        this.refName = refName;
        this.path = path;
        this.oid = oid;
        this.size = size;
    }

    /**
     * Constructor to init a {@link LargeFile}
     *
     * @param jLargeFile : large file details as {@link JSONObject}
     **/
    public LargeFile(JSONObject jLargeFile) {
        super(jLargeFile);
        refName = hResponse.getString("ref_name");
        path = hResponse.getString("path");
        oid = hResponse.getString("oid");
        size = hResponse.getDouble("size", 0);
    }

    /**
     * Method to get {@link #refName} instance <br>
     * No-any params required
     *
     * @return {@link #refName} instance as {@link String}
     **/
    public String getRefName() {
        return refName;
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
     * Method to get {@link #oid} instance <br>
     * No-any params required
     *
     * @return {@link #oid} instance as {@link String}
     **/
    public String getOid() {
        return oid;
    }

    /**
     * Method to get {@link #size} instance <br>
     * No-any params required
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

}
