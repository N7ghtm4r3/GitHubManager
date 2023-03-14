package com.tecknobit.githubmanager.packages;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.organizations.organizations.records.Organization;
import com.tecknobit.githubmanager.packages.records.GitHubPackage;
import com.tecknobit.githubmanager.packages.records.GitHubPackage.PackageType;
import com.tecknobit.githubmanager.packages.records.GitHubPackageVersion;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.meta.GitHubMetaManager.VERSIONS_PATH;
import static com.tecknobit.githubmanager.repositories.repositories.records.CompleteRepository.RepoVisibility;

/**
 * The {@code GitHubPackagesManager} class is useful to manage all GitHub's package managers endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages">
 * Packages</a>
 * @see GitHubManager
 **/
public class GitHubPackagesManager extends GitHubManager {

    /**
     * {@code PACKAGES_PATH} constant for {@code "/packages"} path
     **/
    public static final String PACKAGES_PATH = "/packages";

    /**
     * {@code RESTORE_PATH} constant for {@code "/restore"} path
     **/
    public static final String RESTORE_PATH = "/restore";

    /**
     * {@code VERSIONS_QUERY_PATH} constant for {@code "/versions"} path
     **/
    public static final String VERSIONS_QUERY_PATH = "/" + VERSIONS_PATH;

    /**
     * {@code USER_PACKAGES_PATH} constant for {@code "/user/packages"} path
     **/
    public static final String USER_PACKAGES_PATH = USER_PATH + PACKAGES_PATH;

    /**
     * Constructor to init a {@link GitHubPackagesManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubPackagesManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubPackagesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubPackagesManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubPackagesManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubPackagesManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubPackagesManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubPackagesManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubPackagesManager} <br>
     * No-any params required
     *
     * @throws IllegalArgumentException when a parameterized constructor has not been called before this constructor
     * @apiNote this constructor is useful to instantiate a new {@link GitHubManager}'s manager without re-insert
     * the credentials and is useful in those cases if you need to use different manager at the same time:
     * <pre>
     *     {@code
     *        //You need to insert all credentials requested
     *        GitHubManager firstManager = new GitHubManager("accessToken");
     *        //You don't need to insert all credentials to make manager work
     *        GitHubManager secondManager = new GitHubManager(); //same credentials used
     *     }
     * </pre>
     **/
    public GitHubPackagesManager() {
        super();
    }

    /**
     * Method to get the list of the packages in an organization readable by the user. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param org:         the organization from fetch the list
     * @param packageType: type of supported package
     * @return packages list as {@link ArrayList} of {@link GitHubPackage} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-packages-for-an-organization">
     * List packages for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/packages")
    public ArrayList<GitHubPackage> getOrganizationPackages(Organization org, PackageType packageType) throws IOException {
        return getOrganizationPackages(org.getLogin(), packageType, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the packages in an organization readable by the user. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param org:         the organization from fetch the list
     * @param packageType: type of supported package
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return packages list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-packages-for-an-organization">
     * List packages for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/packages")
    public <T> T getOrganizationPackages(Organization org, PackageType packageType, ReturnFormat format) throws IOException {
        return getOrganizationPackages(org.getLogin(), packageType, format);
    }

    /**
     * Method to get the list of the packages in an organization readable by the user. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param packageType: type of supported package
     * @return packages list as {@link ArrayList} of {@link GitHubPackage} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-packages-for-an-organization">
     * List packages for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/packages")
    public ArrayList<GitHubPackage> getOrganizationPackages(String org, PackageType packageType) throws IOException {
        return getOrganizationPackages(org, packageType, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the packages in an organization readable by the user. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param packageType: type of supported package
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return packages list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-packages-for-an-organization">
     * List packages for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/packages")
    public <T> T getOrganizationPackages(String org, PackageType packageType, ReturnFormat format) throws IOException {
        return getOrganizationPackages(org, packageType, null, format);
    }

    /**
     * Method to get the list of the packages in an organization readable by the user. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param org:         the organization from fetch the list
     * @param packageType: type of supported package
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "visibility"} -> the selected visibility of the packages. This parameter is
     *                            optional and only filters an existing result set. The internal visibility is only
     *                            supported for GitHub Packages registries that allow for granular permissions. For other
     *                            ecosystems internal is synonymous with private. For the list of GitHub Packages registries
     *                            that support granular permissions, see "About permissions for GitHub Packages." - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return packages list as {@link ArrayList} of {@link GitHubPackage} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-packages-for-an-organization">
     * List packages for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/packages")
    public ArrayList<GitHubPackage> getOrganizationPackages(Organization org, PackageType packageType,
                                                            Params queryParams) throws IOException {
        return getOrganizationPackages(org.getLogin(), packageType, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the packages in an organization readable by the user. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param org:         the organization from fetch the list
     * @param packageType: type of supported package
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "visibility"} -> the selected visibility of the packages. This parameter is
     *                            optional and only filters an existing result set. The internal visibility is only
     *                            supported for GitHub Packages registries that allow for granular permissions. For other
     *                            ecosystems internal is synonymous with private. For the list of GitHub Packages registries
     *                            that support granular permissions, see "About permissions for GitHub Packages." - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return packages list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-packages-for-an-organization">
     * List packages for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/packages")
    public <T> T getOrganizationPackages(Organization org, PackageType packageType, Params queryParams,
                                         ReturnFormat format) throws IOException {
        return getOrganizationPackages(org.getLogin(), packageType, queryParams, format);
    }

    /**
     * Method to get the list of the packages in an organization readable by the user. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param packageType: type of supported package
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "visibility"} -> the selected visibility of the packages. This parameter is
     *                            optional and only filters an existing result set. The internal visibility is only
     *                            supported for GitHub Packages registries that allow for granular permissions. For other
     *                            ecosystems internal is synonymous with private. For the list of GitHub Packages registries
     *                            that support granular permissions, see "About permissions for GitHub Packages." - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return packages list as {@link ArrayList} of {@link GitHubPackage} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-packages-for-an-organization">
     * List packages for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/packages")
    public ArrayList<GitHubPackage> getOrganizationPackages(String org, PackageType packageType,
                                                            Params queryParams) throws IOException {
        return getOrganizationPackages(org, packageType, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the packages in an organization readable by the user. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param packageType: type of supported package
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "visibility"} -> the selected visibility of the packages. This parameter is
     *                            optional and only filters an existing result set. The internal visibility is only
     *                            supported for GitHub Packages registries that allow for granular permissions. For other
     *                            ecosystems internal is synonymous with private. For the list of GitHub Packages registries
     *                            that support granular permissions, see "About permissions for GitHub Packages." - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return packages list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-packages-for-an-organization">
     * List packages for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/packages")
    public <T> T getOrganizationPackages(String org, PackageType packageType, Params queryParams,
                                         ReturnFormat format) throws IOException {
        if (queryParams == null)
            queryParams = new Params();
        queryParams.addParam("package_type", packageType);
        return returnPackages(sendGetRequest(ORGS_PATH + org + PACKAGES_PATH + queryParams.createQueryString()),
                format);
    }

    /**
     * Method to get a specific package in an organization <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @param org:         the organization from fetch the package
     * @return package as {@link GitHubPackage} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#get-a-package-for-an-organization">
     * Get a package for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/packages/{package_type}/{package_name}")
    public GitHubPackage getOrganizationPackage(PackageType packageType, String packageName,
                                                Organization org) throws IOException {
        return getOrganizationPackage(packageType, packageName, org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific package in an organization <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @param org:         the organization from fetch the package
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return package as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#get-a-package-for-an-organization">
     * Get a package for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/packages/{package_type}/{package_name}")
    public <T> T getOrganizationPackage(PackageType packageType, String packageName, Organization org,
                                        ReturnFormat format) throws IOException {
        return getOrganizationPackage(packageType, packageName, org.getLogin(), format);
    }

    /**
     * Method to get a specific package in an organization <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @param org:         the organization name. The name is not case-sensitive
     * @return package as {@link GitHubPackage} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#get-a-package-for-an-organization">
     * Get a package for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/packages/{package_type}/{package_name}")
    public GitHubPackage getOrganizationPackage(PackageType packageType, String packageName, String org) throws IOException {
        return getOrganizationPackage(packageType, packageName, org, LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific package in an organization <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @param org:         the organization name. The name is not case-sensitive
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return package as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#get-a-package-for-an-organization">
     * Get a package for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/packages/{package_type}/{package_name}")
    public <T> T getOrganizationPackage(PackageType packageType, String packageName, String org,
                                        ReturnFormat format) throws IOException {
        return returnPackage(sendGetRequest(ORGS_PATH + org + PACKAGES_PATH + "/" + packageType + "/"
                + packageName), format);
    }

    /**
     * Method to delete an entire package in an organization. You cannot delete a public package if any version of the
     * package has more than 5,000 downloads. In this scenario, contact GitHub support for further assistance.
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType: type of supported package
     * @param gPackage:    the package to delete
     * @param org:         the organization from delete the package
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#delete-a-package-for-an-organization">
     * Delete a package for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/packages/{package_type}/{package_name}")
    public boolean deleteOrganizationPackage(PackageType packageType, GitHubPackage gPackage, Organization org) {
        return deleteOrganizationPackage(packageType, gPackage.getName(), org.getLogin());
    }

    /**
     * Method to delete an entire package in an organization. You cannot delete a public package if any version of the
     * package has more than 5,000 downloads. In this scenario, contact GitHub support for further assistance.
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @param org:         the organization from delete the package
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#delete-a-package-for-an-organization">
     * Delete a package for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/packages/{package_type}/{package_name}")
    public boolean deleteOrganizationPackage(PackageType packageType, String packageName, Organization org) {
        return deleteOrganizationPackage(packageType, packageName, org.getLogin());
    }

    /**
     * Method to delete an entire package in an organization. You cannot delete a public package if any version of the
     * package has more than 5,000 downloads. In this scenario, contact GitHub support for further assistance.
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType: type of supported package
     * @param gPackage:    the package to delete
     * @param org:         the organization name. The name is not case-sensitive
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#delete-a-package-for-an-organization">
     * Delete a package for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/packages/{package_type}/{package_name}")
    public boolean deleteOrganizationPackage(PackageType packageType, GitHubPackage gPackage, String org) {
        return deleteOrganizationPackage(packageType, gPackage.getName(), org);
    }

    /**
     * Method to delete an entire package in an organization. You cannot delete a public package if any version of the
     * package has more than 5,000 downloads. In this scenario, contact GitHub support for further assistance.
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @param org:         the organization name. The name is not case-sensitive
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#delete-a-package-for-an-organization">
     * Delete a package for an organization</a>
     **/
    @RequestPath(method = DELETE, path = "/orgs/{org}/packages/{package_type}/{package_name}")
    public boolean deleteOrganizationPackage(PackageType packageType, String packageName, String org) {
        try {
            sendDeleteRequest(ORGS_PATH + org + PACKAGES_PATH + "/" + packageType + "/" + packageName);
            if (apiRequest.getResponseStatusCode() != 204) {
                printErrorResponse();
                return false;
            }
            return true;
        } catch (IOException e) {
            printErrorResponse();
            return false;
        }
    }

