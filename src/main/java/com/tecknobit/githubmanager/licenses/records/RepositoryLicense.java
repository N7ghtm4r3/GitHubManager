package com.tecknobit.githubmanager.licenses.records;

import com.tecknobit.githubmanager.records.generic.ShaItem;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import org.json.JSONObject;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code RepositoryLicense} class is useful to format a GitHub's repository license
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/licenses#get-the-license-for-a-repository">
 * Get the license for a repository</a>
 * @see GitHubResponse
 * @see ShaItem
 **/
public class RepositoryLicense extends ShaItem {

    /**
     * {@code name} of the repository license
     **/
    private final String name;

    /**
     * {@code path} of the repository license
     **/
    private final String path;

    /**
     * {@code size} of the repository license
     **/
    private final double size;

    /**
     * {@code htmlUrl} html url of the repository license
     **/
    private final String htmlUrl;

    /**
     * {@code gitUrl} git url of the repository license
     **/
    private final String gitUrl;

    /**
     * {@code downloadUrl} download url of the repository license
     **/
    private final String downloadUrl;

    /**
     * {@code type} of the repository license
     **/
    private final String type;

    /**
     * {@code content} of the repository license
     **/
    private final String content;

    /**
     * {@code encoding} of the repository license
     **/
    private final String encoding;

    /**
     * {@code links} of the repository license
     **/
    private final Links links;

    /**
     * {@code commonLicense} the common license used
     **/
    private final CommonLicense commonLicense;

    /**
     * Constructor to init a {@link RepositoryLicense}
     *
     * @param sha           : sha of the repository license
     * @param url           : url of the repository license
     * @param name          : name of the repository license
     * @param path          : path of the repository license
     * @param size          : size of the repository license
     * @param htmlUrl       : html url of the repository license
     * @param gitUrl        : git url of the repository license
     * @param downloadUrl   : download url of the repository license
     * @param type          : type of the repository license
     * @param content       : content of the repository license
     * @param encoding      : encoding of the repository license
     * @param links         : links of the repository license
     * @param commonLicense : the common license used
     **/
    public RepositoryLicense(String sha, String url, String name, String path, double size, String htmlUrl, String gitUrl,
                             String downloadUrl, String type, String content, String encoding, Links links,
                             CommonLicense commonLicense) {
        super(sha, url);
        this.name = name;
        this.path = path;
        this.size = size;
        this.htmlUrl = htmlUrl;
        this.gitUrl = gitUrl;
        this.downloadUrl = downloadUrl;
        this.type = type;
        this.content = content;
        this.encoding = encoding;
        this.links = links;
        this.commonLicense = commonLicense;
    }

    /**
     * Constructor to init a {@link RepositoryLicense}
     *
     * @param jRepositoryLicense : repository license details as {@link JSONObject}
     **/
    public RepositoryLicense(JSONObject jRepositoryLicense) {
        super(jRepositoryLicense);
        name = hResponse.getString("name");
        path = hResponse.getString("path");
        size = hResponse.getDouble("size", 0);
        htmlUrl = hResponse.getString("html_url");
        gitUrl = hResponse.getString("git_url");
        downloadUrl = hResponse.getString("download_url");
        type = hResponse.getString("type");
        content = hResponse.getString("content");
        encoding = hResponse.getString("encoding");
        links = new Links(hResponse.getJSONObject("_links"));
        commonLicense = new CommonLicense(hResponse.getJSONObject("license"));
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
     * Method to get {@link #path} instance <br>
     * No-any params required
     *
     * @return {@link #path} instance as {@link String}
     **/
    public String getPath() {
        return path;
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
     * Method to get {@link #htmlUrl} instance <br>
     * No-any params required
     *
     * @return {@link #htmlUrl} instance as {@link String}
     **/
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * Method to get {@link #gitUrl} instance <br>
     * No-any params required
     *
     * @return {@link #gitUrl} instance as {@link String}
     **/
    public String getGitUrl() {
        return gitUrl;
    }

    /**
     * Method to get {@link #downloadUrl} instance <br>
     * No-any params required
     *
     * @return {@link #downloadUrl} instance as {@link String}
     **/
    public String getDownloadUrl() {
        return downloadUrl;
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
     * Method to get {@link #content} instance <br>
     * No-any params required
     *
     * @return {@link #content} instance as {@link String}
     **/
    public String getContent() {
        return content;
    }

    /**
     * Method to get {@link #encoding} instance <br>
     * No-any params required
     *
     * @return {@link #encoding} instance as {@link String}
     **/
    public String getEncoding() {
        return encoding;
    }

    /**
     * Method to get {@link #links} instance <br>
     * No-any params required
     *
     * @return {@link #links} instance as {@link Links}
     **/
    public Links getLinks() {
        return links;
    }

    /**
     * Method to get {@link #commonLicense} instance <br>
     * No-any params required
     *
     * @return {@link #commonLicense} instance as {@link CommonLicense}
     **/
    public CommonLicense getCommonLicense() {
        return commonLicense;
    }

    /**
     * The {@code Links} class is useful to format a GitHub's links for the repository license
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class Links extends InnerClassItem {

        /**
         * {@code self} link
         **/
        private final String self;

        /**
         * {@code git} link
         **/
        private final String git;

        /**
         * {@code html} link
         **/
        private final String html;

        /**
         * Constructor to init a {@link Links}
         *
         * @param self : self link
         * @param git: git link
         * @param html : html link
         **/
        public Links(String self, String git, String html) {
            super(null);
            this.self = self;
            this.git = git;
            this.html = html;
        }

        /**
         * Constructor to init a {@link Links}
         *
         * @param jLinks : links details as {@link JSONObject}
         **/
        public Links(JSONObject jLinks) {
            super(jLinks);
            self = hItem.getString("self");
            git = hItem.getString("git");
            html = hItem.getString("html");
        }

        /**
         * Method to get {@link #self} instance <br>
         * No-any params required
         *
         * @return {@link #self} instance as {@link String}
         **/
        public String getSelf() {
            return self;
        }

        /**
         * Method to get {@link #git} instance <br>
         * No-any params required
         *
         * @return {@link #git} instance as {@link String}
         **/
        public String getGit() {
            return git;
        }

        /**
         * Method to get {@link #html} instance <br>
         * No-any params required
         *
         * @return {@link #html} instance as {@link String}
         **/
        public String getHtml() {
            return html;
        }

    }

}
