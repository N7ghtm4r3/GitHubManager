package com.tecknobit.githubmanager.ratelimit.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import org.json.JSONObject;

/**
 * The {@code RateLimit} class is useful to format a GitHub's rate limit
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/rate-limit#get-rate-limit-status-for-the-authenticated-user">
 * Get rate limit status for the authenticated user</a>
 * @see GitHubResponse
 **/
public class RateOverview extends GitHubResponse {

    /**
     * {@code resources} of the rate limit
     **/
    private final Resources resources;

    /**
     * {@code rate} value
     **/
    private final RateLimit rate;

    /**
     * Constructor to init a {@link RateOverview}
     *
     * @param resources: resources of the rate limit
     * @param rate:      rate value
     **/
    public RateOverview(Resources resources, RateLimit rate) {
        super(null);
        this.resources = resources;
        this.rate = rate;
    }

    /**
     * Constructor to init a {@link RateOverview}
     *
     * @param jRateOverview: rate overview details as {@link JSONObject}
     **/
    public RateOverview(JSONObject jRateOverview) {
        super(jRateOverview);
        resources = new Resources(hResponse.getJSONObject("resources"));
        rate = new RateLimit(hResponse.getJSONObject("rate"));
    }

    /**
     * Method to get {@link #resources} instance <br>
     * No-any params required
     *
     * @return {@link #resources} instance as {@link Resources}
     **/
    public Resources getResources() {
        return resources;
    }

    /**
     * Method to get {@link #rate} instance <br>
     * No-any params required
     *
     * @return {@link #rate} instance as {@link RateLimit}
     **/
    public RateLimit getRate() {
        return rate;
    }

    /**
     * The {@code Resources} class is useful to format a GitHub's rate limit resources
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class Resources extends InnerClassItem {

        /**
         * {@code core} rate limit
         **/
        private final RateLimit core;

        /**
         * {@code graphql} rate limit
         **/
        private final RateLimit graphql;

        /**
         * {@code search} rate limit
         **/
        private final RateLimit search;

        /**
         * {@code sourceImport} source import rate limit
         **/
        private final RateLimit sourceImport;

        /**
         * {@code integrationManifest} integration manifest rate limit
         **/
        private final RateLimit integrationManifest;

        /**
         * {@code codeScanningUpload} code scanning upload rate limit
         **/
        private final RateLimit codeScanningUpload;

        /**
         * {@code actionsRunnerRegistration} actions runner registration rate limit
         **/
        private final RateLimit actionsRunnerRegistration;

        /**
         * {@code scim} rate limit
         **/
        private final RateLimit scim;

        /**
         * {@code dependencySnapshots} dependency snapshots rate limit
         **/
        private final RateLimit dependencySnapshots;

        /**
         * Constructor to init a {@link Resources}
         *
         * @param core:                      core rate limit
         * @param graphql:                   graphql rate limit
         * @param search:search              rate limit
         * @param sourceImport:              source import rate limit
         * @param integrationManifest:       integration manifest rate limit
         * @param codeScanningUpload:        code scanning upload rate limit
         * @param actionsRunnerRegistration: actions runner registration rate limit
         * @param scim:                      scim rate limit
         * @param dependencySnapshots:       dependency snapshots rate limit
         **/
        public Resources(RateLimit core, RateLimit graphql, RateLimit search, RateLimit sourceImport,
                         RateLimit integrationManifest, RateLimit codeScanningUpload,
                         RateLimit actionsRunnerRegistration, RateLimit scim, RateLimit dependencySnapshots) {
            super(null);
            this.core = core;
            this.graphql = graphql;
            this.search = search;
            this.sourceImport = sourceImport;
            this.integrationManifest = integrationManifest;
            this.codeScanningUpload = codeScanningUpload;
            this.actionsRunnerRegistration = actionsRunnerRegistration;
            this.scim = scim;
            this.dependencySnapshots = dependencySnapshots;
        }

        /**
         * Constructor to init a {@link Resources}
         *
         * @param jResources: resources details as {@link JSONObject}
         **/
        public Resources(JSONObject jResources) {
            super(jResources);
            core = new RateLimit(hItem.getJSONObject("core"));
            JSONObject jResource = hItem.getJSONObject("graphql");
            if (jResource != null)
                graphql = new RateLimit(jResource);
            else
                graphql = null;
            search = new RateLimit(hItem.getJSONObject("search"));
            jResource = hItem.getJSONObject("source_import");
            if (jResource != null)
                sourceImport = new RateLimit(jResource);
            else
                sourceImport = null;
            jResource = hItem.getJSONObject("integration_manifest");
            if (jResource != null)
                integrationManifest = new RateLimit(jResource);
            else
                integrationManifest = null;
            jResource = hItem.getJSONObject("code_scanning_upload");
            if (jResource != null)
                codeScanningUpload = new RateLimit(jResource);
            else
                codeScanningUpload = null;
            jResource = hItem.getJSONObject("actions_runner_registration");
            if (jResource != null)
                actionsRunnerRegistration = new RateLimit(jResource);
            else
                actionsRunnerRegistration = null;
            jResource = hItem.getJSONObject("scim");
            if (jResource != null)
                scim = new RateLimit(jResource);
            else
                scim = null;
            jResource = hItem.getJSONObject("dependency_snapshots");
            if (jResource != null)
                dependencySnapshots = new RateLimit(jResource);
            else
                dependencySnapshots = null;
        }

