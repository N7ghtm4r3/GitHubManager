package com.tecknobit.githubmanager.dependabot.alerts.records;

import com.tecknobit.githubmanager.GitHubManager.SeverityLevel;
import com.tecknobit.githubmanager.records.generic.GitHubAlert;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import com.tecknobit.githubmanager.users.users.records.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;
import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code DependabotAlert} class is useful to format a GitHub's dependabot alert
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/dependabot/alerts#list-dependabot-alerts-for-an-enterprise">
 *             List Dependabot alerts for an enterprise</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/dependabot/alerts#list-dependabot-alerts-for-an-organization">
 *             List Dependabot alerts for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/dependabot/alerts#list-dependabot-alerts-for-a-repository">
 *             List Dependabot alerts for a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/dependabot/alerts#get-a-dependabot-alert">
 *             Get a Dependabot alert</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/dependabot/alerts#update-a-dependabot-alert">
 *             Update a Dependabot alert</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see GitHubAlert
 **/
public class DependabotAlert extends GitHubAlert {

    /**
     * {@code state} the state of the Dependabot alert
     **/
    private final DependabotAlertState state;

    /**
     * {@code dependency} details for the vulnerable dependency
     **/
    private final Dependency dependency;

    /**
     * {@code securityAdvisory} details for the GitHub Security Advisory
     **/
    private final SecurityAdvisory securityAdvisory;

    /**
     * {@code securityVulnerability} details pertaining to one vulnerable version range for the advisory
     **/
    private final Vulnerability securityVulnerability;

    /**
     * {@code dismissedReason} the reason that the alert was dismissed
     **/
    private final DependabotDismissedReason dismissedReason;

    /**
     * Constructor to init a {@link DependabotAlert}
     *
     * @param number                : the security alert number
     * @param createdAt             : the time that the alert was created in ISO 8601 format: "YYYY-MM-DDTHH:MM:SSZ"
     * @param updatedAt             : the time that the alert was updated in ISO 8601 format: "YYYY-MM-DDTHH:MM:SSZ"
     * @param url                   : the REST API URL of the alert resource
     * @param htmlUrl               : the GitHub URL of the alert resource
     * @param fixedAt               : the time that the alert was no longer detected and was considered fixed in ISO 8601 format: "YYYY-MM-DDTHH:MM:SSZ"
     * @param dismissedBy           : dismissed by user
     * @param dismissedAt           : the time that the alert was dismissed in ISO 8601 format: "YYYY-MM-DDTHH:MM:SSZ"
     * @param dismissedComment      : the dismissal comment associated with the dismissal of the alert
     * @param repository            : repository of the alert
     * @param state                 : the state of the Dependabot alert
     * @param dependency            : details for the vulnerable dependency
     * @param securityAdvisory      : details for the GitHub Security Advisory
     * @param securityVulnerability : details pertaining to one vulnerable version range for the advisory
     * @param dismissedReason       : the reason that the alert was dismissed
     **/
    public DependabotAlert(long number, String createdAt, String updatedAt, String url, String htmlUrl, String fixedAt,
                           User dismissedBy, String dismissedAt, String dismissedComment, Repository repository,
                           DependabotAlertState state, Dependency dependency, SecurityAdvisory securityAdvisory,
                           Vulnerability securityVulnerability, DependabotDismissedReason dismissedReason) {
        super(number, createdAt, updatedAt, url, htmlUrl, fixedAt, dismissedBy, dismissedAt, dismissedComment, repository);
        this.state = state;
        this.dependency = dependency;
        this.securityAdvisory = securityAdvisory;
        this.securityVulnerability = securityVulnerability;
        this.dismissedReason = dismissedReason;
    }

    /**
     * Constructor to init a {@link DependabotAlert}
     *
     * @param jDependabotAlert : alert details as {@link JSONObject}
     **/
    public DependabotAlert(JSONObject jDependabotAlert) {
        super(jDependabotAlert);
        state = DependabotAlertState.valueOf(hResponse.getString("state"));
        dependency = new Dependency(hResponse.getJSONObject("dependency", new JSONObject()));
        securityAdvisory = new SecurityAdvisory(hResponse.getJSONObject("security_advisory", new JSONObject()));
        securityVulnerability = new Vulnerability(hResponse.getJSONObject("security_vulnerability", new JSONObject()));
        String sDismissedReason = hResponse.getString("dismissed_reason");
        if (sDismissedReason != null)
            dismissedReason = DependabotDismissedReason.valueOf(sDismissedReason);
        else
            dismissedReason = null;
    }

