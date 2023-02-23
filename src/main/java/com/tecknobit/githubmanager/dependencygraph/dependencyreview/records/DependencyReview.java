package com.tecknobit.githubmanager.dependencygraph.dependencyreview.records;

import com.tecknobit.githubmanager.GitHubManager.SeverityLevel;
import com.tecknobit.githubmanager.dependabot.alerts.records.DependabotAlert.Dependency.Scope;
import com.tecknobit.githubmanager.dependabot.alerts.records.DependabotAlert.Ecosystem;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The {@code DependencyReview} class is useful to format a GitHub's dependency review
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependency-graph/dependency-review">
 * Get a diff of the dependencies between commits</a>
 * @see GitHubResponse
 **/
public class DependencyReview extends GitHubResponse {

    /**
     * {@code ChangeType} list of available change types
     **/
    public enum ChangeType {

        /**
         * {@code added} change type
         **/
        added,

        /**
         * {@code removed} change type
         **/
        removed

    }

    /**
     * {@code changeType} change type value
     **/
    private final ChangeType changeType;

    /**
     * {@code manifest} path to package
     **/
    private final String manifest;

    /**
     * {@code ecosystem} value
     **/
    private final Ecosystem ecosystem;

    /**
     * {@code name} value
     **/
    private final String name;

    /**
     * {@code version} value
     **/
    private final String version;

    /**
     * {@code packageUrl} url of the package
     **/
    private final String packageUrl;

    /**
     * {@code license} value
     **/
    private final String license;

    /**
     * {@code sourceRepositoryUrl} source repository url
     **/
    private final String sourceRepositoryUrl;

    /**
     * {@code vulnerabilities} list of {@link ReviewVulnerability}
     **/
    private final ArrayList<ReviewVulnerability> vulnerabilities;

    /**
     * {@code scope} value
     **/
    private final Scope scope;

    /**
     * Constructor to init a {@link DependencyReview}
     *
     * @param changeType          : change type value
     * @param manifest            : path to package
     * @param ecosystem           : ecosystem value
     * @param name                : name value
     * @param version             : version value
     * @param packageUrl          : url of the package
     * @param license             : scope value
     * @param sourceRepositoryUrl : source repository url
     * @param vulnerabilities     : list of {@link ReviewVulnerability}
     * @param scope               : scope value
     **/
    public DependencyReview(ChangeType changeType, String manifest, Ecosystem ecosystem, String name, String version,
                            String packageUrl, String license, String sourceRepositoryUrl,
                            ArrayList<ReviewVulnerability> vulnerabilities, Scope scope) {
        super(null);
        this.changeType = changeType;
        this.manifest = manifest;
        this.ecosystem = ecosystem;
        this.name = name;
        this.version = version;
        this.packageUrl = packageUrl;
        this.license = license;
        this.sourceRepositoryUrl = sourceRepositoryUrl;
        this.vulnerabilities = vulnerabilities;
        this.scope = scope;
    }

    /**
     * Constructor to init a {@link DependencyReview}
     *
     * @param jDependencyReview: dependency review details as {@link JSONObject}
     **/
    public DependencyReview(JSONObject jDependencyReview) {
        super(jDependencyReview);
        changeType = ChangeType.valueOf(hResponse.getString("change_type"));
        manifest = hResponse.getString("manifest");
        ecosystem = Ecosystem.valueOf(hResponse.getString("ecosystem"));
        name = hResponse.getString("name");
        version = hResponse.getString("version");
        packageUrl = hResponse.getString("package_url");
        license = hResponse.getString("license");
        sourceRepositoryUrl = hResponse.getString("source_repository_url");
        vulnerabilities = new ArrayList<>();
        JSONArray jVulnerabilities = hResponse.getJSONArray("vulnerabilities", new JSONArray());
        for (int j = 0; j < jVulnerabilities.length(); j++)
            vulnerabilities.add(new ReviewVulnerability(jVulnerabilities.getJSONObject(j)));
        scope = Scope.valueOf(hResponse.getString("scope", Scope.unknown.name()));
    }

