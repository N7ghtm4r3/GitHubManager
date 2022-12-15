package com.tecknobit.githubmanager.billing.records;

import com.tecknobit.githubmanager.records.basics.GitHubResponse;
import org.json.JSONObject;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code PackagesBilling} class is useful to format a GitHub's packages billing
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/billing#get-github-packages-billing-for-an-organization">
 *             Get GitHub Packages billing for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/billing#get-github-packages-billing-for-a-user">
 *             Get GitHub Packages billing for a user</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class PackagesBilling extends GitHubResponse {

    /**
     * {@code totalGigabytesBandwidthUsed} sum of the free and paid storage space (GB) for GitHuub Packages
     **/
    private final double totalGigabytesBandwidthUsed;

    /**
     * {@code totalPaidGigabytesBandwidthUsed} total paid storage space (GB) for GitHub Packages
     **/
    private final double totalPaidGigabytesBandwidthUsed;

    /**
     * {@code totalGigabytesBandwidthUsed} free storage space (GB) for GitHub Packages
     **/
    private final double includedGigabytesBandwidth;

    /**
     * Constructor to init a {@link PackagesBilling}
     *
     * @param totalGigabytesBandwidthUsed     : sum of the free and paid storage space (GB) for GitHuub Packages
     * @param totalPaidGigabytesBandwidthUsed : total paid storage space (GB) for GitHub Packages
     * @param includedGigabytesBandwidth      : free storage space (GB) for GitHub Packages
     **/
    public PackagesBilling(double totalGigabytesBandwidthUsed, double totalPaidGigabytesBandwidthUsed,
                           double includedGigabytesBandwidth) {
        super(null);
        this.totalGigabytesBandwidthUsed = totalGigabytesBandwidthUsed;
        this.totalPaidGigabytesBandwidthUsed = totalPaidGigabytesBandwidthUsed;
        this.includedGigabytesBandwidth = includedGigabytesBandwidth;
    }

    /**
     * Constructor to init a {@link PackagesBilling}
     *
     * @param jPackagesBilling : packages billing details as {@link JSONObject}
     **/
    public PackagesBilling(JSONObject jPackagesBilling) {
        super(jPackagesBilling);
        totalGigabytesBandwidthUsed = hResponse.getDouble("total_gigabytes_bandwidth_used", 0);
        totalPaidGigabytesBandwidthUsed = hResponse.getDouble("total_paid_gigabytes_bandwidth_used", 0);
        includedGigabytesBandwidth = hResponse.getDouble("included_gigabytes_bandwidth", 0);
    }

    /**
     * Method to get {@link #totalGigabytesBandwidthUsed} instance <br>
     * Any params required
     *
     * @return {@link #totalGigabytesBandwidthUsed} instance as double
     **/
    public double getTotalGigabytesBandwidthUsed() {
        return totalGigabytesBandwidthUsed;
    }

    /**
     * Method to get {@link #totalGigabytesBandwidthUsed} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #totalGigabytesBandwidthUsed} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getTotalGigabytesBandwidthUsed(int decimals) {
        return roundValue(totalGigabytesBandwidthUsed, decimals);
    }

    /**
     * Method to get {@link #totalPaidGigabytesBandwidthUsed} instance <br>
     * Any params required
     *
     * @return {@link #totalPaidGigabytesBandwidthUsed} instance as double
     **/
    public double getTotalPaidGigabytesBandwidthUsed() {
        return totalPaidGigabytesBandwidthUsed;
    }

    /**
     * Method to get {@link #totalPaidGigabytesBandwidthUsed} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #totalPaidGigabytesBandwidthUsed} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getTotalPaidGigabytesBandwidthUsed(int decimals) {
        return roundValue(totalPaidGigabytesBandwidthUsed, decimals);
    }

    /**
     * Method to get {@link #includedGigabytesBandwidth} instance <br>
     * Any params required
     *
     * @return {@link #includedGigabytesBandwidth} instance as double
     **/
    public double getIncludedGigabytesBandwidth() {
        return includedGigabytesBandwidth;
    }

    /**
     * Method to get {@link #includedGigabytesBandwidth} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #includedGigabytesBandwidth} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getIncludedGigabytesBandwidth(int decimals) {
        return roundValue(includedGigabytesBandwidth, decimals);
    }

}
