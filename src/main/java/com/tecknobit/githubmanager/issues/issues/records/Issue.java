package com.tecknobit.githubmanager.issues.issues.records;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.records.parents.*;
import com.tecknobit.githubmanager.records.repository.CompleteRepository;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat;

/**
 * The {@code Issue} class is useful to format a GitHub's issue
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/issues/issues#list-issues-assigned-to-the-authenticated-user">
 *             List issues assigned to the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/issues/issues#list-organization-issues-assigned-to-the-authenticated-user">
 *             List organization issues assigned to the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/issues/issues#list-repository-issues">
 *             List repository issues</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/issues/issues#create-an-issue">
 *             Create an issue</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/issues/issues#get-an-issue">
 *             Get an issue</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/issues/issues#update-an-issue">
 *             Update an issue</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/issues/issues#list-user-account-issues-assigned-to-the-authenticated-user">
 *             List user account issues assigned to the authenticated user</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see GitHubOperationBaseStructure
 * @see GitHubOperation
 **/
public class Issue extends GitHubOperation {

    /**
     * {@code repositoryUrl} repository url of the issue
     **/
    private final String repositoryUrl;

    /**
     * {@code labelsUrl} labels url of the issue
     **/
    private final String labelsUrl;

    /**
     * {@code commentsUrl} comments url of the issue
     **/
    private final String commentsUrl;

    /**
     * {@code eventsUrl} events url of the issue
     **/
    private final String eventsUrl;

    /**
     * {@code comments} of the issue
     **/
    private final int comments;

    /**
     * {@code pullRequest} pull request attached to the issue
     **/
    private final IssuePullRequest pullRequest;

    /**
     * {@code repository} of the issue
     **/
    private final CompleteRepository repository;

    /**
     * {@code stateReason} the reason for the current state
     **/
    private final StateReason stateReason;

    /**
     * Constructor to init a {@link Issue}
     *
     * @param url               : url of the issue
     * @param htmlUrl           : html url of the issue
     * @param id                : id of the issue
     * @param nodeId            : node identifier of the issue
     * @param number            : number of the issue
     * @param state             : state of the issue
     * @param title             : title of the issue
     * @param createdAt         : creation time of the issue
     * @param updatedAt         : update time of the issue
     * @param closedAt          :  close time of the issue
     * @param locked            : whether the pull request is locked
     * @param user              : user of the issue
     * @param body              :  body of the issue
     * @param labels            : labels the pull request
     * @param milestone         : milestone of the issue
     * @param activeLockReason  : active lock reason of the issue
     * @param assignee          : assignee of the issue
     * @param assignees         : assignees of the issue
     * @param authorAssociation : id of the issue
     * @param repositoryUrl     : repository url of the issue
     * @param labelsUrl         : labels url of the issue
     * @param commentsUrl       : comments url of the issue
     * @param eventsUrl         : events url of the issue
     * @param comments          : comments of the issue
     * @param pullRequest       : pull request attached to the issue
     * @param repository        : repository of the issue
     * @param stateReason:      the reason for the current state
     **/
    public Issue(String url, String htmlUrl, long id, String nodeId, long number, OperationState state, String title,
                 String createdAt, String updatedAt, String closedAt, boolean locked, User user, String body,
                 ArrayList<Label> labels, Milestone milestone, LockReason activeLockReason, User assignee,
                 ArrayList<User> assignees, AuthorAssociation authorAssociation, String repositoryUrl, String labelsUrl,
                 String commentsUrl, String eventsUrl, int comments, IssuePullRequest pullRequest,
                 CompleteRepository repository, StateReason stateReason) {
        super(url, htmlUrl, id, nodeId, number, state, title, createdAt, updatedAt, closedAt, locked, user, body, labels,
                milestone, activeLockReason, assignee, assignees, authorAssociation);
        this.repositoryUrl = repositoryUrl;
        this.labelsUrl = labelsUrl;
        this.commentsUrl = commentsUrl;
        this.eventsUrl = eventsUrl;
        this.comments = comments;
        this.pullRequest = pullRequest;
        this.repository = repository;
        this.stateReason = stateReason;
    }

    /**
     * Constructor to init a {@link Issue}
     *
     * @param jIssue : issue details as {@link JSONObject}
     **/
    public Issue(JSONObject jIssue) {
        super(jIssue);
        repositoryUrl = hResponse.getString("repository_url");
        labelsUrl = hResponse.getString("labels_url");
        commentsUrl = hResponse.getString("comments_url");
        eventsUrl = hResponse.getString("events_url");
        comments = hResponse.getInt("comments", 0);
        pullRequest = new IssuePullRequest(hResponse.getJSONObject("pull_request", new JSONObject()));
        repository = new CompleteRepository(hResponse.getJSONObject("repository", new JSONObject()));
        String sStateReason = hResponse.getString("state_reason");
        if (sStateReason != null)
            stateReason = StateReason.valueOf(sStateReason);
        else
            stateReason = null;
    }