    /**
     * Method to get {@link #changeType} instance <br>
     * No-any params required
     *
     * @return {@link #changeType} instance as {@link ChangeType}
     **/
    public ChangeType getChangeType() {
        return changeType;
    }

    /**
     * Method to get {@link #manifest} instance <br>
     * No-any params required
     *
     * @return {@link #manifest} instance as {@link String}
     **/
    public String getManifest() {
        return manifest;
    }

    /**
     * Method to get {@link #ecosystem} instance <br>
     * No-any params required
     *
     * @return {@link #ecosystem} instance as {@link Ecosystem}
     **/
    public Ecosystem getEcosystem() {
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

    /**
     * Method to get {@link #version} instance <br>
     * No-any params required
     *
     * @return {@link #version} instance as {@link String}
     **/
    public String getVersion() {
        return version;
    }

    /**
     * Method to get {@link #packageUrl} instance <br>
     * No-any params required
     *
     * @return {@link #packageUrl} instance as {@link String}
     **/
    public String getPackageUrl() {
        return packageUrl;
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
     * Method to get {@link #sourceRepositoryUrl} instance <br>
     * No-any params required
     *
     * @return {@link #sourceRepositoryUrl} instance as {@link String}
     **/
    public String getSourceRepositoryUrl() {
        return sourceRepositoryUrl;
    }

    /**
     * Method to get {@link #vulnerabilities} instance <br>
     * No-any params required
     *
     * @return {@link #vulnerabilities} instance as {@link ArrayList} of {@link ReviewVulnerability}
     **/
    public ArrayList<ReviewVulnerability> getVulnerabilities() {
        return vulnerabilities;
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
     * The {@code ReviewVulnerability} class is useful to format a GitHub's review vulnerability
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class ReviewVulnerability extends InnerClassItem {

        /**
         * {@code severity} value
         **/
        private final SeverityLevel severity;

        /**
         * {@code advisoryGHSAId} advisory GHSA identifier
         **/
        private final String advisoryGHSAId;

        /**
         * {@code advisorySummary} advisory summary value
         **/
        private final String advisorySummary;

        /**
         * {@code advisoryUrl} advisory url value
         **/
        private final String advisoryUrl;

        /**
         * Constructor to init a {@link ReviewVulnerability}
         *
         * @param severity:                severity value
         * @param advisoryGHSAId:          advisory GHSA identifier
         * @param advisorySummary:advisory summary value
         * @param advisoryUrl:             advisory url value
         **/
        public ReviewVulnerability(SeverityLevel severity, String advisoryGHSAId, String advisorySummary, String advisoryUrl) {
            super(null);
            this.severity = severity;
            this.advisoryGHSAId = advisoryGHSAId;
            this.advisorySummary = advisorySummary;
            this.advisoryUrl = advisoryUrl;
        }

        /**
         * Constructor to init a {@link ReviewVulnerability}
         *
         * @param jReviewVulnerability: review vulnerability details as {@link JSONObject}
         **/
        public ReviewVulnerability(JSONObject jReviewVulnerability) {
            super(jReviewVulnerability);
            severity = SeverityLevel.valueOf(hItem.getString("severity"));
            advisoryGHSAId = hItem.getString("advisory_ghsa_id");
            advisorySummary = hItem.getString("advisory_summary");
            advisoryUrl = hItem.getString("advisory_url");
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
         * Method to get {@link #advisoryGHSAId} instance <br>
         * No-any params required
         *
         * @return {@link #advisoryGHSAId} instance as {@link String}
         **/
        public String getAdvisoryGHSAId() {
            return advisoryGHSAId;
        }

        /**
         * Method to get {@link #advisorySummary} instance <br>
         * No-any params required
         *
         * @return {@link #advisorySummary} instance as {@link String}
         **/
        public String getAdvisorySummary() {
            return advisorySummary;
        }

        /**
         * Method to get {@link #advisoryUrl} instance <br>
         * No-any params required
         *
         * @return {@link #advisoryUrl} instance as {@link String}
         **/
        public String getAdvisoryUrl() {
            return advisoryUrl;
        }

    }

}
