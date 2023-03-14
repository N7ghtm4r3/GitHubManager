package com.tecknobit.githubmanager.releases.releases.records;

import com.tecknobit.githubmanager.reactions.records.Reactions;
import com.tecknobit.githubmanager.records.parents.BaseResponseDetails;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.User;
import com.tecknobit.githubmanager.releases.releaseassets.records.ReleaseAsset;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;
import static com.tecknobit.githubmanager.releases.releaseassets.records.ReleaseAsset.returnReleaseAssets;

/**
 * The {@code Release} class is useful to format a GitHub's release
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/releases/releases#list-releases">
 *             List releases</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/releases/releases#create-a-release">
 *             Create a release</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/releases/releases#get-the-latest-release">
 *             Get the latest release</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/releases/releases#get-a-release-by-tag-name">
 *             Get a release by tag name</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/releases/releases#get-a-release">
 *             Get a release</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/releases/releases#update-a-release">
 *             Update a release</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see BaseResponseDetails
 **/
public class Release extends BaseResponseDetails {

    /**
     * {@code htmlUrl} html url of the release
     **/
    private final String htmlUrl;

    /**
     * {@code assetsUrl} assets url of the release
     **/
    private final String assetsUrl;

    /**
     * {@code uploadUrl} upload url of the release
     **/
    private final String uploadUrl;

    /**
     * {@code tarballUrl} tarball url of the release
     **/
    private final String tarballUrl;

    /**
     * {@code zipballUrl} zipball url of the release
     **/
    private final String zipballUrl;

    /**
     * {@code nodeId} node id of the release
     **/
    private final String nodeId;

    /**
     * {@code tagName} the name of the tag
     **/
    private final String tagName;

    /**
     * {@code targetCommitish} specifies the commitish value that determines where the Git tag is created from
     **/
    private final String targetCommitish;

    /**
     * {@code body} of the release
     **/
    private final String body;

    /**
     * {@code draft} {@code "true"} to create a draft (unpublished) release, {@code "false"} to create a published one
     **/
    private final boolean draft;

    /**
     * {@code prerelease} whether to identify the release as a prerelease or a full release
     **/
    private final boolean prerelease;

    /**
     * {@code createdAt} creation time of the release
     **/
    private final String createdAt;

    /**
     * {@code publishedAt} publish time of the release
     **/
    private final String publishedAt;

    /**
     * {@code author} of the release
     **/
    private final User author;

    /**
     * {@code assets} of the release
     **/
    private final ArrayList<ReleaseAsset> assets;

    /**
     * {@code bodyHtml} body html of the release
     **/
    private final String bodyHtml;

    /**
     * {@code bodyText} body text of the release
     **/
    private final String bodyText;

    /**
     * {@code mentionsCount} mentions count of the release
     **/
    private final int mentionsCount;

    /**
     * {@code discussionUrl} discussion url of the release
     **/
    private final String discussionUrl;

    /**
     * {@code reactions} of the release
     **/
    private final Reactions reactions;

    /**
     * Constructor to init a {@link Release}
     *
     * @param id              : id of the release
     * @param name            : name of the release
     * @param url             : url of the release
     * @param htmlUrl         : html url of the release
     * @param assetsUrl       : assets url of the release
     * @param uploadUrl       : upload url of the release
     * @param tarballUrl      : tarball url of the release
     * @param zipballUrl      : zipball of the release
     * @param nodeId          : node id of the release
     * @param tagName         : the name of the tag
     * @param targetCommitish : specifies the commitish value that determines where the Git tag is created from
     * @param body            : body of the release
     * @param draft           : {@code "true"} to create a draft (unpublished) release, {@code "false"} to create a published one
     * @param prerelease      : whether to identify the release as a prerelease or a full release
     * @param createdAt       : creation time of the release
     * @param publishedAt     : publish time of the release
     * @param author          : author of the release
     * @param assets          : assets of the release
     * @param bodyHtml        : body html of the release
     * @param bodyText        : body text of the release
     * @param mentionsCount   : mentions count of the release
     * @param discussionUrl   : discussion url of the release
     * @param reactions       : reactions of the release
     **/
    public Release(long id, String name, String url, String htmlUrl, String assetsUrl, String uploadUrl, String tarballUrl,
                   String zipballUrl, String nodeId, String tagName, String targetCommitish, String body, boolean draft,
                   boolean prerelease, String createdAt, String publishedAt, User author, ArrayList<ReleaseAsset> assets,
                   String bodyHtml, String bodyText, int mentionsCount, String discussionUrl, Reactions reactions) {
        super(id, name, url);
        this.htmlUrl = htmlUrl;
        this.assetsUrl = assetsUrl;
        this.uploadUrl = uploadUrl;
        this.tarballUrl = tarballUrl;
        this.zipballUrl = zipballUrl;
        this.nodeId = nodeId;
        this.tagName = tagName;
        this.targetCommitish = targetCommitish;
        this.body = body;
        this.draft = draft;
        this.prerelease = prerelease;
        this.createdAt = createdAt;
        this.publishedAt = publishedAt;
        this.author = author;
        this.assets = assets;
        this.bodyHtml = bodyHtml;
        this.bodyText = bodyText;
        this.mentionsCount = mentionsCount;
        this.discussionUrl = discussionUrl;
        this.reactions = reactions;
    }

