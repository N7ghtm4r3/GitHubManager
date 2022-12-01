package com.tecknobit.githubmanager.activity.notifications;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.activity.notifications.records.Notification;
import com.tecknobit.githubmanager.activity.notifications.records.Subscription;
import com.tecknobit.githubmanager.records.repository.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GitHubNotificationsManager} class is useful to manage all GitHub's notifications endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/activity/notifications">
 * Notifications</a>
 * @see GitHubManager
 **/
public class GitHubNotificationsManager extends GitHubManager {

    /**
     * {@code NOTIFICATIONS_PATH} constant for {@code "notifications"} path
     **/
    public static final String NOTIFICATIONS_PATH = "notifications";

    /**
     * {@code NOTIFICATIONS_THREADS_PATH} constant for {@code "notifications/threads/"} path
     **/
    public static final String NOTIFICATIONS_THREADS_PATH = NOTIFICATIONS_PATH + "/threads/";

    /**
     * {@code SUBSCRIPTION_PATH} constant for {@code "/subscription"} path
     **/
    public static final String SUBSCRIPTION_PATH = "/subscription";

    /**
     * Constructor to init a {@link GitHubNotificationsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubNotificationsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubNotificationsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubNotificationsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubNotificationsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubNotificationsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubNotificationsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubNotificationsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubNotificationsManager} <br>
     * Any params required
     *
     * @throws IllegalArgumentException when a parameterized constructor has not been called before this constructor
     * @apiNote this constructor is useful to instantiate a new {@link GitHubManager}'s manager without re-insert
     * the credentials and is useful in those cases if you need to use different manager at the same time:
     * <pre>
     *     {@code
     *        //You need to insert all credentials requested
     *        GitHubManager firstManager = new GitHubManager("accessToken");
     *        //You don't need to insert all credentials to make manager work
     *        GitHubManager secondManager = new GitHubManager(); //same credentials used
     *     }
     * </pre>
     **/
    public GitHubNotificationsManager() {
        super();
    }

    /**
     * Method to get all notifications list for the current user, sorted by most recently updated <br>
     * Any params required
     *
     * @return notifications list as {@link Collection} of {@link Notification} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#list-notifications-for-the-authenticated-user">
     * List notifications for the authenticated user</a>
     **/
    @RequestPath(path = "/notifications")
    public Collection<Notification> getAuthenticatedUserNotifications() throws IOException {
        return getAuthenticatedUserNotifications(LIBRARY_OBJECT);
    }

    /**
     * Method to get all notifications list for the current user, sorted by most recently updated
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return notifications list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#list-notifications-for-the-authenticated-user">
     * List notifications for the authenticated user</a>
     **/
    @RequestPath(path = "/notifications")
    public <T> T getAuthenticatedUserNotifications(ReturnFormat format) throws IOException {
        return returnNotificationsList(sendGetRequest(NOTIFICATIONS_PATH), format);
    }

