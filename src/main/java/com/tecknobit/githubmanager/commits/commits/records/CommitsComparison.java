package com.tecknobit.githubmanager.commits.commits.records;

import com.tecknobit.githubmanager.commits.commits.records.Commit.CommitFile;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.githubmanager.commits.commits.records.Commit.CommitFile.returnFiles;

/**
 * The {@code CommitsComparison} class is useful to format a GitHub's commits comparison
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/commits/commits#compare-two-commits">
 * Compare two commits</a>
 * @see GitHubResponse
 **/
public class CommitsComparison extends GitHubResponse {

    /**
     * {@code url} of the comparison
     **/
    private final String url;

    /**
     * {@code htmlUrl} html url of the comparison
     **/
    private final String htmlUrl;

    /**
     * {@code permanentLinkUrl} permanent link url of the comparison
     **/
    private final String permanentLinkUrl;

    /**
     * {@code diffUrl} diff url of the comparison
     **/
    private final String diffUrl;

    /**
     * {@code patchUrl} patch url of the comparison
     **/
    private final String patchUrl;

    /**
     * {@code baseCommit} base commit of the comparison
     **/
    private final Commit baseCommit;

    /**
     * {@code mergeBaseCommit} merge base commit of the comparison
     **/
    private final Commit mergeBaseCommit;

    /**
     * {@code status} of the comparison
     **/
    private final CommitStatus status;

    /**
     * {@code aheadBy} ahead by of the comparison
     **/
    private final int aheadBy;

    /**
     * {@code behindBy} behind by of the comparison
     **/
    private final int behindBy;

    /**
     * {@code totalCommits} total commits of the comparison
     **/
    private final int totalCommits;

    /**
     * {@code commits} list of {@link Commit}
     **/
    private final ArrayList<Commit> commits;

    /**
     * {@code files} list of {@link CommitFile}
     **/
    private final ArrayList<CommitFile> files;

    /**
     * Constructor to init a {@link CommitsComparison}
     *
     * @param url              : url of the comparison
     * @param htmlUrl          : html url of the comparison
     * @param permanentLinkUrl : permanent link url of the comparison
     * @param diffUrl          : diff url of the comparison
     * @param patchUrl         : patch url of the comparison
     * @param baseCommit       : base commit of the comparison
     * @param mergeBaseCommit  : merge base commit of the comparison
     * @param status           : status of the comparison
     * @param aheadBy          : ahead by of the comparison
     * @param behindBy         :  behind by of the comparison
     * @param totalCommits     : total commits of the comparison
     * @param commits          : list of {@link Commit}
     * @param files            : list of {@link CommitFile}
     **/
    public CommitsComparison(String url, String htmlUrl, String permanentLinkUrl, String diffUrl, String patchUrl,
                             Commit baseCommit, Commit mergeBaseCommit, CommitStatus status, int aheadBy, int behindBy,
                             int totalCommits, ArrayList<Commit> commits, ArrayList<CommitFile> files) {
        super(null);
        this.url = url;
        this.htmlUrl = htmlUrl;
        this.permanentLinkUrl = permanentLinkUrl;
        this.diffUrl = diffUrl;
        this.patchUrl = patchUrl;
        this.baseCommit = baseCommit;
        this.mergeBaseCommit = mergeBaseCommit;
        this.status = status;
        this.aheadBy = aheadBy;
        this.behindBy = behindBy;
        this.totalCommits = totalCommits;
        this.commits = commits;
        this.files = files;
    }