    /**
     * Method to get {@link #repositoryUrl} instance <br>
     * No-any params required
     *
     * @return {@link #repositoryUrl} instance as {@link String}
     **/
    public String getRepositoryUrl() {
        return repositoryUrl;
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
     * Method to get {@link #commentsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #commentsUrl} instance as {@link String}
     **/
    public String getCommentsUrl() {
        return commentsUrl;
    }

    /**
     * Method to get {@link #eventsUrl} instance <br>
     * No-any params required
     *
     * @return {@link #eventsUrl} instance as {@link String}
     **/
    public String getEventsUrl() {
        return eventsUrl;
    }

    /**
     * Method to get {@link #comments} instance <br>
     * No-any params required
     *
     * @return {@link #comments} instance as int
     **/
    public int getComments() {
        return comments;
    }

    /**
     * Method to get {@link #pullRequest} instance <br>
     * No-any params required
     *
     * @return {@link #pullRequest} instance as {@link IssuePullRequest}
     **/
    public IssuePullRequest getPullRequest() {
        return pullRequest;
    }

    /**
     * Method to get {@link #repository} instance <br>
     * No-any params required
     *
     * @return {@link #repository} instance as {@link CompleteRepository}
     **/
    public CompleteRepository getRepository() {
        return repository;
    }

    /**
     * The {@code IssuePullRequest} class is useful to format a GitHub's issue pull request attached to {@link Issue}
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class IssuePullRequest extends InnerClassItem {

        /**
         * {@code url} of the issue pull request
         **/
        private final String url;

        /**
         * {@code htmlUrl} html url of the issue pull request
         **/
        private final String htmlUrl;

        /**
         * {@code diffUrl} diff url of the issue pull request
         **/
        private final String diffUrl;

        /**
         * {@code patchUrl} patch url of the issue pull request
         **/
        private final String patchUrl;

        /**
         * Constructor to init a {@link IssuePullRequest}
         *
         * @param url      : url of the issue pull request
         * @param htmlUrl  : html url of the issue pull request
         * @param diffUrl  : diff url of the issue pull request
         * @param patchUrl : patch url of the issue pull request
         **/
        public IssuePullRequest(String url, String htmlUrl, String diffUrl, String patchUrl) {
            super(null);
            this.url = url;
            this.htmlUrl = htmlUrl;
            this.diffUrl = diffUrl;
            this.patchUrl = patchUrl;
        }

        /**
         * Constructor to init a {@link IssuePullRequest}
         *
         * @param jIssuePullRequest : issue pull request details as {@link JSONObject}
         **/
        public IssuePullRequest(JSONObject jIssuePullRequest) {
            super(jIssuePullRequest);
            url = hItem.getString("url");
            htmlUrl = hItem.getString("html_url");
            diffUrl = hItem.getString("diff_url");
            patchUrl = hItem.getString("patch_url");
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
         * Method to get {@link #htmlUrl} instance <br>
         * No-any params required
         *
         * @return {@link #htmlUrl} instance as {@link String}
         **/
        public String getHtmlUrl() {
            return htmlUrl;
        }

        /**
         * Method to get {@link #diffUrl} instance <br>
         * No-any params required
         *
         * @return {@link #diffUrl} instance as {@link String}
         **/
        public String getDiffUrl() {
            return diffUrl;
        }

        /**
         * Method to get {@link #patchUrl} instance <br>
         * No-any params required
         *
         * @return {@link #patchUrl} instance as {@link String}
         **/
        public String getPatchUrl() {
            return patchUrl;
        }

    }

    /**
     * {@code StateReason} list of available state reasons
     **/
    public enum StateReason {

        /**
         * {@code completed} state reason
         **/
        completed,

        /**
         * {@code reopened} state reason
         **/
        reopened,

        /**
         * {@code not_planned} state reason
         **/
        not_planned

    }

    /**
     * {@code IssueSort} is a list for the issue sorts available
     **/
    public enum IssueSort {

        /**
         * {@code "created"} sort
         **/
        created,

        /**
         * {@code "updated"} sort
         **/
        updated,

        /**
         * {@code "comments"} sort
         **/
        comments

    }

    /**
     * {@code IssueFilter} is a list for the issue filter available
     **/
    public enum IssueFilter {

        /**
         * {@code "assigned"} filter
         **/
        assigned,

        /**
         * {@code "created"} filter
         **/
        created,

        /**
         * {@code "mentioned"} filter
         **/
        mentioned,

        /**
         * {@code "subscribed"} filter
         **/
        subscribed,

        /**
         * {@code "repos"} filter
         **/
        repos,

        /**
         * {@code "all"} filter
         **/
        all,

    }

    /**
     * Method to create an issue
     *
     * @param issueListResponse : obtained from GitHub's response
     * @param format            :              return type formatter -> {@link ReturnFormat}
     * @return issue as {@code "format"} defines
     **/
    @Returner
    public static <T> T returnIssue(String issueListResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(issueListResponse);
            case LIBRARY_OBJECT:
                return (T) new Issue(new JSONObject(issueListResponse));
            default:
                return (T) issueListResponse;
        }
    }

}
