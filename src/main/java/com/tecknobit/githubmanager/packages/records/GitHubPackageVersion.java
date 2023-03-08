package com.tecknobit.githubmanager.packages.records;

import com.tecknobit.githubmanager.packages.records.GitHubPackage.PackageType;
import com.tecknobit.githubmanager.records.parents.BaseResponseDetails;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code GitHubPackageVersion} class is useful to format a GitHub's package version
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-an-organization">
 *             List package versions for a package owned by an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/packages#get-a-package-version-for-an-organization">
 *             Get a package version for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-an-organization">
 *             List package versions for a package owned by an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/packages#get-a-package-version-for-an-organization">
 *             Get a package version for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-a-user">
 *             List package versions for a package owned by a user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/packages#get-a-package-version-for-a-user">
 *             Get a package version for a user</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see BaseResponseDetails
 **/
public class GitHubPackageVersion extends BaseResponseDetails {

    /**
     * {@code packageHtmlUrl} package html url of the package version
     **/
    private final String packageHtmlUrl;

    /**
     * {@code packageType} html url of the package version
     **/
    private final String htmlUrl;

    /**
     * {@code license} of the package version
     **/
    private final String license;

    /**
     * {@code createdAt} creation date of the package version
     **/
    private final String createdAt;

    /**
     * {@code updatedAt} update date of the package version
     **/
    private final String updatedAt;

    /**
     * {@code deletedAt} deletion date of the package version
     **/
    private final String deletedAt;

    /**
     * {@code metadata} of the package version
     **/
    private final Metadata metadata;

    /**
     * Constructor to init a {@link GitHubPackageVersion}
     *
     * @param id             : identifier value
     * @param name           : the name of the item
     * @param url            : url value
     * @param packageHtmlUrl : package html url of the package version
     * @param htmlUrl        : html url of the package version
     * @param license        : license of the package version
     * @param createdAt      : creation date of the package version
     * @param updatedAt      : update date of the package version
     * @param deletedAt      : deletion date of the package version
     * @param metadata       : metadata of the package version
     **/
    public GitHubPackageVersion(long id, String name, String url, String packageHtmlUrl, String htmlUrl, String license,
                                String createdAt, String updatedAt, String deletedAt, Metadata metadata) {
        super(id, name, url);
        this.packageHtmlUrl = packageHtmlUrl;
        this.htmlUrl = htmlUrl;
        this.license = license;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.metadata = metadata;
    }

    /**
     * Constructor to init a {@link GitHubPackageVersion}
     *
     * @param jGitHubPackageVersion : GitHub package version details as {@link JSONObject}
     **/
    public GitHubPackageVersion(JSONObject jGitHubPackageVersion) {
        super(jGitHubPackageVersion);
        packageHtmlUrl = hResponse.getString("package_html_url");
        htmlUrl = hResponse.getString("html_url");
        license = hResponse.getString("license");
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
        deletedAt = hResponse.getString("deleted_at");
        JSONObject jMetadata = hResponse.getJSONObject("metadata");
        if (jMetadata != null)
            metadata = new Metadata(jMetadata);
        else
            metadata = null;
    }

    /**
     * Method to get {@link #packageHtmlUrl} instance <br>
     * No-any params required
     *
     * @return {@link #packageHtmlUrl} instance as {@link String}
     **/
    public String getPackageHtmlUrl() {
        return packageHtmlUrl;
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
     * Method to get {@link #license} instance <br>
     * No-any params required
     *
     * @return {@link #license} instance as {@link String}
     **/
    public String getLicense() {
        return license;
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
     * Method to get {@link #updatedAt} instance <br>
     * No-any params required
     *
     * @return {@link #updatedAt} instance as {@link String}
     **/
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Method to get {@link #updatedAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #updatedAt} timestamp as long
     **/
    public long getUpdatedAtTimestamp() {
        return getDateTimestamp(updatedAt);
    }

    /**
     * Method to get {@link #deletedAt} instance <br>
     * No-any params required
     *
     * @return {@link #deletedAt} instance as {@link String}
     **/
    public String getDeletedAt() {
        return deletedAt;
    }

    /**
     * Method to get {@link #deletedAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #deletedAt} timestamp as long
     **/
    public long getDeletedAtTimestamp() {
        return getDateTimestamp(deletedAt);
    }

    /**
     * Method to get {@link #metadata} instance <br>
     * No-any params required
     *
     * @return {@link #metadata} instance as {@link Metadata}
     **/
    public Metadata getMetadata() {
        return metadata;
    }

    /**
     * The {@code Metadata} class is useful to format a GitHub's metadata for a package version
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class Metadata extends InnerClassItem {

        /**
         * {@code packageType} package type of the metadata
         **/
        private final PackageType packageType;

        /**
         * {@code containerTags} list of container tags
         **/
        private final ArrayList<String> containerTags;

        /**
         * {@code dockerTags} list of docker tags
         **/
        private final ArrayList<String> dockerTags;

        /**
         * Constructor to init a {@link Metadata}
         *
         * @param packageType   : package type of the metadata
         * @param containerTags : list of container tags
         * @param dockerTags    : list of docker tags
         **/
        public Metadata(PackageType packageType, ArrayList<String> containerTags, ArrayList<String> dockerTags) {
            super(null);
            this.packageType = packageType;
            this.containerTags = containerTags;
            this.dockerTags = dockerTags;
        }

        /**
         * Constructor to init a {@link Metadata}
         *
         * @param jMetadata : metadata details as {@link JSONObject}
         **/
        public Metadata(JSONObject jMetadata) {
            super(jMetadata);
            packageType = PackageType.valueOf(hItem.getString("package_type"));
            hItem.setJSONObjectSource(hItem.getJSONObject("container", new JSONObject()));
            containerTags = returnStringsList(hItem.getJSONArray("tags"));
            hItem.setJSONObjectSource(hItem.getJSONObject("docker", new JSONObject()));
            dockerTags = returnStringsList(hItem.getJSONArray("tags"));
        }

        /**
         * Method to get {@link #packageType} instance <br>
         * No-any params required
         *
         * @return {@link #packageType} instance as {@link PackageType}
         **/
        public PackageType getPackageType() {
            return packageType;
        }

        /**
         * Method to get {@link #containerTags} instance <br>
         * No-any params required
         *
         * @return {@link #containerTags} instance as {@link ArrayList} of {@link String}
         **/
        public ArrayList<String> getContainerTags() {
            return containerTags;
        }

        /**
         * Method to get {@link #dockerTags} instance <br>
         * No-any params required
         *
         * @return {@link #dockerTags} instance as {@link ArrayList} of {@link String}
         **/
        public ArrayList<String> getDockerTags() {
            return dockerTags;
        }

    }

}
