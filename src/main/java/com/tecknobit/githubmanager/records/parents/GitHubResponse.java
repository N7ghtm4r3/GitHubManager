package com.tecknobit.githubmanager.records.parents;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.GitHubManager.ReturnFormat;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The {@code GitHubResponse} class is useful to format all GitHub's responses giving basics methods
 * for others {@link ReturnFormat#LIBRARY_OBJECT}
 *
 * @author N7ghtm4r3 - Tecknobit
 **/
public abstract class GitHubResponse {

    /**
     * {@code INSTANTIATED_WITH_ERROR_KEY} constant for {@link #instantiatedWithError} key
     **/
    public static final String INSTANTIATED_WITH_ERROR_KEY = "instantiatedWithError";

    /**
     * {@code hResponse} is instance to manage {@code "JSON"} data format
     **/
    protected final JsonHelper hResponse;

    /**
     * {@code message} the message of the error if exists
     **/
    protected String message;

    /**
     * {@code documentationUrl} the documentation url to see more details if the error exists
     **/
    protected final String documentationUrl;

    /**
     * {@code instantiatedWithError} this flag shows if the object has been instantiated with a normal workflow,
     * so with successful response by {@code "GitHub"}, or if this object has been instantiated with an unsuccessful
     * workflow if the response by {@code "GitHub"} has been unsuccessful
     **/
    protected final boolean instantiatedWithError;

    /**
     * Constructor to init a {@link GitHubResponse}
     *
     * @param jResponse: response by {@code "GitHub"} as {@link JSONObject}
     **/
    public GitHubResponse(JSONObject jResponse) {
        if (jResponse == null)
            jResponse = new JSONObject();
        hResponse = new JsonHelper(jResponse);
        message = hResponse.getString("message");
        documentationUrl = hResponse.getString("documentation_url");
        instantiatedWithError = documentationUrl != null;
    }

    /**
     * Method to get {@link #message} instance <br>
     * No-any params required
     *
     * @return {@link #message} instance as {@link String}
     **/
    public String getMessage() {
        return message;
    }

    /**
     * Method to get {@link #documentationUrl} instance <br>
     * No-any params required
     *
     * @return {@link #documentationUrl} instance as {@link String}
     **/
    public String getDocumentationUrl() {
        return documentationUrl;
    }

    /**
     * Method to get {@link #instantiatedWithError} instance <br>
     * No-any params required
     *
     * @return {@link #instantiatedWithError} instance as boolean
     **/
    public boolean isInstantiatedWithError() {
        return instantiatedWithError;
    }

    /**
     * Returns a string representation of the object <br>
     * No-any params required
     *
     * @return a string representation of the object as {@link String} in two different ways:
     * <ul>
     *     <li>
     *         If {@link #instantiatedWithError} is set on {@code "true"} the message will be something like:
     *         <pre>{@code {
     *  "instantiatedWithError": true,
     *  "message": #message,
     *  "documentation_url": #documentation_url
     * }
     *             }
     *         </pre>
     *     </li>
     *     <li>
     *         If {@link #instantiatedWithError} is set on {@code "false"} the message will be something like:
     *         <pre {@code {
     * "instantiatedWithError": false,
     * // rest of the LIBRARY_OBJECT details formatted as JSON
     *}
     *             }
     *         </pre>
     *     </li>
     * </ul>
     */
    @Override
    public String toString() {
        if (instantiatedWithError) {
            return new JSONObject("{" +
                    "\"message\":\"" + message + "\"," +
                    "\"documentation_url\":\"" + documentationUrl +
                    "\"}").put("instantiatedWithError", true).toString();
        }
        return new JSONObject(this).toString();
    }

    /**
     * Method to create a {@link String} list
     *
     * @param jList: {@link JSONArray} source from assemble the list
     * @return list of string as {@link ArrayList} of {@link String}
     **/
    @Returner
    public static ArrayList<String> returnStringsList(JSONArray jList) {
        ArrayList<String> list = new ArrayList<>();
        if (jList != null)
            for (int j = 0; j < jList.length(); j++)
                list.add(jList.getString(j));
        return list;
    }

    /**
     * Method to create an {@link Integer} list
     *
     * @param jList: {@link JSONArray} source from assemble the list
     * @return list of integer as {@link ArrayList} of {@link Integer}
     **/
    @Returner
    public static ArrayList<Integer> returnIntegersList(JSONArray jList) {
        ArrayList<Integer> list = new ArrayList<>();
        if (jList != null)
            for (int j = 0; j < jList.length(); j++)
                list.add(jList.getInt(j));
        return list;
    }

}