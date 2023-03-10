package com.tecknobit.githubmanager.projects.cards;

import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.projects.cards.records.ProjectCard;
import com.tecknobit.githubmanager.projects.cards.records.ProjectCard.ArchivedState;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.*;
import static com.tecknobit.githubmanager.GitHubManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.githubmanager.projects.boards.GitHubBoardsManager.ROOT_PROJECTS_PATH;

/**
 * The {@code GitHubCardsManager} class is useful to manage all GitHub's cards endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/cards">
 * Project (classic) cards</a>
 * @see GitHubManager
 **/
public class GitHubCardsManager extends GitHubManager {

    /**
     * {@code CARDS_PATH} constant for {@code "/cards"} path
     **/
    public static final String CARDS_PATH = "/cards";

    /**
     * {@code COLUMNS_PATH} constant for {@code "/columns"} path
     **/
    public static final String COLUMNS_PATH = "/columns";

    /**
     * {@code PROJECTS_COLUMNS_PATH} constant for {@code "projects/columns"} path
     **/
    public static final String PROJECTS_COLUMNS_PATH = ROOT_PROJECTS_PATH + COLUMNS_PATH;

    /**
     * {@code PROJECTS_COLUMNS_CARDS_PATH} constant for {@code "projects/columns/cards"} path
     **/
    public static final String PROJECTS_COLUMNS_CARDS_PATH = PROJECTS_COLUMNS_PATH + CARDS_PATH;

    /**
     * {@code MOVES_PATH} constant for {@code "/moves"} path
     **/
    public static final String MOVES_PATH = "/moves";

    /**
     * Constructor to init a {@link GitHubCardsManager}
     *
     * @param accessToken : personal access token for authentication to {@code "GitHub"}
     **/
    public GitHubCardsManager(String accessToken) {
        super(accessToken);
    }

    /**
     * Constructor to init a {@link GitHubCardsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     **/
    public GitHubCardsManager(String accessToken, String defaultErrorMessage) {
        super(accessToken, defaultErrorMessage);
    }

