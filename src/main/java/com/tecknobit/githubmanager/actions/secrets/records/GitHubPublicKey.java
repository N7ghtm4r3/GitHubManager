package com.tecknobit.githubmanager.actions.secrets.records;

import com.tecknobit.githubmanager.records.GitHubResponse;
import org.json.JSONObject;

public class GitHubPublicKey extends GitHubResponse {

    private final long keyId;
    private final String key;

    public GitHubPublicKey(long keyId, String key) {
        super(null);
        this.keyId = keyId;
        this.key = key;
    }

    /**
     * Constructor to init a {@link GitHubPublicKey}
     *
     * @param jGitHubPublicKey : public key details as {@link JSONObject}
     **/
    public GitHubPublicKey(JSONObject jGitHubPublicKey) {
        super(jGitHubPublicKey);
        keyId = hResponse.getLong("key_id");
        key = hResponse.getString("key");
    }

    public long getKeyId() {
        return keyId;
    }

    public String getKey() {
        return key;
    }

}
