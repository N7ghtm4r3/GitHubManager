package com.tecknobit.githubmanager.checks.runs.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

public class CheckRunAnnotation extends GitHubResponse {

    private final String path;
    private final int startLine;
    private final int endLine;
    private final int startColumn;
    private final int endColumn;
    private final String annotationLevel;
    private final String title;
    private final String rawDetails;
    private final String blobHref;

    public CheckRunAnnotation(JSONObject jResponse, String path, int startLine, int endLine, int startColumn, int endColumn,
                              String annotationLevel, String title, String rawDetails, String blobHref) {
        super(jResponse);
        this.path = path;
        this.startLine = startLine;
        this.endLine = endLine;
        this.startColumn = startColumn;
        this.endColumn = endColumn;
        this.annotationLevel = annotationLevel;
        this.title = title;
        this.rawDetails = rawDetails;
        this.blobHref = blobHref;
    }

    /**
     * Constructor to init a {@link CheckRunAnnotation}
     *
     * @param jCheckRunAnnotation : response by {@code "GitHub"} as {@link JSONObject}
     **/
    public CheckRunAnnotation(JSONObject jCheckRunAnnotation) {
        super(jCheckRunAnnotation);
        path = hResponse.getString("path");
        startLine = hResponse.getInt("start_line", 0);
        endLine = hResponse.getInt("end_line", 0);
        startColumn = hResponse.getInt("start_column", 0);
        endColumn = hResponse.getInt("end_column", 0);
        annotationLevel = hResponse.getString("annotation_level");
        title = hResponse.getString("title");
        rawDetails = hResponse.getString("raw_details");
        blobHref = hResponse.getString("blob_href");
    }

    public String getPath() {
        return path;
    }

    public int getStartLine() {
        return startLine;
    }

    public int getEndLine() {
        return endLine;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public int getEndColumn() {
        return endColumn;
    }

    public String getAnnotationLevel() {
        return annotationLevel;
    }

    public String getTitle() {
        return title;
    }

    public String getRawDetails() {
        return rawDetails;
    }

    public String getBlobHref() {
        return blobHref;
    }

}
