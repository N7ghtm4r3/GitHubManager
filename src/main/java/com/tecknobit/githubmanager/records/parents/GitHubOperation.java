package com.tecknobit.githubmanager.records.parents;

import com.tecknobit.githubmanager.pulls.pulls.records.PullRequest;
import com.tecknobit.githubmanager.users.users.records.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;
import static com.tecknobit.githubmanager.users.users.records.User.returnUsersList;

/**
 * The {@code GitHubOperation} class is useful to format a GitHub's operation
 *
 * @author N7ghtm4r3 - Tecknobit
 * @see GitHubResponse
 * @see GitHubOperationBaseStructure
 **/
public abstract class GitHubOperation extends GitHubOperationBaseStructure {

    /**
     * {@code locked} whether the operation is locked
     **/
    protected final boolean locked;

    /**
     * {@code user} of the operation
     **/
    protected final User user;

    /**
     * {@code body} of the operation
     **/
    protected final String body;

    /**
     * {@code labels} list of the operation
     **/
    protected final ArrayList<Label> labels;

    /**
     * {@code milestone} of the operation
     **/
    protected final Milestone milestone;

    /**
     * {@code authorAssociation} how the author is associated with the repository
     **/
    protected final AuthorAssociation authorAssociation;

    /**
     * {@code activeLockReason} active lock reason of the operation
     **/
    protected final LockReason activeLockReason;

    /**
     * {@code assignee} of the operation
     **/
    protected final User assignee;

    /**
     * {@code assignees} of the operation
     **/
    protected final ArrayList<User> assignees;

    /**
     * {@code draft} indicates whether the operation is a draft
     **/
    protected final boolean draft;

    /**
     * Constructor to init a {@link GitHubOperation}
     *
     * @param url               : url the operation
     * @param htmlUrl           : html url the operation
     * @param id                : id the operation
     * @param nodeId            : node identifier the operation
     * @param number            : number the operation
     * @param state             : state the operation
     * @param title             : title the operation
     * @param createdAt         : creation time the operation
     * @param updatedAt         : update time the operation
     * @param closedAt          :  close time the operation
     * @param locked            : whether the pull request is locked
     * @param user              : user the operation
     * @param body              :  body the operation
     * @param labels            : labels the pull request
     * @param milestone         : milestone the operation
     * @param activeLockReason  : active lock reason of the operation
     * @param assignee          : assignee the operation
     * @param assignees         : assignees the operation
     * @param authorAssociation : id the operation
     * @param draft: indicates whether the operation is a draft
     **/
    public GitHubOperation(String url, String htmlUrl, long id, String nodeId, long number, OperationState state,
                           String title, String createdAt, String updatedAt, String closedAt, boolean locked, User user,
                           String body, ArrayList<Label> labels, Milestone milestone, LockReason activeLockReason,
                           User assignee, ArrayList<User> assignees, AuthorAssociation authorAssociation, boolean draft) {
        super(url, htmlUrl, id, nodeId, number, state, title, createdAt, updatedAt, closedAt);
        this.locked = locked;
        this.user = user;
        this.body = body;
        this.labels = labels;
        this.milestone = milestone;
        this.activeLockReason = activeLockReason;
        this.assignee = assignee;
        this.assignees = assignees;
        this.authorAssociation = authorAssociation;
        this.draft = draft;
    }

    /**
     * Constructor to init a {@link GitHubOperation}
     *
     * @param jGitHubOperation: GitHub operation details as {@link JSONObject}
     **/
    public GitHubOperation(JSONObject jGitHubOperation) {
        super(jGitHubOperation);
        locked = hResponse.getBoolean("locked");
        user = new User(hResponse.getJSONObject("user", new JSONObject()));
        body = hResponse.getString("body");
        labels = new ArrayList<>();
        JSONArray jLabels = hResponse.getJSONArray("labels", new JSONArray());
        for (int j = 0; j < jLabels.length(); j++)
            labels.add(new Label(jLabels.getJSONObject(j)));
        JSONObject jMilestone = hResponse.getJSONObject("milestone");
        if (jMilestone != null)
            milestone = new Milestone(jMilestone);
        else
            milestone = null;
        String sEnum = hResponse.getString("active_lock_reason");
        if (sEnum != null)
            activeLockReason = LockReason.valueOf(sEnum);
        else
            activeLockReason = null;
        assignee = new User(hResponse.getJSONObject("assignee", new JSONObject()));
        assignees = returnUsersList(hResponse.getJSONArray("assignees"));
        sEnum = hResponse.getString("author_association");
        if (sEnum != null)
            authorAssociation = AuthorAssociation.valueOf(sEnum);
        else
            authorAssociation = null;
        draft = hResponse.getBoolean("draft");
    }

