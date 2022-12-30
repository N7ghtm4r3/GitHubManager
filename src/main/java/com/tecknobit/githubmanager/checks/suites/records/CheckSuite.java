package com.tecknobit.githubmanager.checks.suites.records;

import com.tecknobit.githubmanager.records.parents.BaseResponseDetails;
import org.json.JSONObject;

public class CheckSuite extends BaseResponseDetails {

    /**
     * Constructor to init a {@link CheckSuite}
     *
     * @param id   : identifier value
     * @param name : the name of the item
     * @param url  : url value
     **/
    public CheckSuite(long id, String name, String url) {
        super(id, name, url);
    }

    /**
     * Constructor to init a {@link CheckSuite}
     *
     * @param jCheckSuite : check suited details as {@link JSONObject}
     **/
    public CheckSuite(JSONObject jCheckSuite) {
        super(jCheckSuite);
    }

}
