package com.tecknobit.githubmanager.branches.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.apps.apps.records.GitHubApp;
import com.tecknobit.githubmanager.records.organization.Team;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.apps.apps.records.GitHubApp.returnAppsList;
import static com.tecknobit.githubmanager.records.organization.Team.returnTeamsList;
import static com.tecknobit.githubmanager.records.parents.User.returnUsersList;

public class BranchProtection extends GitHubResponse {

    private final boolean enabled;
    private final String url;
    private final RequiredStatusCheck requiredStatusCheck;
    private final ProtectionItem enforceAdmins;
    private final RequiredPullRequestReviews<Object> requiredPullRequestReviews;
    private final Restrictions<Object> restrictions;
    private final boolean requiredLinearHistory;
    private final boolean allowForcePushes;
    private final boolean allowDeletions;
    private final boolean blockCreations;
    private final boolean requiredConversationResolution;
    private final String name;
    private final String protectionUrl;
    private final ProtectionItem requiredSignatures;
    private final boolean lockBranch;
    private final boolean allowForkSyncing;

    public BranchProtection(boolean enabled, String url, RequiredStatusCheck requiredStatusCheck,
                            ProtectionItem enforceAdmins, RequiredPullRequestReviews<Object> requiredPullRequestReviews,
                            Restrictions<Object> restrictions, boolean requiredLinearHistory, boolean allowForcePushes,
                            boolean allowDeletions, boolean blockCreations, boolean requiredConversationResolution,
                            String name, String protectionUrl, ProtectionItem requiredSignatures, boolean lockBranch,
                            boolean allowForkSyncing) {
        super(null);
        this.enabled = enabled;
        this.url = url;
        this.requiredStatusCheck = requiredStatusCheck;
        this.enforceAdmins = enforceAdmins;
        this.requiredPullRequestReviews = requiredPullRequestReviews;
        this.restrictions = restrictions;
        this.requiredLinearHistory = requiredLinearHistory;
        this.allowForcePushes = allowForcePushes;
        this.allowDeletions = allowDeletions;
        this.blockCreations = blockCreations;
        this.requiredConversationResolution = requiredConversationResolution;
        this.name = name;
        this.protectionUrl = protectionUrl;
        this.requiredSignatures = requiredSignatures;
        this.lockBranch = lockBranch;
        this.allowForkSyncing = allowForkSyncing;
    }

