package com.tecknobit.githubmanager.pages.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import org.json.JSONObject;

/**
 * The {@code PagesHealthCheck} class is useful to format a GitHub's pages health check
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pages#get-a-dns-health-check-for-github-pages">
 * Get a DNS health check for GitHub Pages</a>
 * @see GitHubResponse
 **/
public class PagesHealthCheck extends GitHubResponse {

    /**
     * {@code domain} of the check
     **/
    private final Domain domain;

    /**
     * {@code altDomain} alt domain of the check
     **/
    private final Domain altDomain;

    /**
     * Constructor to init a {@link PagesHealthCheck}
     *
     * @param domain:    domain of the check
     * @param altDomain: alt domain of the check
     **/
    public PagesHealthCheck(Domain domain, Domain altDomain) {
        super(null);
        this.domain = domain;
        this.altDomain = altDomain;
    }

    /**
     * Constructor to init a {@link PagesHealthCheck}
     *
     * @param jPagesHealthCheck: pages health check details as {@link JSONObject}
     **/
    public PagesHealthCheck(JSONObject jPagesHealthCheck) {
        super(jPagesHealthCheck);
        domain = new Domain(hResponse.getJSONObject("domain"));
        altDomain = new Domain(hResponse.getJSONObject("alt_domain"));
    }

    /**
     * Method to get {@link #domain} instance <br>
     * No-any params required
     *
     * @return {@link #domain} instance as {@link Domain}
     **/
    public Domain getDomain() {
        return domain;
    }

    /**
     * Method to get {@link #altDomain} instance <br>
     * No-any params required
     *
     * @return {@link #altDomain} instance as {@link Domain}
     **/
    public Domain getAltDomain() {
        return altDomain;
    }

    /**
     * The {@code Domain} class is useful to format a GitHub's domain
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class Domain extends InnerClassItem {

        /**
         * {@code host} of the domain
         **/
        private final String host;

        /**
         * {@code uri} of the domain
         **/
        private final String uri;

        /**
         * {@code nameservers} of the domain
         **/
        private final String nameservers;

        /**
         * {@code dnsResolves} whether the domain resolves dns
         **/
        private final boolean dnsResolves;

        /**
         * {@code isProxied} whether the domain is proxied
         **/
        private final boolean isProxied;

        /**
         * {@code isCloudflareIp} whether the domain is cloudflare ip
         **/
        private final boolean isCloudflareIp;

        /**
         * {@code isFastlyIp} whether the domain is fastly ip
         **/
        private final boolean isFastlyIp;

        /**
         * {@code isOldIpAddress} whether the domain is old ip address
         **/
        private final boolean isOldIpAddress;

        /**
         * {@code isARecord} whether the domain is a record
         **/
        private final boolean isARecord;

        /**
         * {@code hasCnameRecord} whether the domain has cname record
         **/
        private final boolean hasCnameRecord;

        /**
         * {@code hasMxRecordsPresent} whether the domain has mx records present
         **/
        private final boolean hasMxRecordsPresent;

        /**
         * {@code isValidDomain} whether the domain is valid domain
         **/
        private final boolean isValidDomain;

        /**
         * {@code isApexDomain} whether the domain is apex domain
         **/
        private final boolean isApexDomain;

        /**
         * {@code shouldBeARecord} whether the domain should be a record
         **/
        private final boolean shouldBeARecord;

        /**
         * {@code isCnameToGithubUserDomain} whether the domain is cname to GitHub user domain
         **/
        private final boolean isCnameToGithubUserDomain;

        /**
         * {@code isCnameToPagesDotGithubDotCom} whether the domain is cname to pages dot GitHub dot com
         **/
        private final boolean isCnameToPagesDotGithubDotCom;

        /**
         * {@code isCnameToFastly} whether the domain is cname to fastly
         **/
        private final boolean isCnameToFastly;

        /**
         * {@code isPointedToGithubPagesIp} whether the domain is pointed to GitHub pages ip
         **/
        private final boolean isPointedToGithubPagesIp;

        /**
         * {@code isNonGithubPagesIpPresent} whether the domain has non GitHub pages ip present
         **/
        private final boolean isNonGithubPagesIpPresent;

        /**
         * {@code isPagesDomain} whether the domain has pages domain
         **/
        private final boolean isPagesDomain;

        /**
         * {@code isServedByPages} whether the domain is served by pages
         **/
        private final boolean isServedByPages;

        /**
         * {@code isValid} whether the domain is valid
         **/
        private final boolean isValid;

