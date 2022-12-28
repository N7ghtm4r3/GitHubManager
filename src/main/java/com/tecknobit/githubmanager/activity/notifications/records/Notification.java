package com.tecknobit.githubmanager.activity.notifications.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONObject;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code Notification} class is useful to format a GitHub's notification
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/activity/notifications#list-notifications-for-the-authenticated-user">
 *             List notifications for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/activity/notifications#get-a-thread">
 *             Get a thread</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/activity/notifications#list-repository-notifications-for-the-authenticated-user">
 *             List repository notifications for the authenticated user</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class Notification extends GitHubResponse {

    /**
     * {@code id} id of the notification
     **/
    private final long id;

    /**
     * {@code repository} repository of the notification
     **/
    private final Repository repository;

    /**
     * {@code subject} subject of the notification
     **/
    private final Subject subject;

    /**
     * {@code reason} reason of the notification
     **/
    private final String reason;

    /**
     * {@code unread} whether the notification has been marked as unread
     **/
    private final boolean unread;

    /**
     * {@code updatedAt} updated at value
     **/
    private final String updatedAt;

    /**
     * {@code lastReadAt} last read at value
     **/
    private final String lastReadAt;

    /**
     * {@code url} value
     **/
    private final String url;

    /**
     * {@code subscriptionUrl} subscription url value
     **/
    private final String subscriptionUrl;

    /**
     * Constructor to init a {@link Notification}
     *
     * @param id:              id of the notification
     * @param repository:      repository of the notification
     * @param subject:         subject of the notification
     * @param reason:          reason of the notification
     * @param unread:          whether the notification has been marked as unread
     * @param updatedAt:       updated at value
     * @param lastReadAt:      last read at value
     * @param url:             url value
     * @param subscriptionUrl: subscription url value
     **/
    public Notification(long id, Repository repository, Subject subject, String reason, boolean unread, String updatedAt,
                        String lastReadAt, String url, String subscriptionUrl) {
        super(null);
        this.id = id;
        this.repository = repository;
        this.subject = subject;
        this.reason = reason;
        this.unread = unread;
        this.updatedAt = updatedAt;
        this.lastReadAt = lastReadAt;
        this.url = url;
        this.subscriptionUrl = subscriptionUrl;
    }

    /**
     * Constructor to init a {@link Notification}
     *
     * @param jNotification: notification details as {@link JSONObject}
     **/
    public Notification(JSONObject jNotification) {
        super(jNotification);
        id = hResponse.getLong("id", 0);
        repository = new Repository(hResponse.getJSONObject("repository", new JSONObject()));
        subject = new Subject(hResponse.getJSONObject("subject", new JSONObject()));
        reason = hResponse.getString("reason");
        unread = hResponse.getBoolean("unread");
        updatedAt = hResponse.getString("updated_at");
        lastReadAt = hResponse.getString("last_read_at");
        url = hResponse.getString("url");
        subscriptionUrl = hResponse.getString("subscription_url");
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
     * Method to get {@link #repository} instance <br>
     * Any params required
     *
     * @return {@link #repository} instance as {@link Repository}
     **/
    public Repository getRepository() {
        return repository;
    }

    /**
     * Method to get {@link #subject} instance <br>
     * Any params required
     *
     * @return {@link #subject} instance as {@link Subject}
     **/
    public Subject getSubject() {
        return subject;
    }

    /**
     * Method to get {@link #reason} instance <br>
     * Any params required
     *
     * @return {@link #reason} instance as {@link String}
     **/
    public String getReason() {
        return reason;
    }

    /**
     * Method to get {@link #unread} instance <br>
     * Any params required
     *
     * @return {@link #unread} instance as boolean
     **/
    public boolean isUnread() {
        return unread;
    }

    /**
     * Method to get {@link #updatedAt} instance <br>
     * Any params required
     *
     * @return {@link #updatedAt} instance as {@link String}
     **/
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Method to get {@link #updatedAt} timestamp <br>
     * Any params required
     *
     * @return {@link #updatedAt} timestamp as long
     **/
    public long getUpdatedAtTimestamp() {
        return getDateTimestamp(updatedAt);
    }

    /**
     * Method to get {@link #lastReadAt} instance <br>
     * Any params required
     *
     * @return {@link #lastReadAt} instance as {@link String}
     **/
    public String getLastReadAt() {
        return lastReadAt;
    }

    /**
     * Method to get {@link #lastReadAt} timestamp <br>
     * Any params required
     *
     * @return {@link #lastReadAt} timestamp as long
     **/
    public long getLastReadAtTimestamp() {
        return getDateTimestamp(lastReadAt);
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
     * Method to get {@link #subscriptionUrl} instance <br>
     * Any params required
     *
     * @return {@link #subscriptionUrl} instance as {@link String}
     **/
    public String getSubscriptionUrl() {
        return subscriptionUrl;
    }

    /**
     * The {@code Subject} class is useful to format a GitHub's subject for a {@link Notification}
     *
     * @author N7ghtm4r3 - Tecknobit
     **/
    public static class Subject {

        /**
         * {@code title} title of the subject
         **/
        private final String title;

        /**
         * {@code url} url value
         **/
        private final String url;

        /**
         * {@code latestCommentUrl} latest comment url value
         **/
        private final String latestCommentUrl;

        /**
         * {@code type} type of the notification
         **/
        private final String type;

        /**
         * Constructor to init a {@link Subject}
         *
         * @param title:            title of the subject
         * @param url:              url value
         * @param latestCommentUrl: latest comment url value
         * @param type:             type of the notification
         **/
        public Subject(String title, String url, String latestCommentUrl, String type) {
            this.title = title;
            this.url = url;
            this.latestCommentUrl = latestCommentUrl;
            this.type = type;
        }

        /**
         * Constructor to init a {@link Subject}
         *
         * @param jSubject: subject details as {@link JSONObject}
         **/
        public Subject(JSONObject jSubject) {
            this(jSubject.getString("title"), jSubject.getString("url"), jSubject.getString("latest_comment_url"),
                    jSubject.getString("type"));
        }

        /**
         * Method to get {@link #title} instance <br>
         * Any params required
         *
         * @return {@link #title} instance as {@link String}
         **/
        public String getTitle() {
            return title;
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
         * Method to get {@link #latestCommentUrl} instance <br>
         * Any params required
         *
         * @return {@link #latestCommentUrl} instance as {@link String}
         **/
        public String getLatestCommentUrl() {
            return latestCommentUrl;
        }

        /**
         * Method to get {@link #type} instance <br>
         * Any params required
         *
         * @return {@link #type} instance as {@link String}
         **/
        public String getType() {
            return type;
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
