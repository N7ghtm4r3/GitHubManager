package com.tecknobit.githubmanager.metrics.community.records;

import com.tecknobit.githubmanager.licenses.records.CommonLicense;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import org.json.JSONObject;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code CommunityProfile} class is useful to format a GitHub's community profile
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/metrics/community">
 * Get community profile metrics</a>
 * @see GitHubResponse
 **/
public class CommunityProfile extends GitHubResponse {

    /**
     * {@code healthPercentage} of the community profile
     **/
    private final double healthPercentage;

    /**
     * {@code description} of the community profile
     **/
    private final String description;

    /**
     * {@code documentation} of the community profile
     **/
    private final String documentation;

    /**
     * {@code files} of the community profile
     **/
    private final CommunityProfileFiles files;

    /**
     * {@code updatedAt} updated date of the community profile
     **/
    private final String updatedAt;

    /**
     * {@code contentReportsEnabled} whether the content reports of the community profile are enabled
     **/
    private final boolean contentReportsEnabled;

    /**
     * Constructor to init a {@link CommunityProfile}
     *
     * @param healthPercentage:      health percentage of the community profile
     * @param description:           description of the community profile
     * @param documentation:         documentation of the community profile
     * @param files:                 files of the community profile
     * @param updatedAt:             updated date of the community profile
     * @param contentReportsEnabled: whether the content reports of the community profile are enabled
     **/
    public CommunityProfile(double healthPercentage, String description, String documentation, CommunityProfileFiles files,
                            String updatedAt, boolean contentReportsEnabled) {
        super(null);
        this.healthPercentage = healthPercentage;
        this.description = description;
        this.documentation = documentation;
        this.files = files;
        this.updatedAt = updatedAt;
        this.contentReportsEnabled = contentReportsEnabled;
    }

    /**
     * Constructor to init a {@link CommunityProfile}
     *
     * @param jCommunityProfile: community profile details as {@link JSONObject}
     **/
    public CommunityProfile(JSONObject jCommunityProfile) {
        super(jCommunityProfile);
        healthPercentage = hResponse.getDouble("health_percentage", 0);
        description = hResponse.getString("description");
        documentation = hResponse.getString("documentation");
        files = new CommunityProfileFiles(hResponse.getJSONObject("files"));
        updatedAt = hResponse.getString("updated_at");
        contentReportsEnabled = hResponse.getBoolean("content_reports_enabled");
    }

    /**
     * Method to get {@link #healthPercentage} instance <br>
     * No-any params required
     *
     * @return {@link #healthPercentage} instance as double
     **/
    public double getHealthPercentage() {
        return healthPercentage;
    }

    /**
     * Method to get {@link #healthPercentage} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #healthPercentage} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getHealthPercentage(int decimals) {
        return roundValue(healthPercentage, decimals);
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
     * Method to get {@link #documentation} instance <br>
     * No-any params required
     *
     * @return {@link #documentation} instance as {@link String}
     **/
    public String getDocumentation() {
        return documentation;
    }

    /**
     * Method to get {@link #files} instance <br>
     * No-any params required
     *
     * @return {@link #files} instance as {@link CommunityProfileFiles}
     **/
    public CommunityProfileFiles getFiles() {
        return files;
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
        return timeFormatter.formatAsTimestamp(updatedAt);
    }

    /**
     * Method to get {@link #contentReportsEnabled} instance <br>
     * No-any params required
     *
     * @return {@link #contentReportsEnabled} instance as boolean
     **/
    public boolean areContentReportsEnabled() {
        return contentReportsEnabled;
    }

    /**
     * The {@code CommunityProfileFiles} class is useful to format a GitHub's community profile files
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class CommunityProfileFiles extends InnerClassItem {

        /**
         * {@code codeOfConduct} code of conduct item
         **/
        private final CommunityProfileFile codeOfConduct;

        /**
         * {@code codeOfConductFile} code of conduct file item
         **/
        private final CommunityProfileItem codeOfConductFile;

        /**
         * {@code license} item
         **/
        private final CommonLicense license;

        /**
         * {@code contributing} item
         **/
        private final CommunityProfileItem contributing;

        /**
         * {@code readme} item
         **/
        private final CommunityProfileItem readme;

        /**
         * {@code issueTemplate} issue template item
         **/
        private final CommunityProfileItem issueTemplate;

        /**
         * {@code pullRequestTemplate} pull request template item
         **/
        private final CommunityProfileItem pullRequestTemplate;

        /**
         * Constructor to init a {@link CommunityProfileFiles}
         *
         * @param codeOfConduct:       code of conduct item
         * @param codeOfConductFile:   code of conduct file item
         * @param license:             license item
         * @param contributing:        contributing item
         * @param readme:              readme item
         * @param issueTemplate:       issue template item
         * @param pullRequestTemplate: pull request template item
         **/
        public CommunityProfileFiles(CommunityProfileFile codeOfConduct, CommunityProfileItem codeOfConductFile,
                                     CommonLicense license, CommunityProfileItem contributing, CommunityProfileItem readme,
                                     CommunityProfileItem issueTemplate, CommunityProfileItem pullRequestTemplate) {
            super(null);
            this.codeOfConduct = codeOfConduct;
            this.codeOfConductFile = codeOfConductFile;
            this.license = license;
            this.contributing = contributing;
            this.readme = readme;
            this.issueTemplate = issueTemplate;
            this.pullRequestTemplate = pullRequestTemplate;
        }

