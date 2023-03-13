package com.tecknobit.githubmanager.gists.gists.records;

import com.tecknobit.githubmanager.records.parents.BaseItemStructure;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONObject;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code Gist} class is useful to format a GitHub's gist
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/gists/gists#list-gists-for-the-authenticated-user">
 *             List gists for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/gists/gists#create-a-gist">
 *             Create a gist</a>
 *     </li>
 *     <li>
 *          <a href="https://docs.github.com/en/rest/gists/gists#list-public-gists">
 *              List public gists</a>
 *     </li>
 *     <li>
 *          <a href="https://docs.github.com/en/rest/gists/gists#list-starred-gists">
 *              List starred gists</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/gists/gists#get-a-gist">
 *             Get a gist</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/gists/gists#update-a-gist">
 *             Update a gist</a>
 *     </li>
 *     <li>
 *          <a href="https://docs.github.com/en/rest/gists/gists#list-gists-for-a-user">
 *              List gists for a user</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see BaseItemStructure
 **/
public class Gist extends BaseItemStructure {

    /**
     * {@code forksUrl} the forks url of the gist
     **/
    private final String forksUrl;

    /**
     * {@code commitsUrl} the commits url of the gist
     **/
    private final String commitsUrl;

    /**
     * {@code gitPullUrl} the git pull url of the gist
     **/
    private final String gitPullUrl;

    /**
     * {@code gitPushUrl} the git push url of the gist
     **/
    private final String gitPushUrl;

    /**
     * {@code htmlUrl} the html url of the gist
     **/
    private final String htmlUrl;

    /**
     * {@code files} the files of the gist
     **/
    private final GistFile files;

    /**
     * {@code isPublic} whether the gist is public
     **/
    private final boolean isPublic;

    /**
     * {@code description} the description of the gist
     **/
    private final String description;

    /**
     * {@code comments} the comments of the gist
     **/
    private final int comments;

    /**
     * {@code commentsUrl} the comments url of the gist
     **/
    private final String commentsUrl;

    /**
     * {@code owner} the owner of the gist
     **/
    private final User owner;

    /**
     * {@code truncated} whether the gist is truncated
     **/
    private final boolean truncated;

    /**
     * Constructor to init a {@link Gist}
     *
     * @param url         : the url of the gist
     * @param id          : the id of the gist
     * @param nodeId      : the node id of the gist
     * @param createdAt   : the creation time of the gist
     * @param updatedAt   : the updated time of the gist
     * @param forksUrl    : the forks url of the gist
     * @param commitsUrl  : the commits url of the gist
     * @param gitPullUrl  : the git pull url of the gist
     * @param gitPushUrl  : the git push url of the gist
     * @param htmlUrl     : the html url of the gist
     * @param files       : the files of the gist
     * @param isPublic    : whether the gist is public
     * @param description : the description of the gist
     * @param comments    : the comments of the gist
     * @param commentsUrl : the comments url of the gist
     * @param owner       : the owner of the gist
     * @param truncated   : whether the gist is truncated
     **/
    public Gist(String url, String id, String nodeId, String createdAt, String updatedAt, String forksUrl, String commitsUrl,
                String gitPullUrl, String gitPushUrl, String htmlUrl, GistFile files, boolean isPublic, String description,
                int comments, String commentsUrl, User owner, boolean truncated) {
        super(url, id, nodeId, createdAt, updatedAt);
        this.forksUrl = forksUrl;
        this.commitsUrl = commitsUrl;
        this.gitPullUrl = gitPullUrl;
        this.gitPushUrl = gitPushUrl;
        this.htmlUrl = htmlUrl;
        this.files = files;
        this.isPublic = isPublic;
        this.description = description;
        this.comments = comments;
        this.commentsUrl = commentsUrl;
        this.owner = owner;
        this.truncated = truncated;
    }

    /**
     * Constructor to init a {@link Gist}
     *
     * @param jGist : gist details as {@link JSONObject}
     **/
    public Gist(JSONObject jGist) {
        super(jGist);
        forksUrl = hResponse.getString("forks_url");
        commitsUrl = hResponse.getString("commits_url");
        gitPullUrl = hResponse.getString("git_pull_url");
        gitPushUrl = hResponse.getString("git_push_url");
        htmlUrl = hResponse.getString("html_url");
        files = new GistFile(hResponse.getJSONObject("files"));
        isPublic = hResponse.getBoolean("public");
        description = hResponse.getString("description");
        comments = hResponse.getInt("comments", 0);
        commentsUrl = hResponse.getString("comments_url");
        owner = new User(hResponse.getJSONObject("owner"));
        truncated = hResponse.getBoolean("truncated");
    }

