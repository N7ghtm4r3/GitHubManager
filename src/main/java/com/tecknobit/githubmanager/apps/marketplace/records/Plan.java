package com.tecknobit.githubmanager.apps.marketplace.records;

import com.tecknobit.githubmanager.records.basics.BaseResponseDetails;
import com.tecknobit.githubmanager.records.basics.GitHubResponse;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code Plan} class is useful to format a GitHub's plan
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/apps/marketplace#list-plans">
 * List plans</a>
 * @see GitHubResponse
 * @see BaseResponseDetails
 **/
public class Plan extends BaseResponseDetails {

    /**
     * {@code accountsUrl} the accounts url of the plan
     **/
    private final String accountsUrl;

    /**
     * {@code number} the number of the plan
     **/
    private final int number;

    /**
     * {@code description} the description of the plan
     **/
    private final String description;

    /**
     * {@code monthlyPriceInCents} the monthly price in cents of the plan
     **/
    private final double monthlyPriceInCents;

    /**
     * {@code yearlyPriceInCents} the yearly price in cents of the plan
     **/
    private final double yearlyPriceInCents;

    /**
     * {@code priceModel} the price model of the plan
     **/
    private final PriceModel priceModel;

    /**
     * {@code hasFreeTrial} whether the plan has a free trial
     **/
    private final boolean hasFreeTrial;

    /**
     * {@code unitName} the unit name of the plan
     **/
    private final String unitName;

    /**
     * {@code state} the state of the plan
     **/
    private final String state;

    /**
     * {@code bullets} the bullets of the plan
     **/
    private final ArrayList<String> bullets;

    /**
     * Constructor to init a {@link Plan}
     *
     * @param id       : the id of the plan
     * @param name     : the name of the plan
     * @param url      : the url of the plan
     * @param accountsUrl       : the accounts url of the plan
     * @param number     : the number of the plan
     * @param description      : the description of the plan
     * @param monthlyPriceInCents       : the monthly price in cents of the plan
     * @param yearlyPriceInCents     : the yearly price in cents of the plan
     * @param priceModel      : the price model of the plan
     * @param hasFreeTrial       : whether the plan has a free trial
     * @param unitName     : the unit name of the plan
     * @param state      : the state of the plan
     * @param bullets      : the bullets of the plan
     **/
    public Plan(long id, String name, String url, String accountsUrl, int number, String description,
                double monthlyPriceInCents, double yearlyPriceInCents, PriceModel priceModel, boolean hasFreeTrial,
                String unitName, String state, ArrayList<String> bullets) {
        super(id, name, url);
        this.accountsUrl = accountsUrl;
        this.number = number;
        this.description = description;
        this.monthlyPriceInCents = monthlyPriceInCents;
        this.yearlyPriceInCents = yearlyPriceInCents;
        this.priceModel = priceModel;
        this.hasFreeTrial = hasFreeTrial;
        this.unitName = unitName;
        this.state = state;
        this.bullets = bullets;
    }

    /**
     * Constructor to init a {@link Plan}
     *
     * @param jPlan : plan details as {@link JSONObject}
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
        unitName = hResponse.getString("unit_name");
        state = hResponse.getString("state");
        bullets = returnStringList(hResponse.getJSONArray("bullets"));
    }

    /**
     * Method to get {@link #accountsUrl} instance <br>
     * Any params required
     *
     * @return {@link #accountsUrl} instance as {@link String}
     **/
    public String getAccountsUrl() {
        return accountsUrl;
    }

    /**
     * Method to get {@link #number} instance <br>
     * Any params required
     *
     * @return {@link #number} instance as int
     **/
    public int getNumber() {
        return number;
    }

    /**
     * Method to get {@link #description} instance <br>
     * Any params required
     *
     * @return {@link #description} instance as {@link String}
     **/
    public String getDescription() {
        return description;
    }

    /**
     * Method to get {@link #monthlyPriceInCents} instance <br>
     * Any params required
     *
     * @return {@link #monthlyPriceInCents} instance as double
     **/
    public double getMonthlyPriceInCents() {
        return monthlyPriceInCents;
    }

    /**
     * Method to get {@link #monthlyPriceInCents} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #monthlyPriceInCents} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getMonthlyPriceInCents(int decimals) {
        return roundValue(monthlyPriceInCents, decimals);
    }

    /**
     * Method to get {@link #yearlyPriceInCents} instance <br>
     * Any params required
     *
     * @return {@link #yearlyPriceInCents} instance as double
     **/
    public double getYearlyPriceInCents() {
        return yearlyPriceInCents;
    }

    /**
     * Method to get {@link #yearlyPriceInCents} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #yearlyPriceInCents} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getYearlyPriceInCents(int decimals) {
        return roundValue(yearlyPriceInCents, decimals);
    }

    /**
     * Method to get {@link #priceModel} instance <br>
     * Any params required
     *
     * @return {@link #priceModel} instance as {@link PriceModel}
     **/
    public PriceModel getPriceModel() {
        return priceModel;
    }

    /**
     * Method to get {@link #hasFreeTrial} instance <br>
     * Any params required
     *
     * @return {@link #hasFreeTrial} instance as boolean
     **/
    public boolean isHasFreeTrial() {
        return hasFreeTrial;
    }

    /**
     * Method to get {@link #unitName} instance <br>
     * Any params required
     *
     * @return {@link #unitName} instance as {@link String}
     **/
    public String getUnitName() {
        return unitName;
    }

    /**
     * Method to get {@link #state} instance <br>
     * Any params required
     *
     * @return {@link #state} instance as {@link String}
     **/
    public String getState() {
        return state;
    }

    /**
     * Method to get {@link #bullets} instance <br>
     * Any params required
     *
     * @return {@link #bullets} instance as {@link Collection} of {@link String}
     **/
    public Collection<String> getBullets() {
        return bullets;
    }

    /**
     * {@code PriceModel} list of available price model types
     **/
    public enum PriceModel {

        /**
         * {@code "FREE"} price model type
         **/
        FREE,

        /**
         * {@code "FLAT_RATE"} price model type
         **/
        FLAT_RATE,

        /**
         * {@code "PER_UNIT"} price model type
         **/
        PER_UNIT

    }

}
