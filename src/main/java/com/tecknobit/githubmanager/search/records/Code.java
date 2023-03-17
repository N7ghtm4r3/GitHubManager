package com.tecknobit.githubmanager.search.records;

import com.tecknobit.githubmanager.records.generic.ShaItem;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Code extends ShaItem {

    private final String name;
    private final String path;
    private final String gitUrl;
    private final String htmlUrl;
    private final Repository repository;
    private final double fileSize;
    private final String language;
    private final String lastModifiedAt;
    private final ArrayList<Integer> lineNumbers;

    /**
     * Constructor to init a {@link ShaItem}
     **/
    public Code(String name, String path, String gitUrl, String htmlUrl, Repository repository, double fileSize,
                String language, String lastModifiedAt, ArrayList<Integer> lineNumbers) {
        super(null);
        this.name = name;
        this.path = path;
        this.gitUrl = gitUrl;
        this.htmlUrl = htmlUrl;
        this.repository = repository;
        this.fileSize = fileSize;
        this.language = language;
        this.lastModifiedAt = lastModifiedAt;
        this.lineNumbers = lineNumbers;
    }

    /**
     * Constructor to init a {@link ShaItem}
     *
     * @param jCode : sha item details as {@link JSONObject}
     **/
    public Code(JSONObject jCode) {
        super(jCode);
        name = hResponse.getString("name");
        path = hResponse.getString("path");
        gitUrl = hResponse.getString("git_url");
        htmlUrl = hResponse.getString("html_url");
        repository = new Repository(hResponse.getJSONObject("repository"));
        fileSize = hResponse.getDouble("file_size", 0);
        language = hResponse.getString("language");
        lastModifiedAt = hResponse.getString("last_modified_at");
        lineNumbers = new ArrayList<>();
        JSONArray jLineNumbers = hResponse.getJSONArray("line_numbers", new JSONArray());
        for (int j = 0; j < jLineNumbers.length(); j++)
            lineNumbers.add(jLineNumbers.getInt(j));
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public Repository getRepository() {
        return repository;
    }

    public double getFileSize() {
        return fileSize;
    }

    public String getLanguage() {
        return language;
    }

    public String getLastModifiedAt() {
        return lastModifiedAt;
    }

    public ArrayList<Integer> getLineNumbers() {
        return lineNumbers;
    }

}
