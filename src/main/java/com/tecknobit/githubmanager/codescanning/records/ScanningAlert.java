package com.tecknobit.githubmanager.codescanning.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.User;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.githubmanager.codescanning.records.ScanningAlert.Rule.SecuritySeverityLevel.low;

public class ScanningAlert extends GitHubResponse {

    private final long number;
    private final String createdAt;
    private final String updatedAt;
    private final String url;
    private final String htmlUrl;
    private final String instancesUrl;
    private final State state;
    private final String fixedAt;
    private final User dismissedBy;
    private final String dismissedAt;
    private final DismissedReason dismissedReason;
    private final String dismissedComment;
    private final Rule rule;
    private final Tool tool;
    private final Instance mostRecentInstance;
    private final Repository repository;

    public ScanningAlert(long number, String createdAt, String updatedAt, String url, String htmlUrl, String instancesUrl,
                         State state, String fixedAt, User dismissedBy, String dismissedAt,
                         DismissedReason dismissedReason, String dismissedComment, Rule rule, Tool tool,
                         Instance mostRecentInstance, Repository repository) {
        super(null);
        this.number = number;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.url = url;
        this.htmlUrl = htmlUrl;
        this.instancesUrl = instancesUrl;
        this.state = state;
        this.fixedAt = fixedAt;
        this.dismissedBy = dismissedBy;
        this.dismissedAt = dismissedAt;
        this.dismissedReason = dismissedReason;
        this.dismissedComment = dismissedComment;
        this.rule = rule;
        this.tool = tool;
        this.mostRecentInstance = mostRecentInstance;
        this.repository = repository;
    }
    /**
     * Constructor to init a {@link GitHubResponse}
     *
     * @param jScanningAlert : response by {@code "GitHub"} as {@link JSONObject}
     **/
    public ScanningAlert(JSONObject jScanningAlert) {
        super(jScanningAlert);
        number = hResponse.getLong("number");
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
        url = hResponse.getString("url");
        htmlUrl = hResponse.getString("html_url");
        instancesUrl = hResponse.getString("instances_url");
        state = State.valueOf(hResponse.getString("state"));
        fixedAt = hResponse.getString("fixed_at");
        dismissedBy = new User(hResponse.getJSONObject("dismissed_by", new JSONObject()));
        dismissedAt = hResponse.getString("dismissed_at");
        String sDismissedReason = hResponse.getString("dismissed_reason");
        if (sDismissedReason != null)
            dismissedReason = DismissedReason.valueOf(sDismissedReason);
        else
            dismissedReason = null;
        dismissedComment = hResponse.getString("dismissed_comment");
        rule = new Rule(hResponse.getJSONObject("rule", new JSONObject()));
        tool = new Tool(hResponse.getJSONObject("tool", new JSONObject()));
        mostRecentInstance = new Instance(hResponse.getJSONObject("most_recent_instance", new JSONObject()));
        repository = new Repository(hResponse.getJSONObject("repository", new JSONObject()));
    }

    public long getNumber() {
        return number;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getUrl() {
        return url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getInstancesUrl() {
        return instancesUrl;
    }

    public State getState() {
        return state;
    }

    public String getFixedAt() {
        return fixedAt;
    }

    public User getDismissedBy() {
        return dismissedBy;
    }

    public String getDismissedAt() {
        return dismissedAt;
    }

    public DismissedReason getDismissedReason() {
        return dismissedReason;
    }

    public String getDismissedComment() {
        return dismissedComment;
    }

    public Rule getRule() {
        return rule;
    }

    public Tool getTool() {
        return tool;
    }

    public Instance getMostRecentInstance() {
        return mostRecentInstance;
    }

    public Repository getRepository() {
        return repository;
    }

    public enum State {

        open,
        closed,
        dismissed,
        fixed

    }

    public enum DismissedReason {

        false_positive("false positive"),
        wont_fix("won't fix"),
        used_in_tests("used in tests");

        private final String reason;

        DismissedReason(String reason) {
            this.reason = reason;
        }

        @Override
        public String toString() {
            return reason;
        }

    }

    public static class Rule {

        private final long id;
        private final String name;
        private final Severity severity;
        private final SecuritySeverityLevel securitySeverityLevel;
        private final String description;
        private final String fullDescription;
        private final ArrayList<String> tags;
        private final String help;
        private final String helpUri;
        public Rule(long id, String name, Severity severity, SecuritySeverityLevel securitySeverityLevel, String description,
                    String fullDescription, ArrayList<String> tags, String help, String helpUri) {
            this.id = id;
            this.name = name;
            this.severity = severity;
            this.securitySeverityLevel = securitySeverityLevel;
            this.description = description;
            this.fullDescription = fullDescription;
            this.tags = tags;
            this.help = help;
            this.helpUri = helpUri;
        }
        public Rule(JSONObject jRule) {
            JsonHelper hRule = new JsonHelper(jRule);
            id = hRule.getLong("id", 0);
            name = hRule.getString("name");
            severity = Severity.valueOf(hRule.getString("severity", Severity.none.name()));
            securitySeverityLevel = SecuritySeverityLevel.valueOf(hRule.getString("security_severity_level", low.name()));
            description = hRule.getString("description");
            fullDescription = hRule.getString("full_description");
            tags = returnStringsList(hRule.getJSONArray("tags"));
            help = hRule.getString("help");
            helpUri = hRule.getString("help_uri");
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Severity getSeverity() {
            return severity;
        }

        public SecuritySeverityLevel getSecuritySeverityLevel() {
            return securitySeverityLevel;
        }

        public String getDescription() {
            return description;
        }

        public String getFullDescription() {
            return fullDescription;
        }

        public ArrayList<String> getTags() {
            return tags;
        }

        public String getHelp() {
            return help;
        }

        public String getHelpUri() {
            return helpUri;
        }

        /**
         * Returns a string representation of the object <br>
         * Any params required
         *
         * @return a string representation of the object as {@link String}
         */
        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

        public enum Severity {

            none,
            note,
            warning,
            error

        }

        public enum SecuritySeverityLevel {

            low,
            medium,
            high,
            critical

        }

    }

    public static class Tool {

        private final String name;
        private final String guid;
        private final String version;

        public Tool(String name, String guid, String version) {
            this.name = name;
            this.guid = guid;
            this.version = version;
        }

        public Tool(JSONObject jTool) {
            JsonHelper hTool = new JsonHelper(jTool);
            name = hTool.getString("name");
            guid = hTool.getString("guid");
            version = hTool.getString("version");
        }

        public String getName() {
            return name;
        }

        public String getGuid() {
            return guid;
        }

        public String getVersion() {
            return version;
        }

        /**
         * Returns a string representation of the object <br>
         * Any params required
         *
         * @return a string representation of the object as {@link String}
         */
        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

    }

}
