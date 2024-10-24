package com.tecknobit.githubmanager.records.parents;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.apimanager.formatters.TimeFormatter;
import org.json.JSONObject;

/**
 * The {@code InnerClassItem} class is useful to manage the inner classes
 *
 * @author N7ghtm4r3 - Tecknobit
 **/
public abstract class InnerClassItem {

    /**
     * {@code timeFormatter} timeFormatter the formatter used to format the timestamp values
     */
    protected final TimeFormatter timeFormatter = TimeFormatter.getInstance("yyyy-MM-dd'T'HH:mm:ss'Z'");

    /**
     * {@code hItem} useful to manage the {@code "JSON"} data
     **/
    protected final JsonHelper hItem;

    /**
     * Constructor to init a {@link InnerClassItem}
     *
     * @param jItem: item details as {@link JSONObject}
     **/
    public InnerClassItem(JSONObject jItem) {
        if (jItem != null)
            hItem = new JsonHelper(jItem);
        else
            hItem = null;
    }

    /**
     * Returns a string representation of the object <br>
     * No-any params required
     *
     * @return a string representation of the object as {@link String}
     */
    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

}
