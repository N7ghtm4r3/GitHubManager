package com.tecknobit.githubmanager.checks.suites.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import com.tecknobit.githubmanager.records.repository.CompleteRepository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@code CheckSuitesPreferences} class is useful to format a GitHub's check suites preferences
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/suites#update-repository-preferences-for-check-suites">
 * Update repository preferences for check suites</a>
 * @see GitHubResponse
 **/
public class CheckSuitesPreferences extends GitHubResponse {

    /**
     * {@code preferences} of the check suite
     **/
    private final SuitesPreferences preferences;

    /**
     * {@code repository} of the check suite
     **/
    private final CompleteRepository repository;

    /**
     * Constructor to init an {@link CheckSuitesPreferences}
     *
     * @param preferences : preferences of the check suite
     * @param repository: repository of the check suite
     **/
    public CheckSuitesPreferences(SuitesPreferences preferences, CompleteRepository repository) {
        super(null);
        this.preferences = preferences;
        this.repository = repository;
    }

    /**
     * Constructor to init an {@link CheckSuitesPreferences}
     *
     * @param jPreferences : preferences details as {@link JSONObject}
     **/
    public CheckSuitesPreferences(JSONObject jPreferences) {
        super(jPreferences);
        preferences = new SuitesPreferences(hResponse.getJSONObject("preferences", new JSONObject()));
        repository = new CompleteRepository(hResponse.getJSONObject("repository", new JSONObject()));
    }

    /**
     * Method to get {@link #preferences} instance <br>
     * No-any params required
     *
     * @return {@link #preferences} instance as {@link SuitesPreferences}
     **/
    public SuitesPreferences getPreferences() {
        return preferences;
    }

    /**
     * Method to get {@link #repository} instance <br>
     * No-any params required
     *
     * @return {@link #repository} instance as {@link CompleteRepository}
     **/
    public CompleteRepository getRepository() {
        return repository;
    }

    /**
     * The {@code SuitesPreferences} class is useful to format a GitHub's preferences for {@link CheckSuitesPreferences}
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class SuitesPreferences extends InnerClassItem {

        /**
         * {@code autoTriggerChecks} list of {@link AutoTriggerCheck}
         **/
        private final ArrayList<AutoTriggerCheck> autoTriggerChecks;

        /**
         * Constructor to init an {@link SuitesPreferences}
         *
         * @param autoTriggerChecks : list of {@link AutoTriggerCheck}
         **/
        public SuitesPreferences(ArrayList<AutoTriggerCheck> autoTriggerChecks) {
            super(null);
            this.autoTriggerChecks = autoTriggerChecks;
        }

        /**
         * Constructor to init an {@link SuitesPreferences}
         *
         * @param jPreferences : preferences details as {@link JSONObject}
         **/
        public SuitesPreferences(JSONObject jPreferences) {
            super(jPreferences);
            autoTriggerChecks = new ArrayList<>();
            JSONArray jAutoTriggerChecks = hItem.getJSONArray("auto_trigger_checks", new JSONArray());
            for (int j = 0; j < jAutoTriggerChecks.length(); j++)
                autoTriggerChecks.add(new AutoTriggerCheck(jAutoTriggerChecks.getJSONObject(j)));
        }

        /**
         * Method to get {@link #autoTriggerChecks} instance <br>
         * No-any params required
         *
         * @return {@link #autoTriggerChecks} instance as {@link Collection} of {@link AutoTriggerCheck}
         **/
        public Collection<AutoTriggerCheck> getAutoTriggerChecks() {
            return autoTriggerChecks;
        }

        /**
         * The {@code AutoTriggerCheck} class is useful to format a GitHub's auto trigger check for {@link SuitesPreferences}
         *
         * @author N7ghtm4r3 - Tecknobit
         * @see InnerClassItem
         **/
        public static class AutoTriggerCheck extends InnerClassItem {

            /**
             * {@code appId} app identifier
             **/
            private final long appId;

            /**
             * {@code setting} whether is setting
             **/
            private final boolean setting;

            public AutoTriggerCheck(long appId, boolean setting) {
                super(null);
                this.appId = appId;
                this.setting = setting;
            }

            /**
             * Constructor to init an {@link AutoTriggerCheck}
             *
             * @param jAutoTriggerCheck : auto trigger check details as {@link JSONObject}
             **/
            public AutoTriggerCheck(JSONObject jAutoTriggerCheck) {
                super(jAutoTriggerCheck);
                appId = hItem.getLong("app_id", 0);
                setting = hItem.getBoolean("setting");
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


            /**
             * Method to get {@link #setting} instance <br>
             * No-any params required
             *
             * @return {@link #setting} instance as boolean
             **/
            public boolean isSetting() {
                return setting;
            }

        }

    }

}
