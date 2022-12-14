package com.tecknobit.githubmanager.apps.marketplace.records;

import com.tecknobit.githubmanager.records.basics.GitHubResponse;
import org.json.JSONObject;

public class SubscriptionPlan extends GitHubResponse {

    private final String url;
    private final String type;
    private final long id;
    private final String login;
    private final String organizationBillingEmail;
    private final String email;
    private final MarketplacePendingChange marketplacePendingChange;
    private final MarketPlacePurchase marketPlacePurchase;

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
     * @param jSubscriptionPlan : response by {@code "GitHub"} as {@link JSONObject}
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

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getOrganizationBillingEmail() {
        return organizationBillingEmail;
    }

    public String getEmail() {
        return email;
    }

    public MarketplacePendingChange getMarketplacePendingChange() {
        return marketplacePendingChange;
    }

    public MarketPlacePurchase getMarketPlacePurchase() {
        return marketPlacePurchase;
    }

    public static class MarketplacePendingChange {

        private final String effectiveDate;
        private final long id;
        private final Plan plan;

        public MarketplacePendingChange(String effectiveDate, long id, Plan plan) {
            this.effectiveDate = effectiveDate;
            this.id = id;
            this.plan = plan;
        }

        public MarketplacePendingChange(JSONObject jPendingChange) {
            this(jPendingChange.getString("effective_date"), jPendingChange.getLong("id"),
                    new Plan(jPendingChange.getJSONObject("plan")));
        }

        public String getEffectiveDate() {
            return effectiveDate;
        }

        public long getId() {
            return id;
        }

        public Plan getPlan() {
            return plan;
        }

        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

    }

    public static class MarketPlacePurchase {

        private final String billingCycle;
        private final String nextBillingDate;
        private final boolean onFreeTrial;
        private final String freeTrialEndsOn;
        private final String updatedAt;
        private final Plan plan;

        public MarketPlacePurchase(String billingCycle, String nextBillingDate, boolean onFreeTrial, String freeTrialEndsOn,
                                   String updatedAt, Plan plan) {
            this.billingCycle = billingCycle;
            this.nextBillingDate = nextBillingDate;
            this.onFreeTrial = onFreeTrial;
            this.freeTrialEndsOn = freeTrialEndsOn;
            this.updatedAt = updatedAt;
            this.plan = plan;
        }

        public MarketPlacePurchase(JSONObject jPlacePurchase) {
            this(jPlacePurchase.getString("billing_cycle"), jPlacePurchase.getString("next_billing_date"),
                    jPlacePurchase.getBoolean("on_free_trial"), jPlacePurchase.getString("free_trial_ends_on"),
                    jPlacePurchase.getString("updated_at"), new Plan(jPlacePurchase.getJSONObject("plan")));
        }

        public String getBillingCycle() {
            return billingCycle;
        }

        public String getNextBillingDate() {
            return nextBillingDate;
        }

        public boolean isOnFreeTrial() {
            return onFreeTrial;
        }

        public String getFreeTrialEndsOn() {
            return freeTrialEndsOn;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public Plan getPlan() {
            return plan;
        }

        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

    }

}