    /**
     * Method to restore an entire package in an organization. <br>
     * You can restore a deleted package under the following conditions:
     * <ul>
     *     <li>
     *         The package was deleted within the last 30 days.
     *     </li>
     *     <li>
     *         The same package namespace and version is still available and not reused for a new package. If the same
     *         package namespace is not available, you will not be able to restore your package. In this scenario, to
     *         restore the deleted package, you must delete the new package that uses the deleted package's namespace first.
     *     </li>
     * </ul>
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType: type of supported package
     * @param gPackage:    the package to restore
     * @param org:         the organization where restore the package
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#restore-a-package-for-an-organization">
     * Restore a package for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/packages/{package_type}/{package_name}/restore")
    public boolean restoreOrganizationPackage(PackageType packageType, GitHubPackage gPackage, Organization org) {
        return restoreOrganizationPackage(packageType, gPackage.getName(), org.getLogin());
    }

    /**
     * Method to restore an entire package in an organization. <br>
     * You can restore a deleted package under the following conditions:
     * <ul>
     *     <li>
     *         The package was deleted within the last 30 days.
     *     </li>
     *     <li>
     *         The same package namespace and version is still available and not reused for a new package. If the same
     *         package namespace is not available, you will not be able to restore your package. In this scenario, to
     *         restore the deleted package, you must delete the new package that uses the deleted package's namespace first.
     *     </li>
     * </ul>
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType: type of supported package
     * @param gPackage:    the package to restore
     * @param org:         the organization name. The name is not case-sensitive
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#restore-a-package-for-an-organization">
     * Restore a package for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/packages/{package_type}/{package_name}/restore")
    public boolean restoreOrganizationPackage(PackageType packageType, GitHubPackage gPackage, String org) {
        return restoreOrganizationPackage(packageType, gPackage.getName(), org);
    }

    /**
     * Method to restore an entire package in an organization. <br>
     * You can restore a deleted package under the following conditions:
     * <ul>
     *     <li>
     *         The package was deleted within the last 30 days.
     *     </li>
     *     <li>
     *         The same package namespace and version is still available and not reused for a new package. If the same
     *         package namespace is not available, you will not be able to restore your package. In this scenario, to
     *         restore the deleted package, you must delete the new package that uses the deleted package's namespace first.
     *     </li>
     * </ul>
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @param org:         the organization where restore the package
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#restore-a-package-for-an-organization">
     * Restore a package for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/packages/{package_type}/{package_name}/restore")
    public boolean restoreOrganizationPackage(PackageType packageType, String packageName, Organization org) {
        return restoreOrganizationPackage(packageType, packageName, org.getLogin());
    }

    /**
     * Method to restore an entire package in an organization. <br>
     * You can restore a deleted package under the following conditions:
     * <ul>
     *     <li>
     *         The package was deleted within the last 30 days.
     *     </li>
     *     <li>
     *         The same package namespace and version is still available and not reused for a new package. If the same
     *         package namespace is not available, you will not be able to restore your package. In this scenario, to
     *         restore the deleted package, you must delete the new package that uses the deleted package's namespace first.
     *     </li>
     * </ul>
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @param org:         the organization name. The name is not case-sensitive
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#restore-a-package-for-an-organization">
     * Restore a package for an organization</a>
     **/
    @RequestPath(method = POST, path = "/orgs/{org}/packages/{package_type}/{package_name}/restore")
    public boolean restoreOrganizationPackage(PackageType packageType, String packageName, String org) {
        return restoreOrganizationPackage(packageType, packageName, org, null);
    }

    /**
     * Method to restore an entire package in an organization. <br>
     * You can restore a deleted package under the following conditions:
     * <ul>
     *     <li>
     *         The package was deleted within the last 30 days.
     *     </li>
     *     <li>
     *         The same package namespace and version is still available and not reused for a new package. If the same
     *         package namespace is not available, you will not be able to restore your package. In this scenario, to
     *         restore the deleted package, you must delete the new package that uses the deleted package's namespace first.
     *     </li>
     * </ul>
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType: type of supported package
     * @param gPackage:    the package to restore
     * @param org:         the organization where restore the package
     * @param token:       package token
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#restore-a-package-for-an-organization">
     * Restore a package for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/packages/{package_type}/{package_name}/restore")
    public boolean restoreOrganizationPackage(PackageType packageType, GitHubPackage gPackage, Organization org,
                                              String token) {
        return restoreOrganizationPackage(packageType, gPackage.getName(), org.getLogin(), token);
    }

    /**
     * Method to restore an entire package in an organization. <br>
     * You can restore a deleted package under the following conditions:
     * <ul>
     *     <li>
     *         The package was deleted within the last 30 days.
     *     </li>
     *     <li>
     *         The same package namespace and version is still available and not reused for a new package. If the same
     *         package namespace is not available, you will not be able to restore your package. In this scenario, to
     *         restore the deleted package, you must delete the new package that uses the deleted package's namespace first.
     *     </li>
     * </ul>
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @param org:         the organization where restore the package
     * @param token:       package token
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#restore-a-package-for-an-organization">
     * Restore a package for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/packages/{package_type}/{package_name}/restore")
    public boolean restoreOrganizationPackage(PackageType packageType, String packageName, Organization org, String token) {
        return restoreOrganizationPackage(packageType, packageName, org.getLogin(), token);
    }

    /**
     * Method to restore an entire package in an organization. <br>
     * You can restore a deleted package under the following conditions:
     * <ul>
     *     <li>
     *         The package was deleted within the last 30 days.
     *     </li>
     *     <li>
     *         The same package namespace and version is still available and not reused for a new package. If the same
     *         package namespace is not available, you will not be able to restore your package. In this scenario, to
     *         restore the deleted package, you must delete the new package that uses the deleted package's namespace first.
     *     </li>
     * </ul>
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType: type of supported package
     * @param gPackage:    the package to restore
     * @param org:         the organization name. The name is not case-sensitive
     * @param token:       package token
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#restore-a-package-for-an-organization">
     * Restore a package for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/packages/{package_type}/{package_name}/restore")
    public boolean restoreOrganizationPackage(PackageType packageType, GitHubPackage gPackage, String org, String token) {
        return restoreOrganizationPackage(packageType, gPackage.getName(), org, token);
    }

    /**
     * Method to restore an entire package in an organization. <br>
     * You can restore a deleted package under the following conditions:
     * <ul>
     *     <li>
     *         The package was deleted within the last 30 days.
     *     </li>
     *     <li>
     *         The same package namespace and version is still available and not reused for a new package. If the same
     *         package namespace is not available, you will not be able to restore your package. In this scenario, to
     *         restore the deleted package, you must delete the new package that uses the deleted package's namespace first.
     *     </li>
     * </ul>
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @param org:         the organization name. The name is not case-sensitive
     * @param token:       package token
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#restore-a-package-for-an-organization">
     * Restore a package for an organization</a>
     **/
    @RequestPath(method = POST, path = "/orgs/{org}/packages/{package_type}/{package_name}/restore")
    public boolean restoreOrganizationPackage(PackageType packageType, String packageName, String org, String token) {
        try {
            String reqUrl = ORGS_PATH + org + PACKAGES_PATH + "/" + packageType + "/" + packageName + RESTORE_PATH;
            if (token != null)
                reqUrl += "?token=" + token;
            sendPostRequest(reqUrl, null);
            if (apiRequest.getResponseStatusCode() != 204) {
                printErrorResponse();
                return false;
            }
            return true;
        } catch (IOException e) {
            printErrorResponse();
            return false;
        }
    }

