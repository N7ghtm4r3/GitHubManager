package com.tecknobit.githubmanager.actions.selfhosted.runners.records.labels;

import com.tecknobit.githubmanager.records.parents.GitHubList;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The {@code RunnerLabelsList} class is useful to format a GitHub's labels list
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-labels-for-a-self-hosted-runner-for-an-enterprise">
 *             List labels for a self-hosted runner for an enterprise</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-enterprise">
 *             Add custom labels to a self-hosted runner for an enterprise</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-enterprise">
 *             Set custom labels for a self-hosted runner for an enterprise</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-all-custom-labels-from-a-self-hosted-runner-for-an-enterprise">
 *             Remove all custom labels from a self-hosted runner for an enterprise</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-labels-for-a-self-hosted-runner-for-an-organization">
 *             List labels for a self-hosted runner for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-an-organization">
 *             Add custom labels to a self-hosted runner for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-an-organization">
 *             Set custom labels for a self-hosted runner for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-all-custom-labels-from-a-self-hosted-runner-for-an-organization">
 *             Remove all custom labels from a self-hosted runner for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-an-organization">
 *             Remove a custom label from a self-hosted runner for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#list-labels-for-a-self-hosted-runner-for-a-repository">
 *             List labels for a self-hosted runner for a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#add-custom-labels-to-a-self-hosted-runner-for-a-repository">
 *             Add custom labels to a self-hosted runner for a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#set-custom-labels-for-a-self-hosted-runner-for-a-repository">
 *             Set custom labels for a self-hosted runner for a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-all-custom-labels-from-a-self-hosted-runner-for-a-repository">
 *             Remove all custom labels from a self-hosted runner for a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runners#remove-a-custom-label-from-a-self-hosted-runner-for-a-repository">
 *             Remove a custom label from a self-hosted runner for a repository</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see GitHubList
 **/
public class RunnerLabelsList extends GitHubList {

    /**
     * {@code labels} labels list
     **/
    private final ArrayList<RunnerLabel> labels;

    /**
     * Constructor to init an {@link RunnerLabelsList}
     *
     * @param labels: labels list
     **/
    public RunnerLabelsList(ArrayList<RunnerLabel> labels) {
        super(labels.size());
        this.labels = labels;
    }

    /**
     * Constructor to init an {@link RunnerLabelsList}
     *
     * @param totalCount : total number of the labels in the list
     * @param labels:    labels list
     **/
    public RunnerLabelsList(int totalCount, ArrayList<RunnerLabel> labels) {
        super(totalCount);
        this.labels = labels;
    }

    /**
     * Constructor to init a {@link RunnerLabelsList}
     *
     * @param jLabelsList : labels list details as {@link JSONObject}
     **/
    public RunnerLabelsList(JSONObject jLabelsList) {
        super(jLabelsList);
        labels = new ArrayList<>();
        JSONArray jLabels = hResponse.getJSONArray("labels", new JSONArray());
        for (int j = 0; j < jLabels.length(); j++)
            labels.add(new RunnerLabel(jLabels.getJSONObject(j)));
    }

    /**
     * Method to get {@link #labels} instance <br>
     * No-any params required
     *
     * @return {@link #labels} instance as {@link ArrayList} of {@link RunnerLabel}
     **/
    public ArrayList<RunnerLabel> getLabels() {
        return labels;
    }

}
