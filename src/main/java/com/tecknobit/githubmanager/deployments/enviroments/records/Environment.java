package com.tecknobit.githubmanager.deployments.enviroments.records;

import com.tecknobit.githubmanager.records.parents.BaseResponseDetails;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code Environment} class is useful to format a GitHub's environment
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/deployments/environments#get-an-environment">
 *             Get an environment</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/deployments/environments#create-or-update-an-environment">
 *             Create or update an environment</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see BaseResponseDetails
 **/
public class Environment extends BaseResponseDetails {

    /**
     * {@code nodeId} node id of the environment
     **/
    private final String nodeId;

    /**
     * {@code htmlUrl} html url of the environment
     **/
    private final String htmlUrl;

    /**
     * {@code createdAt} the time that the environment was created, in ISO 8601 format
     **/
    private final String createdAt;

    /**
     * {@code updatedAt} the time that the environment was last updated, in ISO 8601 format
     **/
    private final String updatedAt;

    /**
     * {@code protectionRules} list of {@link ProtectionRule}
     **/
    private final ArrayList<ProtectionRule> protectionRules;

    /**
     * {@code deploymentBranchPolicy} the type of deployment branch policy for this environment. To allow all branches
     * to deploy, set to null
     **/
    private final DeploymentBranchPolicy deploymentBranchPolicy;

    /**
     * Constructor to init a {@link Environment}
     *
     * @param id                     : node id of the environment
     * @param name                   : the name of the item
     * @param url                    : url value
     * @param nodeId                 : identifier value
     * @param htmlUrl                : html url of the environment
     * @param createdAt              : the time that the environment was created, in ISO 8601 format
     * @param updatedAt              : the time that the environment was last updated, in ISO 8601 format
     * @param protectionRules        : list of {@link ProtectionRule}
     * @param deploymentBranchPolicy :  the type of deployment branch policy for this environment. To allow all branches
     *                               to deploy, set to null
     **/
    public Environment(long id, String name, String url, String nodeId, String htmlUrl, String createdAt, String updatedAt,
                       ArrayList<ProtectionRule> protectionRules, DeploymentBranchPolicy deploymentBranchPolicy) {
        super(id, name, url);
        this.nodeId = nodeId;
        this.htmlUrl = htmlUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.protectionRules = protectionRules;
        this.deploymentBranchPolicy = deploymentBranchPolicy;
    }

    /**
     * Constructor to init a {@link Environment}
     *
     * @param jEnvironment : environment details as {@link JSONObject}
     **/
    public Environment(JSONObject jEnvironment) {
        super(jEnvironment);
        nodeId = hResponse.getString("node_id");
        htmlUrl = hResponse.getString("html_url");
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
        protectionRules = new ArrayList<>();
        JSONArray jProtectionRules = hResponse.getJSONArray("protection_rules");
        for (int j = 0; j < jProtectionRules.length(); j++)
            protectionRules.add(new ProtectionRule(jProtectionRules.getJSONObject(j)));
        JSONObject jDeploymentBranchPolicy = hResponse.getJSONObject("deployment_branch_policy");
        if (jDeploymentBranchPolicy != null)
            deploymentBranchPolicy = new DeploymentBranchPolicy(jDeploymentBranchPolicy);
        else
            deploymentBranchPolicy = null;
    }

