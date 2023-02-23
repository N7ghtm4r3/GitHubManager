package com.tecknobit.githubmanager.meta.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The {@code GitHubMetaInformation} class is useful to format a GitHub's meta information
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/meta#get-github-meta-information">
 * Get GitHub meta information</a>
 * @see GitHubResponse
 **/
public class GitHubMetaInformation extends GitHubResponse {

    /**
     * {@code verifiablePasswordAuthentication} whether verifiable password authentication is enabled
     **/
    private final boolean verifiablePasswordAuthentication;

    /**
     * {@code sshKeyFingerprints} value
     **/
    private final SSHKeyFingerprints sshKeyFingerprints;

    /**
     * {@code sshKeys} list of ssh keys
     **/
    private final ArrayList<String> sshKeys;

    /**
     * {@code hooks} list of hooks
     **/
    private final ArrayList<String> hooks;

    /**
     * {@code web} list of web
     **/
    private final ArrayList<String> web;

    /**
     * {@code api} list of api
     **/
    private final ArrayList<String> api;

    /**
     * {@code git} list of git
     **/
    private final ArrayList<String> git;

    /**
     * {@code packages} list of packages
     **/
    private final ArrayList<String> packages;

    /**
     * {@code pages} list of pages
     **/
    private final ArrayList<String> pages;

    /**
     * {@code importer} list of importer
     **/
    private final ArrayList<String> importer;

    /**
     * {@code actions} list of actions
     **/
    private final ArrayList<String> actions;

    /**
     * {@code dependabot} list of dependabot
     **/
    private final ArrayList<String> dependabot;

    /**
     * Constructor to init a {@link GitHubMetaInformation}
     *
     * @param verifiablePasswordAuthentication: code search url
     * @param sshKeyFingerprints:               commit search url
     * @param sshKeys:                          list of ssh keys
     * @param hooks:                            list of hooks
     * @param web:                              list of web
     * @param api:                              list of api
     * @param git:                              list of git
     * @param packages:                         list of packages
     * @param pages:                            list of pages
     * @param importer:                         list of importer
     * @param actions:                          list of actions
     * @param dependabot:                       list of dependabot
     **/
    public GitHubMetaInformation(boolean verifiablePasswordAuthentication, SSHKeyFingerprints sshKeyFingerprints,
                                 ArrayList<String> sshKeys, ArrayList<String> hooks, ArrayList<String> web,
                                 ArrayList<String> api, ArrayList<String> git, ArrayList<String> packages,
                                 ArrayList<String> pages, ArrayList<String> importer, ArrayList<String> actions,
                                 ArrayList<String> dependabot) {
        super(null);
        this.verifiablePasswordAuthentication = verifiablePasswordAuthentication;
        this.sshKeyFingerprints = sshKeyFingerprints;
        this.sshKeys = sshKeys;
        this.hooks = hooks;
        this.web = web;
        this.api = api;
        this.git = git;
        this.packages = packages;
        this.pages = pages;
        this.importer = importer;
        this.actions = actions;
        this.dependabot = dependabot;
    }

    /**
     * Constructor to init a {@link GitHubMetaInformation}
     *
     * @param jGitHubMetaInformation: GitHub meta information details as {@link JSONObject}
     **/
    public GitHubMetaInformation(JSONObject jGitHubMetaInformation) {
        super(jGitHubMetaInformation);
        verifiablePasswordAuthentication = hResponse.getBoolean("verifiable_password_authentication");
        JSONObject jFingerprints = hResponse.getJSONObject("ssh_key_fingerprints");
        if (jFingerprints != null)
            sshKeyFingerprints = new SSHKeyFingerprints(jFingerprints);
        else
            sshKeyFingerprints = null;
        sshKeys = returnStringsList(hResponse.getJSONArray("ssh_keys"));
        hooks = returnStringsList(hResponse.getJSONArray("hooks"));
        web = returnStringsList(hResponse.getJSONArray("web"));
        api = returnStringsList(hResponse.getJSONArray("api"));
        git = returnStringsList(hResponse.getJSONArray("git"));
        packages = returnStringsList(hResponse.getJSONArray("packages"));
        pages = returnStringsList(hResponse.getJSONArray("pages"));
        importer = returnStringsList(hResponse.getJSONArray("importer"));
        actions = returnStringsList(hResponse.getJSONArray("actions"));
        dependabot = returnStringsList(hResponse.getJSONArray("dependabot"));
    }

    /**
     * Method to get {@link #verifiablePasswordAuthentication} instance <br>
     * No-any params required
     *
     * @return {@link #verifiablePasswordAuthentication} instance as boolean
     **/
    public boolean isVerifiablePasswordAuthentication() {
        return verifiablePasswordAuthentication;
    }

    /**
     * Method to get {@link #sshKeyFingerprints} instance <br>
     * No-any params required
     *
     * @return {@link #sshKeyFingerprints} instance as {@link SSHKeyFingerprints}
     **/
    public SSHKeyFingerprints getSshKeyFingerprints() {
        return sshKeyFingerprints;
    }

    /**
     * Method to get {@link #sshKeys} instance <br>
     * No-any params required
     *
     * @return {@link #sshKeys} instance as {@link ArrayList} of {@link String}
     **/
    public ArrayList<String> getSshKeys() {
        return sshKeys;
    }

