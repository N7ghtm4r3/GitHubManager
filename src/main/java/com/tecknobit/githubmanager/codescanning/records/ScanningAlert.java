package com.tecknobit.githubmanager.codescanning.records;

import com.tecknobit.githubmanager.GitHubManager.SeverityLevel;
import com.tecknobit.githubmanager.records.generic.GitHubAlert;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import com.tecknobit.githubmanager.records.parents.User;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.githubmanager.GitHubManager.SeverityLevel.low;

/**
 * The {@code ScanningAlert} class is useful to format a GitHub's scanning alert
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-alerts-for-an-enterprise">
 *             List code scanning alerts for an enterprise</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-alerts-for-an-organization">
 *             List code scanning alerts for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/code-scanning#list-code-scanning-alerts-for-a-repository">
 *             List code scanning alerts for a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/code-scanning#get-a-code-scanning-alert">
 *             Get a code scanning alert</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/code-scanning#update-a-code-scanning-alert">
 *             Update a code scanning alert</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class ScanningAlert extends GitHubAlert {

    /**
     * {@code instancesUrl} the REST API URL for fetching the list of instances for an alert
     **/
    private final String instancesUrl;

    /**
     * {@code state} of a code scanning alert
     **/
    private final State state;

    /**
     * {@code dismissedReason} <b>Required when the state is dismissed</b>. The reason for dismissing or closing the alert
     **/
    private final DismissedReason dismissedReason;

    /**
     * {@code rule} for the alert
     **/
    private final Rule rule;

    /**
     * {@code tool} for the alert
     **/
    private final Tool tool;

    /**
     * {@code mostRecentInstance} most recent instance for the alert
     **/
    private final Instance mostRecentInstance;

    /**
     * Constructor to init a {@link ScanningAlert}
     *
     * @param number             : the security alert number
     * @param createdAt          : the time that the alert was created in ISO 8601 format: "YYYY-MM-DDTHH:MM:SSZ"
     * @param updatedAt          : the time that the alert was updated in ISO 8601 format: "YYYY-MM-DDTHH:MM:SSZ"
     * @param url                : the REST API URL of the alert resource
     * @param htmlUrl            : the GitHub URL of the alert resource
     * @param instancesUrl       : the REST API URL for fetching the list of instances for an alert
     * @param state              : state of a code scanning alert
     * @param fixedAt            : the time that the alert was no longer detected and was considered fixed in ISO 8601 format: "YYYY-MM-DDTHH:MM:SSZ"
     * @param dismissedBy        : dismissed by user
     * @param dismissedAt        : the time that the alert was dismissed in ISO 8601 format: "YYYY-MM-DDTHH:MM:SSZ"
     * @param dismissedReason    : the reason for dismissing or closing the alert
     * @param dismissedComment   : the dismissal comment associated with the dismissal of the alert
     * @param rule               : rule of the alert
     * @param tool               : tool of the alert
     * @param mostRecentInstance : most recent instance for the alert
     * @param repository         : repository of the alert
     **/
    public ScanningAlert(long number, String createdAt, String updatedAt, String url, String htmlUrl, String instancesUrl,
                         State state, String fixedAt, User dismissedBy, String dismissedAt,
                         DismissedReason dismissedReason, String dismissedComment, Rule rule, Tool tool,
                         Instance mostRecentInstance, Repository repository) {
        super(number, createdAt, updatedAt, url, htmlUrl, fixedAt, dismissedBy, dismissedAt, dismissedComment, repository);
        this.instancesUrl = instancesUrl;
        this.state = state;
        this.dismissedReason = dismissedReason;
        this.rule = rule;
        this.tool = tool;
        this.mostRecentInstance = mostRecentInstance;
    }

    /**
     * Constructor to init a {@link ScanningAlert}
     *
     * @param jScanningAlert : alert details as {@link JSONObject}
     **/
    public ScanningAlert(JSONObject jScanningAlert) {
        super(jScanningAlert);
        instancesUrl = hResponse.getString("instances_url");
        state = State.valueOf(hResponse.getString("state"));
        String sDismissedReason = hResponse.getString("dismissed_reason");
        if (sDismissedReason != null)
            dismissedReason = DismissedReason.valueOf(sDismissedReason);
        else
            dismissedReason = null;
        rule = new Rule(hResponse.getJSONObject("rule", new JSONObject()));
        tool = new Tool(hResponse.getJSONObject("tool", new JSONObject()));
        mostRecentInstance = new Instance(hResponse.getJSONObject("most_recent_instance", new JSONObject()));
    }

    /**
     * Method to get {@link #instancesUrl} instance <br>
     * No-any params required
     *
     * @return {@link #instancesUrl} instance as {@link String}
     **/
    public String getInstancesUrl() {
        return instancesUrl;
    }

    /**
     * Method to get {@link #state} instance <br>
     * No-any params required
     *
     * @return {@link #state} instance as {@link State}
     **/
    public State getState() {
        return state;
    }

    /**
     * Method to get {@link #dismissedReason} instance <br>
     * No-any params required
     *
     * @return {@link #dismissedReason} instance as {@link DismissedReason}
     **/
    public DismissedReason getDismissedReason() {
        return dismissedReason;
    }

    /**
     * Method to get {@link #rule} instance <br>
     * No-any params required
     *
     * @return {@link #rule} instance as {@link Rule}
     **/
    public Rule getRule() {
        return rule;
    }

    /**
     * Method to get {@link #tool} instance <br>
     * No-any params required
     *
     * @return {@link #tool} instance as {@link Tool}
     **/
    public Tool getTool() {
        return tool;
    }

    /**
     * Method to get {@link #mostRecentInstance} instance <br>
     * No-any params required
     *
     * @return {@link #mostRecentInstance} instance as {@link Instance}
     **/
    public Instance getMostRecentInstance() {
        return mostRecentInstance;
    }

    /**
     * {@code State} list of available states for an alert
     **/
    public enum State {

        /**
         * {@code open} state
         **/
        open,

        /**
         * {@code closed} state
         **/
        closed,

        /**
         * {@code dismissed} state
         **/
        dismissed,

        /**
         * {@code fixed} state
         **/
        fixed

    }

    /**
     * {@code DismissedReason} list of available dismissed reasons for an alert
     **/
    public enum DismissedReason {

        /**
         * {@code false_positive} dismissed reason
         **/
        false_positive("false positive"),

        /**
         * {@code wont_fix} dismissed reason
         **/
        wont_fix("won't fix"),

        /**
         * {@code used_in_tests} dismissed reason
         **/
        used_in_tests("used in tests");

        /**
         * {@code reason} value
         **/
        private final String reason;

        /**
         * Constructor to init a {@link DismissedReason}
         *
         * @param reason: reason value
         **/
        DismissedReason(String reason) {
            this.reason = reason;
        }

        /**
         * Method to get {@link #reason} instance <br>
         * No-any params required
         *
         * @return {@link #reason} instance as {@link String}
         **/
        @Override
        public String toString() {
            return reason;
        }

    }

    /**
     * The {@code Rule} class is useful to format a GitHub's rule for {@link ScanningAlert}
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class Rule extends InnerClassItem {

        /**
         * {@code id} a unique identifier for the rule used to detect the alert
         **/
        private final long id;

        /**
         * {@code name} the name of the rule used to detect the alert
         **/
        private final String name;

        /**
         * {@code severity} the severity of the alert
         **/
        private final Severity severity;

        /**
         * {@code securitySeverityLevel} the security severity of the alert
         **/
        private final SeverityLevel securitySeverityLevel;

        /**
         * {@code description} a short description of the rule used to detect the alert
         **/
        private final String description;

        /**
         * {@code fullDescription} description of the rule used to detect the alert
         **/
        private final String fullDescription;

        /**
         * {@code tags} set of tags applicable for the rule
         **/
        private final ArrayList<String> tags;

        /**
         * {@code help} a detailed documentation for the rule as GitHub Flavored Markdown
         **/
        private final String help;

        /**
         * {@code helpUri} a link to the documentation for the rule used to detect the alert
         **/
        private final String helpUri;

        /**
         * Constructor to init a {@link Rule}
         *
         * @param id                    : a unique identifier for the rule used to detect the alert
         * @param name                  : the name of the rule used to detect the alert
         * @param severity              : the name of the rule used to detect the alert
         * @param securitySeverityLevel : the security severity of the alert
         * @param description           : a short description of the rule used to detect the alert
         * @param fullDescription       : description of the rule used to detect the alert
         * @param tags                  : set of tags applicable for the rule
         * @param help                  : a detailed documentation for the rule as GitHub Flavored Markdown
         * @param helpUri               : a link to the documentation for the rule used to detect the alert
         **/
        public Rule(long id, String name, Severity severity, SeverityLevel securitySeverityLevel, String description,
                    String fullDescription, ArrayList<String> tags, String help, String helpUri) {
            super(null);
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

        /**
         * Constructor to init a {@link Rule}
         *
         * @param jRule: rule details as {@link JSONObject}
         **/
        public Rule(JSONObject jRule) {
            super(jRule);
            id = hItem.getLong("id", 0);
            name = hItem.getString("name");
            severity = Severity.valueOf(hItem.getString("severity", Severity.none.name()));
            securitySeverityLevel = SeverityLevel.valueOf(hItem.getString("security_severity_level", low.name()));
            description = hItem.getString("description");
            fullDescription = hItem.getString("full_description");
            tags = returnStringsList(hItem.getJSONArray("tags"));
            help = hItem.getString("help");
            helpUri = hItem.getString("help_uri");
        }

        /**
         * Method to get {@link #id} instance <br>
         * No-any params required
         *
         * @return {@link #id} instance as long
         **/
        public long getId() {
            return id;
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
         * Method to get {@link #severity} instance <br>
         * No-any params required
         *
         * @return {@link #severity} instance as {@link Severity}
         **/
        public Severity getSeverity() {
            return severity;
        }

        /**
         * Method to get {@link #securitySeverityLevel} instance <br>
         * No-any params required
         *
         * @return {@link #securitySeverityLevel} instance as {@link SeverityLevel}
         **/
        public SeverityLevel getSecuritySeverityLevel() {
            return securitySeverityLevel;
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
         * Method to get {@link #fullDescription} instance <br>
         * No-any params required
         *
         * @return {@link #fullDescription} instance as {@link String}
         **/
        public String getFullDescription() {
            return fullDescription;
        }

        /**
         * Method to get {@link #tags} instance <br>
         * No-any params required
         *
         * @return {@link #tags} instance as {@link Collection} of {@link String}
         **/
        public Collection<String> getTags() {
            return tags;
        }

        /**
         * Method to get {@link #help} instance <br>
         * No-any params required
         *
         * @return {@link #help} instance as {@link String}
         **/
        public String getHelp() {
            return help;
        }

        /**
         * Method to get {@link #helpUri} instance <br>
         * No-any params required
         *
         * @return {@link #helpUri} instance as {@link String}
         **/
        public String getHelpUri() {
            return helpUri;
        }

        /**
         * {@code Severity} list of available severities for a rule
         **/
        public enum Severity {

            /**
             * {@code none} severity
             **/
            none,

            /**
             * {@code note} severity
             **/
            note,

            /**
             * {@code warning} severity
             **/
            warning,

            /**
             * {@code error} severity
             **/
            error

        }

    }

    /**
     * The {@code Tool} class is useful to format a GitHub's tool for {@link ScanningAlert}
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class Tool extends InnerClassItem {

        /**
         * {@code name} the name of the tool used to generate the code scanning analysis
         **/
        private final String name;

        /**
         * {@code medium} the version of the tool used to generate the code scanning analysis
         **/
        private final String version;

        /**
         * {@code guid} the GUID of the tool used to generate the code scanning analysis, if provided in the uploaded SARIF data
         **/
        private final String guid;

        /**
         * Constructor to init a {@link Tool}
         *
         * @param name    : the name of the tool used to generate the code scanning analysis
         * @param version : the version of the tool used to generate the code scanning analysis
         * @param guid    : the GUID of the tool used to generate the code scanning analysis, if provided in the uploaded SARIF data
         **/
        public Tool(String name, String version, String guid) {
            super(null);
            this.name = name;
            this.version = version;
            this.guid = guid;
        }

        /**
         * Constructor to init a {@link Tool}
         *
         * @param jTool : tool details as {@link JSONObject}
         **/
        public Tool(JSONObject jTool) {
            super(jTool);
            name = hItem.getString("name");
            guid = hItem.getString("guid");
            version = hItem.getString("version");
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
         * Method to get {@link #guid} instance <br>
         * No-any params required
         *
         * @return {@link #guid} instance as {@link String}
         **/
        public String getGuid() {
            return guid;
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

    }

}