    /**
     * Method to get {@link #state} instance <br>
     * No-any params required
     *
     * @return {@link #state} instance as {@link DependabotAlertState}
     **/
    public DependabotAlertState getState() {
        return state;
    }

    /**
     * Method to get {@link #dependency} instance <br>
     * No-any params required
     *
     * @return {@link #dependency} instance as {@link Dependency}
     **/
    public Dependency getDependency() {
        return dependency;
    }

    /**
     * Method to get {@link #securityAdvisory} instance <br>
     * No-any params required
     *
     * @return {@link #securityAdvisory} instance as {@link SecurityAdvisory}
     **/
    public SecurityAdvisory getSecurityAdvisory() {
        return securityAdvisory;
    }

    /**
     * Method to get {@link #securityVulnerability} instance <br>
     * No-any params required
     *
     * @return {@link #securityVulnerability} instance as {@link Vulnerability}
     **/
    public Vulnerability getSecurityVulnerability() {
        return securityVulnerability;
    }

    /**
     * Method to get {@link #dismissedReason} instance <br>
     * No-any params required
     *
     * @return {@link #dismissedReason} instance as {@link DependabotDismissedReason}
     **/
    public DependabotDismissedReason getDismissedReason() {
        return dismissedReason;
    }

    /**
     * {@code DependabotAlertState} list of available dependabot alert states
     **/
    public enum DependabotAlertState {

        /**
         * {@code dismissed} state
         **/
        dismissed,

        /**
         * {@code fixed} state
         **/
        fixed,

        /**
         * {@code open} state
         **/
        open

    }

    /**
     * {@code Ecosystem} list of available ecosystems
     **/
    public enum Ecosystem {

        /**
         * {@code composer} ecosystem
         **/
        composer,

        /**
         * {@code go} ecosystem
         **/
        go,

        /**
         * {@code maven} ecosystem
         **/
        maven,

        /**
         * {@code npm} ecosystem
         **/
        npm,

        /**
         * {@code nuget} ecosystem
         **/
        nuget,

        /**
         * {@code pip} ecosystem
         **/
        pip,

        /**
         * {@code pub} ecosystem
         **/
        pub,

        /**
         * {@code rubygems} ecosystem
         **/
        rubygems,

        /**
         * {@code rust} ecosystem
         **/
        rust,

    }

    /**
     * {@code DependabotDismissedReason} list of available dependabot alert dismissed reasons
     **/
    public enum DependabotDismissedReason {

        /**
         * {@code fix_started} dismissed reason
         **/
        fix_started,

        /**
         * {@code inaccurate} dismissed reason
         **/
        inaccurate,

        /**
         * {@code no_bandwidth} dismissed reason
         **/
        no_bandwidth,

        /**
         * {@code not_used} dismissed reason
         **/
        not_used,

        /**
         * {@code tolerable_risk} dismissed reason
         **/
        tolerable_risk

    }

    /**
     * The {@code Dependency} class is useful to format a GitHub's dependency
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class Dependency extends InnerClassItem {

        /**
         * {@code dPackage} details for the vulnerable package
         **/
        private final Package dPackage;

        /**
         * {@code manifestPath} the full path to the dependency manifest file, relative to the root of the repository
         **/
        private final String manifestPath;

        /**
         * {@code scope} the execution scope of the vulnerable dependency
         **/
        private final Scope scope;

        /**
         * Constructor to init a {@link Dependency}
         *
         * @param dPackage:     details for the vulnerable package
         * @param manifestPath: the full path to the dependency manifest file, relative to the root of the repository
         * @param scope:        the execution scope of the vulnerable dependency
         **/
        public Dependency(Package dPackage, String manifestPath, Scope scope) {
            super(null);
            this.dPackage = dPackage;
            this.manifestPath = manifestPath;
            this.scope = scope;
        }

        /**
         * Constructor to init a {@link Dependency}
         *
         * @param jDependency: dependency details as {@link JSONObject}
         **/
        public Dependency(JSONObject jDependency) {
            super(jDependency);
            dPackage = new Package(hItem.getJSONObject("package", new JSONObject()));
            manifestPath = hItem.getString("manifest_path");
            String sScope = hItem.getString("scope");
            if (sScope != null)
                scope = Scope.valueOf(sScope);
            else
                scope = null;
        }

