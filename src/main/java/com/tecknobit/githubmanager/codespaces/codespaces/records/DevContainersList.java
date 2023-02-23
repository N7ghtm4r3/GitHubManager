package com.tecknobit.githubmanager.codespaces.codespaces.records;

import com.tecknobit.githubmanager.records.parents.GitHubList;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The {@code DevContainersList} class is useful to format a GitHub's dev containers list
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/codespaces/codespaces#list-devcontainer-configurations-in-a-repository-for-the-authenticated-user">
 *             List devcontainer configurations in a repository for the authenticated user</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/codespaces/codespaces#get-default-attributes-for-a-codespace">
 *             Get default attributes for a codespace</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see GitHubList
 **/
public class DevContainersList extends GitHubList {

    /**
     * {@code devContainers} list of {@link DevContainer}
     **/
    private final ArrayList<DevContainer> devContainers;

    /**
     * Constructor to init an {@link DevContainersList}
     *
     * @param devContainers : list of {@link DevContainer}
     **/
    public DevContainersList(ArrayList<DevContainer> devContainers) {
        super(devContainers.size());
        this.devContainers = devContainers;
    }

    /**
     * Constructor to init an {@link DevContainersList}
     *
     * @param totalCount    : total number of the items in the list
     * @param devContainers : list of {@link DevContainer}
     **/
    public DevContainersList(int totalCount, ArrayList<DevContainer> devContainers) {
        super(totalCount);
        this.devContainers = devContainers;
    }

    /**
     * Constructor to init a {@link DevContainersList}
     *
     * @param jDevContainersList : dev containers list as {@link JSONObject}
     **/
    public DevContainersList(JSONObject jDevContainersList) {
        super(jDevContainersList);
        this.devContainers = new ArrayList<>();
        JSONArray jDevContainers = hResponse.getJSONArray("", new JSONArray());
        for (int j = 0; j < jDevContainers.length(); j++)
            devContainers.add(new DevContainer(jDevContainers.getJSONObject(j)));
    }

    /**
     * Method to get {@link #devContainers} instance <br>
     * No-any params required
     *
     * @return {@link #devContainers} instance as {@link ArrayList} of {@link DevContainer}
     **/
    public ArrayList<DevContainer> getDevContainers() {
        return devContainers;
    }

    /**
     * The {@code DevContainer} class is useful to format a GitHub's dev container
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class DevContainer extends InnerClassItem {

        /**
         * {@code path} of the container
         **/
        private final String path;

        /**
         * {@code name} of the container
         **/
        private final String name;

        /**
         * Constructor to init a {@link DevContainer}
         *
         * @param path : path of the container
         * @param name : name of the container
         **/
        public DevContainer(String path, String name) {
            super(null);
            this.path = path;
            this.name = name;
        }

        /**
         * Constructor to init a {@link DevContainer}
         *
         * @param jDevContainer : dev container details as {@link JSONObject}
         **/
        public DevContainer(JSONObject jDevContainer) {
            super(jDevContainer);
            path = hItem.getString("name");
            name = hItem.getString("path");
        }

        /**
         * Method to get {@link #path} instance <br>
         * No-any params required
         *
         * @return {@link #path} instance as {@link String}
         **/
        public String getPath() {
            return path;
        }

        /**
         * Method to get {@link #name} instance <br>
         * No-any params required
         *
         * @return {@link #name} instance as {@link String}
         **/
        public String getName() {
            return name;
        }

    }

}