    /**
     * Method to get all notifications list for the current user, sorted by most recently updated
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "all"} -> if true, show notifications marked as read - [boolean, default false]
     *                        </li>
     *                        <li>
     *                            {@code "participating"} -> if true, only shows notifications in which the user is
     *                            directly participating or mentioned - [boolean, default false]
     *                        </li>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> only show notifications updated before the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return notifications list as {@link Collection} of {@link Notification} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#list-notifications-for-the-authenticated-user">
     * List notifications for the authenticated user</a>
     **/
    @RequestPath(path = "/notifications")
    public Collection<Notification> getAuthenticatedUserNotifications(Params queryParams) throws IOException {
        return getAuthenticatedUserNotifications(queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get all notifications list for the current user, sorted by most recently updated
     *
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "all"} -> if true, show notifications marked as read - [boolean, default false]
     *                        </li>
     *                        <li>
     *                            {@code "participating"} -> if true, only shows notifications in which the user is
     *                            directly participating or mentioned - [boolean, default false]
     *                        </li>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> only show notifications updated before the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return notifications list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#list-notifications-for-the-authenticated-user">
     * List notifications for the authenticated user</a>
     **/
    @RequestPath(path = "/notifications")
    public <T> T getAuthenticatedUserNotifications(Params queryParams, ReturnFormat format) throws IOException {
        return returnNotificationsList(sendGetRequest(NOTIFICATIONS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to mark all notifications as "read" for the current user. If the number of notifications is too large to
     * complete in one request, you will receive a 202 Accepted status and GitHub will run an asynchronous process to mark
     * notifications as "read." To check whether any "unread" notifications remain, you can use the List notifications
     * for the authenticated user endpoint and pass the query parameter all=false <br>
     * Any params required
     *
     * @return message status as {@link String}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#mark-notifications-as-read">
     * Mark notifications as read</a>
     **/
    @RequestPath(path = "/notifications")
    public String markNotificationsAsRead() throws IOException {
        return markNotificationsAsRead(LIBRARY_OBJECT);
    }

    /**
     * Method to mark all notifications as "read" for the current user. If the number of notifications is too large to
     * complete in one request, you will receive a 202 Accepted status and GitHub will run an asynchronous process to mark
     * notifications as "read." To check whether any "unread" notifications remain, you can use the List notifications
     * for the authenticated user endpoint and pass the query parameter all=false
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return message status as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#mark-notifications-as-read">
     * Mark notifications as read</a>
     **/
    @RequestPath(path = "/notifications")
    public <T> T markNotificationsAsRead(ReturnFormat format) throws IOException {
        return returnMessage(sendPutRequest(NOTIFICATIONS_PATH, null), format);
    }

    /**
     * Method to mark all notifications as "read" for the current user. If the number of notifications is too large to
     * complete in one request, you will receive a 202 Accepted status and GitHub will run an asynchronous process to mark
     * notifications as "read." To check whether any "unread" notifications remain, you can use the List notifications
     * for the authenticated user endpoint and pass the query parameter all=false
     *
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "last_read_at"} -> describes the last point that notifications were checked.
     *                           Anything updated since this time will not be marked as read.
     *                           If you omit this parameter, all notifications are marked as read.
     *                           This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ
     *                           - [string, default current timestamp]
     *                       </li>
     *                       <li>
     *                           {@code "read"} -> whether the notification has been read - [boolean]
     *                       </li>
     *                    </ul>
     * @return message status as {@link String}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#mark-notifications-as-read">
     * Mark notifications as read</a>
     **/
    @RequestPath(path = "/notifications")
    public String markNotificationsAsRead(Params bodyParams) throws IOException {
        return markNotificationsAsRead(bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to mark all notifications as "read" for the current user. If the number of notifications is too large to
     * complete in one request, you will receive a 202 Accepted status and GitHub will run an asynchronous process to mark
     * notifications as "read." To check whether any "unread" notifications remain, you can use the List notifications
     * for the authenticated user endpoint and pass the query parameter all=false
     *
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "last_read_at"} -> describes the last point that notifications were checked.
     *                           Anything updated since this time will not be marked as read.
     *                           If you omit this parameter, all notifications are marked as read.
     *                           This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ
     *                           - [string, default current timestamp]
     *                       </li>
     *                       <li>
     *                           {@code "read"} -> whether the notification has been read - [boolean]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return message status as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#mark-notifications-as-read">
     * Mark notifications as read</a>
     **/
    @RequestPath(path = "/notifications")
    public <T> T markNotificationsAsRead(Params bodyParams, ReturnFormat format) throws IOException {
        return returnMessage(sendPutRequest(NOTIFICATIONS_PATH, bodyParams), format);
    }

    /**
     * Method to get information about a notification thread
     *
     * @param threadId: the unique identifier of the notification thread
     * @return thread as {@link Notification} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#get-a-thread">
     * Get a thread</a>
     **/
    @RequestPath(path = "/notifications/threads/{thread_id}")
    public Notification getThread(long threadId) throws IOException {
        return getThread(threadId, LIBRARY_OBJECT);
    }

    /**
     * Method to get information about a notification thread
     *
     * @param threadId: the unique identifier of the notification thread
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return thread as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#get-a-thread">
     * Get a thread</a>
     **/
    @Returner
    @RequestPath(path = "/notifications/threads/{thread_id}")
    public <T> T getThread(long threadId, ReturnFormat format) throws IOException {
        String threadResponse = sendGetRequest(NOTIFICATIONS_THREADS_PATH + threadId);
        switch (format) {
            case JSON:
                return (T) new JSONObject(threadResponse);
            case LIBRARY_OBJECT:
                return (T) new Notification(new JSONObject(threadResponse));
            default:
                return (T) threadResponse;
        }
    }

    /**
     * Method to mark a thread as "read." <br>
     * Marking a thread as "read" is equivalent to clicking
     * a notification in your notification inbox on <a href="https://github.com/notifications">GitHub</a>
     *
     * @param thread: thread to mark as read
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#mark-a-thread-as-read">
     * Mark a thread as read</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/notifications/threads/{thread_id}")
    public boolean markThreadAsRead(Notification thread) {
        return markThreadAsRead(thread.getId());
    }

    /**
     * Method to mark a thread as "read." <br>
     * Marking a thread as "read" is equivalent to clicking
     * a notification in your notification inbox on <a href="https://github.com/notifications">GitHub</a>
     *
     * @param threadId: the unique identifier of the notification thread
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#mark-a-thread-as-read">
     * Mark a thread as read</a>
     **/
    @RequestPath(path = "/notifications/threads/{thread_id}")
    public boolean markThreadAsRead(long threadId) {
        try {
            sendPatchRequest(NOTIFICATIONS_THREADS_PATH + threadId, null);
            if (apiRequest.getResponseStatusCode() != 205) {
                printErrorResponse();
                return false;
            }
            return true;
        } catch (IOException e) {
            printErrorResponse();
            return false;
        }
    }

    /**
     * Method to check to see if the current user is subscribed to a thread. You can also get a repository subscription.
     * Note that subscriptions are only generated if a user is participating in a conversation--for example,
     * they've replied to the thread, were @mentioned, or manually subscribe to a thread
     *
     * @param thread: thread from fetch the subscription
     * @return subscription as {@link Subscription} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#get-a-thread-subscription-for-the-authenticated-user">
     * Get a thread subscription for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/notifications/threads/{thread_id}/subscription")
    public Subscription getAuthenticatedUserThreadSubscription(Notification thread) throws IOException {
        return getAuthenticatedUserThreadSubscription(thread.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to check to see if the current user is subscribed to a thread. You can also get a repository subscription.
     * Note that subscriptions are only generated if a user is participating in a conversation--for example,
     * they've replied to the thread, were @mentioned, or manually subscribe to a thread
     *
     * @param thread: thread from fetch the subscription
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return subscription as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#get-a-thread-subscription-for-the-authenticated-user">
     * Get a thread subscription for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/notifications/threads/{thread_id}/subscription")
    public <T> T getAuthenticatedUserThreadSubscription(Notification thread, ReturnFormat format) throws IOException {
        return getAuthenticatedUserThreadSubscription(thread.getId(), format);
    }

    /**
     * Method to check to see if the current user is subscribed to a thread. You can also get a repository subscription.
     * Note that subscriptions are only generated if a user is participating in a conversation--for example,
     * they've replied to the thread, were @mentioned, or manually subscribe to a thread
     *
     * @param threadId: the unique identifier of the notification thread
     * @return subscription as {@link Subscription} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#get-a-thread-subscription-for-the-authenticated-user">
     * Get a thread subscription for the authenticated user</a>
     **/
    @RequestPath(path = "/notifications/threads/{thread_id}/subscription")
    public Subscription getAuthenticatedUserThreadSubscription(long threadId) throws IOException {
        return getAuthenticatedUserThreadSubscription(threadId, LIBRARY_OBJECT);
    }

    /**
     * Method to check to see if the current user is subscribed to a thread. You can also get a repository subscription.
     * Note that subscriptions are only generated if a user is participating in a conversation--for example,
     * they've replied to the thread, were @mentioned, or manually subscribe to a thread
     *
     * @param threadId: the unique identifier of the notification thread
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return subscription as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#get-a-thread-subscription-for-the-authenticated-user">
     * Get a thread subscription for the authenticated user</a>
     **/
    @RequestPath(path = "/notifications/threads/{thread_id}/subscription")
    public <T> T getAuthenticatedUserThreadSubscription(long threadId, ReturnFormat format) throws IOException {
        return returnSubscription(sendGetRequest(NOTIFICATIONS_THREADS_PATH + threadId + SUBSCRIPTION_PATH),
                format);
    }

    /**
     * This method is useful when you are watching a repository, you receive notifications for all threads by default.
     * Use this endpoint to ignore future notifications for threads until you comment on the thread or get a @mention.
     * You can also use this endpoint to subscribe to threads that you are currently not receiving notifications for or
     * to subscribed to threads that you have previously ignored.
     * Unsubscribing from a conversation in a repository that you are not watching is functionally equivalent
     * to use the {@link #deleteThreadSubscription(Notification)} method
     *
     * @param thread:  thread to set the subscription
     * @param ignored: whether to block all notifications from a thread
     * @return subscription as {@link Subscription} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#set-a-thread-subscription">
     * Set a thread subscription</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/notifications/threads/{thread_id}/subscription")
    public Subscription setThreadSubscription(Notification thread, boolean ignored) throws IOException {
        return setThreadSubscription(thread.getId(), ignored, LIBRARY_OBJECT);
    }

    /**
     * This method is useful when you are watching a repository, you receive notifications for all threads by default.
     * Use this endpoint to ignore future notifications for threads until you comment on the thread or get a @mention.
     * You can also use this endpoint to subscribe to threads that you are currently not receiving notifications for or
     * to subscribed to threads that you have previously ignored.
     * Unsubscribing from a conversation in a repository that you are not watching is functionally equivalent
     * to use the {@link #deleteThreadSubscription(Notification)} method
     *
     * @param thread:  thread to set the subscription
     * @param ignored: whether to block all notifications from a thread
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return subscription as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#set-a-thread-subscription">
     * Set a thread subscription</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/notifications/threads/{thread_id}/subscription")
    public <T> T setThreadSubscription(Notification thread, boolean ignored, ReturnFormat format) throws IOException {
        return setThreadSubscription(thread.getId(), ignored, format);
    }

    /**
     * This method is useful when you are watching a repository, you receive notifications for all threads by default.
     * Use this endpoint to ignore future notifications for threads until you comment on the thread or get a @mention.
     * You can also use this endpoint to subscribe to threads that you are currently not receiving notifications for or
     * to subscribed to threads that you have previously ignored.
     * Unsubscribing from a conversation in a repository that you are not watching is functionally equivalent
     * to use the {@link #deleteThreadSubscription(long)} method
     *
     * @param threadId: the unique identifier of the notification thread
     * @param ignored:  whether to block all notifications from a thread
     * @return subscription as {@link Subscription} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#set-a-thread-subscription">
     * Set a thread subscription</a>
     **/
    @RequestPath(path = "/notifications/threads/{thread_id}/subscription")
    public Subscription setThreadSubscription(long threadId, boolean ignored) throws IOException {
        return setThreadSubscription(threadId, ignored, LIBRARY_OBJECT);
    }

    /**
     * This method is useful when you are watching a repository, you receive notifications for all threads by default.
     * Use this endpoint to ignore future notifications for threads until you comment on the thread or get a @mention.
     * You can also use this endpoint to subscribe to threads that you are currently not receiving notifications for or
     * to subscribed to threads that you have previously ignored.
     * Unsubscribing from a conversation in a repository that you are not watching is functionally equivalent
     * to use the {@link #deleteThreadSubscription(long)} method
     *
     * @param threadId: the unique identifier of the notification thread
     * @param ignored:  whether to block all notifications from a thread
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return subscription as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#set-a-thread-subscription">
     * Set a thread subscription</a>
     **/
    @RequestPath(path = "/notifications/threads/{thread_id}/subscription")
    public <T> T setThreadSubscription(long threadId, boolean ignored, ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("ignored", ignored);
        return returnSubscription(sendPutRequest(NOTIFICATIONS_THREADS_PATH + threadId + SUBSCRIPTION_PATH,
                payload), format);
    }

    /**
     * Method to create a subscription object
     *
     * @param subscriptionResponse: obtained from GitHub's response
     * @param format:               return type formatter -> {@link ReturnFormat}
     * @return subscription as {@code "format"} defines
     **/
    @Returner
    private <T> T returnSubscription(String subscriptionResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(subscriptionResponse);
            case LIBRARY_OBJECT:
                return (T) new Subscription(new JSONObject(subscriptionResponse));
            default:
                return (T) subscriptionResponse;
        }
    }

    /**
     * Method to mute all future notifications for a conversation until you comment on the thread or get a @mention.
     * If you are watching the repository of the thread, you will still receive notifications.
     * To ignore future notifications for a repository you are watching, use the {@link #setThreadSubscription(Notification, boolean)}
     * and set ignore to true
     *
     * @param thread: thread to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#delete-a-thread-subscription">
     * Delete a thread subscription</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/notifications/threads/{thread_id}/subscription")
    public boolean deleteThreadSubscription(Notification thread) {
        return deleteThreadSubscription(thread.getId());
    }

    /**
     * Method to mute all future notifications for a conversation until you comment on the thread or get a @mention.
     * If you are watching the repository of the thread, you will still receive notifications.
     * To ignore future notifications for a repository you are watching, use the {@link #setThreadSubscription(long, boolean)}
     * and set ignore to true
     *
     * @param threadId: the unique identifier of the notification thread
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#delete-a-thread-subscription">
     * Delete a thread subscription</a>
     **/
    @RequestPath(path = "/notifications/threads/{thread_id}/subscription")
    public boolean deleteThreadSubscription(long threadId) {
        try {
            sendDeleteRequest(NOTIFICATIONS_THREADS_PATH + threadId + SUBSCRIPTION_PATH);
            if (apiRequest.getResponseStatusCode() != 204) {
                printErrorResponse();
                return false;
            }
            return true;
        } catch (IOException e) {
            printErrorResponse();
            return false;
        }
    }

    /**
     * Method to get all notifications list for the current user in the specified repository
     *
     * @param repository: repository from fetch the notifications
     * @return notifications list as {@link Collection} of {@link Notification} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#list-repository-notifications-for-the-authenticated-user">
     * List repository notifications for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/notifications")
    public Collection<Notification> getRepositoryNotifications(Repository repository) throws IOException {
        return getRepositoryNotifications(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to get all notifications list for the current user in the specified repository
     *
     * @param repository: repository from fetch the notifications
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return notifications list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#list-repository-notifications-for-the-authenticated-user">
     * List repository notifications for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/notifications")
    public <T> T getRepositoryNotifications(Repository repository, ReturnFormat format) throws IOException {
        return getRepositoryNotifications(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to get all notifications list for the current user in the specified repository
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return notifications list as {@link Collection} of {@link Notification} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#list-repository-notifications-for-the-authenticated-user">
     * List repository notifications for the authenticated user</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/notifications")
    public Collection<Notification> getRepositoryNotifications(String owner, String repo) throws IOException {
        return getRepositoryNotifications(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to get all notifications list for the current user in the specified repository
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return notifications list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#list-repository-notifications-for-the-authenticated-user">
     * List repository notifications for the authenticated user</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/notifications")
    public <T> T getRepositoryNotifications(String owner, String repo, ReturnFormat format) throws IOException {
        return returnNotificationsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + "/" + NOTIFICATIONS_PATH),
                format);
    }

    /**
     * Method to get all notifications list for the current user in the specified repository
     *
     * @param repository:  repository from fetch the notifications
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "all"} -> if true, show notifications marked as read - [boolean, default false]
     *                        </li>
     *                        <li>
     *                            {@code "participating"} -> if true, only shows notifications in which the user is
     *                            directly participating or mentioned - [boolean, default false]
     *                        </li>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> only show notifications updated before the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return notifications list as {@link Collection} of {@link Notification} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#list-repository-notifications-for-the-authenticated-user">
     * List repository notifications for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/notifications")
    public Collection<Notification> getRepositoryNotifications(Repository repository, Params queryParams) throws IOException {
        return getRepositoryNotifications(repository.getOwner().getLogin(), repository.getName(), queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get all notifications list for the current user in the specified repository
     *
     * @param repository:  repository from fetch the notifications
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "all"} -> if true, show notifications marked as read - [boolean, default false]
     *                        </li>
     *                        <li>
     *                            {@code "participating"} -> if true, only shows notifications in which the user is
     *                            directly participating or mentioned - [boolean, default false]
     *                        </li>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> only show notifications updated before the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return notifications list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#list-repository-notifications-for-the-authenticated-user">
     * List repository notifications for the authenticated user</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/notifications")
    public <T> T getRepositoryNotifications(Repository repository, Params queryParams,
                                            ReturnFormat format) throws IOException {
        return getRepositoryNotifications(repository.getOwner().getLogin(), repository.getName(), queryParams, format);
    }

    /**
     * Method to get all notifications list for the current user in the specified repository
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "all"} -> if true, show notifications marked as read - [boolean, default false]
     *                        </li>
     *                        <li>
     *                            {@code "participating"} -> if true, only shows notifications in which the user is
     *                            directly participating or mentioned - [boolean, default false]
     *                        </li>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> only show notifications updated before the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return notifications list as {@link Collection} of {@link Notification} custom object
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#list-repository-notifications-for-the-authenticated-user">
     * List repository notifications for the authenticated user</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/notifications")
    public Collection<Notification> getRepositoryNotifications(String owner, String repo,
                                                               Params queryParams) throws IOException {
        return getRepositoryNotifications(owner, repo, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get all notifications list for the current user in the specified repository
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "all"} -> if true, show notifications marked as read - [boolean, default false]
     *                        </li>
     *                        <li>
     *                            {@code "participating"} -> if true, only shows notifications in which the user is
     *                            directly participating or mentioned - [boolean, default false]
     *                        </li>
     *                        <li>
     *                            {@code "since"} -> only show notifications updated after the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "before"} -> only show notifications updated before the given time.
     *                            This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return notifications list as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#list-repository-notifications-for-the-authenticated-user">
     * List repository notifications for the authenticated user</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/notifications")
    public <T> T getRepositoryNotifications(String owner, String repo, Params queryParams,
                                            ReturnFormat format) throws IOException {
        return returnNotificationsList(sendGetRequest(REPOS_PATH + owner + "/" + repo + "/" + NOTIFICATIONS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a notifications list
     *
     * @param notificationsResponse: obtained from GitHub's response
     * @param format:                return type formatter -> {@link ReturnFormat}
     * @return notifications list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnNotificationsList(String notificationsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(notificationsResponse);
            case LIBRARY_OBJECT:
                ArrayList<Notification> notifications = new ArrayList<>();
                JSONArray jNotification = new JSONArray(notificationsResponse);
                for (int j = 0; j < jNotification.length(); j++)
                    notifications.add(new Notification(jNotification.getJSONObject(j)));
                return (T) notifications;
            default:
                return (T) notificationsResponse;
        }
    }

    /**
     * Method to arks all notifications in a repository as "read" for the current user.
     * If the number of notifications is too large to complete in one request, you will receive a 202 Accepted status
     * and GitHub will run an asynchronous process to mark notifications as "read."
     * To check whether any "unread" notifications remain, you can use the List repository notifications for
     * the authenticated user endpoint and pass the query parameter all=false
     *
     * @param repository: repository to mark the notifications as read
     * @return message status as {@link String}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#mark-repository-notifications-as-read">
     * Mark repository notifications as read</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/notifications")
    public String markRepositoryNotificationsAsRead(Repository repository) throws IOException {
        return markRepositoryNotificationsAsRead(repository.getOwner().getLogin(), repository.getName(), LIBRARY_OBJECT);
    }

    /**
     * Method to arks all notifications in a repository as "read" for the current user.
     * If the number of notifications is too large to complete in one request, you will receive a 202 Accepted status
     * and GitHub will run an asynchronous process to mark notifications as "read."
     * To check whether any "unread" notifications remain, you can use the List repository notifications for
     * the authenticated user endpoint and pass the query parameter all=false
     *
     * @param repository: repository to mark the notifications as read
     * @return message status as {@link String}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#mark-repository-notifications-as-read">
     * Mark repository notifications as read</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/notifications")
    public <T> T markRepositoryNotificationsAsRead(Repository repository, ReturnFormat format) throws IOException {
        return markRepositoryNotificationsAsRead(repository.getOwner().getLogin(), repository.getName(), format);
    }

    /**
     * Method to arks all notifications in a repository as "read" for the current user.
     * If the number of notifications is too large to complete in one request, you will receive a 202 Accepted status
     * and GitHub will run an asynchronous process to mark notifications as "read."
     * To check whether any "unread" notifications remain, you can use the List repository notifications for
     * the authenticated user endpoint and pass the query parameter all=false
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @return message status as {@link String}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#mark-repository-notifications-as-read">
     * Mark repository notifications as read</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/notifications")
    public String markRepositoryNotificationsAsRead(String owner, String repo) throws IOException {
        return markRepositoryNotificationsAsRead(owner, repo, LIBRARY_OBJECT);
    }

    /**
     * Method to arks all notifications in a repository as "read" for the current user.
     * If the number of notifications is too large to complete in one request, you will receive a 202 Accepted status
     * and GitHub will run an asynchronous process to mark notifications as "read."
     * To check whether any "unread" notifications remain, you can use the List repository notifications for
     * the authenticated user endpoint and pass the query parameter all=false
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return message status as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#mark-repository-notifications-as-read">
     * Mark repository notifications as read</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/notifications")
    public <T> T markRepositoryNotificationsAsRead(String owner, String repo, ReturnFormat format) throws IOException {
        return returnMessage(sendPutRequest(REPOS_PATH + owner + "/" + repo + "/" + NOTIFICATIONS_PATH,
                null), format);
    }

    /**
     * Method to arks all notifications in a repository as "read" for the current user.
     * If the number of notifications is too large to complete in one request, you will receive a 202 Accepted status
     * and GitHub will run an asynchronous process to mark notifications as "read."
     * To check whether any "unread" notifications remain, you can use the List repository notifications for
     * the authenticated user endpoint and pass the query parameter all=false
     *
     * @param repository: repository to mark the notifications as read
     * @param lastReadAt: describes the last point that notifications were checked. Anything updated since this time
     *                    will not be marked as read. If you omit this parameter, all notifications are marked as read.
     *                    This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ. Default: The current timestamp
     * @return message status as {@link String}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#mark-repository-notifications-as-read">
     * Mark repository notifications as read</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/notifications")
    public String markRepositoryNotificationsAsRead(Repository repository, String lastReadAt) throws IOException {
        return markRepositoryNotificationsAsRead(repository.getOwner().getLogin(), repository.getName(), lastReadAt,
                LIBRARY_OBJECT);
    }

    /**
     * Method to arks all notifications in a repository as "read" for the current user.
     * If the number of notifications is too large to complete in one request, you will receive a 202 Accepted status
     * and GitHub will run an asynchronous process to mark notifications as "read."
     * To check whether any "unread" notifications remain, you can use the List repository notifications for
     * the authenticated user endpoint and pass the query parameter all=false
     *
     * @param repository: repository to mark the notifications as read
     * @param lastReadAt: describes the last point that notifications were checked. Anything updated since this time
     *                    will not be marked as read. If you omit this parameter, all notifications are marked as read.
     *                    This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ. Default: The current timestamp
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return message status as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#mark-repository-notifications-as-read">
     * Mark repository notifications as read</a>
     **/
    @WrappedRequest
    @RequestPath(path = "/repos/{owner}/{repo}/notifications")
    public <T> T markRepositoryNotificationsAsRead(Repository repository, String lastReadAt,
                                                   ReturnFormat format) throws IOException {
        return markRepositoryNotificationsAsRead(repository.getOwner().getLogin(), repository.getName(), lastReadAt,
                format);
    }

    /**
     * Method to arks all notifications in a repository as "read" for the current user.
     * If the number of notifications is too large to complete in one request, you will receive a 202 Accepted status
     * and GitHub will run an asynchronous process to mark notifications as "read."
     * To check whether any "unread" notifications remain, you can use the List repository notifications for
     * the authenticated user endpoint and pass the query parameter all=false
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param lastReadAt: describes the last point that notifications were checked. Anything updated since this time
     *                    will not be marked as read. If you omit this parameter, all notifications are marked as read.
     *                    This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ. Default: The current timestamp
     * @return message status as {@link String}
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#mark-repository-notifications-as-read">
     * Mark repository notifications as read</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/notifications")
    public String markRepositoryNotificationsAsRead(String owner, String repo, String lastReadAt) throws IOException {
        return markRepositoryNotificationsAsRead(owner, repo, lastReadAt, LIBRARY_OBJECT);
    }

    /**
     * Method to arks all notifications in a repository as "read" for the current user.
     * If the number of notifications is too large to complete in one request, you will receive a 202 Accepted status
     * and GitHub will run an asynchronous process to mark notifications as "read."
     * To check whether any "unread" notifications remain, you can use the List repository notifications for
     * the authenticated user endpoint and pass the query parameter all=false
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param lastReadAt: describes the last point that notifications were checked. Anything updated since this time
     *                    will not be marked as read. If you omit this parameter, all notifications are marked as read.
     *                    This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ. Default: The current timestamp
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return message status as {@code "format"} defines
     * @throws IOException when request has been go wrong -> you can use these methods to get more details about error:
     *                     <ul>
     *                         <li>
     *                             {@link #getErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #getJSONErrorResponse()}
     *                         </li>
     *                         <li>
     *                             {@link #printErrorResponse()}
     *                         </li>
     *                     </ul> using a {@code "try and catch statement"} during runtime, see how to do in {@code "README"} file
     * @implNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/notifications#mark-repository-notifications-as-read">
     * Mark repository notifications as read</a>
     **/
    @RequestPath(path = "/repos/{owner}/{repo}/notifications")
    public <T> T markRepositoryNotificationsAsRead(String owner, String repo, String lastReadAt,
                                                   ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("last_read_at", lastReadAt);
        return returnMessage(sendPutRequest(REPOS_PATH + owner + "/" + repo + "/" + NOTIFICATIONS_PATH,
                null), format);
    }

    /**
     * Method to create a message
     *
     * @param messageResponse: obtained from GitHub's response
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return message status as {@code "format"} defines
     * @apiNote in this case {@link ReturnFormat#LIBRARY_OBJECT} will return directly the message value as {@link String}
     **/
    @Returner
    private <T> T returnMessage(String messageResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(messageResponse);
            case LIBRARY_OBJECT:
                return (T) new JSONObject(messageResponse).getString("message");
            default:
                return (T) messageResponse;
        }
    }

}
