package com.tecknobit.githubmanager.packages.records;

import com.tecknobit.githubmanager.records.parents.BaseResponseDetails;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.User;
import com.tecknobit.githubmanager.records.repository.CompleteRepository;
import com.tecknobit.githubmanager.records.repository.CompleteRepository.RepoVisibility;
import org.json.JSONObject;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code GitHubPackage} class is useful to format a GitHub's package
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/packages#list-packages-for-an-organization">
 *             List packages for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/packages#get-a-package-for-an-organization">
 *             Get a package for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/packages#list-packages-for-the-authenticated-users-namespace">
 *             List packages for the authenticated user's namespace</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/packages#get-a-package-for-the-authenticated-user">
 *             Get a package for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/packages#list-packages-for-a-user">
 *             List packages for a user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/packagesa#get-a-package-for-a-user">
 *             Get a package for a user</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see BaseResponseDetails
 **/
public class GitHubPackage extends BaseResponseDetails {

    /**
     * {@code PackageType} list of available package types
     **/
    public enum PackageType {

        /**
         * {@code npm} package type
         **/
        npm,

        /**
         * {@code maven} package type
         **/
        maven,

        /**
         * {@code rubygems} package type
         **/
        rubygems,

        /**
         * {@code docker} package type
         **/
        docker,

        /**
         * {@code nuget} package type
         **/
        nuget,

        /**
         * {@code container} package type
         **/
        container

    }

    /**
     * {@code packageType} type of the package
     **/
    private final PackageType packageType;

    /**
     * {@code htmlUrl} html url of the package
     **/
    private final String htmlUrl;

    /**
     * {@code container} the number of versions of the package
     **/
    private final int versionCount;

    /**
     * {@code visibility} of the package
     **/
    private final RepoVisibility visibility;

    /**
     * {@code owner} of the package
     **/
    private final User owner;

    /**
     * {@code repository} of the package
     **/
    private final CompleteRepository repository;

    /**
     * {@code createdAt} creation date of the package
     **/
    private final String createdAt;

    /**
     * {@code updatedAt} update date of the package
     **/
    private final String updatedAt;

    /**
     * Constructor to init a {@link GitHubPackage}
     *
     * @param id           : identifier value
     * @param name         : the name of the item
     * @param url          : url value
     * @param packageType  : type of the package
     * @param htmlUrl      : html url of the package
     * @param versionCount : the number of versions of the package
     * @param visibility   : visibility of the package
     * @param owner        : owner of the package
     * @param repository   : repository of the package
     * @param createdAt    : creation date of the package
     * @param updatedAt    : update date of the package
     **/
    public GitHubPackage(long id, String name, String url, PackageType packageType, String htmlUrl, int versionCount,
                         RepoVisibility visibility, User owner, CompleteRepository repository, String createdAt,
                         String updatedAt) {
        super(id, name, url);
        this.packageType = packageType;
        this.htmlUrl = htmlUrl;
        this.versionCount = versionCount;
        this.visibility = visibility;
        this.owner = owner;
        this.repository = repository;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Constructor to init a {@link GitHubPackage}
     *
     * @param jGitHubPackage : GitHub package details as {@link JSONObject}
     **/
    public GitHubPackage(JSONObject jGitHubPackage) {
        super(jGitHubPackage);
        packageType = PackageType.valueOf(hResponse.getString("package_type"));
        htmlUrl = hResponse.getString("html_url");
        versionCount = hResponse.getInt("version_count", 0);
        visibility = RepoVisibility.reachEnumConstant(hResponse.getString("visibility"));
        JSONObject jObject = hResponse.getJSONObject("owner");
        if (jObject != null)
            owner = new User(jObject);
        else
            owner = null;
        jObject = hResponse.getJSONObject("repository");
        if (jObject != null)
            repository = new CompleteRepository(jObject);
        else
            repository = null;
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
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
     * Method to get {@link #htmlUrl} instance <br>
     * No-any params required
     *
     * @return {@link #htmlUrl} instance as {@link String}
     **/
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * Method to get {@link #versionCount} instance <br>
     * No-any params required
     *
     * @return {@link #versionCount} instance as int
     **/
    public int getVersionCount() {
        return versionCount;
    }

    /**
     * Method to get {@link #visibility} instance <br>
     * No-any params required
     *
     * @return {@link #visibility} instance as {@link RepoVisibility}
     **/
    public RepoVisibility getVisibility() {
        return visibility;
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
     * Method to get {@link #repository} instance <br>
     * No-any params required
     *
     * @return {@link #repository} instance as {@link CompleteRepository}
     **/
    public CompleteRepository getRepository() {
        return repository;
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

}
