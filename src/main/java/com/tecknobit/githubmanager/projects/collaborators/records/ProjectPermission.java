package com.tecknobit.githubmanager.projects.collaborators.records;

import com.tecknobit.githubmanager.organizations.organizations.records.Organization.RepositoryPermission;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.users.users.records.User;
import org.json.JSONObject;

/**
 * The {@code ProjectPermission} class is useful to format a GitHub's project permission
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/collaborators#get-project-permission-for-a-user">
 * Get project permission for a user</a>
 * @see GitHubResponse
 **/
public class ProjectPermission extends GitHubResponse {

    /**
     * {@code permission} of the project
     **/
    private final RepositoryPermission permission;

    /**
     * {@code user} of the project
     **/
    private final User user;

    /**
     * Constructor to init a {@link ProjectPermission}
     *
     * @param permission : permission of the project
     * @param user       : user of the project
     **/
    public ProjectPermission(RepositoryPermission permission, User user) {
        super(null);
        this.permission = permission;
        this.user = user;
    }

    /**
     * Constructor to init a {@link ProjectPermission}
     *
     * @param jProjectPermission : project permission details as {@link JSONObject}
     **/
    public ProjectPermission(JSONObject jProjectPermission) {
        super(jProjectPermission);
        permission = RepositoryPermission.valueOf(hResponse.getString("permission"));
        user = new User(hResponse.getJSONObject("user"));
    }

    /**
     * Method to get {@link #permission} instance <br>
     * No-any params required
     *
     * @return {@link #permission} instance as {@link RepositoryPermission}
     **/
    public RepositoryPermission getPermission() {
        return permission;
    }

    /**
     * Method to get {@link #user} instance <br>
     * No-any params required
     *
     * @return {@link #user} instance as {@link User}
     **/
    public User getUser() {
        return user;
    }

}