    /**
     * Constructor to init a {@link BranchProtection}
     *
     * @param jBranchProtection : response by {@code "GitHub"} as {@link JSONObject}
     **/
    public BranchProtection(JSONObject jBranchProtection) throws Exception {
        super(jBranchProtection);
        enabled = hResponse.getBoolean("enabled");
        url = hResponse.getString("url");
        JSONObject jRequiredStatusCheck = hResponse.getJSONObject("required_status_checks");
        if (jRequiredStatusCheck != null)
            requiredStatusCheck = new RequiredStatusCheck(jRequiredStatusCheck);
        else
            requiredStatusCheck = null;
        JSONObject jEnforceAdmins = hResponse.getJSONObject("enforce_admins");
        if (jEnforceAdmins != null)
            enforceAdmins = new ProtectionItem(jEnforceAdmins);
        else
            enforceAdmins = null;
        JSONObject jRequestReviews = hResponse.getJSONObject("required_pull_request_reviews");
        if (jRequestReviews != null)
            requiredPullRequestReviews = new RequiredPullRequestReviews<>(jRequestReviews);
        else
            requiredPullRequestReviews = null;
        restrictions = new Restrictions<>(hResponse.getJSONObject("restrictions", new JSONObject()));
        JsonHelper hBooleans = new JsonHelper(hResponse.getJSONObject("required_linear_history", new JSONObject()));
        requiredLinearHistory = hBooleans.getBoolean("enabled");
        hBooleans.setJSONObjectSource(hResponse.getJSONObject("allow_force_pushes", new JSONObject()));
        allowForcePushes = hBooleans.getBoolean("enabled");
        hBooleans.setJSONObjectSource(hResponse.getJSONObject("allow_deletions", new JSONObject()));
        allowDeletions = hBooleans.getBoolean("enabled");
        hBooleans.setJSONObjectSource(hResponse.getJSONObject("required_conversation_resolution", new JSONObject()));
        blockCreations = hBooleans.getBoolean("enabled");
        hBooleans.setJSONObjectSource(hResponse.getJSONObject("required_conversation_resolution", new JSONObject()));
        requiredConversationResolution = hBooleans.getBoolean("enabled");
        name = hResponse.getString("name");
        protectionUrl = hResponse.getString("protection_url");
        JSONObject jRequiredSignatures = hResponse.getJSONObject("required_signatures", new JSONObject());
        if (jRequiredSignatures != null)
            requiredSignatures = new ProtectionItem(jRequiredSignatures);
        else
            requiredSignatures = null;
        hBooleans.setJSONObjectSource(hResponse.getJSONObject("lock_branch", new JSONObject()));
        lockBranch = hBooleans.getBoolean("enabled");
        hBooleans.setJSONObjectSource(hResponse.getJSONObject("allow_fork_syncing", new JSONObject()));
        allowForkSyncing = hBooleans.getBoolean("enabled");
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getUrl() {
        return url;
    }

    public RequiredStatusCheck getRequiredStatusCheck() {
        return requiredStatusCheck;
    }

    public ProtectionItem getEnforceAdmins() {
        return enforceAdmins;
    }

    public RequiredPullRequestReviews<Object> getRequiredPullRequestReviews() {
        return requiredPullRequestReviews;
    }

    public Restrictions<Object> getRestrictions() {
        return restrictions;
    }

    public boolean isRequiredLinearHistory() {
        return requiredLinearHistory;
    }

    public boolean isAllowForcePushes() {
        return allowForcePushes;
    }

    public boolean isAllowDeletions() {
        return allowDeletions;
    }

    public boolean isBlockCreations() {
        return blockCreations;
    }

    public boolean isRequiredConversationResolution() {
        return requiredConversationResolution;
    }

    public String getName() {
        return name;
    }

    public String getProtectionUrl() {
        return protectionUrl;
    }

    public ProtectionItem getRequiredSignatures() {
        return requiredSignatures;
    }

    public boolean isLockBranch() {
        return lockBranch;
    }

    public boolean isAllowForkSyncing() {
        return allowForkSyncing;
    }

    /**
     * The {@code RequiredStatusCheck} class is useful to format a GitHub's required status check for {@link BranchProtection}
     *
     * @author N7ghtm4r3 - Tecknobit
     **/
    public static class RequiredStatusCheck {

        /**
         * {@code enforcementLevel} type of the enforcement level of the required status check
         **/
        private final String enforcementLevel;

        /**
         * {@code contexts} of the required status check
         **/
        private final ArrayList<String> contexts;

        /**
         * {@code checks} of the required status check
         **/
        private final ArrayList<Check> checks;

        private final String contextsUrl;
        private final boolean strict;

        public RequiredStatusCheck(ArrayList<Check> checks, boolean strict) {
            this(null, null, checks, null, strict);
        }

        /**
         * Constructor to init a {@link RequiredStatusCheck}
         *
         * @param enforcementLevel : type of the enforcement level of the required status check
         * @param contexts         : contexts of the required status check
         * @param checks           : checks of the required status check
         * @param contextsUrl
         * @param strict
         **/
        public RequiredStatusCheck(String enforcementLevel, ArrayList<String> contexts, ArrayList<Check> checks,
                                   String contextsUrl, boolean strict) {
            this.enforcementLevel = enforcementLevel;
            this.contexts = contexts;
            this.contextsUrl = contextsUrl;
            this.checks = checks;
            this.strict = strict;
        }

        /**
         * Constructor to init a {@link RequiredStatusCheck}
         *
         * @param jRequiredStatusCheck : required status check details as {@link JSONObject}
         **/
        public RequiredStatusCheck(JSONObject jRequiredStatusCheck) {
            JsonHelper hRequiredStatusCheck = new JsonHelper(jRequiredStatusCheck);
            enforcementLevel = hRequiredStatusCheck.getString("enforcement_level");
            contexts = returnStringsList(hRequiredStatusCheck.getJSONArray("contexts"));
            checks = new ArrayList<>();
            JSONArray jChecks = hRequiredStatusCheck.getJSONArray("checks", new JSONArray());
            for (int j = 0; j < jChecks.length(); j++)
                checks.add(new Check(jChecks.getJSONObject(j)));
            contextsUrl = hRequiredStatusCheck.getString("contexts_url");
            strict = hRequiredStatusCheck.getBoolean("strict");
        }

        /**
         * Method to get {@link #enforcementLevel} instance <br>
         * Any params required
         *
         * @return {@link #enforcementLevel} instance as {@link String}
         **/
        public String getEnforcementLevel() {
            return enforcementLevel;
        }

        /**
         * Method to get {@link #contexts} instance <br>
         * Any params required
         *
         * @return {@link #contexts} instance as {@link Collection} of {@link String}
         **/
        public Collection<String> getContexts() {
            return contexts;
        }

        /**
         * Method to get {@link #checks} instance <br>
         * Any params required
         *
         * @return {@link #checks} instance as {@link Collection} of {@link Check}
         **/
        public Collection<Check> getChecks() {
            return checks;
        }

        public String getContextsUrl() {
            return contextsUrl;
        }

        public boolean isStrict() {
            return strict;
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

        public static class Check {

            private final String context;
            private final long appId;

            public Check(String context) {
                this(context, 0);
            }

            public Check(String context, long appId) {
                this.context = context;
                this.appId = appId;
            }

            public Check(JSONObject jCheck) {
                JsonHelper hCheck = new JsonHelper(jCheck);
                context = hCheck.getString("context");
                appId = hCheck.getLong("app_id", 0);
            }

            public String getContext() {
                return context;
            }

            public long getAppId() {
                return appId;
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

    public static class ProtectionItem {

        private final String url;
        private final boolean enabled;

        public ProtectionItem(String url, boolean enabled) {
            this.url = url;
            this.enabled = enabled;
        }

        public ProtectionItem(JSONObject jEnforceAdmins) {
            JsonHelper hEnforceAdmins = new JsonHelper(jEnforceAdmins);
            url = hEnforceAdmins.getString("url");
            enabled = hEnforceAdmins.getBoolean("enabled");
        }

        public String getUrl() {
            return url;
        }

        public boolean isEnabled() {
            return enabled;
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

    public static class RequiredPullRequestReviews<T> {

        private final String url;
        private final Restrictions<T> dismissalRestrictions;
        private final boolean dismissStaleReviews;
        private final boolean requireCodeOwnerReviews;
        private final int requiredApprovingReviewCount;
        private final boolean requireLastPushApproval;
        private final Restrictions<T> bypassPullRequestAllowances;

        public RequiredPullRequestReviews(String url, Restrictions<T> dismissalRestrictions, boolean dismissStaleReviews,
                                          boolean requireCodeOwnerReviews, int requiredApprovingReviewCount,
                                          boolean requireLastPushApproval, Restrictions<T> bypassPullRequestAllowances) {
            this.url = url;
            this.dismissalRestrictions = dismissalRestrictions;
            this.dismissStaleReviews = dismissStaleReviews;
            this.requireCodeOwnerReviews = requireCodeOwnerReviews;
            this.requiredApprovingReviewCount = requiredApprovingReviewCount;
            this.requireLastPushApproval = requireLastPushApproval;
            this.bypassPullRequestAllowances = bypassPullRequestAllowances;
        }

        public RequiredPullRequestReviews(JSONObject jRequestReviews) throws Exception {
            JsonHelper hRequestReviews = new JsonHelper(jRequestReviews);
            url = hRequestReviews.getString("url");
            dismissalRestrictions = new Restrictions<>(hRequestReviews.getJSONObject("dismissal_restrictions",
                    new JSONObject()));
            dismissStaleReviews = hRequestReviews.getBoolean("dismiss_stale_reviews");
            requireCodeOwnerReviews = hRequestReviews.getBoolean("require_code_owner_reviews");
            requiredApprovingReviewCount = hRequestReviews.getInt("required_approving_review_count", 0);
            requireLastPushApproval = hRequestReviews.getBoolean("require_last_push_approval");
            JSONObject jRequestAllowances = hRequestReviews.getJSONObject("bypass_pull_request_allowances");
            if (jRequestAllowances != null)
                bypassPullRequestAllowances = new Restrictions<>(jRequestAllowances);
            else
                bypassPullRequestAllowances = null;
        }

        public String getUrl() {
            return url;
        }

        public Restrictions<T> getDismissalRestrictions() {
            return dismissalRestrictions;
        }

        public boolean isDismissStaleReviews() {
            return dismissStaleReviews;
        }

        public boolean isRequireCodeOwnerReviews() {
            return requireCodeOwnerReviews;
        }

        public int getRequiredApprovingReviewCount() {
            return requiredApprovingReviewCount;
        }

        public boolean isRequireLastPushApproval() {
            return requireLastPushApproval;
        }

        public Restrictions<T> getBypassPullRequestAllowances() {
            return bypassPullRequestAllowances;
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

    public static class Restrictions<T> {

        protected final String url;
        protected final String usersUrl;
        protected final String teamsUrl;
        protected final ArrayList<T> users;
        protected final ArrayList<T> teams;
        protected final ArrayList<T> apps;
        protected final JsonHelper hRestrictions;

        public Restrictions(ArrayList<String> users, ArrayList<String> teams) {
            this(users, teams, null);
        }

        public Restrictions(ArrayList<String> users, ArrayList<String> teams, ArrayList<String> apps) {
            this.users = (ArrayList<T>) users;
            this.teams = (ArrayList<T>) teams;
            this.apps = (ArrayList<T>) apps;
            url = null;
            usersUrl = null;
            teamsUrl = null;
            hRestrictions = null;
        }

        public Restrictions(String url, String usersUrl, String teamsUrl, ArrayList<User> users,
                            ArrayList<Team> teams, ArrayList<GitHubApp> apps, boolean dismissStaleReviews,
                            boolean requireCodeOwnerReviews, int requiredApprovingReviewCount,
                            boolean requireLastPushApproval) {
            this.url = url;
            this.usersUrl = usersUrl;
            this.teamsUrl = teamsUrl;
            this.users = (ArrayList<T>) users;
            this.teams = (ArrayList<T>) teams;
            this.apps = (ArrayList<T>) apps;
            hRestrictions = null;
        }

        public Restrictions(JSONObject jDismissalRestrictions) throws Exception {
            hRestrictions = new JsonHelper(jDismissalRestrictions);
            url = hRestrictions.getString("url");
            usersUrl = hRestrictions.getString("users_url");
            teamsUrl = hRestrictions.getString("teams_url");
            users = returnUsersList(String.valueOf(hRestrictions.getJSONArray("users", new JSONArray())), LIBRARY_OBJECT);
            teams = returnTeamsList(String.valueOf(hRestrictions.getJSONArray("teams", new JSONArray())), LIBRARY_OBJECT);
            apps = returnAppsList(String.valueOf(hRestrictions.getJSONArray("apps", new JSONArray())), LIBRARY_OBJECT);
        }

        public String getUrl() {
            return url;
        }

        public String getUsersUrl() {
            return usersUrl;
        }

        public String getTeamsUrl() {
            return teamsUrl;
        }

        public Collection<T> getUsers() {
            return users;
        }

        public Collection<T> getTeams() {
            return teams;
        }

        public Collection<T> getApps() {
            return apps;
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
