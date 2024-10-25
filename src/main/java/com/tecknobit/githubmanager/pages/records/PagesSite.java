package com.tecknobit.githubmanager.pages.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import org.json.JSONObject;

import java.util.ArrayList;



/**
 * The {@code PagesSite} class is useful to format a GitHub's pages site
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/pages#get-a-github-pages-site">
 *             Get a GitHub Pages site</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/pages#create-a-github-pages-site">
 *             Create a GitHub Pages site</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class PagesSite extends GitHubResponse {

    /**
     * {@code PagesSiteStatus} list of available pages site statuses
     **/
    public enum PagesSiteStatus {

        /**
         * {@code built} status
         **/
        built,

        /**
         * {@code building} status
         **/
        building,

        /**
         * {@code errored} status
         **/
        errored,

    }

    /**
     * {@code ProtectedDomainState} list of available protected domain states
     **/
    public enum ProtectedDomainState {

        /**
         * {@code pending} domain state
         **/
        pending,

        /**
         * {@code verified} domain state
         **/
        verified,

        /**
         * {@code unverified} domain state
         **/
        unverified,

    }

    /**
     * {@code BuildType} list of available build types
     **/
    public enum BuildType {

        /**
         * {@code legacy} list of available build type
         **/
        legacy,

        /**
         * {@code workflow} list of available build type
         **/
        workflow

    }

    /**
     * {@code url} the API address for accessing this Page resource
     **/
    private final String url;

    /**
     * {@code status} the status of the most recent build of the Page
     **/
    private final PagesSiteStatus status;

    /**
     * {@code cname} the Pages site's custom domain
     **/
    private final String cname;

    /**
     * {@code protectedDomainState} the state if the domain is verified
     **/
    private final ProtectedDomainState protectedDomainState;

    /**
     * {@code pending_domain_unverified_at} the timestamp when a pending domain becomes unverified
     **/
    private final String pendingDomainUnverifiedAt;

    /**
     * {@code custom404} whether the Page has a custom 404 page
     **/
    private final boolean custom404;

    /**
     * {@code htmlUrl} the web address the Page can be accessed from
     **/
    private final String htmlUrl;

    /**
     * {@code buildType} the process in which the Page will be built
     **/
    private final BuildType buildType;

    /**
     * {@code source} pages Source Hash
     **/
    private final Source source;

    /**
     * {@code BuildType} whether the GitHub Pages site is publicly visible. If set to {@code "true"}, the site is
     * accessible to anyone on the internet. If set to {@code "false"}, the site will only be accessible to users who
     * have at least {@code "read"} access to the repository that published the site.
     **/
    private final boolean isPublic;

    /**
     * {@code httpsCertificate} pages Https Certificate
     **/
    private final HttpsCertificate httpsCertificate;

    /**
     * {@code httpsEnforced} whether https is enabled on the domain
     **/
    private final boolean httpsEnforced;

    /**
     * Constructor to init a {@link PagesSite}
     *
     * @param url:                       the API address for accessing this Page resource
     * @param status:                    the status of the most recent build of the Page
     * @param cname:                     the Pages site's custom domain
     * @param protectedDomainState:      the state if the domain is verified
     * @param pendingDomainUnverifiedAt: the timestamp when a pending domain becomes unverified
     * @param custom404:                 whether the Page has a custom 404 page
     * @param htmlUrl:the                web address the Page can be accessed from
     * @param buildType:                 the process in which the Page will be built
     * @param source:                    pages Source Hash
     * @param isPublic:                  whether the GitHub Pages site is publicly visible
     * @param httpsCertificate:          pages Https Certificate
     * @param httpsEnforced:             whether https is enabled on the domain
     **/
    public PagesSite(String url, PagesSiteStatus status, String cname, ProtectedDomainState protectedDomainState,
                     String pendingDomainUnverifiedAt, boolean custom404, String htmlUrl, BuildType buildType,
                     Source source, boolean isPublic, HttpsCertificate httpsCertificate, boolean httpsEnforced) {
        super(null);
        this.url = url;
        this.status = status;
        this.cname = cname;
        this.protectedDomainState = protectedDomainState;
        this.pendingDomainUnverifiedAt = pendingDomainUnverifiedAt;
        this.custom404 = custom404;
        this.htmlUrl = htmlUrl;
        this.buildType = buildType;
        this.source = source;
        this.isPublic = isPublic;
        this.httpsCertificate = httpsCertificate;
        this.httpsEnforced = httpsEnforced;
    }

    /**
     * Constructor to init a {@link PagesSite}
     *
     * @param jPagesSite: pages site details as {@link JSONObject}
     **/
    public PagesSite(JSONObject jPagesSite) {
        super(jPagesSite);
        url = hResponse.getString("url");
        status = PagesSiteStatus.valueOf(hResponse.getString("status"));
        cname = hResponse.getString("cname");
        String sEnum = hResponse.getString("protected_domain_state");
        if (sEnum != null)
            protectedDomainState = ProtectedDomainState.valueOf(sEnum);
        else
            protectedDomainState = null;
        pendingDomainUnverifiedAt = hResponse.getString("pending_domain_unverified_at");
        custom404 = hResponse.getBoolean("custom_404");
        htmlUrl = hResponse.getString("html_url");
        sEnum = hResponse.getString("build_type");
        if (sEnum != null)
            buildType = BuildType.valueOf(sEnum);
        else
            buildType = null;
        JSONObject jItem = hResponse.getJSONObject("source");
        if (jItem != null)
            source = new Source(jItem);
        else
            source = null;
        isPublic = hResponse.getBoolean("public");
        jItem = hResponse.getJSONObject("https_certificate");
        if (jItem != null)
            httpsCertificate = new HttpsCertificate(jItem);
        else
            httpsCertificate = null;
        httpsEnforced = hResponse.getBoolean("https_enforced");
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
     * Method to get {@link #status} instance <br>
     * No-any params required
     *
     * @return {@link #status} instance as {@link PagesSiteStatus}
     **/
    public PagesSiteStatus getStatus() {
        return status;
    }

    /**
     * Method to get {@link #cname} instance <br>
     * No-any params required
     *
     * @return {@link #cname} instance as {@link String}
     **/
    public String getCname() {
        return cname;
    }

    /**
     * Method to get {@link #protectedDomainState} instance <br>
     * No-any params required
     *
     * @return {@link #protectedDomainState} instance as {@link ProtectedDomainState}
     **/
    public ProtectedDomainState getProtectedDomainState() {
        return protectedDomainState;
    }

    /**
     * Method to get {@link #pendingDomainUnverifiedAt} instance <br>
     * No-any params required
     *
     * @return {@link #pendingDomainUnverifiedAt} instance as {@link String}
     **/
    public String getPendingDomainUnverifiedAt() {
        return pendingDomainUnverifiedAt;
    }

    /**
     * Method to get {@link #pendingDomainUnverifiedAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #pendingDomainUnverifiedAt} timestamp as long
     **/
    public long getPendingDomainUnverifiedAtTimestamp() {
        return timeFormatter.formatAsTimestamp(pendingDomainUnverifiedAt);
    }

    /**
     * Method to get {@link #custom404} instance <br>
     * No-any params required
     *
     * @return {@link #custom404} instance as boolean
     **/
    public boolean isCustom404() {
        return custom404;
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
     * Method to get {@link #buildType} instance <br>
     * No-any params required
     *
     * @return {@link #buildType} instance as {@link BuildType}
     **/
    public BuildType getBuildType() {
        return buildType;
    }

    /**
     * Method to get {@link #source} instance <br>
     * No-any params required
     *
     * @return {@link #source} instance as {@link Source}
     **/
    public Source getSource() {
        return source;
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
     * Method to get {@link #httpsCertificate} instance <br>
     * No-any params required
     *
     * @return {@link #httpsCertificate} instance as {@link HttpsCertificate}
     **/
    public HttpsCertificate getHttpsCertificate() {
        return httpsCertificate;
    }

    /**
     * Method to get {@link #httpsEnforced} instance <br>
     * No-any params required
     *
     * @return {@link #httpsEnforced} instance as boolean
     **/
    public boolean isHttpsEnforced() {
        return httpsEnforced;
    }

    /**
     * The {@code Source} class is useful to format a GitHub's source
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class Source extends InnerClassItem {

        /**
         * {@code branch} of the source
         **/
        private final String branch;

        /**
         * {@code path} of the source
         **/
        private final String path;

        /**
         * Constructor to init a {@link Source}
         *
         * @param branch: branch of the source
         * @apiNote you can use this constructor to create a new one {@link PagesSite}
         **/
        public Source(String branch) {
            this(branch, "/");
        }

        /**
         * Constructor to init a {@link Source}
         *
         * @param branch: branch of the source
         * @param path:   path of the source
         **/
        public Source(String branch, String path) {
            super(null);
            this.branch = branch;
            this.path = path;
        }

        /**
         * Constructor to init a {@link Source}
         *
         * @param jSource: source details as {@link JSONObject}
         **/
        public Source(JSONObject jSource) {
            super(jSource);
            branch = hItem.getString("branch");
            path = hItem.getString("path");
        }

        /**
         * Method to get {@link #branch} instance <br>
         * No-any params required
         *
         * @return {@link #branch} instance as {@link String}
         **/
        public String getBranch() {
            return branch;
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

    }

    /**
     * The {@code HttpsCertificate} class is useful to format a GitHub's https certificate
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class HttpsCertificate extends InnerClassItem {

        /**
         * {@code HttpsCertificateState} list of available states for the https certificate
         **/
        public enum HttpsCertificateState {

            /**
             * {@code _new} state
             **/
            _new("new"),

            /**
             * {@code authorization_created} state
             **/
            authorization_created("authorization_created"),

            /**
             * {@code authorization_pending} state
             **/
            authorization_pending("authorization_pending"),

            /**
             * {@code authorized} state
             **/
            authorized("authorized"),

            /**
             * {@code authorization_revoked} state
             **/
            authorization_revoked("authorization_revoked"),

            /**
             * {@code issued} state
             **/
            issued("issued"),

            /**
             * {@code uploaded} state
             **/
            uploaded("uploaded"),

            /**
             * {@code approved} state
             **/
            approved("approved"),

            /**
             * {@code errored} state
             **/
            errored("errored"),

            /**
             * {@code bad_authz} state
             **/
            bad_authz("bad_authz"),

            /**
             * {@code destroy_pending} state
             **/
            destroy_pending("destroy_pending"),

            /**
             * {@code dns_changed} state
             **/
            dns_changed("dns_changed");

            /**
             * {@code state} value
             **/
            private final String state;

            /**
             * Constructor to init a {@link HttpsCertificateState}
             *
             * @param state: state value
             **/
            HttpsCertificateState(String state) {
                this.state = state;
            }

            /**
             * Method to get {@link #state} instance <br>
             * No-any params required
             *
             * @return {@link #state} instance as {@link String}
             **/
            @Override
            public String toString() {
                return state;
            }

        }

        /**
         * {@code state} of the certificate
         **/
        private final HttpsCertificateState state;

        /**
         * {@code description} of the certificate
         **/
        private final String description;

        /**
         * {@code domains} array of the domain set and its alternate name (if it is configured)
         **/
        private final ArrayList<String> domains;

        /**
         * {@code expiresAt} expiration date of the certificate
         **/
        private final String expiresAt;

        /**
         * Constructor to init a {@link HttpsCertificate}
         *
         * @param state:       state of the certificate
         * @param description: description of the certificate
         * @param domains:     array of the domain set and its alternate name (if it is configured)
         * @param expiresAt:   expiration date of the certificate
         **/
        public HttpsCertificate(HttpsCertificateState state, String description, ArrayList<String> domains,
                                String expiresAt) {
            super(null);
            this.state = state;
            this.description = description;
            this.domains = domains;
            this.expiresAt = expiresAt;
        }

        /**
         * Constructor to init a {@link HttpsCertificate}
         *
         * @param jHttpsCertificate: https certificate details as {@link JSONObject}
         **/
        public HttpsCertificate(JSONObject jHttpsCertificate) {
            super(jHttpsCertificate);
            state = HttpsCertificateState.valueOf(hItem.getString("state"));
            description = hItem.getString("description");
            domains = returnStringsList(hItem.getJSONArray("domains"));
            expiresAt = hItem.getString("expires_at");
        }

        /**
         * Method to get {@link #state} instance <br>
         * No-any params required
         *
         * @return {@link #state} instance as {@link HttpsCertificateState}
         **/
        public HttpsCertificateState getState() {
            return state;
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
         * Method to get {@link #domains} instance <br>
         * No-any params required
         *
         * @return {@link #domains} instance as {@link ArrayList} of {@link String}
         **/
        public ArrayList<String> getDomains() {
            return domains;
        }

        /**
         * Method to get {@link #expiresAt} instance <br>
         * No-any params required
         *
         * @return {@link #expiresAt} instance as {@link String}
         **/
        public String getExpiresAt() {
            return expiresAt;
        }

        /**
         * Method to get {@link #expiresAt} timestamp <br>
         * No-any params required
         *
         * @return {@link #expiresAt} timestamp as long
         **/
        public long getExpiresAtTimestamp() {
            return timeFormatter.formatAsTimestamp(expiresAt);
        }

    }

}