    /**
     * Method to get the list of the package versions for a package owned by an organization. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param gPackage:    the package from fetch the list
     * @param org:         the organization from fetch the list
     * @return package versions list as {@link ArrayList} of {@link GitHubPackageVersion} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-an-organization">
     * List package versions for a package owned by an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions")
    public ArrayList<GitHubPackageVersion> getOrganizationPackageVersions(PackageType packageType, GitHubPackage gPackage,
                                                                          Organization org) throws IOException {
        return getOrganizationPackageVersions(packageType, gPackage.getName(), org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the package versions for a package owned by an organization. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param gPackage:    the package from fetch the list
     * @param org:         the organization from fetch the list
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return package versions list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-an-organization">
     * List package versions for a package owned by an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions")
    public <T> T getOrganizationPackageVersions(PackageType packageType, Organization org, GitHubPackage gPackage,
                                                ReturnFormat format) throws IOException {
        return getOrganizationPackageVersions(packageType, gPackage.getName(), org.getLogin(), format);
    }

    /**
     * Method to get the list of the package versions for a package owned by an organization. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @param org:         the organization from fetch the list
     * @return package versions list as {@link ArrayList} of {@link GitHubPackageVersion} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-an-organization">
     * List package versions for a package owned by an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions")
    public ArrayList<GitHubPackageVersion> getOrganizationPackageVersions(PackageType packageType, String packageName,
                                                                          Organization org) throws IOException {
        return getOrganizationPackageVersions(packageType, packageName, org.getLogin(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the package versions for a package owned by an organization. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @param org:         the organization from fetch the list
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return package versions list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-an-organization">
     * List package versions for a package owned by an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions")
    public <T> T getOrganizationPackageVersions(PackageType packageType, Organization org, String packageName,
                                                ReturnFormat format) throws IOException {
        return getOrganizationPackageVersions(packageType, packageName, org.getLogin(), format);
    }

    /**
     * Method to get the list of the package versions for a package owned by an organization. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param gPackage:    the package from fetch the list
     * @param org:         the organization name. The name is not case-sensitive
     * @return package versions list as {@link ArrayList} of {@link GitHubPackageVersion} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-an-organization">
     * List package versions for a package owned by an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions")
    public ArrayList<GitHubPackageVersion> getOrganizationPackageVersions(PackageType packageType, GitHubPackage gPackage,
                                                                          String org) throws IOException {
        return getOrganizationPackageVersions(packageType, gPackage.getName(), org, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the package versions for a package owned by an organization. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param gPackage:    the package from fetch the list
     * @param org:         the organization name. The name is not case-sensitive
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return package versions list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-an-organization">
     * List package versions for a package owned by an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions")
    public <T> T getOrganizationPackageVersions(PackageType packageType, String org, GitHubPackage gPackage,
                                                ReturnFormat format) throws IOException {
        return getOrganizationPackageVersions(packageType, gPackage.getName(), org, format);
    }

    /**
     * Method to get the list of the package versions for a package owned by an organization. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @param org:         the organization name. The name is not case-sensitive
     * @return package versions list as {@link ArrayList} of {@link GitHubPackageVersion} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-an-organization">
     * List package versions for a package owned by an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions")
    public ArrayList<GitHubPackageVersion> getOrganizationPackageVersions(PackageType packageType, String packageName,
                                                                          String org) throws IOException {
        return getOrganizationPackageVersions(packageType, packageName, org, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the package versions for a package owned by an organization. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @param org:         the organization name. The name is not case-sensitive
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return package versions list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-an-organization">
     * List package versions for a package owned by an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions")
    public <T> T getOrganizationPackageVersions(PackageType packageType, String packageName, String org,
                                                ReturnFormat format) throws IOException {
        return returnPackageVersions(sendGetRequest(ORGS_PATH + org + PACKAGES_PATH + "/" + packageType + "/"
                + packageName + VERSIONS_QUERY_PATH), format);
    }

    /**
     * Method to get the list of the package versions for a package owned by an organization. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param org:         the organization from fetch the list
     * @param gPackage:    the package from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> the state of the package, either active or deleted
     *                            - [string, default active]
     *                        </li>
     *                     </ul>
     * @return package versions list as {@link ArrayList} of {@link GitHubPackageVersion} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-an-organization">
     * List package versions for a package owned by an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions")
    public ArrayList<GitHubPackageVersion> getOrganizationPackageVersions(PackageType packageType, Organization org,
                                                                          GitHubPackage gPackage,
                                                                          Params queryParams) throws IOException {
        return getOrganizationPackageVersions(packageType, gPackage.getName(), org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the package versions for a package owned by an organization. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param org:         the organization from fetch the list
     * @param gPackage:    the package from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> the state of the package, either active or deleted
     *                            - [string, default active]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return package versions list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-an-organization">
     * List package versions for a package owned by an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions")
    public <T> T getOrganizationPackageVersions(PackageType packageType, Organization org, GitHubPackage gPackage,
                                                Params queryParams, ReturnFormat format) throws IOException {
        return getOrganizationPackageVersions(packageType, gPackage.getName(), org.getLogin(), queryParams, format);
    }

    /**
     * Method to get the list of the package versions for a package owned by an organization. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param org:         the organization from fetch the list
     * @param packageName: the name of the package
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> the state of the package, either active or deleted
     *                            - [string, default active]
     *                        </li>
     *                     </ul>
     * @return package versions list as {@link ArrayList} of {@link GitHubPackageVersion} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-an-organization">
     * List package versions for a package owned by an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions")
    public ArrayList<GitHubPackageVersion> getOrganizationPackageVersions(PackageType packageType, Organization org,
                                                                          String packageName, Params queryParams) throws IOException {
        return getOrganizationPackageVersions(packageType, packageName, org.getLogin(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the package versions for a package owned by an organization. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param org:         the organization from fetch the list
     * @param packageName: the name of the package
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> the state of the package, either active or deleted
     *                            - [string, default active]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return package versions list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-an-organization">
     * List package versions for a package owned by an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions")
    public <T> T getOrganizationPackageVersions(PackageType packageType, Organization org, String packageName,
                                                Params queryParams, ReturnFormat format) throws IOException {
        return getOrganizationPackageVersions(packageType, packageName, org.getLogin(), queryParams, format);
    }

    /**
     * Method to get the list of the package versions for a package owned by an organization. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param org:         the organization name. The name is not case-sensitive
     * @param gPackage:    the package from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> the state of the package, either active or deleted
     *                            - [string, default active]
     *                        </li>
     *                     </ul>
     * @return package versions list as {@link ArrayList} of {@link GitHubPackageVersion} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-an-organization">
     * List package versions for a package owned by an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions")
    public ArrayList<GitHubPackageVersion> getOrganizationPackageVersions(PackageType packageType, String org,
                                                                          GitHubPackage gPackage, Params queryParams) throws IOException {
        return getOrganizationPackageVersions(packageType, gPackage.getName(), org, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the package versions for a package owned by an organization. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param org:         the organization name. The name is not case-sensitive
     * @param gPackage:    the package from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> the state of the package, either active or deleted
     *                            - [string, default active]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return package versions list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-an-organization">
     * List package versions for a package owned by an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions")
    public <T> T getOrganizationPackageVersions(PackageType packageType, String org, GitHubPackage gPackage,
                                                Params queryParams, ReturnFormat format) throws IOException {
        return getOrganizationPackageVersions(packageType, gPackage.getName(), org, queryParams, format);
    }

    /**
     * Method to get the list of the package versions for a package owned by an organization. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @param org:         the organization name. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> the state of the package, either active or deleted
     *                            - [string, default active]
     *                        </li>
     *                     </ul>
     * @return package versions list as {@link ArrayList} of {@link GitHubPackageVersion} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-an-organization">
     * List package versions for a package owned by an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions")
    public ArrayList<GitHubPackageVersion> getOrganizationPackageVersions(PackageType packageType, String packageName,
                                                                          String org, Params queryParams) throws IOException {
        return getOrganizationPackageVersions(packageType, packageName, org, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the package versions for a package owned by an organization. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @param org:         the organization name. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> the state of the package, either active or deleted
     *                            - [string, default active]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return package versions list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-an-organization">
     * List package versions for a package owned by an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions")
    public <T> T getOrganizationPackageVersions(PackageType packageType, String packageName, String org,
                                                Params queryParams, ReturnFormat format) throws IOException {
        return returnPackageVersions(sendGetRequest(ORGS_PATH + org + PACKAGES_PATH + "/" + packageType + "/"
                + packageName + VERSIONS_QUERY_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to get a specific package version in an organization <br>
     * You must authenticate using an access token with the {@code "read:packages"} scope. If the {@code "package_type"}
     * belongs to a GitHub Packages registry that only supports repository-scoped permissions, your token must also include
     * the repo scope.For the list of GitHub Packages registries that only support repository-scoped permissions, see
     * "About permissions for GitHub Packages."
     *
     * @param packageType:      type of supported package
     * @param gPackage:         the package from fetch the package version
     * @param org:              the organization from fetch the package version
     * @param packageVersionId: unique identifier of the package version
     * @return package version as {@link GitHubPackageVersion} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#get-a-package-version-for-an-organization">
     * Get a package version for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions/{package_version_id}")
    public GitHubPackageVersion getOrganizationPackageVersion(PackageType packageType, GitHubPackage gPackage,
                                                              Organization org, long packageVersionId) throws IOException {
        return getOrganizationPackageVersion(packageType, gPackage.getName(), org.getLogin(), packageVersionId,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific package version in an organization <br>
     * You must authenticate using an access token with the {@code "read:packages"} scope. If the {@code "package_type"}
     * belongs to a GitHub Packages registry that only supports repository-scoped permissions, your token must also include
     * the repo scope.For the list of GitHub Packages registries that only support repository-scoped permissions, see
     * "About permissions for GitHub Packages."
     *
     * @param packageType:      type of supported package
     * @param gPackage:         the package from fetch the package version
     * @param org:              the organization from fetch the package version
     * @param packageVersionId: unique identifier of the package version
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return package version as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#get-a-package-version-for-an-organization">
     * Get a package version for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions/{package_version_id}")
    public <T> T getOrganizationPackageVersion(PackageType packageType, GitHubPackage gPackage, Organization org,
                                               long packageVersionId, ReturnFormat format) throws IOException {
        return getOrganizationPackageVersion(packageType, gPackage.getName(), org.getLogin(), packageVersionId, format);
    }

    /**
     * Method to get a specific package version in an organization <br>
     * You must authenticate using an access token with the {@code "read:packages"} scope. If the {@code "package_type"}
     * belongs to a GitHub Packages registry that only supports repository-scoped permissions, your token must also include
     * the repo scope.For the list of GitHub Packages registries that only support repository-scoped permissions, see
     * "About permissions for GitHub Packages."
     *
     * @param packageType:      type of supported package
     * @param gPackage:         the package from fetch the package version
     * @param org:              the organization name. The name is not case-sensitive
     * @param packageVersionId: unique identifier of the package version
     * @return package version as {@link GitHubPackageVersion} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#get-a-package-version-for-an-organization">
     * Get a package version for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions/{package_version_id}")
    public GitHubPackageVersion getOrganizationPackageVersion(PackageType packageType, GitHubPackage gPackage,
                                                              String org, long packageVersionId) throws IOException {
        return getOrganizationPackageVersion(packageType, gPackage.getName(), org, packageVersionId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific package version in an organization <br>
     * You must authenticate using an access token with the {@code "read:packages"} scope. If the {@code "package_type"}
     * belongs to a GitHub Packages registry that only supports repository-scoped permissions, your token must also include
     * the repo scope.For the list of GitHub Packages registries that only support repository-scoped permissions, see
     * "About permissions for GitHub Packages."
     *
     * @param packageType:      type of supported package
     * @param gPackage:         the package from fetch the package version
     * @param org:              the organization name. The name is not case-sensitive
     * @param packageVersionId: unique identifier of the package version
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return package version as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#get-a-package-version-for-an-organization">
     * Get a package version for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions/{package_version_id}")
    public <T> T getOrganizationPackageVersion(PackageType packageType, GitHubPackage gPackage, String org,
                                               long packageVersionId, ReturnFormat format) throws IOException {
        return getOrganizationPackageVersion(packageType, gPackage.getName(), org, packageVersionId, format);
    }

    /**
     * Method to get a specific package version in an organization <br>
     * You must authenticate using an access token with the {@code "read:packages"} scope. If the {@code "package_type"}
     * belongs to a GitHub Packages registry that only supports repository-scoped permissions, your token must also include
     * the repo scope.For the list of GitHub Packages registries that only support repository-scoped permissions, see
     * "About permissions for GitHub Packages."
     *
     * @param packageType:      type of supported package
     * @param packageName:      the name of the package
     * @param org:              the organization from fetch the package version
     * @param packageVersionId: unique identifier of the package version
     * @return package version as {@link GitHubPackageVersion} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#get-a-package-version-for-an-organization">
     * Get a package version for an organization</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions/{package_version_id}")
    public GitHubPackageVersion getOrganizationPackageVersion(PackageType packageType, String packageName,
                                                              Organization org, long packageVersionId) throws IOException {
        return getOrganizationPackageVersion(packageType, packageName, org.getLogin(), packageVersionId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific package version in an organization <br>
     * You must authenticate using an access token with the {@code "read:packages"} scope. If the {@code "package_type"}
     * belongs to a GitHub Packages registry that only supports repository-scoped permissions, your token must also include
     * the repo scope.For the list of GitHub Packages registries that only support repository-scoped permissions, see
     * "About permissions for GitHub Packages."
     *
     * @param packageType:      type of supported package
     * @param packageName:      the name of the package
     * @param org:              the organization from fetch the package version
     * @param packageVersionId: unique identifier of the package version
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return package version as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#get-a-package-version-for-an-organization">
     * Get a package version for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions/{package_version_id}")
    public <T> T getOrganizationPackageVersion(PackageType packageType, String packageName, Organization org,
                                               long packageVersionId, ReturnFormat format) throws IOException {
        return getOrganizationPackageVersion(packageType, packageName, org.getLogin(), packageVersionId, format);
    }

    /**
     * Method to get a specific package version in an organization <br>
     * You must authenticate using an access token with the {@code "read:packages"} scope. If the {@code "package_type"}
     * belongs to a GitHub Packages registry that only supports repository-scoped permissions, your token must also include
     * the repo scope.For the list of GitHub Packages registries that only support repository-scoped permissions, see
     * "About permissions for GitHub Packages."
     *
     * @param packageType:      type of supported package
     * @param packageName:      the name of the package
     * @param org:              the organization name. The name is not case-sensitive
     * @param packageVersionId: unique identifier of the package version
     * @return package version as {@link GitHubPackageVersion} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#get-a-package-version-for-an-organization">
     * Get a package version for an organization</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions/{package_version_id}")
    public GitHubPackageVersion getOrganizationPackageVersion(PackageType packageType, String packageName, String org,
                                                              long packageVersionId) throws IOException {
        return getOrganizationPackageVersion(packageType, packageName, org, packageVersionId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific package version in an organization <br>
     * You must authenticate using an access token with the {@code "read:packages"} scope. If the {@code "package_type"}
     * belongs to a GitHub Packages registry that only supports repository-scoped permissions, your token must also include
     * the repo scope.For the list of GitHub Packages registries that only support repository-scoped permissions, see
     * "About permissions for GitHub Packages."
     *
     * @param packageType:      type of supported package
     * @param packageName:      the name of the package
     * @param org:              the organization name. The name is not case-sensitive
     * @param packageVersionId: unique identifier of the package version
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return package version as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#get-a-package-version-for-an-organization">
     * Get a package version for an organization</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions/{package_version_id}")
    public <T> T getOrganizationPackageVersion(PackageType packageType, String packageName, String org,
                                               long packageVersionId, ReturnFormat format) throws IOException {
        return returnPackageVersion(sendGetRequest(ORGS_PATH + org + PACKAGES_PATH + "/" + packageType + "/"
                + packageName + VERSIONS_QUERY_PATH + "/" + packageVersionId), format);
    }

    /**
     * Method to delete a specific package version in an organization. If the package is public and the package version
     * has more than 5,000 downloads, you cannot delete the package version. In this scenario, contact GitHub support
     * for further assistance.
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType:    type of supported package
     * @param gPackage:       the package from delete the package version
     * @param org:            the organization from delete the package version
     * @param packageVersion: the package version to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#delete-package-version-for-an-organization">
     * Delete package version for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions/{package_version_id}")
    public boolean deleteOrganizationPackageVersion(PackageType packageType, GitHubPackage gPackage, Organization org,
                                                    GitHubPackageVersion packageVersion) {
        return deleteOrganizationPackageVersion(packageType, gPackage.getName(), org.getLogin(), packageVersion.getId());
    }

    /**
     * Method to delete a specific package version in an organization. If the package is public and the package version
     * has more than 5,000 downloads, you cannot delete the package version. In this scenario, contact GitHub support
     * for further assistance.
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType:      type of supported package
     * @param gPackage:         the package from delete the package version
     * @param org:              the organization from delete the package version
     * @param packageVersionId: unique identifier of the package version
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#delete-package-version-for-an-organization">
     * Delete package version for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions/{package_version_id}")
    public boolean deleteOrganizationPackageVersion(PackageType packageType, GitHubPackage gPackage, Organization org,
                                                    long packageVersionId) {
        return deleteOrganizationPackageVersion(packageType, gPackage.getName(), org.getLogin(), packageVersionId);
    }

    /**
     * Method to delete a specific package version in an organization. If the package is public and the package version
     * has more than 5,000 downloads, you cannot delete the package version. In this scenario, contact GitHub support
     * for further assistance.
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType:    type of supported package
     * @param packageName:    the name of the package
     * @param org:            the organization from delete the package version
     * @param packageVersion: the package version to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#delete-package-version-for-an-organization">
     * Delete package version for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions/{package_version_id}")
    public boolean deleteOrganizationPackageVersion(PackageType packageType, String packageName, Organization org,
                                                    GitHubPackageVersion packageVersion) {
        return deleteOrganizationPackageVersion(packageType, packageName, org.getLogin(), packageVersion.getId());
    }

    /**
     * Method to delete a specific package version in an organization. If the package is public and the package version
     * has more than 5,000 downloads, you cannot delete the package version. In this scenario, contact GitHub support
     * for further assistance.
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType:      type of supported package
     * @param packageName:      the name of the package
     * @param org:              the organization name. The name is not case-sensitive
     * @param packageVersionId: unique identifier of the package version
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#delete-package-version-for-an-organization">
     * Delete package version for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions/{package_version_id}")
    public boolean deleteOrganizationPackageVersion(PackageType packageType, String packageName, Organization org,
                                                    long packageVersionId) {
        return deleteOrganizationPackageVersion(packageType, packageName, org.getLogin(), packageVersionId);
    }

    /**
     * Method to delete a specific package version in an organization. If the package is public and the package version
     * has more than 5,000 downloads, you cannot delete the package version. In this scenario, contact GitHub support
     * for further assistance.
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType:    type of supported package
     * @param gPackage:       the package from delete the package version
     * @param org:            the organization name. The name is not case-sensitive
     * @param packageVersion: the package version to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#delete-package-version-for-an-organization">
     * Delete package version for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions/{package_version_id}")
    public boolean deleteOrganizationPackageVersion(PackageType packageType, GitHubPackage gPackage, String org,
                                                    GitHubPackageVersion packageVersion) {
        return deleteOrganizationPackageVersion(packageType, gPackage.getName(), org, packageVersion.getId());
    }

    /**
     * Method to delete a specific package version in an organization. If the package is public and the package version
     * has more than 5,000 downloads, you cannot delete the package version. In this scenario, contact GitHub support
     * for further assistance.
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType:      type of supported package
     * @param gPackage:         the package from delete the package version
     * @param org:              the organization from delete the package version
     * @param packageVersionId: unique identifier of the package version
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#delete-package-version-for-an-organization">
     * Delete package version for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions/{package_version_id}")
    public boolean deleteOrganizationPackageVersion(PackageType packageType, GitHubPackage gPackage, String org,
                                                    long packageVersionId) {
        return deleteOrganizationPackageVersion(packageType, gPackage.getName(), org, packageVersionId);
    }

    /**
     * Method to delete a specific package version in an organization. If the package is public and the package version
     * has more than 5,000 downloads, you cannot delete the package version. In this scenario, contact GitHub support
     * for further assistance.
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType:    type of supported package
     * @param packageName:    the name of the package
     * @param org:            the organization name. The name is not case-sensitive
     * @param packageVersion: the package version to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#delete-package-version-for-an-organization">
     * Delete package version for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions/{package_version_id}")
    public boolean deleteOrganizationPackageVersion(PackageType packageType, String packageName, String org,
                                                    GitHubPackageVersion packageVersion) {
        return deleteOrganizationPackageVersion(packageType, packageName, org, packageVersion.getId());
    }

    /**
     * Method to delete a specific package version in an organization. If the package is public and the package version
     * has more than 5,000 downloads, you cannot delete the package version. In this scenario, contact GitHub support
     * for further assistance.
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType:      type of supported package
     * @param packageName:      the name of the package
     * @param org:              the organization name. The name is not case-sensitive
     * @param packageVersionId: unique identifier of the package version
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#delete-package-version-for-an-organization">
     * Delete package version for an organization</a>
     **/
    @RequestPath(method = DELETE, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions/{package_version_id}")
    public boolean deleteOrganizationPackageVersion(PackageType packageType, String packageName, String org,
                                                    long packageVersionId) {
        try {
            sendDeleteRequest(ORGS_PATH + org + PACKAGES_PATH + "/" + packageType + "/" + packageName
                    + VERSIONS_QUERY_PATH + "/" + packageVersionId);
            if (apiRequest.getResponseStatusCode() != 204) {
                printErrorResponse();
                return false;
            }
            return true;
        } catch (IOException e) {
            printErrorResponse();
            return false;
        }
    }

    /**
     * Method to restore a specific package version in an organization. <br>
     * You can restore a deleted package under the following conditions:
     * <ul>
     *     <li>
     *         The package was deleted within the last 30 days.
     *     </li>
     *     <li>
     *         The same package namespace and version is still available and not reused for a new package.
     *         If the same package namespace is not available, you will not be able to restore your package. In this scenario,
     *         to restore the deleted package, you must delete the new package that uses the deleted package's namespace first.
     *     </li>
     * </ul>
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType:    type of supported package
     * @param gPackage:       the package where restore the package version
     * @param org:            the organization where restore the package version
     * @param packageVersion: the package version to restore
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#restore-package-version-for-an-organization">
     * Restore package version for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions/{package_version_id}/restore")
    public boolean restoreOrganizationPackageVersion(PackageType packageType, GitHubPackage gPackage, Organization org,
                                                     GitHubPackageVersion packageVersion) {
        return restoreOrganizationPackageVersion(packageType, gPackage.getName(), org.getLogin(), packageVersion.getId());
    }

    /**
     * Method to restore a specific package version in an organization. <br>
     * You can restore a deleted package under the following conditions:
     * <ul>
     *     <li>
     *         The package was deleted within the last 30 days.
     *     </li>
     *     <li>
     *         The same package namespace and version is still available and not reused for a new package.
     *         If the same package namespace is not available, you will not be able to restore your package. In this scenario,
     *         to restore the deleted package, you must delete the new package that uses the deleted package's namespace first.
     *     </li>
     * </ul>
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType:      type of supported package
     * @param gPackage:         the package where restore the package version
     * @param org:              the organization where restore the package version
     * @param packageVersionId: unique identifier of the package version
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#restore-package-version-for-an-organization">
     * Restore package version for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions/{package_version_id}/restore")
    public boolean restoreOrganizationPackageVersion(PackageType packageType, GitHubPackage gPackage, Organization org,
                                                     long packageVersionId) {
        return restoreOrganizationPackageVersion(packageType, gPackage.getName(), org.getLogin(), packageVersionId);
    }

    /**
     * Method to restore a specific package version in an organization. <br>
     * You can restore a deleted package under the following conditions:
     * <ul>
     *     <li>
     *         The package was deleted within the last 30 days.
     *     </li>
     *     <li>
     *         The same package namespace and version is still available and not reused for a new package.
     *         If the same package namespace is not available, you will not be able to restore your package. In this scenario,
     *         to restore the deleted package, you must delete the new package that uses the deleted package's namespace first.
     *     </li>
     * </ul>
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType:    type of supported package
     * @param packageName:    the name of the package
     * @param org:            the organization where restore the package version
     * @param packageVersion: the package version to restore
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#restore-package-version-for-an-organization">
     * Restore package version for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions/{package_version_id}/restore")
    public boolean restoreOrganizationPackageVersion(PackageType packageType, String packageName, Organization org,
                                                     GitHubPackageVersion packageVersion) {
        return restoreOrganizationPackageVersion(packageType, packageName, org.getLogin(), packageVersion.getId());
    }

    /**
     * Method to restore a specific package version in an organization. <br>
     * You can restore a deleted package under the following conditions:
     * <ul>
     *     <li>
     *         The package was deleted within the last 30 days.
     *     </li>
     *     <li>
     *         The same package namespace and version is still available and not reused for a new package.
     *         If the same package namespace is not available, you will not be able to restore your package. In this scenario,
     *         to restore the deleted package, you must delete the new package that uses the deleted package's namespace first.
     *     </li>
     * </ul>
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType:      type of supported package
     * @param packageName:      the name of the package
     * @param org:              the organization where restore the package version
     * @param packageVersionId: unique identifier of the package version
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#restore-package-version-for-an-organization">
     * Restore package version for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions/{package_version_id}/restore")
    public boolean restoreOrganizationPackageVersion(PackageType packageType, String packageName, Organization org,
                                                     long packageVersionId) {
        return restoreOrganizationPackageVersion(packageType, packageName, org.getLogin(), packageVersionId);
    }

    /**
     * Method to restore a specific package version in an organization. <br>
     * You can restore a deleted package under the following conditions:
     * <ul>
     *     <li>
     *         The package was deleted within the last 30 days.
     *     </li>
     *     <li>
     *         The same package namespace and version is still available and not reused for a new package.
     *         If the same package namespace is not available, you will not be able to restore your package. In this scenario,
     *         to restore the deleted package, you must delete the new package that uses the deleted package's namespace first.
     *     </li>
     * </ul>
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType:    type of supported package
     * @param gPackage:       the package where restore the package version
     * @param org:            the organization name. The name is not case-sensitive
     * @param packageVersion: the package version to restore
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#restore-package-version-for-an-organization">
     * Restore package version for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions/{package_version_id}/restore")
    public boolean restoreOrganizationPackageVersion(PackageType packageType, GitHubPackage gPackage, String org,
                                                     GitHubPackageVersion packageVersion) {
        return restoreOrganizationPackageVersion(packageType, gPackage.getName(), org, packageVersion.getId());
    }

    /**
     * Method to restore a specific package version in an organization. <br>
     * You can restore a deleted package under the following conditions:
     * <ul>
     *     <li>
     *         The package was deleted within the last 30 days.
     *     </li>
     *     <li>
     *         The same package namespace and version is still available and not reused for a new package.
     *         If the same package namespace is not available, you will not be able to restore your package. In this scenario,
     *         to restore the deleted package, you must delete the new package that uses the deleted package's namespace first.
     *     </li>
     * </ul>
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType:      type of supported package
     * @param gPackage:         the package where restore the package version
     * @param org:              the organization name. The name is not case-sensitive
     * @param packageVersionId: unique identifier of the package version
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#restore-package-version-for-an-organization">
     * Restore package version for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions/{package_version_id}/restore")
    public boolean restoreOrganizationPackageVersion(PackageType packageType, GitHubPackage gPackage, String org,
                                                     long packageVersionId) {
        return restoreOrganizationPackageVersion(packageType, gPackage.getName(), org, packageVersionId);
    }

    /**
     * Method to restore a specific package version in an organization. <br>
     * You can restore a deleted package under the following conditions:
     * <ul>
     *     <li>
     *         The package was deleted within the last 30 days.
     *     </li>
     *     <li>
     *         The same package namespace and version is still available and not reused for a new package.
     *         If the same package namespace is not available, you will not be able to restore your package. In this scenario,
     *         to restore the deleted package, you must delete the new package that uses the deleted package's namespace first.
     *     </li>
     * </ul>
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType:    type of supported package
     * @param packageName:    the name of the package
     * @param org:            the organization name. The name is not case-sensitive
     * @param packageVersion: the package version to restore
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#restore-package-version-for-an-organization">
     * Restore package version for an organization</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions/{package_version_id}/restore")
    public boolean restoreOrganizationPackageVersion(PackageType packageType, String packageName, String org,
                                                     GitHubPackageVersion packageVersion) {
        return restoreOrganizationPackageVersion(packageType, packageName, org, packageVersion.getId());
    }

    /**
     * Method to restore a specific package version in an organization. <br>
     * You can restore a deleted package under the following conditions:
     * <ul>
     *     <li>
     *         The package was deleted within the last 30 days.
     *     </li>
     *     <li>
     *         The same package namespace and version is still available and not reused for a new package.
     *         If the same package namespace is not available, you will not be able to restore your package. In this scenario,
     *         to restore the deleted package, you must delete the new package that uses the deleted package's namespace first.
     *     </li>
     * </ul>
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType:      type of supported package
     * @param packageName:      the name of the package
     * @param org:              the organization name. The name is not case-sensitive
     * @param packageVersionId: unique identifier of the package version
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#restore-package-version-for-an-organization">
     * Restore package version for an organization</a>
     **/
    @RequestPath(method = POST, path = "/orgs/{org}/packages/{package_type}/{package_name}/versions/{package_version_id}/restore")
    public boolean restoreOrganizationPackageVersion(PackageType packageType, String packageName, String org,
                                                     long packageVersionId) {
        try {
            sendPostRequest(ORGS_PATH + org + PACKAGES_PATH + "/" + packageType + "/" + packageName
                    + VERSIONS_QUERY_PATH + "/" + packageVersionId + RESTORE_PATH, null);
            if (apiRequest.getResponseStatusCode() != 204) {
                printErrorResponse();
                return false;
            }
            return true;
        } catch (IOException e) {
            printErrorResponse();
            return false;
        }
    }

    /**
     * Method to get the list of the packages owned by the authenticated user within the user's namespace. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @return packages list as {@link ArrayList} of {@link GitHubPackage} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-packages-for-the-authenticated-users-namespace">
     * List packages for the authenticated user's namespace</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/packages")
    public ArrayList<GitHubPackage> getAuthenticatedUserPackages(PackageType packageType) throws IOException {
        return getAuthenticatedUserPackages(packageType, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the packages owned by the authenticated user within the user's namespace. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return packages list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-packages-for-the-authenticated-users-namespace">
     * List packages for the authenticated user's namespace</a>
     **/
    @RequestPath(method = GET, path = "/user/packages")
    public <T> T getAuthenticatedUserPackages(PackageType packageType, ReturnFormat format) throws IOException {
        return getAuthenticatedUserPackages(packageType, null, format);
    }

    /**
     * Method to get the list of the packages owned by the authenticated user within the user's namespace. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param visibility:  the selected visibility of the packages. This parameter is optional and only filters an existing
     *                     result set. The internal visibility is only supported for GitHub Packages registries that allow
     *                     for granular permissions. For other ecosystems internal is synonymous with private. For the list
     *                     of GitHub Packages registries that support granular permissions, see "About permissions for
     *                     GitHub Packages."
     * @return packages list as {@link ArrayList} of {@link GitHubPackage} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-packages-for-the-authenticated-users-namespace">
     * List packages for the authenticated user's namespace</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/packages")
    public ArrayList<GitHubPackage> getAuthenticatedUserPackages(PackageType packageType,
                                                                 RepoVisibility visibility) throws IOException {
        return getAuthenticatedUserPackages(packageType, visibility, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the packages owned by the authenticated user within the user's namespace. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param visibility:  the selected visibility of the packages. This parameter is optional and only filters an existing
     *                     result set. The internal visibility is only supported for GitHub Packages registries that allow
     *                     for granular permissions. For other ecosystems internal is synonymous with private. For the list
     *                     of GitHub Packages registries that support granular permissions, see "About permissions for
     *                     GitHub Packages."
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return packages list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-packages-for-the-authenticated-users-namespace">
     * List packages for the authenticated user's namespace</a>
     **/
    @RequestPath(method = GET, path = "/user/packages")
    public <T> T getAuthenticatedUserPackages(PackageType packageType, RepoVisibility visibility,
                                              ReturnFormat format) throws IOException {
        String reqUrl = USER_PACKAGES_PATH + "?package_type=" + packageType;
        if (visibility != null)
            reqUrl += "&visibility=" + visibility;
        return returnPackages(sendGetRequest(reqUrl), format);
    }

    /**
     * Method to get a specific package for a package owned by the authenticated user <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @return package as {@link GitHubPackage} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#get-a-package-for-the-authenticated-user">
     * Get a package for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/packages/{package_type}/{package_name}")
    public GitHubPackage getAuthenticatedUserPackage(PackageType packageType, String packageName) throws IOException {
        return getAuthenticatedUserPackage(packageType, packageName, LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific package for a package owned by the authenticated user <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return package as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#get-a-package-for-the-authenticated-user">
     * Get a package for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/packages/{package_type}/{package_name}")
    public <T> T getAuthenticatedUserPackage(PackageType packageType, String packageName,
                                             ReturnFormat format) throws IOException {
        return returnPackage(sendGetRequest(USER_PACKAGES_PATH + "/" + packageType + "/" + packageName), format);
    }

    /**
     * Method to delete a package owned by the authenticated user. If the package is public and the package version
     * has more than 5,000 downloads, you cannot delete the package version. In this scenario, contact GitHub support
     * for further assistance.
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} and
     * {@code "delete:packages"} scopes.
     * If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped permissions, your
     * token must also include the repo scope. For the list of GitHub Packages registries that only support
     * repository-scoped permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param gPackage:    the package to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#delete-a-package-for-the-authenticated-user">
     * Delete a package for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/user/packages/{package_type}/{package_name}")
    public boolean deleteAuthenticatedUserPackage(PackageType packageType, GitHubPackage gPackage) {
        return deleteAuthenticatedUserPackage(packageType, gPackage.getName());
    }

    /**
     * Method to delete a package owned by the authenticated user. If the package is public and the package version
     * has more than 5,000 downloads, you cannot delete the package version. In this scenario, contact GitHub support
     * for further assistance.
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} and
     * {@code "delete:packages"} scopes.
     * If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped permissions, your
     * token must also include the repo scope. For the list of GitHub Packages registries that only support
     * repository-scoped permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#delete-a-package-for-the-authenticated-user">
     * Delete a package for the authenticated user</a>
     **/
    @RequestPath(method = DELETE, path = "/user/packages/{package_type}/{package_name}")
    public boolean deleteAuthenticatedUserPackage(PackageType packageType, String packageName) {
        try {
            sendDeleteRequest(USER_PACKAGES_PATH + "/" + packageType + "/" + packageName);
            if (apiRequest.getResponseStatusCode() != 204) {
                printErrorResponse();
                return false;
            }
            return true;
        } catch (IOException e) {
            printErrorResponse();
            return false;
        }
    }

    /**
     * Method to restore a package owned by the authenticated user
     * You can restore a deleted package under the following conditions:
     * <ul>
     *     <li>
     *         The package was deleted within the last 30 days.
     *     </li>
     *     <li>
     *         The same package namespace and version is still available and not reused for a new package.
     *         If the same package namespace is not available, you will not be able to restore your package. In this scenario,
     *         to restore the deleted package, you must delete the new package that uses the deleted package's namespace first.
     *     </li>
     * </ul>
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType: type of supported package
     * @param gPackage:    the package to restore
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#restore-a-package-for-the-authenticated-user">
     * Restore a package for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/user/packages/{package_type}/{package_name}/restore")
    public boolean restoreAuthenticatedUserPackage(PackageType packageType, GitHubPackage gPackage) {
        return restoreAuthenticatedUserPackage(packageType, gPackage.getName());
    }

    /**
     * Method to restore a package owned by the authenticated user
     * You can restore a deleted package under the following conditions:
     * <ul>
     *     <li>
     *         The package was deleted within the last 30 days.
     *     </li>
     *     <li>
     *         The same package namespace and version is still available and not reused for a new package.
     *         If the same package namespace is not available, you will not be able to restore your package. In this scenario,
     *         to restore the deleted package, you must delete the new package that uses the deleted package's namespace first.
     *     </li>
     * </ul>
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#restore-a-package-for-the-authenticated-user">
     * Restore a package for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/user/packages/{package_type}/{package_name}/restore")
    public boolean restoreAuthenticatedUserPackage(PackageType packageType, String packageName) {
        return restoreAuthenticatedUserPackage(packageType, packageName, null);
    }

    /**
     * Method to restore a package owned by the authenticated user
     * You can restore a deleted package under the following conditions:
     * <ul>
     *     <li>
     *         The package was deleted within the last 30 days.
     *     </li>
     *     <li>
     *         The same package namespace and version is still available and not reused for a new package.
     *         If the same package namespace is not available, you will not be able to restore your package. In this scenario,
     *         to restore the deleted package, you must delete the new package that uses the deleted package's namespace first.
     *     </li>
     * </ul>
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType: type of supported package
     * @param gPackage:    the package to restore
     * @param token:       package token
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#restore-a-package-for-the-authenticated-user">
     * Restore a package for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/user/packages/{package_type}/{package_name}/restore")
    public boolean restoreAuthenticatedUserPackage(PackageType packageType, GitHubPackage gPackage, String token) {
        return restoreAuthenticatedUserPackage(packageType, gPackage.getName(), token);
    }

    /**
     * Method to restore a package owned by the authenticated user
     * You can restore a deleted package under the following conditions:
     * <ul>
     *     <li>
     *         The package was deleted within the last 30 days.
     *     </li>
     *     <li>
     *         The same package namespace and version is still available and not reused for a new package.
     *         If the same package namespace is not available, you will not be able to restore your package. In this scenario,
     *         to restore the deleted package, you must delete the new package that uses the deleted package's namespace first.
     *     </li>
     * </ul>
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @param token:       package token
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#restore-a-package-for-the-authenticated-user">
     * Restore a package for the authenticated user</a>
     **/
    @RequestPath(method = POST, path = "/user/packages/{package_type}/{package_name}/restore")
    public boolean restoreAuthenticatedUserPackage(PackageType packageType, String packageName, String token) {
        try {
            String reqUrl = USER_PACKAGES_PATH + "/" + packageType + "/" + packageName + RESTORE_PATH;
            if (token != null)
                reqUrl += "?token=" + token;
            sendPostRequest(reqUrl, null);
            if (apiRequest.getResponseStatusCode() != 204) {
                printErrorResponse();
                return false;
            }
            return true;
        } catch (IOException e) {
            printErrorResponse();
            return false;
        }
    }

    /**
     * Method to get the list of the package versions for a package owned by the authenticated user. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param gPackage:    the package from fetch the list
     * @return package versions list as {@link ArrayList} of {@link GitHubPackageVersion} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-the-authenticated-user">
     * List package versions for a package owned by the authenticated user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/user/packages/{package_type}/{package_name}/versions")
    public ArrayList<GitHubPackageVersion> getAuthenticatedUserPackageVersions(PackageType packageType,
                                                                               GitHubPackage gPackage) throws IOException {
        return getAuthenticatedUserPackageVersions(packageType, gPackage.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the package versions for a package owned by the authenticated user. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param gPackage:    the package from fetch the list
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return package versions list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-the-authenticated-user">
     * List package versions for a package owned by the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/user/packages/{package_type}/{package_name}/versions")
    public <T> T getAuthenticatedUserPackageVersions(PackageType packageType, GitHubPackage gPackage,
                                                     ReturnFormat format) throws IOException {
        return getAuthenticatedUserPackageVersions(packageType, gPackage.getName(), format);
    }

    /**
     * Method to get the list of the package versions for a package owned by the authenticated user. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @return package versions list as {@link ArrayList} of {@link GitHubPackageVersion} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-the-authenticated-user">
     * List package versions for a package owned by the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/packages/{package_type}/{package_name}/versions")
    public ArrayList<GitHubPackageVersion> getAuthenticatedUserPackageVersions(PackageType packageType,
                                                                               String packageName) throws IOException {
        return getAuthenticatedUserPackageVersions(packageType, packageName, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the package versions for a package owned by the authenticated user. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return package versions list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-the-authenticated-user">
     * List package versions for a package owned by the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/packages/{package_type}/{package_name}/versions")
    public <T> T getAuthenticatedUserPackageVersions(PackageType packageType, String packageName,
                                                     ReturnFormat format) throws IOException {
        return returnPackageVersions(sendGetRequest(USER_PACKAGES_PATH + "/" + packageType + "/" + packageName
                + VERSIONS_QUERY_PATH), format);
    }

    /**
     * Method to get the list of the package versions for a package owned by the authenticated user. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param gPackage:    the package from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> the state of the package, either active or deleted
     *                            - [string, default active]
     *                        </li>
     *                     </ul>
     * @return package versions list as {@link ArrayList} of {@link GitHubPackageVersion} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-the-authenticated-user">
     * List package versions for a package owned by the authenticated user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/user/packages/{package_type}/{package_name}/versions")
    public ArrayList<GitHubPackageVersion> getAuthenticatedUserPackageVersions(PackageType packageType,
                                                                               GitHubPackage gPackage,
                                                                               Params queryParams) throws IOException {
        return getAuthenticatedUserPackageVersions(packageType, gPackage.getName(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the package versions for a package owned by the authenticated user. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param gPackage:    the package from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> the state of the package, either active or deleted
     *                            - [string, default active]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return package versions list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-the-authenticated-user">
     * List package versions for a package owned by the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/user/packages/{package_type}/{package_name}/versions")
    public <T> T getAuthenticatedUserPackageVersions(PackageType packageType, GitHubPackage gPackage, Params queryParams,
                                                     ReturnFormat format) throws IOException {
        return getAuthenticatedUserPackageVersions(packageType, gPackage.getName(), queryParams, format);
    }

    /**
     * Method to get the list of the package versions for a package owned by the authenticated user. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> the state of the package, either active or deleted
     *                            - [string, default active]
     *                        </li>
     *                     </ul>
     * @return package versions list as {@link ArrayList} of {@link GitHubPackageVersion} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-the-authenticated-user">
     * List package versions for a package owned by the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/packages/{package_type}/{package_name}/versions")
    public ArrayList<GitHubPackageVersion> getAuthenticatedUserPackageVersions(PackageType packageType, String packageName,
                                                                               Params queryParams) throws IOException {
        return getAuthenticatedUserPackageVersions(packageType, packageName, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the package versions for a package owned by the authenticated user. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                        <li>
     *                            {@code "state"} -> the state of the package, either active or deleted
     *                            - [string, default active]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return package versions list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-the-authenticated-user">
     * List package versions for a package owned by the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/packages/{package_type}/{package_name}/versions")
    public <T> T getAuthenticatedUserPackageVersions(PackageType packageType, String packageName, Params queryParams,
                                                     ReturnFormat format) throws IOException {
        return returnPackageVersions(sendGetRequest(USER_PACKAGES_PATH + "/" + packageType + "/" + packageName
                + VERSIONS_QUERY_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to get a specific package version for a package owned by the authenticated user. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType:      type of supported package
     * @param gPackage:         the package from fetch the package version
     * @param packageVersionId: unique identifier of the package version
     * @return package version as {@link GitHubPackageVersion} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#get-a-package-version-for-the-authenticated-user">
     * Get a package version for the authenticated user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/user/packages/{package_type}/{package_name}/versions/{package_version_id}")
    public GitHubPackageVersion getAuthenticatedUserPackageVersion(PackageType packageType, GitHubPackage gPackage,
                                                                   long packageVersionId) throws IOException {
        return getAuthenticatedUserPackageVersion(packageType, gPackage.getName(), packageVersionId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific package version for a package owned by the authenticated user. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType:      type of supported package
     * @param gPackage:         the package from fetch the package version
     * @param packageVersionId: unique identifier of the package version
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return package version as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#get-a-package-version-for-the-authenticated-user">
     * Get a package version for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/user/packages/{package_type}/{package_name}/versions/{package_version_id}")
    public <T> T getAuthenticatedUserPackageVersion(PackageType packageType, GitHubPackage gPackage,
                                                    long packageVersionId, ReturnFormat format) throws IOException {
        return getAuthenticatedUserPackageVersion(packageType, gPackage.getName(), packageVersionId, format);
    }

    /**
     * Method to get a specific package version for a package owned by the authenticated user. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType:      type of supported package
     * @param packageName:      the name of the package
     * @param packageVersionId: unique identifier of the package version
     * @return package version as {@link GitHubPackageVersion} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#get-a-package-version-for-the-authenticated-user">
     * Get a package version for the authenticated user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/user/packages/{package_type}/{package_name}/versions/{package_version_id}")
    public GitHubPackageVersion getAuthenticatedUserPackageVersion(PackageType packageType, String packageName,
                                                                   long packageVersionId) throws IOException {
        return getAuthenticatedUserPackageVersion(packageType, packageName, packageVersionId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific package version for a package owned by the authenticated user. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType:      type of supported package
     * @param packageName:      the name of the package
     * @param packageVersionId: unique identifier of the package version
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return package version as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#get-a-package-version-for-the-authenticated-user">
     * Get a package version for the authenticated user</a>
     **/
    @RequestPath(method = GET, path = "/user/packages/{package_type}/{package_name}/versions/{package_version_id}")
    public <T> T getAuthenticatedUserPackageVersion(PackageType packageType, String packageName, long packageVersionId,
                                                    ReturnFormat format) throws IOException {
        return returnPackageVersion(sendGetRequest(USER_PACKAGES_PATH + "/" + packageType + "/" + packageName
                + VERSIONS_QUERY_PATH + "/" + packageVersionId), format);
    }

    /**
     * Method to delete a specific package version for a package owned by the authenticated user. If the package is public
     * and the package version has more than 5,000 downloads, you cannot delete the package version. In this scenario,
     * contact GitHub support for further assistance
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} and
     * {@code "delete:packages"} scopes.
     * If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped permissions, your
     * token must also include the repo scope. For the list of GitHub Packages registries that only support
     * repository-scoped permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType:    type of supported package
     * @param gPackage:       the where delete package version to delete
     * @param packageVersion: the package version to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#delete-a-package-version-for-the-authenticated-user">
     * Delete a package version for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/user/packages/{package_type}/{package_name}/versions/{package_version_id}")
    public boolean deleteAuthenticatedUserPackageVersion(PackageType packageType, GitHubPackage gPackage,
                                                         GitHubPackageVersion packageVersion) {
        return deleteAuthenticatedUserPackageVersion(packageType, gPackage.getName(), packageVersion.getId());
    }

    /**
     * Method to delete a specific package version for a package owned by the authenticated user. If the package is public
     * and the package version has more than 5,000 downloads, you cannot delete the package version. In this scenario,
     * contact GitHub support for further assistance
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} and
     * {@code "delete:packages"} scopes.
     * If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped permissions, your
     * token must also include the repo scope. For the list of GitHub Packages registries that only support
     * repository-scoped permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType:    type of supported package
     * @param packageName:    the name of the package
     * @param packageVersion: the package version to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#delete-a-package-version-for-the-authenticated-user">
     * Delete a package version for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/user/packages/{package_type}/{package_name}/versions/{package_version_id}")
    public boolean deleteAuthenticatedUserPackageVersion(PackageType packageType, String packageName,
                                                         GitHubPackageVersion packageVersion) {
        return deleteAuthenticatedUserPackageVersion(packageType, packageName, packageVersion.getId());
    }

    /**
     * Method to delete a specific package version for a package owned by the authenticated user. If the package is public
     * and the package version has more than 5,000 downloads, you cannot delete the package version. In this scenario,
     * contact GitHub support for further assistance
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} and
     * {@code "delete:packages"} scopes.
     * If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped permissions, your
     * token must also include the repo scope. For the list of GitHub Packages registries that only support
     * repository-scoped permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType:      type of supported package
     * @param gPackage:         the where delete package version to delete
     * @param packageVersionId: unique identifier of the package version
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#delete-a-package-version-for-the-authenticated-user">
     * Delete a package version for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/user/packages/{package_type}/{package_name}/versions/{package_version_id}")
    public boolean deleteAuthenticatedUserPackageVersion(PackageType packageType, GitHubPackage gPackage,
                                                         long packageVersionId) {
        return deleteAuthenticatedUserPackageVersion(packageType, gPackage.getName(), packageVersionId);
    }

    /**
     * Method to delete a specific package version for a package owned by the authenticated user. If the package is public
     * and the package version has more than 5,000 downloads, you cannot delete the package version. In this scenario,
     * contact GitHub support for further assistance
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} and
     * {@code "delete:packages"} scopes.
     * If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped permissions, your
     * token must also include the repo scope. For the list of GitHub Packages registries that only support
     * repository-scoped permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType:      type of supported package
     * @param packageName:      the name of the package
     * @param packageVersionId: unique identifier of the package version
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#delete-a-package-version-for-the-authenticated-user">
     * Delete a package version for the authenticated user</a>
     **/
    @RequestPath(method = DELETE, path = "/user/packages/{package_type}/{package_name}/versions/{package_version_id}")
    public boolean deleteAuthenticatedUserPackageVersion(PackageType packageType, String packageName,
                                                         long packageVersionId) {
        try {
            sendDeleteRequest(USER_PACKAGES_PATH + "/" + packageType + "/" + packageName + VERSIONS_QUERY_PATH
                    + "/" + packageVersionId);
            if (apiRequest.getResponseStatusCode() != 204) {
                printErrorResponse();
                return false;
            }
            return true;
        } catch (IOException e) {
            printErrorResponse();
            return false;
        }
    }

    /**
     * Method to restore a package version owned by the authenticated user. <br>
     * You can restore a deleted package under the following conditions:
     * <ul>
     *     <li>
     *         The package was deleted within the last 30 days.
     *     </li>
     *     <li>
     *         The same package namespace and version is still available and not reused for a new package. If the same
     *         package namespace is not available, you will not be able to restore your package. In this scenario, to
     *         restore the deleted package, you must delete the new package that uses the deleted package's namespace first.
     *     </li>
     * </ul>
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType:    type of supported package
     * @param gPackage:       the package where restore the package version
     * @param packageVersion: the package version to restore
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#restore-a-package-version-for-the-authenticated-user">
     * Restore a package version for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/user/packages/{package_type}/{package_name}/versions/{package_version_id}/restore")
    public boolean restoreAuthenticatedUserPackageVersion(PackageType packageType, GitHubPackage gPackage,
                                                          GitHubPackageVersion packageVersion) {
        return restoreAuthenticatedUserPackageVersion(packageType, gPackage.getName(), packageVersion.getId());
    }

    /**
     * Method to restore a package version owned by the authenticated user. <br>
     * You can restore a deleted package under the following conditions:
     * <ul>
     *     <li>
     *         The package was deleted within the last 30 days.
     *     </li>
     *     <li>
     *         The same package namespace and version is still available and not reused for a new package. If the same
     *         package namespace is not available, you will not be able to restore your package. In this scenario, to
     *         restore the deleted package, you must delete the new package that uses the deleted package's namespace first.
     *     </li>
     * </ul>
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType:    type of supported package
     * @param packageName:    the name of the package
     * @param packageVersion: the package version to restore
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#restore-a-package-version-for-the-authenticated-user">
     * Restore a package version for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/user/packages/{package_type}/{package_name}/versions/{package_version_id}/restore")
    public boolean restoreAuthenticatedUserPackageVersion(PackageType packageType, String packageName,
                                                          GitHubPackageVersion packageVersion) {
        return restoreAuthenticatedUserPackageVersion(packageType, packageName, packageVersion.getId());
    }

    /**
     * Method to restore a package version owned by the authenticated user. <br>
     * You can restore a deleted package under the following conditions:
     * <ul>
     *     <li>
     *         The package was deleted within the last 30 days.
     *     </li>
     *     <li>
     *         The same package namespace and version is still available and not reused for a new package. If the same
     *         package namespace is not available, you will not be able to restore your package. In this scenario, to
     *         restore the deleted package, you must delete the new package that uses the deleted package's namespace first.
     *     </li>
     * </ul>
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType:      type of supported package
     * @param gPackage:         the package where restore the package version
     * @param packageVersionId: unique identifier of the package version
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#restore-a-package-version-for-the-authenticated-user">
     * Restore a package version for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/user/packages/{package_type}/{package_name}/versions/{package_version_id}/restore")
    public boolean restoreAuthenticatedUserPackageVersion(PackageType packageType, GitHubPackage gPackage,
                                                          long packageVersionId) {
        return restoreAuthenticatedUserPackageVersion(packageType, gPackage.getName(), packageVersionId);
    }

    /**
     * Method to restore a package version owned by the authenticated user. <br>
     * You can restore a deleted package under the following conditions:
     * <ul>
     *     <li>
     *         The package was deleted within the last 30 days.
     *     </li>
     *     <li>
     *         The same package namespace and version is still available and not reused for a new package. If the same
     *         package namespace is not available, you will not be able to restore your package. In this scenario, to
     *         restore the deleted package, you must delete the new package that uses the deleted package's namespace first.
     *     </li>
     * </ul>
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType:      type of supported package
     * @param packageName:      the name of the package
     * @param packageVersionId: unique identifier of the package version
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#restore-a-package-version-for-the-authenticated-user">
     * Restore a package version for the authenticated user</a>
     **/
    @RequestPath(method = POST, path = "/user/packages/{package_type}/{package_name}/versions/{package_version_id}/restore")
    public boolean restoreAuthenticatedUserPackageVersion(PackageType packageType, String packageName,
                                                          long packageVersionId) {
        try {
            sendPostRequest(USER_PACKAGES_PATH + "/" + packageType + "/" + packageName + VERSIONS_QUERY_PATH
                    + "/" + packageVersionId + RESTORE_PATH, null);
            if (apiRequest.getResponseStatusCode() != 204) {
                printErrorResponse();
                return false;
            }
            return true;
        } catch (IOException e) {
            printErrorResponse();
            return false;
        }
    }

    /**
     * Method to get the list of the all packages in a user's namespace for which the requesting user has access <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param username:    the handle for the GitHub user account
     * @param packageType: type of supported package
     * @return packages list as {@link ArrayList} of {@link GitHubPackage} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-packages-for-a-user">
     * List packages for a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/packages")
    public ArrayList<GitHubPackage> getUserPackages(String username, PackageType packageType) throws IOException {
        return getUserPackages(username, packageType, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all packages in a user's namespace for which the requesting user has access <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param username:    the handle for the GitHub user account
     * @param packageType: type of supported package
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return packages list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-packages-for-a-user">
     * List packages for a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/packages")
    public <T> T getUserPackages(String username, PackageType packageType, ReturnFormat format) throws IOException {
        return getUserPackages(username, packageType, null, format);
    }

    /**
     * Method to get the list of the all packages in a user's namespace for which the requesting user has access <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param username:    the handle for the GitHub user account
     * @param packageType: type of supported package
     * @param visibility:  the selected visibility of the packages. This parameter is optional and only filters an existing
     *                     result set. The internal visibility is only supported for GitHub Packages registries that allow
     *                     for granular permissions. For other ecosystems internal is synonymous with private. For the list
     *                     of GitHub Packages registries that support granular permissions, see "About permissions for
     *                     GitHub Packages."
     * @return packages list as {@link ArrayList} of {@link GitHubPackage} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-packages-for-a-user">
     * List packages for a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/packages")
    public ArrayList<GitHubPackage> getUserPackages(String username, PackageType packageType,
                                                    RepoVisibility visibility) throws IOException {
        return getUserPackages(username, packageType, visibility, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the all packages in a user's namespace for which the requesting user has access <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param username:    the handle for the GitHub user account
     * @param packageType: type of supported package
     * @param visibility:  the selected visibility of the packages. This parameter is optional and only filters an existing
     *                     result set. The internal visibility is only supported for GitHub Packages registries that allow
     *                     for granular permissions. For other ecosystems internal is synonymous with private. For the list
     *                     of GitHub Packages registries that support granular permissions, see "About permissions for
     *                     GitHub Packages."
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return packages list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-packages-for-a-user">
     * List packages for a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/packages")
    public <T> T getUserPackages(String username, PackageType packageType, RepoVisibility visibility,
                                 ReturnFormat format) throws IOException {
        String reqUrl = USERS_PATH + username + PACKAGES_PATH + "?package_type=" + packageType;
        if (visibility != null)
            reqUrl += "&visibility=" + visibility;
        return returnPackages(sendGetRequest(reqUrl), format);
    }

    /**
     * Method to create a packages list
     *
     * @param packagesResponse: obtained from GitHub's response
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return packages list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnPackages(String packagesResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(packagesResponse);
            case LIBRARY_OBJECT:
                ArrayList<GitHubPackage> packages = new ArrayList<>();
                JSONArray jPackages = new JSONArray(packagesResponse);
                for (int j = 0; j < jPackages.length(); j++)
                    packages.add(new GitHubPackage(jPackages.getJSONObject(j)));
                return (T) packages;
            default:
                return (T) packagesResponse;
        }
    }

    /**
     * Method to get a specific package metadata for a public package owned by a user <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param gPackage:    the package from fetch the package
     * @param username:    the handle for the GitHub user account
     * @return package as {@link GitHubPackage} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#get-a-package-for-a-user">
     * Get a package for a user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/packages/{package_type}/{package_name}")
    public GitHubPackage getUserPackage(PackageType packageType, GitHubPackage gPackage, String username) throws IOException {
        return getUserPackage(packageType, gPackage.getName(), username, LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific package metadata for a public package owned by a user <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param gPackage:    the package from fetch the package
     * @param username:    the handle for the GitHub user account
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return package as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#get-a-package-for-a-user">
     * Get a package for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/packages/{package_type}/{package_name}")
    public <T> T getUserPackage(PackageType packageType, GitHubPackage gPackage, String username,
                                ReturnFormat format) throws IOException {
        return getUserPackage(packageType, gPackage.getName(), username, format);
    }

    /**
     * Method to get a specific package metadata for a public package owned by a user <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @param username:    the handle for the GitHub user account
     * @return package as {@link GitHubPackage} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#get-a-package-for-a-user">
     * Get a package for a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/packages/{package_type}/{package_name}")
    public GitHubPackage getUserPackage(PackageType packageType, String packageName, String username) throws IOException {
        return getUserPackage(packageType, packageName, username, LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific package metadata for a public package owned by a user <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @param username:    the handle for the GitHub user account
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return package as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#get-a-package-for-a-user">
     * Get a package for a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/packages/{package_type}/{package_name}")
    public <T> T getUserPackage(PackageType packageType, String packageName, String username,
                                ReturnFormat format) throws IOException {
        return returnPackage(sendGetRequest(USERS_PATH + username + PACKAGES_PATH + "/" + packageType + "/"
                + packageName), format);
    }

    /**
     * Method to create a package
     *
     * @param packageResponse: obtained from GitHub's response
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return package as {@code "format"} defines
     **/
    @Returner
    private <T> T returnPackage(String packageResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(packageResponse);
            case LIBRARY_OBJECT:
                return (T) new GitHubPackage(new JSONObject(packageResponse));
            default:
                return (T) packageResponse;
        }
    }

