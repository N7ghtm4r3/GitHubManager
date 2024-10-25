package com.tecknobit.githubmanager.actions.secrets.records;

import com.goterl.lazysodium.LazySodiumJava;
import com.goterl.lazysodium.SodiumJava;
import com.goterl.lazysodium.exceptions.SodiumException;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.GitHubManager.Params;
import com.tecknobit.githubmanager.GitHubManager.Visibility;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

import static com.goterl.lazysodium.utils.Key.fromBase64String;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat;
import static com.tecknobit.githubmanager.GitHubManager.Visibility.vPrivate;
import static com.tecknobit.githubmanager.GitHubManager.Visibility.valueOf;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Base64.getEncoder;

/**
 * The {@code Secret} class is useful to format a GitHub's organization secret
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/secrets#get-a-repository-secret">
 *             Get a repository secret</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/secrets#delete-a-repository-secret">
 *             Delete a repository secret</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/secrets#get-an-environment-secret">
 *             Get an environment secret</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/secrets#delete-an-environment-secret">
 *             Delete an environment secret</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/secrets#get-an-organization-secret">
 *             Get an organization secret</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/secrets#delete-an-organization-secret">
 *             Delete an organization secret</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/codespaces/organization-secrets#get-an-organization-secret">
 *             Get an organization secret</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/codespaces/repository-secrets#get-a-repository-secret">
 *             Get a repository secret</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/codespaces/secrets#get-a-secret-for-the-authenticated-user">
 *             Get a secret for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/dependabot/secrets#get-an-organization-secret">
 *             Get an organization secret</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/dependabot/secrets#get-a-repository-secret">
 *             Get a repository secret</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class Secret extends GitHubResponse {

    /**
     * {@code name} the name of the secret
     **/
    private final String name;

    /**
     * {@code createdAt} created at value
     **/
    private final String createdAt;

    /**
     * {@code updatedAt} updated at value
     **/
    private final String updatedAt;

    /**
     * {@code visibility} visibility of a secret
     **/
    private final Visibility visibility;

    /**
     * {@code selectedRepositoriesUrl} selected repositories url value
     **/
    private final String selectedRepositoriesUrl;

    /**
     * Constructor to init a {@link Secret}
     *
     * @param name:                    the name of the secret
     * @param createdAt:               created at value
     * @param updatedAt:               updated at value
     * @param visibility:              visibility of a secret
     * @param selectedRepositoriesUrl: selected repositories url value
     **/
    public Secret(String name, String createdAt, String updatedAt, Visibility visibility,
                  String selectedRepositoriesUrl) {
        super(null);
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.visibility = visibility;
        this.selectedRepositoriesUrl = selectedRepositoriesUrl;
    }

    /**
     * Constructor to init a {@link Secret}
     *
     * @param jOrganizationSecret: organization secret details as {@link JSONObject}
     **/
    public Secret(JSONObject jOrganizationSecret) {
        super(jOrganizationSecret);
        name = hResponse.getString("name");
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
        String visibilityKey = hResponse.getString("visibility", vPrivate.name());
        if (visibilityKey.equals(vPrivate.toString()))
            visibility = vPrivate;
        else
            visibility = valueOf(visibilityKey);
        selectedRepositoriesUrl = hResponse.getString("selected_repositories_url");
    }

    /**
     * Method to get {@link #name} instance <br>
     * No-any params required
     *
     * @return {@link #name} instance as {@link String}
     **/
    public String getName() {
        return name;
    }

    /**
     * Method to get {@link #createdAt} instance <br>
     * No-any params required
     *
     * @return {@link #createdAt} instance as {@link String}
     **/
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Method to get {@link #createdAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #createdAt} timestamp as long
     **/
    public long getCreatedAtTimestamp() {
        return timeFormatter.formatAsTimestamp(createdAt);
    }

    /**
     * Method to get {@link #updatedAt} instance <br>
     * No-any params required
     *
     * @return {@link #updatedAt} instance as {@link String}
     **/
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Method to get {@link #updatedAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #updatedAt} timestamp as long
     **/
    public long getUpdatedAtTimestamp() {
        return timeFormatter.formatAsTimestamp(updatedAt);
    }

    /**
     * Method to get {@link #visibility} instance <br>
     * No-any params required
     *
     * @return {@link #visibility} instance as {@link Visibility}
     **/
    public Visibility getVisibility() {
        return visibility;
    }

    /**
     * Method to get {@link #selectedRepositoriesUrl} instance <br>
     * No-any params required
     *
     * @return {@link #selectedRepositoriesUrl} instance as {@link String}
     **/
    public String getSelectedRepositoriesUrl() {
        return selectedRepositoriesUrl;
    }

    /**
     * Method to create a payload to create or update a secret
     *
     * @param secretValue: the value of the secret
     * @param publicKey:   public key used
     * @param extraParams: extra params to add to the payload, you can pass {@code "null"} if there aren't
     * @return payload as {@link Params}
     **/
    public static Params createSecretPayload(String secretValue, GitHubPublicKey publicKey,
                                             Params extraParams) throws SodiumException {
        if (extraParams == null)
            extraParams = new Params();
        LazySodiumJava lazySodium = new LazySodiumJava(new SodiumJava(), UTF_8);
        String ciphertext = lazySodium.cryptoBoxSealEasy(secretValue, fromBase64String(publicKey.getKey()));
        extraParams.addParam("encrypted_value", getEncoder().encodeToString(ciphertext.getBytes(UTF_8)));
        extraParams.addParam("key_id", "" + publicKey.getKeyId());
        return extraParams;
    }

    /**
     * Method to create a secret
     *
     * @param secretResponse: obtained from GitHub's response
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return secret as {@code "format"} defines
     **/
    @Returner
    public static <T> T returnSecret(String secretResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(secretResponse);
            case LIBRARY_OBJECT:
                return (T) new Secret(new JSONObject(secretResponse));
            default:
                return (T) secretResponse;
        }
    }

}