    /**
     * Method to get {@link #forksUrl} instance <br>
     * No-any params required
     *
     * @return {@link #forksUrl} instance as {@link String}
     **/
    public String getForksUrl() {
        return forksUrl;
    }

    /**
     * Method to get {@link #commitsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #commitsUrl} instance as {@link String}
     **/
    public String getCommitsUrl() {
        return commitsUrl;
    }

    /**
     * Method to get {@link #gitPullUrl} instance <br>
     * No-any params required
     *
     * @return {@link #gitPullUrl} instance as {@link String}
     **/
    public String getGitPullUrl() {
        return gitPullUrl;
    }

    /**
     * Method to get {@link #gitPushUrl} instance <br>
     * No-any params required
     *
     * @return {@link #gitPushUrl} instance as {@link String}
     **/
    public String getGitPushUrl() {
        return gitPushUrl;
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
     * Method to get {@link #files} instance <br>
     * No-any params required
     *
     * @return {@link #files} instance as {@link GistFile}
     **/
    public GistFile getFiles() {
        return files;
    }

    /**
     * Method to get {@link #isPublic} instance <br>
     * No-any params required
     *
     * @return {@link #isPublic} instance as boolean
     **/
    public boolean isPublic() {
        return isPublic;
    }

    /**
     * Method to get {@link #description} instance <br>
     * No-any params required
     *
     * @return {@link #description} instance as {@link String}
     **/
    public String getDescription() {
        return description;
    }

    /**
     * Method to get {@link #comments} instance <br>
     * No-any params required
     *
     * @return {@link #comments} instance as int
     **/
    public int getComments() {
        return comments;
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
     * Method to get {@link #owner} instance <br>
     * No-any params required
     *
     * @return {@link #owner} instance as {@link User}
     **/
    public User getOwner() {
        return owner;
    }

    /**
     * Method to get {@link #truncated} instance <br>
     * No-any params required
     *
     * @return {@link #truncated} instance as boolean
     **/
    public boolean isTruncated() {
        return truncated;
    }

    /**
     * The {@code GistFile} class is useful to format a GitHub's gist file
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class GistFile extends InnerClassItem {

        /**
         * {@code fileName} name of the gist file
         **/
        private final String fileName;

        /**
         * {@code type} of the gist file
         **/
        private final String type;

        /**
         * {@code language} of the gist file
         **/
        private final String language;

        /**
         * {@code rawUrl} raw url of the gist file
         **/
        private final String rawUrl;

        /**
         * {@code size} of the gist file
         **/
        private final double size;

        /**
         * {@code truncated} whether the file is truncated
         **/
        private final boolean truncated;

        /**
         * {@code content} of the gist file
         **/
        private final String content;

        /**
         * Constructor to init a {@link GistFile}
         *
         * @param fileName : name of the gist file
         * @param content  : content of the gist file
         * @apiNote this constructor is useful when you need to create or update a gist file
         **/
        public GistFile(String fileName, String content) {
            this(fileName, null, null, null, -1, false, content);
        }

        /**
         * Constructor to init a {@link GistFile}
         *
         * @param fileName  : name of the gist file
         * @param type      : type of the gist file
         * @param language  : language of the gist file
         * @param rawUrl    : raw url of the gist file
         * @param size      : size of the gist file
         * @param truncated : whether the file is truncated
         * @param content   : content of the gist file
         **/
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

        /**
         * Constructor to init a {@link GistFile}
         *
         * @param jGistFile : gist file details as {@link JSONObject}
         **/
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
         * Method to get {@link #type} instance <br>
         * No-any params required
         *
         * @return {@link #type} instance as {@link String}
         **/
        public String getType() {
            return type;
        }

        /**
         * Method to get {@link #language} instance <br>
         * No-any params required
         *
         * @return {@link #language} instance as {@link String}
         **/
        public String getLanguage() {
            return language;
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
         * Method to get {@link #truncated} instance <br>
         * No-any params required
         *
         * @return {@link #truncated} instance as boolean
         **/
        public boolean isTruncated() {
            return truncated;
        }

        /**
         * Method to get {@link #content} instance <br>
         * No-any params required
         *
         * @return {@link #content} instance as {@link String}
         **/
        public String getContent() {
            return content;
        }

    }

}
