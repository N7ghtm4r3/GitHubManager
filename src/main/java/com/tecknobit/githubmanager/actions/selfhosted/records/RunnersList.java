package com.tecknobit.githubmanager.actions.selfhosted.records;

import com.tecknobit.githubmanager.records.basics.GitHubList;
import com.tecknobit.githubmanager.records.basics.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@code RunnersList} class is useful to format a GitHub's runners list
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
 * @see GitHubList
 **/
// TODO: 05/11/2022 FIX DOCU STRING
public class RunnersList extends GitHubList {

    /**
     * {@code runners} runners list
     **/
    private final ArrayList<Runner> runners;

    /**
     * Constructor to init an {@link RunnersList}
     *
     * @param runners: runners list
     **/
    public RunnersList(ArrayList<Runner> runners) {
        super(runners.size());
        this.runners = runners;
    }

    /**
     * Constructor to init an {@link RunnersList}
     *
     * @param totalCount : total number of the runners in the list
     * @param runners:   runners list
     **/
    public RunnersList(int totalCount, ArrayList<Runner> runners) {
        super(totalCount);
        this.runners = runners;
    }

    /**
     * Constructor to init a {@link RunnersList}
     *
     * @param jRunnersList : runners list details as {@link JSONObject}
     **/
    public RunnersList(JSONObject jRunnersList) {
        super(jRunnersList);
        runners = new ArrayList<>();
        JSONArray jRunners = hResponse.getJSONArray("runners", new JSONArray());
        for (int j = 0; j < jRunners.length(); j++)
            runners.add(new Runner(jRunners.getJSONObject(j)));
    }

    /**
     * Method to get {@link #runners} instance <br>
     * Any params required
     *
     * @return {@link #runners} instance as {@link Collection} of {@link Runner}
     **/
    public Collection<Runner> getRunners() {
        return runners;
    }

}