    /**
     * Method to delete an entire package for a user. If the package is public and the package version
     * has more than 5,000 downloads, you cannot delete the package version. In this scenario, contact GitHub support
     * for further assistance.
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType: type of supported package
     * @param gPackage:    the package to delete
     * @param username:    the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#delete-a-package-for-a-user>
     * Delete a package for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/users/{username}/packages/{package_type}/{package_name}")
    public boolean deleteUserPackage(PackageType packageType, GitHubPackage gPackage, String username) {
        return deleteUserPackage(packageType, gPackage.getName(), username);
    }

    /**
     * Method to delete an entire package for a user. If the package is public and the package version
     * has more than 5,000 downloads, you cannot delete the package version. In this scenario, contact GitHub support
     * for further assistance.
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @param username:    the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#delete-a-package-for-a-user>
     * Delete a package for a user</a>
     **/
    @RequestPath(method = DELETE, path = "/users/{username}/packages/{package_type}/{package_name}")
    public boolean deleteUserPackage(PackageType packageType, String packageName, String username) {
        try {
            sendDeleteRequest(USERS_PATH + username + PACKAGES_PATH + "/" + packageType + "/" + packageName);
            if (apiRequest.getResponseStatusCode() != 204) {
                printErrorResponse();
                return false;
            }
            return true;
        } catch (IOException e) {
            printErrorResponse();
            return false;
        }
    }

