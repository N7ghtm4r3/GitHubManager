package com.tecknobit.githubmanager.users.gpgkeys.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.users.emails.records.GitHubEmail;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;



/**
 * The {@code GitHubGPGKey} class is useful to format a GitHub's GPG key
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/users/gpg-keys#list-gpg-keys-for-the-authenticated-user">
 *             List GPG keys for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/users/gpg-keys#create-a-gpg-key-for-the-authenticated-user">
 *             Create a GPG key for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/users/gpg-keys#get-a-gpg-key-for-the-authenticated-user">
 *             Get a GPG key for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/users/gpg-keys#list-gpg-keys-for-a-user">
 *             List GPG keys for a user</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class GitHubGPGKey extends GitHubResponse {

    /**
     * {@code id} of the key
     **/
    private final long id;

    /**
     * {@code name} of the key
     **/
    private final String name;

    /**
     * {@code primaryKeyId} primary key id of the key
     **/
    private final long primaryKeyId;

    /**
     * {@code keyId} key id of the key
     **/
    private final String keyId;

    /**
     * {@code publicKey} public key value
     **/
    private final String publicKey;

    /**
     * {@code emails} of the key
     **/
    private final ArrayList<GitHubEmail> emails;

    /**
     * {@code subkeys} of the key
     **/
    private final ArrayList<GitHubGPGKey> subkeys;

    /**
     * {@code canSign} whether the key can sign
     **/
    private final boolean canSign;

    /**
     * {@code canEncryptComms} whether the key can encrypt comms
     **/
    private final boolean canEncryptComms;

    /**
     * {@code canEncryptStorage} whether the key can encrypt storage
     **/
    private final boolean canEncryptStorage;

    /**
     * {@code canCertify} whether the key can certify
     **/
    private final boolean canCertify;

    /**
     * {@code createdAt} creation date of the key
     **/
    private final String createdAt;

    /**
     * {@code expiresAt} expiration date of the key
     **/
    private final String expiresAt;

    /**
     * {@code revoked} whether the key is revoked
     **/
    private final boolean revoked;

    /**
     * {@code rawKey} raw key value
     **/
    private final String rawKey;

    /**
     * Constructor to init a {@link GitHubGPGKey}
     *
     * @param id                : id of the key
     * @param name              : name of the key
     * @param primaryKeyId      : primary key id of the key
     * @param keyId             : key id of the key
     * @param publicKey         : public key value
     * @param emails            : emails of the key
     * @param subkeys           : subkeys of the key
     * @param canSign           : whether the key can sign
     * @param canEncryptComms   : whether the key can encrypt comms
     * @param canEncryptStorage : whether the key can encrypt storage
     * @param canCertify        : whether the key can certify
     * @param createdAt         : creation date of the key
     * @param expiresAt         : expiration date of the key
     * @param revoked           : whether the key is revoked
     * @param rawKey            : raw key value
     **/
    public GitHubGPGKey(long id, String name, long primaryKeyId, String keyId, String publicKey,
                        ArrayList<GitHubEmail> emails, ArrayList<GitHubGPGKey> subkeys, boolean canSign,
                        boolean canEncryptComms, boolean canEncryptStorage, boolean canCertify, String createdAt,
                        String expiresAt, boolean revoked, String rawKey) {
        super(null);
        this.id = id;
        this.name = name;
        this.primaryKeyId = primaryKeyId;
        this.keyId = keyId;
        this.publicKey = publicKey;
        this.emails = emails;
        this.subkeys = subkeys;
        this.canSign = canSign;
        this.canEncryptComms = canEncryptComms;
        this.canEncryptStorage = canEncryptStorage;
        this.canCertify = canCertify;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.revoked = revoked;
        this.rawKey = rawKey;
    }

    /**
     * Constructor to init a {@link GitHubGPGKey}
     *
     * @param jGitHubGPGKey : key details as {@link JSONObject}
     **/
    public GitHubGPGKey(JSONObject jGitHubGPGKey) {
        super(jGitHubGPGKey);
        id = hResponse.getLong("id", 0);
        name = hResponse.getString("name");
        primaryKeyId = hResponse.getLong("primary_key_id", 0);
        keyId = hResponse.getString("key_id");
        publicKey = hResponse.getString("public_key");
        emails = new ArrayList<>();
        JSONArray jList = hResponse.getJSONArray("emails", new JSONArray());
        for (int j = 0; j < jList.length(); j++)
            emails.add(new GitHubEmail(jList.getJSONObject(j)));
        subkeys = new ArrayList<>();
        jList = hResponse.getJSONArray("subkeys", new JSONArray());
        for (int j = 0; j < jList.length(); j++)
            subkeys.add(new GitHubGPGKey(jList.getJSONObject(j)));
        canSign = hResponse.getBoolean("can_sign");
        canEncryptComms = hResponse.getBoolean("can_encrypt_comms");
        canEncryptStorage = hResponse.getBoolean("can_encrypt_storage");
        canCertify = hResponse.getBoolean("can_certify");
        createdAt = hResponse.getString("created_at");
        expiresAt = hResponse.getString("expires_at");
        revoked = hResponse.getBoolean("revoked");
        rawKey = hResponse.getString("raw_key");
    }

    /**
     * Method to get {@link #id} instance <br>
     * No-any params required
     *
     * @return {@link #id} instance as long
     **/
    public long getId() {
        return id;
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
     * Method to get {@link #primaryKeyId} instance <br>
     * No-any params required
     *
     * @return {@link #primaryKeyId} instance as long
     **/
    public long getPrimaryKeyId() {
        return primaryKeyId;
    }

    /**
     * Method to get {@link #keyId} instance <br>
     * No-any params required
     *
     * @return {@link #keyId} instance as {@link String}
     **/
    public String getKeyId() {
        return keyId;
    }

    /**
     * Method to get {@link #publicKey} instance <br>
     * No-any params required
     *
     * @return {@link #publicKey} instance as {@link String}
     **/
    public String getPublicKey() {
        return publicKey;
    }

    /**
     * Method to get {@link #emails} instance <br>
     * No-any params required
     *
     * @return {@link #emails} instance as {@link ArrayList} of {@link GitHubEmail}
     **/
    public ArrayList<GitHubEmail> getEmails() {
        return emails;
    }

    /**
     * Method to get {@link #subkeys} instance <br>
     * No-any params required
     *
     * @return {@link #subkeys} instance as {@link ArrayList} of {@link GitHubGPGKey}
     **/
    public ArrayList<GitHubGPGKey> getSubkeys() {
        return subkeys;
    }

    /**
     * Method to get {@link #canSign} instance <br>
     * No-any params required
     *
     * @return {@link #canSign} instance as boolean
     **/
    public boolean canSign() {
        return canSign;
    }

    /**
     * Method to get {@link #canEncryptComms} instance <br>
     * No-any params required
     *
     * @return {@link #canEncryptComms} instance as boolean
     **/
    public boolean canEncryptComms() {
        return canEncryptComms;
    }

    /**
     * Method to get {@link #canEncryptStorage} instance <br>
     * No-any params required
     *
     * @return {@link #canEncryptStorage} instance as boolean
     **/
    public boolean canEncryptStorage() {
        return canEncryptStorage;
    }

    /**
     * Method to get {@link #canCertify} instance <br>
     * No-any params required
     *
     * @return {@link #canCertify} instance as boolean
     **/
    public boolean canCertify() {
        return canCertify;
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
     * Method to get {@link #expiresAt} instance <br>
     * No-any params required
     *
     * @return {@link #expiresAt} instance as {@link String}
     **/
    public String getExpiresAt() {
        return expiresAt;
    }

    /**
     * Method to get {@link #expiresAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #expiresAt} timestamp as long
     **/
    public long getExpiresAtTimestamp() {
        return timeFormatter.formatAsTimestamp(expiresAt);
    }

    /**
     * Method to get {@link #revoked} instance <br>
     * No-any params required
     *
     * @return {@link #revoked} instance as boolean
     **/
    public boolean isRevoked() {
        return revoked;
    }

    /**
     * Method to get {@link #rawKey} instance <br>
     * No-any params required
     *
     * @return {@link #rawKey} instance as {@link String}
     **/
    public String getRawKey() {
        return rawKey;
    }

}
