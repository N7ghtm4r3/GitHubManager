package com.tecknobit.githubmanager.migrations.sourceimports.records;

import com.tecknobit.githubmanager.records.parents.BaseResponseDetails;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code CommitAuthor} class is useful to format a GitHub's commit author
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/migrations/source-imports#get-commit-authors">
 *             Get commit authors</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/migrations/source-imports#map-a-commit-author">
 *             Map a commit author</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see BaseResponseDetails
 **/
public class CommitAuthor extends BaseResponseDetails {

    /**
     * {@code remoteId} remote identifier of the commit author
     **/
    private final String remoteId;

    /**
     * {@code remoteName} remote name of the commit author
     **/
    private final String remoteName;

    /**
     * {@code email} of the commit author
     **/
    private final String email;

    /**
     * {@code importUrl} import url of the commit author
     **/
    private final String importUrl;

    /**
     * Constructor to init a {@link CommitAuthor}
     *
     * @param id         : identifier value
     * @param name       : the name of the item
     * @param url        : url value
     * @param remoteId   : remote identifier of the commit author
     * @param remoteName : remote name of the commit author
     * @param email      : email of the commit author
     * @param importUrl  : import url of the commit author
     **/
    public CommitAuthor(long id, String name, String url, String remoteId, String remoteName, String email,
                        String importUrl) {
        super(id, name, url);
        this.remoteId = remoteId;
        this.remoteName = remoteName;
        this.email = email;
        this.importUrl = importUrl;
    }

    /**
     * Constructor to init a {@link CommitAuthor}
     *
     * @param jCommitAuthor : commit author details as {@link JSONObject}
     **/
    public CommitAuthor(JSONObject jCommitAuthor) {
        super(jCommitAuthor);
        remoteId = hResponse.getString("remote_id");
        remoteName = hResponse.getString("remote_name");
        email = hResponse.getString("email");
        importUrl = hResponse.getString("import_url");
    }

    /**
     * Method to get {@link #remoteId} instance <br>
     * No-any params required
     *
     * @return {@link #remoteId} instance as {@link String}
     **/
    public String getRemoteId() {
        return remoteId;
    }

    /**
     * Method to get {@link #remoteName} instance <br>
     * No-any params required
     *
     * @return {@link #remoteName} instance as {@link String}
     **/
    public String getRemoteName() {
        return remoteName;
    }

    /**
     * Method to get {@link #email} instance <br>
     * No-any params required
     *
     * @return {@link #email} instance as {@link String}
     **/
    public String getEmail() {
        return email;
    }

    /**
     * Method to get {@link #importUrl} instance <br>
     * No-any params required
     *
     * @return {@link #importUrl} instance as {@link String}
     **/
    public String getImportUrl() {
        return importUrl;
    }

}
