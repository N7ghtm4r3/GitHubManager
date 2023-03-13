package com.tecknobit.githubmanager.gitdatabase.commits.records;

import com.tecknobit.githubmanager.commits.commits.records.Commit.Parent;
import com.tecknobit.githubmanager.records.generic.CommitData;
import com.tecknobit.githubmanager.records.generic.ShaItem;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The {@code GitCommit} class is useful to format a GitHub's git commit
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/git/commits#create-a-commit">
 *             Create a commit</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/git/commits#get-a-commit">
 *             Get a commit</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see CommitData
 **/
public class GitCommit extends CommitData {

    /**
     * {@code sha} for the commit
     **/
    private final String sha;

    /**
     * {@code nodeId} node id for the commit
     **/
    private final String nodeId;

    /**
     * {@code parents} list of {@link Parent}
     **/
    private final ArrayList<Parent> parents;

    /**
     * {@code htmlUrl} html url of the commit
     **/
    private final String htmlUrl;

    /**
     * Constructor to init a {@link GitCommit}
     *
     * @param author       : author of the commit
     * @param committer    : committer of the commit
     * @param message      : message of the commit
     * @param tree         : tree of the commit
     * @param url          : url of the commit
     * @param verification : verification of the commit
     * @param sha          : sha for the commit
     * @param nodeId       : node id for the commit
     * @param parents      : list of {@link Parent}
     * @param htmlUrl      : html url of the commit
     **/
    public GitCommit(CommitProfile author, CommitProfile committer, String message, ShaItem tree, String url,
                     Verification verification, String sha, String nodeId, ArrayList<Parent> parents, String htmlUrl) {
        super(author, committer, message, tree, url, verification);
        this.sha = sha;
        this.nodeId = nodeId;
        this.parents = parents;
        this.htmlUrl = htmlUrl;
    }

    /**
     * Constructor to init a {@link GitCommit}
     *
     * @param jGitCommit : git commit details as {@link JSONObject}
     **/
    public GitCommit(JSONObject jGitCommit) {
        super(jGitCommit);
        sha = hResponse.getString("sha");
        nodeId = hResponse.getString("node_id");
        parents = Parent.returnParentsList(hResponse.getJSONArray("parents"));
        htmlUrl = hResponse.getString("html_url");
    }

    /**
     * Method to get {@link #sha} instance <br>
     * No-any params required
     *
     * @return {@link #sha} instance as {@link String}
     **/
    public String getSha() {
        return sha;
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
     * Method to get {@link #parents} instance <br>
     * No-any params required
     *
     * @return {@link #parents} instance as {@link ArrayList} of {@link Parent}
     **/
    public ArrayList<Parent> getParents() {
        return parents;
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
