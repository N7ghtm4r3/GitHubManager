package com.tecknobit.githubmanager.checks.runs.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.Location;
import org.json.JSONObject;

/**
 * The {@code CheckRunAnnotation} class is useful to format a GitHub's check run annotation
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/checks/runs#list-check-run-annotations">
 * List check run annotations</a>
 * @see GitHubResponse
 * @see Location
 **/
public class CheckRunAnnotation extends Location {

    /**
     * {@code annotationLevel} annotation level of the annotation
     **/
    private final String annotationLevel;

    /**
     * {@code title} of the annotation
     **/
    private final String title;

    /**
     * {@code rawDetails} raw details of the annotation
     **/
    private final String rawDetails;

    /**
     * {@code blobHref} blob href of the annotation
     **/
    private final String blobHref;

    /**
     * Constructor to init a {@link CheckRunAnnotation}
     *
     * @param path : path of the annotation
     * @param startLine : start line of the annotation
     * @param endLine : end line of the annotation
     * @param startColumn : start column of the annotation
     * @param endColumn : end column of the annotation
     * @param annotationLevel : annotation level of the annotation
     * @param title : title of the annotation
     * @param rawDetails : raw details of the annotation
     * @param blobHref :  blob href of the annotation
     * **/
    public CheckRunAnnotation(String path, int startLine, int endLine, int startColumn, int endColumn,
                              String annotationLevel, String title, String rawDetails, String blobHref) {
        super(null);
        this.annotationLevel = annotationLevel;
        this.title = title;
        this.rawDetails = rawDetails;
        this.blobHref = blobHref;
    }

    /**
     * Constructor to init a {@link CheckRunAnnotation}
     *
     * @param jCheckRunAnnotation : check run annotation details as {@link JSONObject}
     **/
    public CheckRunAnnotation(JSONObject jCheckRunAnnotation) {
        super(jCheckRunAnnotation);
        annotationLevel = hResponse.getString("annotation_level");
        title = hResponse.getString("title");
        rawDetails = hResponse.getString("raw_details");
        blobHref = hResponse.getString("blob_href");
    }

    /**
     * Method to get {@link #annotationLevel} instance <br>
     * Any params required
     *
     * @return {@link #annotationLevel} instance as {@link String}
     **/
    public String getAnnotationLevel() {
        return annotationLevel;
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
     * Method to get {@link #rawDetails} instance <br>
     * Any params required
     *
     * @return {@link #rawDetails} instance as {@link String}
     **/
    public String getRawDetails() {
        return rawDetails;
    }

    /**
     * Method to get {@link #blobHref} instance <br>
     * Any params required
     *
     * @return {@link #blobHref} instance as {@link String}
     **/
    public String getBlobHref() {
        return blobHref;
    }

}
