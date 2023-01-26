package com.tecknobit.githubmanager.actions.workflow.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@code WorkflowUsage} class is useful to format a GitHub's workflow usage
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/actions/workflows#get-workflow-usage">
 * Get workflow usage</a>
 * @see GitHubResponse
 **/
public class WorkflowUsage extends GitHubResponse {

    /**
     * {@code billables} billables list
     **/
    private final ArrayList<Billable> billables;

    /**
     * Constructor to init a {@link WorkflowUsage}
     *
     * @param billables: billables list
     **/
    public WorkflowUsage(ArrayList<Billable> billables) {
        super(null);
        this.billables = billables;
    }

    /**
     * Constructor to init a {@link WorkflowUsage}
     *
     * @param jWorkflowUsage : workflow usage details as {@link JSONObject}
     **/
    public WorkflowUsage(JSONObject jWorkflowUsage) {
        super(jWorkflowUsage);
        billables = new ArrayList<>();
        JSONObject jBillable = hResponse.getJSONObject("billable", new JSONObject());
        for (String key : jBillable.keySet())
            billables.add(new Billable(jBillable.getJSONObject(key).put("name", key)));
    }

    /**
     * Method to get {@link #billables} instance <br>
     * Any params required
     *
     * @return {@link #billables} instance as {@link Collection} of {@link Billable}
     **/
    public Collection<Billable> getBillables() {
        return billables;
    }

    /**
     * The {@code BillableRun} class is useful to format a GitHub's billable
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class Billable extends InnerClassItem {

        /**
         * {@code name} name of the billable
         **/
        protected final String name;

        /**
         * {@code totalMs} total run duration in milliseconds
         **/
        protected final long totalMs;

        /**
         * Constructor to init a {@link Billable}
         *
         * @param name:    name of the billable
         * @param totalMs: total run duration in milliseconds
         **/
        public Billable(String name, long totalMs) {
            super(null);
            this.name = name;
            this.totalMs = totalMs;
        }

        /**
         * Constructor to init a {@link Billable}
         *
         * @param jBillable: billable details as {@link JSONObject}
         **/
        public Billable(JSONObject jBillable) {
            super(jBillable);
            name = hItem.getString("name");
            totalMs = hItem.getLong("total_ms", 0);
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
         * Method to get {@link #totalMs} instance <br>
         * Any params required
         *
         * @return {@link #totalMs} instance as long
         **/
        public long getTotalMs() {
            return totalMs;
        }

    }

}
