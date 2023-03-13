package com.tecknobit.githubmanager.commits.commits.records;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.records.generic.CommitData;
import com.tecknobit.githubmanager.records.generic.ShaItem;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat;
import static com.tecknobit.githubmanager.commits.commits.records.Commit.CommitFile.returnFiles;
import static com.tecknobit.githubmanager.commits.commits.records.Commit.Parent.returnParentsList;

/**
 * The {@code Commit} class is useful to format a GitHub's commit
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/branches/branches#merge-a-branch">
 *             Sync a fork branch with the upstream repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/commits/commits#list-commits">
 *             List commits</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/commits/commits#get-a-commit">
 *             Get a commit</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/pulls/pulls#list-commits-on-a-pull-request">
 *             List commits on a pull request</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class Commit extends GitHubResponse {

    /**
     * {@code sha} of the branch commit
     **/
    private final String sha;

    /**
     * {@code nodeId} node identifier of the branch commit
     **/
    private final String nodeId;

    /**
     * {@code commit} details of the branch commit
     **/
    private final CommitDetails commit;

    /**
     * {@code url} of the branch commit
     **/
    private final String url;

    /**
     * {@code htmlUrl} html url of the branch commit
     **/
    private final String htmlUrl;

    /**
     * {@code commentsUrl} comments url of the branch commit
     **/
    private final String commentsUrl;

    /**
     * {@code author} of the branch commit
     **/
    private final User author;

    /**
     * {@code committer} of the branch commit
     **/
    private final User committer;

    /**
     * {@code parents} of the branch commit
     **/
    private final ArrayList<Parent> parents;

    /**
     * {@code stats} of the branch commit
     **/
    private final Stats stats;

    /**
     * {@code files} of the branch commit
     **/
    private final ArrayList<CommitFile> files;

    /**
     * Constructor to init a {@link Commit}
     *
     * @param sha         : sha of the branch commit
     * @param nodeId      : node identifier of the branch commit
     * @param commit      : commit of the branch commit
     * @param url         : url of the branch commit
     * @param htmlUrl     : html url of the branch commit
     * @param commentsUrl : comments url of the branch commit
     * @param author      : author of the branch commit
     * @param committer   : committer of the branch commit
     * @param parents     : parents of the branch commit
     **/
    public Commit(String sha, String nodeId, CommitDetails commit, String url, String htmlUrl, String commentsUrl,
                  User author, User committer, ArrayList<Parent> parents) {
        this(sha, nodeId, commit, url, htmlUrl, commentsUrl, author, committer, parents, null, null);
    }

    /**
     * Constructor to init a {@link Commit}
     *
     * @param sha         : sha of the branch commit
     * @param nodeId      : node identifier of the branch commit
     * @param commit      : commit of the branch commit
     * @param url         : url of the branch commit
     * @param htmlUrl     : html url of the branch commit
     * @param commentsUrl : comments url of the branch commit
     * @param author      : author of the branch commit
     * @param committer   : committer of the branch commit
     * @param parents     : parents of the branch commit
     * @param stats       : stats of the branch commit
     * @param files       : files of the branch commit
     **/
    public Commit(String sha, String nodeId, CommitDetails commit, String url, String htmlUrl, String commentsUrl,
                  User author, User committer, ArrayList<Parent> parents, Stats stats, ArrayList<CommitFile> files) {
        super(null);
        this.sha = sha;
        this.nodeId = nodeId;
        this.commit = commit;
        this.url = url;
        this.htmlUrl = htmlUrl;
        this.commentsUrl = commentsUrl;
        this.author = author;
        this.committer = committer;
        this.parents = parents;
        this.stats = stats;
        this.files = files;
    }

    /**
     * Constructor to init a {@link Commit}
     *
     * @param jCommit : commit details as {@link JSONObject}
     **/
    public Commit(JSONObject jCommit) {
        super(jCommit);
        sha = hResponse.getString("sha");
        nodeId = hResponse.getString("node_id");
        commit = new CommitDetails(hResponse.getJSONObject("commit", new JSONObject()));
        url = hResponse.getString("url");
        htmlUrl = hResponse.getString("html_url");
        commentsUrl = hResponse.getString("comments_url");
        author = new User(hResponse.getJSONObject("author", new JSONObject()));
        committer = new User(hResponse.getJSONObject("committer", new JSONObject()));
        parents = returnParentsList(hResponse.getJSONArray("parents"));
        stats = new Stats(hResponse.getJSONObject("stats", new JSONObject()));
        files = returnFiles(hResponse.getJSONArray("files"));
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
     * Method to get {@link #commit} instance <br>
     * No-any params required
     *
     * @return {@link #commit} instance as {@link CommitDetails}
     **/
    public CommitDetails getCommit() {
        return commit;
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
     * Method to get {@link #htmlUrl} instance <br>
     * No-any params required
     *
     * @return {@link #htmlUrl} instance as {@link String}
     **/
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * Method to get {@link #commentsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #commentsUrl} instance as {@link String}
     **/
    public String getCommentsUrl() {
        return commentsUrl;
    }

    /**
     * Method to get {@link #author} instance <br>
     * No-any params required
     *
     * @return {@link #author} instance as {@link User}
     **/
    public User getAuthor() {
        return author;
    }

    /**
     * Method to get {@link #committer} instance <br>
     * No-any params required
     *
     * @return {@link #committer} instance as {@link User}
     **/
    public User getCommitter() {
        return committer;
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
     * Method to get {@link #stats} instance <br>
     * No-any params required
     *
     * @return {@link #stats} instance as {@link String}
     **/
    public Stats getStats() {
        return stats;
    }

    /**
     * Method to get {@link #files} instance <br>
     * No-any params required
     *
     * @return {@link #files} instance as {@link ArrayList} of {@link CommitFile}
     **/
    public ArrayList<CommitFile> getFiles() {
        return files;
    }

    /**
     * Method to create a commits list
     *
     * @param commitsListResponse: obtained from GitHub's response
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return commits list as {@code "format"} defines
     **/
    @Returner
    public static <T> T returnCommitsList(String commitsListResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(commitsListResponse);
            case LIBRARY_OBJECT:
                ArrayList<Commit> commits = new ArrayList<>();
                JSONArray jCommits = new JSONArray(commitsListResponse);
                for (int j = 0; j < jCommits.length(); j++)
                    commits.add(new Commit(jCommits.getJSONObject(j)));
                return (T) commits;
            default:
                return (T) commitsListResponse;
        }
    }

    /**
     * The {@code CommitDetails} class is useful to format a GitHub's commit details for {@link Commit}
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class CommitDetails extends CommitData {

        /**
         * {@code commentCount} comment count of the commit
         **/
        private final int commentCount;

        /**
         * Constructor to init a {@link CommitDetails}
         *
         * @param author       : author of the commit
         * @param committer    : committer of the commit
         * @param message      : message of the commit
         * @param tree         : tree of the commit
         * @param url          : url of the commit
         * @param commentCount : comment count of the commit
         * @param verification : verification of the commit
         **/
        public CommitDetails(CommitProfile author, CommitProfile committer, String message, ShaItem tree, String url,
                             int commentCount, Verification verification) {
            super(author, committer, message, tree, url, verification);
            this.commentCount = commentCount;
        }

        /**
         * Constructor to init a {@link CommitDetails}
         *
         * @param jCommitDetails : commit details as {@link JSONObject}
         **/
        public CommitDetails(JSONObject jCommitDetails) {
            super(jCommitDetails);
            commentCount = hResponse.getInt("comment_count", 0);
        }

        /**
         * Method to get {@link #commentCount} instance <br>
         * No-any params required
         *
         * @return {@link #commentCount} instance as int
         **/
        public int getCommentCount() {
            return commentCount;
        }

    }

    /**
     * The {@code Parent} class is useful to format a GitHub's parent for {@link Commit}
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see ShaItem
     **/
    public static class Parent extends ShaItem {

        /**
         * {@code htmlUrl} html url of the parent
         **/
        private final String htmlUrl;

        /**
         * Constructor to init a {@link Parent}
         *
         * @param sha      : sha of the parent
         * @param url      : url of the parent
         * @param htmlUrl: html url of the parent
         **/
        public Parent(String sha, String url, String htmlUrl) {
            super(sha, url);
            this.htmlUrl = htmlUrl;
        }

        /**
         * Constructor to init a {@link Parent}
         *
         * @param jParent: parent details as {@link JSONObject}
         **/
        public Parent(JSONObject jParent) {
            super(jParent);
            htmlUrl = hResponse.getString("html_url");
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

        /**
         * Method to create a parents list
         *
         * @param jParents: obtained from GitHub's response
         * @return parents list as {@code "format"} defines
         **/
        @Returner
        public static ArrayList<Parent> returnParentsList(JSONArray jParents) {
            ArrayList<Parent> parents = new ArrayList<>();
            if (jParents != null)
                for (int j = 0; j < jParents.length(); j++)
                    parents.add(new Parent(jParents.getJSONObject(j)));
            return parents;
        }

    }

    /**
     * The {@code Stats} class is useful to format a GitHub's stats for {@link Commit}
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class Stats extends InnerClassItem {

        /**
         * {@code additions} value
         **/
        private final int additions;

        /**
         * {@code deletions} value
         **/
        private final int deletions;

        /**
         * {@code total} value
         **/
        private final int total;

        /**
         * Constructor to init a {@link Stats}
         *
         * @param additions : additions value
         * @param deletions : deletions value
         * @param total:    total value
         **/
        public Stats(int additions, int deletions, int total) {
            super(null);
            this.additions = additions;
            this.deletions = deletions;
            this.total = total;
        }

        /**
         * Constructor to init a {@link Stats}
         *
         * @param jStats : stats details as {@link JSONObject}
         **/
        public Stats(JSONObject jStats) {
            super(jStats);
            additions = hItem.getInt("additions", 0);
            deletions = hItem.getInt("deletions", 0);
            total = hItem.getInt("total", 0);
        }

        /**
         * Method to get {@link #additions} instance <br>
         * No-any params required
         *
         * @return {@link #additions} instance as int
         **/
        public int getAdditions() {
            return additions;
        }

        /**
         * Method to get {@link #deletions} instance <br>
         * No-any params required
         *
         * @return {@link #deletions} instance as int
         **/
        public int getDeletions() {
            return deletions;
        }

        /**
         * Method to get {@link #total} instance <br>
         * No-any params required
         *
         * @return {@link #total} instance as int
         **/
        public int getTotal() {
            return total;
        }

    }

    /**
     * The {@code CommitFile} class is useful to format a GitHub's file for {@link Commit}
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class CommitFile extends InnerClassItem {

        /**
         * {@code sha} of the file
         **/
        private final String sha;

        /**
         * {@code fileName} name of the file
         **/
        private final String fileName;

        /**
         * {@code additions} of the file
         **/
        private final int additions;

        /**
         * {@code deletions} of the file
         **/
        private final int deletions;

        /**
         * {@code changes}  of the file
         **/
        private final int changes;

        /**
         * {@code status} of the file
         **/
        private final FileStatus status;

        /**
         * {@code rawUrl} raw url of the file
         **/
        private final String rawUrl;

        /**
         * {@code blobUrl} blob url of the file
         **/
        private final String blobUrl;

        /**
         * {@code patch} patch of the file
         **/
        private final String patch;

        /**
         * Constructor to init a {@link CommitFile}
         *
         * @param sha:      sha of the file
         * @param fileName  : name of the file
         * @param additions : additions of the file
         * @param deletions : deletions of the file
         * @param changes   : changes of the file
         * @param status    : status of the file
         * @param rawUrl    :    raw url of the file
         * @param blobUrl   : blob url of the file
         * @param patch     : patch of the file
         **/
        public CommitFile(String sha, String fileName, int additions, int deletions, int changes, FileStatus status,
                          String rawUrl, String blobUrl, String patch) {
            super(null);
            this.sha = sha;
            this.fileName = fileName;
            this.additions = additions;
            this.deletions = deletions;
            this.changes = changes;
            this.status = status;
            this.rawUrl = rawUrl;
            this.blobUrl = blobUrl;
            this.patch = patch;
        }

        /**
         * Constructor to init a {@link CommitFile}
         *
         * @param jCommitFile : file details as {@link JSONObject}
         **/
        public CommitFile(JSONObject jCommitFile) {
            super(jCommitFile);
            sha = hItem.getString("sha");
            fileName = hItem.getString("filename");
            additions = hItem.getInt("additions", 0);
            deletions = hItem.getInt("deletions", 0);
            changes = hItem.getInt("changes", 0);
            status = FileStatus.valueOf(hItem.getString("status"));
            rawUrl = hItem.getString("raw_url");
            blobUrl = hItem.getString("blob_url");
            patch = hItem.getString("patch");
        }

        /**
         * Method to create a files list
         *
         * @param jFiles: obtained from GitHub's response
         * @return files list as {@link ArrayList} of {@link CommitFile}
         **/
        @Returner
        public static ArrayList<CommitFile> returnFiles(JSONArray jFiles) {
            ArrayList<CommitFile> files = new ArrayList<>();
            if (jFiles != null)
                for (int j = 0; j < jFiles.length(); j++)
                    files.add(new CommitFile(jFiles.getJSONObject(j)));
            return files;
        }

        /**
         * Method to create a files list
         *
         * @param filesResponse: obtained from GitHub's response
         * @param format:        return type formatter -> {@link ReturnFormat}
         * @return files list as {@code "format"} defines
         **/
        @Returner
        public static <T> T returnFiles(String filesResponse, ReturnFormat format) {
            switch (format) {
                case JSON:
                    return (T) new JSONArray(filesResponse);
                case LIBRARY_OBJECT:
                    returnFiles(new JSONArray(filesResponse));
                default:
                    return (T) filesResponse;
            }
        }

        /**
         * Method to get {@link #fileName} instance <br>
         * No-any params required
         *
         * @return {@link #fileName} instance as {@link String}
         **/
        public String getFileName() {
            return fileName;
        }

        /**
         * Method to get {@link #additions} instance <br>
         * No-any params required
         *
         * @return {@link #additions} instance as int
         **/
        public int getAdditions() {
            return additions;
        }

        /**
         * Method to get {@link #deletions} instance <br>
         * No-any params required
         *
         * @return {@link #deletions} instance as int
         **/
        public int getDeletions() {
            return deletions;
        }

        /**
         * Method to get {@link #changes} instance <br>
         * No-any params required
         *
         * @return {@link #changes} instance as int
         **/
        public int getChanges() {
            return changes;
        }

        /**
         * Method to get {@link #status} instance <br>
         * No-any params required
         *
         * @return {@link #status} instance as {@link FileStatus}
         **/
        public FileStatus getStatus() {
            return status;
        }

        /**
         * Method to get {@link #rawUrl} instance <br>
         * No-any params required
         *
         * @return {@link #rawUrl} instance as {@link String}
         **/
        public String getRawUrl() {
            return rawUrl;
        }

        /**
         * Method to get {@link #blobUrl} instance <br>
         * No-any params required
         *
         * @return {@link #blobUrl} instance as {@link String}
         **/
        public String getBlobUrl() {
            return blobUrl;
        }

        /**
         * Method to get {@link #patch} instance <br>
         * No-any params required
         *
         * @return {@link #patch} instance as {@link String}
         **/
        public String getPatch() {
            return patch;
        }

        /**
         * {@code FileStatus} statuses for a file
         **/
        public enum FileStatus {

            /**
             * {@code "added"} status
             **/
            added,

            /**
             * {@code "removed"} status
             **/
            removed,

            /**
             * {@code "modified"} status
             **/
            modified,

            /**
             * {@code "renamed"} status
             **/
            renamed,

            /**
             * {@code "copied"} status
             **/
            copied,

            /**
             * {@code "changed"} status
             **/
            changed,

            /**
             * {@code "unchanged"} status
             **/
            unchanged

        }

    }

}
