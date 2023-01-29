package com.tecknobit.githubmanager.collaborators.collaborators.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code RepositoryPermissions} class is useful to format a GitHub's repository permissions
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/collaborators/collaborators#get-repository-permissions-for-a-user">
 * Get repository permissions for a user/a>
 * @see GitHubResponse
 **/
public class RepositoryPermissions extends GitHubResponse {

    /**
     * {@code permissions} of the repository
     **/
    private final String permission;

    /**
     * {@code roleName} role name of the repository
     **/
    private final String roleName;

    /**
     * {@code user} of the repository
     **/
    private final Collaborator user;

    /**
     * Constructor to init a {@link RepositoryPermissions}
     *
     * @param permission :    permission of the repository
     * @param roleName   : role name of the repository
     * @param user:      user of the repository
     **/
    public RepositoryPermissions(String permission, String roleName, Collaborator user) {
        super(null);
        this.permission = permission;
        this.roleName = roleName;
        this.user = user;
    }

    /**
     * Constructor to init a {@link RepositoryPermissions}
     *
     * @param jRepositoryPermissions :    repository permissions details as {@link JSONObject}
     **/
    public RepositoryPermissions(JSONObject jRepositoryPermissions) {
        super(jRepositoryPermissions);
        permission = hResponse.getString("permission");
        roleName = hResponse.getString("role_name");
        user = new Collaborator(hResponse.getJSONObject("user", new JSONObject()));
    }

    /**
     * Method to get {@link #permission} instance <br>
     * No-any params required
     *
     * @return {@link #permission} instance as {@link String}
     **/
    public String getPermission() {
        return permission;
    }

    /**
     * Method to get {@link #roleName} instance <br>
     * No-any params required
     *
     * @return {@link #roleName} instance as {@link String}
     **/
    public String getRoleName() {
        return roleName;
    }

    /**
     * Method to get {@link #user} instance <br>
     * No-any params required
     *
     * @return {@link #user} instance as {@link Collaborator}
     **/
    public Collaborator getUser() {
        return user;
    }

}
