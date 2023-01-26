package com.tecknobit.githubmanager.activity.events.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.actions.workflow.runs.records.WorkflowRun.Commit.CommitProfile;
import com.tecknobit.githubmanager.records.parents.BaseResponseDetails;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code Event} class is useful to format a GitHub's event
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/activity/events#list-public-events">
 *             List public events</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/activity/events#list-public-events-for-a-network-of-repositories">
 *             List public events for a network of repositories</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/activity/events#list-public-organization-events">
 *             List public organization events</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/activity/events#list-repository-events">
 *             List repository events</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/activity/events#list-events-for-the-authenticated-user">
 *             List events for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/activity/events#list-organization-events-for-the-authenticated-user">
 *             List organization events for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/activity/events#list-public-events-for-a-user">
 *             List public events for a user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/activity/events#list-events-received-by-the-authenticated-user">
 *             List events received by the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/activity/events#list-public-events-received-by-a-user">
 *             List public events received by a user</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class Event extends GitHubResponse {

    /**
     * {@code id} identifier value
     **/
    private final long id;

    /**
     * {@code type} type of the event
     **/
    private final EventType type;

    /**
     * {@code actor} actor value
     **/
    private final EventEntity actor;

    /**
     * {@code repo} repo value
     **/
    private final BaseResponseDetails repo;

    /**
     * {@code payload} payload of the event
     **/
    private final Payload payload;

    /**
     * {@code isPublic} whether this event is public
     **/
    private final boolean isPublic;

    /**
     * {@code createdAt} creation time of the event
     **/
    private final String createdAt;

    /**
     * {@code org} organization value
     **/
    private final EventEntity org;

    /**
     * Constructor to init a {@link Event}
     *
     * @param id:        identifier of the event
     * @param type:      type of the event
     * @param actor:     actor value
     * @param repo:      repo value
     * @param payload:   payload of the event
     * @param isPublic:  whether this event is public
     * @param createdAt: creation time of the event
     **/
    public Event(long id, EventType type, EventEntity actor, BaseResponseDetails repo, Payload payload, boolean isPublic,
                 String createdAt) {
        this(id, type, actor, repo, payload, isPublic, createdAt, null);
    }

    /**
     * Constructor to init a {@link Event}
     *
     * @param id:        identifier of the event
     * @param type:      type of the event
     * @param actor:     actor value
     * @param repo:      repo value
     * @param payload:   payload of the event
     * @param isPublic:  whether this event is public
     * @param createdAt: creation time of the event
     * @param org:       organization value
     **/
    public Event(long id, EventType type, EventEntity actor, BaseResponseDetails repo, Payload payload, boolean isPublic,
                 String createdAt, EventEntity org) {
        super(null);
        this.id = id;
        this.type = type;
        this.actor = actor;
        this.repo = repo;
        this.payload = payload;
        this.isPublic = isPublic;
        this.createdAt = createdAt;
        this.org = org;
    }

    /**
     * Constructor to init a {@link Event}
     *
     * @param jEvent: event details as {@link JSONObject}
     **/
    public Event(JSONObject jEvent) {
        super(jEvent);
        id = hResponse.getLong("id");
        type = EventType.valueOf(hResponse.getString("type"));
        actor = new EventEntity(hResponse.getJSONObject("actor", new JSONObject()));
        repo = new BaseResponseDetails(hResponse.getJSONObject("repo", new JSONObject()));
        payload = new Payload(hResponse.getJSONObject("payload", new JSONObject()));
        isPublic = hResponse.getBoolean("public");
        createdAt = hResponse.getString("created_at");
        org = new EventEntity(hResponse.getJSONObject("org", new JSONObject()));
    }

    /**
     * Method to get {@link #id} instance <br>
     * Any params required
     *
     * @return {@link #id} instance as long
     **/
    public long getId() {
        return id;
    }

    /**
     * Method to get {@link #type} instance <br>
     * Any params required
     *
     * @return {@link #type} instance as {@link EventType}
     **/
    public EventType getType() {
        return type;
    }

    /**
     * Method to get {@link #actor} instance <br>
     * Any params required
     *
     * @return {@link #actor} instance as {@link EventEntity}
     **/
    public EventEntity getActor() {
        return actor;
    }

    /**
     * Method to get {@link #repo} instance <br>
     * Any params required
     *
     * @return {@link #repo} instance as {@link BaseResponseDetails}
     **/
    public BaseResponseDetails getRepo() {
        return repo;
    }

    /**
     * Method to get {@link #payload} instance <br>
     * Any params required
     *
     * @return {@link #payload} instance as {@link Payload}
     **/
    public Payload getPayload() {
        return payload;
    }

    /**
     * Method to get {@link #isPublic} instance <br>
     * Any params required
     *
     * @return {@link #isPublic} instance as boolean
     **/
    public boolean isPublic() {
        return isPublic;
    }

    /**
     * Method to get {@link #createdAt} instance <br>
     * Any params required
     *
     * @return {@link #createdAt} instance as {@link String}
     **/
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Method to get {@link #createdAt} timestamp <br>
     * Any params required
     *
     * @return {@link #createdAt} timestamp as long
     **/
    public long getCreatedAtTimestamp() {
        return getDateTimestamp(createdAt);
    }

    /**
     * Method to get {@link #org} instance <br>
     * Any params required
     *
     * @return {@link #org} instance as {@link EventEntity}
     **/
    public EventEntity getOrg() {
        return org;
    }

    /**
     * {@code EventType} list of available event types
     **/
    public enum EventType {

        /**
         * {@code "WatchEvent"} event type
         **/
        WatchEvent,

        /**
         * {@code "PushEvent"} event type
         **/
        PushEvent,

        /**
         * {@code "ReleaseEvent"} event type
         **/
        ReleaseEvent,

        /**
         * {@code "CreateEvent"} event type
         **/
        CreateEvent,

        /**
         * {@code "PullRequestEvent"} event type
         **/
        PullRequestEvent,

        /**
         * {@code "DeleteEvent"} event type
         **/
        DeleteEvent,

        /**
         * {@code "IssuesEvent"} event type
         **/
        IssuesEvent,

        /**
         * {@code "ForkEvent"} event type
         **/
        ForkEvent,

        /**
         * {@code "PullRequestReviewEvent"} event type
         **/
        PullRequestReviewEvent,

        /**
         * {@code "CommitCommentEvent"} event type
         **/
        CommitCommentEvent,

        /**
         * {@code "PullRequestReviewCommentEvent"} event type
         **/
        PullRequestReviewCommentEvent,

        /**
         * {@code "IssueCommentEvent"} event type
         **/
        IssueCommentEvent,

        /**
         * {@code "PublicEvent"} event type
         **/
        PublicEvent,

        /**
         * {@code "MemberEvent"} event type
         **/
        MemberEvent

    }

    /**
     * The {@code EventEntity} class is useful to format a GitHub's entity for an event
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class EventEntity extends InnerClassItem {

        /**
         * {@code id} identifier value
         **/
        private final long id;

        /**
         * {@code login} login value
         **/
        private final String login;

        /**
         * {@code displayLogin} display login value
         **/
        private final String displayLogin;

        /**
         * {@code gravatarId} gravatar identifier value
         **/
        private final String gravatarId;

        /**
         * {@code url} url value
         **/
        private final String url;

        /**
         * {@code avatarUrl} avatar url value
         **/
        private final String avatarUrl;

        /**
         * Constructor to init a {@link EventEntity}
         *
         * @param id:           identifier value
         * @param login:        login value
         * @param displayLogin: display login value
         * @param gravatarId:   gravatar identifier value
         * @param url:          url value
         * @param avatarUrl:    avatar url value
         **/
        public EventEntity(long id, String login, String displayLogin, String gravatarId, String url, String avatarUrl) {
            super(null);
            this.id = id;
            this.login = login;
            this.displayLogin = displayLogin;
            this.gravatarId = gravatarId;
            this.url = url;
            this.avatarUrl = avatarUrl;
        }

        /**
         * Constructor to init a {@link EventEntity}
         *
         * @param jEntity: entity details as {@link JSONObject}
         **/
        public EventEntity(JSONObject jEntity) {
            super(jEntity);
            id = hItem.getLong("id", -1);
            login = hItem.getString("login");
            displayLogin = hItem.getString("display_login");
            gravatarId = hItem.getString("gravatar_id");
            url = hItem.getString("url");
            avatarUrl = hItem.getString("avatar_url");
        }

        /**
         * Method to get {@link #id} instance <br>
         * Any params required
         *
         * @return {@link #id} instance as long
         **/
        public long getId() {
            return id;
        }

        /**
         * Method to get {@link #login} instance <br>
         * Any params required
         *
         * @return {@link #login} instance as {@link String}
         **/
        public String getLogin() {
            return login;
        }

        /**
         * Method to get {@link #displayLogin} instance <br>
         * Any params required
         *
         * @return {@link #displayLogin} instance as {@link String}
         **/
        public String getDisplayLogin() {
            return displayLogin;
        }

        /**
         * Method to get {@link #gravatarId} instance <br>
         * Any params required
         *
         * @return {@link #gravatarId} instance as {@link String}
         **/
        public String getGravatarId() {
            return gravatarId;
        }

        /**
         * Method to get {@link #url} instance <br>
         * Any params required
         *
         * @return {@link #url} instance as {@link String}
         **/
        public String getUrl() {
            return url;
        }

        /**
         * Method to get {@link #avatarUrl} instance <br>
         * Any params required
         *
         * @return {@link #avatarUrl} instance as {@link String}
         **/
        public String getAvatarUrl() {
            return avatarUrl;
        }

    }

    /**
     * The {@code Payload} class is useful to format a GitHub's payload for an event
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class Payload extends InnerClassItem {

        /**
         * {@code pushId} push identifier value
         **/
        private final long pushId;

        /**
         * {@code size} size value
         **/
        private final int size;

        /**
         * {@code distinctSize} distinct size value
         **/
        private final int distinctSize;

        /**
         * {@code ref} reference value
         **/
        private final String ref;

        /**
         * {@code head} head value
         **/
        private final String head;

        /**
         * {@code before} before value
         **/
        private final String before;

        /**
         * {@code id} list of {@link Commit}
         **/
        private final ArrayList<Commit> commits;

        /**
         * {@code action} action value
         **/
        private final String action;

        /**
         * {@code refType} reference type value
         **/
        private final String refType;

        /**
         * {@code masterBranch} master branch value
         **/
        private final String masterBranch;

        /**
         * {@code description} description value
         **/
        private final String description;

        /**
         * {@code pusherType} pusher type
         **/
        private final String pusherType;

        /**
         * Constructor to init a {@link Payload}
         *
         * @param action: action value
         **/
        public Payload(String action) {
            super(null);
            this.action = action;
            pushId = -1;
            size = -1;
            distinctSize = -1;
            ref = null;
            head = null;
            before = null;
            commits = null;
            refType = null;
            masterBranch = null;
            description = null;
            pusherType = null;
        }

        /**
         * Constructor to init a {@link Payload}
         *
         * @param ref:          reference value
         * @param refType:      reference type value
         * @param masterBranch: master branch value
         * @param description:  description value
         * @param pusherType:   pusher type
         **/
        public Payload(String ref, String refType, String masterBranch, String description, String pusherType) {
            super(null);
            this.ref = ref;
            this.refType = refType;
            this.masterBranch = masterBranch;
            this.description = description;
            this.pusherType = pusherType;
            pushId = -1;
            size = -1;
            distinctSize = -1;
            head = null;
            before = null;
            commits = null;
            action = null;
        }

        /**
         * Constructor to init a {@link Payload}
         *
         * @param pushId:       push identifier
         * @param size:         size value
         * @param distinctSize: distinct size value
         * @param ref:          reference value
         * @param head:         head value
         * @param before:       before value
         * @param commits:      list of {@link Commit}
         **/
        public Payload(long pushId, int size, int distinctSize, String ref, String head, String before,
                       ArrayList<Commit> commits) {
            super(null);
            this.pushId = pushId;
            this.size = size;
            this.distinctSize = distinctSize;
            this.ref = ref;
            this.head = head;
            this.before = before;
            this.commits = commits;
            action = null;
            refType = null;
            masterBranch = null;
            description = null;
            pusherType = null;
        }

        /**
         * Constructor to init a {@link Payload}
         *
         * @param jPayload: payload details {@link JSONObject}
         **/
        public Payload(JSONObject jPayload) {
            super(jPayload);
            action = hItem.getString("action");
            pushId = hItem.getLong("push_id");
            size = hItem.getInt("size");
            distinctSize = hItem.getInt("distinct_size");
            ref = hItem.getString("ref");
            head = hItem.getString("head");
            before = hItem.getString("before");
            commits = new ArrayList<>();
            JSONArray hCommits = hItem.getJSONArray("commits", new JSONArray());
            for (int j = 0; j < hCommits.length(); j++)
                commits.add(new Commit(hCommits.getJSONObject(j)));
            refType = hItem.getString("ref_type");
            masterBranch = hItem.getString("master_branch");
            description = hItem.getString("description");
            pusherType = hItem.getString("pusher_type");
        }

        /**
         * Method to get {@link #pushId} instance <br>
         * Any params required
         *
         * @return {@link #pushId} instance as long
         **/
        public long getPushId() {
            return pushId;
        }

        /**
         * Method to get {@link #size} instance <br>
         * Any params required
         *
         * @return {@link #size} instance as int
         **/
        public int getSize() {
            return size;
        }

        /**
         * Method to get {@link #distinctSize} instance <br>
         * Any params required
         *
         * @return {@link #distinctSize} instance as int
         **/
        public int getDistinctSize() {
            return distinctSize;
        }

        /**
         * Method to get {@link #ref} instance <br>
         * Any params required
         *
         * @return {@link #ref} instance as {@link String}
         **/
        public String getRef() {
            return ref;
        }

        /**
         * Method to get {@link #head} instance <br>
         * Any params required
         *
         * @return {@link #head} instance as {@link String}
         **/
        public String getHead() {
            return head;
        }

        /**
         * Method to get {@link #before} instance <br>
         * Any params required
         *
         * @return {@link #before} instance as {@link String}
         **/
        public String getBefore() {
            return before;
        }

        /**
         * Method to get {@link #commits} instance <br>
         * Any params required
         *
         * @return {@link #commits} instance as {@link Collection} of {@link Commit}
         **/
        public Collection<Commit> getCommits() {
            return commits;
        }

        /**
         * Method to get {@link #action} instance <br>
         * Any params required
         *
         * @return {@link #action} instance as {@link String}
         **/
        public String getAction() {
            return action;
        }

        /**
         * Method to get {@link #refType} instance <br>
         * Any params required
         *
         * @return {@link #refType} instance as {@link String}
         **/
        public String getRefType() {
            return refType;
        }

        /**
         * Method to get {@link #masterBranch} instance <br>
         * Any params required
         *
         * @return {@link #masterBranch} instance as {@link String}
         **/
        public String getMasterBranch() {
            return masterBranch;
        }

        /**
         * Method to get {@link #description} instance <br>
         * Any params required
         *
         * @return {@link #description} instance as {@link String}
         **/
        public String getDescription() {
            return description;
        }

        /**
         * Method to get {@link #pusherType} instance <br>
         * Any params required
         *
         * @return {@link #pusherType} instance as {@link String}
         **/
        public String getPusherType() {
            return pusherType;
        }

        /**
         * The {@code Commit} class is useful to format a GitHub's commit for an event
         *
         * @author N7ghtm4r3 - Tecknobit
         **/
        public static class Commit {

            /**
             * {@code sha} value
             **/
            private final String sha;

            /**
             * {@code author} value
             **/
            private final CommitProfile author;

            /**
             * {@code message} value
             **/
            private final String message;

            /**
             * {@code distinct} whether is distinct
             **/
            private final boolean distinct;

            /**
             * {@code url} value
             **/
            private final String url;

            /**
             * Constructor to init a {@link Payload}
             *
             * @param sha:      sha identifier
             * @param author:   author value
             * @param message:  message value
             * @param distinct: whether is distinct value
             * @param url:      url value
             **/
            public Commit(String sha, CommitProfile author, String message, boolean distinct, String url) {
                this.sha = sha;
                this.author = author;
                this.message = message;
                this.distinct = distinct;
                this.url = url;
            }

            /**
             * Constructor to init a {@link Payload}
             *
             * @param jCommit: commit details as {@link JSONObject}
             **/
            public Commit(JSONObject jCommit) {
                JsonHelper hCommit = new JsonHelper(jCommit);
                sha = hCommit.getString("sha");
                author = new CommitProfile(hCommit.getJSONObject("author", new JSONObject()));
                message = hCommit.getString("message");
                distinct = hCommit.getBoolean("distinct");
                url = hCommit.getString("url");
            }

            /**
             * Method to get {@link #sha} instance <br>
             * Any params required
             *
             * @return {@link #sha} instance as {@link String}
             **/
            public String getSha() {
                return sha;
            }

            /**
             * Method to get {@link #author} instance <br>
             * Any params required
             *
             * @return {@link #author} instance as {@link CommitProfile}
             **/
            public CommitProfile getAuthor() {
                return author;
            }

            /**
             * Method to get {@link #message} instance <br>
             * Any params required
             *
             * @return {@link #message} instance as {@link String}
             **/
            public String getMessage() {
                return message;
            }

            /**
             * Method to get {@link #distinct} instance <br>
             * Any params required
             *
             * @return {@link #distinct} instance as boolean
             **/
            public boolean isDistinct() {
                return distinct;
            }

            /**
             * Method to get {@link #url} instance <br>
             * Any params required
             *
             * @return {@link #url} instance as {@link String}
             **/
            public String getUrl() {
                return url;
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

}
