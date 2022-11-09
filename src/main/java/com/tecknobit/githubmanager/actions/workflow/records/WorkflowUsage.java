package com.tecknobit.githubmanager.actions.workflow.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import com.tecknobit.githubmanager.records.basics.GitHubResponse;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class WorkflowUsage extends GitHubResponse {

    private final ArrayList<Billable> billables;

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

    public Collection<Billable> getBillables() {
        return billables;
    }

    public static class Billable {

        protected final String name;
        protected final long totalMs;
        protected final JsonHelper hBillable;

        public Billable(String name, long totalMs) {
            this.name = name;
            this.totalMs = totalMs;
            hBillable = null;
        }

        public Billable(JSONObject jBillable) {
            hBillable = new JsonHelper(jBillable);
            name = hBillable.getString("name");
            totalMs = hBillable.getLong("total_ms", 0);
        }

        public String getName() {
            return name;
        }

        public long getTotalMs() {
            return totalMs;
        }

        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

    }

}