    /**
     * Method to restore an entire package for a user <br>
     * You can restore a deleted package under the following conditions:
     * <ul>
     *     <li>
     *         The package was deleted within the last 30 days.
     *     </li>
     *     <li>
     *         The same package namespace and version is still available and not reused for a new package. If the same
     *         package namespace is not available, you will not be able to restore your package. In this scenario, to
     *         restore the deleted package, you must delete the new package that uses the deleted package's namespace first.
     *     </li>
     * </ul>
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType: type of supported package
     * @param gPackage:    the package to restore
     * @param username:    the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#restore-a-package-version-for-the-authenticated-user">
     * Restore a package for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/users/{username}/packages/{package_type}/{package_name}/restore")
    public boolean restoreUserPackage(PackageType packageType, GitHubPackage gPackage, String username) {
        return restoreUserPackage(packageType, gPackage.getName(), username);
    }

    /**
     * Method to restore an entire package for a user <br>
     * You can restore a deleted package under the following conditions:
     * <ul>
     *     <li>
     *         The package was deleted within the last 30 days.
     *     </li>
     *     <li>
     *         The same package namespace and version is still available and not reused for a new package. If the same
     *         package namespace is not available, you will not be able to restore your package. In this scenario, to
     *         restore the deleted package, you must delete the new package that uses the deleted package's namespace first.
     *     </li>
     * </ul>
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @param username:    the handle for the GitHub user account
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#restore-a-package-version-for-the-authenticated-user">
     * Restore a package for a user</a>
     **/
    @RequestPath(method = POST, path = "/users/{username}/packages/{package_type}/{package_name}/restore")
    public boolean restoreUserPackage(PackageType packageType, String packageName, String username) {
        try {
            sendPostRequest(USERS_PATH + username + PACKAGES_PATH + "/" + packageType + "/" + packageName
                    + RESTORE_PATH, null);
            if (apiRequest.getResponseStatusCode() != 204) {
                printErrorResponse();
                return false;
            }
            return true;
        } catch (IOException e) {
            printErrorResponse();
            return false;
        }
    }

