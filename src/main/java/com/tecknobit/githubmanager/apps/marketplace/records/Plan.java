package com.tecknobit.githubmanager.apps.marketplace.records;

import com.tecknobit.githubmanager.records.basics.BaseResponseDetails;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class Plan extends BaseResponseDetails {

    private final String accountsUrl;
    private final int number;
    private final String description;
    private final double monthlyPriceInCents;
    private final double yearlyPriceInCents;
    private final PriceModel priceModel;
    private final boolean hasFreeTrial;
    private final String state;
    private final ArrayList<String> bullets;
    /**
     * Constructor to init a {@link BaseResponseDetails}
     *
     * @param id   : identifier value
     * @param name : the name of the item
     * @param url  : url value
     **/
    public Plan(long id, String name, String url, String accountsUrl, int number, String description,
                double monthlyPriceInCents, double yearlyPriceInCents, PriceModel priceModel, boolean hasFreeTrial,
                String state, ArrayList<String> bullets) {
        super(id, name, url);
        this.accountsUrl = accountsUrl;
        this.number = number;
        this.description = description;
        this.monthlyPriceInCents = monthlyPriceInCents;
        this.yearlyPriceInCents = yearlyPriceInCents;
        this.priceModel = priceModel;
        this.hasFreeTrial = hasFreeTrial;
        this.state = state;
        this.bullets = bullets;
    }

    /**
     * Constructor to init a {@link Plan}
     *
     * @param jPlan : response by {@code "GitHub"} as {@link JSONObject}
     **/
    public Plan(JSONObject jPlan) {
        super(jPlan);
        accountsUrl = hResponse.getString("accounts_url");
        number = hResponse.getInt("number");
        description = hResponse.getString("description");
        monthlyPriceInCents = hResponse.getDouble("monthly_price_in_cents");
        yearlyPriceInCents = hResponse.getDouble("yearly_price_in_cents");
        priceModel = PriceModel.valueOf(hResponse.getString("price_model"));
        hasFreeTrial = hResponse.getBoolean("has_free_trial");
        state = hResponse.getString("state");
        bullets = returnStringList(hResponse.getJSONArray("bullets"));
    }

    public String getAccountsUrl() {
        return accountsUrl;
    }

    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public double getMonthlyPriceInCents() {
        return monthlyPriceInCents;
    }

    public double getYearlyPriceInCents() {
        return yearlyPriceInCents;
    }

    public PriceModel getPriceModel() {
        return priceModel;
    }

    public boolean isHasFreeTrial() {
        return hasFreeTrial;
    }

    public String getState() {
        return state;
    }

    public Collection<String> getBullets() {
        return bullets;
    }

    public enum PriceModel {

        FREE,
        FLAT_RATE,
        PER_UNIT

    }

}