    /**
     * Method to get {@link #locked} instance <br>
     * No-any params required
     *
     * @return {@link #locked} instance as boolean
     **/
    public boolean isLocked() {
        return locked;
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
     * Method to get {@link #labels} instance <br>
     * No-any params required
     *
     * @return {@link #labels} instance as {@link ArrayList} of {@link Label}
     **/
    public ArrayList<Label> getLabels() {
        return labels;
    }

    /**
     * Method to get {@link #milestone} instance <br>
     * No-any params required
     *
     * @return {@link #milestone} instance as {@link Milestone}
     **/
    public Milestone getMilestone() {
        return milestone;
    }

    /**
     * Method to get {@link #activeLockReason} instance <br>
     * No-any params required
     *
     * @return {@link #activeLockReason} instance as {@link LockReason}
     **/
    public LockReason getActiveLockReason() {
        return activeLockReason;
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
     * Method to get {@link #assignees} instance <br>
     * No-any params required
     *
     * @return {@link #assignees} instance as {@link ArrayList} of {@link User}
     **/
    public ArrayList<User> getAssignees() {
        return assignees;
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
     * Method to get {@link #draft} instance <br>
     * No-any params required
     *
     * @return {@link #draft} instance as boolean
     **/
    public boolean isDraft() {
        return draft;
    }

    /**
     * {@code AuthorAssociation} list of available author associations
     **/
    public enum AuthorAssociation {

        /**
         * {@code COLLABORATOR} author association
         **/
        COLLABORATOR,

        /**
         * {@code CONTRIBUTOR} author association
         **/
        CONTRIBUTOR,

        /**
         * {@code FIRST_TIMER} author association
         **/
        FIRST_TIMER,

        /**
         * {@code FIRST_TIME_CONTRIBUTOR} author association
         **/
        FIRST_TIME_CONTRIBUTOR,

        /**
         * {@code MANNEQUIN} author association
         **/
        MANNEQUIN,

        /**
         * {@code MEMBER} author association
         **/
        MEMBER,

        /**
         * {@code NONE} author association
         **/
        NONE,

        /**
         * {@code OWNER} author association
         **/
        OWNER

    }

    /**
     * {@code LockReason} list of available lock reasons
     **/
    public enum LockReason {

        /**
         * {@code off_topic} lock reason
         **/
        off_topic("off-topic"),

        /**
         * {@code too_hated} lock reason
         **/
        too_hated("too heated"),

        /**
         * {@code resolved} lock reason
         **/
        resolved("resolved"),

        /**
         * {@code spam} lock reason
         **/
        spam("spam");

        private final String lockReason;

        LockReason(String lockReason) {
            this.lockReason = lockReason;
        }

        @Override
        public String toString() {
            return lockReason;
        }

    }

    /**
     * The {@code Label} class is useful to format a GitHub's label
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see GitHubResponse
     * @see BaseResponseDetails
     **/
    public static class Label extends BaseResponseDetails {

        /**
         * {@code nodeId} node identifier of the label
         **/
        private final String nodeId;

        /**
         * {@code description} of the label
         **/
        private final String description;

        /**
         * {@code color} of the label
         **/
        private final String color;

        /**
         * {@code isDefault} whether the label is a default label
         **/
        private final boolean isDefault;

        /**
         * Constructor to init a {@link PullRequest.Label}
         *
         * @param id          : identifier value
         * @param name        : the name of the item
         * @param url         : url value
         * @param nodeId      : node identifier value
         * @param description : description of the label
         * @param color       : color of the label
         * @param isDefault   : whether the label is a default label
         **/
        public Label(long id, String name, String url, String nodeId, String description, String color, boolean isDefault) {
            super(id, name, url);
            this.nodeId = nodeId;
            this.description = description;
            this.color = color;
            this.isDefault = isDefault;
        }

        /**
         * Constructor to init a {@link PullRequest.Label}
         *
         * @param jLabel : label details as {@link JSONObject}
         **/
        public Label(JSONObject jLabel) {
            super(jLabel);
            nodeId = hResponse.getString("node_id");
            description = hResponse.getString("description");
            color = hResponse.getString("color");
            isDefault = hResponse.getBoolean("default");
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
         * Method to get {@link #description} instance <br>
         * No-any params required
         *
         * @return {@link #description} instance as {@link String}
         **/
        public String getDescription() {
            return description;
        }

        /**
         * Method to get {@link #color} instance <br>
         * No-any params required
         *
         * @return {@link #color} instance as {@link String}
         **/
        public String getColor() {
            return color;
        }

        /**
         * Method to get {@link #isDefault} instance <br>
         * No-any params required
         *
         * @return {@link #isDefault} instance as {@link String}
         **/
        public boolean isDefault() {
            return isDefault;
        }

    }

    /**
     * The {@code Milestone} class is useful to format a GitHub's milestone
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see GitHubResponse
     * @see GitHubOperationBaseStructure
     **/
    public static class Milestone extends GitHubOperationBaseStructure {

        /**
         * {@code labelsUrl} labels url of the milestone
         **/
        private final String labelsUrl;

        /**
         * {@code description} of the milestone
         **/
        private final String description;

        /**
         * {@code creator} of the milestone
         **/
        private final User creator;

        /**
         * {@code openIssues} open issues
         **/
        private final int openIssues;

        /**
         * {@code openIssues} closed issues
         **/
        private final int closedIssues;

        /**
         * {@code dueOn} due on date of the milestone
         **/
        private final String dueOn;

        /**
         * Constructor to init a {@link PullRequest.Milestone}
         *
         * @param url          : url the operation
         * @param htmlUrl      : html url the operation
         * @param id           : id the operation
         * @param nodeId       : node identifier the operation
         * @param number       : number the operation
         * @param state        : state the operation
         * @param title        : title the operation
         * @param createdAt    : creation time the operation
         * @param updatedAt    : update time the operation
         * @param closedAt     :  close time the operation
         * @param labelsUrl    : labels url of the milestone
         * @param description  : description of the milestone
         * @param creator      : creator of the milestone
         * @param openIssues   : open issues
         * @param closedIssues : closed issues
         * @param dueOn        : due on date of the milestone
         **/
        public Milestone(String url, String htmlUrl, long id, String nodeId, long number, OperationState state,
                         String title, String createdAt, String updatedAt, String closedAt, String labelsUrl,
                         String description, User creator, int openIssues, int closedIssues, String dueOn) {
            super(url, htmlUrl, id, nodeId, number, state, title, createdAt, updatedAt, closedAt);
            this.labelsUrl = labelsUrl;
            this.description = description;
            this.creator = creator;
            this.openIssues = openIssues;
            this.closedIssues = closedIssues;
            this.dueOn = dueOn;
        }

        /**
         * Constructor to init a {@link PullRequest.Milestone}
         *
         * @param jMilestone : milestone details as {@link JSONObject}
         **/
        public Milestone(JSONObject jMilestone) {
            super(jMilestone);
            labelsUrl = hResponse.getString("labels_url");
            description = hResponse.getString("description");
            creator = new User(hResponse.getJSONObject("creator", new JSONObject()));
            openIssues = hResponse.getInt("open_issues", 0);
            closedIssues = hResponse.getInt("closed_issues", 0);
            dueOn = hResponse.getString("due_on");
        }

        /**
         * Method to get {@link #labelsUrl} instance <br>
         * No-any params required
         *
         * @return {@link #labelsUrl} instance as {@link String}
         **/
        public String getLabelsUrl() {
            return labelsUrl;
        }

        /**
         * Method to get {@link #description} instance <br>
         * No-any params required
         *
         * @return {@link #description} instance as {@link String}
         **/
        public String getDescription() {
            return description;
        }

        /**
         * Method to get {@link #creator} instance <br>
         * No-any params required
         *
         * @return {@link #creator} instance as {@link User}
         **/
        public User getCreator() {
            return creator;
        }

        /**
         * Method to get {@link #openIssues} instance <br>
         * No-any params required
         *
         * @return {@link #openIssues} instance as int
         **/
        public int getOpenIssues() {
            return openIssues;
        }

        /**
         * Method to get {@link #closedIssues} instance <br>
         * No-any params required
         *
         * @return {@link #closedIssues} instance as int
         **/
        public int getClosedIssues() {
            return closedIssues;
        }

        /**
         * Method to get {@link #dueOn} instance <br>
         * No-any params required
         *
         * @return {@link #dueOn} instance as {@link String}
         **/
        public String getDueOn() {
            return dueOn;
        }

        /**
         * Method to get {@link #dueOn} timestamp <br>
         * No-any params required
         *
         * @return {@link #dueOn} timestamp as long
         **/
        public long getDueOnTimestamp() {
            return getDateTimestamp(dueOn);
        }

        /**
         * {@code MilestoneSort} is a list for the milestone sorts available
         **/
        public enum MilestoneSort {

            /**
             * {@code "due_on"} sort
             **/
            due_on,

            /**
             * {@code "completeness"} sort
             **/
            completeness

        }

    }

}
