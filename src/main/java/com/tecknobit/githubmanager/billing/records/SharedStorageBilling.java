package com.tecknobit.githubmanager.billing.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code SharedStorageBilling} class is useful to format a GitHub's shared storage billing
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/billing#get-shared-storage-billing-for-an-organization">
 *             Get shared storage billing for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/billing#get-shared-storage-billing-for-a-user">
 *             Get shared storage billing for a user</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class SharedStorageBilling extends GitHubResponse {

    /**
     * {@code daysLeftInBillingCycle} numbers of days left in billing cycle
     **/
    private final int daysLeftInBillingCycle;

    /**
     * {@code estimatedPaidStorageForMonth} estimated storage space (GB) used in billing cycle
     **/
    private final double estimatedPaidStorageForMonth;

    /**
     * {@code estimatedStorageForMonth} estimated sum of free and paid storage space (GB) used in billing cycle
     **/
    private final double estimatedStorageForMonth;

    /**
     * Constructor to init a {@link SharedStorageBilling}
     *
     * @param daysLeftInBillingCycle       : numbers of days left in billing cycle
     * @param estimatedPaidStorageForMonth : estimated storage space (GB) used in billing cycle
     * @param estimatedStorageForMonth     : estimated sum of free and paid storage space (GB) used in billing cycle
     **/
    public SharedStorageBilling(JSONObject jResponse, int daysLeftInBillingCycle, double estimatedPaidStorageForMonth,
                                double estimatedStorageForMonth) {
        super(jResponse);
        this.daysLeftInBillingCycle = daysLeftInBillingCycle;
        this.estimatedPaidStorageForMonth = estimatedPaidStorageForMonth;
        this.estimatedStorageForMonth = estimatedStorageForMonth;
    }

    /**
     * Constructor to init a {@link SharedStorageBilling}
     *
     * @param jSharedStorageBilling : shared storage billing details as {@link JSONObject}
     **/
    public SharedStorageBilling(JSONObject jSharedStorageBilling) {
        super(jSharedStorageBilling);
        daysLeftInBillingCycle = hResponse.getInt("days_left_in_billing_cycle", 0);
        estimatedPaidStorageForMonth = hResponse.getDouble("estimated_paid_storage_for_month", 0);
        estimatedStorageForMonth = hResponse.getDouble("estimated_storage_for_month", 0);
    }

    /**
     * Method to get {@link #daysLeftInBillingCycle} instance <br>
     * Any params required
     *
     * @return {@link #daysLeftInBillingCycle} instance as int
     **/
    public int getDaysLeftInBillingCycle() {
        return daysLeftInBillingCycle;
    }

    /**
     * Method to get {@link #estimatedPaidStorageForMonth} instance <br>
     * Any params required
     *
     * @return {@link #estimatedPaidStorageForMonth} instance as double
     **/
    public double getEstimatedPaidStorageForMonth() {
        return estimatedPaidStorageForMonth;
    }

    /**
     * Method to get {@link #estimatedPaidStorageForMonth} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #estimatedPaidStorageForMonth} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getEstimatedPaidStorageForMonth(int decimals) {
        return roundValue(estimatedPaidStorageForMonth, decimals);
    }

    /**
     * Method to get {@link #estimatedStorageForMonth} instance <br>
     * Any params required
     *
     * @return {@link #estimatedStorageForMonth} instance as double
     **/
    public double getEstimatedStorageForMonth() {
        return estimatedStorageForMonth;
    }

    /**
     * Method to get {@link #estimatedStorageForMonth} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #estimatedStorageForMonth} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getEstimatedStorageForMonth(int decimals) {
        return roundValue(estimatedStorageForMonth, decimals);
    }

}
