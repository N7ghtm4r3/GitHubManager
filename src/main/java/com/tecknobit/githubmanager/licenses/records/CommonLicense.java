package com.tecknobit.githubmanager.licenses.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code CommonLicense} class is useful to format a GitHub's common license
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/licenses#get-all-commonly-used-licenses">
 *             Get all commonly used licenses</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/licenses#get-a-license">
 *             Get a license</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class CommonLicense extends GitHubResponse {

    /**
     * {@code "key"} value
     **/
    protected final String key;

    /**
     * {@code "name"} value
     **/
    protected final String name;

    /**
     * {@code "url"} value
     **/
    protected final String url;

    /**
     * {@code "spdxId"} value
     **/
    protected final String spdxId;

    /**
     * {@code "nodeId"} value
     **/
    protected final String nodeId;

    /**
     * {@code "htmlUrl"} value
     **/
    protected final String htmlUrl;

    /**
     * Constructor to init a {@link CommonLicense}
     *
     * @param key:     key value
     * @param name:    name value
     * @param url:     url value
     * @param spdxId:  spdx identifier value
     * @param nodeId:  identifier of the node value
     * @param htmlUrl: html url value
     **/
    public CommonLicense(String key, String name, String url, String spdxId, String nodeId, String htmlUrl) {
        super(null);
        this.key = key;
        this.name = name;
        this.url = url;
        this.spdxId = spdxId;
        this.nodeId = nodeId;
        this.htmlUrl = htmlUrl;
    }

    /**
     * Constructor to init a {@link CommonLicense}
     *
     * @param jCommonLicense: common license details as {@link JSONObject}
     **/
    public CommonLicense(JSONObject jCommonLicense) {
        super(jCommonLicense);
        key = hResponse.getString("key");
        name = hResponse.getString("name");
        url = hResponse.getString("url");
        spdxId = hResponse.getString("spdx_id");
        nodeId = hResponse.getString("node_id");
        htmlUrl = hResponse.getString("html_url");
    }

    /**
     * Method to get {@link #key} instance <br>
     * No-any params required
     *
     * @return {@link #key} instance as {@link String}
     **/
    public String getKey() {
        return key;
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
     * Method to get {@link #url} instance <br>
     * No-any params required
     *
     * @return {@link #url} instance as {@link String}
     **/
    public String getUrl() {
        return url;
    }

    /**
     * Method to get {@link #spdxId} instance <br>
     * No-any params required
     *
     * @return {@link #spdxId} instance as {@link String}
     **/
    public String getSpdxId() {
        return spdxId;
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

    /**
     * Method to get {@link #htmlUrl} instance <br>
     * No-any params required
     *
     * @return {@link #htmlUrl} instance as {@link String}
     **/
    public String getHtmlUrl() {
        return htmlUrl;
    }

}