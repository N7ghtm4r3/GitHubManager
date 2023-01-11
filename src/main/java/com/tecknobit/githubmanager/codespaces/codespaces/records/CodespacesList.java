package com.tecknobit.githubmanager.codespaces.codespaces.records;

import com.tecknobit.githubmanager.records.parents.GitHubList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class CodespacesList extends GitHubList {

    private final ArrayList<Codespace> codespaces;

    /**
     * Constructor to init a {@link GitHubList}
     *
     * @param codespaces : response by {@code "GitHub"} as {@link JSONObject}
     **/
    public CodespacesList(ArrayList<Codespace> codespaces) {
        super(codespaces.size());
        this.codespaces = codespaces;
    }

    /**
     * Constructor to init an {@link GitHubList}
     *
     * @param totalCount : total number of the items in the list
     **/
    public CodespacesList(int totalCount, ArrayList<Codespace> codespaces) {
        super(totalCount);
        this.codespaces = codespaces;
    }

    /**
     * Constructor to init a {@link GitHubList}
     *
     * @param jCodespacesList : response by {@code "GitHub"} as {@link JSONObject}
     **/
    public CodespacesList(JSONObject jCodespacesList) {
        super(jCodespacesList);
        codespaces = new ArrayList<>();
        JSONArray jCodespaces = hResponse.getJSONArray("codespaces", new JSONArray());
        for (int j = 0; j < jCodespaces.length(); j++)
            codespaces.add(new Codespace(jCodespaces.getJSONObject(j)));
    }

    public Collection<Codespace> getCodespaces() {
        return codespaces;
    }

}