        /**
         * Constructor to init a {@link CommunityProfileFiles}
         *
         * @param jCommunityProfileFiles: community profile files details as {@link JSONObject}
         **/
        public CommunityProfileFiles(JSONObject jCommunityProfileFiles) {
            super(jCommunityProfileFiles);
            JSONObject jItem = hItem.getJSONObject("code_of_conduct");
            if (jItem != null)
                codeOfConduct = new CommunityProfileFile(jItem);
            else
                codeOfConduct = null;
            jItem = hItem.getJSONObject("code_of_conduct_file");
            if (jItem != null)
                codeOfConductFile = new CommunityProfileItem(jItem);
            else
                codeOfConductFile = null;
            jItem = hItem.getJSONObject("license");
            if (jItem != null)
                license = new CommonLicense(jItem);
            else
                license = null;
            jItem = hItem.getJSONObject("contributing");
            if (jItem != null)
                contributing = new CommunityProfileItem(jItem);
            else
                contributing = null;
            jItem = hItem.getJSONObject("readme");
            if (jItem != null)
                readme = new CommunityProfileItem(jItem);
            else
                readme = null;
            jItem = hItem.getJSONObject("issue_template");
            if (jItem != null)
                issueTemplate = new CommunityProfileItem(jItem);
            else
                issueTemplate = null;
            jItem = hItem.getJSONObject("pull_request_template");
            if (jItem != null)
                pullRequestTemplate = new CommunityProfileItem(jCommunityProfileFiles);
            else
                pullRequestTemplate = null;
        }

        /**
         * Method to get {@link #codeOfConduct} instance <br>
         * No-any params required
         *
         * @return {@link #codeOfConduct} instance as {@link CommunityProfileFile}
         **/
        public CommunityProfileFile getCodeOfConduct() {
            return codeOfConduct;
        }

        /**
         * Method to get {@link #codeOfConductFile} instance <br>
         * No-any params required
         *
         * @return {@link #codeOfConductFile} instance as {@link CommunityProfileItem}
         **/
        public CommunityProfileItem getCodeOfConductFile() {
            return codeOfConductFile;
        }

        /**
         * Method to get {@link #license} instance <br>
         * No-any params required
         *
         * @return {@link #license} instance as {@link CommonLicense}
         **/
        public CommonLicense getLicense() {
            return license;
        }

        /**
         * Method to get {@link #contributing} instance <br>
         * No-any params required
         *
         * @return {@link #contributing} instance as {@link CommunityProfileItem}
         **/
        public CommunityProfileItem getContributing() {
            return contributing;
        }

        /**
         * Method to get {@link #readme} instance <br>
         * No-any params required
         *
         * @return {@link #readme} instance as {@link CommunityProfileItem}
         **/
        public CommunityProfileItem getReadme() {
            return readme;
        }

        /**
         * Method to get {@link #issueTemplate} instance <br>
         * No-any params required
         *
         * @return {@link #issueTemplate} instance as {@link CommunityProfileItem}
         **/
        public CommunityProfileItem getIssueTemplate() {
            return issueTemplate;
        }

        /**
         * Method to get {@link #pullRequestTemplate} instance <br>
         * No-any params required
         *
         * @return {@link #pullRequestTemplate} instance as {@link CommunityProfileItem}
         **/
        public CommunityProfileItem getPullRequestTemplate() {
            return pullRequestTemplate;
        }

        /**
         * The {@code CommunityProfileFile} class is useful to format a GitHub's community profile file
         *
         * @author N7ghtm4r3 - Tecknobit
         * @see InnerClassItem
         **/
        public static class CommunityProfileFile extends CommunityProfileItem {

            /**
             * {@code key} of the profile file
             **/
            private final String key;

            /**
             * {@code name} of the profile file
             **/
            private final String name;

            /**
             * Constructor to init a {@link CommunityProfileFile}
             *
             * @param url:     url of the profile file
             * @param htmlUrl: html url of the profile file
             * @param key:     key of the profile file
             * @param name:    name url of the profile file
             **/
            public CommunityProfileFile(String url, String htmlUrl, String key, String name) {
                super(url, htmlUrl);
                this.key = key;
                this.name = name;
            }

            /**
             * Constructor to init a {@link CommunityProfileFile}
             *
             * @param jCommunityProfileFile: community profile file details as {@link JSONObject}
             **/
            public CommunityProfileFile(JSONObject jCommunityProfileFile) {
                super(jCommunityProfileFile);
                key = hItem.getString("key");
                name = hItem.getString("name");
            }

            /**
             * Method to get {@link #key} instance <br>
             * No-any params required
             *
             * @return {@link #key} instance as {@link String}
             **/
            public String getKey() {
                return key;
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
         * The {@code CommunityProfileItem} class is useful to format a GitHub's community profile item
         *
         * @author N7ghtm4r3 - Tecknobit
         * @see InnerClassItem
         **/
        public static class CommunityProfileItem extends InnerClassItem {

            /**
             * {@code url} of the profile item
             **/
            protected final String url;

            /**
             * {@code htmlUrl} html url of the profile item
             **/
            protected final String htmlUrl;

            /**
             * Constructor to init a {@link CommunityProfileItem}
             *
             * @param url:     url of the profile item
             * @param htmlUrl: html url of the profile item
             **/
            public CommunityProfileItem(String url, String htmlUrl) {
                super(null);
                this.url = url;
                this.htmlUrl = htmlUrl;
            }

            /**
             * Constructor to init a {@link CommunityProfileItem}
             *
             * @param jCommunityProfileItem: community profile item details as {@link JSONObject}
             **/
            public CommunityProfileItem(JSONObject jCommunityProfileItem) {
                super(jCommunityProfileItem);
                url = hItem.getString("url");
                htmlUrl = hItem.getString("html_url");
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
             * Method to get {@link #htmlUrl} instance <br>
             * No-any params required
             *
             * @return {@link #htmlUrl} instance as {@link String}
             **/
            public String getHtmlUrl() {
                return htmlUrl;
            }

        }

    }

}
