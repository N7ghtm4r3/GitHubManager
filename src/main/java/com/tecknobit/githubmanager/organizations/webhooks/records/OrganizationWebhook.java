package com.tecknobit.githubmanager.organizations.webhooks.records;

import com.tecknobit.githubmanager.apps.webhooks.records.Webhook;
import com.tecknobit.githubmanager.records.parents.BaseResponseDetails;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code OrganizationWebhook} class is useful to format a GitHub's organization webhook
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/orgs/webhooks#list-organization-webhooks">
 *             List organization webhooks</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/orgs/webhooks#create-an-organization-webhook">
 *             Create an organization webhook</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/orgs/webhooks#get-an-organization-webhook">
 *             Get an organization webhook</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/orgs/webhooks#update-an-organization-webhook">
 *             Update an organization webhook</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see BaseResponseDetails
 **/
public class OrganizationWebhook extends BaseResponseDetails {

    /**
     * {@code pingUrl} the ping url of the organization webhook
     **/
    private final String pingUrl;

    /**
     * {@code deliveriesUrl} deliveries url of the organization webhook
     **/
    private final String deliveriesUrl;

    /**
     * {@code events} of the organization webhook
     **/
    private final ArrayList<String> events;

    /**
     * {@code active} whether the organization webhook is active
     **/
    private final boolean active;

    /**
     * {@code config} of the organization webhook
     **/
    private final Webhook config;

    /**
     * {@code updatedAt} update date of the organization webhook
     **/
    private final String updatedAt;

    /**
     * {@code createdAt} creation date of the organization webhook
     **/
    private final String createdAt;

    /**
     * {@code type} of the organization webhook
     **/
    private final String type;

    /**
     * Constructor to init a {@link OrganizationWebhook}
     *
     * @param id            : the ping url of the organization webhook
     * @param name          : the name of the item
     * @param url           : url value
     * @param pingUrl       : the name of the item
     * @param deliveriesUrl : deliveries url of the organization webhook
     * @param events        : events of the organization webhook
     * @param active        : whether the organization webhook is active
     * @param config        : config of the organization webhook
     * @param updatedAt     : update date of the organization webhook
     * @param createdAt     : creation date of the organization webhook
     * @param type          : type of the organization webhook
     **/
    public OrganizationWebhook(long id, String name, String url, String pingUrl, String deliveriesUrl,
                               ArrayList<String> events, boolean active, Webhook config, String updatedAt,
                               String createdAt, String type) {
        super(id, name, url);
        this.pingUrl = pingUrl;
        this.deliveriesUrl = deliveriesUrl;
        this.events = events;
        this.active = active;
        this.config = config;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.type = type;
    }

    /**
     * Constructor to init a {@link OrganizationWebhook}
     *
     * @param jOrganizationWebhook : organization webhook details as {@link JSONObject}
     **/
    public OrganizationWebhook(JSONObject jOrganizationWebhook) {
        super(jOrganizationWebhook);
        pingUrl = hResponse.getString("ping_url");
        deliveriesUrl = hResponse.getString("deliveries_url");
        events = returnStringsList(hResponse.getJSONArray("events"));
        active = hResponse.getBoolean("active");
        config = new Webhook(hResponse.getJSONObject("config"));
        updatedAt = hResponse.getString("updated_at");
        createdAt = hResponse.getString("created_at");
        type = hResponse.getString("type");
    }

    /**
     * Method to get {@link #pingUrl} instance <br>
     * No-any params required
     *
     * @return {@link #pingUrl} instance as {@link String}
     **/
    public String getPingUrl() {
        return pingUrl;
    }

    /**
     * Method to get {@link #deliveriesUrl} instance <br>
     * No-any params required
     *
     * @return {@link #deliveriesUrl} instance as {@link String}
     **/
    public String getDeliveriesUrl() {
        return deliveriesUrl;
    }

    /**
     * Method to get {@link #events} instance <br>
     * No-any params required
     *
     * @return {@link #events} instance as {@link ArrayList} of {@link String}
     **/
    public ArrayList<String> getEvents() {
        return events;
    }

    /**
     * Method to get {@link #active} instance <br>
     * No-any params required
     *
     * @return {@link #active} instance as boolean
     **/
    public boolean isActive() {
        return active;
    }

    /**
     * Method to get {@link #config} instance <br>
     * No-any params required
     *
     * @return {@link #config} instance as {@link Webhook}
     **/
    public Webhook getConfig() {
        return config;
    }

    /**
     * Method to get {@link #updatedAt} instance <br>
     * No-any params required
     *
     * @return {@link #updatedAt} instance as {@link String}
     **/
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Method to get {@link #updatedAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #updatedAt} timestamp as long
     **/
    public long getUpdatedAtTimestamp() {
        return getDateTimestamp(updatedAt);
    }

    /**
     * Method to get {@link #createdAt} instance <br>
     * No-any params required
     *
     * @return {@link #createdAt} instance as {@link String}
     **/
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Method to get {@link #createdAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #createdAt} timestamp as long
     **/
    public long getCreatedAtTimestamp() {
        return getDateTimestamp(createdAt);
    }

    /**
     * Method to get {@link #type} instance <br>
     * No-any params required
     *
     * @return {@link #type} instance as {@link String}
     **/
    public String getType() {
        return type;
    }

}
