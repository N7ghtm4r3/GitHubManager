package com.tecknobit.githubmanager.actions.selfhosted.runnergroups.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.records.basics.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Runner extends GitHubResponse {

    private final long id;
    private final String name;
    private final String os;
    private final String status;
    private final boolean busy;
    private final ArrayList<Label> labels;

    public Runner(long id, String name, String os, String status, boolean busy, ArrayList<Label> labels) {
        super(null);
        this.id = id;
        this.name = name;
        this.os = os;
        this.status = status;
        this.busy = busy;
        this.labels = labels;
    }

    /**
     * Constructor to init a {@link Runner}
     *
     * @param jRunner : runner details as {@link JSONObject}
     **/
    public Runner(JSONObject jRunner) {
        super(jRunner);
        id = hResponse.getLong("id", 0);
        name = hResponse.getString("name");
        os = hResponse.getString("os");
        status = hResponse.getString("status");
        busy = hResponse.getBoolean("busy");
        labels = new ArrayList<>();
        JSONArray jLabels = hResponse.getJSONArray("labels", new JSONArray());
        for (int j = 0; j < jLabels.length(); j++)
            labels.add(new Label(jLabels.getJSONObject(j)));
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOs() {
        return os;
    }

    public String getStatus() {
        return status;
    }

    public boolean isBusy() {
        return busy;
    }

    public ArrayList<Label> getLabels() {
        return labels;
    }

    public static class Label {

        private final long id;
        private final String name;
        private final String type;

        public Label(long id, String name, String type) {
            this.id = id;
            this.name = name;
            this.type = type;
        }

        public Label(JSONObject jLabel) {
            JsonHelper hLabel = new JsonHelper(jLabel);
            id = hLabel.getLong("id", 0);
            name = hLabel.getString("name");
            type = hLabel.getString("type");
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

    }

}
