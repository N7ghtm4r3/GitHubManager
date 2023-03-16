package com.tecknobit.githubmanager.repositories.contents.records;

import com.tecknobit.githubmanager.gitdatabase.commits.records.GitCommit;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code FileContents} class is useful to format a GitHub's file contents
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/repos/contents#create-or-update-file-contents">
 *             Create or update file contents</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/repos/contents#delete-a-file">
 *             Delete a file</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class FileContents extends GitHubResponse {

    /**
     * {@code content} of the file
     **/
    private final ContentFile content;

    /**
     * {@code commit} of the file
     **/
    private final GitCommit commit;

    /**
     * Constructor to init a {@link FileContents}
     *
     * @param content : content of the file
     * @param commit  : commit of the file
     **/
    public FileContents(ContentFile content, GitCommit commit) {
        super(null);
        this.content = content;
        this.commit = commit;
    }

    /**
     * Constructor to init a {@link FileContents}
     *
     * @param jFileContents : file contents details as {@link JSONObject}
     **/
    public FileContents(JSONObject jFileContents) {
        super(jFileContents);
        content = new ContentFile(hResponse.getJSONObject("content"));
        commit = new GitCommit(hResponse.getJSONObject("commit"));
    }

    /**
     * Method to get {@link #content} instance <br>
     * No-any params required
     *
     * @return {@link #content} instance as {@link ContentFile}
     **/
    public ContentFile getContent() {
        return content;
    }

    /**
     * Method to get {@link #commit} instance <br>
     * No-any params required
     *
     * @return {@link #commit} instance as {@link GitCommit}
     **/
    public GitCommit getCommit() {
        return commit;
    }

}
