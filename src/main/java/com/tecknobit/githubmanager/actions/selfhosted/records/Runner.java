package com.tecknobit.githubmanager.actions.selfhosted.records;

import com.tecknobit.githubmanager.actions.selfhosted.runners.records.labels.GitHubLabel;
import com.tecknobit.githubmanager.records.basics.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@code Runner} class is useful to format a GitHub's runner
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runners-in-a-group-for-an-enterprise">
 *             List self-hosted runners in a group for an enterprise</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/actions/self-hosted-runner-groups#list-self-hosted-runners-in-a-group-for-an-organization">
 *             List self-hosted runners in a group for an organization</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
// TODO: 05/11/2022 FIX DOCUSTRING
public class Runner extends GitHubResponse {

    /**
     * {@code id} identifier of the runner
     **/
    private final long id;

    /**
     * {@code name} the name of the runner
     **/
    private final String name;

    /**
     * {@code os} the Operating System of the runner
     **/
    private final String os;

    /**
     * {@code status} the status of the runner
     **/
    private final String status;

    /**
     * {@code busy} flag for busy value
     **/
    private final boolean busy;

    /**
     * {@code labels} labels list
     **/
    private final ArrayList<GitHubLabel> labels;

    /**
     * Constructor to init a {@link Runner}
     *
     * @param id:       identifier of the runner
     * @param name:     the name of the runner
     * @param os:the    Operating System of the runner
     * @param status:   the status of the runner
     * @param busy:flag for busy value
     * @param labels:   labels list
     **/
    public Runner(long id, String name, String os, String status, boolean busy, ArrayList<GitHubLabel> labels) {
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
            labels.add(new GitHubLabel(jLabels.getJSONObject(j)));
    }

    /**
     * Method to get {@link #id} instance <br>
     * Any params required
     *
     * @return {@link #id} instance as long
     **/
    public long getId() {
        return id;
    }

    /**
     * Method to get {@link #name} instance <br>
     * Any params required
     *
     * @return {@link #name} instance as {@link String}
     **/
    public String getName() {
        return name;
    }

    /**
     * Method to get {@link #os} instance <br>
     * Any params required
     *
     * @return {@link #os} instance as {@link String}
     **/
    public String getOs() {
        return os;
    }

    /**
     * Method to get {@link #status} instance <br>
     * Any params required
     *
     * @return {@link #status} instance as {@link String}
     **/
    public String getStatus() {
        return status;
    }

    /**
     * Method to get {@link #busy} instance <br>
     * Any params required
     *
     * @return {@link #busy} instance as boolean
     **/
    public boolean isBusy() {
        return busy;
    }

    /**
     * Method to get {@link #labels} instance <br>
     * Any params required
     *
     * @return {@link #labels} instance as {@link Collection} of {@link GitHubLabel}
     **/
    public Collection<GitHubLabel> getLabels() {
        return labels;
    }

}
