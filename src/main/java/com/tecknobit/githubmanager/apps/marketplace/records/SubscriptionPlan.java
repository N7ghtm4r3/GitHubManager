package com.tecknobit.githubmanager.apps.marketplace.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import org.json.JSONObject;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;
import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code SubscriptionPlan} class is useful to format a GitHub's subscription plan
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/apps/marketplace#get-a-subscription-plan-for-an-account">
 *             Get a subscription plan for an account</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/apps/marketplace#list-accounts-for-a-plan">
 *             List accounts for a plan</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class SubscriptionPlan extends GitHubResponse {

    /**
     * {@code url} the ID of the subscription plan
     **/
    private final String url;

    /**
     * {@code type} the ID of the subscription plan
     **/
    private final String type;

    /**
     * {@code id} the ID of the subscription plan
     **/
    private final long id;

    /**
     * {@code login} the login of the subscription plan
     **/
    private final String login;

    /**
     * {@code organizationBillingEmail} the organization billing email of the subscription plan
     **/
    private final String organizationBillingEmail;

    /**
     * {@code email} the email of the subscription plan
     **/
    private final String email;

    /**
     * {@code marketplacePendingChange} the marketplace pending change of the subscription plan
     **/
    private final MarketplacePendingChange marketplacePendingChange;

    /**
     * {@code marketPlacePurchase} the marketplace purchase of the subscription plan
     **/
    private final MarketPlacePurchase marketPlacePurchase;

    /**
     * Constructor to init a {@link SubscriptionPlan}
     *
     * @param url:                      the url of the subscription plan
     * @param type:                     the type of the subscription plan
     * @param id:                       the id of the subscription plan
     * @param login:                    the login of the subscription plan
     * @param organizationBillingEmail: the organization billing email of the subscription plan
     * @param email:                    the email of the subscription plan
     * @param marketplacePendingChange: the marketplace pending change of the subscription plan
     * @param marketPlacePurchase:      the marketplace purchase of the subscription plan
     **/
    public SubscriptionPlan(String url, String type, long id, String login, String organizationBillingEmail, String email,
                            MarketplacePendingChange marketplacePendingChange, MarketPlacePurchase marketPlacePurchase) {
        super(null);
        this.url = url;
        this.type = type;
        this.id = id;
        this.login = login;
        this.organizationBillingEmail = organizationBillingEmail;
        this.email = email;
        this.marketplacePendingChange = marketplacePendingChange;
        this.marketPlacePurchase = marketPlacePurchase;
    }

    /**
     * Constructor to init a {@link SubscriptionPlan}
     *
     * @param jSubscriptionPlan: subscription plan details as {@link JSONObject}
     **/
    public SubscriptionPlan(JSONObject jSubscriptionPlan) {
        super(jSubscriptionPlan);
        url = hResponse.getString("url");
        type = hResponse.getString("type");
        id = hResponse.getLong("id", 0);
        login = hResponse.getString("login");
        organizationBillingEmail = hResponse.getString("organization_billing_email");
        email = hResponse.getString("email");
        marketplacePendingChange = new MarketplacePendingChange(hResponse.getJSONObject("marketplace_pending_change",
                new JSONObject()));
        marketPlacePurchase = new MarketPlacePurchase(hResponse.getJSONObject("marketplace_purchase",
                new JSONObject()));
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

    /**
     * Method to get {@link #type} instance <br>
     * Any params required
     *
     * @return {@link #type} instance as {@link String}
     **/
    public String getType() {
        return type;
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
     * Method to get {@link #login} instance <br>
     * Any params required
     *
     * @return {@link #login} instance as {@link String}
     **/
    public String getLogin() {
        return login;
    }

    /**
     * Method to get {@link #organizationBillingEmail} instance <br>
     * Any params required
     *
     * @return {@link #organizationBillingEmail} instance as {@link String}
     **/
    public String getOrganizationBillingEmail() {
        return organizationBillingEmail;
    }

    /**
     * Method to get {@link #email} instance <br>
     * Any params required
     *
     * @return {@link #email} instance as {@link String}
     **/
    public String getEmail() {
        return email;
    }

    /**
     * Method to get {@link #marketplacePendingChange} instance <br>
     * Any params required
     *
     * @return {@link #marketplacePendingChange} instance as {@link MarketplacePendingChange}
     **/
    public MarketplacePendingChange getMarketplacePendingChange() {
        return marketplacePendingChange;
    }

    /**
     * Method to get {@link #marketPlacePurchase} instance <br>
     * Any params required
     *
     * @return {@link #marketPlacePurchase} instance as {@link MarketPlacePurchase}
     **/
    public MarketPlacePurchase getMarketPlacePurchase() {
        return marketPlacePurchase;
    }

    /**
     * The {@code MarketplacePendingChange} class is useful to format a GitHub's marketplace pending change for a
     * subscription plan
     *
     * @author N7ghtm4r3 - Tecknobit
     **/
    public static class MarketplacePendingChange {

        /**
         * {@code effectiveDate} the effective date of the marketplace pending change
         **/
        private final String effectiveDate;

        /**
         * {@code id} the id of the marketplace pending change
         **/
        private final long id;

        /**
         * {@code plan} the plan of the marketplace pending change
         **/
        private final Plan plan;

        /**
         * Constructor to init a {@link MarketplacePendingChange}
         *
         * @param effectiveDate: the effective date of the marketplace pending change
         * @param id:            the id of the marketplace pending change
         * @param plan:          the plan of the marketplace pending change
         **/
        public MarketplacePendingChange(String effectiveDate, long id, Plan plan) {
            this.effectiveDate = effectiveDate;
            this.id = id;
            this.plan = plan;
        }

        /**
         * Constructor to init a {@link MarketplacePendingChange}
         *
         * @param jPendingChange: marketplace pending change as {@link JSONObject}
         **/
        public MarketplacePendingChange(JSONObject jPendingChange) {
            this(jPendingChange.getString("effective_date"), jPendingChange.getLong("id"),
                    new Plan(jPendingChange.getJSONObject("plan")));
        }

        /**
         * Method to get {@link #effectiveDate} instance <br>
         * Any params required
         *
         * @return {@link #effectiveDate} instance as {@link String}
         **/
        public String getEffectiveDate() {
            return effectiveDate;
        }

        /**
         * Method to get {@link #effectiveDate} timestamp <br>
         * Any params required
         *
         * @return {@link #effectiveDate} timestamp as long
         **/
        public long getEffectiveDateTimestamp() {
            return getDateTimestamp(effectiveDate);
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
         * Method to get {@link #plan} instance <br>
         * Any params required
         *
         * @return {@link #plan} instance as {@link Plan}
         **/
        public Plan getPlan() {
            return plan;
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

    /**
     * The {@code MarketPlacePurchase} class is useful to format a GitHub's marketplace purchase for a
     * subscription plan
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class MarketPlacePurchase extends InnerClassItem {

        /**
         * {@code billingCycle} the billing cycle of the marketplace purchase
         **/
        private final String billingCycle;

        /**
         * {@code nextBillingDate} the next billing date of the marketplace purchase
         **/
        private final String nextBillingDate;

        /**
         * {@code unitCount} the unit count of the marketplace purchase
         **/
        private final double unitCount;

        /**
         * {@code onFreeTrial} whether is on a free trial mode
         **/
        private final boolean onFreeTrial;

        /**
         * {@code freeTrialEndsOn} the end date of the free trial
         **/
        private final String freeTrialEndsOn;

        /**
         * {@code updatedAt} the update date of the marketplace purchase
         **/
        private final String updatedAt;

        /**
         * {@code account} the account of the marketplace purchase
         **/
        private final Account account;

        /**
         * {@code plan} the plan of the marketplace purchase
         **/
        private final Plan plan;

        /**
         * Constructor to init a {@link MarketPlacePurchase}
         *
         * @param billingCycle:    the billing cycle of the marketplace purchase
         * @param nextBillingDate: the next billing date of the marketplace purchase
         * @param unitCount:       the unit count of the marketplace purchase
         * @param onFreeTrial:     whether is on a free trial mode
         * @param freeTrialEndsOn: the end date of the free trial
         * @param updatedAt:       the update date of the marketplace purchase
         * @param account:         the account of the marketplace purchase
         * @param plan:            the plan of the marketplace purchase
         **/
        public MarketPlacePurchase(String billingCycle, String nextBillingDate, double unitCount, boolean onFreeTrial,
                                   String freeTrialEndsOn, String updatedAt, Account account, Plan plan) {
            super(null);
            this.billingCycle = billingCycle;
            this.nextBillingDate = nextBillingDate;
            this.unitCount = unitCount;
            this.onFreeTrial = onFreeTrial;
            this.freeTrialEndsOn = freeTrialEndsOn;
            this.updatedAt = updatedAt;
            this.account = account;
            this.plan = plan;
        }

        /**
         * Constructor to init a {@link MarketPlacePurchase}
         *
         * @param jPlacePurchase: marketplace purchase details as {@link JSONObject}
         **/
        public MarketPlacePurchase(JSONObject jPlacePurchase) {
            super(jPlacePurchase);
            billingCycle = hItem.getString("billing_cycle");
            nextBillingDate = hItem.getString("next_billing_date");
            unitCount = hItem.getDouble("unit_count", 0);
            onFreeTrial = hItem.getBoolean("on_free_trial");
            freeTrialEndsOn = hItem.getString("free_trial_ends_on");
            updatedAt = hItem.getString("updated_at");
            account = new Account(hItem.getJSONObject("account", new JSONObject()));
            plan = new Plan(hItem.getJSONObject("plan"));
        }

        /**
         * Method to get {@link #billingCycle} instance <br>
         * Any params required
         *
         * @return {@link #billingCycle} instance as {@link String}
         **/
        public String getBillingCycle() {
            return billingCycle;
        }

        /**
         * Method to get {@link #nextBillingDate} instance <br>
         * Any params required
         *
         * @return {@link #nextBillingDate} instance as {@link String}
         **/
        public String getNextBillingDate() {
            return nextBillingDate;
        }

        /**
         * Method to get {@link #nextBillingDate} timestamp <br>
         * Any params required
         *
         * @return {@link #nextBillingDate} timestamp as long
         **/
        public long getNextBillingDateTimestamp() {
            return getDateTimestamp(nextBillingDate);
        }

        /**
         * Method to get {@link #unitCount} instance <br>
         * Any params required
         *
         * @return {@link #unitCount} instance as double
         **/
        public double getUnitCount() {
            return unitCount;
        }

        /**
         * Method to get {@link #unitCount} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #unitCount} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         **/
        public double getUnitCount(int decimals) {
            return roundValue(unitCount, decimals);
        }

        /**
         * Method to get {@link #onFreeTrial} instance <br>
         * Any params required
         *
         * @return {@link #onFreeTrial} instance as boolean
         **/
        public boolean isOnFreeTrial() {
            return onFreeTrial;
        }

        /**
         * Method to get {@link #freeTrialEndsOn} instance <br>
         * Any params required
         *
         * @return {@link #freeTrialEndsOn} instance as {@link String}
         **/
        public String getFreeTrialEndsOn() {
            return freeTrialEndsOn;
        }

        /**
         * Method to get {@link #freeTrialEndsOn} timestamp <br>
         * Any params required
         *
         * @return {@link #freeTrialEndsOn} timestamp as long
         **/
        public long getFreeTrialEndsOnTimestamp() {
            return getDateTimestamp(freeTrialEndsOn);
        }

        /**
         * Method to get {@link #updatedAt} instance <br>
         * Any params required
         *
         * @return {@link #updatedAt} instance as {@link String}
         **/
        public String getUpdatedAt() {
            return updatedAt;
        }

        /**
         * Method to get {@link #updatedAt} timestamp <br>
         * Any params required
         *
         * @return {@link #updatedAt} timestamp as long
         **/
        public long getUpdatedAtTimestamp() {
            return getDateTimestamp(updatedAt);
        }

        /**
         * Method to get {@link #account} instance <br>
         * Any params required
         *
         * @return {@link #account} instance as {@link Account}
         **/
        public Account getAccount() {
            return account;
        }

        /**
         * Method to get {@link #plan} instance <br>
         * Any params required
         *
         * @return {@link #plan} instance as {@link Plan}
         **/
        public Plan getPlan() {
            return plan;
        }

        /**
         * The {@code Account} class is useful to format a GitHub's account for a marketplace purchase
         *
         * @author N7ghtm4r3 - Tecknobit
         * @see InnerClassItem
         **/
        public static class Account extends InnerClassItem {

            /**
             * {@code login} the login of the account
             **/
            private final String login;

            /**
             * {@code id} the ID of the account
             **/
            private final long id;

            /**
             * {@code nodeId} the node ID of the account
             **/
            private final String nodeId;

            /**
             * {@code email} the email of the account
             **/
            private final String email;

            /**
             * {@code url} the ID of the account
             **/
            private final String url;

            /**
             * {@code organizationBillingEmail} the organization billing email of the account
             **/
            private final String organizationBillingEmail;

            /**
             * {@code type} the ID of the account
             **/
            private final String type;

            /**
             * Constructor to init a {@link Account}
             *
             * @param login:                    the login of the account
             * @param id:                       the id of the account
             * @param nodeId:                   the node id of the account
             * @param email:                    the email of the account
             * @param url:                      the url of the account
             * @param organizationBillingEmail: the organization billing email of the account
             * @param type:                     the type of the account
             **/
            public Account(String login, long id, String nodeId, String email, String url, String organizationBillingEmail,
                           String type) {
                super(null);
                this.login = login;
                this.id = id;
                this.nodeId = nodeId;
                this.email = email;
                this.url = url;
                this.organizationBillingEmail = organizationBillingEmail;
                this.type = type;
            }

            /**
             * Constructor to init a {@link Account}
             *
             * @param jAccount: account details as {@link JSONObject}
             **/
            public Account(JSONObject jAccount) {
                super(jAccount);
                login = hItem.getString("login");
                id = hItem.getLong("id", 0);
                nodeId = hItem.getString("node_id");
                url = hItem.getString("url");
                email = hItem.getString("email");
                organizationBillingEmail = hItem.getString("organization_billing_email");
                type = hItem.getString("type");
            }

            /**
             * Method to get {@link #login} instance <br>
             * Any params required
             *
             * @return {@link #login} instance as {@link String}
             **/
            public String getLogin() {
                return login;
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
             * Method to get {@link #nodeId} instance <br>
             * Any params required
             *
             * @return {@link #nodeId} instance as {@link String}
             **/
            public String getNodeId() {
                return nodeId;
            }

            /**
             * Method to get {@link #email} instance <br>
             * Any params required
             *
             * @return {@link #email} instance as {@link String}
             **/
            public String getEmail() {
                return email;
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

            /**
             * Method to get {@link #organizationBillingEmail} instance <br>
             * Any params required
             *
             * @return {@link #organizationBillingEmail} instance as {@link String}
             **/
            public String getOrganizationBillingEmail() {
                return organizationBillingEmail;
            }

            /**
             * Method to get {@link #type} instance <br>
             * Any params required
             *
             * @return {@link #type} instance as {@link String}
             **/
            public String getType() {
                return type;
            }

        }

    }

}