    /**
     * Constructor to init a {@link CommitsComparison}
     *
     * @param jCommitsComparison : comparison details as {@link JSONObject}
     **/
    public CommitsComparison(JSONObject jCommitsComparison) {
        super(jCommitsComparison);
        url = hResponse.getString("url");
        htmlUrl = hResponse.getString("html_url");
        permanentLinkUrl = hResponse.getString("permalink_url");
        diffUrl = hResponse.getString("diff_url");
        patchUrl = hResponse.getString("patch_url");
        baseCommit = new Commit(hResponse.getJSONObject("base_commit", new JSONObject()));
        mergeBaseCommit = new Commit(hResponse.getJSONObject("merge_base_commit", new JSONObject()));
        status = CommitStatus.valueOf(hResponse.getString("status"));
        aheadBy = hResponse.getInt("ahead_by", 0);
        behindBy = hResponse.getInt("behind_by", 0);
        totalCommits = hResponse.getInt("total_commits", 0);
        commits = new ArrayList<>();
        JSONArray jCommits = hResponse.getJSONArray("commits", new JSONArray());
        for (int j = 0; j < jCommits.length(); j++)
            commits.add(new Commit(jCommits.getJSONObject(j)));
        files = returnFiles(hResponse.getJSONArray("files"));
    }

    /**
     * Method to get {@link #url} instance <br>
     * Any params required
     *
     * @return {@link #url} instance as {@link String}
     **/
    public String getUrl() {
        return url;
    }

    /**
     * Method to get {@link #htmlUrl} instance <br>
     * Any params required
     *
     * @return {@link #htmlUrl} instance as {@link String}
     **/
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * Method to get {@link #permanentLinkUrl} instance <br>
     * Any params required
     *
     * @return {@link #permanentLinkUrl} instance as {@link String}
     **/
    public String getPermanentLinkUrl() {
        return permanentLinkUrl;
    }

    /**
     * Method to get {@link #diffUrl} instance <br>
     * Any params required
     *
     * @return {@link #diffUrl} instance as {@link String}
     **/
    public String getDiffUrl() {
        return diffUrl;
    }

    /**
     * Method to get {@link #patchUrl} instance <br>
     * Any params required
     *
     * @return {@link #patchUrl} instance as {@link String}
     **/
    public String getPatchUrl() {
        return patchUrl;
    }

    /**
     * Method to get {@link #baseCommit} instance <br>
     * Any params required
     *
     * @return {@link #baseCommit} instance as {@link Commit}
     **/
    public Commit getBaseCommit() {
        return baseCommit;
    }

    /**
     * Method to get {@link #mergeBaseCommit} instance <br>
     * Any params required
     *
     * @return {@link #mergeBaseCommit} instance as {@link String}
     **/
    public Commit getMergeBaseCommit() {
        return mergeBaseCommit;
    }

    /**
     * Method to get {@link #status} instance <br>
     * Any params required
     *
     * @return {@link #status} instance as {@link CommitStatus}
     **/
    public CommitStatus getStatus() {
        return status;
    }

    /**
     * Method to get {@link #aheadBy} instance <br>
     * Any params required
     *
     * @return {@link #aheadBy} instance as int
     **/
    public int getAheadBy() {
        return aheadBy;
    }

    /**
     * Method to get {@link #behindBy} instance <br>
     * Any params required
     *
     * @return {@link #behindBy} instance as int
     **/
    public int getBehindBy() {
        return behindBy;
    }

    /**
     * Method to get {@link #totalCommits} instance <br>
     * Any params required
     *
     * @return {@link #totalCommits} instance as int
     **/
    public int getTotalCommits() {
        return totalCommits;
    }

    /**
     * Method to get {@link #commits} instance <br>
     * Any params required
     *
     * @return {@link #commits} instance as {@link Collection} of {@link Commit}
     **/
    public Collection<Commit> getCommits() {
        return commits;
    }

    /**
     * Method to get {@link #files} instance <br>
     * Any params required
     *
     * @return {@link #files} instance as {@link Collection} of {@link CommitFile}
     **/
    public Collection<CommitFile> getFiles() {
        return files;
    }

    /**
     * {@code CommitStatus} list of available of commit statuses
     **/
    public enum CommitStatus {

        /**
         * {@code diverged} commit status
         **/
        diverged,

        /**
         * {@code ahead} commit status
         **/
        ahead,

        /**
         * {@code behind} commit status
         **/
        behind,

        /**
         * {@code identical} commit status
         **/
        identical

    }

}
