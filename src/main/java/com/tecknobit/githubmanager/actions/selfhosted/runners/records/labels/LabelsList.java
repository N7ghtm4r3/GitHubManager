package com.tecknobit.githubmanager.actions.selfhosted.runners.records.labels;

import com.tecknobit.githubmanager.records.basics.GitHubList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class LabelsList extends GitHubList {

    private final ArrayList<GitHubLabel> labels;

    /**
     * Constructor to init an {@link LabelsList}
     *
     * @param labels: labels list
     **/
    public LabelsList(ArrayList<GitHubLabel> labels) {
        super(labels.size());
        this.labels = labels;
    }

    /**
     * Constructor to init an {@link LabelsList}
     *
     * @param totalCount : total number of the labels in the list
     * @param labels:    labels list
     **/
    public LabelsList(int totalCount, ArrayList<GitHubLabel> labels) {
        super(totalCount);
        this.labels = labels;
    }

    /**
     * Constructor to init a {@link LabelsList}
     *
     * @param jLabelsList : labels list details as {@link JSONObject}
     **/
    public LabelsList(JSONObject jLabelsList) {
        super(jLabelsList);
        labels = new ArrayList<>();
        JSONArray jLabels = hResponse.getJSONArray("labels", new JSONArray());
        for (int j = 0; j < jLabels.length(); j++)
            labels.add(new GitHubLabel(jLabels.getJSONObject(j)));
    }

    public Collection<GitHubLabel> getLabels() {
        return labels;
    }

}
