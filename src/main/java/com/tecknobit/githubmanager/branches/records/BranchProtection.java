package com.tecknobit.githubmanager.branches.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.apps.apps.records.GitHubApp;
import com.tecknobit.githubmanager.records.organization.Team;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.apps.apps.records.GitHubApp.returnAppsList;
import static com.tecknobit.githubmanager.records.organization.Team.returnTeamsList;
import static com.tecknobit.githubmanager.records.parents.User.returnUsersList;

/**
 * The {@code Branch} class is useful to format a GitHub's branch protection
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/branches/branch-protection#get-branch-protection">
 *             Get branch protection</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/branches/branch-protection#update-branch-protection">
 *             Update branch protection</a>
 *     </li>ì
 * </ul>
 * @see GitHubResponse
 **/
public class BranchProtection extends GitHubResponse {

    /**
     * {@code enabled} whether the branch protection is enabled
     **/
    private final boolean enabled;

    /**
     * {@code url} of the branch protection
     **/
    private final String url;

    /**
     * {@code requiredStatusCheck} protected branch required status check of the branch protection
     **/
    private final RequiredStatusCheck requiredStatusCheck;

    /**
     * {@code enforceAdmins} protected branch admin enforced of the branch protection
     **/
    private final ProtectionItem enforceAdmins;

    /**
     * {@code requiredPullRequestReviews} protected branch pull request review of the branch protection
     **/
    private final RequiredPullRequestReviews<Object> requiredPullRequestReviews;

    /**
     * {@code restrictions} of the branch protection
     **/
    private final Restrictions<Object> restrictions;

    /**
     * {@code requiredLinearHistory} whether the branch protection allows deletions
     **/
    private final boolean requiredLinearHistory;

    /**
     * {@code allowForcePushes} whether the branch protection allows deletions
     **/
    private final boolean allowForcePushes;

    /**
     * {@code allowDeletions} whether the branch protection allows deletions
     **/
    private final boolean allowDeletions;

    /**
     * {@code blockCreations} whether the branch protection blocks creations
     **/
    private final boolean blockCreations;

    /**
     * {@code requiredConversationResolution} whether the branch protection requires conversation resolution
     **/
    private final boolean requiredConversationResolution;

    /**
     * {@code name} of the branch protection
     **/
    private final String name;

    /**
     * {@code protectionUrl} protection url of the branch protection
     **/
    private final String protectionUrl;

    /**
     * {@code requiredSignatures}  required signatures of the branch protection
     **/
    private final ProtectionItem requiredSignatures;

    /**
     * {@code lockBranch} whether branch protection is locked
     **/
    private final boolean lockBranch;

    /**
     * {@code allowForkSyncing} whether the branch protection allows fork syncing
     **/
    private final boolean allowForkSyncing;

    /**
     * Constructor to init a {@link BranchProtection}
     *
     * @param enabled                        : whether the branch protection is enabled
     * @param url                            : url of the branch protection
     * @param requiredStatusCheck            : protected branch required status check of the branch protection
     * @param enforceAdmins                  : protected branch admin enforced of the branch protection
     * @param requiredPullRequestReviews     : protected branch pull request review of the branch protection
     * @param restrictions                   : restrictions of the branch protection
     * @param requiredLinearHistory          : whether the branch protection allows deletions
     * @param allowForcePushes               : whether the branch protection allows deletions
     * @param allowDeletions                 : whether the branch protection allows deletions
     * @param blockCreations                 : whether the branch protection blocks creations
     * @param requiredConversationResolution : whether the branch protection requires conversation resolution
     * @param name                           : name of the branch protection
     * @param protectionUrl                  : protection url of the branch protection
     * @param requiredSignatures             : required signatures of the branch protection
     * @param lockBranch                     : whether branch protection is locked
     * @param allowForkSyncing               : whether the branch protection allows fork syncing
     **/
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
     * @param jBranchProtection : branch protection details as {@link JSONObject}
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

