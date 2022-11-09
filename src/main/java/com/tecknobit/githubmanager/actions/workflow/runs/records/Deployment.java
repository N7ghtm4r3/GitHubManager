package com.tecknobit.githubmanager.actions.workflow.runs.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.actions.workflow.runs.records.Review.Environment;
import com.tecknobit.githubmanager.records.basics.GitHubResponse;
import com.tecknobit.githubmanager.records.basics.User;
import com.tecknobit.githubmanager.records.organization.Team;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

import static com.tecknobit.githubmanager.actions.artifacts.records.Artifact.dateFormatter;

public class Deployment extends GitHubResponse {

    private final Environment environment;
    private final long waitTimer;
    private final String waitTimerStartedAt;
    private final boolean currentUserCanApprove;
    private final ArrayList<Reviewer<?>> reviewers;

    public Deployment(Environment environment, long waitTimer, String waitTimerStartedAt, boolean currentUserCanApprove,
                      ArrayList<Reviewer<?>> reviewers) {
        super(null);
        this.environment = environment;
        this.waitTimer = waitTimer;
        this.waitTimerStartedAt = waitTimerStartedAt;
        this.currentUserCanApprove = currentUserCanApprove;
        this.reviewers = reviewers;
    }

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

    public Environment getEnvironment() {
        return environment;
    }

    public long getWaitTimer() {
        return waitTimer;
    }

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
        try {
            return dateFormatter.parse(waitTimerStartedAt).getTime();
        } catch (ParseException e) {
            return -1;
        }
    }

    public boolean isCurrentUserCanApprove() {
        return currentUserCanApprove;
    }

    public ArrayList<Reviewer<?>> getReviewers() {
        return reviewers;
    }

    public <T> ArrayList<T> getReviewers(Class<T> filter) {
        ArrayList<T> fReviewers = new ArrayList<>();
        for (Reviewer<?> reviewer : reviewers) {
            T gReviewer = (T) reviewer.getEntity();
            if (gReviewer.getClass().equals(filter))
                fReviewers.add(gReviewer);
        }
        return fReviewers;
    }

    public static class Reviewer<T> {

        private final ReviewerType type;
        private final T reviewer;

        public Reviewer(ReviewerType type, T entity) {
            this.type = type;
            this.reviewer = entity;
        }

        public Reviewer(JSONObject jEntity) {
            JsonHelper hReviewer = new JsonHelper(jEntity);
            type = ReviewerType.valueOf(hReviewer.getString("type", ReviewerType.User.toString()));
            if (type.equals(ReviewerType.User))
                reviewer = (T) new User(hReviewer.getJSONObject("reviewer", new JSONObject()));
            else
                reviewer = (T) new Team(hReviewer.getJSONObject("reviewer", new JSONObject()));
        }

        public ReviewerType getType() {
            return type;
        }

        public T getEntity() {
            return reviewer;
        }

        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

        public enum ReviewerType {

            User,
            Team

        }

    }

}
