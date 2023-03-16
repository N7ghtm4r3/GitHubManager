package com.tecknobit.githubmanager.repositories.contents.records;

import com.tecknobit.githubmanager.gitdatabase.blobs.records.Blob.Encoding;
import com.tecknobit.githubmanager.licenses.records.RepositoryLicense.Links;
import com.tecknobit.githubmanager.records.generic.ShaItem;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code ContentFile} class is useful to format a GitHub's content file
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/repos/contents#get-repository-content">
 *             Get repository content</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/repos/contents#get-a-repository-readmey">
 *             Get a repository README</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/repos/contents#get-a-repository-readme-for-a-directory">
 *             Get a repository README for a directory</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see ShaItem
 **/
public class ContentFile extends ShaItem {

    /**
     * {@code type} of the content file
     **/
    private final String type;

    /**
     * {@code encoding} of the content file
     **/
    private final Encoding encoding;

    /**
     * {@code size} of the content file
     **/
    private final double size;

    /**
     * {@code name} of the content file
     **/
    private final String name;

    /**
     * {@code path} of the content file
     **/
    private final String path;

    /**
     * {@code content} of the file
     **/
    private final String content;

    /**
     * {@code gitUrl} git url of the content file
     **/
    private final String gitUrl;

    /**
     * {@code htmlUrl} html url of the content file
     **/
    private final String htmlUrl;

    /**
     * {@code downloadUrl} download url of the content file
     **/
    private final String downloadUrl;

    /**
     * {@code links} of the content file
     **/
    private final Links links;

    /**
     * {@code target} of the content file
     **/
    private final String target;

    /**
     * {@code submoduleGitUrl} submodule git url of the content file
     **/
    private final String submoduleGitUrl;

    /**
     * Constructor to init a {@link ContentFile}
     *
     * @param sha             : sha of the content file
     * @param url             : url of the content file
     * @param type            : type of the content file
     * @param encoding        : encoding of the content file
     * @param size            : size of the content file
     * @param name            : name of the content file
     * @param path            : path of the content file
     * @param content         : content of the file
     * @param gitUrl          : git url of the content file
     * @param htmlUrl         : html url of the content file
     * @param downloadUrl     : download url of the content file
     * @param links           : links of the content file
     * @param target          : target of the content file
     * @param submoduleGitUrl : submodule git url of the content file
     **/
    public ContentFile(String sha, String url, String type, Encoding encoding, double size, String name, String path,
                       String content, String gitUrl, String htmlUrl, String downloadUrl, Links links, String target,
                       String submoduleGitUrl) {
        super(sha, url);
        this.type = type;
        this.encoding = encoding;
        this.size = size;
        this.name = name;
        this.path = path;
        this.content = content;
        this.gitUrl = gitUrl;
        this.htmlUrl = htmlUrl;
        this.downloadUrl = downloadUrl;
        this.links = links;
        this.target = target;
        this.submoduleGitUrl = submoduleGitUrl;
    }

    /**
     * Constructor to init a {@link ContentFile}
     *
     * @param jContentFile : content file details as {@link JSONObject}
     **/
    public ContentFile(JSONObject jContentFile) {
        super(jContentFile);
        type = hResponse.getString("type");
        String sEncoding = hResponse.getString("encoding");
        if (sEncoding != null)
            encoding = Encoding.valueOf(sEncoding);
        else
            encoding = null;
        size = hResponse.getDouble("size");
        name = hResponse.getString("name");
        path = hResponse.getString("path");
        content = hResponse.getString("content");
        gitUrl = hResponse.getString("git_url");
        htmlUrl = hResponse.getString("html_url");
        downloadUrl = hResponse.getString("download_url");
        JSONObject jLinks = hResponse.getJSONObject("_links");
        if (jLinks != null)
            links = new Links(jLinks);
        else
            links = null;
        target = hResponse.getString("target");
        submoduleGitUrl = hResponse.getString("submodule_git_url");
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
     * Method to get {@link #encoding} instance <br>
     * No-any params required
     *
     * @return {@link #encoding} instance as {@link Encoding}
     **/
    public Encoding getEncoding() {
        return encoding;
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
     * Method to get {@link #content} instance <br>
     * No-any params required
     *
     * @return {@link #content} instance as {@link String}
     **/
    public String getContent() {
        return content;
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
     * Method to get {@link #htmlUrl} instance <br>
     * No-any params required
     *
     * @return {@link #htmlUrl} instance as {@link String}
     **/
    public String getHtmlUrl() {
        return htmlUrl;
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
     * Method to get {@link #links} instance <br>
     * No-any params required
     *
     * @return {@link #links} instance as {@link Links}
     **/
    public Links getLinks() {
        return links;
    }

    /**
     * Method to get {@link #target} instance <br>
     * No-any params required
     *
     * @return {@link #target} instance as {@link String}
     **/
    public String getTarget() {
        return target;
    }

    /**
     * Method to get {@link #submoduleGitUrl} instance <br>
     * No-any params required
     *
     * @return {@link #submoduleGitUrl} instance as {@link String}
     **/
    public String getSubmoduleGitUrl() {
        return submoduleGitUrl;
    }

}
