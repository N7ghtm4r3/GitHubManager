package com.tecknobit.githubmanager.codespaces.machines.records;

import com.tecknobit.githubmanager.codespaces.records.Codespace.Machine;
import com.tecknobit.githubmanager.records.parents.GitHubList;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@code MachinesList} class is useful to format a GitHub's machines list
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/codespaces/machines#list-available-machine-types-for-a-repository">
 *             List available machine types for a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/codespaces/machines#list-machine-types-for-a-codespace">
 *             List machine types for a codespace</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see GitHubList
 **/
public class MachinesList extends GitHubList {

    /**
     * {@code machines} list of {@link Machine}
     **/
    private final ArrayList<Machine> machines;

    /**
     * Constructor to init an {@link MachinesList}
     *
     * @param machines: list of {@link Machine}
     **/
    public MachinesList(ArrayList<Machine> machines) {
        super(machines.size());
        this.machines = machines;
    }

    /**
     * Constructor to init an {@link MachinesList}
     *
     * @param totalCount : total number of the items in the list
     * @param machines:  list of {@link Machine}
     **/
    public MachinesList(int totalCount, ArrayList<Machine> machines) {
        super(totalCount);
        this.machines = machines;
    }

    /**
     * Constructor to init a {@link MachinesList}
     *
     * @param jMachinesList : machines list details as {@link JSONObject}
     **/
    public MachinesList(JSONObject jMachinesList) {
        super(jMachinesList);
        machines = new ArrayList<>();
        JSONArray jMachines = new JSONArray();
        for (int j = 0; j < jMachines.length(); j++)
            machines.add(new Machine(jMachines.getJSONObject(j)));
    }

    /**
     * Method to get {@link #machines} instance <br>
     * Any params required
     *
     * @return {@link #machines} instance as {@link Collection} of {@link Machine}
     **/
    public Collection<Machine> getMachines() {
        return machines;
    }

}
