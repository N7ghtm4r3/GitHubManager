package com.tecknobit.githubmanager.actions.secrets.records.secrets;

import com.tecknobit.githubmanager.records.GitHubResponse;
import org.json.JSONObject;

import java.text.ParseException;

import static com.tecknobit.githubmanager.actions.artifacts.records.Artifact.dateFormatter;

public class Secret extends GitHubResponse {

    protected final String name;
    protected final String createdAt;
    protected final String updatedAt;

    public Secret(String name, String createdAt, String updatedAt) {
        super(null);
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Secret(JSONObject jSecret) {
        super(jSecret);
        name = hResponse.getString("name");
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
    }

    public String getName() {
        return name;
    }

    /**
     * Method to get {@link #createdAt} instance <br>
     * Any params required
     *
     * @return {@link #createdAt} instance as {@link String}
     **/
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Method to get {@link #createdAt} timestamp <br>
     * Any params required
     *
     * @return {@link #createdAt} timestamp as long
     **/
    public long getCreatedAtTimestamp() {
        try {
            return dateFormatter.parse(createdAt).getTime();
        } catch (ParseException e) {
            return -1;
        }
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
        try {
            return dateFormatter.parse(updatedAt).getTime();
        } catch (ParseException e) {
            return -1;
        }
    }

    /**
     * {@code SecretVisibility} list of available visibilities for a secret
     **/
    public enum SecretVisibility {

        /**
         * {@code "public"} visibility
         **/
        all("all"),

        /**
         * {@code "private"} visibility
         **/
        vPrivate("private"),

        /**
         * {@code "selected"} visibility
         **/
        selected("selected");

        /**
         * {@code "visibility"} value
         **/
        private final String visibility;

        /**
         * Constructor to init a {@link SecretVisibility}
         *
         * @param visibility : {@code "visibility"} value
         **/
        SecretVisibility(String visibility) {
            this.visibility = visibility;
        }

        /**
         * Method to get {@link #visibility} instance <br>
         * Any params required
         *
         * @return {@link #visibility} instance as {@link String}
         **/
        @Override
        public String toString() {
            return visibility;
        }

    }

}
