package com.tecknobit.githubmanager.reactions;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.commits.commitcomments.records.CommitComment;
import com.tecknobit.githubmanager.issues.comments.records.IssueComment;
import com.tecknobit.githubmanager.issues.issues.records.Issue;
import com.tecknobit.githubmanager.organizations.organizations.records.Organization;
import com.tecknobit.githubmanager.pulls.reviewcomments.records.ReviewComment;
import com.tecknobit.githubmanager.reactions.records.Reaction;
import com.tecknobit.githubmanager.reactions.records.Reaction.ReactionContent;
import com.tecknobit.githubmanager.records.organization.Team;
import com.tecknobit.githubmanager.records.repository.Repository;
import com.tecknobit.githubmanager.releases.releases.records.Release;
import com.tecknobit.githubmanager.teams.Discussion;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.commits.commitcomments.GitHubCommitCommentsManager.COMMENTS_PATH;
import static com.tecknobit.githubmanager.issues.comments.GitHubIssueCommentsManager.ISSUES_COMMENTS_PATH;
import static com.tecknobit.githubmanager.issues.issues.GitHubIssuesManager.ISSUES_PATH;
import static com.tecknobit.githubmanager.pulls.reviewcomments.GitHubReviewCommentsManager.PULLS_COMMENTS_PATH;
import static com.tecknobit.githubmanager.releases.releases.GitHubReleasesManager.RELEASES_QUERY_PATH;

/**
 * The {@code GitHubReactionsManager} class is useful to manage all GitHub's reactions endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions">
 * CommitCommentReactions</a>
 * @see GitHubManager
 **/
public class GitHubReactionsManager extends GitHubManager {

    /**
     * {@code REACTIONS_PATH} constant for {@code "/reactions"} path
     **/
    public static final String REACTIONS_PATH = "/reactions";

    /**
     * {@code DISCUSSIONS_PATH} constant for {@code "/discussions/"} path
     **/
    public static final String DISCUSSIONS_PATH = "/discussions/";

    /**
     * Constructor to init a {@link GitHubReactionsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubReactionsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubReactionsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubReactionsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubReactionsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubReactionsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubReactionsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubReactionsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubReactionsManager} <br>
     * No-any params required
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
    public GitHubReactionsManager() {
        super();
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:           the organization from fetch the list
     * @param team:          the team from fetch the list
     * @param discussion:    the discussion from fetch the list
     * @param commentNumber: the number that identifies the comment
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionCommentReactions(Organization org, Team team, Discussion discussion,
                                                                 long commentNumber) throws IOException {
        return getTeamDiscussionCommentReactions(org.getLogin(), team.getSlug(), discussion.getNumber(), commentNumber,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:           the organization from fetch the list
     * @param team:          the team from fetch the list
     * @param discussion:    the discussion from fetch the list
     * @param commentNumber: the number that identifies the comment
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public <T> T getTeamDiscussionCommentReactions(Organization org, Team team, Discussion discussion, long commentNumber,
                                                   ReturnFormat format) throws IOException {
        return getTeamDiscussionCommentReactions(org.getLogin(), team.getSlug(), discussion.getNumber(), commentNumber,
                format);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization from fetch the list
     * @param team:             the team from fetch the list
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionCommentReactions(Organization org, Team team, long discussionNumber,
                                                                 long commentNumber) throws IOException {
        return getTeamDiscussionCommentReactions(org.getLogin(), team.getSlug(), discussionNumber, commentNumber,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization from fetch the list
     * @param team:             the team from fetch the list
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public <T> T getTeamDiscussionCommentReactions(Organization org, Team team, long discussionNumber, long commentNumber,
                                                   ReturnFormat format) throws IOException {
        return getTeamDiscussionCommentReactions(org.getLogin(), team.getSlug(), discussionNumber, commentNumber, format);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param team:          the team from fetch the list
     * @param discussion:    the discussion from fetch the list
     * @param commentNumber: the number that identifies the comment
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionCommentReactions(String org, Team team, Discussion discussion,
                                                                 long commentNumber) throws IOException {
        return getTeamDiscussionCommentReactions(org, team.getSlug(), discussion.getNumber(), commentNumber,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param team:          the team from fetch the list
     * @param discussion:    the discussion from fetch the list
     * @param commentNumber: the number that identifies the comment
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public <T> T getTeamDiscussionCommentReactions(String org, Team team, Discussion discussion, long commentNumber,
                                                   ReturnFormat format) throws IOException {
        return getTeamDiscussionCommentReactions(org, team.getSlug(), discussion.getNumber(), commentNumber, format);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param team:             the team from fetch the list
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionCommentReactions(String org, Team team, long discussionNumber,
                                                                 long commentNumber) throws IOException {
        return getTeamDiscussionCommentReactions(org, team.getSlug(), discussionNumber, commentNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param team:             the team from fetch the list
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public <T> T getTeamDiscussionCommentReactions(String org, Team team, long discussionNumber, long commentNumber,
                                                   ReturnFormat format) throws IOException {
        return getTeamDiscussionCommentReactions(org, team.getSlug(), discussionNumber, commentNumber, format);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:           the organization from fetch the list
     * @param teamSlug:      the slug of the team name
     * @param discussion:    the discussion from fetch the list
     * @param commentNumber: the number that identifies the comment
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionCommentReactions(Organization org, String teamSlug, Discussion discussion,
                                                                 long commentNumber) throws IOException {
        return getTeamDiscussionCommentReactions(org.getLogin(), teamSlug, discussion.getNumber(), commentNumber,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:           the organization from fetch the list
     * @param teamSlug:      the slug of the team name
     * @param discussion:    the discussion from fetch the list
     * @param commentNumber: the number that identifies the comment
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public <T> T getTeamDiscussionCommentReactions(Organization org, String teamSlug, Discussion discussion,
                                                   long commentNumber, ReturnFormat format) throws IOException {
        return getTeamDiscussionCommentReactions(org.getLogin(), teamSlug, discussion.getNumber(), commentNumber, format);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization from fetch the list
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionCommentReactions(Organization org, String teamSlug, long discussionNumber,
                                                                 long commentNumber) throws IOException {
        return getTeamDiscussionCommentReactions(org.getLogin(), teamSlug, discussionNumber, commentNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization from fetch the list
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public <T> T getTeamDiscussionCommentReactions(Organization org, String teamSlug, long discussionNumber,
                                                   long commentNumber, ReturnFormat format) throws IOException {
        return getTeamDiscussionCommentReactions(org.getLogin(), teamSlug, discussionNumber, commentNumber, format);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param teamSlug:      the slug of the team name
     * @param discussion:    the discussion from fetch the list
     * @param commentNumber: the number that identifies the comment
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionCommentReactions(String org, String teamSlug, Discussion discussion,
                                                                 long commentNumber) throws IOException {
        return getTeamDiscussionCommentReactions(org, teamSlug, discussion.getNumber(), commentNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param teamSlug:      the slug of the team name
     * @param discussion:    the discussion from fetch the list
     * @param commentNumber: the number that identifies the comment
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public <T> T getTeamDiscussionCommentReactions(String org, String teamSlug, Discussion discussion, long commentNumber,
                                                   ReturnFormat format) throws IOException {
        return getTeamDiscussionCommentReactions(org, teamSlug, discussion.getNumber(), commentNumber, format);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionCommentReactions(String org, String teamSlug, long discussionNumber,
                                                                 long commentNumber) throws IOException {
        return getTeamDiscussionCommentReactions(org, teamSlug, discussionNumber, commentNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public <T> T getTeamDiscussionCommentReactions(String org, String teamSlug, long discussionNumber, long commentNumber,
                                                   ReturnFormat format) throws IOException {
        return returnReactions(sendGetRequest(ORGS_PATH + org + TEAMS_PATH + "/" + teamSlug + DISCUSSIONS_PATH
                + discussionNumber + COMMENTS_PATH + "/" + commentNumber + REACTIONS_PATH), format);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:           the organization from fetch the list
     * @param team:          the team from fetch the list
     * @param discussion:    the discussion from fetch the list
     * @param commentNumber: the number that identifies the comment
     * @param queryParams:   extra query params not mandatory, keys accepted are:
     *                       <ul>
     *                          <li>
     *                              {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                              reactions, constants available {@link ReactionContent} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                          </li>
     *                          <li>
     *                              {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                          </li>
     *                       </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionCommentReactions(Organization org, Team team, Discussion discussion,
                                                                 long commentNumber, Params queryParams) throws IOException {
        return getTeamDiscussionCommentReactions(org.getLogin(), team.getSlug(), discussion.getNumber(), commentNumber,
                queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:           the organization from fetch the list
     * @param team:          the team from fetch the list
     * @param discussion:    the discussion from fetch the list
     * @param commentNumber: the number that identifies the comment
     * @param queryParams:   extra query params not mandatory, keys accepted are:
     *                       <ul>
     *                          <li>
     *                              {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                              reactions, constants available {@link ReactionContent} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                          </li>
     *                          <li>
     *                              {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                          </li>
     *                       </ul>
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public <T> T getTeamDiscussionCommentReactions(Organization org, Team team, Discussion discussion, long commentNumber,
                                                   Params queryParams, ReturnFormat format) throws IOException {
        return getTeamDiscussionCommentReactions(org.getLogin(), team.getSlug(), discussion.getNumber(), commentNumber,
                queryParams, format);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization from fetch the list
     * @param team:             the team from fetch the list
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @param queryParams:      extra query params not mandatory, keys accepted are:
     *                          <ul>
     *                             <li>
     *                                 {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                                 reactions, constants available {@link ReactionContent} - [string]
     *                             </li>
     *                             <li>
     *                                 {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                             </li>
     *                             <li>
     *                                 {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                             </li>
     *                          </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionCommentReactions(Organization org, Team team, long discussionNumber,
                                                                 long commentNumber, Params queryParams) throws IOException {
        return getTeamDiscussionCommentReactions(org.getLogin(), team.getSlug(), discussionNumber, commentNumber,
                queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization from fetch the list
     * @param team:             the team from fetch the list
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @param queryParams:      extra query params not mandatory, keys accepted are:
     *                          <ul>
     *                             <li>
     *                                 {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                                 reactions, constants available {@link ReactionContent} - [string]
     *                             </li>
     *                             <li>
     *                                 {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                             </li>
     *                             <li>
     *                                 {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                             </li>
     *                          </ul>
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public <T> T getTeamDiscussionCommentReactions(Organization org, Team team, long discussionNumber, long commentNumber,
                                                   Params queryParams, ReturnFormat format) throws IOException {
        return getTeamDiscussionCommentReactions(org.getLogin(), team.getSlug(), discussionNumber, commentNumber,
                queryParams, format);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param team:          the team from fetch the list
     * @param discussion:    the discussion from fetch the list
     * @param commentNumber: the number that identifies the comment
     * @param queryParams:   extra query params not mandatory, keys accepted are:
     *                       <ul>
     *                          <li>
     *                              {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                              reactions, constants available {@link ReactionContent} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                          </li>
     *                          <li>
     *                              {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                          </li>
     *                       </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionCommentReactions(String org, Team team, Discussion discussion,
                                                                 long commentNumber, Params queryParams) throws IOException {
        return getTeamDiscussionCommentReactions(org, team.getSlug(), discussion.getNumber(), commentNumber, queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param team:          the team from fetch the list
     * @param discussion:    the discussion from fetch the list
     * @param commentNumber: the number that identifies the comment
     * @param queryParams:   extra query params not mandatory, keys accepted are:
     *                       <ul>
     *                          <li>
     *                              {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                              reactions, constants available {@link ReactionContent} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                          </li>
     *                          <li>
     *                              {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                          </li>
     *                       </ul>
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public <T> T getTeamDiscussionCommentReactions(String org, Team team, Discussion discussion, long commentNumber,
                                                   Params queryParams, ReturnFormat format) throws IOException {
        return getTeamDiscussionCommentReactions(org, team.getSlug(), discussion.getNumber(), commentNumber, queryParams,
                format);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param team:             the team from fetch the list
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @param queryParams:      extra query params not mandatory, keys accepted are:
     *                          <ul>
     *                             <li>
     *                                 {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                                 reactions, constants available {@link ReactionContent} - [string]
     *                             </li>
     *                             <li>
     *                                 {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                             </li>
     *                             <li>
     *                                 {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                             </li>
     *                          </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionCommentReactions(String org, Team team, long discussionNumber,
                                                                 long commentNumber, Params queryParams) throws IOException {
        return getTeamDiscussionCommentReactions(org, team.getSlug(), discussionNumber, commentNumber, queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param team:             the team from fetch the list
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @param queryParams:      extra query params not mandatory, keys accepted are:
     *                          <ul>
     *                             <li>
     *                                 {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                                 reactions, constants available {@link ReactionContent} - [string]
     *                             </li>
     *                             <li>
     *                                 {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                             </li>
     *                             <li>
     *                                 {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                             </li>
     *                          </ul>
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public <T> T getTeamDiscussionCommentReactions(String org, Team team, long discussionNumber, long commentNumber,
                                                   Params queryParams, ReturnFormat format) throws IOException {
        return getTeamDiscussionCommentReactions(org, team.getSlug(), discussionNumber, commentNumber, queryParams,
                format);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:           the organization from fetch the list
     * @param teamSlug:      the slug of the team name
     * @param discussion:    the discussion from fetch the list
     * @param commentNumber: the number that identifies the comment
     * @param queryParams:   extra query params not mandatory, keys accepted are:
     *                       <ul>
     *                          <li>
     *                              {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                              reactions, constants available {@link ReactionContent} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                          </li>
     *                          <li>
     *                              {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                          </li>
     *                       </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionCommentReactions(Organization org, String teamSlug, Discussion discussion,
                                                                 long commentNumber, Params queryParams) throws IOException {
        return getTeamDiscussionCommentReactions(org.getLogin(), teamSlug, discussion.getNumber(), commentNumber,
                queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:           the organization from fetch the list
     * @param teamSlug:      the slug of the team name
     * @param discussion:    the discussion from fetch the list
     * @param commentNumber: the number that identifies the comment
     * @param queryParams:   extra query params not mandatory, keys accepted are:
     *                       <ul>
     *                          <li>
     *                              {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                              reactions, constants available {@link ReactionContent} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                          </li>
     *                          <li>
     *                              {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                          </li>
     *                       </ul>
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public <T> T getTeamDiscussionCommentReactions(Organization org, String teamSlug, Discussion discussion,
                                                   long commentNumber, Params queryParams,
                                                   ReturnFormat format) throws IOException {
        return getTeamDiscussionCommentReactions(org.getLogin(), teamSlug, discussion.getNumber(), commentNumber,
                queryParams, format);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization from fetch the list
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @param queryParams:      extra query params not mandatory, keys accepted are:
     *                          <ul>
     *                             <li>
     *                                 {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                                 reactions, constants available {@link ReactionContent} - [string]
     *                             </li>
     *                             <li>
     *                                 {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                             </li>
     *                             <li>
     *                                 {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                             </li>
     *                          </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionCommentReactions(Organization org, String teamSlug, long discussionNumber,
                                                                 long commentNumber, Params queryParams) throws IOException {
        return getTeamDiscussionCommentReactions(org.getLogin(), teamSlug, discussionNumber, commentNumber, queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization from fetch the list
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @param queryParams:      extra query params not mandatory, keys accepted are:
     *                          <ul>
     *                             <li>
     *                                 {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                                 reactions, constants available {@link ReactionContent} - [string]
     *                             </li>
     *                             <li>
     *                                 {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                             </li>
     *                             <li>
     *                                 {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                             </li>
     *                          </ul>
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public <T> T getTeamDiscussionCommentReactions(Organization org, String teamSlug, long discussionNumber,
                                                   long commentNumber, Params queryParams,
                                                   ReturnFormat format) throws IOException {
        return getTeamDiscussionCommentReactions(org.getLogin(), teamSlug, discussionNumber, commentNumber, queryParams,
                format);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param teamSlug:      the slug of the team name
     * @param discussion:    the discussion from fetch the list
     * @param commentNumber: the number that identifies the comment
     * @param queryParams:   extra query params not mandatory, keys accepted are:
     *                       <ul>
     *                          <li>
     *                              {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                              reactions, constants available {@link ReactionContent} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                          </li>
     *                          <li>
     *                              {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                          </li>
     *                       </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionCommentReactions(String org, String teamSlug, Discussion discussion,
                                                                 long commentNumber, Params queryParams) throws IOException {
        return getTeamDiscussionCommentReactions(org, teamSlug, discussion.getNumber(), commentNumber, queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param teamSlug:      the slug of the team name
     * @param discussion:    the discussion from fetch the list
     * @param commentNumber: the number that identifies the comment
     * @param queryParams:   extra query params not mandatory, keys accepted are:
     *                       <ul>
     *                          <li>
     *                              {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                              reactions, constants available {@link ReactionContent} - [string]
     *                          </li>
     *                          <li>
     *                              {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                          </li>
     *                          <li>
     *                              {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                          </li>
     *                       </ul>
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public <T> T getTeamDiscussionCommentReactions(String org, String teamSlug, Discussion discussion, long commentNumber,
                                                   Params queryParams, ReturnFormat format) throws IOException {
        return getTeamDiscussionCommentReactions(org, teamSlug, discussion.getNumber(), commentNumber, queryParams,
                format);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @param queryParams:      extra query params not mandatory, keys accepted are:
     *                          <ul>
     *                             <li>
     *                                 {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                                 reactions, constants available {@link ReactionContent} - [string]
     *                             </li>
     *                             <li>
     *                                 {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                             </li>
     *                             <li>
     *                                 {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                             </li>
     *                          </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionCommentReactions(String org, String teamSlug, long discussionNumber,
                                                                 long commentNumber, Params queryParams) throws IOException {
        return getTeamDiscussionCommentReactions(org, teamSlug, discussionNumber, commentNumber, queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion comment.
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @param queryParams:      extra query params not mandatory, keys accepted are:
     *                          <ul>
     *                             <li>
     *                                 {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                                 reactions, constants available {@link ReactionContent} - [string]
     *                             </li>
     *                             <li>
     *                                 {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                             </li>
     *                             <li>
     *                                 {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                             </li>
     *                          </ul>
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion-comment">
     * List reactions for a team discussion comment</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public <T> T getTeamDiscussionCommentReactions(String org, String teamSlug, long discussionNumber, long commentNumber,
                                                   Params queryParams, ReturnFormat format) throws IOException {
        return returnReactions(sendGetRequest(ORGS_PATH + org + TEAMS_PATH + "/" + teamSlug + DISCUSSIONS_PATH
                + discussionNumber + COMMENTS_PATH + "/" + commentNumber + REACTIONS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a reaction to a team discussion comment. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:           the organization where create the reaction
     * @param team:          the team where create the reaction
     * @param discussion:    the discussion where create the reaction
     * @param commentNumber: the number that identifies the comment
     * @param content:       the reaction type to add to the team discussion comment
     * @return reaction as {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion-comment">
     * Create reaction for a team discussion comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public Reaction createTeamDiscussionCommentReaction(Organization org, Team team, Discussion discussion,
                                                        long commentNumber, ReactionContent content) throws IOException {
        return createTeamDiscussionCommentReaction(org.getLogin(), team.getSlug(), discussion.getNumber(), commentNumber,
                content, LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to a team discussion comment. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:           the organization where create the reaction
     * @param team:          the team where create the reaction
     * @param discussion:    the discussion where create the reaction
     * @param commentNumber: the number that identifies the comment
     * @param content:       the reaction type to add to the team discussion comment
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion-comment">
     * Create reaction for a team discussion comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public <T> T createTeamDiscussionCommentReaction(Organization org, Team team, Discussion discussion, long commentNumber,
                                                     ReactionContent content, ReturnFormat format) throws IOException {
        return createTeamDiscussionCommentReaction(org.getLogin(), team.getSlug(), discussion.getNumber(), commentNumber,
                content, format);
    }

    /**
     * Method to create a reaction to a team discussion comment. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:              the organization where create the reaction
     * @param team:             the team where create the reaction
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @param content:          the reaction type to add to the team discussion comment
     * @return reaction as {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion-comment">
     * Create reaction for a team discussion comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public Reaction createTeamDiscussionCommentReaction(Organization org, Team team, long discussionNumber,
                                                        long commentNumber, ReactionContent content) throws IOException {
        return createTeamDiscussionCommentReaction(org.getLogin(), team.getSlug(), discussionNumber, commentNumber,
                content, LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to a team discussion comment. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:              the organization where create the reaction
     * @param team:             the team where create the reaction
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @param content:          the reaction type to add to the team discussion comment
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion-comment">
     * Create reaction for a team discussion comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public <T> T createTeamDiscussionCommentReaction(Organization org, Team team, long discussionNumber, long commentNumber,
                                                     ReactionContent content, ReturnFormat format) throws IOException {
        return createTeamDiscussionCommentReaction(org.getLogin(), team.getSlug(), discussionNumber, commentNumber,
                content, format);
    }

    /**
     * Method to create a reaction to a team discussion comment. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param team:          the team where create the reaction
     * @param discussion:    the discussion where create the reaction
     * @param commentNumber: the number that identifies the comment
     * @param content:       the reaction type to add to the team discussion comment
     * @return reaction as {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion-comment">
     * Create reaction for a team discussion comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public Reaction createTeamDiscussionCommentReaction(String org, Team team, Discussion discussion, long commentNumber,
                                                        ReactionContent content) throws IOException {
        return createTeamDiscussionCommentReaction(org, team.getSlug(), discussion.getNumber(), commentNumber, content,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to a team discussion comment. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param team:          the team where create the reaction
     * @param discussion:    the discussion where create the reaction
     * @param commentNumber: the number that identifies the comment
     * @param content:       the reaction type to add to the team discussion comment
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion-comment">
     * Create reaction for a team discussion comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public <T> T createTeamDiscussionCommentReaction(String org, Team team, Discussion discussion, long commentNumber,
                                                     ReactionContent content, ReturnFormat format) throws IOException {
        return createTeamDiscussionCommentReaction(org, team.getSlug(), discussion.getNumber(), commentNumber, content,
                format);
    }

    /**
     * Method to create a reaction to a team discussion comment. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param team:             the team where create the reaction
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @param content:          the reaction type to add to the team discussion comment
     * @return reaction as {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion-comment">
     * Create reaction for a team discussion comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public Reaction createTeamDiscussionCommentReaction(String org, Team team, long discussionNumber, long commentNumber,
                                                        ReactionContent content) throws IOException {
        return createTeamDiscussionCommentReaction(org, team.getSlug(), discussionNumber, commentNumber, content,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to a team discussion comment. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param team:             the team where create the reaction
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @param content:          the reaction type to add to the team discussion comment
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion-comment">
     * Create reaction for a team discussion comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public <T> T createTeamDiscussionCommentReaction(String org, Team team, long discussionNumber, long commentNumber,
                                                     ReactionContent content, ReturnFormat format) throws IOException {
        return createTeamDiscussionCommentReaction(org, team.getSlug(), discussionNumber, commentNumber, content, format);
    }

    /**
     * Method to create a reaction to a team discussion comment. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:           the organization where create the reaction
     * @param teamSlug:      the slug of the team name
     * @param discussion:    the discussion where create the reaction
     * @param commentNumber: the number that identifies the comment
     * @param content:       the reaction type to add to the team discussion comment
     * @return reaction as {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion-comment">
     * Create reaction for a team discussion comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public Reaction createTeamDiscussionCommentReaction(Organization org, String teamSlug, Discussion discussion,
                                                        long commentNumber, ReactionContent content) throws IOException {
        return createTeamDiscussionCommentReaction(org.getLogin(), teamSlug, discussion.getNumber(), commentNumber,
                content, LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to a team discussion comment. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:           the organization where create the reaction
     * @param teamSlug:      the slug of the team name
     * @param discussion:    the discussion where create the reaction
     * @param commentNumber: the number that identifies the comment
     * @param content:       the reaction type to add to the team discussion comment
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion-comment">
     * Create reaction for a team discussion comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public <T> T createTeamDiscussionCommentReaction(Organization org, String teamSlug, Discussion discussion,
                                                     long commentNumber, ReactionContent content,
                                                     ReturnFormat format) throws IOException {
        return createTeamDiscussionCommentReaction(org.getLogin(), teamSlug, discussion.getNumber(), commentNumber,
                content, format);
    }

    /**
     * Method to create a reaction to a team discussion comment. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:              the organization where create the reaction
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @param content:          the reaction type to add to the team discussion comment
     * @return reaction as {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion-comment">
     * Create reaction for a team discussion comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public Reaction createTeamDiscussionCommentReaction(Organization org, String teamSlug, long discussionNumber,
                                                        long commentNumber, ReactionContent content) throws IOException {
        return createTeamDiscussionCommentReaction(org.getLogin(), teamSlug, discussionNumber, commentNumber, content,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to a team discussion comment. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:              the organization where create the reaction
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @param content:          the reaction type to add to the team discussion comment
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion-comment">
     * Create reaction for a team discussion comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public <T> T createTeamDiscussionCommentReaction(Organization org, String teamSlug, long discussionNumber,
                                                     long commentNumber, ReactionContent content,
                                                     ReturnFormat format) throws IOException {
        return createTeamDiscussionCommentReaction(org.getLogin(), teamSlug, discussionNumber, commentNumber, content,
                format);
    }

    /**
     * Method to create a reaction to a team discussion comment. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param teamSlug:      the slug of the team name
     * @param discussion:    the discussion where create the reaction
     * @param commentNumber: the number that identifies the comment
     * @param content:       the reaction type to add to the team discussion comment
     * @return reaction as {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion-comment">
     * Create reaction for a team discussion comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public Reaction createTeamDiscussionCommentReaction(String org, String teamSlug, Discussion discussion,
                                                        long commentNumber, ReactionContent content) throws IOException {
        return createTeamDiscussionCommentReaction(org, teamSlug, discussion.getNumber(), commentNumber, content,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to a team discussion comment. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param teamSlug:      the slug of the team name
     * @param discussion:    the discussion where create the reaction
     * @param commentNumber: the number that identifies the comment
     * @param content:       the reaction type to add to the team discussion comment
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion-comment">
     * Create reaction for a team discussion comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public <T> T createTeamDiscussionCommentReaction(String org, String teamSlug, Discussion discussion,
                                                     long commentNumber, ReactionContent content,
                                                     ReturnFormat format) throws IOException {
        return createTeamDiscussionCommentReaction(org, teamSlug, discussion.getNumber(), commentNumber, content,
                format);
    }

    /**
     * Method to create a reaction to a team discussion comment. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @param content:          the reaction type to add to the team discussion comment
     * @return reaction as {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion-comment">
     * Create reaction for a team discussion comment</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public Reaction createTeamDiscussionCommentReaction(String org, String teamSlug, long discussionNumber,
                                                        long commentNumber, ReactionContent content) throws IOException {
        return createTeamDiscussionCommentReaction(org, teamSlug, discussionNumber, commentNumber, content,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to a team discussion comment. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @param content:          the reaction type to add to the team discussion comment
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion-comment">
     * Create reaction for a team discussion comment</a>
     **/
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions")
    public <T> T createTeamDiscussionCommentReaction(String org, String teamSlug, long discussionNumber,
                                                     long commentNumber, ReactionContent content,
                                                     ReturnFormat format) throws IOException {
        return returnReaction(sendPostRequest(ORGS_PATH + org + TEAMS_PATH + "/" + teamSlug + DISCUSSIONS_PATH
                        + discussionNumber + COMMENTS_PATH + "/" + commentNumber + REACTIONS_PATH, createReactionPayload(content)),
                format);
    }