    /**
     * Method to get the list of the package versions for a public package owned by a specified user. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param gPackage:    the package from fetch the list
     * @param username:    the handle for the GitHub user account
     * @return package versions list as {@link ArrayList} of {@link GitHubPackageVersion} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-a-user">
     * List package versions for a package owned by a user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/packages/{package_type}/{package_name}/versions")
    public ArrayList<GitHubPackageVersion> getUserPackageVersions(PackageType packageType, GitHubPackage gPackage,
                                                                  String username) throws IOException {
        return getUserPackageVersions(packageType, username, gPackage.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the package versions for a public package owned by a specified user. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param gPackage:    the package from fetch the list
     * @param username:    the handle for the GitHub user account
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return package versions list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-a-user">
     * List package versions for a package owned by a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/packages/{package_type}/{package_name}/versions")
    public <T> T getUserPackageVersions(PackageType packageType, GitHubPackage gPackage, String username,
                                        ReturnFormat format) throws IOException {
        return getUserPackageVersions(packageType, username, gPackage.getName(), format);
    }

    /**
     * Method to get the list of the package versions for a public package owned by a specified user. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @param username:    the handle for the GitHub user account
     * @return package versions list as {@link ArrayList} of {@link GitHubPackageVersion} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-a-user">
     * List package versions for a package owned by a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/packages/{package_type}/{package_name}/versions")
    public ArrayList<GitHubPackageVersion> getUserPackageVersions(PackageType packageType, String packageName,
                                                                  String username) throws IOException {
        return getUserPackageVersions(packageType, username, packageName, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the package versions for a public package owned by a specified user. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType: type of supported package
     * @param packageName: the name of the package
     * @param username:    the handle for the GitHub user account
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return package versions list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#list-package-versions-for-a-package-owned-by-a-user">
     * List package versions for a package owned by a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/packages/{package_type}/{package_name}/versions")
    public <T> T getUserPackageVersions(PackageType packageType, String packageName, String username,
                                        ReturnFormat format) throws IOException {
        return returnPackageVersions(sendGetRequest(USERS_PATH + username + PACKAGES_PATH + "/" + packageType
                + "/" + packageName + VERSIONS_QUERY_PATH), format);
    }

    /**
     * Method to create a package versions list
     *
     * @param packageVersionsResponse: obtained from GitHub's response
     * @param format:                  return type formatter -> {@link ReturnFormat}
     * @return package versions list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnPackageVersions(String packageVersionsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(packageVersionsResponse);
            case LIBRARY_OBJECT:
                ArrayList<GitHubPackageVersion> packageVersions = new ArrayList<>();
                JSONArray jPackageVersions = new JSONArray(packageVersionsResponse);
                for (int j = 0; j < jPackageVersions.length(); j++)
                    packageVersions.add(new GitHubPackageVersion(jPackageVersions.getJSONObject(j)));
                return (T) packageVersions;
            default:
                return (T) packageVersionsResponse;
        }
    }

    /**
     * Method to get a specific package version for a public package owned by a specified user. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType:      type of supported package
     * @param gPackage:         the package from fetch the package version
     * @param packageVersionId: unique identifier of the package version
     * @param username:         the handle for the GitHub user account
     * @return package version as {@link GitHubPackageVersion} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#get-a-package-version-for-a-user">
     * Get a package version for a user</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/packages/{package_type}/{package_name}/versions/{package_version_id}")
    public GitHubPackageVersion getUserPackageVersion(PackageType packageType, GitHubPackage gPackage,
                                                      long packageVersionId, String username) throws IOException {
        return getUserPackageVersion(packageType, gPackage.getName(), packageVersionId, username, LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific package version for a public package owned by a specified user. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType:      type of supported package
     * @param gPackage:         the package from fetch the package version
     * @param packageVersionId: unique identifier of the package version
     * @param username:         the handle for the GitHub user account
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return package version as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#get-a-package-version-for-a-user">
     * Get a package version for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/users/{username}/packages/{package_type}/{package_name}/versions/{package_version_id}")
    public <T> T getUserPackageVersion(PackageType packageType, GitHubPackage gPackage, long packageVersionId,
                                       String username, ReturnFormat format) throws IOException {
        return getUserPackageVersion(packageType, gPackage.getName(), packageVersionId, username, LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific package version for a public package owned by a specified user. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType:      type of supported package
     * @param packageName:      the name of the package
     * @param packageVersionId: unique identifier of the package version
     * @param username:         the handle for the GitHub user account
     * @return package version as {@link GitHubPackageVersion} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#get-a-package-version-for-a-user">
     * Get a package version for a user</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/users/{username}/packages/{package_type}/{package_name}/versions/{package_version_id}")
    public GitHubPackageVersion getUserPackageVersion(PackageType packageType, String packageName, long packageVersionId,
                                                      String username) throws IOException {
        return getUserPackageVersion(packageType, packageName, packageVersionId, username, LIBRARY_OBJECT);
    }

    /**
     * Method to get a specific package version for a public package owned by a specified user. <br>
     * To use this endpoint, you must authenticate using an access token with the {@code "read:packages"} scope.
     * If the {@code "package_type"} belongs to a registry that only supports repository-scoped permissions, your token
     * must also include the repo scope. For the list of GitHub Packages registries that only support repository-scoped
     * permissions, see "About permissions for GitHub Packages."
     *
     * @param packageType:      type of supported package
     * @param packageName:      the name of the package
     * @param packageVersionId: unique identifier of the package version
     * @param username:         the handle for the GitHub user account
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return package version as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#get-a-package-version-for-a-user">
     * Get a package version for a user</a>
     **/
    @RequestPath(method = GET, path = "/users/{username}/packages/{package_type}/{package_name}/versions/{package_version_id}")
    public <T> T getUserPackageVersion(PackageType packageType, String packageName, long packageVersionId,
                                       String username, ReturnFormat format) throws IOException {
        return returnPackageVersion(sendGetRequest(USERS_PATH + username + PACKAGES_PATH + "/" + packageType
                + "/" + packageName + VERSIONS_QUERY_PATH + packageVersionId), format);
    }

