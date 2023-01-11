package com.tecknobit.githubmanager.codespaces.codespaces.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.records.parents.GitHubList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class DevContainersList extends GitHubList {

    private final ArrayList<DevContainer> devContainers;

    /**
     * Constructor to init an {@link GitHubList}
     *
     * @param devContainers : total number of the items in the list
     **/
    public DevContainersList(ArrayList<DevContainer> devContainers) {
        super(devContainers.size());
        this.devContainers = devContainers;
    }

    /**
     * Constructor to init an {@link GitHubList}
     *
     * @param totalCount : total number of the items in the list
     **/
    public DevContainersList(int totalCount, ArrayList<DevContainer> devContainers) {
        super(totalCount);
        this.devContainers = devContainers;
    }

    /**
     * Constructor to init a {@link GitHubList}
     *
     * @param jDevContainersList : response by {@code "GitHub"} as {@link JSONObject}
     **/
    public DevContainersList(JSONObject jDevContainersList) {
        super(jDevContainersList);
        this.devContainers = new ArrayList<>();
        JSONArray jDevContainers = hResponse.getJSONArray("", new JSONArray());
        for (int j = 0; j < jDevContainers.length(); j++)
            devContainers.add(new DevContainer(jDevContainers.getJSONObject(j)));
    }

    public Collection<DevContainer> getDevContainers() {
        return devContainers;
    }

    public static class DevContainer {

        private final String path;
        private final String name;

        public DevContainer(String path, String name) {
            this.path = path;
            this.name = name;
        }

        public DevContainer(JSONObject jDevContainer) {
            JsonHelper hDevContainer = new JsonHelper(jDevContainer);
            path = hDevContainer.getString("name");
            name = hDevContainer.getString("path");
        }

        public String getPath() {
            return path;
        }

        public String getName() {
            return name;
        }

        /**
         * Returns a string representation of the object <br>
         * Any params required
         *
         * @return a string representation of the object as {@link String}
         */
        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

    }

}
