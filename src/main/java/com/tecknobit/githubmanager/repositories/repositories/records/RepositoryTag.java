package com.tecknobit.githubmanager.repositories.repositories.records;

import com.tecknobit.githubmanager.records.generic.ShaItem;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code RepositoryTag} class is useful to format a GitHub's repository tag
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repository-tags">
 * List repository tags</a>
 * @see GitHubResponse
 **/
public class RepositoryTag extends GitHubResponse {

    /**
     * {@code name} of the repository tag
     **/
    private final String name;

    /**
     * {@code commit} of the repository tag
     **/
    private final ShaItem commit;

    /**
     * {@code zipballUrl} zipball url of the repository tag
     **/
    private final String zipballUrl;

    /**
     * {@code tarballUrl} tarball url of the repository tag
     **/
    private final String tarballUrl;

    /**
     * {@code nodeId} node id of the repository tag
     **/
    private final String nodeId;

    /**
     * Constructor to init a {@link RepositoryTag}
     *
     * @param name       : name of the repository tag
     * @param commit     : commit of the repository tag
     * @param zipballUrl : zipball url of the repository tag
     * @param tarballUrl : tarball url of the repository tag
     * @param nodeId     : node id of the repository tag
     **/
    public RepositoryTag(String name, ShaItem commit, String zipballUrl, String tarballUrl, String nodeId) {
        super(null);
        this.name = name;
        this.commit = commit;
        this.zipballUrl = zipballUrl;
        this.tarballUrl = tarballUrl;
        this.nodeId = nodeId;
    }

    /**
     * Constructor to init a {@link RepositoryTag}
     *
     * @param jRepositoryTag : repository tag details as {@link JSONObject}
     **/
    public RepositoryTag(JSONObject jRepositoryTag) {
        super(jRepositoryTag);
        name = hResponse.getString("name");
        commit = new ShaItem(hResponse.getJSONObject("commit"));
        zipballUrl = hResponse.getString("zipball_url");
        tarballUrl = hResponse.getString("tarball_url");
        nodeId = hResponse.getString("node_id");
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
     * Method to get {@link #commit} instance <br>
     * No-any params required
     *
     * @return {@link #commit} instance as {@link ShaItem}
     **/
    public ShaItem getCommit() {
        return commit;
    }

    /**
     * Method to get {@link #zipballUrl} instance <br>
     * No-any params required
     *
     * @return {@link #zipballUrl} instance as {@link String}
     **/
    public String getZipballUrl() {
        return zipballUrl;
    }

    /**
     * Method to get {@link #tarballUrl} instance <br>
     * No-any params required
     *
     * @return {@link #tarballUrl} instance as {@link String}
     **/
    public String getTarballUrl() {
        return tarballUrl;
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