        /**
         * Method to get {@link #core} instance <br>
         * No-any params required
         *
         * @return {@link #core} instance as {@link RateLimit}
         **/
        public RateLimit getCore() {
            return core;
        }

        /**
         * Method to get {@link #graphql} instance <br>
         * No-any params required
         *
         * @return {@link #graphql} instance as {@link RateLimit}
         **/
        public RateLimit getGraphql() {
            return graphql;
        }

        /**
         * Method to get {@link #search} instance <br>
         * No-any params required
         *
         * @return {@link #search} instance as {@link RateLimit}
         **/
        public RateLimit getSearch() {
            return search;
        }

        /**
         * Method to get {@link #sourceImport} instance <br>
         * No-any params required
         *
         * @return {@link #sourceImport} instance as {@link RateLimit}
         **/
        public RateLimit getSourceImport() {
            return sourceImport;
        }

        /**
         * Method to get {@link #integrationManifest} instance <br>
         * No-any params required
         *
         * @return {@link #integrationManifest} instance as {@link RateLimit}
         **/
        public RateLimit getIntegrationManifest() {
            return integrationManifest;
        }

        /**
         * Method to get {@link #codeScanningUpload} instance <br>
         * No-any params required
         *
         * @return {@link #codeScanningUpload} instance as {@link RateLimit}
         **/
        public RateLimit getCodeScanningUpload() {
            return codeScanningUpload;
        }

        /**
         * Method to get {@link #actionsRunnerRegistration} instance <br>
         * No-any params required
         *
         * @return {@link #actionsRunnerRegistration} instance as {@link RateLimit}
         **/
        public RateLimit getActionsRunnerRegistration() {
            return actionsRunnerRegistration;
        }

        /**
         * Method to get {@link #scim} instance <br>
         * No-any params required
         *
         * @return {@link #scim} instance as {@link RateLimit}
         **/
        public RateLimit getScim() {
            return scim;
        }

        /**
         * Method to get {@link #dependencySnapshots} instance <br>
         * No-any params required
         *
         * @return {@link #dependencySnapshots} instance as {@link RateLimit}
         **/
        public RateLimit getDependencySnapshots() {
            return dependencySnapshots;
        }

    }

    /**
     * The {@code RateLimit} class is useful to format a GitHub's rate limit value
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class RateLimit extends InnerClassItem {

        /**
         * {@code limit} value
         **/
        private final int limit;

        /**
         * {@code remaining} value
         **/
        private final int remaining;

        /**
         * {@code reset} value
         **/
        private final long reset;

        /**
         * {@code used} value
         **/
        private final int used;

        /**
         * Constructor to init a {@link RateLimit}
         *
         * @param limit:     limit value
         * @param remaining: remaining value
         * @param reset:     reset value
         * @param used:      used value
         **/
        public RateLimit(int limit, int remaining, long reset, int used) {
            super(null);
            this.limit = limit;
            this.remaining = remaining;
            this.reset = reset;
            this.used = used;
        }

        /**
         * Constructor to init a {@link RateLimit}
         *
         * @param jRateLimit: rate limit details as {@link JSONObject}
         **/
        public RateLimit(JSONObject jRateLimit) {
            super(jRateLimit);
            limit = hItem.getInt("limit", 0);
            remaining = hItem.getInt("remaining", 0);
            reset = hItem.getLong("reset", 0);
            used = hItem.getInt("used", 0);
        }

        /**
         * Method to get {@link #limit} instance <br>
         * No-any params required
         *
         * @return {@link #limit} instance as int
         **/
        public int getLimit() {
            return limit;
        }

        /**
         * Method to get {@link #remaining} instance <br>
         * No-any params required
         *
         * @return {@link #remaining} instance as int
         **/
        public int getRemaining() {
            return remaining;
        }

        /**
         * Method to get {@link #reset} instance <br>
         * No-any params required
         *
         * @return {@link #reset} instance as long
         **/
        public long getReset() {
            return reset;
        }

        /**
         * Method to get {@link #reset} instance <br>
         * No-any params required
         *
         * @return {@link #reset} instance as {@link String}
         **/
        public String getResetDate() {
            return timeFormatter.formatAsString(reset * 1000);
        }

        /**
         * Method to get {@link #used} instance <br>
         * No-any params required
         *
         * @return {@link #used} instance as int
         **/
        public int getUsed() {
            return used;
        }

    }

}
