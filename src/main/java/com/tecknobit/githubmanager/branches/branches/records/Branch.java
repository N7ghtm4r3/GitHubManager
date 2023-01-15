package com.tecknobit.githubmanager.branches.branches.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.branches.records.BranchProtection;
import com.tecknobit.githubmanager.commits.commits.records.Commit;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code Branch} class is useful to format a GitHub's branch
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/branches/branches#list-branches">
 *             List branches</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/branches/branches#get-a-branch">
 *             Get a branch</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/branches/branches#rename-a-branch">
 *             Rename a branch</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see ShortBranch
 **/
public class Branch extends ShortBranch {

    /**
     * {@code _links} links of the branch
     **/
    private final BranchLink _links;

    /**
     * {@code protection} of the branch
     **/
    private final BranchProtection protection;

    /**
     * {@code protectionUrl} protection url of the branch protection
     **/
    private final String protectionUrl;

    /**
     * Constructor to init a {@link Branch}
     *
     * @param name          : name of the branch
     * @param commit        : commit of the branch
     * @param _links        : links of the branch
     * @param isProtected   : whether the branch is protected
     * @param protection    : protection of the branch
     * @param protectionUrl : protection url of the branch protection
     **/
    public Branch(String name, Commit commit, BranchLink _links, boolean isProtected, BranchProtection protection,
                  String protectionUrl) {
        super(name, commit, isProtected);
        this._links = _links;
        this.protection = protection;
        this.protectionUrl = protectionUrl;
    }

    /**
     * Constructor to init a {@link Branch}
     *
     * @param jBranch : branch details as {@link JSONObject}
     **/
    public Branch(JSONObject jBranch) throws Exception {
        super(jBranch);
        _links = new BranchLink(hResponse.getJSONObject("_links", new JSONObject()));
        protection = new BranchProtection(hResponse.getJSONObject("protection", new JSONObject()));
        protectionUrl = hResponse.getString("protection_url");
    }

    /**
     * Method to get {@link #_links} instance <br>
     * Any params required
     *
     * @return {@link #_links} instance as {@link BranchLink}
     **/
    public BranchLink getLinks() {
        return _links;
    }

    /**
     * Method to get {@link #protection} instance <br>
     * Any params required
     *
     * @return {@link #protection} instance as {@link BranchProtection}
     **/
    public BranchProtection getProtection() {
        return protection;
    }

    /**
     * Method to get {@link #protectionUrl} instance <br>
     * Any params required
     *
     * @return {@link #protectionUrl} instance as {@link String}
     **/
    public String getProtectionUrl() {
        return protectionUrl;
    }

    /**
     * The {@code BranchLink} class is useful to format a GitHub's branch link for {@link Branch}
     *
     * @author N7ghtm4r3 - Tecknobit
     **/
    public static class BranchLink {

        /**
         * {@code self} value of the link
         **/
        private final String self;

        /**
         * {@code html} value of the link
         **/
        private final String html;

        /**
         * Constructor to init a {@link BranchLink}
         *
         * @param self : self value of the link
         * @param html : html value of the link
         **/
        public BranchLink(String self, String html) {
            this.self = self;
            this.html = html;
        }

        /**
         * Constructor to init a {@link BranchLink}
         *
         * @param jBranchLink : link details as {@link JSONObject}
         **/
        public BranchLink(JSONObject jBranchLink) {
            JsonHelper hBranchLink = new JsonHelper(jBranchLink);
            self = hBranchLink.getString("self");
            html = hBranchLink.getString("html");
        }

        /**
         * Method to get {@link #self} instance <br>
         * Any params required
         *
         * @return {@link #self} instance as {@link String}
         **/
        public String getSelf() {
            return self;
        }

        /**
         * Method to get {@link #html} instance <br>
         * Any params required
         *
         * @return {@link #html} instance as {@link String}
         **/
        public String getHtml() {
            return html;
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
