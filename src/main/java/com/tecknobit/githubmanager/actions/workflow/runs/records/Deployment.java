package com.tecknobit.githubmanager.actions.workflow.runs.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.actions.workflow.runs.records.Review.Environment;
import com.tecknobit.githubmanager.records.organization.Team;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code Deployment} class is useful to format a GitHub's deployment
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-pending-deployments-for-a-workflow-run">
 * Get pending deployments for a workflow run</a>
 * @see GitHubResponse
 **/
public class Deployment extends GitHubResponse {

    /**
     * {@code environment} environment value
     **/
    private final Environment environment;

    /**
     * {@code waitTimer} the set duration of the wait timer
     **/
    private final long waitTimer;

    /**
     * {@code waitTimerStartedAt} the time that the wait timer began
     **/
    private final String waitTimerStartedAt;

    /**
     * {@code currentUserCanApprove} whether the currently authenticated user can approve the deployment
     **/
    private final boolean currentUserCanApprove;

    /**
     * {@code reviewers} the people or teams that may approve jobs that reference the environment. You can list up to six users or teams as reviewers.
     * The reviewers must have at least read access to the repository. Only one of the required reviewers needs to approve the job for it to proceed
     **/
    private final ArrayList<Reviewer<?>> reviewers;

    /**
     * Constructor to init a {@link Deployment}
     *
     * @param environment: environment value
     * @param waitTimer: the set duration of the wait timer
     * @param waitTimerStartedAt: the time that the wait timer began
     * @param currentUserCanApprove: whether the currently authenticated user can approve the deployment
     * @param reviewers:  the people or teams that may approve jobs that reference the environment
     **/
    public Deployment(Environment environment, long waitTimer, String waitTimerStartedAt, boolean currentUserCanApprove,
                      ArrayList<Reviewer<?>> reviewers) {
        super(null);
        this.environment = environment;
        this.waitTimer = waitTimer;
        this.waitTimerStartedAt = waitTimerStartedAt;
        this.currentUserCanApprove = currentUserCanApprove;
        this.reviewers = reviewers;
    }

    /**
     * Constructor to init a {@link Deployment}
     *
     * @param jDeployment: deployment details as {@link JSONObject}
     **/
    public Deployment(JSONObject jDeployment) {
        super(jDeployment);
        environment = new Environment(hResponse.getJSONObject("environment", new JSONObject()));
        waitTimer = hResponse.getLong("wait_timer", 0);
        waitTimerStartedAt = hResponse.getString("wait_timer_started_at");
        currentUserCanApprove = hResponse.getBoolean("current_user_can_approve");
        reviewers = new ArrayList<>();
        JSONArray jReviewers = hResponse.getJSONArray("reviewers", new JSONArray());
        for (int j = 0; j < jReviewers.length(); j++) {
            JSONObject reviewer = jReviewers.getJSONObject(j);
            if (reviewer.getString("type").equals(Reviewer.ReviewerType.User.toString()))
                reviewers.add(new Reviewer<User>(reviewer));
            else
                reviewers.add(new Reviewer<Team>(reviewer));
        }
    }

    /**
     * Method to get {@link #environment} instance <br>
     * Any params required
     *
     * @return {@link #environment} instance as {@link Environment}
     **/
    public Environment getEnvironment() {
        return environment;
    }

    /**
     * Method to get {@link #waitTimer} instance <br>
     * Any params required
     *
     * @return {@link #waitTimer} instance as long
     **/
    public long getWaitTimer() {
        return waitTimer;
    }

    /**
     * Method to get {@link #waitTimerStartedAt} instance <br>
     * Any params required
     *
     * @return {@link #waitTimerStartedAt} instance as {@link String}
     **/
    public String getWaitTimerStartedAt() {
        return waitTimerStartedAt;
    }

    /**
     * Method to get {@link #waitTimerStartedAt} timestamp <br>
     * Any params required
     *
     * @return {@link #waitTimerStartedAt} timestamp as long
     **/
    public long getWaitTimerStartedAtTimestamp() {
        return getDateTimestamp(waitTimerStartedAt);
    }

