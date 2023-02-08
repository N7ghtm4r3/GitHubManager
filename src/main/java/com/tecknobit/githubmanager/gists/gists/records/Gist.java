package com.tecknobit.githubmanager.gists.gists.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONObject;

public class Gist extends GitHubResponse {

    private final String url;
    private final String forksUrl;
    private final String commitsUrl;
    private final String id;
    private final String nodeId;
    private final String gitPullUrl;
    private final String gitPushUrl;
    private final String htmlUrl;
    private final GistFile files;
    private final boolean isPublic;
    private final String createdAt;
    private final String updatedAt;
    private final String description;
    private final int comments;
    private final String commentsUrl;
    private final User owner;
    private final boolean truncated;

    public Gist(String url, String forksUrl, String commitsUrl, String id, String nodeId, String gitPullUrl,
                String gitPushUrl, String htmlUrl, GistFile files, boolean isPublic, String createdAt, String updatedAt,
                String description, int comments, String commentsUrl, User owner, boolean truncated) {
        super(null);
        this.url = url;
        this.forksUrl = forksUrl;
        this.commitsUrl = commitsUrl;
        this.id = id;
        this.nodeId = nodeId;
        this.gitPullUrl = gitPullUrl;
        this.gitPushUrl = gitPushUrl;
        this.htmlUrl = htmlUrl;
        this.files = files;
        this.isPublic = isPublic;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.description = description;
        this.comments = comments;
        this.commentsUrl = commentsUrl;
        this.owner = owner;
        this.truncated = truncated;
    }

    public Gist(JSONObject jGist) {
        super(jGist);
        url = hResponse.getString("url");
        forksUrl = hResponse.getString("forks_url");
        commitsUrl = hResponse.getString("commits_url");
        id = hResponse.getString("id");
        nodeId = hResponse.getString("node_id");
        gitPullUrl = hResponse.getString("git_pull_url");
        gitPushUrl = hResponse.getString("git_push_url");
        htmlUrl = hResponse.getString("html_url");
        files = new GistFile(hResponse.getJSONObject("files"));
        isPublic = hResponse.getBoolean("public");
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
        description = hResponse.getString("description");
        comments = hResponse.getInt("comments", 0);
        commentsUrl = hResponse.getString("comments_url");
        owner = new User(hResponse.getJSONObject("owner"));
        truncated = hResponse.getBoolean("truncated");
    }

    public String getUrl() {
        return url;
    }

    public String getForksUrl() {
        return forksUrl;
    }

    public String getCommitsUrl() {
        return commitsUrl;
    }

    public String getId() {
        return id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getGitPullUrl() {
        return gitPullUrl;
    }

    public String getGitPushUrl() {
        return gitPushUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public GistFile getFiles() {
        return files;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getDescription() {
        return description;
    }

    public int getComments() {
        return comments;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public User getOwner() {
        return owner;
    }

    public boolean isTruncated() {
        return truncated;
    }

    public static class GistFile extends InnerClassItem {

        private final String fileName;
        private final String type;
        private final String language;
        private final String rawUrl;
        private final double size;
        private final boolean truncated;
        private final String content;

        public GistFile(String fileName, String content) {
            this(fileName, null, null, null, -1, false, content);
        }

        public GistFile(String fileName, String type, String language, String rawUrl, double size, boolean truncated,
                        String content) {
            super(null);
            this.fileName = fileName;
            this.type = type;
            this.language = language;
            this.rawUrl = rawUrl;
            this.size = size;
            this.truncated = truncated;
            this.content = content;
        }

        public GistFile(JSONObject jGistFile) {
            super(jGistFile);
            fileName = hItem.getString("filename");
            type = hItem.getString("type");
            language = hItem.getString("language");
            rawUrl = hItem.getString("raw_url");
            size = hItem.getDouble("size", 0);
            truncated = hItem.getBoolean("truncated");
            content = hItem.getString("content");
        }

        public String getFileName() {
            return fileName;
        }

        public String getType() {
            return type;
        }

        public String getLanguage() {
            return language;
        }

        public String getRawUrl() {
            return rawUrl;
        }

        public double getSize() {
            return size;
        }

        public boolean isTruncated() {
            return truncated;
        }

        public String getContent() {
            return content;
        }

    }

}