    /**
     * Constructor to init a {@link Release}
     *
     * @param jRelease : release details as {@link JSONObject}
     **/
    public Release(JSONObject jRelease) {
        super(jRelease);
        htmlUrl = hResponse.getString("html_url");
        assetsUrl = hResponse.getString("assets_url");
        uploadUrl = hResponse.getString("upload_url");
        tarballUrl = hResponse.getString("tarball_url");
        zipballUrl = hResponse.getString("zipball_url");
        nodeId = hResponse.getString("node_id");
        tagName = hResponse.getString("tag_name");
        targetCommitish = hResponse.getString("target_commitish");
        body = hResponse.getString("body");
        draft = hResponse.getBoolean("draft");
        prerelease = hResponse.getBoolean("prerelease");
        createdAt = hResponse.getString("created_at");
        publishedAt = hResponse.getString("published_at");
        author = new User(hResponse.getJSONObject("author"));
        assets = returnReleaseAssets(hResponse.getJSONArray("assets"));
        bodyHtml = hResponse.getString("body_html");
        bodyText = hResponse.getString("body_text");
        mentionsCount = hResponse.getInt("mentions_count", 0);
        discussionUrl = hResponse.getString("discussion_url");
        JSONObject jReactions = hResponse.getJSONObject("reactions");
        if (jReactions != null)
            reactions = new Reactions(jReactions);
        else
            reactions = null;
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
     * Method to get {@link #assetsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #assetsUrl} instance as {@link String}
     **/
    public String getAssetsUrl() {
        return assetsUrl;
    }

    /**
     * Method to get {@link #uploadUrl} instance <br>
     * No-any params required
     *
     * @return {@link #uploadUrl} instance as {@link String}
     **/
    public String getUploadUrl() {
        return uploadUrl;
    }

    /**
     * Method to get {@link #tarballUrl} instance <br>
     * No-any params required
     *
     * @return {@link #tarballUrl} instance as {@link String}
     **/
    public String getTarballUrl() {
        return tarballUrl;
    }

    /**
     * Method to get {@link #zipballUrl} instance <br>
     * No-any params required
     *
     * @return {@link #zipballUrl} instance as {@link String}
     **/
    public String getZipballUrl() {
        return zipballUrl;
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
     * Method to get {@link #tagName} instance <br>
     * No-any params required
     *
     * @return {@link #tagName} instance as {@link String}
     **/
    public String getTagName() {
        return tagName;
    }

    /**
     * Method to get {@link #targetCommitish} instance <br>
     * No-any params required
     *
     * @return {@link #targetCommitish} instance as {@link String}
     **/
    public String getTargetCommitish() {
        return targetCommitish;
    }

    /**
     * Method to get {@link #body} instance <br>
     * No-any params required
     *
     * @return {@link #body} instance as {@link String}
     **/
    public String getBody() {
        return body;
    }

    /**
     * Method to get {@link #draft} instance <br>
     * No-any params required
     *
     * @return {@link #draft} instance as boolean
     **/
    public boolean isDraft() {
        return draft;
    }

    /**
     * Method to get {@link #prerelease} instance <br>
     * No-any params required
     *
     * @return {@link #prerelease} instance as boolean
     **/
    public boolean isPrerelease() {
        return prerelease;
    }

    /**
     * Method to get {@link #createdAt} instance <br>
     * No-any params required
     *
     * @return {@link #createdAt} instance as {@link String}
     **/
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Method to get {@link #createdAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #createdAt} timestamp as long
     **/
    public long getCreatedAtTimestamp() {
        return getDateTimestamp(createdAt);
    }

    /**
     * Method to get {@link #publishedAt} instance <br>
     * No-any params required
     *
     * @return {@link #publishedAt} instance as {@link String}
     **/
    public String getPublishedAt() {
        return publishedAt;
    }

    /**
     * Method to get {@link #publishedAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #publishedAt} timestamp as long
     **/
    public long getPublishedAtTimestamp() {
        return getDateTimestamp(publishedAt);
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
     * Method to get {@link #assets} instance <br>
     * No-any params required
     *
     * @return {@link #assets} instance as {@link ArrayList} of {@link ReleaseAsset}
     **/
    public ArrayList<ReleaseAsset> getAssets() {
        return assets;
    }

    /**
     * Method to get {@link #bodyHtml} instance <br>
     * No-any params required
     *
     * @return {@link #bodyHtml} instance as {@link String}
     **/
    public String getBodyHtml() {
        return bodyHtml;
    }

    /**
     * Method to get {@link #bodyText} instance <br>
     * No-any params required
     *
     * @return {@link #bodyText} instance as {@link String}
     **/
    public String getBodyText() {
        return bodyText;
    }

    /**
     * Method to get {@link #mentionsCount} instance <br>
     * No-any params required
     *
     * @return {@link #mentionsCount} instance as int
     **/
    public int getMentionsCount() {
        return mentionsCount;
    }

    /**
     * Method to get {@link #discussionUrl} instance <br>
     * No-any params required
     *
     * @return {@link #discussionUrl} instance as {@link String}
     **/
    public String getDiscussionUrl() {
        return discussionUrl;
    }

    /**
     * Method to get {@link #reactions} instance <br>
     * No-any params required
     *
     * @return {@link #reactions} instance as {@link Reactions}
     **/
    public Reactions getReactions() {
        return reactions;
    }

}
