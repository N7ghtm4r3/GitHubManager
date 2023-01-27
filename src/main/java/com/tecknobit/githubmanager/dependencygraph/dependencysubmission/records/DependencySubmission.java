package com.tecknobit.githubmanager.dependencygraph.dependencysubmission.records;

import com.tecknobit.githubmanager.dependabot.alerts.records.DependabotAlert.Dependency.Scope;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code DependencySubmission} class is useful to format a GitHub's dependency submission
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/dependency-graph/dependency-submission">
 * Create a snapshot of dependencies for a repository</a>
 * @see GitHubResponse
 **/
public class DependencySubmission extends GitHubResponse {

    /**
     * {@code id} ID of the created snapshot
     **/
    private final long id;

    /**
     * {@code changeType} the time at which the snapshot was created
     **/
    private final String createdAt;

    /**
     * {@code result} of the creation
     **/
    private final String result;

    /**
     * Constructor to init a {@link DependencySubmission}
     *
     * @param id        : ID of the created snapshot
     * @param createdAt : the time at which the snapshot was created
     * @param result    : result of the creation
     **/
    public DependencySubmission(long id, String createdAt, String result) {
        super(null);
        this.id = id;
        this.createdAt = createdAt;
        this.result = result;
    }

    /**
     * Constructor to init a {@link DependencySubmission}
     *
     * @param jDependencySubmission: dependency submission details as {@link JSONObject}
     **/
    public DependencySubmission(JSONObject jDependencySubmission) {
        super(jDependencySubmission);
        id = hResponse.getLong("id", 0);
        createdAt = hResponse.getString("created_at");
        result = hResponse.getString("message");
    }

    /**
     * Method to get {@link #id} instance <br>
     * Any params required
     *
     * @return {@link #id} instance as long
     **/
    public long getId() {
        return id;
    }

    /**
     * Method to get {@link #createdAt} instance <br>
     * Any params required
     *
     * @return {@link #createdAt} instance as {@link String}
     **/
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Method to get {@link #createdAt} timestamp <br>
     * Any params required
     *
     * @return {@link #createdAt} timestamp as long
     **/
    public long getCreatedAtTimestamp() {
        return getDateTimestamp(createdAt);
    }

    /**
     * Method to get {@link #result} instance <br>
     * Any params required
     *
     * @return {@link #result} instance as {@link String}
     **/
    public String getResult() {
        return result;
    }

    /**
     * The {@code SubmissionJob} class is useful to format a GitHub's submission job
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class SubmissionJob extends InnerClassItem {

        /**
         * {@code id} the external ID of the job
         **/
        private final String id;

        /**
         * {@code correlator} correlator provides a key that is used to group snapshots submitted over time. Only the "latest"
         * submitted snapshot for a given combination of job.correlator and detector.name will be considered when calculating
         * a repository's current dependencies. Correlator should be as unique as it takes to distinguish all detection
         * runs for a given "wave" of CI workflow you run. If you're using GitHub Actions, a good default value for this
         * could be the environment variables GITHUB_WORKFLOW and GITHUB_JOB concatenated together. If you're using a build
         * matrix, then you'll also need to add additional key(s) to distinguish between each submission inside a matrix variation.
         **/
        private final String correlator;

        /**
         * {@code htmlUrl} the url for the job
         **/
        private final String htmlUrl;

        /**
         * Constructor to init a {@link SubmissionJob}
         *
         * @param id:         the external ID of the job
         * @param correlator: used to group snapshots
         **/
        public SubmissionJob(String id, String correlator) {
            this(id, correlator, null);
        }

        /**
         * Constructor to init a {@link SubmissionJob}
         *
         * @param id:         the external ID of the job
         * @param correlator: used to group snapshots
         * @param htmlUrl:    the url for the job
         **/
        public SubmissionJob(String id, String correlator, String htmlUrl) {
            super(null);
            this.id = id;
            this.correlator = correlator;
            this.htmlUrl = htmlUrl;
        }

        /**
         * Constructor to init a {@link SubmissionJob}
         *
         * @param jSubmissionJob: submission job details as {@link JSONObject}
         **/
        public SubmissionJob(JSONObject jSubmissionJob) {
            super(jSubmissionJob);
            id = hItem.getString("id");
            correlator = hItem.getString("correlator");
            htmlUrl = hItem.getString("html_url");
        }

        /**
         * Method to get {@link #id} instance <br>
         * Any params required
         *
         * @return {@link #id} instance as {@link String}
         **/
        public String getId() {
            return id;
        }

        /**
         * Method to get {@link #correlator} instance <br>
         * Any params required
         *
         * @return {@link #correlator} instance as {@link String}
         **/
        public String getCorrelator() {
            return correlator;
        }

        /**
         * Method to get {@link #htmlUrl} instance <br>
         * Any params required
         *
         * @return {@link #htmlUrl} instance as {@link String}
         **/
        public String getHtmlUrl() {
            return htmlUrl;
        }

    }

    /**
     * The {@code Detector} class is useful to format a GitHub's detector
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class Detector extends InnerClassItem {

        /**
         * {@code name} the name of the detector used
         **/
        private final String name;

        /**
         * {@code version} the version of the detector used
         **/
        private final String version;

        /**
         * {@code url} the url of the detector used
         **/
        private final String url;

        /**
         * Constructor to init a {@link Detector}
         *
         * @param name:    the name of the detector used
         * @param version: the version of the detector used
         * @param url:     the url of the detector used
         **/
        public Detector(String name, String version, String url) {
            super(null);
            this.name = name;
            this.version = version;
            this.url = url;
        }

        /**
         * Constructor to init a {@link Detector}
         *
         * @param jDetector: detector details as {@link JSONObject}
         **/
        public Detector(JSONObject jDetector) {
            super(jDetector);
            name = hItem.getString("name");
            version = hItem.getString("version");
            url = hItem.getString("url");
        }

        /**
         * Method to get {@link #name} instance <br>
         * Any params required
         *
         * @return {@link #name} instance as {@link String}
         **/
        public String getName() {
            return name;
        }

        /**
         * Method to get {@link #version} instance <br>
         * Any params required
         *
         * @return {@link #version} instance as {@link String}
         **/
        public String getVersion() {
            return version;
        }

        /**
         * Method to get {@link #url} instance <br>
         * Any params required
         *
         * @return {@link #url} instance as {@link String}
         **/
        public String getUrl() {
            return url;
        }

    }