    /**
     * Constructor to init a {@link GitHubCardsManager}
     *
     * @param accessToken    :    personal access token for authentication to {@code "GitHub"}
     * @param requestTimeout : custom timeout for request
     **/
    public GitHubCardsManager(String accessToken, int requestTimeout) {
        super(accessToken, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubCardsManager}
     *
     * @param accessToken         :         personal access token for authentication to {@code "GitHub"}
     * @param defaultErrorMessage : custom error to show when is not a request error
     * @param requestTimeout      :      custom timeout for request
     **/
    public GitHubCardsManager(String accessToken, String defaultErrorMessage, int requestTimeout) {
        super(accessToken, defaultErrorMessage, requestTimeout);
    }

    /**
     * Constructor to init a {@link GitHubCardsManager} <br>
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
    public GitHubCardsManager() {
        super();
    }

    /**
     * Method to get a project card
     *
     * @param cardId: the unique identifier of the card
     * @return project card as {@link ProjectCard} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/cards#get-a-project-card">
     * Get a project card</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/projects/columns/cards/{card_id}")
    public ProjectCard getProjectCard(long cardId) throws IOException {
        return getProjectCard(cardId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a project card
     *
     * @param cardId: the unique identifier of the card
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return project card as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/cards#get-a-project-card">
     * Get a project card</a>
     **/
    @RequestPath(method = GET, path = "/projects/columns/cards/{card_id}")
    public <T> T getProjectCard(long cardId, ReturnFormat format) throws IOException {
        return returnProjectCard(sendGetRequest(PROJECTS_COLUMNS_CARDS_PATH + "/" + cardId), format);
    }

    /**
     * Method to update an existing project card
     *
     * @param card:       the card to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "note"} -> the project card's note - [string or null]
     *                       </li>
     *                       <li>
     *                           {@code "archived"} -> whether or not the card is archived - [boolean]
     *                       </li>
     *                    </ul>
     * @return project card as {@link ProjectCard} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/cards#update-an-existing-project-card">
     * Update an existing project card</a>
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/projects/columns/cards/{card_id}")
    public ProjectCard updateProjectCard(ProjectCard card, Params bodyParams) throws IOException {
        return updateProjectCard(card.getId(), bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update an existing project card
     *
     * @param card:       the card to update
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "note"} -> the project card's note - [string or null]
     *                       </li>
     *                       <li>
     *                           {@code "archived"} -> whether or not the card is archived - [boolean]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return project card as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/cards#update-an-existing-project-card">
     * Update an existing project card</a>
     **/
    @WrappedRequest
    @RequestPath(method = PATCH, path = "/projects/columns/cards/{card_id}")
    public <T> T updateProjectCard(ProjectCard card, Params bodyParams, ReturnFormat format) throws IOException {
        return updateProjectCard(card.getId(), bodyParams, format);
    }

    /**
     * Method to update an existing project card
     *
     * @param cardId:     the unique identifier of the card
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "note"} -> the project card's note - [string or null]
     *                       </li>
     *                       <li>
     *                           {@code "archived"} -> whether or not the card is archived - [boolean]
     *                       </li>
     *                    </ul>
     * @return project card as {@link ProjectCard} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/cards#update-an-existing-project-card">
     * Update an existing project card</a>
     **/
    @Wrapper
    @RequestPath(method = PATCH, path = "/projects/columns/cards/{card_id}")
    public ProjectCard updateProjectCard(long cardId, Params bodyParams) throws IOException {
        return updateProjectCard(cardId, bodyParams, LIBRARY_OBJECT);
    }

    /**
     * Method to update an existing project card
     *
     * @param cardId:     the unique identifier of the card
     * @param bodyParams: extra body params not mandatory, keys accepted are:
     *                    <ul>
     *                       <li>
     *                           {@code "note"} -> the project card's note - [string or null]
     *                       </li>
     *                       <li>
     *                           {@code "archived"} -> whether or not the card is archived - [boolean]
     *                       </li>
     *                    </ul>
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return project card as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/cards#update-an-existing-project-card">
     * Update an existing project card</a>
     **/
    @RequestPath(method = PATCH, path = "/projects/columns/cards/{card_id}")
    public <T> T updateProjectCard(long cardId, Params bodyParams, ReturnFormat format) throws IOException {
        return returnProjectCard(sendPatchRequest(PROJECTS_COLUMNS_CARDS_PATH + "/" + cardId, bodyParams), format);
    }

    /**
     * Method to delete a project card
     *
     * @param card: the card to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/cards#delete-a-project-card">
     * Delete a project card</a>
     **/
    @WrappedRequest
    @RequestPath(method = DELETE, path = "/projects/columns/cards/{card_id}")
    public boolean deleteProjectCard(ProjectCard card) {
        return deleteProjectCard(card.getId());
    }

    /**
     * Method to delete a project card
     *
     * @param cardId: the unique identifier of the card
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/cards#delete-a-project-card">
     * Delete a project card</a>
     **/
    @RequestPath(method = DELETE, path = "/projects/columns/cards/{card_id}")
    public boolean deleteProjectCard(long cardId) {
        try {
            sendDeleteRequest(PROJECTS_COLUMNS_CARDS_PATH + "/" + cardId);
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
     * Method to move a project card
     *
     * @param card:     the card to move
     * @param position: the position of the card in a column. Can be one of: top, bottom, or {@code "after:<card_id>"} to place
     *                  after the specified card
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/cards#move-a-project-card">
     * Move a project card</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/projects/columns/cards/{card_id}/moves")
    public boolean moveProjectCard(ProjectCard card, String position) {
        return moveProjectCard(card.getId(), position);
    }

    /**
     * Method to move a project card
     *
     * @param cardId:   the unique identifier of the card
     * @param position: the position of the card in a column. Can be one of: top, bottom, or {@code "after:<card_id>"} to place
     *                  after the specified card
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/cards#move-a-project-card">
     * Move a project card</a>
     **/
    @RequestPath(method = POST, path = "/projects/columns/cards/{card_id}/moves")
    public boolean moveProjectCard(long cardId, String position) {
        return moveProjectCard(cardId, position, -1);
    }

    /**
     * Method to move a project card
     *
     * @param card:     the card to move
     * @param position: the position of the card in a column. Can be one of: top, bottom, or {@code "after:<card_id>"} to place
     *                  after the specified card
     * @param columnId: the unique identifier of the column the card should be moved to
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/cards#move-a-project-card">
     * Move a project card</a>
     **/
    @WrappedRequest
    @RequestPath(method = POST, path = "/projects/columns/cards/{card_id}/moves")
    public boolean moveProjectCard(ProjectCard card, String position, long columnId) {
        return moveProjectCard(card.getId(), position, columnId);
    }

    /**
     * Method to move a project card
     *
     * @param cardId:   the unique identifier of the card
     * @param position: the position of the card in a column. Can be one of: top, bottom, or {@code "after:<card_id>"} to place
     *                  after the specified card
     * @param columnId: the unique identifier of the column the card should be moved to
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} and error printed with {@link #printErrorResponse()} method if not successful
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/cards#move-a-project-card">
     * Move a project card</a>
     **/
    @RequestPath(method = POST, path = "/projects/columns/cards/{card_id}/moves")
    public boolean moveProjectCard(long cardId, String position, long columnId) {
        try {
            Params payload = new Params();
            payload.addParam("position", position);
            if (columnId != -1)
                payload.addParam("column_id", columnId);
            sendPostRequest(PROJECTS_COLUMNS_CARDS_PATH + "/" + cardId + MOVES_PATH, payload);
            if (apiRequest.getResponseStatusCode() != 201) {
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
     * Method to get the list of the project cards
     *
     * @param columnId: the unique identifier of the column
     * @return project cards as {@link ArrayList} of {@link ProjectCard} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/cards#list-project-cards">
     * List project cards</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/projects/columns/{column_id}/cards")
    public ArrayList<ProjectCard> getProjectCards(long columnId) throws IOException {
        return getProjectCards(columnId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the project cards
     *
     * @param columnId: the unique identifier of the column
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return projects cards list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/cards#list-project-cards">
     * List project cards</a>
     **/
    @RequestPath(method = GET, path = "/projects/columns/{column_id}/cards")
    public <T> T getProjectCards(long columnId, ReturnFormat format) throws IOException {
        return returnProjectCards(sendGetRequest(PROJECTS_COLUMNS_PATH + "/" + columnId + CARDS_PATH), format);
    }

    /**
     * Method to get the list of the project cards
     *
     * @param columnId:    the unique identifier of the column
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "archived_state"} -> filters the project cards that are returned by the card's
     *                            state, constants available {@link  ArchivedState} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @return project cards as {@link ArrayList} of {@link ProjectCard} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/cards#list-project-cards">
     * List project cards</a>
     **/
    @Wrapper
    @RequestPath(method = GET, path = "/projects/columns/{column_id}/cards")
    public ArrayList<ProjectCard> getProjectCards(long columnId, Params queryParams) throws IOException {
        return getProjectCards(columnId, queryParams, LIBRARY_OBJECT);
    }

    /**
     * Method to get the list of the project cards
     *
     * @param columnId:    the unique identifier of the column
     * @param queryParams: extra query params not mandatory, keys accepted are:
     *                     <ul>
     *                        <li>
     *                            {@code "archived_state"} -> filters the project cards that are returned by the card's
     *                            state, constants available {@link  ArchivedState} - [string]
     *                        </li>
     *                        <li>
     *                            {@code "per_page"} -> the number of results per page (max 100) - [integer, default 30]
     *                        </li>
     *                        <li>
     *                            {@code "page"} -> page number of the results to fetch - [integer, default 1]
     *                        </li>
     *                     </ul>
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return projects cards list as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/cards#list-project-cards">
     * List project cards</a>
     **/
    @RequestPath(method = GET, path = "/projects/columns/{column_id}/cards")
    public <T> T getProjectCards(long columnId, Params queryParams, ReturnFormat format) throws IOException {
        return returnProjectCards(sendGetRequest(PROJECTS_COLUMNS_PATH + "/" + columnId + CARDS_PATH
                + queryParams.createQueryString()), format);
    }

    /**
     * Method to create a projects cards list
     *
     * @param projectCardsResponse: obtained from GitHub's response
     * @param format:               return type formatter -> {@link ReturnFormat}
     * @return projects cards list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnProjectCards(String projectCardsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(projectCardsResponse);
            case LIBRARY_OBJECT:
                ArrayList<ProjectCard> projectCards = new ArrayList<>();
                JSONArray jProjectCards = new JSONArray(projectCardsResponse);
                for (int j = 0; j < jProjectCards.length(); j++)
                    projectCards.add(new ProjectCard(jProjectCards.getJSONObject(j)));
                return (T) projectCards;
            default:
                return (T) projectCardsResponse;
        }
    }

    /**
     * Method to create a project card<
     *
     * @param columnId:    the unique identifier of the column
     * @param note:        the project card's note
     * @param contentId:   the unique identifier of the content associated with the card
     * @param contentType: the piece of content associated with the card
     * @return project card as {@link ProjectCard} custom object
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/cards#create-a-project-card">
     * Create a project card</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "/projects/columns/{column_id}/cards")
    public ProjectCard createProjectCard(long columnId, String note, long contentId, String contentType) throws IOException {
        return createProjectCard(columnId, note, contentId, contentType, LIBRARY_OBJECT);
    }

    /**
     * Method to create a project card<
     *
     * @param columnId:    the unique identifier of the column
     * @param note:        the project card's note
     * @param contentId:   the unique identifier of the content associated with the card
     * @param contentType: the piece of content associated with the card
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return project card as {@code "format"} defines
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
     * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/projects/cards#create-a-project-card">
     * Create a project card</a>
     **/
    @RequestPath(method = POST, path = "/projects/columns/{column_id}/cards")
    public <T> T createProjectCard(long columnId, String note, long contentId, String contentType,
                                   ReturnFormat format) throws IOException {
        Params payload = new Params();
        payload.addParam("note", note);
        payload.addParam("content_id", contentId);
        payload.addParam("content_type", contentType);
        return returnProjectCard(sendPostRequest(PROJECTS_COLUMNS_PATH + "/" + columnId + CARDS_PATH, payload),
                format);
    }

    /**
     * Method to create a project card
     *
     * @param projectCardResponse: obtained from GitHub's response
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return project card as {@code "format"} defines
     **/
    @Returner
    private <T> T returnProjectCard(String projectCardResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(projectCardResponse);
            case LIBRARY_OBJECT:
                return (T) new ProjectCard(new JSONObject(projectCardResponse));
            default:
                return (T) projectCardResponse;
        }
    }

}
