package com.tecknobit.githubmanager.billing.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.records.basics.GitHubResponse;
import org.json.JSONObject;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code ActionsBilling} class is useful to format a GitHub's actions billing
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/billing#get-github-actions-billing-for-an-organization">
 *             Get GitHub Actions billing for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/billing#get-github-actions-billing-for-a-user">
 *             Get GitHub Actions billing for a user</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class ActionsBilling extends GitHubResponse {

    /**
     * {@code totalMinutesUsed} the sum of the free and paid GitHub Actions minutes used
     **/
    private final int totalMinutesUsed;

    /**
     * {@code totalPaidMinutesUsed} the total paid GitHub Actions minutes used
     **/
    private final double totalPaidMinutesUsed;

    /**
     * {@code includedMinutes} the amount of free GitHub Actions minutes available
     **/
    private final int includedMinutes;

    /**
     * {@code minutesUsedBreakdown} the minutes used breakdown for actions billing
     **/
    private final MinutesUsedBreakdown minutesUsedBreakdown;

    /**
     * Constructor to init a {@link ActionsBilling}
     *
     * @param totalMinutesUsed     : the sum of the free and paid GitHub Actions minutes used
     * @param totalPaidMinutesUsed : the total paid GitHub Actions minutes used
     * @param includedMinutes      : the amount of free GitHub Actions minutes available
     * @param minutesUsedBreakdown : the minutes used breakdown for actions billing
     **/
    public ActionsBilling(int totalMinutesUsed, double totalPaidMinutesUsed, int includedMinutes,
                          MinutesUsedBreakdown minutesUsedBreakdown) {
        super(null);
        this.totalMinutesUsed = totalMinutesUsed;
        this.totalPaidMinutesUsed = totalPaidMinutesUsed;
        this.includedMinutes = includedMinutes;
        this.minutesUsedBreakdown = minutesUsedBreakdown;
    }

    /**
     * Constructor to init a {@link ActionsBilling}
     *
     * @param jActionsBilling : actions billing details as {@link JSONObject}
     **/
    public ActionsBilling(JSONObject jActionsBilling) {
        super(jActionsBilling);
        totalMinutesUsed = hResponse.getInt("total_minutes_used", 0);
        totalPaidMinutesUsed = hResponse.getDouble("total_paid_minutes_used", 0);
        includedMinutes = hResponse.getInt("included_minutes", 0);
        minutesUsedBreakdown = new MinutesUsedBreakdown(hResponse.getJSONObject("minutes_used_breakdown",
                new JSONObject()));
    }

    /**
     * Method to get {@link #totalMinutesUsed} instance <br>
     * Any params required
     *
     * @return {@link #totalMinutesUsed} instance as int
     **/
    public int getTotalMinutesUsed() {
        return totalMinutesUsed;
    }

    /**
     * Method to get {@link #totalPaidMinutesUsed} instance <br>
     * Any params required
     *
     * @return {@link #totalPaidMinutesUsed} instance as double
     **/
    public double getTotalPaidMinutesUsed() {
        return totalPaidMinutesUsed;
    }

    /**
     * Method to get {@link #totalPaidMinutesUsed} instance
     *
     * @param decimals: number of digits to round final value
     * @return {@link #totalPaidMinutesUsed} instance rounded with decimal digits inserted
     * @throws IllegalArgumentException if decimalDigits is negative
     **/
    public double getTotalPaidMinutesUsed(int decimals) {
        return roundValue(totalPaidMinutesUsed, decimals);
    }

    /**
     * Method to get {@link #includedMinutes} instance <br>
     * Any params required
     *
     * @return {@link #includedMinutes} instance as int
     **/
    public int getIncludedMinutes() {
        return includedMinutes;
    }

    /**
     * Method to get {@link #minutesUsedBreakdown} instance <br>
     * Any params required
     *
     * @return {@link #minutesUsedBreakdown} instance as {@link MinutesUsedBreakdown}
     **/
    public MinutesUsedBreakdown getMinutesUsedBreakdown() {
        return minutesUsedBreakdown;
    }

    /**
     * The {@code MinutesUsedBreakdown} class is useful to format a GitHub's minutes used breakdown for actions billing
     *
     * @author N7ghtm4r3 - Tecknobit
     **/
    public static class MinutesUsedBreakdown {

        /**
         * {@code UBUNTU} total minutes used on Ubuntu runner machines
         **/
        private final int UBUNTU;

        /**
         * {@code includedMinutes} total minutes used on macOS runner machines
         **/
        private final int MACOS;

        /**
         * {@code includedMinutes} total minutes used on Windows runner machines
         **/
        private final int WINDOWS;

        /**
         * {@code ubuntu4Core} total minutes used on Ubuntu 4 core runner machines
         **/
        private final int ubuntu4Core;

        /**
         * {@code ubuntu8Core} total minutes used on Ubuntu 8 core runner machines
         **/
        private final int ubuntu8Core;

        /**
         * {@code ubuntu16Core} total minutes used on Ubuntu 16 core runner machines
         **/
        private final int ubuntu16Core;

        /**
         * {@code ubuntu32Core} total minutes used on Ubuntu 32 core runner machines
         **/
        private final int ubuntu32Core;

        /**
         * {@code ubuntu64Core} total minutes used on Ubuntu 64 core runner machines
         **/
        private final int ubuntu64Core;

        /**
         * {@code windows4Core} total minutes used on Windows 4 core runner machines
         **/
        private final int windows4Core;

        /**
         * {@code windows8Core} total minutes used on Windows 8 core runner machines
         **/
        private final int windows8Core;

        /**
         * {@code windows16Core} total minutes used on Windows 16 core runner machines
         **/
        private final int windows16Core;

        /**
         * {@code windows32Core} total minutes used on Windows 32 core runner machines
         **/
        private final int windows32Core;

        /**
         * {@code windows64Core} total minutes used on Windows 64 core runner machines
         **/
        private final int windows64Core;

        /**
         * {@code total} total minutes used on all runner machines
         **/
        private final int total;

        /**
         * Constructor to init a {@link MinutesUsedBreakdown}
         *
         * @param UBUNTU        : total minutes used on Ubuntu runner machines
         * @param MACOS         : total minutes used on macOS runner machines
         * @param WINDOWS       : total minutes used on Windows runner machines
         * @param ubuntu4Core   :total minutes used on Ubuntu 4 core runner machines
         * @param ubuntu8Core   : total minutes used on Ubuntu 8 core runner machines
         * @param ubuntu16Core  : total minutes used on Ubuntu 16 core runner machines
         * @param ubuntu32Core  : total minutes used on Ubuntu 32 core runner machines
         * @param ubuntu64Core  : total minutes used on Ubuntu 64 core runner machines
         * @param windows4Core  : total minutes used on Windows 4 core runner machines
         * @param windows8Core  : total minutes used on Windows 8 core runner machines
         * @param windows16Core : total minutes used on Windows 16 core runner machines
         * @param windows32Core :total minutes used on Windows 32 core runner machines
         * @param windows64Core : total minutes used on Windows 64 core runner machines
         * @param total         : total minutes used on all runner machines
         **/
        public MinutesUsedBreakdown(int UBUNTU, int MACOS, int WINDOWS, int ubuntu4Core, int ubuntu8Core, int ubuntu16Core,
                                    int ubuntu32Core, int ubuntu64Core, int windows4Core, int windows8Core, int windows16Core,
                                    int windows32Core, int windows64Core, int total) {
            this.UBUNTU = UBUNTU;
            this.MACOS = MACOS;
            this.WINDOWS = WINDOWS;
            this.ubuntu4Core = ubuntu4Core;
            this.ubuntu8Core = ubuntu8Core;
            this.ubuntu16Core = ubuntu16Core;
            this.ubuntu32Core = ubuntu32Core;
            this.ubuntu64Core = ubuntu64Core;
            this.windows4Core = windows4Core;
            this.windows8Core = windows8Core;
            this.windows16Core = windows16Core;
            this.windows32Core = windows32Core;
            this.windows64Core = windows64Core;
            this.total = total;
        }

        /**
         * Constructor to init a {@link MinutesUsedBreakdown}
         *
         * @param jBreakdown : minutes used breakdown details as {@link JSONObject}
         **/
        public MinutesUsedBreakdown(JSONObject jBreakdown) {
            JsonHelper hBreakdown = new JsonHelper(jBreakdown);
            UBUNTU = hBreakdown.getInt("UBUNTU", 0);
            MACOS = hBreakdown.getInt("MACOS", 0);
            WINDOWS = hBreakdown.getInt("WINDOWS", 0);
            ubuntu4Core = hBreakdown.getInt("ubuntu_4_core", 0);
            ubuntu8Core = hBreakdown.getInt("ubuntu_8_core", 0);
            ubuntu16Core = hBreakdown.getInt("ubuntu_16_core", 0);
            ubuntu32Core = hBreakdown.getInt("ubuntu_32_core", 0);
            ubuntu64Core = hBreakdown.getInt("ubuntu_64_core", 0);
            windows4Core = hBreakdown.getInt("windows_4_core", 0);
            windows8Core = hBreakdown.getInt("windows_8_core", 0);
            windows16Core = hBreakdown.getInt("windows_16_core", 0);
            windows32Core = hBreakdown.getInt("windows_32_core", 0);
            windows64Core = hBreakdown.getInt("windows_64_core", 0);
            total = hBreakdown.getInt("total", 0);
        }

        /**
         * Method to get {@link #UBUNTU} instance <br>
         * Any params required
         *
         * @return {@link #UBUNTU} instance as int
         **/
        public int getUBUNTU() {
            return UBUNTU;
        }

        /**
         * Method to get {@link #MACOS} instance <br>
         * Any params required
         *
         * @return {@link #MACOS} instance as int
         **/
        public int getMACOS() {
            return MACOS;
        }

        /**
         * Method to get {@link #WINDOWS} instance <br>
         * Any params required
         *
         * @return {@link #WINDOWS} instance as int
         **/
        public int getWINDOWS() {
            return WINDOWS;
        }

        /**
         * Method to get {@link #ubuntu4Core} instance <br>
         * Any params required
         *
         * @return {@link #ubuntu4Core} instance as int
         **/
        public int getUbuntu4Core() {
            return ubuntu4Core;
        }

        /**
         * Method to get {@link #ubuntu8Core} instance <br>
         * Any params required
         *
         * @return {@link #ubuntu8Core} instance as int
         **/
        public int getUbuntu8Core() {
            return ubuntu8Core;
        }

        /**
         * Method to get {@link #ubuntu16Core} instance <br>
         * Any params required
         *
         * @return {@link #ubuntu16Core} instance as int
         **/
        public int getUbuntu16Core() {
            return ubuntu16Core;
        }

        /**
         * Method to get {@link #ubuntu32Core} instance <br>
         * Any params required
         *
         * @return {@link #ubuntu32Core} instance as int
         **/
        public int getUbuntu32Core() {
            return ubuntu32Core;
        }

        /**
         * Method to get {@link #ubuntu64Core} instance <br>
         * Any params required
         *
         * @return {@link #ubuntu64Core} instance as int
         **/
        public int getUbuntu64Core() {
            return ubuntu64Core;
        }

        /**
         * Method to get {@link #windows4Core} instance <br>
         * Any params required
         *
         * @return {@link #windows4Core} instance as int
         **/
        public int getWindows4Core() {
            return windows4Core;
        }

        /**
         * Method to get {@link #windows8Core} instance <br>
         * Any params required
         *
         * @return {@link #windows8Core} instance as int
         **/
        public int getWindows8Core() {
            return windows8Core;
        }

        /**
         * Method to get {@link #windows16Core} instance <br>
         * Any params required
         *
         * @return {@link #windows16Core} instance as int
         **/
        public int getWindows16Core() {
            return windows16Core;
        }

        /**
         * Method to get {@link #windows32Core} instance <br>
         * Any params required
         *
         * @return {@link #windows32Core} instance as int
         **/
        public int getWindows32Core() {
            return windows32Core;
        }

        /**
         * Method to get {@link #windows64Core} instance <br>
         * Any params required
         *
         * @return {@link #windows64Core} instance as int
         **/
        public int getWindows64Core() {
            return windows64Core;
        }

        /**
         * Method to get {@link #total} instance <br>
         * Any params required
         *
         * @return {@link #total} instance as int
         **/
        public int getTotal() {
            return total;
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
