package com.tecknobit.githubmanager.activity.feeds.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * The {@code Feed} class is useful to format a GitHub's feed
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:<a href="https://docs.github.com/en/rest/activity/feeds#get-feeds">
 * Get feeds</a>
 * @see GitHubResponse
 **/
public class Feed extends GitHubResponse {

    /**
     * {@code timelineUrl} timeline url value
     **/
    private final String timelineUrl;

    /**
     * {@code userUrl} user url value
     **/
    private final String userUrl;

    /**
     * {@code currentUserPublicUrl} current user public url value
     **/
    private final String currentUserPublicUrl;

    /**
     * {@code currentUserUrl} current user url value
     **/
    private final String currentUserUrl;

    /**
     * {@code currentUserActorUrl} current user actor url value
     **/
    private final String currentUserActorUrl;

    /**
     * {@code currentUserOrganizationUrl} current user organization url value
     **/
    private final String currentUserOrganizationUrl;

    /**
     * {@code currentUserOrganizationUrls} current user organizations url value
     **/
    private final ArrayList<String> currentUserOrganizationUrls;

    /**
     * {@code securityAdvisoriesUrl} security advisories url value
     **/
    private final String securityAdvisoriesUrl;

    /**
     * {@code links} links list
     **/
    private final HashMap<String, Link> links;

    /**
     * {@code currentUserOrganizations} current user organizations value
     **/
    private final ArrayList<Link> currentUserOrganizations;

    /**
     * Constructor to init a {@link Feed}
     *
     * @param timelineUrl:                 timeline url value
     * @param userUrl:                     user url value
     * @param currentUserPublicUrl:        current user public url value
     * @param currentUserUrl:              current user url value
     * @param currentUserActorUrl:         current user actor url value
     * @param currentUserOrganizationUrl:  current user organization url value
     * @param currentUserOrganizationUrls: current user organizations url value
     * @param securityAdvisoriesUrl:       security advisories url value
     * @param links:                       links list
     * @param currentUserOrganizations:    current user organizations value
     **/
    public Feed(String timelineUrl, String userUrl, String currentUserPublicUrl, String currentUserUrl,
                String currentUserActorUrl, String currentUserOrganizationUrl, ArrayList<String> currentUserOrganizationUrls,
                String securityAdvisoriesUrl, HashMap<String, Link> links, ArrayList<Link> currentUserOrganizations) {
        super(null);
        this.timelineUrl = timelineUrl;
        this.userUrl = userUrl;
        this.currentUserPublicUrl = currentUserPublicUrl;
        this.currentUserUrl = currentUserUrl;
        this.currentUserActorUrl = currentUserActorUrl;
        this.currentUserOrganizationUrl = currentUserOrganizationUrl;
        this.currentUserOrganizationUrls = currentUserOrganizationUrls;
        this.securityAdvisoriesUrl = securityAdvisoriesUrl;
        this.links = links;
        this.currentUserOrganizations = currentUserOrganizations;
    }

    /**
     * Constructor to init a {@link Feed}
     *
     * @param jFeed: feed details as {@link JSONObject}
     **/
    public Feed(JSONObject jFeed) {
        super(jFeed);
        timelineUrl = hResponse.getString("timeline_url");
        userUrl = hResponse.getString("user_url");
        currentUserPublicUrl = hResponse.getString("current_user_public_url");
        currentUserUrl = hResponse.getString("current_user_url");
        currentUserActorUrl = hResponse.getString("current_user_actor_url");
        currentUserOrganizationUrl = hResponse.getString("current_user_organization_url");
        currentUserOrganizationUrls = new ArrayList<>();
        JSONArray jOrganizations = hResponse.getJSONArray("current_user_organization_urls", new JSONArray());
        for (int j = 0; j < jOrganizations.length(); j++)
            currentUserOrganizationUrls.add(jOrganizations.getString(j));
        securityAdvisoriesUrl = hResponse.getString("security_advisories_url");
        links = new HashMap<>();
        currentUserOrganizations = new ArrayList<>();
        JSONObject jLinks = hResponse.getJSONObject("_links", new JSONObject());
        for (String key : jLinks.keySet()) {
            Object link = jLinks.get(key);
            if (link instanceof JSONObject)
                links.put(key, new Link(jLinks.getJSONObject(key).put("name", key)));
            else {
                JSONArray jCOrganizations = ((JSONArray) link);
                for (int j = 0; j < jCOrganizations.length(); j++)
                    currentUserOrganizations.add(new Link(jCOrganizations.getJSONObject(j).put("name", key)));
            }
        }
    }