        /**
         * {@code reason} of the domain
         **/
        private final String reason;

        /**
         * {@code respondsToHttps} whether the domain responds to https
         **/
        private final boolean respondsToHttps;

        /**
         * {@code enforcesHttps} whether the domain enforces https
         **/
        private final boolean enforcesHttps;

        /**
         * {@code httpsError} https error of the domain
         **/
        private final String httpsError;

        /**
         * {@code isHttpsEligible} whether the domain is https eligible
         **/
        private final boolean isHttpsEligible;

        /**
         * {@code caaError} caa error of the domain
         **/
        private final String caaError;

        /**
         * Constructor to init a {@link Domain}
         *
         * @param host:                          host of the domain
         * @param uri:                           uri of the domain
         * @param nameservers:                   nameservers of the domain
         * @param dnsResolves:                   whether the domain resolves dns
         * @param isProxied:                     whether the domain is proxied
         * @param isCloudflareIp:                whether the domain is cloudflare ip
         * @param isFastlyIp:                    whether the domain is fastly ip
         * @param isOldIpAddress:                whether the domain is old ip address
         * @param isARecord:                     whether the domain is a record
         * @param hasCnameRecord:                whether the domain has cname record
         * @param hasMxRecordsPresent:           whether the domain has mx records present
         * @param isValidDomain:                 whether the domain is valid domain
         * @param isApexDomain:                  whether the domain is apex domain
         * @param shouldBeARecord:               whether the domain should be a record
         * @param isCnameToGithubUserDomain:     whether the domain is cname to GitHub user domain
         * @param isCnameToPagesDotGithubDotCom: whether the domain is cname to pages dot GitHub dot com
         * @param isCnameToFastly:               whether the domain is cname to fastly
         * @param isPointedToGithubPagesIp:      whether the domain is pointed to GitHub pages ip
         * @param isNonGithubPagesIpPresent:     whether the domain has non GitHub pages ip present
         * @param isPagesDomain:                 whether the domain has pages domain
         * @param isServedByPages:               whether the domain is served by pages
         * @param isValid:                       whether the domain is valid
         * @param reason:                        reason of the domain
         * @param respondsToHttps:               domain details as {@link JSONObject}
         * @param enforcesHttps:                 whether the domain enforces https
         * @param httpsError:                    https error of the domain
         * @param isHttpsEligible:               whether the domain is https eligible
         * @param caaError:                      caa error of the domain
         **/
        public Domain(String host, String uri, String nameservers, boolean dnsResolves, boolean isProxied,
                      boolean isCloudflareIp, boolean isFastlyIp, boolean isOldIpAddress, boolean isARecord,
                      boolean hasCnameRecord, boolean hasMxRecordsPresent, boolean isValidDomain, boolean isApexDomain,
                      boolean shouldBeARecord, boolean isCnameToGithubUserDomain, boolean isCnameToPagesDotGithubDotCom,
                      boolean isCnameToFastly, boolean isPointedToGithubPagesIp, boolean isNonGithubPagesIpPresent,
                      boolean isPagesDomain, boolean isServedByPages, boolean isValid, String reason,
                      boolean respondsToHttps, boolean enforcesHttps, String httpsError, boolean isHttpsEligible,
                      String caaError) {
            super(null);
            this.host = host;
            this.uri = uri;
            this.nameservers = nameservers;
            this.dnsResolves = dnsResolves;
            this.isProxied = isProxied;
            this.isCloudflareIp = isCloudflareIp;
            this.isFastlyIp = isFastlyIp;
            this.isOldIpAddress = isOldIpAddress;
            this.isARecord = isARecord;
            this.hasCnameRecord = hasCnameRecord;
            this.hasMxRecordsPresent = hasMxRecordsPresent;
            this.isValidDomain = isValidDomain;
            this.isApexDomain = isApexDomain;
            this.shouldBeARecord = shouldBeARecord;
            this.isCnameToGithubUserDomain = isCnameToGithubUserDomain;
            this.isCnameToPagesDotGithubDotCom = isCnameToPagesDotGithubDotCom;
            this.isCnameToFastly = isCnameToFastly;
            this.isPointedToGithubPagesIp = isPointedToGithubPagesIp;
            this.isNonGithubPagesIpPresent = isNonGithubPagesIpPresent;
            this.isPagesDomain = isPagesDomain;
            this.isServedByPages = isServedByPages;
            this.isValid = isValid;
            this.reason = reason;
            this.respondsToHttps = respondsToHttps;
            this.enforcesHttps = enforcesHttps;
            this.httpsError = httpsError;
            this.isHttpsEligible = isHttpsEligible;
            this.caaError = caaError;
        }

