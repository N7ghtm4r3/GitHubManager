package com.tecknobit.githubmanager.records.generic;

import com.tecknobit.apimanager.formatters.JsonHelper;
import org.json.JSONObject;

/**
 * The {@code Permissions} class is useful to format a GitHub's permissions
 *
 * @author N7ghtm4r3 - Tecknobit
 **/
public class Permissions {

    /**
     * {@code "pull"} flag
     **/
    private final boolean pull;

    /**
     * {@code "triage"} flag
     **/
    private final boolean triage;

    /**
     * {@code "push"} flag
     **/
    private final boolean push;

    /**
     * {@code "maintain"} flag
     **/
    private final boolean maintain;

    /**
     * {@code "admin"} flag
     **/
    private final boolean admin;

    /**
     * Constructor to init a {@link Permissions}
     *
     * @param pull:  pull flag
     * @param push:  push flag
     * @param admin: admin flag
     **/
    public Permissions(boolean pull, boolean push, boolean admin) {
        this(pull, false, push, false, admin);
    }

    /**
     * Constructor to init a {@link Permissions}
     *
     * @param pull:     pull flag
     * @param triage:   triage flag
     * @param push:     push flag
     * @param maintain: maintain flag
     * @param admin:    admin flag
     **/
    public Permissions(boolean pull, boolean triage, boolean push, boolean maintain, boolean admin) {
        this.pull = pull;
        this.triage = triage;
        this.push = push;
        this.maintain = maintain;
        this.admin = admin;
    }

    /**
     * Constructor to init a {@link Permissions}
     *
     * @param jPermissions: permissions details as {@link JSONObject}
     **/
    public Permissions(JSONObject jPermissions) {
        JsonHelper hRepoPermissions = new JsonHelper(jPermissions);
        pull = hRepoPermissions.getBoolean("pull");
        triage = hRepoPermissions.getBoolean("triage");
        push = hRepoPermissions.getBoolean("push");
        maintain = hRepoPermissions.getBoolean("maintain");
        admin = hRepoPermissions.getBoolean("admin");
    }

    /**
     * Method to get {@link #admin} instance <br>
     * Any params required
     *
     * @return {@link #admin} instance as boolean
     **/
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Method to get {@link #triage} instance <br>
     * Any params required
     *
     * @return {@link #triage} instance as boolean
     **/
    public boolean isTriage() {
        return triage;
    }

    /**
     * Method to get {@link #push} instance <br>
     * Any params required
     *
     * @return {@link #push} instance as boolean
     **/
    public boolean canPush() {
        return push;
    }

    /**
     * Method to get {@link #maintain} instance <br>
     * Any params required
     *
     * @return {@link #maintain} instance as boolean
     **/
    public boolean canMaintain() {
        return maintain;
    }

    /**
     * Method to get {@link #pull} instance <br>
     * Any params required
     *
     * @return {@link #pull} instance as boolean
     **/
    public boolean canPull() {
        return pull;
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
