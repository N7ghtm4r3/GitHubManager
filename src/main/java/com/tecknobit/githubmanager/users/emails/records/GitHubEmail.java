package com.tecknobit.githubmanager.users.emails.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code GitHubEmail} class is useful to format a GitHub's email
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/users/emails#set-primary-email-visibility-for-the-authenticated-user">
 *             Set primary email visibility for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/users/emails#list-email-addresses-for-the-authenticated-user">
 *             List email addresses for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/users/emails#add-an-email-address-for-the-authenticated-user">
 *             Add an email address for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/users/emails#list-public-email-addresses-for-the-authenticated-user">
 *             List public email addresses for the authenticated user</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class GitHubEmail extends GitHubResponse {

    /**
     * {@code EmailVisibility} list of available visibilities for an email
     **/
    public enum EmailVisibility {

        /**
         * {@code "public"} visibility
         **/
        vPublic("public"),

        /**
         * {@code "private"} visibility
         **/
        vPrivate("private");

        /**
         * {@code "visibility"} value
         **/
        private final String visibility;

        /**
         * Constructor to init a {@link EmailVisibility}
         *
         * @param visibility : {@code "visibility"} value
         **/
        EmailVisibility(String visibility) {
            this.visibility = visibility;
        }

        /**
         * Method to reach a {@link EmailVisibility} value
         *
         * @param target: target of the {@link EmailVisibility} to reach
         * @return visibility as {@link EmailVisibility} or null if it not exists
         **/
        public static EmailVisibility reachEnumConstant(String target) {
            for (EmailVisibility visibility : EmailVisibility.values())
                if (visibility.toString().equals(target))
                    return visibility;
            return null;
        }

        /**
         * Method to get {@link #visibility} instance <br>
         * No-any params required
         *
         * @return {@link #visibility} instance as {@link String}
         **/
        @Override
        public String toString() {
            return visibility;
        }

    }

    /**
     * {@code email} value
     **/
    private final String email;

    /**
     * {@code primary} whether the email is primary
     **/
    private final boolean primary;

    /**
     * {@code verified} whether the email is verified
     **/
    private final boolean verified;

    /**
     * {@code visibility} of the mail
     **/
    private final EmailVisibility visibility;

    /**
     * Constructor to init a {@link GitHubEmail}
     *
     * @param email      : email value
     * @param primary    : whether the email is primary
     * @param verified   : whether the email is verified
     * @param visibility : visibility of the mail
     **/
    public GitHubEmail(String email, boolean primary, boolean verified, EmailVisibility visibility) {
        super(null);
        this.email = email;
        this.primary = primary;
        this.verified = verified;
        this.visibility = visibility;
    }

    /**
     * Constructor to init a {@link GitHubEmail}
     *
     * @param jGitHubEmail : email details as {@link JSONObject}
     **/
    public GitHubEmail(JSONObject jGitHubEmail) {
        super(jGitHubEmail);
        email = hResponse.getString("email");
        primary = hResponse.getBoolean("primary");
        verified = hResponse.getBoolean("verified");
        String sVisibility = hResponse.getString("visibility");
        if (sVisibility != null)
            visibility = EmailVisibility.reachEnumConstant(sVisibility);
        else
            visibility = null;
    }

    /**
     * Method to get {@link #email} instance <br>
     * No-any params required
     *
     * @return {@link #email} instance as {@link String}
     **/
    public String getEmail() {
        return email;
    }

    /**
     * Method to get {@link #primary} instance <br>
     * No-any params required
     *
     * @return {@link #primary} instance as boolean
     **/
    public boolean isPrimary() {
        return primary;
    }

    /**
     * Method to get {@link #verified} instance <br>
     * No-any params required
     *
     * @return {@link #verified} instance as boolean
     **/
    public boolean isVerified() {
        return verified;
    }

    /**
     * Method to get {@link #visibility} instance <br>
     * No-any params required
     *
     * @return {@link #visibility} instance as {@link EmailVisibility}
     **/
    public EmailVisibility getVisibility() {
        return visibility;
    }

}