        /**
         * Constructor to init a {@link Domain}
         *
         * @param jDomain: domain details as {@link JSONObject}
         **/
        public Domain(JSONObject jDomain) {
            super(jDomain);
            host = hItem.getString("host");
            uri = hItem.getString("uri");
            nameservers = hItem.getString("nameservers");
            dnsResolves = hItem.getBoolean("dns_resolves");
            isProxied = hItem.getBoolean("is_proxied");
            isCloudflareIp = hItem.getBoolean("is_cloudflare_ip");
            isFastlyIp = hItem.getBoolean("is_fastly_ip");
            isOldIpAddress = hItem.getBoolean("is_old_ip_address");
            isARecord = hItem.getBoolean("is_a_record");
            hasCnameRecord = hItem.getBoolean("has_cname_record");
            hasMxRecordsPresent = hItem.getBoolean("has_mx_records_present");
            isValidDomain = hItem.getBoolean("is_valid_domain");
            isApexDomain = hItem.getBoolean("is_apex_domain");
            shouldBeARecord = hItem.getBoolean("should_be_a_record");
            isCnameToGithubUserDomain = hItem.getBoolean("is_cname_to_github_user_domain");
            isCnameToPagesDotGithubDotCom = hItem.getBoolean("is_cname_to_pages_dot_github_dot_com");
            isCnameToFastly = hItem.getBoolean("is_cname_to_fastly");
            isPointedToGithubPagesIp = hItem.getBoolean("is_pointed_to_github_pages_ip");
            isNonGithubPagesIpPresent = hItem.getBoolean("is_non_github_pages_ip_present");
            isPagesDomain = hItem.getBoolean("is_pages_domain");
            isServedByPages = hItem.getBoolean("is_served_by_pages");
            isValid = hItem.getBoolean("is_valid");
            reason = hItem.getString("reason");
            respondsToHttps = hItem.getBoolean("responds_to_https");
            enforcesHttps = hItem.getBoolean("enforces_https");
            httpsError = hItem.getString("https_error");
            isHttpsEligible = hItem.getBoolean("is_https_eligible");
            caaError = hItem.getString("caa_error");
        }

        /**
         * Method to get {@link #host} instance <br>
         * No-any params required
         *
         * @return {@link #host} instance as {@link String}
         **/
        public String getHost() {
            return host;
        }

        /**
         * Method to get {@link #uri} instance <br>
         * No-any params required
         *
         * @return {@link #uri} instance as {@link String}
         **/
        public String getUri() {
            return uri;
        }

        /**
         * Method to get {@link #nameservers} instance <br>
         * No-any params required
         *
         * @return {@link #nameservers} instance as {@link String}
         **/
        public String getNameservers() {
            return nameservers;
        }

        /**
         * Method to get {@link #dnsResolves} instance <br>
         * No-any params required
         *
         * @return {@link #dnsResolves} instance as boolean
         **/
        public boolean isDnsResolves() {
            return dnsResolves;
        }

        /**
         * Method to get {@link #isProxied} instance <br>
         * No-any params required
         *
         * @return {@link #isProxied} instance as boolean
         **/
        public boolean isProxied() {
            return isProxied;
        }

        /**
         * Method to get {@link #isCloudflareIp} instance <br>
         * No-any params required
         *
         * @return {@link #isCloudflareIp} instance as boolean
         **/
        public boolean isCloudflareIp() {
            return isCloudflareIp;
        }

        /**
         * Method to get {@link #isFastlyIp} instance <br>
         * No-any params required
         *
         * @return {@link #isFastlyIp} instance as boolean
         **/
        public boolean isFastlyIp() {
            return isFastlyIp;
        }

        /**
         * Method to get {@link #isOldIpAddress} instance <br>
         * No-any params required
         *
         * @return {@link #isOldIpAddress} instance as boolean
         **/
        public boolean isOldIpAddress() {
            return isOldIpAddress;
        }

        /**
         * Method to get {@link #isARecord} instance <br>
         * No-any params required
         *
         * @return {@link #isARecord} instance as boolean
         **/
        public boolean isARecord() {
            return isARecord;
        }

        /**
         * Method to get {@link #hasCnameRecord} instance <br>
         * No-any params required
         *
         * @return {@link #hasCnameRecord} instance as boolean
         **/
        public boolean isHasCnameRecord() {
            return hasCnameRecord;
        }

