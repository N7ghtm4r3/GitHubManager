package com.tecknobit.githubmanager.pulls.reviewrequests.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.teams.teams.records.Team;
import com.tecknobit.githubmanager.users.users.records.User;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.teams.teams.records.Team.returnTeamsList;
import static com.tecknobit.githubmanager.users.users.records.User.returnUsersList;

/**
 * The {@code RequestedReviewers} class is useful to format a GitHub's requested reviewers
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/pulls/review-requests#get-all-requested-reviewers-for-a-pull-request">
 * Get all requested reviewers for a pull request</a>
 * @see GitHubResponse
 **/
public class RequestedReviewers extends GitHubResponse {

    /**
     * {@code url} list
     **/
    private final ArrayList<User> users;

    /**
     * {@code teams} list
     **/
    private final ArrayList<Team> teams;

    /**
     * Constructor to init a {@link RequestedReviewers}
     *
     * @param users: users list
     * @param teams  : teams list
     **/
    public RequestedReviewers(ArrayList<User> users, ArrayList<Team> teams) {
        super(null);
        this.users = users;
        this.teams = teams;
    }

    /**
     * Constructor to init a {@link RequestedReviewers}
     *
     * @param jRequestedReviewers: list of requested reviewers details as {@link JSONObject}
     **/
    public RequestedReviewers(JSONObject jRequestedReviewers) {
        super(jRequestedReviewers);
        users = returnUsersList(hResponse.getJSONArray("users"));
        teams = returnTeamsList(hResponse.getJSONArray("teams").toString(), LIBRARY_OBJECT);
    }

    /**
     * Method to get {@link #users} instance <br>
     * No-any params required
     *
     * @return {@link #users} instance as {@link ArrayList} of {@link User}
     **/
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Method to get {@link #teams} instance <br>
     * No-any params required
     *
     * @return {@link #teams} instance as {@link ArrayList} of {@link Team}
     **/
    public ArrayList<Team> getTeams() {
        return teams;
    }

}
