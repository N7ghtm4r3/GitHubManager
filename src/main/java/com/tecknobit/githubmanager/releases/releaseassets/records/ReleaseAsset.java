package com.tecknobit.githubmanager.releases.releaseassets.records;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.GitHubManager.ReturnFormat;
import com.tecknobit.githubmanager.records.parents.BaseItemStructure;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code ReleaseAsset} class is useful to format a GitHub's release asset
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/releases/assets#get-a-release-asset">
 *             Get a release asset</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/releases/assets#update-a-release-asset">
 *             Update a release asset</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/releases/assets#list-release-assets">
 *             List release assets</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/releases/assets#upload-a-release-asset">
 *             Upload a release asset</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see BaseItemStructure
 **/
public class ReleaseAsset extends BaseItemStructure {

    /**
     * {@code AssetState} list of available asset states
     **/
    public enum AssetState {

        /**
         * {@code uploaded} asset state
         **/
        uploaded,

        /**
         * {@code open} asset state
         **/
        open

    }

    /**
     * {@code browserDownloadUrl} browser download url of the release asset
     **/
    private final String browserDownloadUrl;

    /**
     * {@code name} of the release asset
     **/
    private final String name;

    /**
     * {@code label} of the release asset
     **/
    private final String label;

    /**
     * {@code state} of the release asset
     **/
    private final AssetState state;

    /**
     * {@code contentType} content type of the release asset
     **/
    private final String contentType;

    /**
     * {@code size} of the release asset
     **/
    private final double size;

    /**
     * {@code downloadCount} download count of the release asset
     **/
    private final int downloadCount;

    /**
     * {@code uploader} of the release asset
     **/
    private final User uploader;

    /**
     * Constructor to init a {@link ReleaseAsset}
     *
     * @param url                : the url of the release asset
     * @param id                 : the id of the release asset
     * @param nodeId             : the node id of the release asset
     * @param createdAt          : the creation time of the release asset
     * @param updatedAt          : the updated time of the release asset
     * @param browserDownloadUrl : browser download url of the release asset
     * @param name               :  name of the release asset
     * @param label              : label of the release asset
     * @param state              : state of the release asset
     * @param contentType        : content type of the release asset
     * @param size               : size of the release asset
     * @param downloadCount      : download count of the release asset
     * @param uploader           : uploader of the release asset
     **/
    public <T> ReleaseAsset(String url, T id, String nodeId, String createdAt, String updatedAt, String browserDownloadUrl,
                            String name, String label, AssetState state, String contentType, double size, int downloadCount,
                            User uploader) {
        super(url, id, nodeId, createdAt, updatedAt);
        this.browserDownloadUrl = browserDownloadUrl;
        this.name = name;
        this.label = label;
        this.state = state;
        this.contentType = contentType;
        this.size = size;
        this.downloadCount = downloadCount;
        this.uploader = uploader;
    }

    /**
     * Constructor to init a {@link ReleaseAsset}
     *
     * @param jReleaseAsset : release asset details as {@link JSONObject}
     **/
    public ReleaseAsset(JSONObject jReleaseAsset) {
        super(jReleaseAsset);
        browserDownloadUrl = hResponse.getString("browser_download_url");
        name = hResponse.getString("name");
        label = hResponse.getString("label");
        state = AssetState.valueOf(hResponse.getString("state"));
        contentType = hResponse.getString("content_type");
        size = hResponse.getDouble("size");
        downloadCount = hResponse.getInt("download_count");
        uploader = new User(hResponse.getJSONObject("uploader"));
    }

    /**
     * Method to get {@link #id} instance <br>
     * No-any params required
     *
     * @return {@link #id} instance as {@link String}
     **/
    @Override
    public Long getId() {
        return super.getId();
    }

    /**
     * Method to get {@link #browserDownloadUrl} instance <br>
     * No-any params required
     *
     * @return {@link #browserDownloadUrl} instance as {@link String}
     **/
    public String getBrowserDownloadUrl() {
        return browserDownloadUrl;
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
     * Method to get {@link #label} instance <br>
     * No-any params required
     *
     * @return {@link #label} instance as {@link String}
     **/
    public String getLabel() {
        return label;
    }

    /**
     * Method to get {@link #state} instance <br>
     * No-any params required
     *
     * @return {@link #state} instance as {@link AssetState}
     **/
    public AssetState getState() {
        return state;
    }

    /**
     * Method to get {@link #contentType} instance <br>
     * No-any params required
     *
     * @return {@link #contentType} instance as {@link String}
     **/
    public String getContentType() {
        return contentType;
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
     * Method to get {@link #downloadCount} instance <br>
     * No-any params required
     *
     * @return {@link #downloadCount} instance as int
     **/
    public int getDownloadCount() {
        return downloadCount;
    }

    /**
     * Method to get {@link #uploader} instance <br>
     * No-any params required
     *
     * @return {@link #uploader} instance as {@link User}
     **/
    public User getUploader() {
        return uploader;
    }

    /**
     * Method to create a release assets list
     *
     * @param releaseAssetsResponse : obtained from GitHub's response
     * @param format                :              return type formatter -> {@link ReturnFormat}
     * @return release assets list as {@code "format"} defines
     **/
    @Returner
    public static <T> T returnReleaseAssets(String releaseAssetsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(releaseAssetsResponse);
            case LIBRARY_OBJECT:
                return returnReleaseAssets(new JSONArray(releaseAssetsResponse));
            default:
                return (T) releaseAssetsResponse;
        }
    }

    /**
     * Method to create a release assets list
     *
     * @param jReleaseAssets : obtained from GitHub's response
     * @return release assets list as {@link ArrayList} of {@link ReleaseAsset}
     **/
    @Returner
    public static <T> T returnReleaseAssets(JSONArray jReleaseAssets) {
        ArrayList<ReleaseAsset> releaseAssets = new ArrayList<>();
        if (jReleaseAssets != null)
            for (int j = 0; j < jReleaseAssets.length(); j++)
                releaseAssets.add(new ReleaseAsset(jReleaseAssets.getJSONObject(j)));
        return (T) releaseAssets;
    }

}
