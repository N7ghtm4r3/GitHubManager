package com.tecknobit.githubmanager.codescanning.records;

import com.tecknobit.githubmanager.records.parents.BaseResponseDetails;
import com.tecknobit.githubmanager.records.parents.User;
import org.json.JSONObject;

public class CodeQL extends BaseResponseDetails {

    private final String language;
    private final User uploader;
    private final String contentType;
    private final double size;
    private final String createdAt;
    private final String updatedAt;

    /**
     * Constructor to init a {@link BaseResponseDetails}
     *
     * @param id   : identifier value
     * @param name : the name of the item
     * @param url  : url value
     **/
    public CodeQL(long id, String name, String url, String language, User uploader, String contentType, double size,
                  String createdAt, String updatedAt) {
        super(id, name, url);
        this.language = language;
        this.uploader = uploader;
        this.contentType = contentType;
        this.size = size;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Constructor to init a {@link BaseResponseDetails}
     *
     * @param jCodeQL : basics response details as {@link JSONObject}
     **/
    public CodeQL(JSONObject jCodeQL) {
        super(jCodeQL);
        language = hResponse.getString("language");
        uploader = new User(hResponse.getJSONObject("uploader", new JSONObject()));
        contentType = hResponse.getString("content_type");
        size = hResponse.getDouble("size", 0);
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
    }

    public String getLanguage() {
        return language;
    }

    public User getUploader() {
        return uploader;
    }

    public String getContentType() {
        return contentType;
    }

    public double getSize() {
        return size;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

}