    /**
     * Method to get {@link #timelineUrl} instance <br>
     * Any params required
     *
     * @return {@link #timelineUrl} instance as {@link String}
     **/
    public String getTimelineUrl() {
        return timelineUrl;
    }

    /**
     * Method to get {@link #userUrl} instance <br>
     * Any params required
     *
     * @return {@link #userUrl} instance as {@link String}
     **/
    public String getUserUrl() {
        return userUrl;
    }

    /**
     * Method to get {@link #currentUserPublicUrl} instance <br>
     * Any params required
     *
     * @return {@link #currentUserPublicUrl} instance as {@link String}
     **/
    public String getCurrentUserPublicUrl() {
        return currentUserPublicUrl;
    }

    /**
     * Method to get {@link #currentUserUrl} instance <br>
     * Any params required
     *
     * @return {@link #currentUserUrl} instance as {@link String}
     **/
    public String getCurrentUserUrl() {
        return currentUserUrl;
    }

    /**
     * Method to get {@link #currentUserActorUrl} instance <br>
     * Any params required
     *
     * @return {@link #currentUserActorUrl} instance as {@link String}
     **/
    public String getCurrentUserActorUrl() {
        return currentUserActorUrl;
    }

    /**
     * Method to get {@link #currentUserOrganizationUrl} instance <br>
     * Any params required
     *
     * @return {@link #currentUserOrganizationUrl} instance as {@link String}
     **/
    public String getCurrentUserOrganizationUrl() {
        return currentUserOrganizationUrl;
    }

    /**
     * Method to get {@link #currentUserOrganizationUrls} instance <br>
     * Any params required
     *
     * @return {@link #currentUserOrganizationUrls} instance as {@link Collection} of {@link String}
     **/
    public Collection<String> getCurrentUserOrganizationUrls() {
        return currentUserOrganizationUrls;
    }

    /**
     * Method to get {@link #securityAdvisoriesUrl} instance <br>
     * Any params required
     *
     * @return {@link #securityAdvisoriesUrl} instance as {@link String}
     **/
    public String getSecurityAdvisoriesUrl() {
        return securityAdvisoriesUrl;
    }

    /**
     * Method to get {@link #links} instance <br>
     * Any params required
     *
     * @return {@link #links} instance as {@link HashMap} of {@link String}, {@link Link}
     **/
    public HashMap<String, Link> getLinks() {
        return links;
    }

    /**
     * Method to get {@link #currentUserOrganizations} instance <br>
     * Any params required
     *
     * @return {@link #currentUserOrganizations} instance as {@link Collection} of {@link Link}
     **/
    public Collection<Link> getCurrentUserOrganizations() {
        return currentUserOrganizations;
    }

    /**
     * The {@code Link} class is useful to format a GitHub's link for {@link Feed}
     *
     * @author N7ghtm4r3 - Tecknobit
     **/
    public static class Link {

        /**
         * {@code name} name of the link
         **/
        private final String name;

        /**
         * {@code href} href value of the link
         **/
        private final String href;

        /**
         * {@code type} type of the link
         **/
        private final String type;

        /**
         * Constructor to init a {@link Link}
         *
         * @param name:     name of the link
         * @param href:     href value of the link
         * @param type:type of the link
         **/
        public Link(String name, String href, String type) {
            this.name = name;
            this.href = href;
            this.type = type;
        }

        /**
         * Constructor to init a {@link Link}
         *
         * @param jLink: link details as {@link JSONObject}
         **/
        public Link(JSONObject jLink) {
            this(jLink.getString("name"), jLink.getString("href"), jLink.getString("type"));
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
         * Method to get {@link #href} instance <br>
         * Any params required
         *
         * @return {@link #href} instance as {@link String}
         **/
        public String getHref() {
            return href;
        }

        /**
         * Method to get {@link #type} instance <br>
         * Any params required
         *
         * @return {@link #type} instance as {@link String}
         **/
        public String getType() {
            return type;
        }

        /**
         * Returns a string representation of the object <br>
         * Any params required
         *
         * @return a string representation of the object as {@link String}
         */
        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

    }

}