    /**
     * Method to get {@link #nodeId} instance <br>
     * No-any params required
     *
     * @return {@link #nodeId} instance as {@link String}
     **/
    public String getNodeId() {
        return nodeId;
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

    /**
     * Method to get {@link #createdAt} instance <br>
     * No-any params required
     *
     * @return {@link #createdAt} instance as {@link String}
     **/
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Method to get {@link #createdAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #createdAt} timestamp as long
     **/
    public long getCreatedAtTimestamp() {
        return getDateTimestamp(createdAt);
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
     * Method to get {@link #protectionRules} instance <br>
     * No-any params required
     *
     * @return {@link #protectionRules} instance as {@link Collection} of {@link ProtectionRule}
     **/
    public Collection<ProtectionRule> getProtectionRules() {
        return protectionRules;
    }

    /**
     * Method to get {@link #deploymentBranchPolicy} instance <br>
     * No-any params required
     *
     * @return {@link #deploymentBranchPolicy} instance as {@link DeploymentBranchPolicy}
     **/
    public DeploymentBranchPolicy getDeploymentBranchPolicy() {
        return deploymentBranchPolicy;
    }

    /**
     * The {@code ProtectionRule} class is useful to format a GitHub's protection rule
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class ProtectionRule extends InnerClassItem {

        /**
         * {@code id} of the rule
         **/
        private final long id;

        /**
         * {@code nodeId} node id of the rule
         **/
        private final String nodeId;

        /**
         * {@code type}  of the rule
         **/
        private final String type;

        /**
         * {@code ruleValue} value of the rule
         **/
        private Object ruleValue;

        /**
         * Constructor to init a {@link ProtectionRule}
         *
         * @param id        : id of the rule
         * @param nodeId    : node id of the rule
         * @param type      : type of the rule
         * @param ruleValue : value of the rule
         **/
        public ProtectionRule(long id, String nodeId, String type, Object ruleValue) {
            super(null);
            this.id = id;
            this.nodeId = nodeId;
            this.type = type;
            this.ruleValue = ruleValue;
        }

        /**
         * Constructor to init a {@link ProtectionRule}
         *
         * @param jProtectionRule : protection rule details as {@link JSONObject}
         **/
        public ProtectionRule(JSONObject jProtectionRule) {
            super(jProtectionRule);
            id = hItem.getLong("id", 0);
            jProtectionRule.remove("id");
            nodeId = hItem.getString("node_id");
            jProtectionRule.remove("node_id");
            type = hItem.getString("type");
            jProtectionRule.remove("type");
            try {
                ruleValue = hItem.get(jProtectionRule.keys().next());
            } catch (NoSuchElementException e) {
                ruleValue = null;
            }
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
         * Method to get {@link #nodeId} instance <br>
         * No-any params required
         *
         * @return {@link #nodeId} instance as {@link String}
         **/
        public String getNodeId() {
            return nodeId;
        }

        /**
         * Method to get {@link #type} instance <br>
         * No-any params required
         *
         * @return {@link #type} instance as {@link String}
         **/
        public String getType() {
            return type;
        }

        /**
         * Method to get {@link #ruleValue} instance <br>
         * No-any params required
         *
         * @return {@link #ruleValue} instance as {@link T}
         **/
        public <T> T getRuleValue() {
            return (T) ruleValue;
        }

    }

    /**
     * The {@code DeploymentBranchPolicy} class is useful to format a GitHub's deployment branch policy
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class DeploymentBranchPolicy extends InnerClassItem {

        /**
         * {@code protectedBranches} whether only branches with branch protection rules can deploy to this environment
         **/
        private final boolean protectedBranches;

        /**
         * {@code customBranchPolicies} whether only branches that match the specified name patterns can deploy to this environment
         **/
        private final boolean customBranchPolicies;

        /**
         * Constructor to init a {@link DeploymentBranchPolicy}
         *
         * @param protectedBranches    : whether only branches with branch protection rules can deploy to this environment.
         *                             If `protected_branches` is {@code "true"}, {@code "custom_branch_policies"} must be {@code "false"};
         *                             if `protected_branches` is {@code "false"}, {@code "custom_branch_policies"} must be {@code "true"}.
         * @param customBranchPolicies : whether only branches that match the specified name patterns can deploy to this environment.
         *                             If {@code "custom_branch_policies"} is {@code "true"}, {@code "protected_branches"} must be {@code "false"};
         *                             if {@code "custom_branch_policies"} is `{@code "false"}, {@code "protected_branches"} must be {@code "true"}
         **/
        public DeploymentBranchPolicy(boolean protectedBranches, boolean customBranchPolicies) {
            super(null);
            this.protectedBranches = protectedBranches;
            this.customBranchPolicies = customBranchPolicies;
        }

        /**
         * Constructor to init a {@link DeploymentBranchPolicy}
         *
         * @param jDeploymentBranchPolicy : deployment branch policy details as {@link JSONObject}
         **/
        public DeploymentBranchPolicy(JSONObject jDeploymentBranchPolicy) {
            super(jDeploymentBranchPolicy);
            protectedBranches = hItem.getBoolean("protected_branches");
            customBranchPolicies = hItem.getBoolean("custom_branch_policies");
        }

        /**
         * Method to get {@link #protectedBranches} instance <br>
         * No-any params required
         *
         * @return {@link #protectedBranches} instance as boolean
         **/
        public boolean areProtectedBranches() {
            return protectedBranches;
        }

        /**
         * Method to get {@link #customBranchPolicies} instance <br>
         * No-any params required
         *
         * @return {@link #customBranchPolicies} instance as boolean
         **/
        public boolean areCustomBranchPolicies() {
            return customBranchPolicies;
        }

        /**
         * {@inheritDoc}
         **/
        @Override
        public String toString() {
            return new JSONObject().put("protected_branches", protectedBranches)
                    .put("custom_branch_policies", customBranchPolicies).toString();
        }

    }

}