    /**
     * The {@code Manifests} class is useful to format a GitHub's manifests
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class Manifests extends InnerClassItem {

        /**
         * {@code key} a user-defined key to represent an item in manifests
         **/
        private final ManifestsKey key;

        /**
         * {@code metadata} user-defined metadata to store domain-specific information limited to 8 keys with scalar values
         **/
        private final Object metadata;

        /**
         * {@code resolved} a collection of resolved package dependencies
         **/
        private final ArrayList<Resolved> resolved;

        /**
         * Constructor to init a {@link Manifests}
         *
         * @param key:      a user-defined key to represent an item in manifests
         * @param metadata: user-defined metadata to store domain-specific information limited to 8 keys with scalar values
         * @param resolved: a collection of resolved package dependencies
         **/
        public Manifests(ManifestsKey key, Object metadata, ArrayList<Resolved> resolved) {
            super(null);
            this.key = key;
            this.metadata = metadata;
            this.resolved = resolved;
        }

        /**
         * Constructor to init a {@link Manifests}
         *
         * @param jManifests: manifests details as {@link JSONObject}
         **/
        public Manifests(JSONObject jManifests) {
            super(jManifests);
            key = new ManifestsKey(hItem.getJSONObject("key", new JSONObject()));
            metadata = hItem.get("metadata");
            resolved = new ArrayList<>();
            JSONArray jResolved = hItem.getJSONArray("resolved", new JSONArray());
            for (int j = 0; j < jResolved.length(); j++)
                resolved.add(new Resolved(jResolved.getJSONObject(j)));
        }

        /**
         * Method to get {@link #key} instance <br>
         * Any params required
         *
         * @return {@link #key} instance as {@link ManifestsKey}
         **/
        public ManifestsKey getKey() {
            return key;
        }

        /**
         * Method to get {@link #metadata} instance <br>
         * Any params required
         *
         * @return {@link #metadata} instance as {@link Object}
         **/
        public Object getMetadata() {
            return metadata;
        }

        /**
         * Method to get {@link #resolved} instance <br>
         * Any params required
         *
         * @return {@link #resolved} instance as {@link Collection} of {@link Resolved}
         **/
        public Collection<Resolved> getResolved() {
            return resolved;
        }

        /**
         * The {@code ManifestsKey} class is useful to format a GitHub's manifests key
         *
         * @author N7ghtm4r3 - Tecknobit
         * @see InnerClassItem
         **/
        public static class ManifestsKey extends InnerClassItem {

            /**
             * {@code name} the name of the manifest
             **/
            private final String name;

            /**
             * {@code sourceLocation} the path of the manifest file relative to the root of the Git repository
             **/
            private final String sourceLocation;

            /**
             * Constructor to init a {@link ManifestsKey}
             *
             * @param name:           the name of the manifest
             * @param sourceLocation: the path of the manifest file relative to the root of the Git repository
             **/
            public ManifestsKey(String name, String sourceLocation) {
                super(null);
                this.name = name;
                this.sourceLocation = sourceLocation;
            }

