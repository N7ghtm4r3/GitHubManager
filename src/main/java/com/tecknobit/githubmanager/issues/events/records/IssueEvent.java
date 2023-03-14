package com.tecknobit.githubmanager.issues.events.records;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.apps.apps.records.GitHubApp;
import com.tecknobit.githubmanager.issues.issues.records.Issue;
import com.tecknobit.githubmanager.reactions.records.Reactions;
import com.tecknobit.githubmanager.records.organization.Team;
import com.tecknobit.githubmanager.records.parents.GitHubOperation.AuthorAssociation;
import com.tecknobit.githubmanager.records.parents.GitHubOperation.Label;
import com.tecknobit.githubmanager.records.parents.GitHubOperation.LockReason;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat;

/**
 * The {@code IssueEvent} class is useful to format a GitHub's issue event
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/issues/events#list-issue-events-for-a-repository">
 *             List issue events for a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/issues/events#get-an-issue-event">
 *             Get an issue event</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/issues/events#list-issue-events">
 *             List issue events</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class IssueEvent extends GitHubResponse {

    /**
     * {@code id} unique identifier of the issue event
     **/
    private final long id;

    /**
     * {@code nodeId} node id of the issue event
     **/
    private final String nodeId;

    /**
     * {@code url} of the issue event
     **/
    private final String url;

    /**
     * {@code actor} of the issue event
     **/
    private final User actor;

    /**
     * {@code event} of the issue
     **/
    private final String event;

    /**
     * {@code commitId} commit identifier of the issue event
     **/
    private final String commitId;

    /**
     * {@code commitUrl} commit url of the issue event
     **/
    private final String commitUrl;

    /**
     * {@code createdAt} creation time of the issue event
     **/
    private final String createdAt;

    /**
     * {@code issue} of the event
     **/
    private final Issue issue;

    /**
     * {@code label} of the issue event
     **/
    private final Label label;

    /**
     * {@code assignee} of the issue event
     **/
    private final User assignee;

    /**
     * {@code assigner} of the issue event
     **/
    private final User assigner;

    /**
     * {@code reviewRequester} review requester of the issue event
     **/
    private final User reviewRequester;

    /**
     * {@code requestedReviewer} requested reviewer of the issue event
     **/
    private final User requestedReviewer;

    /**
     * {@code requestedTeam} requested team of the issue event
     **/
    private final Team requestedTeam;

    /**
     * {@code dismissedReview} dismissed review event type
     **/
    private final DismissedReview dismissedReview;

    /**
     * {@code milestone} event type
     **/
    private final String milestone;

    /**
     * {@code projectCard} project card event type
     **/
    private final ProjectCard projectCard;

    /**
     * {@code rename} event type
     **/
    private final Rename rename;

    /**
     * {@code authorAssociation} how the author is associated with the repository
     **/
    private final AuthorAssociation authorAssociation;

    /**
     * {@code lockReason} lock reason of the issue event
     **/
    private final LockReason lockReason;

    /**
     * {@code performedViaGitHubApp} GitHub app attached to the issue event
     **/
    private final GitHubApp performedViaGitHubApp;

    /**
     * {@code body} of the issue event
     **/
    private final String body;

    /**
     * {@code reactions} reactions of the issue event
     **/
    private final Reactions reactions;


    /**
     * Constructor to init a {@link IssueEvent}
     *
     * @param id                    : id of the issue event
     * @param nodeId                : node id of the issue event
     * @param url                   :  url of the issue event
     * @param actor                 : actor of the issue event
     * @param event                 : event of the issue
     * @param commitId              : commit id of the issue event
     * @param commitUrl             : commit url of the issue event
     * @param createdAt             : creation time of the issue event
     * @param issue                 : of the event
     * @param label                 : label of the issue event
     * @param assignee              : assignee of the issue event
     * @param assigner              : assigner of the issue event
     * @param reviewRequester       : review requester of the issue event
     * @param requestedReviewer     : requested reviewer of the issue event
     * @param requestedTeam         : requested team of the issue event
     * @param dismissedReview       : dismissed review event type
     * @param milestone             :  milestone event type
     * @param projectCard           : project card event type
     * @param rename                : rename event type
     * @param authorAssociation     : how the author is associated with the repository
     * @param lockReason            : lock reason of the issue event
     * @param performedViaGitHubApp : GitHub app attached to the issue event
     * @param body:                 body of the issue event
     * @param reactions:            reaction of the issue event
     **/
    public IssueEvent(long id, String nodeId, String url, User actor, String event, String commitId, String commitUrl,
                      String createdAt, Issue issue, Label label, User assignee, User assigner, User reviewRequester,
                      User requestedReviewer, Team requestedTeam, DismissedReview dismissedReview, String milestone,
                      ProjectCard projectCard, Rename rename, AuthorAssociation authorAssociation, LockReason lockReason,
                      GitHubApp performedViaGitHubApp, String body, Reactions reactions) {
        super(null);
        this.id = id;
        this.nodeId = nodeId;
        this.url = url;
        this.actor = actor;
        this.event = event;
        this.commitId = commitId;
        this.commitUrl = commitUrl;
        this.createdAt = createdAt;
        this.issue = issue;
        this.label = label;
        this.assignee = assignee;
        this.assigner = assigner;
        this.reviewRequester = reviewRequester;
        this.requestedReviewer = requestedReviewer;
        this.requestedTeam = requestedTeam;
        this.dismissedReview = dismissedReview;
        this.milestone = milestone;
        this.projectCard = projectCard;
        this.rename = rename;
        this.authorAssociation = authorAssociation;
        this.lockReason = lockReason;
        this.performedViaGitHubApp = performedViaGitHubApp;
        this.body = body;
        this.reactions = reactions;
    }

    /**
     * Constructor to init a {@link IssueEvent}
     *
     * @param jIssueEvent: issue event details as {@link JSONObject}
     **/
    public IssueEvent(JSONObject jIssueEvent) throws Exception {
        super(jIssueEvent);
        id = hResponse.getLong("id", 0);
        nodeId = hResponse.getString("node_id");
        url = hResponse.getString("url");
        actor = new User(hResponse.getJSONObject("actor"));
        event = hResponse.getString("event");
        commitId = hResponse.getString("commit_id");
        commitUrl = hResponse.getString("commit_url");
        createdAt = hResponse.getString("created_at");
        body = hResponse.getString("body");
        JSONObject jItem = hResponse.getJSONObject("issue");
        if (jItem != null)
            issue = new Issue(jItem);
        else
            issue = null;
        jItem = hResponse.getJSONObject("label");
        if (jItem != null)
            label = new Label(jItem);
        else
            label = null;
        jItem = hResponse.getJSONObject("assignee");
        if (jItem != null)
            assignee = new User(jItem);
        else
            assignee = null;
        jItem = hResponse.getJSONObject("assigner");
        if (jItem != null)
            assigner = new User(jItem);
        else
            assigner = null;
        jItem = hResponse.getJSONObject("review_requester");
        if (jItem != null)
            reviewRequester = new User(jItem);
        else
            reviewRequester = null;
        jItem = hResponse.getJSONObject("requested_reviewer");
        if (jItem != null)
            requestedReviewer = new User(jItem);
        else
            requestedReviewer = null;
        jItem = hResponse.getJSONObject("requested_team");
        if (jItem != null)
            requestedTeam = new Team(jItem);
        else
            requestedTeam = null;
        jItem = hResponse.getJSONObject("dismissed_review");
        if (jItem != null)
            dismissedReview = new DismissedReview(jItem);
        else
            dismissedReview = null;
        milestone = hResponse.getJSONObject("key", new JSONObject().put("title", "")).getString("title");
        jItem = hResponse.getJSONObject("project_card");
        if (jItem != null)
            projectCard = new ProjectCard(jItem);
        else
            projectCard = null;
        jItem = hResponse.getJSONObject("rename");
        if (jItem != null)
            rename = new Rename(jItem);
        else
            rename = null;
        String sEnum = hResponse.getString("author_association");
        if (sEnum != null)
            authorAssociation = AuthorAssociation.valueOf(sEnum);
        else
            authorAssociation = null;
        sEnum = hResponse.getString("lock_reason");
        if (sEnum != null)
            lockReason = LockReason.valueOf(sEnum.replace("-", "_"));
        else
            lockReason = null;
        jItem = hResponse.getJSONObject("reactions");
        if (jItem != null)
            reactions = new Reactions(jItem);
        else
            reactions = null;
        jItem = hResponse.getJSONObject("performed_via_github_app");
        if (jItem != null)
            performedViaGitHubApp = new GitHubApp(jItem);
        else
            performedViaGitHubApp = null;
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
     * Method to get {@link #nodeId} instance <br>
     * No-any params required
     *
     * @return {@link #nodeId} instance as {@link String}
     **/
    public String getNodeId() {
        return nodeId;
    }

    /**
     * Method to get {@link #url} instance <br>
     * No-any params required
     *
     * @return {@link #url} instance as {@link String}
     **/
    public String getUrl() {
        return url;
    }

    /**
     * Method to get {@link #actor} instance <br>
     * No-any params required
     *
     * @return {@link #actor} instance as {@link User}
     **/
    public User getActor() {
        return actor;
    }

    /**
     * Method to get {@link #event} instance <br>
     * No-any params required
     *
     * @return {@link #event} instance as {@link String}
     **/
    public String getEvent() {
        return event;
    }

    /**
     * Method to get {@link #commitId} instance <br>
     * No-any params required
     *
     * @return {@link #commitId} instance as {@link String}
     **/
    public String getCommitId() {
        return commitId;
    }

    /**
     * Method to get {@link #commitUrl} instance <br>
     * No-any params required
     *
     * @return {@link #commitUrl} instance as {@link String}
     **/
    public String getCommitUrl() {
        return commitUrl;
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
        return getDateTimestamp(createdAt);
    }

    /**
     * Method to get {@link #issue} instance <br>
     * No-any params required
     *
     * @return {@link #issue} instance as {@link Issue}
     **/
    public Issue getIssue() {
        return issue;
    }

    /**
     * Method to get {@link #label} instance <br>
     * No-any params required
     *
     * @return {@link #label} instance as {@link Label}
     **/
    public Label getLabel() {
        return label;
    }

    /**
     * Method to get {@link #assignee} instance <br>
     * No-any params required
     *
     * @return {@link #assignee} instance as {@link User}
     **/
    public User getAssignee() {
        return assignee;
    }

    /**
     * Method to get {@link #assigner} instance <br>
     * No-any params required
     *
     * @return {@link #assigner} instance as {@link User}
     **/
    public User getAssigner() {
        return assigner;
    }

    /**
     * Method to get {@link #reviewRequester} instance <br>
     * No-any params required
     *
     * @return {@link #reviewRequester} instance as {@link User}
     **/
    public User getReviewRequester() {
        return reviewRequester;
    }

    /**
     * Method to get {@link #requestedReviewer} instance <br>
     * No-any params required
     *
     * @return {@link #requestedReviewer} instance as {@link User}
     **/
    public User getRequestedReviewer() {
        return requestedReviewer;
    }

    /**
     * Method to get {@link #requestedTeam} instance <br>
     * No-any params required
     *
     * @return {@link #requestedTeam} instance as {@link Team}
     **/
    public Team getRequestedTeam() {
        return requestedTeam;
    }

    /**
     * Method to get {@link #dismissedReview} instance <br>
     * No-any params required
     *
     * @return {@link #dismissedReview} instance as {@link DismissedReview}
     **/
    public DismissedReview getDismissedReview() {
        return dismissedReview;
    }

    /**
     * Method to get {@link #milestone} instance <br>
     * No-any params required
     *
     * @return {@link #milestone} instance as {@link String}
     **/
    public String getMilestone() {
        return milestone;
    }

    /**
     * Method to get {@link #projectCard} instance <br>
     * No-any params required
     *
     * @return {@link #projectCard} instance as {@link ProjectCard}
     **/
    public ProjectCard getProjectCard() {
        return projectCard;
    }

    /**
     * Method to get {@link #rename} instance <br>
     * No-any params required
     *
     * @return {@link #rename} instance as {@link Rename}
     **/
    public Rename getRename() {
        return rename;
    }

    /**
     * Method to get {@link #authorAssociation} instance <br>
     * No-any params required
     *
     * @return {@link #authorAssociation} instance as {@link AuthorAssociation}
     **/
    public AuthorAssociation getAuthorAssociation() {
        return authorAssociation;
    }

    /**
     * Method to get {@link #lockReason} instance <br>
     * No-any params required
     *
     * @return {@link #lockReason} instance as {@link LockReason}
     **/
    public LockReason getLockReason() {
        return lockReason;
    }

    /**
     * Method to get {@link #performedViaGitHubApp} instance <br>
     * No-any params required
     *
     * @return {@link #performedViaGitHubApp} instance as {@link GitHubApp}
     **/
    public GitHubApp getPerformedViaGitHubApp() {
        return performedViaGitHubApp;
    }

    /**
     * Method to get {@link #body} instance <br>
     * No-any params required
     *
     * @return {@link #body} instance as {@link String}
     **/
    public String getBody() {
        return body;
    }

    /**
     * Method to get {@link #reactions} instance <br>
     * No-any params required
     *
     * @return {@link #reactions} instance as {@link Reactions}
     **/
    public Reactions getReactions() {
        return reactions;
    }

    /**
     * Method to create an issue events list
     *
     * @param issueEventsListResponse : obtained from GitHub's response
     * @param format                  :              return type formatter -> {@link ReturnFormat}
     * @return issue events list as {@code "format"} defines
     **/
    @Returner
    public static <T> T returnIssueEventsList(String issueEventsListResponse, ReturnFormat format) throws Exception {
        switch (format) {
            case JSON:
                return (T) new JSONArray(issueEventsListResponse);
            case LIBRARY_OBJECT:
                ArrayList<IssueEvent> issueEvents = new ArrayList<>();
                JSONArray jIssueEvents = new JSONArray(issueEventsListResponse);
                for (int j = 0; j < jIssueEvents.length(); j++)
                    issueEvents.add(new IssueEvent(jIssueEvents.getJSONObject(j)));
                return (T) issueEvents;
            default:
                return (T) issueEventsListResponse;
        }
    }

    /**
     * The {@code DismissedReview} class is useful to format a GitHub's dismissed review event type
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class DismissedReview extends InnerClassItem {

        /**
         * {@code state} state of the dismissed review
         **/
        private final String state;

        /**
         * {@code reviewId} review identifier of the dismissed review
         **/
        private final long reviewId;

        /**
         * {@code dismissalMessage} dismissal message of the dismissed review
         **/
        private final String dismissalMessage;

        /**
         * {@code dismissalCommitId} dismissal commit identifier of the dismissed review
         **/
        private final String dismissalCommitId;

        /**
         * Constructor to init a {@link DismissedReview}
         *
         * @param state:             state of the dismissed review
         * @param reviewId:          review identifier of the dismissed review
         * @param dismissalMessage:  dismissal message of the dismissed review
         * @param dismissalCommitId: dismissal commit identifier of the dismissed review
         **/
        public DismissedReview(String state, long reviewId, String dismissalMessage, String dismissalCommitId) {
            super(null);
            this.state = state;
            this.reviewId = reviewId;
            this.dismissalMessage = dismissalMessage;
            this.dismissalCommitId = dismissalCommitId;
        }

        /**
         * Constructor to init a {@link DismissedReview}
         *
         * @param jDismissedReview: dismissed review details as {@link JSONObject}
         **/
        public DismissedReview(JSONObject jDismissedReview) {
            super(jDismissedReview);
            state = hItem.getString("state");
            reviewId = hItem.getLong("review_id", 0);
            dismissalMessage = hItem.getString("dismissal_message");
            dismissalCommitId = hItem.getString("dismissal_commit_id");
        }

        /**
         * Method to get {@link #state} instance <br>
         * No-any params required
         *
         * @return {@link #state} instance as {@link String}
         **/
        public String getState() {
            return state;
        }

        /**
         * Method to get {@link #reviewId} instance <br>
         * No-any params required
         *
         * @return {@link #reviewId} instance as long
         **/
        public long getReviewId() {
            return reviewId;
        }

        /**
         * Method to get {@link #dismissalMessage} instance <br>
         * No-any params required
         *
         * @return {@link #dismissalMessage} instance as {@link String}
         **/
        public String getDismissalMessage() {
            return dismissalMessage;
        }

        /**
         * Method to get {@link #dismissalCommitId} instance <br>
         * No-any params required
         *
         * @return {@link #dismissalCommitId} instance as {@link String}
         **/
        public String getDismissalCommitId() {
            return dismissalCommitId;
        }

    }

    /**
     * The {@code ProjectCard} class is useful to format a GitHub's project card event type
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class ProjectCard extends InnerClassItem {

        /**
         * {@code url} of the project card
         **/
        private final String url;

        /**
         * {@code id} of the project card
         **/
        private final long id;

        /**
         * {@code projectUrl} project url of the project card
         **/
        private final String projectUrl;

        /**
         * {@code projectId} project id of the project card
         **/
        private final long projectId;

        /**
         * {@code columnName} column name of the project card
         **/
        private final String columnName;

        /**
         * {@code previousColumnName} previous column name of the project card
         **/
        private final String previousColumnName;

        /**
         * Constructor to init a {@link ProjectCard}
         *
         * @param url:                url of the project card
         * @param id:                 id of the project card
         * @param projectUrl:         project url of the project card
         * @param projectId:          project id of the project card
         * @param columnName:         column name of the project card
         * @param previousColumnName: previous column name of the project card
         **/
        public ProjectCard(String url, long id, String projectUrl, long projectId, String columnName,
                           String previousColumnName) {
            super(null);
            this.url = url;
            this.id = id;
            this.projectUrl = projectUrl;
            this.projectId = projectId;
            this.columnName = columnName;
            this.previousColumnName = previousColumnName;
        }

        /**
         * Constructor to init a {@link ProjectCard}
         *
         * @param jProjectCard: project card details as {@link JSONObject}
         **/
        public ProjectCard(JSONObject jProjectCard) {
            super(jProjectCard);
            url = hItem.getString("url");
            id = hItem.getLong("id", 0);
            projectUrl = hItem.getString("project_url");
            projectId = hItem.getLong("project_id", 0);
            columnName = hItem.getString("column_name");
            previousColumnName = hItem.getString("previous_column_name");
        }

        /**
         * Method to get {@link #url} instance <br>
         * No-any params required
         *
         * @return {@link #url} instance as {@link String}
         **/
        public String getUrl() {
            return url;
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
         * Method to get {@link #projectUrl} instance <br>
         * No-any params required
         *
         * @return {@link #projectUrl} instance as {@link String}
         **/
        public String getProjectUrl() {
            return projectUrl;
        }

        /**
         * Method to get {@link #projectId} instance <br>
         * No-any params required
         *
         * @return {@link #projectId} instance as long
         **/
        public long getProjectId() {
            return projectId;
        }

        /**
         * Method to get {@link #columnName} instance <br>
         * No-any params required
         *
         * @return {@link #columnName} instance as {@link String}
         **/
        public String getColumnName() {
            return columnName;
        }

        /**
         * Method to get {@link #previousColumnName} instance <br>
         * No-any params required
         *
         * @return {@link #previousColumnName} instance as {@link String}
         **/
        public String getPreviousColumnName() {
            return previousColumnName;
        }

    }

    /**
     * The {@code Rename} class is useful to format a GitHub's rename event type
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class Rename extends InnerClassItem {

        /**
         * {@code from} object of the rename event type
         **/
        private final String from;

        /**
         * {@code to} object of the rename event type
         **/
        private final String to;

        /**
         * Constructor to init a {@link Rename}
         *
         * @param from: from object of the rename event type
         * @param to:   to object of the rename event type
         **/
        public Rename(String from, String to) {
            super(null);
            this.from = from;
            this.to = to;
        }

        /**
         * Constructor to init a {@link Rename}
         *
         * @param jRename: rename details as {@link JSONObject}
         **/
        public Rename(JSONObject jRename) {
            super(jRename);
            from = hItem.getString("from");
            to = hItem.getString("to");
        }

        /**
         * Method to get {@link #from} instance <br>
         * No-any params required
         *
         * @return {@link #from} instance as {@link String}
         **/
        public String getFrom() {
            return from;
        }

        /**
         * Method to get {@link #to} instance <br>
         * No-any params required
         *
         * @return {@link #to} instance as {@link String}
         **/
        public String getTo() {
            return to;
        }

    }

}
