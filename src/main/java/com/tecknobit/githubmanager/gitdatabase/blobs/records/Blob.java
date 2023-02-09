package com.tecknobit.githubmanager.gitdatabase.blobs.records;

import com.tecknobit.githubmanager.records.generic.ShaItem;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code Blob} class is useful to format a GitHub's blob
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/git/blobs#get-a-blob">
 * Get a blob</a>
 * @see GitHubResponse
 * @see ShaItem
 **/
public class Blob extends ShaItem {

    /**
     * {@code Encoding} list of available encoding for a blob
     **/
    public enum Encoding {

        /**
         * {@code utf_8} encoding for a blob
         **/
        utf_8("utf-8"),

        /**
         * {@code base64} encoding for a blob
         **/
        base64("base64");

        /**
         * {@code encoding} type
         **/
        private final String encoding;

        /**
         * Constructor to init a {@link Encoding}
         *
         * @param encoding: encoding type
         **/
        Encoding(String encoding) {
            this.encoding = encoding;
        }

        /**
         * Method to get {@link #encoding} instance <br>
         * No-any params required
         *
         * @return {@link #encoding} instance as {@link String}
         **/
        @Override
        public String toString() {
            return encoding;
        }

    }

    /**
     * {@code content} of the blob
     **/
    private final String content;

    /**
     * {@code encoding} of the blob
     **/
    private final Encoding encoding;

    /**
     * {@code size} of the blob
     **/
    private final double size;

    /**
     * {@code nodeId} the node id of the blob
     **/
    private final String nodeId;

    /**
     * Constructor to init a {@link Blob}
     *
     * @param sha      : sha of the blob
     * @param url      : url of the blob
     * @param content  : content of the blob
     * @param encoding :encoding of the blob
     * @param size     : size of the blob
     * @param nodeId   : the node id of the blob
     **/
    public Blob(String sha, String url, String content, Encoding encoding, double size, String nodeId) {
        super(sha, url);
        this.content = content;
        this.encoding = encoding;
        this.size = size;
        this.nodeId = nodeId;
    }

    /**
     * Constructor to init a {@link Blob}
     *
     * @param jBlob : blob details as {@link JSONObject}
     **/
    public Blob(JSONObject jBlob) {
        super(jBlob);
        content = hResponse.getString("content");
        encoding = Encoding.valueOf(hResponse.getString("encoding", Encoding.utf_8.name()));
        size = hResponse.getDouble("size", 0);
        nodeId = hResponse.getString("node_id");
    }

    /**
     * Method to get {@link #content} instance <br>
     * No-any params required
     *
     * @return {@link #content} instance as {@link String}
     **/
    public String getContent() {
        return content;
    }

    /**
     * Method to get {@link #encoding} instance <br>
     * No-any params required
     *
     * @return {@link #encoding} instance as {@link Encoding}
     **/
    public Encoding getEncoding() {
        return encoding;
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

    /**
     * Method to get {@link #nodeId} instance <br>
     * No-any params required
     *
     * @return {@link #nodeId} instance as {@link String}
     **/
    public String getNodeId() {
        return nodeId;
    }

}