            /**
             * Constructor to init a {@link ManifestsKey}
             *
             * @param jManifestKey: manifests key details as {@link JSONObject}
             **/
            public ManifestsKey(JSONObject jManifestKey) {
                super(jManifestKey);
                name = hItem.getString("name");
                sourceLocation = hItem.getString("source_location");
            }

            /**
             * Method to get {@link #name} instance <br>
             * Any params required
             *
             * @return {@link #name} instance as {@link String}
             **/
            public String getName() {
                return name;
            }

            /**
             * Method to get {@link #sourceLocation} instance <br>
             * Any params required
             *
             * @return {@link #sourceLocation} instance as {@link String}
             **/
            public String getSourceLocation() {
                return sourceLocation;
            }

        }

        /**
         * The {@code Resolved} class is useful to format a GitHub's resolved
         *
         * @author N7ghtm4r3 - Tecknobit
         * @see InnerClassItem
         **/
        public static class Resolved extends InnerClassItem {

            /**
             * {@code Relationship} list of available relationships
             **/
            public enum Relationship {

                /**
                 * {@code direct} relationship
                 **/
                direct,

                /**
                 * {@code indirect} relationship
                 **/
                indirect

            }

            /**
             * {@code packageUrl} package-url (PURL) of dependency. See <a href="https://github.com/package-url/purl-spec">this</a>
             * for more details
             **/
            private final String packageUrl;

            /**
             * {@code metadata} user-defined metadata to store domain-specific information limited to 8 keys with scalar
             * values
             **/
            private final Object metadata;

            /**
             * {@code relationship} a  notation of whether a dependency is requested directly by this manifest or is a dependency
             * of another dependency
             **/
            private final Relationship relationship;

            /**
             * {@code scope} a notation of whether the dependency is required for the primary build artifact (runtime) or
             * is only used for development. Future versions of this specification may allow for more granular scopes
             **/
            private final Scope scope;

            /**
             * {@code dependencies} array of package-url (PURLs) of direct child dependencies
             **/
            private final ArrayList<String> dependencies;

            /**
             * Constructor to init a {@link Resolved}
             *
             * @param packageUrl:   package-url (PURL) of dependency
             * @param metadata:     user-defined metadata to store domain-specific information limited to 8 keys with scalar values
             * @param relationship: notation of whether a dependency is requested directly by this manifest or is a dependency
             *                      of another dependency
             * @param scope:        a notation of whether the dependency is required for the primary build artifact (runtime) or
             *                      is only used for development. Future versions of this specification may allow for more granular scopes
             * @param dependencies: resolved details as {@link JSONObject}
             **/
            public Resolved(String packageUrl, Object metadata, Relationship relationship, Scope scope,
                            ArrayList<String> dependencies) {
                super(null);
                this.packageUrl = packageUrl;
                this.metadata = metadata;
                this.relationship = relationship;
                this.scope = scope;
                this.dependencies = dependencies;
            }

            /**
             * Constructor to init a {@link Resolved}
             *
             * @param jResolved: resolved details as {@link JSONObject}
             **/
            public Resolved(JSONObject jResolved) {
                super(jResolved);
                packageUrl = hItem.getString("package_url");
                metadata = hItem.get("metadata");
                relationship = Relationship.valueOf(hItem.getString("relationship"));
                scope = Scope.valueOf(hItem.getString("scope"));
                dependencies = returnStringsList(hItem.getJSONArray("dependencies"));
            }

            /**
             * Method to get {@link #packageUrl} instance <br>
             * Any params required
             *
             * @return {@link #packageUrl} instance as {@link String}
             **/
            public String getPackageUrl() {
                return packageUrl;
            }

            /**
             * Method to get {@link #metadata} instance <br>
             * Any params required
             *
             * @return {@link #metadata} instance as {@link Object}
             **/
            public Object getMetadata() {
                return metadata;
            }

            /**
             * Method to get {@link #relationship} instance <br>
             * Any params required
             *
             * @return {@link #relationship} instance as {@link Relationship}
             **/
            public Relationship getRelationship() {
                return relationship;
            }

            /**
             * Method to get {@link #scope} instance <br>
             * Any params required
             *
             * @return {@link #scope} instance as {@link Scope}
             **/
            public Scope getScope() {
                return scope;
            }

            /**
             * Method to get {@link #dependencies} instance <br>
             * Any params required
             *
             * @return {@link #dependencies} instance as {@link Collection} of {@link String}
             **/
            public Collection<String> getDependencies() {
                return dependencies;
            }

        }

    }

}