        /**
         * Method to get {@link #hasMxRecordsPresent} instance <br>
         * No-any params required
         *
         * @return {@link #hasMxRecordsPresent} instance as boolean
         **/
        public boolean isHasMxRecordsPresent() {
            return hasMxRecordsPresent;
        }

        /**
         * Method to get {@link #isValidDomain} instance <br>
         * No-any params required
         *
         * @return {@link #isValidDomain} instance as boolean
         **/
        public boolean isValidDomain() {
            return isValidDomain;
        }

        /**
         * Method to get {@link #isApexDomain} instance <br>
         * No-any params required
         *
         * @return {@link #isApexDomain} instance as boolean
         **/
        public boolean isApexDomain() {
            return isApexDomain;
        }

        /**
         * Method to get {@link #shouldBeARecord} instance <br>
         * No-any params required
         *
         * @return {@link #shouldBeARecord} instance as boolean
         **/
        public boolean isShouldBeARecord() {
            return shouldBeARecord;
        }

        /**
         * Method to get {@link #isCnameToGithubUserDomain} instance <br>
         * No-any params required
         *
         * @return {@link #isCnameToGithubUserDomain} instance as boolean
         **/
        public boolean isCnameToGithubUserDomain() {
            return isCnameToGithubUserDomain;
        }

        /**
         * Method to get {@link #isCnameToPagesDotGithubDotCom} instance <br>
         * No-any params required
         *
         * @return {@link #isCnameToPagesDotGithubDotCom} instance as boolean
         **/
        public boolean isCnameToPagesDotGithubDotCom() {
            return isCnameToPagesDotGithubDotCom;
        }

        /**
         * Method to get {@link #isCnameToFastly} instance <br>
         * No-any params required
         *
         * @return {@link #isCnameToFastly} instance as boolean
         **/
        public boolean isCnameToFastly() {
            return isCnameToFastly;
        }

        /**
         * Method to get {@link #isPointedToGithubPagesIp} instance <br>
         * No-any params required
         *
         * @return {@link #isPointedToGithubPagesIp} instance as boolean
         **/
        public boolean isPointedToGithubPagesIp() {
            return isPointedToGithubPagesIp;
        }

        /**
         * Method to get {@link #isNonGithubPagesIpPresent} instance <br>
         * No-any params required
         *
         * @return {@link #isNonGithubPagesIpPresent} instance as boolean
         **/
        public boolean isNonGithubPagesIpPresent() {
            return isNonGithubPagesIpPresent;
        }

        /**
         * Method to get {@link #isPagesDomain} instance <br>
         * No-any params required
         *
         * @return {@link #isPagesDomain} instance as boolean
         **/
        public boolean isPagesDomain() {
            return isPagesDomain;
        }

        /**
         * Method to get {@link #isServedByPages} instance <br>
         * No-any params required
         *
         * @return {@link #isServedByPages} instance as boolean
         **/
        public boolean isServedByPages() {
            return isServedByPages;
        }

        /**
         * Method to get {@link #isValid} instance <br>
         * No-any params required
         *
         * @return {@link #isValid} instance as boolean
         **/
        public boolean isValid() {
            return isValid;
        }

        /**
         * Method to get {@link #reason} instance <br>
         * No-any params required
         *
         * @return {@link #reason} instance as {@link String}
         **/
        public String getReason() {
            return reason;
        }

        /**
         * Method to get {@link #respondsToHttps} instance <br>
         * No-any params required
         *
         * @return {@link #respondsToHttps} instance as boolean
         **/
        public boolean canRespondsToHttps() {
            return respondsToHttps;
        }

        /**
         * Method to get {@link #enforcesHttps} instance <br>
         * No-any params required
         *
         * @return {@link #enforcesHttps} instance as boolean
         **/
        public boolean enforcesHttps() {
            return enforcesHttps;
        }

        /**
         * Method to get {@link #httpsError} instance <br>
         * No-any params required
         *
         * @return {@link #httpsError} instance as {@link String}
         **/
        public String getHttpsError() {
            return httpsError;
        }

        /**
         * Method to get {@link #isHttpsEligible} instance <br>
         * No-any params required
         *
         * @return {@link #isHttpsEligible} instance as boolean
         **/
        public boolean isHttpsEligible() {
            return isHttpsEligible;
        }

        /**
         * Method to get {@link #caaError} instance <br>
         * No-any params required
         *
         * @return {@link #caaError} instance as {@link String}
         **/
        public String getCaaError() {
            return caaError;
        }

    }

}