        /**
         * Method to get {@link #dPackage} instance <br>
         * No-any params required
         *
         * @return {@link #dPackage} instance as {@link Package}
         **/
        public Package getPackage() {
            return dPackage;
        }

        /**
         * Method to get {@link #manifestPath} instance <br>
         * No-any params required
         *
         * @return {@link #manifestPath} instance as {@link String}
         **/
        public String getManifestPath() {
            return manifestPath;
        }

        /**
         * Method to get {@link #scope} instance <br>
         * No-any params required
         *
         * @return {@link #scope} instance as {@link Scope}
         **/
        public Scope getScope() {
            return scope;
        }

        /**
         * {@code Scope} list of available scopes
         **/
        public enum Scope {

            /**
             * {@code unknown} scope
             **/
            unknown,

            /**
             * {@code development} scope
             **/
            development,

            /**
             * {@code runtime} scope
             **/
            runtime

        }

    }

    /**
     * The {@code SecurityAdvisory} class is useful to format a GitHub's security advisory
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class SecurityAdvisory extends InnerClassItem {

        /**
         * {@code ghsaId} the unique GitHub Security Advisory ID assigned to the advisory
         **/
        private final String ghsaId;

        /**
         * {@code cveId} the unique CVE ID assigned to the advisor
         **/
        private final String cveId;

        /**
         * {@code summary} a short, plain text summary of the advisory
         **/
        private final String summary;

        /**
         * {@code description} a long-form Markdown-supported description of the advisory
         **/
        private final String description;

        /**
         * {@code vulnerabilities} vulnerable version range information for the advisory
         **/
        private final ArrayList<Vulnerability> vulnerabilities;

        /**
         * {@code severity} the severity of the advisory
         **/
        private final SeverityLevel severity;

        /**
         * {@code cvss} details for the advisory pertaining to the Common Vulnerability Scoring System
         **/
        private final CVSS cvss;

        /**
         * {@code cwes} details for the advisory pertaining to Common Weakness Enumeration
         **/
        private final ArrayList<CWE> cwes;

        /**
         * {@code identifiers} values that identify this advisory among security information sources
         **/
        private final ArrayList<Identifier> identifiers;

        /**
         * {@code references} links to additional advisory information
         **/
        private final ArrayList<String> references;

        /**
         * {@code publishedAt} the time that the advisory was published in ISO 8601 format: {@code "YYYY-MM-DDTHH:MM:SSZ"}
         **/
        private final String publishedAt;

        /**
         * {@code updatedAt} the time that the advisory was last modified in ISO 8601 format: {@code "YYYY-MM-DDTHH:MM:SSZ"}
         **/
        private final String updatedAt;

        /**
         * {@code withdrawnAt} the time that the advisory was withdrawn in ISO 8601 format: {@code "YYYY-MM-DDTHH:MM:SSZ"}
         **/
        private final String withdrawnAt;

        /**
         * Constructor to init a {@link SecurityAdvisory}
         *
         * @param ghsaId:          the unique GitHub Security Advisory ID assigned to the advisory
         * @param cveId:           the unique CVE ID assigned to the advisor
         * @param summary:         a short, plain text summary of the advisory
         * @param description:     a long-form Markdown-supported description of the advisory
         * @param vulnerabilities: vulnerable version range information for the advisory
         * @param severity:        the severity of the advisory
         * @param cvss:            details for the advisory pertaining to the Common Vulnerability Scoring System
         * @param cwes:            details for the advisory pertaining to Common Weakness Enumeration
         * @param identifiers:     values that identify this advisory among security information sources
         * @param references:      links to additional advisory information
         * @param publishedAt:     the time that the advisory was published in ISO 8601 format: {@code "YYYY-MM-DDTHH:MM:SSZ"}
         * @param updatedAt:       the time that the advisory was last modified in ISO 8601 format: {@code "YYYY-MM-DDTHH:MM:SSZ"}
         * @param withdrawnAt:     the time that the advisory was withdrawn in ISO 8601 format: {@code "YYYY-MM-DDTHH:MM:SSZ"}
         **/
        public SecurityAdvisory(String ghsaId, String cveId, String summary, String description,
                                ArrayList<Vulnerability> vulnerabilities, SeverityLevel severity, CVSS cvss,
                                ArrayList<CWE> cwes, ArrayList<Identifier> identifiers, ArrayList<String> references,
                                String publishedAt, String updatedAt, String withdrawnAt) {
            super(null);
            this.ghsaId = ghsaId;
            this.cveId = cveId;
            this.summary = summary;
            this.description = description;
            this.vulnerabilities = vulnerabilities;
            this.severity = severity;
            this.cvss = cvss;
            this.cwes = cwes;
            this.identifiers = identifiers;
            this.references = references;
            this.publishedAt = publishedAt;
            this.updatedAt = updatedAt;
            this.withdrawnAt = withdrawnAt;
        }

        /**
         * Constructor to init a {@link SecurityAdvisory}
         *
         * @param jSecurityAdvisory: security advisory details as {@link JSONObject}
         **/
        public SecurityAdvisory(JSONObject jSecurityAdvisory) {
            super(jSecurityAdvisory);
            ghsaId = hItem.getString("ghsa_id");
            cveId = hItem.getString("cve_id");
            summary = hItem.getString("summary");
            description = hItem.getString("description");
            vulnerabilities = new ArrayList<>();
            JSONArray jVulnerabilities = hItem.getJSONArray("vulnerabilities", new JSONArray());
            for (int j = 0; j < jVulnerabilities.length(); j++)
                vulnerabilities.add(new Vulnerability(jVulnerabilities.getJSONObject(j)));
            severity = SeverityLevel.valueOf(hItem.getString("severity"));
            cvss = new CVSS(hItem.getJSONObject("cvss", new JSONObject()));
            cwes = new ArrayList<>();
            JSONArray jCWES = hItem.getJSONArray("cwes", new JSONArray());
            for (int j = 0; j < jCWES.length(); j++)
                cwes.add(new CWE(jCWES.getJSONObject(j)));
            identifiers = new ArrayList<>();
            JSONArray jIdentifier = hItem.getJSONArray("identifiers", new JSONArray());
            for (int j = 0; j < jIdentifier.length(); j++)
                identifiers.add(new Identifier(jIdentifier.getJSONObject(j)));
            references = new ArrayList<>();
            JSONArray jReferences = hItem.getJSONArray("references", new JSONArray());
            for (int j = 0; j < jReferences.length(); j++)
                references.add(jReferences.getJSONObject(j).getString("url"));
            publishedAt = hItem.getString("published_at");
            updatedAt = hItem.getString("updated_at");
            withdrawnAt = hItem.getString("withdrawn_at");
        }

        /**
         * Method to get {@link #ghsaId} instance <br>
         * No-any params required
         *
         * @return {@link #ghsaId} instance as {@link String}
         **/
        public String getGhsaId() {
            return ghsaId;
        }

        /**
         * Method to get {@link #cveId} instance <br>
         * No-any params required
         *
         * @return {@link #cveId} instance as {@link String}
         **/
        public String getCveId() {
            return cveId;
        }

        /**
         * Method to get {@link #summary} instance <br>
         * No-any params required
         *
         * @return {@link #summary} instance as {@link String}
         **/
        public String getSummary() {
            return summary;
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
         * Method to get {@link #vulnerabilities} instance <br>
         * No-any params required
         *
         * @return {@link #vulnerabilities} instance as {@link ArrayList} of {@link Vulnerability}
         **/
        public ArrayList<Vulnerability> getVulnerabilities() {
            return vulnerabilities;
        }

        /**
         * Method to get {@link #severity} instance <br>
         * No-any params required
         *
         * @return {@link #severity} instance as {@link SeverityLevel}
         **/
        public SeverityLevel getSeverity() {
            return severity;
        }

        /**
         * Method to get {@link #cvss} instance <br>
         * No-any params required
         *
         * @return {@link #cvss} instance as {@link CVSS}
         **/
        public CVSS getCvss() {
            return cvss;
        }

        /**
         * Method to get {@link #cwes} instance <br>
         * No-any params required
         *
         * @return {@link #cwes} instance as {@link ArrayList} of {@link CWE}
         **/
        public ArrayList<CWE> getCwes() {
            return cwes;
        }

        /**
         * Method to get {@link #identifiers} instance <br>
         * No-any params required
         *
         * @return {@link #identifiers} instance as {@link ArrayList} of {@link Identifier}
         **/
        public ArrayList<Identifier> getIdentifiers() {
            return identifiers;
        }

        /**
         * Method to get {@link #references} instance <br>
         * No-any params required
         *
         * @return {@link #references} instance as {@link ArrayList} of {@link String}
         **/
        public ArrayList<String> getReferences() {
            return references;
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
         * Method to get {@link #withdrawnAt} instance <br>
         * No-any params required
         *
         * @return {@link #withdrawnAt} instance as {@link String}
         **/
        public String getWithdrawnAt() {
            return withdrawnAt;
        }

        /**
         * Method to get {@link #withdrawnAt} timestamp <br>
         * No-any params required
         *
         * @return {@link #withdrawnAt} timestamp as long
         **/
        public long getWithdrawnTimestamp() {
            return getDateTimestamp(withdrawnAt);
        }

        /**
         * The {@code CVSS} class is useful to format a GitHub's CVSS
         *
         * @author N7ghtm4r3 - Tecknobit
         * @see InnerClassItem
         **/
        public static class CVSS extends InnerClassItem {

            /**
             * {@code score} the overall CVSS score of the advisory
             **/
            private final double score;

            /**
             * {@code vectorString} the full CVSS vector string for the advisory
             **/
            private final String vectorString;

            /**
             * Constructor to init a {@link CVSS}
             *
             * @param score:        the overall CVSS score of the advisory
             * @param vectorString: the full CVSS vector string for the advisory
             **/
            public CVSS(double score, String vectorString) {
                super(null);
                this.score = score;
                this.vectorString = vectorString;
            }

            /**
             * Constructor to init a {@link CVSS}
             *
             * @param jCVSS: CVSS details as {@link JSONObject}
             **/
            public CVSS(JSONObject jCVSS) {
                super(jCVSS);
                score = hItem.getDouble("score", 0);
                vectorString = hItem.getString("vector_string");
            }

            /**
             * Method to get {@link #score} instance <br>
             * No-any params required
             *
             * @return {@link #score} instance as double
             **/
            public double getScore() {
                return score;
            }

            /**
             * Method to get {@link #score} instance
             *
             * @param decimals: number of digits to round final value
             * @return {@link #score} instance rounded with decimal digits inserted
             * @throws IllegalArgumentException if decimalDigits is negative
             **/
            public double getScore(int decimals) {
                return roundValue(score, decimals);
            }

            /**
             * Method to get {@link #vectorString} instance <br>
             * No-any params required
             *
             * @return {@link #vectorString} instance as {@link String}
             **/
            public String getVectorString() {
                return vectorString;
            }

        }

        /**
         * The {@code CWE} class is useful to format a GitHub's CWE
         *
         * @author N7ghtm4r3 - Tecknobit
         * @see InnerClassItem
         **/
        public static class CWE extends InnerClassItem {

            /**
             * {@code cweId} the unique CWE ID
             **/
            private final String cweId;

            /**
             * {@code name} the short, plain text name of the CWE
             **/
            private final String name;

            /**
             * Constructor to init a {@link CWE}
             *
             * @param cweId:the unique CWE ID
             * @param name:     the short, plain text name of the CWE
             **/
            public CWE(String cweId, String name) {
                super(null);
                this.cweId = cweId;
                this.name = name;
            }

            /**
             * Constructor to init a {@link CWE}
             *
             * @param jCWE: CWE details as {@link JSONObject}
             **/
            public CWE(JSONObject jCWE) {
                super(jCWE);
                cweId = hItem.getString("cwe_id");
                name = hItem.getString("name");
            }

            /**
             * Method to get {@link #cweId} instance <br>
             * No-any params required
             *
             * @return {@link #cweId} instance as {@link String}
             **/
            public String getCweId() {
                return cweId;
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

        }

        /**
         * The {@code Identifier} class is useful to format a GitHub's identifier
         *
         * @author N7ghtm4r3 - Tecknobit
         * @see InnerClassItem
         **/
        public static class Identifier extends InnerClassItem {

            /**
             * {@code type} the type of advisory identifier
             **/
            private final IdentifierType type;
            /**
             * {@code value} the value of the advisory identifier
             **/
            private final String value;

            /**
             * Constructor to init a {@link Identifier}
             *
             * @param type:  the type of advisory identifier
             * @param value: the value of the advisory identifier
             **/
            public Identifier(IdentifierType type, String value) {
                super(null);
                this.type = type;
                this.value = value;
            }

            /**
             * Constructor to init a {@link Identifier}
             *
             * @param jIdentifier: identifier details as {@link JSONObject}
             **/
            public Identifier(JSONObject jIdentifier) {
                super(jIdentifier);
                type = IdentifierType.valueOf(hItem.getString("type"));
                value = hItem.getString("value");
            }

            /**
             * Method to get {@link #type} instance <br>
             * No-any params required
             *
             * @return {@link #type} instance as {@link IdentifierType}
             **/
            public IdentifierType getType() {
                return type;
            }

            /**
             * Method to get {@link #value} instance <br>
             * No-any params required
             *
             * @return {@link #value} instance as {@link String}
             **/
            public String getValue() {
                return value;
            }

            /**
             * {@code IdentifierType} list of available identifier types
             **/
            public enum IdentifierType {

                /**
                 * {@code CVE} identifier type
                 **/
                CVE,

                /**
                 * {@code GHSA} identifier type
                 **/
                GHSA

            }

        }

    }

    /**
     * The {@code Vulnerability} class is useful to format a GitHub's vulnerability
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class Vulnerability extends InnerClassItem {

        /**
         * {@code vPackage} details for the vulnerable package
         **/
        private final Package vPackage;

        /**
         * {@code severity} the severity of the vulnerability
         **/
        private final SeverityLevel severity;

        /**
         * {@code vulnerableVersionRange} conditions that identify vulnerable versions of this vulnerability's packager
         **/
        private final String vulnerableVersionRange;

        /**
         * {@code firstPatchedVersion} details pertaining to the package version that patches this vulnerability
         **/
        private final String firstPatchedVersion;

        /**
         * Constructor to init a {@link Vulnerability}
         *
         * @param vPackage:               details for the vulnerable package
         * @param severity:               the severity of the vulnerability
         * @param vulnerableVersionRange: conditions that identify vulnerable versions of this vulnerability's packager
         * @param firstPatchedVersion:    details pertaining to the package version that patches this vulnerability
         **/
        public Vulnerability(Package vPackage, SeverityLevel severity, String vulnerableVersionRange,
                             String firstPatchedVersion) {
            super(null);
            this.vPackage = vPackage;
            this.severity = severity;
            this.vulnerableVersionRange = vulnerableVersionRange;
            this.firstPatchedVersion = firstPatchedVersion;
        }

        /**
         * Constructor to init a {@link Vulnerability}
         *
         * @param jVulnerability: vulnerability details as {@link JSONObject}
         **/
        public Vulnerability(JSONObject jVulnerability) {
            super(jVulnerability);
            vPackage = new Package(hItem.getJSONObject("package", new JSONObject()));
            severity = SeverityLevel.valueOf(hItem.getString("severity"));
            vulnerableVersionRange = hItem.getString("vulnerable_version_range");
            firstPatchedVersion = hItem.getJsonHelper("first_patched_version").getString("identifier");
        }

        /**
         * Method to get {@link #vPackage} instance <br>
         * No-any params required
         *
         * @return {@link #vPackage} instance as {@link Package}
         **/
        public Package getPackage() {
            return vPackage;
        }

        /**
         * Method to get {@link #severity} instance <br>
         * No-any params required
         *
         * @return {@link #severity} instance as {@link SeverityLevel}
         **/
        public SeverityLevel getSeverity() {
            return severity;
        }

        /**
         * Method to get {@link #vulnerableVersionRange} instance <br>
         * No-any params required
         *
         * @return {@link #vulnerableVersionRange} instance as {@link String}
         **/
        public String getVulnerableVersionRange() {
            return vulnerableVersionRange;
        }

        /**
         * Method to get {@link #firstPatchedVersion} instance <br>
         * No-any params required
         *
         * @return {@link #firstPatchedVersion} instance as {@link String}
         **/
        public String getFirstPatchedVersion() {
            return firstPatchedVersion;
        }

    }

    /**
     * The {@code Package} class is useful to format a GitHub's package
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class Package extends InnerClassItem {

        /**
         * {@code ecosystem} the package's language or package management ecosystem
         **/
        private final String ecosystem;

        /**
         * {@code name} the unique package name within its ecosystem
         **/
        private final String name;

        /**
         * Constructor to init a {@link Package}
         *
         * @param ecosystem: the package's language or package management ecosystem
         * @param name:      the unique package name within its ecosystem
         **/
        public Package(String ecosystem, String name) {
            super(null);
            this.ecosystem = ecosystem;
            this.name = name;
        }

        /**
         * Constructor to init a {@link Package}
         *
         * @param jPackage: package details as {@link JSONObject}
         **/
        public Package(JSONObject jPackage) {
            super(jPackage);
            ecosystem = hItem.getString("ecosystem");
            name = hItem.getString("name");
        }

        /**
         * Method to get {@link #ecosystem} instance <br>
         * No-any params required
         *
         * @return {@link #ecosystem} instance as {@link String}
         **/
        public String getEcosystem() {
            return ecosystem;
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

    }

}
