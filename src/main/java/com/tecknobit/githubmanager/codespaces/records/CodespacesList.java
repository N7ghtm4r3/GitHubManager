package com.tecknobit.githubmanager.codespaces.records;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.GitHubManager;
import com.tecknobit.githubmanager.records.parents.GitHubList;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The {@code CodespacesList} class is useful to format a GitHub's codespaces list
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-codespaces-in-a-repository-for-the-authenticated-user">
 *             List codespaces in a repository for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-codespaces-for-the-authenticated-user">
 *             List codespaces for the authenticated user</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see GitHubList
 **/
public class CodespacesList extends GitHubList {

    /**
     * {@code codespaces} list of {@link Codespace}
     **/
    private final ArrayList<Codespace> codespaces;

    /**
     * Constructor to init a {@link CodespacesList}
     *
     * @param codespaces : list of {@link Codespace}
     **/
    public CodespacesList(ArrayList<Codespace> codespaces) {
        super(codespaces.size());
        this.codespaces = codespaces;
    }

    /**
     * Constructor to init an {@link CodespacesList}
     *
     * @param totalCount : total number of the items in the list
     * @param codespaces : list of {@link Codespace}
     **/
    public CodespacesList(int totalCount, ArrayList<Codespace> codespaces) {
        super(totalCount);
        this.codespaces = codespaces;
    }

    /**
     * Constructor to init a {@link CodespacesList}
     *
     * @param jCodespacesList : codespaces list details as {@link JSONObject}
     **/
    public CodespacesList(JSONObject jCodespacesList) {
        super(jCodespacesList);
        codespaces = new ArrayList<>();
        JSONArray jCodespaces = hResponse.getJSONArray("codespaces", new JSONArray());
        for (int j = 0; j < jCodespaces.length(); j++)
            codespaces.add(new Codespace(jCodespaces.getJSONObject(j)));
    }

    /**
     * Method to get {@link #codespaces} instance <br>
     * No-any params required
     *
     * @return {@link #codespaces} instance as {@link ArrayList} of {@link Codespace}
     **/
    public ArrayList<Codespace> getCodespaces() {
        return codespaces;
    }

    /**
     * Method to create a codespaces list
     *
     * @param codespacesListResponse: obtained from GitHub's response
     * @param format:                 return type formatter -> {@link GitHubManager.ReturnFormat}
     * @return codespaces list as {@code "format"} defines
     **/
    @Returner
    public static <T> T returnCodespacesList(String codespacesListResponse, GitHubManager.ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(codespacesListResponse);
            case LIBRARY_OBJECT:
                return (T) new CodespacesList(new JSONObject(codespacesListResponse));
            default:
                return (T) codespacesListResponse;
        }
    }

}