    /**
     * Method to create a package version
     *
     * @param packageVersionResponse: obtained from GitHub's response
     * @param format:                 return type formatter -> {@link ReturnFormat}
     * @return package version as {@code "format"} defines
     **/
    @Returner
    private <T> T returnPackageVersion(String packageVersionResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(packageVersionResponse);
            case LIBRARY_OBJECT:
                return (T) new GitHubPackageVersion(new JSONObject(packageVersionResponse));
            default:
                return (T) packageVersionResponse;
        }
    }

    /**
     * Method to delete a specific package version for a user. If the package is public and the package version
     * has more than 5,000 downloads, you cannot delete the package version. In this scenario, contact GitHub support
     * for further assistance.
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType:    type of supported package
     * @param gPackage:       the package where delete the package version
     * @param username:       the handle for the GitHub user account
     * @param packageVersion: the package version to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#delete-package-version-for-a-user>
     * Delete package version for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/users/{username}/packages/{package_type}/{package_name}")
    public boolean deleteUserPackageVersion(PackageType packageType, GitHubPackage gPackage, String username,
                                            GitHubPackageVersion packageVersion) {
        return deleteUserPackageVersion(packageType, gPackage.getName(), username, packageVersion.getId());
    }

    /**
     * Method to delete a specific package version for a user. If the package is public and the package version
     * has more than 5,000 downloads, you cannot delete the package version. In this scenario, contact GitHub support
     * for further assistance.
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType:    type of supported package
     * @param packageName:    the name of the package
     * @param username:       the handle for the GitHub user account
     * @param packageVersion: the package version to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#delete-package-version-for-a-user>
     * Delete package version for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/users/{username}/packages/{package_type}/{package_name}")
    public boolean deleteUserPackageVersion(PackageType packageType, String packageName, String username,
                                            GitHubPackageVersion packageVersion) {
        return deleteUserPackageVersion(packageType, packageName, username, packageVersion.getId());
    }

    /**
     * Method to delete a specific package version for a user. If the package is public and the package version
     * has more than 5,000 downloads, you cannot delete the package version. In this scenario, contact GitHub support
     * for further assistance.
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType:      type of supported package
     * @param gPackage:         the package where delete the package version
     * @param username:         the handle for the GitHub user account
     * @param packageVersionId: unique identifier of the package version
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#delete-package-version-for-a-user>
     * Delete package version for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/users/{username}/packages/{package_type}/{package_name}")
    public boolean deleteUserPackageVersion(PackageType packageType, GitHubPackage gPackage, String username,
                                            long packageVersionId) {
        return deleteUserPackageVersion(packageType, gPackage.getName(), username, packageVersionId);
    }

    /**
     * Method to delete a specific package version for a user. If the package is public and the package version
     * has more than 5,000 downloads, you cannot delete the package version. In this scenario, contact GitHub support
     * for further assistance.
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType:      type of supported package
     * @param packageName:      the name of the package
     * @param username:         the handle for the GitHub user account
     * @param packageVersionId: unique identifier of the package version
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#delete-package-version-for-a-user>
     * Delete package version for a user</a>
     **/
    @RequestPath(method = DELETE, path = "/users/{username}/packages/{package_type}/{package_name}")
    public boolean deleteUserPackageVersion(PackageType packageType, String packageName, String username,
                                            long packageVersionId) {
        try {
            sendDeleteRequest(USERS_PATH + username + PACKAGES_PATH + "/" + packageType + "/" + packageName
                    + VERSIONS_QUERY_PATH + packageVersionId);
            if (apiRequest.getResponseStatusCode() != 204) {
                printErrorResponse();
                return false;
            }
            return true;
        } catch (IOException e) {
            printErrorResponse();
            return false;
        }
    }

    /**
     * Method to restore a specific package version for a user <br>
     * You can restore a deleted package under the following conditions:
     * <ul>
     *     <li>
     *         The package was deleted within the last 30 days.
     *     </li>
     *     <li>
     *         The same package namespace and version is still available and not reused for a new package. If the same
     *         package namespace is not available, you will not be able to restore your package. In this scenario, to
     *         restore the deleted package, you must delete the new package that uses the deleted package's namespace first.
     *     </li>
     * </ul>
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType:    type of supported package
     * @param gPackage:       the package where restore the package version
     * @param username:       the handle for the GitHub user account
     * @param packageVersion: the package version to restore
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#restore-package-version-for-a-user">
     * Restore package version for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/users/{username}/packages/{package_type}/{package_name}/versions/{package_version_id}/restore")
    public boolean restoreUserPackageVersion(PackageType packageType, GitHubPackage gPackage, String username,
                                             GitHubPackageVersion packageVersion) {
        return restoreUserPackageVersion(packageType, gPackage.getName(), username, packageVersion.getId());
    }

    /**
     * Method to restore a specific package version for a user <br>
     * You can restore a deleted package under the following conditions:
     * <ul>
     *     <li>
     *         The package was deleted within the last 30 days.
     *     </li>
     *     <li>
     *         The same package namespace and version is still available and not reused for a new package. If the same
     *         package namespace is not available, you will not be able to restore your package. In this scenario, to
     *         restore the deleted package, you must delete the new package that uses the deleted package's namespace first.
     *     </li>
     * </ul>
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType:    type of supported package
     * @param packageName:    the name of the package
     * @param username:       the handle for the GitHub user account
     * @param packageVersion: the package version to restore
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#restore-package-version-for-a-user">
     * Restore package version for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/users/{username}/packages/{package_type}/{package_name}/versions/{package_version_id}/restore")
    public boolean restoreUserPackageVersion(PackageType packageType, String packageName, String username,
                                             GitHubPackageVersion packageVersion) {
        return restoreUserPackageVersion(packageType, packageName, username, packageVersion.getId());
    }

    /**
     * Method to restore a specific package version for a user <br>
     * You can restore a deleted package under the following conditions:
     * <ul>
     *     <li>
     *         The package was deleted within the last 30 days.
     *     </li>
     *     <li>
     *         The same package namespace and version is still available and not reused for a new package. If the same
     *         package namespace is not available, you will not be able to restore your package. In this scenario, to
     *         restore the deleted package, you must delete the new package that uses the deleted package's namespace first.
     *     </li>
     * </ul>
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType:      type of supported package
     * @param gPackage:         the package where restore the package version
     * @param username:         the handle for the GitHub user account
     * @param packageVersionId: unique identifier of the package version
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#restore-package-version-for-a-user">
     * Restore package version for a user</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/users/{username}/packages/{package_type}/{package_name}/versions/{package_version_id}/restore")
    public boolean restoreUserPackageVersion(PackageType packageType, GitHubPackage gPackage, String username,
                                             long packageVersionId) {
        return restoreUserPackageVersion(packageType, gPackage.getName(), username, packageVersionId);
    }

    /**
     * Method to restore a specific package version for a user <br>
     * You can restore a deleted package under the following conditions:
     * <ul>
     *     <li>
     *         The package was deleted within the last 30 days.
     *     </li>
     *     <li>
     *         The same package namespace and version is still available and not reused for a new package. If the same
     *         package namespace is not available, you will not be able to restore your package. In this scenario, to
     *         restore the deleted package, you must delete the new package that uses the deleted package's namespace first.
     *     </li>
     * </ul>
     * To use this endpoint, you must have admin permissions in the organization and authenticate using an access token
     * with the {@code "read:packages"} and {@code "delete:packages"} scopes. In addition:
     * <ul>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that only supports repository-scoped
     *          permissions, your token must also include the repo scope. For the list of these registries, see "About
     *          permissions for GitHub Packages."
     *     </li>
     *     <li>
     *          If the {@code "package_type"} belongs to a GitHub Packages registry that supports granular permissions,
     *          you must have admin permissions to the package you want to delete. For the list of these registries, see
     *          "About permissions for GitHub Packages."
     *     </li>
     * </ul>
     *
     * @param packageType:      type of supported package
     * @param packageName:      the name of the package
     * @param username:         the handle for the GitHub user account
     * @param packageVersionId: unique identifier of the package version
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/packages#restore-package-version-for-a-user">
     * Restore package version for a user</a>
     **/
    @RequestPath(method = POST, path = "/users/{username}/packages/{package_type}/{package_name}/versions/{package_version_id}/restore")
    public boolean restoreUserPackageVersion(PackageType packageType, String packageName, String username,
                                             long packageVersionId) {
        try {
            sendPostRequest(USERS_PATH + username + PACKAGES_PATH + "/" + packageType + "/" + packageName
                    + VERSIONS_QUERY_PATH + packageVersionId + RESTORE_PATH, null);
            if (apiRequest.getResponseStatusCode() != 204) {
                printErrorResponse();
                return false;
            }
            return true;
        } catch (IOException e) {
            printErrorResponse();
            return false;
        }
    }

}
