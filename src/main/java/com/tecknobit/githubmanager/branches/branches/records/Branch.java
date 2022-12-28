package com.tecknobit.githubmanager.branches.branches.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.apimanager.formatters.TimeFormatter;
import com.tecknobit.githubmanager.branches.branches.records.Branch.BranchCommit.CommitDetails.Tree;
import com.tecknobit.githubmanager.branches.records.BranchProtection;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@code Branch} class is useful to format a GitHub's branch
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/branches/branches#list-branches">
 *             List branches</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/branches/branches#get-a-branch">
 *             Get a branch</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/branches/branches#rename-a-branch">
 *             Rename a branch</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class Branch extends GitHubResponse {

    /**
     * {@code name} of the branch
     **/
    private final String name;

    /**
     * {@code commit} of the branch
     **/
    private final BranchCommit commit;

    /**
     * {@code _links} links of the branch
     **/
    private final BranchLink _links;

    /**
     * {@code isProtected} whether the branch is protected
     **/
    private final boolean isProtected;

    /**
     * {@code protection} of the branch
     **/
    private final BranchProtection protection;

    /**
     * {@code protectionUrl} protection url of the branch protection
     **/
    private final String protectionUrl;

    /**
     * Constructor to init a {@link Branch}
     *
     * @param name          : name of the branch
     * @param commit        : commit of the branch
     * @param _links        : links of the branch
     * @param isProtected   : whether the branch is protected
     * @param protection    : protection of the branch
     * @param protectionUrl : protection url of the branch protection
     **/
    public Branch(String name, BranchCommit commit, BranchLink _links, boolean isProtected, BranchProtection protection,
                  String protectionUrl) {
        super(null);
        this.name = name;
        this.commit = commit;
        this._links = _links;
        this.isProtected = isProtected;
        this.protection = protection;
        this.protectionUrl = protectionUrl;
    }

    /**
     * Constructor to init a {@link Branch}
     *
     * @param jBranch : branch details as {@link JSONObject}
     **/
    public Branch(JSONObject jBranch) throws Exception {
        super(jBranch);
        name = hResponse.getString("name");
        commit = new BranchCommit(hResponse.getJSONObject("commit", new JSONObject()));
        _links = new BranchLink(hResponse.getJSONObject("_links", new JSONObject()));
        isProtected = hResponse.getBoolean("protected");
        protection = new BranchProtection(hResponse.getJSONObject("protection", new JSONObject()));
        protectionUrl = hResponse.getString("protection_url");
    }

    /**
     * Method to get {@link #name} instance <br>
     * Any params required
     *
     * @return {@link #name} instance as {@link String}
     **/
    public String getName() {
        return name;
    }

    /**
     * Method to get {@link #commit} instance <br>
     * Any params required
     *
     * @return {@link #commit} instance as {@link BranchCommit}
     **/
    public BranchCommit getCommit() {
        return commit;
    }

    /**
     * Method to get {@link #_links} instance <br>
     * Any params required
     *
     * @return {@link #_links} instance as {@link BranchLink}
     **/
    public BranchLink getLinks() {
        return _links;
    }

    /**
     * Method to get {@link #isProtected} instance <br>
     * Any params required
     *
     * @return {@link #isProtected} instance as boolean
     **/
    public boolean isProtected() {
        return isProtected;
    }

    /**
     * Method to get {@link #protection} instance <br>
     * Any params required
     *
     * @return {@link #protection} instance as {@link BranchProtection}
     **/
    public BranchProtection getProtection() {
        return protection;
    }

    /**
     * Method to get {@link #protectionUrl} instance <br>
     * Any params required
     *
     * @return {@link #protectionUrl} instance as {@link String}
     **/
    public String getProtectionUrl() {
        return protectionUrl;
    }

    /**
     * The {@code BranchCommit} class is useful to format a GitHub's branch commit for {@link Branch}
     *
     * @author N7ghtm4r3 - Tecknobit
     **/
    public static class BranchCommit {

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
         * Constructor to init a {@link BranchCommit}
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
        public BranchCommit(String sha, String nodeId, CommitDetails commit, String url, String htmlUrl, String commentsUrl,
                            User author, User committer, ArrayList<Parent> parents) {
            this(sha, nodeId, commit, url, htmlUrl, commentsUrl, author, committer, parents, null, null);
        }

        /**
         * Constructor to init a {@link BranchCommit}
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
        public BranchCommit(String sha, String nodeId, CommitDetails commit, String url, String htmlUrl, String commentsUrl,
                            User author, User committer, ArrayList<Parent> parents, Stats stats,
                            ArrayList<CommitFile> files) {
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
         * Constructor to init a {@link BranchCommit}
         *
         * @param jBranchCommit : branch commit details as {@link JSONObject}
         **/
        public BranchCommit(JSONObject jBranchCommit) {
            JsonHelper hBranchCommit = new JsonHelper(jBranchCommit);
            sha = hBranchCommit.getString("sha");
            nodeId = hBranchCommit.getString("node_id");
            commit = new CommitDetails(hBranchCommit.getJSONObject("commit", new JSONObject()));
            url = hBranchCommit.getString("url");
            htmlUrl = hBranchCommit.getString("html_url");
            commentsUrl = hBranchCommit.getString("comments_url");
            author = new User(hBranchCommit.getJSONObject("author", new JSONObject()));
            committer = new User(hBranchCommit.getJSONObject("committer", new JSONObject()));
            parents = new ArrayList<>();
            JSONArray jParents = hBranchCommit.getJSONArray("parents", new JSONArray());
            for (int j = 0; j < jParents.length(); j++)
                parents.add(new Parent(jParents.getJSONObject(j)));
            stats = new Stats(hBranchCommit.getJSONObject("stats", new JSONObject()));
            files = new ArrayList<>();
            JSONArray jFiles = hBranchCommit.getJSONArray("files", new JSONArray());
            for (int j = 0; j < jFiles.length(); j++)
                files.add(new CommitFile(jFiles.getJSONObject(j)));
        }

        /**
         * Method to get {@link #sha} instance <br>
         * Any params required
         *
         * @return {@link #sha} instance as {@link String}
         **/
        public String getSha() {
            return sha;
        }

        /**
         * Method to get {@link #nodeId} instance <br>
         * Any params required
         *
         * @return {@link #nodeId} instance as {@link String}
         **/
        public String getNodeId() {
            return nodeId;
        }

        /**
         * Method to get {@link #commit} instance <br>
         * Any params required
         *
         * @return {@link #commit} instance as {@link CommitDetails}
         **/
        public CommitDetails getCommit() {
            return commit;
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
         * Method to get {@link #commentsUrl} instance <br>
         * Any params required
         *
         * @return {@link #commentsUrl} instance as {@link String}
         **/
        public String getCommentsUrl() {
            return commentsUrl;
        }

        /**
         * Method to get {@link #author} instance <br>
         * Any params required
         *
         * @return {@link #author} instance as {@link User}
         **/
        public User getAuthor() {
            return author;
        }

        /**
         * Method to get {@link #committer} instance <br>
         * Any params required
         *
         * @return {@link #committer} instance as {@link User}
         **/
        public User getCommitter() {
            return committer;
        }

        /**
         * Method to get {@link #parents} instance <br>
         * Any params required
         *
         * @return {@link #parents} instance as {@link Collection} of {@link Parent}
         **/
        public Collection<Parent> getParents() {
            return parents;
        }

        /**
         * Method to get {@link #stats} instance <br>
         * Any params required
         *
         * @return {@link #stats} instance as {@link String}
         **/
        public Stats getStats() {
            return stats;
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
         * Returns a string representation of the object <br>
         * Any params required
         *
         * @return a string representation of the object as {@link String}
         */
        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

        /**
         * The {@code CommitDetails} class is useful to format a GitHub's commit details for {@link BranchCommit}
         *
         * @author N7ghtm4r3 - Tecknobit
         **/
        public static class CommitDetails {

            /**
             * {@code author} of the commit
             **/
            private final CommitProfile author;

            /**
             * {@code committer} of the commit
             **/
            private final CommitProfile committer;

            /**
             * {@code message} of the commit
             **/
            private final String message;

            /**
             * {@code tree} of the commit
             **/
            private final Tree tree;

            /**
             * {@code url} of the commit
             **/
            private final String url;

            /**
             * {@code commentCount} comment count of the commit
             **/
            private final int commentCount;

            /**
             * {@code verification} of the commit
             **/
            private final Verification verification;

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
            public CommitDetails(CommitProfile author, CommitProfile committer, String message, Tree tree, String url,
                                 int commentCount, Verification verification) {
                this.author = author;
                this.committer = committer;
                this.message = message;
                this.tree = tree;
                this.url = url;
                this.commentCount = commentCount;
                this.verification = verification;
            }

            /**
             * Constructor to init a {@link CommitDetails}
             *
             * @param jCommitDetails : commit details as {@link JSONObject}
             **/
            public CommitDetails(JSONObject jCommitDetails) {
                JsonHelper hCommitDetails = new JsonHelper(jCommitDetails);
                author = new CommitProfile(hCommitDetails.getJSONObject("author", new JSONObject()));
                committer = new CommitProfile(hCommitDetails.getJSONObject("committer", new JSONObject()));
                message = hCommitDetails.getString("message");
                tree = new Tree(hCommitDetails.getJSONObject("tree", new JSONObject()));
                url = hCommitDetails.getString("url");
                commentCount = hCommitDetails.getInt("comment_count", 0);
                verification = new Verification(hCommitDetails.getJSONObject("verification", new JSONObject()));
            }

            /**
             * Method to get {@link #author} instance <br>
             * Any params required
             *
             * @return {@link #author} instance as {@link CommitProfile}
             **/
            public CommitProfile getAuthor() {
                return author;
            }

            /**
             * Method to get {@link #committer} instance <br>
             * Any params required
             *
             * @return {@link #committer} instance as {@link CommitProfile}
             **/
            public CommitProfile getCommitter() {
                return committer;
            }

            /**
             * Method to get {@link #message} instance <br>
             * Any params required
             *
             * @return {@link #message} instance as {@link String}
             **/
            public String getMessage() {
                return message;
            }

            /**
             * Method to get {@link #tree} instance <br>
             * Any params required
             *
             * @return {@link #tree} instance as {@link Tree}
             **/
            public Tree getTree() {
                return tree;
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
             * Method to get {@link #commentCount} instance <br>
             * Any params required
             *
             * @return {@link #commentCount} instance as int
             **/
            public int getCommentCount() {
                return commentCount;
            }

            /**
             * Method to get {@link #verification} instance <br>
             * Any params required
             *
             * @return {@link #verification} instance as {@link Verification}
             **/
            public Verification getVerification() {
                return verification;
            }

            /**
             * Returns a string representation of the object <br>
             * Any params required
             *
             * @return a string representation of the object as {@link String}
             */
            @Override
            public String toString() {
                return new JSONObject(this).toString();
            }

            /**
             * The {@code Tree} class is useful to format a GitHub's commit tree for {@link CommitDetails}
             *
             * @author N7ghtm4r3 - Tecknobit
             **/
            public static class Tree {

                /**
                 * {@code sha} of the tree
                 **/
                protected final String sha;

                /**
                 * {@code url} of the tree
                 **/
                protected final String url;

                /**
                 * {@code hTree} {@link JsonHelper} for the tree
                 **/
                protected final JsonHelper hTree;

                /**
                 * Constructor to init a {@link Tree}
                 *
                 * @param sha : sha of the tree
                 * @param url : url of the tree
                 **/
                public Tree(String sha, String url) {
                    this.sha = sha;
                    this.url = url;
                    hTree = null;
                }

                /**
                 * Constructor to init a {@link Tree}
                 *
                 * @param jTree : tree details as {@link JSONObject}
                 **/
                public Tree(JSONObject jTree) {
                    hTree = new JsonHelper(jTree);
                    sha = hTree.getString("sha");
                    url = hTree.getString("url");
                }

                /**
                 * Method to get {@link #sha} instance <br>
                 * Any params required
                 *
                 * @return {@link #sha} instance as {@link String}
                 **/
                public String getSha() {
                    return sha;
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
                 * Returns a string representation of the object <br>
                 * Any params required
                 *
                 * @return a string representation of the object as {@link String}
                 */
                @Override
                public String toString() {
                    return new JSONObject(this).toString();
                }

            }

            /**
             * The {@code CommitProfile} class is useful to format a GitHub's commit profile for {@link CommitDetails}
             *
             * @author N7ghtm4r3 - Tecknobit
             **/
            public static class CommitProfile {

                /**
                 * {@code name} of the profile
                 **/
                private final String name;

                /**
                 * {@code email} of the profile
                 **/
                private final String email;

                /**
                 * {@code date} of the committing
                 **/
                private final String date;

                /**
                 * Constructor to init a {@link CommitProfile}
                 *
                 * @param name  : name of the profile
                 * @param email : email of the profile
                 * @param date  : date of the committing
                 **/
                public CommitProfile(String name, String email, String date) {
                    this.name = name;
                    this.email = email;
                    this.date = date;
                }

                /**
                 * Constructor to init a {@link CommitProfile}
                 *
                 * @param jCommitProfile : commit profile as {@link JSONObject}
                 **/
                public CommitProfile(JSONObject jCommitProfile) {
                    JsonHelper hCommitProfile = new JsonHelper(jCommitProfile);
                    name = hCommitProfile.getString("name");
                    email = hCommitProfile.getString("email");
                    date = hCommitProfile.getString("date");
                }

                /**
                 * Method to get {@link #name} instance <br>
                 * Any params required
                 *
                 * @return {@link #name} instance as {@link String}
                 **/
                public String getName() {
                    return name;
                }

                /**
                 * Method to get {@link #email} instance <br>
                 * Any params required
                 *
                 * @return {@link #email} instance as {@link String}
                 **/
                public String getEmail() {
                    return email;
                }

                /**
                 * Method to get {@link #date} instance <br>
                 * Any params required
                 *
                 * @return {@link #date} instance as {@link String}
                 **/
                public String getDate() {
                    return date;
                }

                /**
                 * Method to get {@link #date} timestamp <br>
                 * Any params required
                 *
                 * @return {@link #date} timestamp as long
                 **/
                public long getDateTimestamp() {
                    return TimeFormatter.getDateTimestamp(date);
                }

                /**
                 * Returns a string representation of the object <br>
                 * Any params required
                 *
                 * @return a string representation of the object as {@link String}
                 */
                @Override
                public String toString() {
                    return new JSONObject(this).toString();
                }

            }

            /**
             * The {@code Verification} class is useful to format a GitHub's verification for {@link CommitDetails}
             *
             * @author N7ghtm4r3 - Tecknobit
             **/
            public static class Verification {

                /**
                 * {@code verified} whether the commit is verified
                 **/
                private final boolean verified;

                /**
                 * {@code reason} of the verification
                 **/
                private final String reason;

                /**
                 * {@code signature} of the verification
                 **/
                private final String signature;

                /**
                 * {@code payload} of the verification
                 **/
                private final String payload;

                /**
                 * Constructor to init a {@link Verification}
                 *
                 * @param verified  : verified of the verification
                 * @param reason    : reason of the verification
                 * @param signature : signature of the verification
                 * @param payload   : payload of the verification
                 **/
                public Verification(boolean verified, String reason, String signature, String payload) {
                    this.verified = verified;
                    this.reason = reason;
                    this.signature = signature;
                    this.payload = payload;
                }

                /**
                 * Constructor to init a {@link Verification}
                 *
                 * @param jVerification : verification details as {@link JSONObject}
                 **/
                public Verification(JSONObject jVerification) {
                    JsonHelper hVerification = new JsonHelper(jVerification);
                    verified = hVerification.getBoolean("verified");
                    reason = hVerification.getString("reason");
                    signature = hVerification.getString("signature");
                    payload = hVerification.getString("payload");
                }

                /**
                 * Method to get {@link #verified} instance <br>
                 * Any params required
                 *
                 * @return {@link #verified} instance as boolean
                 **/
                public boolean isVerified() {
                    return verified;
                }

                /**
                 * Method to get {@link #reason} instance <br>
                 * Any params required
                 *
                 * @return {@link #reason} instance as {@link String}
                 **/
                public String getReason() {
                    return reason;
                }

                /**
                 * Method to get {@link #signature} instance <br>
                 * Any params required
                 *
                 * @return {@link #signature} instance as {@link String}
                 **/
                public String getSignature() {
                    return signature;
                }

                /**
                 * Method to get {@link #payload} instance <br>
                 * Any params required
                 *
                 * @return {@link #payload} instance as {@link String}
                 **/
                public String getPayload() {
                    return payload;
                }

                /**
                 * Returns a string representation of the object <br>
                 * Any params required
                 *
                 * @return a string representation of the object as {@link String}
                 */
                @Override
                public String toString() {
                    return new JSONObject(this).toString();
                }

            }

        }

        /**
         * The {@code Parent} class is useful to format a GitHub's parent for {@link BranchCommit}
         *
         * @author N7ghtm4r3 - Tecknobit
         * @see Tree
         **/
        public static class Parent extends Tree {

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
                htmlUrl = hTree.getString("html_url");
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

        }

        /**
         * The {@code Stats} class is useful to format a GitHub's stats for {@link BranchCommit}
         *
         * @author N7ghtm4r3 - Tecknobit
         **/
        public static class Stats {

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
                JsonHelper hStats = new JsonHelper(jStats);
                additions = hStats.getInt("additions", 0);
                deletions = hStats.getInt("deletions", 0);
                total = hStats.getInt("total", 0);
            }

            /**
             * Method to get {@link #additions} instance <br>
             * Any params required
             *
             * @return {@link #additions} instance as int
             **/
            public int getAdditions() {
                return additions;
            }

            /**
             * Method to get {@link #deletions} instance <br>
             * Any params required
             *
             * @return {@link #deletions} instance as int
             **/
            public int getDeletions() {
                return deletions;
            }

            /**
             * Method to get {@link #total} instance <br>
             * Any params required
             *
             * @return {@link #total} instance as int
             **/
            public int getTotal() {
                return total;
            }

            /**
             * Returns a string representation of the object <br>
             * Any params required
             *
             * @return a string representation of the object as {@link String}
             */
            @Override
            public String toString() {
                return new JSONObject(this).toString();
            }

        }

        /**
         * The {@code CommitFile} class is useful to format a GitHub's file for {@link BranchCommit}
         *
         * @author N7ghtm4r3 - Tecknobit
         **/
        public static class CommitFile {

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
             * @param fileName   : name of the file
             * @param additions  : additions of the file
             * @param deletions: deletions of the file
             * @param changes    : changes of the file
             * @param status     : status of the file
             * @param rawUrl:    raw url of the file
             * @param blobUrl    : blob url of the file
             * @param patch      : patch of the file
             **/
            public CommitFile(String fileName, int additions, int deletions, int changes, FileStatus status, String rawUrl,
                              String blobUrl, String patch) {
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
                JsonHelper hCommitFile = new JsonHelper(jCommitFile);
                fileName = hCommitFile.getString("filename");
                additions = hCommitFile.getInt("additions", 0);
                deletions = hCommitFile.getInt("deletions", 0);
                changes = hCommitFile.getInt("changes", 0);
                status = FileStatus.valueOf(hCommitFile.getString("status"));
                rawUrl = hCommitFile.getString("raw_url");
                blobUrl = hCommitFile.getString("blob_url");
                patch = hCommitFile.getString("patch");
            }

            /**
             * Method to get {@link #fileName} instance <br>
             * Any params required
             *
             * @return {@link #fileName} instance as {@link String}
             **/
            public String getFileName() {
                return fileName;
            }

            /**
             * Method to get {@link #additions} instance <br>
             * Any params required
             *
             * @return {@link #additions} instance as int
             **/
            public int getAdditions() {
                return additions;
            }

            /**
             * Method to get {@link #deletions} instance <br>
             * Any params required
             *
             * @return {@link #deletions} instance as int
             **/
            public int getDeletions() {
                return deletions;
            }

            /**
             * Method to get {@link #changes} instance <br>
             * Any params required
             *
             * @return {@link #changes} instance as int
             **/
            public int getChanges() {
                return changes;
            }

            /**
             * Method to get {@link #status} instance <br>
             * Any params required
             *
             * @return {@link #status} instance as {@link FileStatus}
             **/
            public FileStatus getStatus() {
                return status;
            }

            /**
             * Method to get {@link #rawUrl} instance <br>
             * Any params required
             *
             * @return {@link #rawUrl} instance as {@link String}
             **/
            public String getRawUrl() {
                return rawUrl;
            }

            /**
             * Method to get {@link #blobUrl} instance <br>
             * Any params required
             *
             * @return {@link #blobUrl} instance as {@link String}
             **/
            public String getBlobUrl() {
                return blobUrl;
            }

            /**
             * Method to get {@link #patch} instance <br>
             * Any params required
             *
             * @return {@link #patch} instance as {@link String}
             **/
            public String getPatch() {
                return patch;
            }

            /**
             * Returns a string representation of the object <br>
             * Any params required
             *
             * @return a string representation of the object as {@link String}
             */
            @Override
            public String toString() {
                return new JSONObject(this).toString();
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

    /**
     * The {@code BranchLink} class is useful to format a GitHub's branch link for {@link Branch}
     *
     * @author N7ghtm4r3 - Tecknobit
     **/
    public static class BranchLink {

        /**
         * {@code self} value of the link
         **/
        private final String self;

        /**
         * {@code html} value of the link
         **/
        private final String html;

        /**
         * Constructor to init a {@link BranchLink}
         *
         * @param self : self value of the link
         * @param html : html value of the link
         **/
        public BranchLink(String self, String html) {
            this.self = self;
            this.html = html;
        }

        /**
         * Constructor to init a {@link BranchLink}
         *
         * @param jBranchLink : link details as {@link JSONObject}
         **/
        public BranchLink(JSONObject jBranchLink) {
            JsonHelper hBranchLink = new JsonHelper(jBranchLink);
            self = hBranchLink.getString("self");
            html = hBranchLink.getString("html");
        }

        /**
         * Method to get {@link #self} instance <br>
         * Any params required
         *
         * @return {@link #self} instance as {@link String}
         **/
        public String getSelf() {
            return self;
        }

        /**
         * Method to get {@link #html} instance <br>
         * Any params required
         *
         * @return {@link #html} instance as {@link String}
         **/
        public String getHtml() {
            return html;
        }

        /**
         * Returns a string representation of the object <br>
         * Any params required
         *
         * @return a string representation of the object as {@link String}
         */
        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

    }

}