    /**
     * Method to get {@link #hooks} instance <br>
     * No-any params required
     *
     * @return {@link #hooks} instance as {@link ArrayList} of {@link String}
     **/
    public ArrayList<String> getHooks() {
        return hooks;
    }

    /**
     * Method to get {@link #web} instance <br>
     * No-any params required
     *
     * @return {@link #web} instance as {@link ArrayList} of {@link String}
     **/
    public ArrayList<String> getWeb() {
        return web;
    }

    /**
     * Method to get {@link #api} instance <br>
     * No-any params required
     *
     * @return {@link #api} instance as {@link ArrayList} of {@link String}
     **/
    public ArrayList<String> getApi() {
        return api;
    }

    /**
     * Method to get {@link #git} instance <br>
     * No-any params required
     *
     * @return {@link #git} instance as {@link ArrayList} of {@link String}
     **/
    public ArrayList<String> getGit() {
        return git;
    }

    /**
     * Method to get {@link #packages} instance <br>
     * No-any params required
     *
     * @return {@link #packages} instance as {@link ArrayList} of {@link String}
     **/
    public ArrayList<String> getPackages() {
        return packages;
    }

    /**
     * Method to get {@link #pages} instance <br>
     * No-any params required
     *
     * @return {@link #pages} instance as {@link ArrayList} of {@link String}
     **/
    public ArrayList<String> getPages() {
        return pages;
    }

    /**
     * Method to get {@link #importer} instance <br>
     * No-any params required
     *
     * @return {@link #importer} instance as {@link ArrayList} of {@link String}
     **/
    public ArrayList<String> getImporter() {
        return importer;
    }

    /**
     * Method to get {@link #actions} instance <br>
     * No-any params required
     *
     * @return {@link #actions} instance as {@link ArrayList} of {@link String}
     **/
    public ArrayList<String> getActions() {
        return actions;
    }

    /**
     * Method to get {@link #dependabot} instance <br>
     * No-any params required
     *
     * @return {@link #dependabot} instance as {@link ArrayList} of {@link String}
     **/
    public ArrayList<String> getDependabot() {
        return dependabot;
    }

    /**
     * The {@code SSHKeyFingerprints} class is useful to format a GitHub's SSH key fingerprints
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class SSHKeyFingerprints extends InnerClassItem {

        /**
         * {@code SHA256_RSA} key fingerprint
         **/
        private final String SHA256_RSA;

        /**
         * {@code SHA256_DSA} key fingerprint
         **/
        private final String SHA256_DSA;

        /**
         * {@code SHA256_ECDSA} key fingerprint
         **/
        private final String SHA256_ECDSA;

        /**
         * {@code SHA256_ED25519} key fingerprint
         **/
        private final String SHA256_ED25519;

        /**
         * Constructor to init a {@link SSHKeyFingerprints}
         *
         * @param SHA256_RSA:     SHA256_RSA  key fingerprint
         * @param SHA256_DSA:     SHA256_DSA key fingerprint
         * @param SHA256_ECDSA:   SHA256_ECDSA key fingerprint
         * @param SHA256_ED25519: SHA256_ED25519 key fingerprint
         **/
        public SSHKeyFingerprints(String SHA256_RSA, String SHA256_DSA, String SHA256_ECDSA, String SHA256_ED25519) {
            super(null);
            this.SHA256_RSA = SHA256_RSA;
            this.SHA256_DSA = SHA256_DSA;
            this.SHA256_ECDSA = SHA256_ECDSA;
            this.SHA256_ED25519 = SHA256_ED25519;
        }

        /**
         * Constructor to init a {@link SSHKeyFingerprints}
         *
         * @param jSSHKeyFingerprints: SSH key fingerprints details as {@link JSONObject}
         **/
        public SSHKeyFingerprints(JSONObject jSSHKeyFingerprints) {
            super(jSSHKeyFingerprints);
            SHA256_RSA = hItem.getString("SHA256_RSA");
            SHA256_DSA = hItem.getString("SHA256_DSA");
            SHA256_ECDSA = hItem.getString("SHA256_ECDSA");
            SHA256_ED25519 = hItem.getString("SHA256_ED25519");
        }

        /**
         * Method to get {@link #SHA256_RSA} instance <br>
         * No-any params required
         *
         * @return {@link #SHA256_RSA} instance as {@link String}
         **/
        public String getSHA256_RSA() {
            return SHA256_RSA;
        }

        /**
         * Method to get {@link #SHA256_DSA} instance <br>
         * No-any params required
         *
         * @return {@link #SHA256_DSA} instance as {@link String}
         **/
        public String getSHA256_DSA() {
            return SHA256_DSA;
        }

        /**
         * Method to get {@link #SHA256_ECDSA} instance <br>
         * No-any params required
         *
         * @return {@link #SHA256_ECDSA} instance as {@link String}
         **/
        public String getSHA256_ECDSA() {
            return SHA256_ECDSA;
        }

        /**
         * Method to get {@link #SHA256_ED25519} instance <br>
         * No-any params required
         *
         * @return {@link #SHA256_ED25519} instance as {@link String}
         **/
        public String getSHA256_ED25519() {
            return SHA256_ED25519;
        }

    }

}