    /**
     * Method to get {@link #enabled} instance <br>
     * No-any params required
     *
     * @return {@link #enabled} instance as boolean
     **/
    public boolean isEnabled() {
        return enabled;
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
     * Method to get {@link #requiredStatusCheck} instance <br>
     * No-any params required
     *
     * @return {@link #requiredStatusCheck} instance as {@link RequiredStatusCheck}
     **/
    public RequiredStatusCheck getRequiredStatusCheck() {
        return requiredStatusCheck;
    }

    /**
     * Method to get {@link #enforceAdmins} instance <br>
     * No-any params required
     *
     * @return {@link #enforceAdmins} instance as {@link ProtectionItem}
     **/
    public ProtectionItem getEnforceAdmins() {
        return enforceAdmins;
    }

    /**
     * Method to get {@link #requiredPullRequestReviews} instance <br>
     * No-any params required
     *
     * @return {@link #requiredPullRequestReviews} instance as {@link RequiredPullRequestReviews} of {@link Object}
     **/
    public RequiredPullRequestReviews<Object> getRequiredPullRequestReviews() {
        return requiredPullRequestReviews;
    }

    /**
     * Method to get {@link #restrictions} instance <br>
     * No-any params required
     *
     * @return {@link #restrictions} instance as {@link Restrictions} of {@link Object}
     **/
    public Restrictions<Object> getRestrictions() {
        return restrictions;
    }

    /**
     * Method to get {@link #requiredLinearHistory} instance <br>
     * No-any params required
     *
     * @return {@link #requiredLinearHistory} instance as boolean
     **/
    public boolean isRequiredLinearHistory() {
        return requiredLinearHistory;
    }

    /**
     * Method to get {@link #allowForcePushes} instance <br>
     * No-any params required
     *
     * @return {@link #allowForcePushes} instance as boolean
     **/
    public boolean isAllowForcePushes() {
        return allowForcePushes;
    }

    /**
     * Method to get {@link #allowDeletions} instance <br>
     * No-any params required
     *
     * @return {@link #allowDeletions} instance as boolean
     **/
    public boolean isAllowDeletions() {
        return allowDeletions;
    }

    /**
     * Method to get {@link #blockCreations} instance <br>
     * No-any params required
     *
     * @return {@link #blockCreations} instance as boolean
     **/
    public boolean isBlockCreations() {
        return blockCreations;
    }

    /**
     * Method to get {@link #requiredConversationResolution} instance <br>
     * No-any params required
     *
     * @return {@link #requiredConversationResolution} instance as boolean
     **/
    public boolean isRequiredConversationResolution() {
        return requiredConversationResolution;
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
     * Method to get {@link #protectionUrl} instance <br>
     * No-any params required
     *
     * @return {@link #protectionUrl} instance as {@link String}
     **/
    public String getProtectionUrl() {
        return protectionUrl;
    }

    /**
     * Method to get {@link #requiredSignatures} instance <br>
     * No-any params required
     *
     * @return {@link #requiredSignatures} instance as {@link ProtectionItem}
     **/
    public ProtectionItem getRequiredSignatures() {
        return requiredSignatures;
    }

    /**
     * Method to get {@link #lockBranch} instance <br>
     * No-any params required
     *
     * @return {@link #lockBranch} instance as boolean
     **/
    public boolean isLockBranch() {
        return lockBranch;
    }

    /**
     * Method to get {@link #allowForkSyncing} instance <br>
     * No-any params required
     *
     * @return {@link #allowForkSyncing} instance as boolean
     **/
    public boolean isAllowForkSyncing() {
        return allowForkSyncing;
    }

    /**
     * The {@code RequiredStatusCheck} class is useful to format a GitHub's required status check for {@link BranchProtection}
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class RequiredStatusCheck extends InnerClassItem {

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

        /**
         * {@code contextsUrl} contexts url of the required status check
         **/
        private final String contextsUrl;

        /**
         * {@code strict} whether the required status check is strict
         **/
        private final boolean strict;

        /**
         * Constructor to init a {@link RequiredStatusCheck}
         *
         * @param checks  : checks of the required status check
         * @param strict: whether the required status check is strict
         * @apiNote this constructor is useful when the {@link RequiredStatusCheck} have to be updated
         **/
        public RequiredStatusCheck(ArrayList<Check> checks, boolean strict) {
            this(null, null, checks, null, strict);
        }

        /**
         * Constructor to init a {@link RequiredStatusCheck}
         *
         * @param enforcementLevel : type of the enforcement level of the required status check
         * @param contexts         : contexts of the required status check
         * @param checks           : checks of the required status check
         * @param contextsUrl: contexts url of the required status check
         * @param strict: whether the required status check is strict
         **/
        public RequiredStatusCheck(String enforcementLevel, ArrayList<String> contexts, ArrayList<Check> checks,
                                   String contextsUrl, boolean strict) {
            super(null);
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
            super(jRequiredStatusCheck);
            enforcementLevel = hItem.getString("enforcement_level");
            contexts = returnStringsList(hItem.getJSONArray("contexts"));
            checks = new ArrayList<>();
            JSONArray jChecks = hItem.getJSONArray("checks", new JSONArray());
            for (int j = 0; j < jChecks.length(); j++)
                checks.add(new Check(jChecks.getJSONObject(j)));
            contextsUrl = hItem.getString("contexts_url");
            strict = hItem.getBoolean("strict");
        }

        /**
         * Method to get {@link #enforcementLevel} instance <br>
         * No-any params required
         *
         * @return {@link #enforcementLevel} instance as {@link String}
         **/
        public String getEnforcementLevel() {
            return enforcementLevel;
        }

        /**
         * Method to get {@link #contexts} instance <br>
         * No-any params required
         *
         * @return {@link #contexts} instance as {@link Collection} of {@link String}
         **/
        public Collection<String> getContexts() {
            return contexts;
        }

        /**
         * Method to get {@link #checks} instance <br>
         * No-any params required
         *
         * @return {@link #checks} instance as {@link Collection} of {@link Check}
         **/
        public Collection<Check> getChecks() {
            return checks;
        }

        /**
         * Method to get {@link #contextsUrl} instance <br>
         * No-any params required
         *
         * @return {@link #contextsUrl} instance as {@link String}
         **/
        public String getContextsUrl() {
            return contextsUrl;
        }

        /**
         * Method to get {@link #strict} instance <br>
         * No-any params required
         *
         * @return {@link #strict} instance as boolean
         **/
        public boolean isStrict() {
            return strict;
        }

        /**
         * The {@code Check} class is useful to format a GitHub's check for {@link RequiredStatusCheck}
         *
         * @author N7ghtm4r3 - Tecknobit
         * @see InnerClassItem
         **/
        public static class Check extends InnerClassItem {

            /**
             * {@code context} of the check
             **/
            private final String context;

            /**
             * {@code appId} app identifier of the check
             **/
            private final long appId;

            public Check(String context) {
                this(context, 0);
            }

            /**
             * Constructor to init a {@link Check}
             *
             * @param context : context of the check
             * @param appId   : app identifier of the check
             **/
            public Check(String context, long appId) {
                super(null);
                this.context = context;
                this.appId = appId;
            }

            /**
             * Constructor to init a {@link Check}
             *
             * @param jCheck : check details as {@link JSONObject}
             **/
            public Check(JSONObject jCheck) {
                super(jCheck);
                context = hItem.getString("context");
                appId = hItem.getLong("app_id", 0);
            }

            /**
             * Method to get {@link #context} instance <br>
             * No-any params required
             *
             * @return {@link #context} instance as {@link String}
             **/
            public String getContext() {
                return context;
            }

            /**
             * Method to get {@link #appId} instance <br>
             * No-any params required
             *
             * @return {@link #appId} instance as long
             **/
            public long getAppId() {
                return appId;
            }

        }

    }

    /**
     * The {@code ProtectionItem} class is useful to format a GitHub's protection item of the {@link BranchProtection}
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class ProtectionItem extends InnerClassItem {

        /**
         * {@code url} of the protection item
         **/
        private final String url;

        /**
         * {@code enabled} whether the protection item is enabled
         **/
        private final boolean enabled;

        /**
         * Constructor to init a {@link ProtectionItem}
         *
         * @param url     : url of the protection item
         * @param enabled : whether the protection item is enabled
         **/
        public ProtectionItem(String url, boolean enabled) {
            super(null);
            this.url = url;
            this.enabled = enabled;
        }

        /**
         * Constructor to init a {@link ProtectionItem}
         *
         * @param jProtectionItem : protection item details as {@link JSONObject}
         **/
        public ProtectionItem(JSONObject jProtectionItem) {
            super(jProtectionItem);
            url = hItem.getString("url");
            enabled = hItem.getBoolean("enabled");
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
         * Method to get {@link #enabled} instance <br>
         * No-any params required
         *
         * @return {@link #enabled} instance as boolean
         **/
        public boolean isEnabled() {
            return enabled;
        }

    }

    /**
     * The {@code RequiredPullRequestReviews} class is useful to format a GitHub's required pull request reviews
     * for the {@link BranchProtection}
     *
     * @param <T> if you need to update the {@link RequiredPullRequestReviews} you can instantiate it with the
     *            {@link String} as type parameter, else you can simply use it with {@link Object} as type parameter
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class RequiredPullRequestReviews<T> extends InnerClassItem {

        /**
         * {@code url} of the required pull request reviews
         **/
        private final String url;

        /**
         * {@code dismissalRestrictions} dismissal restrictions of the required pull request reviews
         **/
        private final Restrictions<T> dismissalRestrictions;

        /**
         * {@code dismissStaleReviews} whether the required pull request reviews dismiss stale reviews
         **/
        private final boolean dismissStaleReviews;

        /**
         * {@code requireCodeOwnerReviews} whether the required pull request reviews requires code owner reviews
         **/
        private final boolean requireCodeOwnerReviews;

        /**
         * {@code requiredApprovingReviewCount} required approving review count of the required pull request reviews
         **/
        private final int requiredApprovingReviewCount;

        /**
         * {@code requireLastPushApproval} whether the required pull request reviews requires last push approval
         **/
        private final boolean requireLastPushApproval;

        /**
         * {@code bypassPullRequestAllowances} bypass pull request allowances of the required pull request reviews
         **/
        private final Restrictions<T> bypassPullRequestAllowances;

        /**
         * Constructor to init a {@link RequiredPullRequestReviews}
         *
         * @param url                          : url of the required pull request reviews
         * @param dismissalRestrictions        : dismissal restrictions of the required pull request reviews
         * @param dismissStaleReviews          : whether the required pull request reviews dismiss stale reviews
         * @param requireCodeOwnerReviews      : whether the required pull request reviews requires code owner reviews
         * @param requiredApprovingReviewCount :  whether the required pull request reviews requires code owner reviews
         * @param requireLastPushApproval      : whether the required pull request reviews requires last push approval
         * @param bypassPullRequestAllowances  : bypass pull request allowances of the required pull request reviews
         **/
        public RequiredPullRequestReviews(String url, Restrictions<T> dismissalRestrictions, boolean dismissStaleReviews,
                                          boolean requireCodeOwnerReviews, int requiredApprovingReviewCount,
                                          boolean requireLastPushApproval, Restrictions<T> bypassPullRequestAllowances) {
            super(null);
            this.url = url;
            this.dismissalRestrictions = dismissalRestrictions;
            this.dismissStaleReviews = dismissStaleReviews;
            this.requireCodeOwnerReviews = requireCodeOwnerReviews;
            this.requiredApprovingReviewCount = requiredApprovingReviewCount;
            this.requireLastPushApproval = requireLastPushApproval;
            this.bypassPullRequestAllowances = bypassPullRequestAllowances;
        }

        /**
         * Constructor to init a {@link RequiredPullRequestReviews}
         *
         * @param jRequestReviews : required pull request reviews details as {@link JSONObject}
         **/
        public RequiredPullRequestReviews(JSONObject jRequestReviews) throws Exception {
            super(jRequestReviews);
            url = hItem.getString("url");
            dismissalRestrictions = new Restrictions<>(hItem.getJSONObject("dismissal_restrictions",
                    new JSONObject()));
            dismissStaleReviews = hItem.getBoolean("dismiss_stale_reviews");
            requireCodeOwnerReviews = hItem.getBoolean("require_code_owner_reviews");
            requiredApprovingReviewCount = hItem.getInt("required_approving_review_count", 0);
            requireLastPushApproval = hItem.getBoolean("require_last_push_approval");
            JSONObject jRequestAllowances = hItem.getJSONObject("bypass_pull_request_allowances");
            if (jRequestAllowances != null)
                bypassPullRequestAllowances = new Restrictions<>(jRequestAllowances);
            else
                bypassPullRequestAllowances = null;
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
         * Method to get {@link #dismissalRestrictions} instance <br>
         * No-any params required
         *
         * @return {@link #dismissalRestrictions} instance as {@link Restrictions} of {@link T}
         **/
        public Restrictions<T> getDismissalRestrictions() {
            return dismissalRestrictions;
        }

        /**
         * Method to get {@link #dismissStaleReviews} instance <br>
         * No-any params required
         *
         * @return {@link #dismissStaleReviews} instance as boolean
         **/
        public boolean isDismissStaleReviews() {
            return dismissStaleReviews;
        }

        /**
         * Method to get {@link #requireCodeOwnerReviews} instance <br>
         * No-any params required
         *
         * @return {@link #requireCodeOwnerReviews} instance as boolean
         **/
        public boolean isRequireCodeOwnerReviews() {
            return requireCodeOwnerReviews;
        }

        /**
         * Method to get {@link #requiredApprovingReviewCount} instance <br>
         * No-any params required
         *
         * @return {@link #requiredApprovingReviewCount} instance as int
         **/
        public int getRequiredApprovingReviewCount() {
            return requiredApprovingReviewCount;
        }

        /**
         * Method to get {@link #requireLastPushApproval} instance <br>
         * No-any params required
         *
         * @return {@link #requireLastPushApproval} instance as boolean
         **/
        public boolean isRequireLastPushApproval() {
            return requireLastPushApproval;
        }

        /**
         * Method to get {@link #bypassPullRequestAllowances} instance <br>
         * No-any params required
         *
         * @return {@link #bypassPullRequestAllowances} instance as {@link Restrictions} of {@link T}
         **/
        public Restrictions<T> getBypassPullRequestAllowances() {
            return bypassPullRequestAllowances;
        }

    }

    /**
     * The {@code Restrictions} class is useful to format a GitHub's restrictions for the {@link BranchProtection}
     *
     * @param <T> if you need to update the {@link Restrictions} you can instantiate it with the
     *            {@link String} as type parameter, else you can simply use it with {@link Object} as type parameter
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class Restrictions<T> extends InnerClassItem {

        /**
         * {@code url} of the restrictions
         **/
        private final String url;

        /**
         * {@code usersUrl} users url of the restrictions
         **/
        private final String usersUrl;

        /**
         * {@code teamsUrl} teams url of the restrictions
         **/
        private final String teamsUrl;

        /**
         * {@code appsUrl} apps url of the restrictions
         **/
        private final String appsUrl;

        /**
         * {@code users} list of the restrictions
         **/
        private final ArrayList<T> users;

        /**
         * {@code teams} list of the restrictions
         **/
        private final ArrayList<T> teams;

        /**
         * {@code apps} list of the restrictions
         **/
        private final ArrayList<T> apps;

        /**
         * Constructor to init a {@link Restrictions}
         *
         * @param users           : users list of the restrictions
         * @param teams: teams list of the restrictions
         * @apiNote this constructor is useful when the {@link Restrictions} have to be updated
         **/
        public Restrictions(ArrayList<String> users, ArrayList<String> teams) {
            this(users, teams, null);
        }

        /**
         * Constructor to init a {@link Restrictions}
         *
         * @param users           : users list of the restrictions
         * @param teams: teams list of the restrictions
         * @param apps: apps list of the restrictions
         * @apiNote this constructor is useful when the {@link Restrictions} have to be updated
         **/
        public Restrictions(ArrayList<String> users, ArrayList<String> teams, ArrayList<String> apps) {
            super(null);
            this.users = (ArrayList<T>) users;
            this.teams = (ArrayList<T>) teams;
            this.apps = (ArrayList<T>) apps;
            url = null;
            usersUrl = null;
            teamsUrl = null;
            appsUrl = null;
        }

        /**
         * Constructor to init a {@link Restrictions}
         *
         * @param url           : url of the restrictions
         * @param usersUrl: users url of the restrictions
         * @param teamsUrl: teams url of the restrictions
         * @param appsUrl           : apps url of the restrictions
         * @param users           : users list of the restrictions
         * @param teams: teams list of the restrictions
         * @param apps: apps list of the restrictions
         **/
        public Restrictions(String url, String usersUrl, String teamsUrl, String appsUrl, ArrayList<User> users,
                            ArrayList<Team> teams, ArrayList<GitHubApp> apps) {
            super(null);
            this.url = url;
            this.usersUrl = usersUrl;
            this.teamsUrl = teamsUrl;
            this.appsUrl = appsUrl;
            this.users = (ArrayList<T>) users;
            this.teams = (ArrayList<T>) teams;
            this.apps = (ArrayList<T>) apps;
        }

        /**
         * Constructor to init a {@link Restrictions}
         *
         * @param jRestrictions           : restrictions details as {@link JSONObject}
         **/
        public Restrictions(JSONObject jRestrictions) throws Exception {
            super(jRestrictions);
            url = hItem.getString("url");
            usersUrl = hItem.getString("users_url");
            teamsUrl = hItem.getString("teams_url");
            appsUrl = hItem.getString("apps_url");
            users = returnUsersList(String.valueOf(hItem.getJSONArray("users", new JSONArray())), LIBRARY_OBJECT);
            teams = returnTeamsList(String.valueOf(hItem.getJSONArray("teams", new JSONArray())), LIBRARY_OBJECT);
            apps = returnAppsList(String.valueOf(hItem.getJSONArray("apps", new JSONArray())), LIBRARY_OBJECT);
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
         * Method to get {@link #usersUrl} instance <br>
         * No-any params required
         *
         * @return {@link #usersUrl} instance as {@link String}
         **/
        public String getUsersUrl() {
            return usersUrl;
        }

        /**
         * Method to get {@link #teamsUrl} instance <br>
         * No-any params required
         *
         * @return {@link #teamsUrl} instance as {@link String}
         **/
        public String getTeamsUrl() {
            return teamsUrl;
        }

        /**
         * Method to get {@link #appsUrl} instance <br>
         * No-any params required
         *
         * @return {@link #appsUrl} instance as {@link String}
         **/
        public String getAppsUrl() {
            return appsUrl;
        }

        /**
         * Method to get {@link #users} instance <br>
         * No-any params required
         *
         * @return {@link #users} instance as {@link Collection} of {@link T}
         **/
        public Collection<T> getUsers() {
            return users;
        }

        /**
         * Method to get {@link #teams} instance <br>
         * No-any params required
         *
         * @return {@link #teams} instance as {@link Collection} of {@link T}
         **/
        public Collection<T> getTeams() {
            return teams;
        }

        /**
         * Method to get {@link #apps} instance <br>
         * No-any params required
         *
         * @return {@link #apps} instance as {@link Collection} of {@link T}
         **/
        public Collection<T> getApps() {
            return apps;
        }

    }

}