    /**
     * Method to get {@link #currentUserCanApprove} instance <br>
     * Any params required
     *
     * @return {@link #currentUserCanApprove} instance as boolean
     **/
    public boolean isCurrentUserCanApprove() {
        return currentUserCanApprove;
    }

    /**
     * Method to get {@link #reviewers} instance <br>
     * Any params required
     *
     * @return {@link #reviewers} instance as {@link Collection} of {@link Reviewer}
     **/
    public Collection<Reviewer<?>> getReviewers() {
        return reviewers;
    }

    /**
     * Method to get a list of reviewers filtered
     * @param filter: filter to create a list of {@link User} or {@link Team}
     * @throws IllegalArgumentException when filter inserted does not respect the correct range
     * @return list filtered as {@link Collection} of {@link User} of {@link Team}
     **/
    public <T> Collection<T> getReviewers(Class<T> filter) {
        if (filter != User.class && filter != Team.class)
            throw new IllegalArgumentException("Filter must be User or Team class type");
        ArrayList<T> fReviewers = new ArrayList<>();
        for (Reviewer<?> reviewer : reviewers) {
            T gReviewer = (T) reviewer.getEntity();
            if (gReviewer.getClass().equals(filter))
                fReviewers.add(gReviewer);
        }
        return fReviewers;
    }

    /**
     * The {@code Reviewer} class is useful to format a GitHub's reviewer
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/actions/workflow-runs#get-pending-deployments-for-a-workflow-run">
     *     Get pending deployments for a workflow run</a>
     * @author N7ghtm4r3 - Tecknobit
     * @param <T> this parameter should be filled with {@link User} or {@link Team} objects to work properly
     **/
    public static class Reviewer<T> {

        /**
         * {@code type} reviewer type value
         **/
        private final ReviewerType type;

        /**
         * {@code reviewer} reviewer value
         **/
        private final T reviewer;

        /**
         * Constructor to init a {@link Reviewer}
         *
         * @param entity: entity to populate reviewer
         * @throws IllegalArgumentException when entity inserted does not respect the correct range
         **/
        public Reviewer(T entity) {
            this.reviewer = entity;
            Class<?> rClass = reviewer.getClass();
            if (rClass == User.class)
                type = ReviewerType.User;
            else if (rClass == Team.class)
                type = ReviewerType.Team;
            else
                throw new IllegalArgumentException("Entity must be User or Team class type");
        }

        /**
         * Constructor to init a {@link Reviewer}
         * @param type: reviewer type value
         * @param entity: entity to populate reviewer
         **/
        public Reviewer(ReviewerType type, T entity) {
            this.type = type;
            this.reviewer = entity;
        }

        /**
         * Constructor to init a {@link Reviewer}
         * @param jReviewer: reviewer details as {@link JSONObject}
         **/
        public Reviewer(JSONObject jReviewer) {
            JsonHelper hReviewer = new JsonHelper(jReviewer);
            type = ReviewerType.valueOf(hReviewer.getString("type", ReviewerType.User.toString()));
            if (type.equals(ReviewerType.User))
                reviewer = (T) new User(hReviewer.getJSONObject("reviewer", new JSONObject()));
            else
                reviewer = (T) new Team(hReviewer.getJSONObject("reviewer", new JSONObject()));
        }

        /**
         * Method to get {@link #type} instance <br>
         * Any params required
         *
         * @return {@link #type} instance as {@link ReviewerType}
         **/
        public ReviewerType getType() {
            return type;
        }

        /**
         * Method to get {@link #reviewer} instance <br>
         * Any params required
         *
         * @return {@link #reviewer} instance as {@link T}
         **/
        public T getEntity() {
            return reviewer;
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

        /**
         * {@code ReviewerType} reviewer types list
         **/
        public enum ReviewerType {

            /**
             * {@code User} {@code "User"} reviewer type
             **/
            User,

            /**
             * {@code Team} {@code "Team"} reviewer type
             **/
            Team

        }

    }

}