    /**
     * Method to delete a reaction to a team discussion comment. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:           the organization where delete the reaction
     * @param team:          the team where delete the reaction
     * @param discussion:    the discussion where delete the reaction
     * @param commentNumber: the number that identifies the comment
     * @param reaction:      the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-comment-reaction">
     * Delete team discussion comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionCommentReaction(Organization org, Team team, Discussion discussion,
                                                       long commentNumber, Reaction reaction) {
        return deleteTeamDiscussionCommentReaction(org.getLogin(), team.getSlug(), discussion.getNumber(), commentNumber,
                reaction.getId());
    }

    /**
     * Method to delete a reaction to a team discussion comment. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:              the organization where delete the reaction
     * @param team:             the team where delete the reaction
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @param reaction:         the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-comment-reaction">
     * Delete team discussion comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionCommentReaction(Organization org, Team team, long discussionNumber,
                                                       long commentNumber, Reaction reaction) {
        return deleteTeamDiscussionCommentReaction(org.getLogin(), team.getSlug(), discussionNumber, commentNumber,
                reaction.getId());
    }

    /**
     * Method to delete a reaction to a team discussion comment. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:           the organization where delete the reaction
     * @param teamSlug:      the slug of the team name
     * @param discussion:    the discussion where delete the reaction
     * @param commentNumber: the number that identifies the comment
     * @param reaction:      the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-comment-reaction">
     * Delete team discussion comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionCommentReaction(Organization org, String teamSlug, Discussion discussion,
                                                       long commentNumber, Reaction reaction) {
        return deleteTeamDiscussionCommentReaction(org.getLogin(), teamSlug, discussion.getNumber(), commentNumber,
                reaction.getId());
    }

    /**
     * Method to delete a reaction to a team discussion comment. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:              the organization where delete the reaction
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @param reaction:         the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-comment-reaction">
     * Delete team discussion comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionCommentReaction(Organization org, String teamSlug, long discussionNumber,
                                                       long commentNumber, Reaction reaction) {
        return deleteTeamDiscussionCommentReaction(org.getLogin(), teamSlug, discussionNumber, commentNumber,
                reaction.getId());
    }

    /**
     * Method to delete a reaction to a team discussion comment. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param teamSlug:      the slug of the team name
     * @param discussion:    the discussion where delete the reaction
     * @param commentNumber: the number that identifies the comment
     * @param reaction:      the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-comment-reaction">
     * Delete team discussion comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionCommentReaction(String org, String teamSlug, Discussion discussion,
                                                       long commentNumber, Reaction reaction) {
        return deleteTeamDiscussionCommentReaction(org, teamSlug, discussion.getNumber(), commentNumber, reaction.getId());
    }

    /**
     * Method to delete a reaction to a team discussion comment. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @param reaction:         the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-comment-reaction">
     * Delete team discussion comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionCommentReaction(String org, String teamSlug, long discussionNumber,
                                                       long commentNumber, Reaction reaction) {
        return deleteTeamDiscussionCommentReaction(org, teamSlug, discussionNumber, commentNumber, reaction.getId());
    }

    /**
     * Method to delete a reaction to a team discussion comment. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param team:          the team where delete the reaction
     * @param discussion:    the discussion where delete the reaction
     * @param commentNumber: the number that identifies the comment
     * @param reaction:      the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-comment-reaction">
     * Delete team discussion comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionCommentReaction(String org, Team team, Discussion discussion, long commentNumber,
                                                       Reaction reaction) {
        return deleteTeamDiscussionCommentReaction(org, team.getSlug(), discussion.getNumber(), commentNumber,
                reaction.getId());
    }

    /**
     * Method to delete a reaction to a team discussion comment. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param team:             the team where delete the reaction
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @param reaction:         the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-comment-reaction">
     * Delete team discussion comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionCommentReaction(String org, Team team, long discussionNumber,
                                                       long commentNumber, Reaction reaction) {
        return deleteTeamDiscussionCommentReaction(org, team.getSlug(), discussionNumber, commentNumber, reaction.getId());
    }

    /**
     * Method to delete a reaction to a team discussion comment. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param team:          the team where delete the reaction
     * @param discussion:    the discussion where delete the reaction
     * @param commentNumber: the number that identifies the comment
     * @param reactionId:    the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-comment-reaction">
     * Delete team discussion comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionCommentReaction(String org, Team team, Discussion discussion,
                                                       long commentNumber, long reactionId) {
        return deleteTeamDiscussionCommentReaction(org, team.getSlug(), discussion.getNumber(), commentNumber, reactionId);
    }

    /**
     * Method to delete a reaction to a team discussion comment. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param team:             the team where delete the reaction
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @param reactionId:       the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-comment-reaction">
     * Delete team discussion comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionCommentReaction(String org, Team team, long discussionNumber,
                                                       long commentNumber, long reactionId) {
        return deleteTeamDiscussionCommentReaction(org, team.getSlug(), discussionNumber, commentNumber, reactionId);
    }

    /**
     * Method to delete a reaction to a team discussion comment. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:           the organization where delete the reaction
     * @param team:          the team where delete the reaction
     * @param discussion:    the discussion where delete the reaction
     * @param commentNumber: the number that identifies the comment
     * @param reactionId:    the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-comment-reaction">
     * Delete team discussion comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionCommentReaction(Organization org, Team team, Discussion discussion,
                                                       long commentNumber, long reactionId) {
        return deleteTeamDiscussionCommentReaction(org.getLogin(), team.getSlug(), discussion.getNumber(), commentNumber,
                reactionId);
    }

    /**
     * Method to delete a reaction to a team discussion comment. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:              the organization where delete the reaction
     * @param team:             the team where delete the reaction
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @param reactionId:       the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-comment-reaction">
     * Delete team discussion comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionCommentReaction(Organization org, Team team, long discussionNumber,
                                                       long commentNumber, long reactionId) {
        return deleteTeamDiscussionCommentReaction(org.getLogin(), team.getSlug(), discussionNumber, commentNumber,
                reactionId);
    }

    /**
     * Method to delete a reaction to a team discussion comment. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:           the organization where delete the reaction
     * @param teamSlug:      the slug of the team name
     * @param discussion:    the discussion where delete the reaction
     * @param commentNumber: the number that identifies the comment
     * @param reactionId:    the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-comment-reaction">
     * Delete team discussion comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionCommentReaction(Organization org, String teamSlug, Discussion discussion,
                                                       long commentNumber, long reactionId) {
        return deleteTeamDiscussionCommentReaction(org.getLogin(), teamSlug, discussion.getNumber(), commentNumber,
                reactionId);
    }

    /**
     * Method to delete a reaction to a team discussion comment. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:              the organization where delete the reaction
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @param reactionId:       the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-comment-reaction">
     * Delete team discussion comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionCommentReaction(Organization org, String teamSlug, long discussionNumber,
                                                       long commentNumber, long reactionId) {
        return deleteTeamDiscussionCommentReaction(org.getLogin(), teamSlug, discussionNumber, commentNumber, reactionId);
    }

    /**
     * Method to delete a reaction to a team discussion comment. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:           the organization name. The name is not case-sensitive
     * @param teamSlug:      the slug of the team name
     * @param discussion:    the discussion where delete the reaction
     * @param commentNumber: the number that identifies the comment
     * @param reactionId:    the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-comment-reaction">
     * Delete team discussion comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionCommentReaction(String org, String teamSlug, Discussion discussion,
                                                       long commentNumber, long reactionId) {
        return deleteTeamDiscussionCommentReaction(org, teamSlug, discussion.getNumber(), commentNumber, reactionId);
    }

    /**
     * Method to delete a reaction to a team discussion comment. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @param commentNumber:    the number that identifies the comment
     * @param reactionId:       the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-comment-reaction">
     * Delete team discussion comment reaction</a>
     **/
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/comments/{comment_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionCommentReaction(String org, String teamSlug, long discussionNumber,
                                                       long commentNumber, long reactionId) {
        try {
            sendDeleteRequest(ORGS_PATH + org + TEAMS_PATH + "/" + teamSlug + DISCUSSIONS_PATH + discussionNumber
                    + COMMENTS_PATH + "/" + commentNumber + REACTIONS_PATH + "/" + reactionId);
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
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:        the organization from fetch the list
     * @param team:       the team from fetch the list
     * @param discussion: the discussion from fetch the list
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionReactions(Organization org, Team team,
                                                          Discussion discussion) throws IOException {
        return getTeamDiscussionReactions(org.getLogin(), team.getSlug(), discussion.getNumber(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:        the organization from fetch the list
     * @param team:       the team from fetch the list
     * @param discussion: the discussion from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public <T> T getTeamDiscussionReactions(Organization org, Team team, Discussion discussion,
                                            ReturnFormat format) throws IOException {
        return getTeamDiscussionReactions(org.getLogin(), team.getSlug(), discussion.getNumber(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization from fetch the list
     * @param team:             the team from fetch the list
     * @param discussionNumber: the number that identifies the discussion
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionReactions(Organization org, Team team,
                                                          long discussionNumber) throws IOException {
        return getTeamDiscussionReactions(org.getLogin(), team.getSlug(), discussionNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization from fetch the list
     * @param team:             the team from fetch the list
     * @param discussionNumber: the number that identifies the discussion
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public <T> T getTeamDiscussionReactions(Organization org, Team team, long discussionNumber,
                                            ReturnFormat format) throws IOException {
        return getTeamDiscussionReactions(org.getLogin(), team.getSlug(), discussionNumber, format);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param team:       the team from fetch the list
     * @param discussion: the discussion from fetch the list
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionReactions(String org, Team team, Discussion discussion) throws IOException {
        return getTeamDiscussionReactions(org, team.getSlug(), discussion.getNumber(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param team:       the team from fetch the list
     * @param discussion: the discussion from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public <T> T getTeamDiscussionReactions(String org, Team team, Discussion discussion,
                                            ReturnFormat format) throws IOException {
        return getTeamDiscussionReactions(org, team.getSlug(), discussion.getNumber(), format);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param team:             the team from fetch the list
     * @param discussionNumber: the number that identifies the discussion
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionReactions(String org, Team team, long discussionNumber) throws IOException {
        return getTeamDiscussionReactions(org, team.getSlug(), discussionNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param team:             the team from fetch the list
     * @param discussionNumber: the number that identifies the discussion
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public <T> T getTeamDiscussionReactions(String org, Team team, long discussionNumber,
                                            ReturnFormat format) throws IOException {
        return getTeamDiscussionReactions(org, team.getSlug(), discussionNumber, format);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:        the organization from fetch the list
     * @param teamSlug:   the slug of the team name
     * @param discussion: the discussion from fetch the list
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionReactions(Organization org, String teamSlug,
                                                          Discussion discussion) throws IOException {
        return getTeamDiscussionReactions(org.getLogin(), teamSlug, discussion.getNumber(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:        the organization from fetch the list
     * @param teamSlug:   the slug of the team name
     * @param discussion: the discussion from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public <T> T getTeamDiscussionReactions(Organization org, String teamSlug, Discussion discussion,
                                            ReturnFormat format) throws IOException {
        return getTeamDiscussionReactions(org.getLogin(), teamSlug, discussion.getNumber(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization from fetch the list
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionReactions(Organization org, String teamSlug,
                                                          long discussionNumber) throws IOException {
        return getTeamDiscussionReactions(org.getLogin(), teamSlug, discussionNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization from fetch the list
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public <T> T getTeamDiscussionReactions(Organization org, String teamSlug, long discussionNumber,
                                            ReturnFormat format) throws IOException {
        return getTeamDiscussionReactions(org.getLogin(), teamSlug, discussionNumber, format);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param teamSlug:   the slug of the team name
     * @param discussion: the discussion from fetch the list
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionReactions(String org, String teamSlug,
                                                          Discussion discussion) throws IOException {
        return getTeamDiscussionReactions(org, teamSlug, discussion.getNumber(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param teamSlug:   the slug of the team name
     * @param discussion: the discussion from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public <T> T getTeamDiscussionReactions(String org, String teamSlug, Discussion discussion,
                                            ReturnFormat format) throws IOException {
        return getTeamDiscussionReactions(org, teamSlug, discussion.getNumber(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionReactions(String org, String teamSlug,
                                                          long discussionNumber) throws IOException {
        return getTeamDiscussionReactions(org, teamSlug, discussionNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public <T> T getTeamDiscussionReactions(String org, String teamSlug, long discussionNumber,
                                            ReturnFormat format) throws IOException {
        return returnReactions(sendGetRequest(ORGS_PATH + org + TEAMS_PATH + "/" + teamSlug + DISCUSSIONS_PATH
                + discussionNumber + REACTIONS_PATH), format);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:         the organization from fetch the list
     * @param team:        the team from fetch the list
     * @param discussion:  the discussion from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionReactions(Organization org, Team team, Discussion discussion,
                                                          Params queryParams) throws IOException {
        return getTeamDiscussionReactions(org.getLogin(), team.getSlug(), discussion.getNumber(), queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:         the organization from fetch the list
     * @param team:        the team from fetch the list
     * @param discussion:  the discussion from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public <T> T getTeamDiscussionReactions(Organization org, Team team, Discussion discussion, Params queryParams,
                                            ReturnFormat format) throws IOException {
        return getTeamDiscussionReactions(org.getLogin(), team.getSlug(), discussion.getNumber(), queryParams, format);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization from fetch the list
     * @param team:             the team from fetch the list
     * @param discussionNumber: the number that identifies the discussion
     * @param queryParams:      extra query params not mandatory, keys accepted are:
     *                          <ul>
     *                             <li>
     *                                 {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                                 reactions, constants available {@link ReactionContent} - [string]
     *                             </li>
     *                             <li>
     *                                 {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                             </li>
     *                             <li>
     *                                 {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                             </li>
     *                          </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionReactions(Organization org, Team team, long discussionNumber,
                                                          Params queryParams) throws IOException {
        return getTeamDiscussionReactions(org.getLogin(), team.getSlug(), discussionNumber, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization from fetch the list
     * @param team:             the team from fetch the list
     * @param discussionNumber: the number that identifies the discussion
     * @param queryParams:      extra query params not mandatory, keys accepted are:
     *                          <ul>
     *                             <li>
     *                                 {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                                 reactions, constants available {@link ReactionContent} - [string]
     *                             </li>
     *                             <li>
     *                                 {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                             </li>
     *                             <li>
     *                                 {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                             </li>
     *                          </ul>
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public <T> T getTeamDiscussionReactions(Organization org, Team team, long discussionNumber, Params queryParams,
                                            ReturnFormat format) throws IOException {
        return getTeamDiscussionReactions(org.getLogin(), team.getSlug(), discussionNumber, queryParams, format);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param team:        the team from fetch the list
     * @param discussion:  the discussion from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionReactions(String org, Team team, Discussion discussion,
                                                          Params queryParams) throws IOException {
        return getTeamDiscussionReactions(org, team.getSlug(), discussion.getNumber(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param team:        the team from fetch the list
     * @param discussion:  the discussion from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public <T> T getTeamDiscussionReactions(String org, Team team, Discussion discussion, Params queryParams,
                                            ReturnFormat format) throws IOException {
        return getTeamDiscussionReactions(org, team.getSlug(), discussion.getNumber(), queryParams, format);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param team:             the team from fetch the list
     * @param discussionNumber: the number that identifies the discussion
     * @param queryParams:      extra query params not mandatory, keys accepted are:
     *                          <ul>
     *                             <li>
     *                                 {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                                 reactions, constants available {@link ReactionContent} - [string]
     *                             </li>
     *                             <li>
     *                                 {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                             </li>
     *                             <li>
     *                                 {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                             </li>
     *                          </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionReactions(String org, Team team, long discussionNumber,
                                                          Params queryParams) throws IOException {
        return getTeamDiscussionReactions(org, team.getSlug(), discussionNumber, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param team:             the team from fetch the list
     * @param discussionNumber: the number that identifies the discussion
     * @param queryParams:      extra query params not mandatory, keys accepted are:
     *                          <ul>
     *                             <li>
     *                                 {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                                 reactions, constants available {@link ReactionContent} - [string]
     *                             </li>
     *                             <li>
     *                                 {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                             </li>
     *                             <li>
     *                                 {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                             </li>
     *                          </ul>
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public <T> T getTeamDiscussionReactions(String org, Team team, long discussionNumber, Params queryParams,
                                            ReturnFormat format) throws IOException {
        return getTeamDiscussionReactions(org, team.getSlug(), discussionNumber, queryParams, format);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:         the organization from fetch the list
     * @param teamSlug:    the slug of the team name
     * @param discussion:  the discussion from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionReactions(Organization org, String teamSlug, Discussion discussion,
                                                          Params queryParams) throws IOException {
        return getTeamDiscussionReactions(org.getLogin(), teamSlug, discussion.getNumber(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:         the organization from fetch the list
     * @param teamSlug:    the slug of the team name
     * @param discussion:  the discussion from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public <T> T getTeamDiscussionReactions(Organization org, String teamSlug, Discussion discussion, Params queryParams,
                                            ReturnFormat format) throws IOException {
        return getTeamDiscussionReactions(org.getLogin(), teamSlug, discussion.getNumber(), queryParams, format);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization from fetch the list
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @param queryParams:      extra query params not mandatory, keys accepted are:
     *                          <ul>
     *                             <li>
     *                                 {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                                 reactions, constants available {@link ReactionContent} - [string]
     *                             </li>
     *                             <li>
     *                                 {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                             </li>
     *                             <li>
     *                                 {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                             </li>
     *                          </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionReactions(Organization org, String teamSlug, long discussionNumber,
                                                          Params queryParams) throws IOException {
        return getTeamDiscussionReactions(org.getLogin(), teamSlug, discussionNumber, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization from fetch the list
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @param queryParams:      extra query params not mandatory, keys accepted are:
     *                          <ul>
     *                             <li>
     *                                 {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                                 reactions, constants available {@link ReactionContent} - [string]
     *                             </li>
     *                             <li>
     *                                 {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                             </li>
     *                             <li>
     *                                 {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                             </li>
     *                          </ul>
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public <T> T getTeamDiscussionReactions(Organization org, String teamSlug, long discussionNumber, Params queryParams,
                                            ReturnFormat format) throws IOException {
        return getTeamDiscussionReactions(org.getLogin(), teamSlug, discussionNumber, queryParams, format);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param teamSlug:    the slug of the team name
     * @param discussion:  the discussion from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionReactions(String org, String teamSlug, Discussion discussion,
                                                          Params queryParams) throws IOException {
        return getTeamDiscussionReactions(org, teamSlug, discussion.getNumber(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:         the organization name. The name is not case-sensitive
     * @param teamSlug:    the slug of the team name
     * @param discussion:  the discussion from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public <T> T getTeamDiscussionReactions(String org, String teamSlug, Discussion discussion, Params queryParams,
                                            ReturnFormat format) throws IOException {
        return getTeamDiscussionReactions(org, teamSlug, discussion.getNumber(), queryParams, format);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @param queryParams:      extra query params not mandatory, keys accepted are:
     *                          <ul>
     *                             <li>
     *                                 {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                                 reactions, constants available {@link ReactionContent} - [string]
     *                             </li>
     *                             <li>
     *                                 {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                             </li>
     *                             <li>
     *                                 {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                             </li>
     *                          </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public ArrayList<Reaction> getTeamDiscussionReactions(String org, String teamSlug, long discussionNumber,
                                                          Params queryParams) throws IOException {
        return getTeamDiscussionReactions(org, teamSlug, discussionNumber, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the reactions to a team discussion. <br>
     * OAuth access tokens require the {@code "read:discussion"} scope.
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @param queryParams:      extra query params not mandatory, keys accepted are:
     *                          <ul>
     *                             <li>
     *                                 {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                                 reactions, constants available {@link ReactionContent} - [string]
     *                             </li>
     *                             <li>
     *                                 {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                             </li>
     *                             <li>
     *                                 {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                             </li>
     *                          </ul>
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-team-discussion">
     * List reactions for a team discussion</a>
     **/
    @RequestPath(method = GET, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public <T> T getTeamDiscussionReactions(String org, String teamSlug, long discussionNumber, Params queryParams,
                                            ReturnFormat format) throws IOException {
        return returnReactions(sendGetRequest(ORGS_PATH + org + TEAMS_PATH + "/" + teamSlug + DISCUSSIONS_PATH
                + discussionNumber + REACTIONS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a reaction to a team discussion. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:        the organization where create the reaction
     * @param team:       the team where create the reaction
     * @param discussion: the discussion where create the reaction
     * @param content:    the reaction type to add to the team discussion comment
     * @return reaction as {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion">
     * Create reaction for a team discussion</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public Reaction createTeamDiscussionReaction(Organization org, Team team, Discussion discussion,
                                                 ReactionContent content) throws IOException {
        return createTeamDiscussionReaction(org.getLogin(), team.getSlug(), discussion.getNumber(), content,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to a team discussion. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:        the organization where create the reaction
     * @param team:       the team where create the reaction
     * @param discussion: the discussion where create the reaction
     * @param content:    the reaction type to add to the team discussion comment
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion">
     * Create reaction for a team discussion</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public <T> T createTeamDiscussionReaction(Organization org, Team team, Discussion discussion, ReactionContent content,
                                              ReturnFormat format) throws IOException {
        return createTeamDiscussionReaction(org.getLogin(), team.getSlug(), discussion.getNumber(), content, format);
    }

    /**
     * Method to create a reaction to a team discussion. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:              the organization where create the reaction
     * @param team:             the team where create the reaction
     * @param discussionNumber: the number that identifies the discussion
     * @param content:          the reaction type to add to the team discussion comment
     * @return reaction as {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion">
     * Create reaction for a team discussion</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public Reaction createTeamDiscussionReaction(Organization org, Team team, long discussionNumber,
                                                 ReactionContent content) throws IOException {
        return createTeamDiscussionReaction(org.getLogin(), team.getSlug(), discussionNumber, content, LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to a team discussion. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:              the organization where create the reaction
     * @param team:             the team where create the reaction
     * @param discussionNumber: the number that identifies the discussion
     * @param content:          the reaction type to add to the team discussion comment
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion">
     * Create reaction for a team discussion</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public <T> T createTeamDiscussionReaction(Organization org, Team team, long discussionNumber, ReactionContent content,
                                              ReturnFormat format) throws IOException {
        return createTeamDiscussionReaction(org.getLogin(), team.getSlug(), discussionNumber, content, format);
    }

    /**
     * Method to create a reaction to a team discussion. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param team:       the team where create the reaction
     * @param discussion: the discussion where create the reaction
     * @param content:    the reaction type to add to the team discussion comment
     * @return reaction as {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion">
     * Create reaction for a team discussion</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public Reaction createTeamDiscussionReaction(String org, Team team, Discussion discussion,
                                                 ReactionContent content) throws IOException {
        return createTeamDiscussionReaction(org, team.getSlug(), discussion.getNumber(), content, LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to a team discussion. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param team:       the team where create the reaction
     * @param discussion: the discussion where create the reaction
     * @param content:    the reaction type to add to the team discussion comment
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion">
     * Create reaction for a team discussion</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public <T> T createTeamDiscussionReaction(String org, Team team, Discussion discussion, ReactionContent content,
                                              ReturnFormat format) throws IOException {
        return createTeamDiscussionReaction(org, team.getSlug(), discussion.getNumber(), content, format);
    }

    /**
     * Method to create a reaction to a team discussion. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param team:             the team where create the reaction
     * @param discussionNumber: the number that identifies the discussion
     * @param content:          the reaction type to add to the team discussion comment
     * @return reaction as {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion">
     * Create reaction for a team discussion</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public Reaction createTeamDiscussionReaction(String org, Team team, long discussionNumber,
                                                 ReactionContent content) throws IOException {
        return createTeamDiscussionReaction(org, team.getSlug(), discussionNumber, content, LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to a team discussion. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param team:             the team where create the reaction
     * @param discussionNumber: the number that identifies the discussion
     * @param content:          the reaction type to add to the team discussion comment
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion">
     * Create reaction for a team discussion</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public <T> T createTeamDiscussionReaction(String org, Team team, long discussionNumber, ReactionContent content,
                                              ReturnFormat format) throws IOException {
        return createTeamDiscussionReaction(org, team.getSlug(), discussionNumber, content, format);
    }

    /**
     * Method to create a reaction to a team discussion. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:        the organization where create the reaction
     * @param teamSlug:   the slug of the team name
     * @param discussion: the discussion where create the reaction
     * @param content:    the reaction type to add to the team discussion comment
     * @return reaction as {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion">
     * Create reaction for a team discussion</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public Reaction createTeamDiscussionReaction(Organization org, String teamSlug, Discussion discussion,
                                                 ReactionContent content) throws IOException {
        return createTeamDiscussionReaction(org.getLogin(), teamSlug, discussion.getNumber(), content, LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to a team discussion. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:        the organization where create the reaction
     * @param teamSlug:   the slug of the team name
     * @param discussion: the discussion where create the reaction
     * @param content:    the reaction type to add to the team discussion comment
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion">
     * Create reaction for a team discussion</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public <T> T createTeamDiscussionReaction(Organization org, String teamSlug, Discussion discussion,
                                              ReactionContent content, ReturnFormat format) throws IOException {
        return createTeamDiscussionReaction(org.getLogin(), teamSlug, discussion.getNumber(), content, format);
    }

    /**
     * Method to create a reaction to a team discussion. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:              the organization where create the reaction
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @param content:          the reaction type to add to the team discussion comment
     * @return reaction as {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion">
     * Create reaction for a team discussion</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public Reaction createTeamDiscussionReaction(Organization org, String teamSlug, long discussionNumber,
                                                 ReactionContent content) throws IOException {
        return createTeamDiscussionReaction(org.getLogin(), teamSlug, discussionNumber, content, LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to a team discussion. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:              the organization where create the reaction
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @param content:          the reaction type to add to the team discussion comment
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion">
     * Create reaction for a team discussion</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public <T> T createTeamDiscussionReaction(Organization org, String teamSlug, long discussionNumber,
                                              ReactionContent content, ReturnFormat format) throws IOException {
        return createTeamDiscussionReaction(org.getLogin(), teamSlug, discussionNumber, content, format);
    }

    /**
     * Method to create a reaction to a team discussion. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param teamSlug:   the slug of the team name
     * @param discussion: the discussion from fetch the list
     * @param content:    the reaction type to add to the team discussion comment
     * @return reaction as {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion">
     * Create reaction for a team discussion</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public Reaction createTeamDiscussionReaction(String org, String teamSlug, Discussion discussion,
                                                 ReactionContent content) throws IOException {
        return createTeamDiscussionReaction(org, teamSlug, discussion.getNumber(), content, LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to a team discussion. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param teamSlug:   the slug of the team name
     * @param discussion: the discussion from fetch the list
     * @param content:    the reaction type to add to the team discussion comment
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion">
     * Create reaction for a team discussion</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public <T> T createTeamDiscussionReaction(String org, String teamSlug, Discussion discussion, ReactionContent content,
                                              ReturnFormat format) throws IOException {
        return createTeamDiscussionReaction(org, teamSlug, discussion.getNumber(), content, format);
    }

    /**
     * Method to create a reaction to a team discussion. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @param content:          the reaction type to add to the team discussion comment
     * @return reaction as {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion">
     * Create reaction for a team discussion</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public Reaction createTeamDiscussionReaction(String org, String teamSlug, long discussionNumber,
                                                 ReactionContent content) throws IOException {
        return createTeamDiscussionReaction(org, teamSlug, discussionNumber, content, LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to a team discussion. OAuth access tokens require {@code "the write:discussion"}
     * scope. A response with an HTTP 200 status means that you already added the reaction type to this team discussion comment
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @param content:          the reaction type to add to the team discussion comment
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-team-discussion">
     * Create reaction for a team discussion</a>
     **/
    @RequestPath(method = POST, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions")
    public <T> T createTeamDiscussionReaction(String org, String teamSlug, long discussionNumber, ReactionContent content,
                                              ReturnFormat format) throws IOException {
        return returnReaction(sendPostRequest(ORGS_PATH + org + TEAMS_PATH + "/" + teamSlug + DISCUSSIONS_PATH
                + discussionNumber + REACTIONS_PATH, createReactionPayload(content)), format);
    }

    /**
     * Method to delete a reaction to a team discussion. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:        the organization where delete the reaction
     * @param team:       the team where delete the reaction
     * @param discussion: the discussion where delete the reaction
     * @param reaction:   the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-reaction">
     * Delete team discussion reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionReaction(Organization org, Team team, Discussion discussion, Reaction reaction) {
        return deleteTeamDiscussionReaction(org.getLogin(), team.getSlug(), discussion.getNumber(), reaction.getId());
    }

    /**
     * Method to delete a reaction to a team discussion. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:              the organization where delete the reaction
     * @param team:             the team where delete the reaction
     * @param discussionNumber: the number that identifies the discussion
     * @param reaction:         the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-reaction">
     * Delete team discussion reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionReaction(Organization org, Team team, long discussionNumber, Reaction reaction) {
        return deleteTeamDiscussionReaction(org.getLogin(), team.getSlug(), discussionNumber, reaction.getId());
    }

    /**
     * Method to delete a reaction to a team discussion. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:        the organization where delete the reaction
     * @param teamSlug:   the slug of the team name
     * @param discussion: the discussion where delete the reaction
     * @param reaction:   the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-reaction">
     * Delete team discussion reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionReaction(Organization org, String teamSlug, Discussion discussion,
                                                Reaction reaction) {
        return deleteTeamDiscussionReaction(org.getLogin(), teamSlug, discussion.getNumber(), reaction.getId());
    }

    /**
     * Method to delete a reaction to a team discussion. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:              the organization where delete the reaction
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @param reaction:         the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-reaction">
     * Delete team discussion reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionReaction(Organization org, String teamSlug, long discussionNumber,
                                                Reaction reaction) {
        return deleteTeamDiscussionReaction(org.getLogin(), teamSlug, discussionNumber, reaction.getId());
    }

    /**
     * Method to delete a reaction to a team discussion. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param teamSlug:   the slug of the team name
     * @param discussion: the discussion where delete the reaction
     * @param reaction:   the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-reaction">
     * Delete team discussion reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionReaction(String org, String teamSlug, Discussion discussion, Reaction reaction) {
        return deleteTeamDiscussionReaction(org, teamSlug, discussion.getNumber(), reaction.getId());
    }

    /**
     * Method to delete a reaction to a team discussion. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @param reaction:         the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-reaction">
     * Delete team discussion reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionReaction(String org, String teamSlug, long discussionNumber, Reaction reaction) {
        return deleteTeamDiscussionReaction(org, teamSlug, discussionNumber, reaction.getId());
    }

    /**
     * Method to delete a reaction to a team discussion. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param team:       the team where delete the reaction
     * @param discussion: the discussion where delete the reaction
     * @param reaction:   the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-reaction">
     * Delete team discussion reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionReaction(String org, Team team, Discussion discussion, Reaction reaction) {
        return deleteTeamDiscussionReaction(org, team.getSlug(), discussion.getNumber(), reaction.getId());
    }

    /**
     * Method to delete a reaction to a team discussion. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param team:             the team where delete the reaction
     * @param discussionNumber: the number that identifies the discussion
     * @param reaction:         the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-reaction">
     * Delete team discussion reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionReaction(String org, Team team, long discussionNumber, Reaction reaction) {
        return deleteTeamDiscussionReaction(org, team.getSlug(), discussionNumber, reaction.getId());
    }

    /**
     * Method to delete a reaction to a team discussion. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param team:       the team where delete the reaction
     * @param discussion: the discussion where delete the reaction
     * @param reactionId: the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-reaction">
     * Delete team discussion reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionReaction(String org, Team team, Discussion discussion, long reactionId) {
        return deleteTeamDiscussionReaction(org, team.getSlug(), discussion.getNumber(), reactionId);
    }

    /**
     * Method to delete a reaction to a team discussion. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param team:             the team where delete the reaction
     * @param discussionNumber: the number that identifies the discussion
     * @param reactionId:       the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-reaction">
     * Delete team discussion reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionReaction(String org, Team team, long discussionNumber, long reactionId) {
        return deleteTeamDiscussionReaction(org, team.getSlug(), discussionNumber, reactionId);
    }

    /**
     * Method to delete a reaction to a team discussion. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:        the organization where delete the reaction
     * @param team:       the team where delete the reaction
     * @param discussion: the discussion where delete the reaction
     * @param reactionId: the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-reaction">
     * Delete team discussion reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionReaction(Organization org, Team team, Discussion discussion, long reactionId) {
        return deleteTeamDiscussionReaction(org.getLogin(), team.getSlug(), discussion.getNumber(), reactionId);

    }

    /**
     * Method to delete a reaction to a team discussion. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:              the organization where delete the reaction
     * @param team:             the team where delete the reaction
     * @param discussionNumber: the number that identifies the discussion
     * @param reactionId:       the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-reaction">
     * Delete team discussion reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionReaction(Organization org, Team team, long discussionNumber, long reactionId) {
        return deleteTeamDiscussionReaction(org.getLogin(), team.getSlug(), discussionNumber, reactionId);

    }

    /**
     * Method to delete a reaction to a team discussion. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:        the organization where delete the reaction
     * @param teamSlug:   the slug of the team name
     * @param discussion: the discussion where delete the reaction
     * @param reactionId: the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-reaction">
     * Delete team discussion reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionReaction(Organization org, String teamSlug, Discussion discussion, long reactionId) {
        return deleteTeamDiscussionReaction(org.getLogin(), teamSlug, discussion.getNumber(), reactionId);
    }

    /**
     * Method to delete a reaction to a team discussion. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:              the organization where delete the reaction
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @param reactionId:       the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-reaction">
     * Delete team discussion reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionReaction(Organization org, String teamSlug, long discussionNumber, long reactionId) {
        return deleteTeamDiscussionReaction(org.getLogin(), teamSlug, discussionNumber, reactionId);
    }

    /**
     * Method to delete a reaction to a team discussion. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:        the organization name. The name is not case-sensitive
     * @param teamSlug:   the slug of the team name
     * @param discussion: the discussion where delete the reaction
     * @param reactionId: the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-reaction">
     * Delete team discussion reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionReaction(String org, String teamSlug, Discussion discussion, long reactionId) {
        return deleteTeamDiscussionReaction(org, teamSlug, discussion.getNumber(), reactionId);
    }

    /**
     * Method to delete a reaction to a team discussion. OAuth access tokens require the {@code "write:discussion"}
     * scope.
     *
     * @param org:              the organization name. The name is not case-sensitive
     * @param teamSlug:         the slug of the team name
     * @param discussionNumber: the number that identifies the discussion
     * @param reactionId:       the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-team-discussion-reaction">
     * Delete team discussion reaction</a>
     **/
    @RequestPath(method = DELETE, path = "/orgs/{org}/teams/{team_slug}/discussions/{discussion_number}/reactions/{reaction_id}")
    public boolean deleteTeamDiscussionReaction(String org, String teamSlug, long discussionNumber, long reactionId) {
        try {
            sendDeleteRequest(ORGS_PATH + org + TEAMS_PATH + "/" + teamSlug + DISCUSSIONS_PATH + discussionNumber
                    + REACTIONS_PATH + "/" + reactionId);
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
     * Method to get the list the reactions to a commit comment
     *
     * @param repository: the repository from fetch the list
     * @param comment:    the comment from fetch the list
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-commit-comment">
     * List reactions for a commit comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions")
    public ArrayList<Reaction> getCommitCommentReactions(Repository repository, CommitComment comment) throws IOException {
        return getCommitCommentReactions(repository.getOwner().getLogin(), repository.getName(), comment.getId(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list the reactions to a commit comment
     *
     * @param repository: the repository from fetch the list
     * @param comment:    the comment from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-commit-comment">
     * List reactions for a commit comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions")
    public <T> T getCommitCommentReactions(Repository repository, CommitComment comment,
                                           ReturnFormat format) throws IOException {
        return getCommitCommentReactions(repository.getOwner().getLogin(), repository.getName(), comment.getId(), format);
    }

    /**
     * Method to get the list the reactions to a commit comment
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param comment: the comment from fetch the list
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-commit-comment">
     * List reactions for a commit comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions")
    public ArrayList<Reaction> getCommitCommentReactions(String owner, String repo, CommitComment comment) throws IOException {
        return getCommitCommentReactions(owner, repo, comment.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list the reactions to a commit comment
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param comment: the comment from fetch the list
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-commit-comment">
     * List reactions for a commit comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions")
    public <T> T getCommitCommentReactions(String owner, String repo, CommitComment comment,
                                           ReturnFormat format) throws IOException {
        return getCommitCommentReactions(owner, repo, comment.getId(), format);
    }

    /**
     * Method to get the list the reactions to a commit comment
     *
     * @param repository: the repository from fetch the list
     * @param commentId:  the unique identifier of the comment
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-commit-comment">
     * List reactions for a commit comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions")
    public ArrayList<Reaction> getCommitCommentReactions(Repository repository, long commentId) throws IOException {
        return getCommitCommentReactions(repository.getOwner().getLogin(), repository.getName(), commentId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list the reactions to a commit comment
     *
     * @param repository: the repository from fetch the list
     * @param commentId:  the unique identifier of the comment
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-commit-comment">
     * List reactions for a commit comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions")
    public <T> T getCommitCommentReactions(Repository repository, long commentId, ReturnFormat format) throws IOException {
        return getCommitCommentReactions(repository.getOwner().getLogin(), repository.getName(), commentId, format);
    }

    /**
     * Method to get the list the reactions to a commit comment
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commentId: the unique identifier of the comment
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-commit-comment">
     * List reactions for a commit comment</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions")
    public ArrayList<Reaction> getCommitCommentReactions(String owner, String repo, long commentId) throws IOException {
        return getCommitCommentReactions(owner, repo, commentId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list the reactions to a commit comment
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commentId: the unique identifier of the comment
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-commit-comment">
     * List reactions for a commit comment</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions")
    public <T> T getCommitCommentReactions(String owner, String repo, long commentId, ReturnFormat format) throws IOException {
        return returnReactions(sendGetRequest(REPOS_PATH + owner + "/" + repo + COMMENTS_PATH + "/" + commentId
                + REACTIONS_PATH), format);
    }

    /**
     * Method to get the list the reactions to a commit comment
     *
     * @param repository:  the repository from fetch the list
     * @param comment:     the comment from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-commit-comment">
     * List reactions for a commit comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions")
    public ArrayList<Reaction> getCommitCommentReactions(Repository repository, CommitComment comment,
                                                         Params queryParams) throws IOException {
        return getCommitCommentReactions(repository.getOwner().getLogin(), repository.getName(), comment.getId(),
                queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list the reactions to a commit comment
     *
     * @param repository:  the repository from fetch the list
     * @param comment:     the comment from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-commit-comment">
     * List reactions for a commit comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions")
    public <T> T getCommitCommentReactions(Repository repository, CommitComment comment, Params queryParams,
                                           ReturnFormat format) throws IOException {
        return getCommitCommentReactions(repository.getOwner().getLogin(), repository.getName(), comment.getId(),
                queryParams, format);
    }

    /**
     * Method to get the list the reactions to a commit comment
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param comment:     the comment from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-commit-comment">
     * List reactions for a commit comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions")
    public ArrayList<Reaction> getCommitCommentReactions(String owner, String repo, CommitComment comment,
                                                         Params queryParams) throws IOException {
        return getCommitCommentReactions(owner, repo, comment.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list the reactions to a commit comment
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param comment:     the comment from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-commit-comment">
     * List reactions for a commit comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions")
    public <T> T getCommitCommentReactions(String owner, String repo, CommitComment comment, Params queryParams,
                                           ReturnFormat format) throws IOException {
        return getCommitCommentReactions(owner, repo, comment.getId(), queryParams, format);
    }

    /**
     * Method to get the list the reactions to a commit comment
     *
     * @param repository:  the repository from fetch the list
     * @param commentId:   the unique identifier of the comment
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-commit-comment">
     * List reactions for a commit comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions")
    public ArrayList<Reaction> getCommitCommentReactions(Repository repository, long commentId,
                                                         Params queryParams) throws IOException {
        return getCommitCommentReactions(repository.getOwner().getLogin(), repository.getName(), commentId, queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list the reactions to a commit comment
     *
     * @param repository:  the repository from fetch the list
     * @param commentId:   the unique identifier of the comment
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-commit-comment">
     * List reactions for a commit comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions")
    public <T> T getCommitCommentReactions(Repository repository, long commentId, Params queryParams,
                                           ReturnFormat format) throws IOException {
        return getCommitCommentReactions(repository.getOwner().getLogin(), repository.getName(), commentId, queryParams,
                format);
    }

    /**
     * Method to get the list the reactions to a commit comment
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param commentId:   the unique identifier of the comment
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-commit-comment">
     * List reactions for a commit comment</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions")
    public ArrayList<Reaction> getCommitCommentReactions(String owner, String repo, long commentId,
                                                         Params queryParams) throws IOException {
        return getCommitCommentReactions(owner, repo, commentId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list the reactions to a commit comment
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param commentId:   the unique identifier of the comment
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-commit-comment">
     * List reactions for a commit comment</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions")
    public <T> T getCommitCommentReactions(String owner, String repo, long commentId, Params queryParams,
                                           ReturnFormat format) throws IOException {
        return returnReactions(sendGetRequest(REPOS_PATH + owner + "/" + repo + COMMENTS_PATH + "/" + commentId
                + REACTIONS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a reaction to a commit comment. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param repository: the repository where create the reaction
     * @param comment:    the comment where create the reaction
     * @param content:    the reaction type to add to the team discussion comment
     * @return reaction as of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-commit-comment">
     * Create reaction for a commit comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions")
    public Reaction createCommitCommentReaction(Repository repository, CommitComment comment,
                                                ReactionContent content) throws IOException {
        return createCommitCommentReaction(repository.getOwner().getLogin(), repository.getName(), comment.getId(),
                content, LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to a commit comment. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param repository: the repository where create the reaction
     * @param comment:    the comment where create the reaction
     * @param content:    the reaction type to add to the team discussion comment
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-commit-comment">
     * Create reaction for a commit comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions")
    public <T> T createCommitCommentReaction(Repository repository, CommitComment comment, ReactionContent content,
                                             ReturnFormat format) throws IOException {
        return createCommitCommentReaction(repository.getOwner().getLogin(), repository.getName(), comment.getId(),
                content, format);
    }

    /**
     * Method to create a reaction to a commit comment. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param comment: the comment where create the reaction
     * @param content: the reaction type to add to the team discussion comment
     * @return reaction as of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-commit-comment">
     * Create reaction for a commit comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions")
    public Reaction createCommitCommentReaction(String owner, String repo, CommitComment comment,
                                                ReactionContent content) throws IOException {
        return createCommitCommentReaction(owner, repo, comment.getId(), content, LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to a commit comment. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param comment: the comment where create the reaction
     * @param content: the reaction type to add to the team discussion comment
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-commit-comment">
     * Create reaction for a commit comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions")
    public <T> T createCommitCommentReaction(String owner, String repo, CommitComment comment, ReactionContent content,
                                             ReturnFormat format) throws IOException {
        return createCommitCommentReaction(owner, repo, comment.getId(), content, format);
    }

    /**
     * Method to create a reaction to a commit comment. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param repository: the repository where create the reaction
     * @param commentId:  the unique identifier of the comment
     * @param content:    the reaction type to add to the team discussion comment
     * @return reaction as of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-commit-comment">
     * Create reaction for a commit comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions")
    public Reaction createCommitCommentReaction(Repository repository, long commentId,
                                                ReactionContent content) throws IOException {
        return createCommitCommentReaction(repository.getOwner().getLogin(), repository.getName(), commentId, content,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to a commit comment. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param repository: the repository where create the reaction
     * @param commentId:  the unique identifier of the comment
     * @param content:    the reaction type to add to the team discussion comment
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-commit-comment">
     * Create reaction for a commit comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions")
    public <T> T createCommitCommentReaction(Repository repository, long commentId, ReactionContent content,
                                             ReturnFormat format) throws IOException {
        return createCommitCommentReaction(repository.getOwner().getLogin(), repository.getName(), commentId, content,
                format);
    }

    /**
     * Method to create a reaction to a commit comment. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commentId: the unique identifier of the comment
     * @param content:   the reaction type to add to the team discussion comment
     * @return reaction as of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-commit-comment">
     * Create reaction for a commit comment</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions")
    public Reaction createCommitCommentReaction(String owner, String repo, long commentId,
                                                ReactionContent content) throws IOException {
        return createCommitCommentReaction(owner, repo, commentId, content, LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to a commit comment. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commentId: the unique identifier of the comment
     * @param content:   the reaction type to add to the team discussion comment
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-commit-comment">
     * Create reaction for a commit comment</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions")
    public <T> T createCommitCommentReaction(String owner, String repo, long commentId, ReactionContent content,
                                             ReturnFormat format) throws IOException {
        return returnReaction(sendPostRequest(REPOS_PATH + owner + "/" + repo + COMMENTS_PATH + "/" + commentId
                + REACTIONS_PATH, createReactionPayload(content)), format);
    }

    /**
     * Method to delete a commit comment reaction
     *
     * @param repository: the repository where delete the reaction
     * @param comment:    the comment where delete the reaction
     * @param reaction:   the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-a-commit-comment-reaction">
     * Delete a commit comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions/{reaction_id}")
    public boolean deleteCommitCommentReaction(Repository repository, CommitComment comment, Reaction reaction) {
        return deleteCommitCommentReaction(repository.getOwner().getLogin(), repository.getName(), comment.getId(),
                reaction.getId());
    }

    /**
     * Method to delete a commit comment reaction
     *
     * @param repository: the repository where delete the reaction
     * @param commentId:  the unique identifier of the comment
     * @param reaction:   the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-a-commit-comment-reaction">
     * Delete a commit comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions/{reaction_id}")
    public boolean deleteCommitCommentReaction(Repository repository, long commentId, Reaction reaction) {
        return deleteCommitCommentReaction(repository.getOwner().getLogin(), repository.getName(), commentId,
                reaction.getId());
    }

    /**
     * Method to delete a commit comment reaction
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commentId: the unique identifier of the comment
     * @param reaction:  the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-a-commit-comment-reaction">
     * Delete a commit comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions/{reaction_id}")
    public boolean deleteCommitCommentReaction(String owner, String repo, long commentId, Reaction reaction) {
        return deleteCommitCommentReaction(owner, repo, commentId, reaction.getId());
    }

    /**
     * Method to delete a commit comment reaction
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param comment:  the comment where delete the reaction
     * @param reaction: the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-a-commit-comment-reaction">
     * Delete a commit comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions/{reaction_id}")
    public boolean deleteCommitCommentReaction(String owner, String repo, CommitComment comment, Reaction reaction) {
        return deleteCommitCommentReaction(owner, repo, comment.getId(), reaction.getId());
    }

    /**
     * Method to delete a commit comment reaction
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param comment:    the comment where delete the reaction
     * @param reactionId: the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-a-commit-comment-reaction">
     * Delete a commit comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions/{reaction_id}")
    public boolean deleteCommitCommentReaction(String owner, String repo, CommitComment comment, long reactionId) {
        return deleteCommitCommentReaction(owner, repo, comment.getId(), reactionId);
    }

    /**
     * Method to delete a commit comment reaction
     *
     * @param repository: the repository where delete the reaction
     * @param comment:    the comment where delete the reaction
     * @param reactionId: the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-a-commit-comment-reaction">
     * Delete a commit comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions/{reaction_id}")
    public boolean deleteCommitCommentReaction(Repository repository, CommitComment comment, long reactionId) {
        return deleteCommitCommentReaction(repository.getOwner().getLogin(), repository.getName(), comment.getId(),
                reactionId);
    }

    /**
     * Method to delete a commit comment reaction
     *
     * @param repository: the repository where delete the reaction
     * @param commentId:  the unique identifier of the comment
     * @param reactionId: the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-a-commit-comment-reaction">
     * Delete a commit comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions/{reaction_id}")
    public boolean deleteCommitCommentReaction(Repository repository, long commentId, long reactionId) {
        return deleteCommitCommentReaction(repository.getOwner().getLogin(), repository.getName(), commentId, reactionId);
    }

    /**
     * Method to delete a commit comment reaction
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param commentId:  the unique identifier of the comment
     * @param reactionId: the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-a-commit-comment-reaction">
     * Delete a commit comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/comments/{comment_id}/reactions/{reaction_id}")
    public boolean deleteCommitCommentReaction(String owner, String repo, long commentId, long reactionId) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + COMMENTS_PATH + "/" + commentId + REACTIONS_PATH
                    + "/" + reactionId);
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
     * Method to get the list reactions to an issue comment
     *
     * @param repository: the repository from fetch the list
     * @param comment:    the comment from fetch the list
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue-comment">
     * List reactions for an issue comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions")
    public ArrayList<Reaction> getIssueCommentReactions(Repository repository, IssueComment comment) throws IOException {
        return getIssueCommentReactions(repository.getOwner().getLogin(), repository.getName(), comment.getId(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list reactions to an issue comment
     *
     * @param repository: the repository from fetch the list
     * @param comment:    the comment from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue-comment">
     * List reactions for an issue comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions")
    public <T> T getIssueCommentReactions(Repository repository, IssueComment comment,
                                          ReturnFormat format) throws IOException {
        return getIssueCommentReactions(repository.getOwner().getLogin(), repository.getName(), comment.getId(),
                format);
    }

    /**
     * Method to get the list reactions to an issue comment
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param comment: the comment from fetch the list
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue-comment">
     * List reactions for an issue comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions")
    public ArrayList<Reaction> getIssueCommentReactions(String owner, String repo, IssueComment comment) throws IOException {
        return getIssueCommentReactions(owner, repo, comment.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list reactions to an issue comment
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param comment: the comment from fetch the list
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue-comment">
     * List reactions for an issue comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions")
    public <T> T getIssueCommentReactions(String owner, String repo, IssueComment comment,
                                          ReturnFormat format) throws IOException {
        return getIssueCommentReactions(owner, repo, comment.getId(), format);
    }

    /**
     * Method to get the list reactions to an issue comment
     *
     * @param repository: the repository from fetch the list
     * @param commentId:  the unique identifier of the comment
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue-comment">
     * List reactions for an issue comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions")
    public ArrayList<Reaction> getIssueCommentReactions(Repository repository, long commentId) throws IOException {
        return getIssueCommentReactions(repository.getOwner().getLogin(), repository.getName(), commentId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list reactions to an issue comment
     *
     * @param repository: the repository from fetch the list
     * @param commentId:  the unique identifier of the comment
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue-comment">
     * List reactions for an issue comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions")
    public <T> T getIssueCommentReactions(Repository repository, long commentId, ReturnFormat format) throws IOException {
        return getIssueCommentReactions(repository.getOwner().getLogin(), repository.getName(), commentId, format);
    }

    /**
     * Method to get the list reactions to an issue comment
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commentId: the unique identifier of the comment
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue-comment">
     * List reactions for an issue comment</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions")
    public ArrayList<Reaction> getIssueCommentReactions(String owner, String repo, long commentId) throws IOException {
        return getIssueCommentReactions(owner, repo, commentId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list reactions to an issue comment
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commentId: the unique identifier of the comment
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue-comment">
     * List reactions for an issue comment</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions")
    public <T> T getIssueCommentReactions(String owner, String repo, long commentId, ReturnFormat format) throws IOException {
        return returnReactions(sendGetRequest(REPOS_PATH + owner + "/" + repo + ISSUES_COMMENTS_PATH + "/"
                + commentId + REACTIONS_PATH), format);
    }

    /**
     * Method to get the list reactions to an issue comment
     *
     * @param repository:  the repository from fetch the list
     * @param comment:     the comment from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue-comment">
     * List reactions for an issue comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions")
    public ArrayList<Reaction> getIssueCommentReactions(Repository repository, IssueComment comment,
                                                        Params queryParams) throws IOException {
        return getIssueCommentReactions(repository.getOwner().getLogin(), repository.getName(), comment.getId(),
                queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list reactions to an issue comment
     *
     * @param repository:  the repository from fetch the list
     * @param comment:     the comment from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue-comment">
     * List reactions for an issue comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions")
    public <T> T getIssueCommentReactions(Repository repository, IssueComment comment, Params queryParams,
                                          ReturnFormat format) throws IOException {
        return getIssueCommentReactions(repository.getOwner().getLogin(), repository.getName(), comment.getId(),
                queryParams, format);
    }

    /**
     * Method to get the list reactions to an issue comment
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param comment:     the comment from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue-comment">
     * List reactions for an issue comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions")
    public ArrayList<Reaction> getIssueCommentReactions(String owner, String repo, IssueComment comment,
                                                        Params queryParams) throws IOException {
        return getIssueCommentReactions(owner, repo, comment.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list reactions to an issue comment
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param comment:     the comment from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue-comment">
     * List reactions for an issue comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions")
    public <T> T getIssueCommentReactions(String owner, String repo, IssueComment comment, Params queryParams,
                                          ReturnFormat format) throws IOException {
        return getIssueCommentReactions(owner, repo, comment.getId(), queryParams, format);
    }

    /**
     * Method to get the list reactions to an issue comment
     *
     * @param repository:  the repository from fetch the list
     * @param commentId:   the unique identifier of the comment
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue-comment">
     * List reactions for an issue comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions")
    public ArrayList<Reaction> getIssueCommentReactions(Repository repository, long commentId,
                                                        Params queryParams) throws IOException {
        return getIssueCommentReactions(repository.getOwner().getLogin(), repository.getName(), commentId, queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list reactions to an issue comment
     *
     * @param repository:  the repository from fetch the list
     * @param commentId:   the unique identifier of the comment
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue-comment">
     * List reactions for an issue comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions")
    public <T> T getIssueCommentReactions(Repository repository, long commentId, Params queryParams,
                                          ReturnFormat format) throws IOException {
        return getIssueCommentReactions(repository.getOwner().getLogin(), repository.getName(), commentId, queryParams,
                format);
    }

    /**
     * Method to get the list reactions to an issue comment
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param commentId:   the unique identifier of the comment
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue-comment">
     * List reactions for an issue comment</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions")
    public ArrayList<Reaction> getIssueCommentReactions(String owner, String repo, long commentId,
                                                        Params queryParams) throws IOException {
        return getIssueCommentReactions(owner, repo, commentId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list reactions to an issue comment
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param commentId:   the unique identifier of the comment
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue-comment">
     * List reactions for an issue comment</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions")
    public <T> T getIssueCommentReactions(String owner, String repo, long commentId, Params queryParams,
                                          ReturnFormat format) throws IOException {
        return returnReactions(sendGetRequest(REPOS_PATH + owner + "/" + repo + ISSUES_COMMENTS_PATH + "/"
                + commentId + REACTIONS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a reaction to an issue comment. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param repository: the repository where create the reaction
     * @param comment:    the comment where create the reaction
     * @param content:    the reaction type to add to the team discussion comment
     * @return reaction as of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-an-issue-comment">
     * Create reaction for an issue comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions")
    public Reaction createIssueCommentReaction(Repository repository, IssueComment comment,
                                               ReactionContent content) throws IOException {
        return createIssueCommentReaction(repository.getOwner().getLogin(), repository.getName(), comment.getId(),
                content, LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to an issue comment. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param repository: the repository where create the reaction
     * @param comment:    the comment where create the reaction
     * @param content:    the reaction type to add to the team discussion comment
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-an-issue-comment">
     * Create reaction for an issue comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions")
    public <T> T createIssueCommentReaction(Repository repository, IssueComment comment, ReactionContent content,
                                            ReturnFormat format) throws IOException {
        return createIssueCommentReaction(repository.getOwner().getLogin(), repository.getName(), comment.getId(),
                content, format);
    }

    /**
     * Method to create a reaction to an issue comment. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param comment: the comment where create the reaction
     * @param content: the reaction type to add to the team discussion comment
     * @return reaction as of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-an-issue-comment">
     * Create reaction for an issue comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions")
    public Reaction createIssueCommentReaction(String owner, String repo, IssueComment comment,
                                               ReactionContent content) throws IOException {
        return createIssueCommentReaction(owner, repo, comment.getId(), content, LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to an issue comment. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param comment: the comment where create the reaction
     * @param content: the reaction type to add to the team discussion comment
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-an-issue-comment">
     * Create reaction for an issue comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions")
    public <T> T createIssueCommentReaction(String owner, String repo, IssueComment comment, ReactionContent content,
                                            ReturnFormat format) throws IOException {
        return createIssueCommentReaction(owner, repo, comment.getId(), content, format);
    }

    /**
     * Method to create a reaction to an issue comment. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param repository: the repository where create the reaction
     * @param commentId:  the unique identifier of the comment
     * @param content:    the reaction type to add to the team discussion comment
     * @return reaction as of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-an-issue-comment">
     * Create reaction for an issue comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions")
    public Reaction createIssueCommentReaction(Repository repository, long commentId,
                                               ReactionContent content) throws IOException {
        return createIssueCommentReaction(repository.getOwner().getLogin(), repository.getName(), commentId, content,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to an issue comment. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param repository: the repository where create the reaction
     * @param commentId:  the unique identifier of the comment
     * @param content:    the reaction type to add to the team discussion comment
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-an-issue-comment">
     * Create reaction for an issue comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions")
    public <T> T createIssueCommentReaction(Repository repository, long commentId, ReactionContent content,
                                            ReturnFormat format) throws IOException {
        return createIssueCommentReaction(repository.getOwner().getLogin(), repository.getName(), commentId, content,
                format);
    }

    /**
     * Method to create a reaction to an issue comment. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commentId: the unique identifier of the comment
     * @param content:   the reaction type to add to the team discussion comment
     * @return reaction as of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-an-issue-comment">
     * Create reaction for an issue comment</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions")
    public Reaction createIssueCommentReaction(String owner, String repo, long commentId,
                                               ReactionContent content) throws IOException {
        return createIssueCommentReaction(owner, repo, commentId, content, LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to an issue comment. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commentId: the unique identifier of the comment
     * @param content:   the reaction type to add to the team discussion comment
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-an-issue-comment">
     * Create reaction for an issue comment</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions")
    public <T> T createIssueCommentReaction(String owner, String repo, long commentId, ReactionContent content,
                                            ReturnFormat format) throws IOException {
        return returnReaction(sendPostRequest(REPOS_PATH + owner + "/" + repo + ISSUES_COMMENTS_PATH + "/"
                + commentId + REACTIONS_PATH, createReactionPayload(content)), format);
    }

    /**
     * Method to delete an issue comment reaction
     *
     * @param repository: the repository where delete the reaction
     * @param comment:    the comment where delete the reaction
     * @param reaction:   the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-an-issue-comment-reaction">
     * Delete an issue comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions/{reaction_id}")
    public boolean deleteIssueCommentReaction(Repository repository, IssueComment comment, Reaction reaction) {
        return deleteIssueCommentReaction(repository.getOwner().getLogin(), repository.getName(), comment.getId(),
                reaction.getId());
    }

    /**
     * Method to delete an issue comment reaction
     *
     * @param repository: the repository where delete the reaction
     * @param commentId:  the unique identifier of the comment
     * @param reaction:   the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-an-issue-comment-reaction">
     * Delete an issue comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions/{reaction_id}")
    public boolean deleteIssueCommentReaction(Repository repository, long commentId, Reaction reaction) {
        return deleteIssueCommentReaction(repository.getOwner().getLogin(), repository.getName(), commentId,
                reaction.getId());
    }

    /**
     * Method to delete an issue comment reaction
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commentId: the unique identifier of the comment
     * @param reaction:  the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-an-issue-comment-reaction">
     * Delete an issue comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions/{reaction_id}")
    public boolean deleteIssueCommentReaction(String owner, String repo, long commentId, Reaction reaction) {
        return deleteIssueCommentReaction(owner, repo, commentId, reaction.getId());
    }

    /**
     * Method to delete an issue comment reaction
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param comment:  the comment where delete the reaction
     * @param reaction: the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-an-issue-comment-reaction">
     * Delete an issue comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions/{reaction_id}")
    public boolean deleteIssueCommentReaction(String owner, String repo, IssueComment comment, Reaction reaction) {
        return deleteIssueCommentReaction(owner, repo, comment.getId(), reaction.getId());
    }

    /**
     * Method to delete an issue comment reaction
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param comment:    the comment where delete the reaction
     * @param reactionId: the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-an-issue-comment-reaction">
     * Delete an issue comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions/{reaction_id}")
    public boolean deleteIssueCommentReaction(String owner, String repo, IssueComment comment, long reactionId) {
        return deleteIssueCommentReaction(owner, repo, comment.getId(), reactionId);
    }

    /**
     * Method to delete an issue comment reaction
     *
     * @param repository: the repository where delete the reaction
     * @param comment:    the comment where delete the reaction
     * @param reactionId: the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-an-issue-comment-reaction">
     * Delete an issue comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions/{reaction_id}")
    public boolean deleteIssueCommentReaction(Repository repository, IssueComment comment, long reactionId) {
        return deleteIssueCommentReaction(repository.getOwner().getLogin(), repository.getName(), comment.getId(),
                reactionId);
    }

    /**
     * Method to delete an issue comment reaction
     *
     * @param repository: the repository where delete the reaction
     * @param commentId:  the unique identifier of the comment
     * @param reactionId: the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-an-issue-comment-reaction">
     * Delete an issue comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions/{reaction_id}")
    public boolean deleteIssueCommentReaction(Repository repository, long commentId, long reactionId) {
        return deleteIssueCommentReaction(repository.getOwner().getLogin(), repository.getName(), commentId, reactionId);
    }

    /**
     * Method to delete an issue comment reaction
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param commentId:  the unique identifier of the comment
     * @param reactionId: the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-an-issue-comment-reaction">
     * Delete an issue comment reaction</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions/{reaction_id}")
    public boolean deleteIssueCommentReaction(String owner, String repo, long commentId, long reactionId) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + ISSUES_COMMENTS_PATH + "/" + commentId
                    + REACTIONS_PATH + "/" + reactionId);
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
     * Method to get the list reactions for an issue
     *
     * @param repository: the repository from fetch the list
     * @param issue:      the issue from fetch the list
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue">
     * List reactions for an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions")
    public ArrayList<Reaction> getIssueReactions(Repository repository, Issue issue) throws IOException {
        return getIssueReactions(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list reactions for an issue
     *
     * @param repository: the repository from fetch the list
     * @param issue:      the issue from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue">
     * List reactions for an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions")
    public <T> T getIssueReactions(Repository repository, Issue issue, ReturnFormat format) throws IOException {
        return getIssueReactions(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(), format);
    }

    /**
     * Method to get the list reactions for an issue
     *
     * @param owner: the account owner of the repository. The name is not case-sensitive
     * @param repo:  the name of the repository. The name is not case-sensitive
     * @param issue: the issue from fetch the list
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue">
     * List reactions for an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions")
    public ArrayList<Reaction> getIssueReactions(String owner, String repo, Issue issue) throws IOException {
        return getIssueReactions(owner, repo, issue.getNumber(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list reactions for an issue
     *
     * @param owner:  the account owner of the repository. The name is not case-sensitive
     * @param repo:   the name of the repository. The name is not case-sensitive
     * @param issue:  the issue from fetch the list
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue">
     * List reactions for an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions")
    public <T> T getIssueReactions(String owner, String repo, Issue issue, ReturnFormat format) throws IOException {
        return getIssueReactions(owner, repo, issue.getNumber(), format);
    }

    /**
     * Method to get the list reactions for an issue
     *
     * @param repository:  the repository from fetch the list
     * @param issueNumber: the number that identifies the issue
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue">
     * List reactions for an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions")
    public ArrayList<Reaction> getIssueReactions(Repository repository, long issueNumber) throws IOException {
        return getIssueReactions(repository.getOwner().getLogin(), repository.getName(), issueNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list reactions for an issue
     *
     * @param repository:  the repository from fetch the list
     * @param issueNumber: the number that identifies the issue
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue">
     * List reactions for an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions")
    public <T> T getIssueReactions(Repository repository, long issueNumber, ReturnFormat format) throws IOException {
        return getIssueReactions(repository.getOwner().getLogin(), repository.getName(), issueNumber, format);
    }

    /**
     * Method to get the list reactions for an issue
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue">
     * List reactions for an issue</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions")
    public ArrayList<Reaction> getIssueReactions(String owner, String repo, long issueNumber) throws IOException {
        return getIssueReactions(owner, repo, issueNumber, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list reactions for an issue
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue">
     * List reactions for an issue</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions")
    public <T> T getIssueReactions(String owner, String repo, long issueNumber, ReturnFormat format) throws IOException {
        return returnReactions(sendGetRequest(REPOS_PATH + owner + "/" + repo + "/" + ISSUES_PATH + "/"
                + issueNumber + REACTIONS_PATH), format);
    }

    /**
     * Method to get the list reactions for an issue
     *
     * @param repository:  the repository from fetch the list
     * @param issue:       the issue from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue">
     * List reactions for an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions")
    public ArrayList<Reaction> getIssueReactions(Repository repository, Issue issue, Params queryParams) throws IOException {
        return getIssueReactions(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(),
                queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list reactions for an issue
     *
     * @param repository:  the repository from fetch the list
     * @param issue:       the issue from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue">
     * List reactions for an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions")
    public <T> T getIssueReactions(Repository repository, Issue issue, Params queryParams,
                                   ReturnFormat format) throws IOException {
        return getIssueReactions(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(),
                queryParams, format);
    }

    /**
     * Method to get the list reactions for an issue
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issue:       the issue from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue">
     * List reactions for an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions")
    public ArrayList<Reaction> getIssueReactions(String owner, String repo, Issue issue,
                                                 Params queryParams) throws IOException {
        return getIssueReactions(owner, repo, issue.getNumber(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list reactions for an issue
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issue:       the issue from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue">
     * List reactions for an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions")
    public <T> T getIssueReactions(String owner, String repo, Issue issue, Params queryParams,
                                   ReturnFormat format) throws IOException {
        return getIssueReactions(owner, repo, issue.getNumber(), queryParams, format);
    }

    /**
     * Method to get the list reactions for an issue
     *
     * @param repository:  the repository from fetch the list
     * @param issueNumber: the number that identifies the issue
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue">
     * List reactions for an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions")
    public ArrayList<Reaction> getIssueReactions(Repository repository, long issueNumber,
                                                 Params queryParams) throws IOException {
        return getIssueReactions(repository.getOwner().getLogin(), repository.getName(), issueNumber, queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list reactions for an issue
     *
     * @param repository:  the repository from fetch the list
     * @param issueNumber: the number that identifies the issue
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue">
     * List reactions for an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions")
    public <T> T getIssueReactions(Repository repository, long issueNumber, Params queryParams,
                                   ReturnFormat format) throws IOException {
        return getIssueReactions(repository.getOwner().getLogin(), repository.getName(), issueNumber, queryParams,
                format);
    }

    /**
     * Method to get the list reactions for an issue
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue">
     * List reactions for an issue</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions")
    public ArrayList<Reaction> getIssueReactions(String owner, String repo, long issueNumber,
                                                 Params queryParams) throws IOException {
        return getIssueReactions(owner, repo, issueNumber, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list reactions for an issue
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-an-issue">
     * List reactions for an issue</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions")
    public <T> T getIssueReactions(String owner, String repo, long issueNumber, Params queryParams,
                                   ReturnFormat format) throws IOException {
        return returnReactions(sendGetRequest(REPOS_PATH + owner + "/" + repo + "/" + ISSUES_PATH + "/"
                + issueNumber + REACTIONS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create reaction for an issue. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param repository: the repository where create the reaction
     * @param issue:      the issue where create the reaction
     * @param content:    the reaction type to add to the team discussion comment
     * @return reaction as of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-an-issue">
     * Create reaction for an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions")
    public Reaction createIssueReaction(Repository repository, Issue issue, ReactionContent content) throws IOException {
        return createIssueReaction(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(),
                content, LIBRARY_OBJECT);
    }

    /**
     * Method to create reaction for an issue. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param repository: the repository where create the reaction
     * @param issue:      the issue where create the reaction
     * @param content:    the reaction type to add to the team discussion comment
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-an-issue">
     * Create reaction for an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions")
    public <T> T createIssueReaction(Repository repository, Issue issue, ReactionContent content,
                                     ReturnFormat format) throws IOException {
        return createIssueReaction(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(), content,
                format);
    }

    /**
     * Method to create reaction for an issue. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param issue:   the issue where create the reaction
     * @param content: the reaction type to add to the team discussion comment
     * @return reaction as of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-an-issue">
     * Create reaction for an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions")
    public Reaction createIssueReaction(String owner, String repo, Issue issue, ReactionContent content) throws IOException {
        return createIssueReaction(owner, repo, issue.getNumber(), content, LIBRARY_OBJECT);
    }

    /**
     * Method to create reaction for an issue. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param issue:   the issue where create the reaction
     * @param content: the reaction type to add to the team discussion comment
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-an-issue">
     * Create reaction for an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions")
    public <T> T createIssueReaction(String owner, String repo, Issue issue, ReactionContent content,
                                     ReturnFormat format) throws IOException {
        return createIssueReaction(owner, repo, issue.getNumber(), content, format);
    }

    /**
     * Method to create reaction for an issue. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param repository:  the repository where create the reaction
     * @param issueNumber: the number that identifies the issue
     * @param content:     the reaction type to add to the team discussion comment
     * @return reaction as of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-an-issue">
     * Create reaction for an issue</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions")
    public Reaction createIssueReaction(Repository repository, long issueNumber, ReactionContent content) throws IOException {
        return createIssueReaction(repository.getOwner().getLogin(), repository.getName(), issueNumber, content,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create reaction for an issue. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param repository:  the repository where create the reaction
     * @param issueNumber: the number that identifies the issue
     * @param content:     the reaction type to add to the team discussion comment
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-an-issue">
     * Create reaction for an issue</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions")
    public <T> T createIssueReaction(Repository repository, long issueNumber, ReactionContent content,
                                     ReturnFormat format) throws IOException {
        return createIssueReaction(repository.getOwner().getLogin(), repository.getName(), issueNumber, content,
                format);
    }

    /**
     * Method to create reaction for an issue. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param content:     the reaction type to add to the team discussion comment
     * @return reaction as of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-an-issue">
     * Create reaction for an issue</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions")
    public Reaction createIssueReaction(String owner, String repo, long issueNumber,
                                        ReactionContent content) throws IOException {
        return createIssueReaction(owner, repo, issueNumber, content, LIBRARY_OBJECT);
    }

    /**
     * Method to create reaction for an issue. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param content:     the reaction type to add to the team discussion comment
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-an-issue">
     * Create reaction for an issue</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions")
    public <T> T createIssueReaction(String owner, String repo, long issueNumber, ReactionContent content,
                                     ReturnFormat format) throws IOException {
        return returnReaction(sendPostRequest(REPOS_PATH + owner + "/" + repo + "/" + ISSUES_PATH + "/"
                + issueNumber + REACTIONS_PATH, createReactionPayload(content)), format);
    }

    /**
     * Method to delete an issue reaction
     *
     * @param repository: the repository where delete the reaction
     * @param issue:      the issue where delete the reaction
     * @param reaction:   the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-an-issue-reaction">
     * Delete an issue reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions/{reaction_id}")
    public boolean deleteIssueReaction(Repository repository, Issue issue, Reaction reaction) {
        return deleteIssueReaction(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(),
                reaction.getId());
    }

    /**
     * Method to delete an issue reaction
     *
     * @param repository:  the repository where delete the reaction
     * @param issueNumber: the number that identifies the issue
     * @param reaction:    the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-an-issue-reaction">
     * Delete an issue reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions/{reaction_id}")
    public boolean deleteIssueReaction(Repository repository, long issueNumber, Reaction reaction) {
        return deleteIssueReaction(repository.getOwner().getLogin(), repository.getName(), issueNumber,
                reaction.getId());
    }

    /**
     * Method to delete an issue reaction
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param reaction:    the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-an-issue-reaction">
     * Delete an issue reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions/{reaction_id}")
    public boolean deleteIssueReaction(String owner, String repo, long issueNumber, Reaction reaction) {
        return deleteIssueReaction(owner, repo, issueNumber, reaction.getId());
    }

    /**
     * Method to delete an issue reaction
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param issue:    the issue where delete the reaction
     * @param reaction: the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-an-issue-reaction">
     * Delete an issue reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions/{reaction_id}")
    public boolean deleteIssueReaction(String owner, String repo, Issue issue, Reaction reaction) {
        return deleteIssueReaction(owner, repo, issue.getNumber(), reaction.getId());
    }

    /**
     * Method to delete an issue reaction
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param issue:      the issue where delete the reaction
     * @param reactionId: the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-an-issue-reaction">
     * Delete an issue reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions/{reaction_id}")
    public boolean deleteIssueReaction(String owner, String repo, Issue issue, long reactionId) {
        return deleteIssueReaction(owner, repo, issue.getNumber(), reactionId);
    }

    /**
     * Method to delete an issue reaction
     *
     * @param repository: the repository where delete the reaction
     * @param issue:      the issue where delete the reaction
     * @param reactionId: the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-an-issue-reaction">
     * Delete an issue reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions/{reaction_id}")
    public boolean deleteIssueReaction(Repository repository, Issue issue, long reactionId) {
        return deleteIssueReaction(repository.getOwner().getLogin(), repository.getName(), issue.getNumber(),
                reactionId);
    }

    /**
     * Method to delete an issue reaction
     *
     * @param repository:  the repository where delete the reaction
     * @param issueNumber: the number that identifies the issue
     * @param reactionId:  the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-an-issue-reaction">
     * Delete an issue reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions/{reaction_id}")
    public boolean deleteIssueReaction(Repository repository, long issueNumber, long reactionId) {
        return deleteIssueReaction(repository.getOwner().getLogin(), repository.getName(), issueNumber, reactionId);
    }

    /**
     * Method to delete an issue reaction
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param issueNumber: the number that identifies the issue
     * @param reactionId:  the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-an-issue-reaction">
     * Delete an issue reaction</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/issues/{issue_number}/reactions/{reaction_id}")
    public boolean deleteIssueReaction(String owner, String repo, long issueNumber, long reactionId) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + "/" + ISSUES_PATH + "/" + issueNumber
                    + REACTIONS_PATH + "/" + reactionId);
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
     * Method to get the list the reactions to a pull request review comment
     *
     * @param repository: the repository from fetch the list
     * @param comment:    the comment from fetch the list
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-pull-request-review-comment">
     * List reactions for a pull request review comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions")
    public ArrayList<Reaction> getPullRequestReviewCommentReactions(Repository repository,
                                                                    ReviewComment comment) throws IOException {
        return getPullRequestReviewCommentReactions(repository.getOwner().getLogin(), repository.getName(),
                comment.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list the reactions to a pull request review comment
     *
     * @param repository: the repository from fetch the list
     * @param comment:    the comment from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-pull-request-review-comment">
     * List reactions for a pull request review comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions")
    public <T> T getPullRequestReviewCommentReactions(Repository repository, ReviewComment comment,
                                                      ReturnFormat format) throws IOException {
        return getPullRequestReviewCommentReactions(repository.getOwner().getLogin(), repository.getName(),
                comment.getId(), format);
    }

    /**
     * Method to get the list the reactions to a pull request review comment
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param comment: the comment from fetch the list
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-pull-request-review-comment">
     * List reactions for a pull request review comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions")
    public ArrayList<Reaction> getPullRequestReviewCommentReactions(String owner, String repo,
                                                                    ReviewComment comment) throws IOException {
        return getPullRequestReviewCommentReactions(owner, repo, comment.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the list the reactions to a pull request review comment
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param comment: the comment from fetch the list
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-pull-request-review-comment">
     * List reactions for a pull request review comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions")
    public <T> T getPullRequestReviewCommentReactions(String owner, String repo, ReviewComment comment,
                                                      ReturnFormat format) throws IOException {
        return getPullRequestReviewCommentReactions(owner, repo, comment.getId(), format);
    }

    /**
     * Method to get the list the reactions to a pull request review comment
     *
     * @param repository: the repository from fetch the list
     * @param commentId:  the unique identifier of the comment
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-pull-request-review-comment">
     * List reactions for a pull request review comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions")
    public ArrayList<Reaction> getPullRequestReviewCommentReactions(Repository repository, long commentId) throws IOException {
        return getPullRequestReviewCommentReactions(repository.getOwner().getLogin(), repository.getName(), commentId,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the list the reactions to a pull request review comment
     *
     * @param repository: the repository from fetch the list
     * @param commentId:  the unique identifier of the comment
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-pull-request-review-comment">
     * List reactions for a pull request review comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions")
    public <T> T getPullRequestReviewCommentReactions(Repository repository, long commentId,
                                                      ReturnFormat format) throws IOException {
        return getPullRequestReviewCommentReactions(repository.getOwner().getLogin(), repository.getName(), commentId,
                format);
    }

    /**
     * Method to get the list the reactions to a pull request review comment
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commentId: the unique identifier of the comment
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-pull-request-review-comment">
     * List reactions for a pull request review comment</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions")
    public ArrayList<Reaction> getPullRequestReviewCommentReactions(String owner, String repo,
                                                                    long commentId) throws IOException {
        return getPullRequestReviewCommentReactions(owner, repo, commentId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list the reactions to a pull request review comment
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commentId: the unique identifier of the comment
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-pull-request-review-comment">
     * List reactions for a pull request review comment</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions")
    public <T> T getPullRequestReviewCommentReactions(String owner, String repo, long commentId,
                                                      ReturnFormat format) throws IOException {
        return returnReactions(sendGetRequest(REPOS_PATH + owner + "/" + repo + PULLS_COMMENTS_PATH + "/"
                + commentId + REACTIONS_PATH), format);
    }

    /**
     * Method to get the list the reactions to a pull request review comment
     *
     * @param repository:  the repository from fetch the list
     * @param comment:     the comment from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-pull-request-review-comment">
     * List reactions for a pull request review comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions")
    public ArrayList<Reaction> getPullRequestReviewCommentReactions(Repository repository, ReviewComment comment,
                                                                    Params queryParams) throws IOException {
        return getPullRequestReviewCommentReactions(repository.getOwner().getLogin(), repository.getName(),
                comment.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list the reactions to a pull request review comment
     *
     * @param repository:  the repository from fetch the list
     * @param comment:     the comment from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-pull-request-review-comment">
     * List reactions for a pull request review comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions")
    public <T> T getPullRequestReviewCommentReactions(Repository repository, ReviewComment comment, Params queryParams,
                                                      ReturnFormat format) throws IOException {
        return getPullRequestReviewCommentReactions(repository.getOwner().getLogin(), repository.getName(),
                comment.getId(), queryParams, format);
    }

    /**
     * Method to get the list the reactions to a pull request review comment
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param comment:     the comment from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-pull-request-review-comment">
     * List reactions for a pull request review comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions")
    public ArrayList<Reaction> getPullRequestReviewCommentReactions(String owner, String repo, ReviewComment comment,
                                                                    Params queryParams) throws IOException {
        return getPullRequestReviewCommentReactions(owner, repo, comment.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list the reactions to a pull request review comment
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param comment:     the comment from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-pull-request-review-comment">
     * List reactions for a pull request review comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions")
    public <T> T getPullRequestReviewCommentReactions(String owner, String repo, ReviewComment comment, Params queryParams,
                                                      ReturnFormat format) throws IOException {
        return getPullRequestReviewCommentReactions(owner, repo, comment.getId(), queryParams, format);
    }

    /**
     * Method to get the list the reactions to a pull request review comment
     *
     * @param repository:  the repository from fetch the list
     * @param commentId:   the unique identifier of the comment
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-pull-request-review-comment">
     * List reactions for a pull request review comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions")
    public ArrayList<Reaction> getPullRequestReviewCommentReactions(Repository repository, long commentId,
                                                                    Params queryParams) throws IOException {
        return getPullRequestReviewCommentReactions(repository.getOwner().getLogin(), repository.getName(), commentId,
                queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list the reactions to a pull request review comment
     *
     * @param repository:  the repository from fetch the list
     * @param commentId:   the unique identifier of the comment
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-pull-request-review-comment">
     * List reactions for a pull request review comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions")
    public <T> T getPullRequestReviewCommentReactions(Repository repository, long commentId, Params queryParams,
                                                      ReturnFormat format) throws IOException {
        return getPullRequestReviewCommentReactions(repository.getOwner().getLogin(), repository.getName(), commentId,
                queryParams, format);
    }

    /**
     * Method to get the list the reactions to a pull request review comment
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param commentId:   the unique identifier of the comment
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-pull-request-review-comment">
     * List reactions for a pull request review comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions")
    public ArrayList<Reaction> getPullRequestReviewCommentReactions(String owner, String repo, long commentId,
                                                                    Params queryParams) throws IOException {
        return getPullRequestReviewCommentReactions(owner, repo, commentId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list the reactions to a pull request review comment
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param commentId:   the unique identifier of the comment
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-pull-request-review-comment">
     * List reactions for a pull request review comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions")
    public <T> T getPullRequestReviewCommentReactions(String owner, String repo, long commentId, Params queryParams,
                                                      ReturnFormat format) throws IOException {
        return returnReactions(sendGetRequest(REPOS_PATH + owner + "/" + repo + PULLS_COMMENTS_PATH + "/"
                + commentId + REACTIONS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a reaction to a pull request review comment. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param repository: the repository where create the reaction
     * @param comment:    the comment where create the reaction
     * @param content:    the reaction type to add to the team discussion comment
     * @return reaction as of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-pull-request-review-comment">
     * Create reaction for a pull request review comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions")
    public Reaction createPullRequestReviewCommentReaction(Repository repository, ReviewComment comment,
                                                           ReactionContent content) throws IOException {
        return createPullRequestReviewCommentReaction(repository.getOwner().getLogin(), repository.getName(),
                comment.getId(), content, LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to a pull request review comment. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param repository: the repository where create the reaction
     * @param comment:    the comment where create the reaction
     * @param content:    the reaction type to add to the team discussion comment
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-pull-request-review-comment">
     * Create reaction for a pull request review comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions")
    public <T> T createPullRequestReviewCommentReaction(Repository repository, ReviewComment comment,
                                                        ReactionContent content, ReturnFormat format) throws IOException {
        return createPullRequestReviewCommentReaction(repository.getOwner().getLogin(), repository.getName(),
                comment.getId(), content, format);
    }

    /**
     * Method to create a reaction to a pull request review comment. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param comment: the comment where create the reaction
     * @param content: the reaction type to add to the team discussion comment
     * @return reaction as of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-pull-request-review-comment">
     * Create reaction for a pull request review comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions")
    public Reaction createPullRequestReviewCommentReaction(String owner, String repo, ReviewComment comment,
                                                           ReactionContent content) throws IOException {
        return createPullRequestReviewCommentReaction(owner, repo, comment.getId(), content, LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to a pull request review comment. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param comment: the comment where create the reaction
     * @param content: the reaction type to add to the team discussion comment
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-pull-request-review-comment">
     * Create reaction for a pull request review comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions")
    public <T> T createPullRequestReviewCommentReaction(String owner, String repo, ReviewComment comment,
                                                        ReactionContent content, ReturnFormat format) throws IOException {
        return createPullRequestReviewCommentReaction(owner, repo, comment.getId(), content, format);
    }

    /**
     * Method to create a reaction to a pull request review comment. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param repository: the repository where create the reaction
     * @param commentId:  the unique identifier of the comment
     * @param content:    the reaction type to add to the team discussion comment
     * @return reaction as of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-pull-request-review-comment">
     * Create reaction for a pull request review comment</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions")
    public Reaction createPullRequestReviewCommentReaction(Repository repository, long commentId,
                                                           ReactionContent content) throws IOException {
        return createPullRequestReviewCommentReaction(repository.getOwner().getLogin(), repository.getName(), commentId,
                content, LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to a pull request review comment. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param repository: the repository where create the reaction
     * @param commentId:  the unique identifier of the comment
     * @param content:    the reaction type to add to the team discussion comment
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-pull-request-review-comment">
     * Create reaction for a pull request review comment</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions")
    public <T> T createPullRequestReviewCommentReaction(Repository repository, long commentId, ReactionContent content,
                                                        ReturnFormat format) throws IOException {
        return createPullRequestReviewCommentReaction(repository.getOwner().getLogin(), repository.getName(), commentId,
                content, format);
    }

    /**
     * Method to create a reaction to a pull request review comment. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commentId: the unique identifier of the comment
     * @param content:   the reaction type to add to the team discussion comment
     * @return reaction as of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-pull-request-review-comment">
     * Create reaction for a pull request review comment</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions")
    public Reaction createPullRequestReviewCommentReaction(String owner, String repo, long commentId,
                                                           ReactionContent content) throws IOException {
        return createPullRequestReviewCommentReaction(owner, repo, commentId, content, LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to a pull request review comment. A response with an HTTP 200 status means that you already
     * added the reaction type to this commit comment
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commentId: the unique identifier of the comment
     * @param content:   the reaction type to add to the team discussion comment
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-pull-request-review-comment">
     * Create reaction for a pull request review comment</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions")
    public <T> T createPullRequestReviewCommentReaction(String owner, String repo, long commentId, ReactionContent content,
                                                        ReturnFormat format) throws IOException {
        return returnReaction(sendPostRequest(REPOS_PATH + owner + "/" + repo + PULLS_COMMENTS_PATH + "/"
                + commentId + REACTIONS_PATH, createReactionPayload(content)), format);
    }

    /**
     * Method to delete a reaction to a pull request review comment
     *
     * @param repository: the repository where delete the reaction
     * @param comment:    the comment where delete the reaction
     * @param reaction:   the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-a-pull-request-comment-reaction">
     * Delete a pull request comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions/{reaction_id}")
    public boolean deletePullRequestReviewCommentReaction(Repository repository, ReviewComment comment,
                                                          Reaction reaction) {
        return deletePullRequestReviewCommentReaction(repository.getOwner().getLogin(), repository.getName(),
                comment.getId(), reaction.getId());
    }

    /**
     * Method to delete a reaction to a pull request review comment
     *
     * @param repository: the repository where delete the reaction
     * @param commentId:  the unique identifier of the comment
     * @param reaction:   the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-a-pull-request-comment-reaction">
     * Delete a pull request comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions/{reaction_id}")
    public boolean deletePullRequestReviewCommentReaction(Repository repository, long commentId, Reaction reaction) {
        return deletePullRequestReviewCommentReaction(repository.getOwner().getLogin(), repository.getName(), commentId,
                reaction.getId());
    }

    /**
     * Method to delete a reaction to a pull request review comment
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param commentId: the unique identifier of the comment
     * @param reaction:  the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-a-pull-request-comment-reaction">
     * Delete a pull request comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions/{reaction_id}")
    public boolean deletePullRequestReviewCommentReaction(String owner, String repo, long commentId, Reaction reaction) {
        return deletePullRequestReviewCommentReaction(owner, repo, commentId, reaction.getId());
    }

    /**
     * Method to delete a reaction to a pull request review comment
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param comment:  the comment where delete the reaction
     * @param reaction: the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-a-pull-request-comment-reaction">
     * Delete a pull request comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions/{reaction_id}")
    public boolean deletePullRequestReviewCommentReaction(String owner, String repo, ReviewComment comment,
                                                          Reaction reaction) {
        return deletePullRequestReviewCommentReaction(owner, repo, comment.getId(), reaction.getId());
    }

    /**
     * Method to delete a reaction to a pull request review comment
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param comment:    the comment where delete the reaction
     * @param reactionId: the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-a-pull-request-comment-reaction">
     * Delete a pull request comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions/{reaction_id}")
    public boolean deletePullRequestReviewCommentReaction(String owner, String repo, ReviewComment comment,
                                                          long reactionId) {
        return deletePullRequestReviewCommentReaction(owner, repo, comment.getId(), reactionId);
    }

    /**
     * Method to delete a reaction to a pull request review comment
     *
     * @param repository: the repository where delete the reaction
     * @param comment:    the comment where delete the reaction
     * @param reactionId: the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-a-pull-request-comment-reaction">
     * Delete a pull request comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions/{reaction_id}")
    public boolean deletePullRequestReviewCommentReaction(Repository repository, ReviewComment comment, long reactionId) {
        return deletePullRequestReviewCommentReaction(repository.getOwner().getLogin(), repository.getName(),
                comment.getId(), reactionId);
    }

    /**
     * Method to delete a reaction to a pull request review comment
     *
     * @param repository: the repository where delete the reaction
     * @param commentId:  the unique identifier of the comment
     * @param reactionId: the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-a-pull-request-comment-reaction">
     * Delete a pull request comment reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions/{reaction_id}")
    public boolean deletePullRequestReviewCommentReaction(Repository repository, long commentId, long reactionId) {
        return deletePullRequestReviewCommentReaction(repository.getOwner().getLogin(), repository.getName(), commentId,
                reactionId);
    }

    /**
     * Method to delete a reaction to a pull request review comment
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param commentId:  the unique identifier of the comment
     * @param reactionId: the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-a-pull-request-comment-reaction">
     * Delete a pull request comment reaction</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/pulls/comments/{comment_id}/reactions/{reaction_id}")
    public boolean deletePullRequestReviewCommentReaction(String owner, String repo, long commentId, long reactionId) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + PULLS_COMMENTS_PATH + "/" + commentId
                    + REACTIONS_PATH + "/" + reactionId);
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
     * Method to get the reactions to a release
     *
     * @param repository: the repository from fetch the list
     * @param release:    the release from fetch the list
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-release">
     * List reactions for a release</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions")
    public ArrayList<Reaction> getReleaseReactions(Repository repository, Release release) throws IOException {
        return getReleaseReactions(repository.getOwner().getLogin(), repository.getName(), release.getId(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the reactions to a release
     *
     * @param repository: the repository from fetch the list
     * @param release:    the release from fetch the list
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-release">
     * List reactions for a release</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions")
    public <T> T getReleaseReactions(Repository repository, Release release, ReturnFormat format) throws IOException {
        return getReleaseReactions(repository.getOwner().getLogin(), repository.getName(), release.getId(), format);
    }

    /**
     * Method to get the reactions to a release
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param release: the release from fetch the list
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-release">
     * List reactions for a release</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions")
    public ArrayList<Reaction> getReleaseReactions(String owner, String repo, Release release) throws IOException {
        return getReleaseReactions(owner, repo, release.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get the reactions to a release
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param release: the release from fetch the list
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-release">
     * List reactions for a release</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions")
    public <T> T getReleaseReactions(String owner, String repo, Release release, ReturnFormat format) throws IOException {
        return getReleaseReactions(owner, repo, release.getId(), format);
    }

    /**
     * Method to get the reactions to a release
     *
     * @param repository: the repository from fetch the list
     * @param releaseId:  the unique identifier of the release
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-release">
     * List reactions for a release</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions")
    public ArrayList<Reaction> getReleaseReactions(Repository repository, long releaseId) throws IOException {
        return getReleaseReactions(repository.getOwner().getLogin(), repository.getName(), releaseId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the reactions to a release
     *
     * @param repository: the repository from fetch the list
     * @param releaseId:  the unique identifier of the release
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-release">
     * List reactions for a release</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions")
    public <T> T getReleaseReactions(Repository repository, long releaseId, ReturnFormat format) throws IOException {
        return getReleaseReactions(repository.getOwner().getLogin(), repository.getName(), releaseId, format);
    }

    /**
     * Method to get the reactions to a release
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param releaseId: the unique identifier of the release
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-release">
     * List reactions for a release</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions")
    public ArrayList<Reaction> getReleaseReactions(String owner, String repo, long releaseId) throws IOException {
        return getReleaseReactions(owner, repo, releaseId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the reactions to a release
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param releaseId: the unique identifier of the release
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-release">
     * List reactions for a release</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions")
    public <T> T getReleaseReactions(String owner, String repo, long releaseId, ReturnFormat format) throws IOException {
        return returnReactions(sendGetRequest(REPOS_PATH + owner + "/" + repo + RELEASES_QUERY_PATH + releaseId
                + REACTIONS_PATH), format);
    }

    /**
     * Method to get the reactions to a release
     *
     * @param repository:  the repository from fetch the list
     * @param release:     the release from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-release">
     * List reactions for a release</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions")
    public ArrayList<Reaction> getReleaseReactions(Repository repository, Release release,
                                                   Params queryParams) throws IOException {
        return getReleaseReactions(repository.getOwner().getLogin(), repository.getName(), release.getId(), queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the reactions to a release
     *
     * @param repository:  the repository from fetch the list
     * @param release:     the release from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-release">
     * List reactions for a release</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions")
    public <T> T getReleaseReactions(Repository repository, Release release, Params queryParams,
                                     ReturnFormat format) throws IOException {
        return getReleaseReactions(repository.getOwner().getLogin(), repository.getName(), release.getId(),
                queryParams, format);
    }

    /**
     * Method to get the reactions to a release
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param release:     the release from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-release">
     * List reactions for a release</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions")
    public ArrayList<Reaction> getReleaseReactions(String owner, String repo, Release release,
                                                   Params queryParams) throws IOException {
        return getReleaseReactions(owner, repo, release.getId(), queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the reactions to a release
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param release:     the release from fetch the list
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-release">
     * List reactions for a release</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions")
    public <T> T getReleaseReactions(String owner, String repo, Release release, Params queryParams,
                                     ReturnFormat format) throws IOException {
        return getReleaseReactions(owner, repo, release.getId(), queryParams, format);
    }

    /**
     * Method to get the reactions to a release
     *
     * @param repository:  the repository from fetch the list
     * @param releaseId:   the unique identifier of the release
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-release">
     * List reactions for a release</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions")
    public ArrayList<Reaction> getReleaseReactions(Repository repository, long releaseId,
                                                   Params queryParams) throws IOException {
        return getReleaseReactions(repository.getOwner().getLogin(), repository.getName(), releaseId, queryParams,
                LIBRARY_OBJECT);
    }

    /**
     * Method to get the reactions to a release
     *
     * @param repository:  the repository from fetch the list
     * @param releaseId:   the unique identifier of the release
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-release">
     * List reactions for a release</a>
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions")
    public <T> T getReleaseReactions(Repository repository, long releaseId, Params queryParams,
                                     ReturnFormat format) throws IOException {
        return getReleaseReactions(repository.getOwner().getLogin(), repository.getName(), releaseId, queryParams,
                format);
    }

    /**
     * Method to get the reactions to a release
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param releaseId:   the unique identifier of the release
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return reactions list as {@link ArrayList} of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-release">
     * List reactions for a release</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions")
    public ArrayList<Reaction> getReleaseReactions(String owner, String repo, long releaseId,
                                                   Params queryParams) throws IOException {
        return getReleaseReactions(owner, repo, releaseId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the reactions to a release
     *
     * @param owner:       the account owner of the repository. The name is not case-sensitive
     * @param repo:        the name of the repository. The name is not case-sensitive
     * @param releaseId:   the unique identifier of the release
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "content"} -> returns a single reaction type. Omit this parameter to list all
     *                            reactions, constants available {@link ReactionContent} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#list-reactions-for-a-release">
     * List reactions for a release</a>
     **/
    @RequestPath(method = GET, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions")
    public <T> T getReleaseReactions(String owner, String repo, long releaseId, Params queryParams,
                                     ReturnFormat format) throws IOException {
        return returnReactions(sendGetRequest(REPOS_PATH + owner + "/" + repo + RELEASES_QUERY_PATH + releaseId
                + REACTIONS_PATH + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a reactions list
     *
     * @param reactionsResponse: obtained from GitHub's response
     * @param format:            return type formatter -> {@link ReturnFormat}
     * @return reactions list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnReactions(String reactionsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(reactionsResponse);
            case LIBRARY_OBJECT:
                ArrayList<Reaction> reactions = new ArrayList<>();
                JSONArray jReactions = new JSONArray(reactionsResponse);
                for (int j = 0; j < jReactions.length(); j++)
                    reactions.add(new Reaction(jReactions.getJSONObject(j)));
                return (T) reactions;
            default:
                return (T) reactionsResponse;
        }
    }

    /**
     * Method to create a reaction to a release. A response with a Status: 200 OK means that you already added the reaction
     * type to this release
     *
     * @param repository: the repository where create the reaction
     * @param release:    the release where create the reaction
     * @param content:    the reaction type to add to the team discussion comment
     * @return reaction as of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-release">
     * Create reaction for a release</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions")
    public Reaction createReleaseReaction(Repository repository, Release release,
                                          ReactionContent content) throws IOException {
        return createReleaseReaction(repository.getOwner().getLogin(), repository.getName(), release.getId(), content,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to a release. A response with a Status: 200 OK means that you already added the reaction
     * type to this release
     *
     * @param repository: the repository where create the reaction
     * @param release:    the release where create the reaction
     * @param content:    the reaction type to add to the team discussion comment
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-release">
     * Create reaction for a release</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions")
    public <T> T createReleaseReaction(Repository repository, Release release, ReactionContent content,
                                       ReturnFormat format) throws IOException {
        return createReleaseReaction(repository.getOwner().getLogin(), repository.getName(), release.getId(), content,
                format);
    }

    /**
     * Method to create a reaction to a release. A response with a Status: 200 OK means that you already added the reaction
     * type to this release
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param release: the release where create the reaction
     * @param content: the reaction type to add to the team discussion comment
     * @return reaction as of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-release">
     * Create reaction for a release</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions")
    public Reaction createReleaseReaction(String owner, String repo, Release release,
                                          ReactionContent content) throws IOException {
        return createReleaseReaction(owner, repo, release.getId(), content, LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to a release. A response with a Status: 200 OK means that you already added the reaction
     * type to this release
     *
     * @param owner:   the account owner of the repository. The name is not case-sensitive
     * @param repo:    the name of the repository. The name is not case-sensitive
     * @param release: the release where create the reaction
     * @param content: the reaction type to add to the team discussion comment
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-release">
     * Create reaction for a release</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions")
    public <T> T createReleaseReaction(String owner, String repo, Release release, ReactionContent content,
                                       ReturnFormat format) throws IOException {
        return createReleaseReaction(owner, repo, release.getId(), content, format);
    }

    /**
     * Method to create a reaction to a release. A response with a Status: 200 OK means that you already added the reaction
     * type to this release
     *
     * @param repository: the repository where create the reaction
     * @param releaseId:  the unique identifier of the release
     * @param content:    the reaction type to add to the team discussion comment
     * @return reaction as of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-release">
     * Create reaction for a release</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions")
    public Reaction createReleaseReaction(Repository repository, long releaseId, ReactionContent content) throws IOException {
        return createReleaseReaction(repository.getOwner().getLogin(), repository.getName(), releaseId, content,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to a release. A response with a Status: 200 OK means that you already added the reaction
     * type to this release
     *
     * @param repository: the repository where create the reaction
     * @param releaseId:  the unique identifier of the release
     * @param content:    the reaction type to add to the team discussion comment
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-release">
     * Create reaction for a release</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions")
    public <T> T createReleaseReaction(Repository repository, long releaseId, ReactionContent content,
                                       ReturnFormat format) throws IOException {
        return createReleaseReaction(repository.getOwner().getLogin(), repository.getName(), releaseId, content,
                format);
    }

    /**
     * Method to create a reaction to a release. A response with a Status: 200 OK means that you already added the reaction
     * type to this release
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param releaseId: the unique identifier of the release
     * @param content:   the reaction type to add to the team discussion comment
     * @return reaction as of {@link Reaction} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-release">
     * Create reaction for a release</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions")
    public Reaction createReleaseReaction(String owner, String repo, long releaseId,
                                          ReactionContent content) throws IOException {
        return createReleaseReaction(owner, repo, releaseId, content, LIBRARY_OBJECT);
    }

    /**
     * Method to create a reaction to a release. A response with a Status: 200 OK means that you already added the reaction
     * type to this release
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param releaseId: the unique identifier of the release
     * @param content:   the reaction type to add to the team discussion comment
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#create-reaction-for-a-release">
     * Create reaction for a release</a>
     **/
    @RequestPath(method = POST, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions")
    public <T> T createReleaseReaction(String owner, String repo, long releaseId, ReactionContent content,
                                       ReturnFormat format) throws IOException {
        return returnReaction(sendPostRequest(REPOS_PATH + owner + "/" + repo + RELEASES_QUERY_PATH + releaseId
                + REACTIONS_PATH, createReactionPayload(content)), format);
    }

    /**
     * Method to create a reaction
     *
     * @param reactionResponse: obtained from GitHub's response
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return reaction as {@code "format"} defines
     **/
    @Returner
    private <T> T returnReaction(String reactionResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(reactionResponse);
            case LIBRARY_OBJECT:
                return (T) new Reaction(new JSONObject(reactionResponse));
            default:
                return (T) reactionResponse;
        }
    }

    /**
     * Method to create the payload for the reaction creation
     *
     * @param content: content to create the payload
     * @return payload for the reaction creation request as {@link Params}
     **/
    private Params createReactionPayload(ReactionContent content) {
        Params payload = new Params();
        payload.addParam("content", content);
        return payload;
    }

    /**
     * Method to delete a release reaction
     *
     * @param repository: the repository where delete the reaction
     * @param release:    the release where delete the reaction
     * @param reaction:   the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-a-release-reaction">
     * Delete a release reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions/{reaction_id}")
    public boolean deleteReleaseReaction(Repository repository, Release release, Reaction reaction) {
        return deleteReleaseReaction(repository.getOwner().getLogin(), repository.getName(), release.getId(),
                reaction.getId());
    }

    /**
     * Method to delete a release reaction
     *
     * @param repository: the repository where delete the reaction
     * @param releaseId:  the unique identifier of the release
     * @param reaction:   the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-a-release-reaction">
     * Delete a release reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions/{reaction_id}")
    public boolean deleteReleaseReaction(Repository repository, long releaseId, Reaction reaction) {
        return deleteReleaseReaction(repository.getOwner().getLogin(), repository.getName(), releaseId,
                reaction.getId());
    }

    /**
     * Method to delete a release reaction
     *
     * @param owner:     the account owner of the repository. The name is not case-sensitive
     * @param repo:      the name of the repository. The name is not case-sensitive
     * @param releaseId: the unique identifier of the release
     * @param reaction:  the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-a-release-reaction">
     * Delete a release reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions/{reaction_id}")
    public boolean deleteReleaseReaction(String owner, String repo, long releaseId, Reaction reaction) {
        return deleteReleaseReaction(owner, repo, releaseId, reaction.getId());
    }

    /**
     * Method to delete a release reaction
     *
     * @param owner:    the account owner of the repository. The name is not case-sensitive
     * @param repo:     the name of the repository. The name is not case-sensitive
     * @param release:  the release where delete the reaction
     * @param reaction: the reaction to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-a-release-reaction">
     * Delete a release reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions/{reaction_id}")
    public boolean deleteReleaseReaction(String owner, String repo, Release release, Reaction reaction) {
        return deleteReleaseReaction(owner, repo, release.getId(), reaction.getId());
    }

    /**
     * Method to delete a release reaction
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param release:    the release where delete the reaction
     * @param reactionId: the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-a-release-reaction">
     * Delete a release reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions/{reaction_id}")
    public boolean deleteReleaseReaction(String owner, String repo, Release release, long reactionId) {
        return deleteReleaseReaction(owner, repo, release.getId(), reactionId);
    }

    /**
     * Method to delete a release reaction
     *
     * @param repository: the repository where delete the reaction
     * @param release:    the release where delete the reaction
     * @param reactionId: the unique identifier of the reaction
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-a-release-reaction">
     * Delete a release reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions/{reaction_id}")
    public boolean deleteReleaseReaction(Repository repository, Release release, long reactionId) {
        return deleteReleaseReaction(repository.getOwner().getLogin(), repository.getName(), release.getId(), reactionId);
    }

    /**
     * Method to delete a release reaction
     *
     * @param repository: the repository where delete the reaction
     * @param releaseId:  the unique identifier of the release
     * @param reactionId: the unique identifier of the release
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-a-release-reaction">
     * Delete a release reaction</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions/{reaction_id}")
    public boolean deleteReleaseReaction(Repository repository, long releaseId, long reactionId) {
        return deleteReleaseReaction(repository.getOwner().getLogin(), repository.getName(), releaseId, reactionId);
    }

    /**
     * Method to delete a release reaction
     *
     * @param owner:      the account owner of the repository. The name is not case-sensitive
     * @param repo:       the name of the repository. The name is not case-sensitive
     * @param releaseId:  the unique identifier of the release
     * @param reactionId: the unique identifier of the release
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/reactions#delete-a-release-reaction">
     * Delete a release reaction</a>
     **/
    @RequestPath(method = DELETE, path = "/repos/{owner}/{repo}/releases/{release_id}/reactions/{reaction_id}")
    public boolean deleteReleaseReaction(String owner, String repo, long releaseId, long reactionId) {
        try {
            sendDeleteRequest(REPOS_PATH + owner + "/" + repo + RELEASES_QUERY_PATH + releaseId + REACTIONS_PATH
                    + "/" + reactionId);
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

}
