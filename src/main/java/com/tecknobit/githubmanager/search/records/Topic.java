package com.tecknobit.githubmanager.search.records;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.tecknobit.apimanager.formatters.TimeFormatter.getDateTimestamp;

/**
 * The {@code Topic} class is useful to format a GitHub's topic
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/search#search-topics">
 * Search topics</a>
 * @see GitHubResponse
 **/
public class Topic extends GitHubResponse {

    /**
     * {@code name} of the topic
     **/
    private final String name;

    /**
     * {@code displayName} diplay name of the topic
     **/
    private final String displayName;

    /**
     * {@code shortDescription} short description of the topic
     **/
    private final String shortDescription;

    /**
     * {@code description} of the topic
     **/
    private final String description;

    /**
     * {@code createdBy} creator of the topic
     **/
    private final String createdBy;

    /**
     * {@code released} of the topic
     **/
    private final String released;

    /**
     * {@code createdAt} creation date of the topic
     **/
    private final String createdAt;

    /**
     * {@code updatedAt} updated time of the topic
     **/
    private final String updatedAt;

    /**
     * {@code featured} whether the topic is featured
     **/
    private final boolean featured;

    /**
     * {@code curated} whether the topic is curated
     **/
    private final boolean curated;

    /**
     * {@code repositoryCount} repository count of the topic
     **/
    private final int repositoryCount;

    /**
     * {@code logoUrl} logo url of the topic
     **/
    private final String logoUrl;

    /**
     * {@code related} of the topic
     **/
    private final ArrayList<TopicOccurrence> related;

    /**
     * {@code aliases} of the topic
     **/
    private final ArrayList<TopicOccurrence> aliases;

    /**
     * Constructor to init a {@link Topic}
     *
     * @param name: name of the topic
     * @param displayName: display name of the topic
     * @param shortDescription: short description of the topic
     * @param description: description of the topic
     * @param createdBy: creator of the topic
     * @param released: released of the topic
     * @param createdAt: creation date of the topic
     * @param updatedAt: update time of the topic
     * @param featured: whether the topic is featured
     * @param curated: whether the topic is curated
     * @param repositoryCount: repository count of the topic
     * @param logoUrl: logo url of the topic
     * @param related: related of the topic
     * @param aliases: aliases of the topic
     **/
    public Topic(String name, String displayName, String shortDescription, String description, String createdBy,
                 String released, String createdAt, String updatedAt, boolean featured, boolean curated, int repositoryCount,
                 String logoUrl, ArrayList<TopicOccurrence> related, ArrayList<TopicOccurrence> aliases) {
        super(null);
        this.name = name;
        this.displayName = displayName;
        this.shortDescription = shortDescription;
        this.description = description;
        this.createdBy = createdBy;
        this.released = released;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.featured = featured;
        this.curated = curated;
        this.repositoryCount = repositoryCount;
        this.logoUrl = logoUrl;
        this.related = related;
        this.aliases = aliases;
    }

    /**
     * Constructor to init a {@link Topic}
     *
     * @param jTopic: topic details as {@link JSONObject}
     **/
    public Topic(JSONObject jTopic) {
        super(jTopic);
        name = hResponse.getString("name");
        displayName = hResponse.getString("display_name");
        shortDescription = hResponse.getString("short_description");
        description = hResponse.getString("description");
        createdBy = hResponse.getString("created_by");
        released = hResponse.getString("released");
        createdAt = hResponse.getString("created_at");
        updatedAt = hResponse.getString("updated_at");
        featured = hResponse.getBoolean("featured");
        curated = hResponse.getBoolean("curated");
        repositoryCount = hResponse.getInt("repository_count", 0);
        logoUrl = hResponse.getString("logo_url");
        related = returnTopicOccurrences(hResponse.getJSONArray("related"));
        aliases = returnTopicOccurrences(hResponse.getJSONArray("aliases"));
    }

    /**
     * Method to create a {@link TopicOccurrence} list
     *
     * @param jOccurrences: {@link JSONArray} source from assemble the list
     * @return list of topic occurrences as {@link ArrayList} of {@link TopicOccurrence}
     **/
    @Returner
    private ArrayList<TopicOccurrence> returnTopicOccurrences(JSONArray jOccurrences) {
        ArrayList<TopicOccurrence> topicOccurrences = new ArrayList<>();
        if (jOccurrences != null)
            for (int j = 0; j < jOccurrences.length(); j++)
                topicOccurrences.add(new TopicOccurrence(jOccurrences.getJSONObject(j)));
        return topicOccurrences;
    }

    /**
     * Method to get {@link #name} instance <br>
     * No-any params required
     *
     * @return {@link #name} instance as {@link String}
     **/
    public String getName() {
        return name;
    }

    /**
     * Method to get {@link #displayName} instance <br>
     * No-any params required
     *
     * @return {@link #displayName} instance as {@link String}
     **/
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Method to get {@link #shortDescription} instance <br>
     * No-any params required
     *
     * @return {@link #shortDescription} instance as {@link String}
     **/
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Method to get {@link #description} instance <br>
     * No-any params required
     *
     * @return {@link #description} instance as {@link String}
     **/
    public String getDescription() {
        return description;
    }

    /**
     * Method to get {@link #createdBy} instance <br>
     * No-any params required
     *
     * @return {@link #createdBy} instance as {@link String}
     **/
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Method to get {@link #released} instance <br>
     * No-any params required
     *
     * @return {@link #released} instance as {@link String}
     **/
    public String getReleased() {
        return released;
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
     * Method to get {@link #updatedAt} instance <br>
     * No-any params required
     *
     * @return {@link #updatedAt} instance as {@link String}
     **/
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Method to get {@link #createdAt} timestamp <br>
     * No-any params required
     *
     * @return {@link #createdAt} timestamp as long
     **/
    public long getUpdatedAtTimestamp() {
        return getDateTimestamp(createdAt);
    }

    /**
     * Method to get {@link #featured} instance <br>
     * No-any params required
     *
     * @return {@link #featured} instance as boolean
     **/
    public boolean isFeatured() {
        return featured;
    }

    /**
     * Method to get {@link #curated} instance <br>
     * No-any params required
     *
     * @return {@link #curated} instance as boolean
     **/
    public boolean isCurated() {
        return curated;
    }

    /**
     * Method to get {@link #repositoryCount} instance <br>
     * No-any params required
     *
     * @return {@link #repositoryCount} instance as int
     **/
    public int getRepositoryCount() {
        return repositoryCount;
    }

    /**
     * Method to get {@link #logoUrl} instance <br>
     * No-any params required
     *
     * @return {@link #logoUrl} instance as {@link String}
     **/
    public String getLogoUrl() {
        return logoUrl;
    }

    /**
     * Method to get {@link #related} instance <br>
     * No-any params required
     *
     * @return {@link #related} instance as {@link ArrayList} of {@link TopicOccurrence}
     **/
    public ArrayList<TopicOccurrence> getRelated() {
        return related;
    }

    /**
     * Method to get {@link #aliases} instance <br>
     * No-any params required
     *
     * @return {@link #aliases} instance as {@link ArrayList} of {@link TopicOccurrence}
     **/
    public ArrayList<TopicOccurrence> getAliases() {
        return aliases;
    }

    /**
     * The {@code TopicOccurrence} class is useful to format a GitHub's topic occurrence
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see InnerClassItem
     **/
    public static class TopicOccurrence extends InnerClassItem {

        /**
         * {@code id} of the topic occurrence
         **/
        private final long id;

        /**
         * {@code name} of the topic occurrence
         **/
        private final String name;

        /**
         * {@code topicId} topic id of the topic occurrence
         **/
        private final long topicId;

        /**
         * {@code relationType} relation type of the topic occurrence
         **/
        private final String relationType;

        /**
         * Constructor to init a {@link TopicOccurrence}
         *
         * @param id:           id of the topic occurrence
         * @param name:         name of the topic occurrence
         * @param topicId:      topic id of the topic occurrence
         * @param relationType: relation type of the topic occurrence
         **/
        public TopicOccurrence(long id, String name, long topicId, String relationType) {
            super(null);
            this.id = id;
            this.name = name;
            this.topicId = topicId;
            this.relationType = relationType;
        }

        /**
         * Constructor to init a {@link TopicOccurrence}
         *
         * @param jTopicOccurrence: topic occurrence details as {@link JSONObject}
         **/
        public TopicOccurrence(JSONObject jTopicOccurrence) {
            super(jTopicOccurrence);
            id = hItem.getLong("id", 0);
            name = hItem.getString("name");
            topicId = hItem.getLong("topic_id", 0);
            relationType = hItem.getString("relation_type");
        }

        /**
         * Method to get {@link #id} instance <br>
         * No-any params required
         *
         * @return {@link #id} instance as long
         **/
        public long getId() {
            return id;
        }

        /**
         * Method to get {@link #name} instance <br>
         * No-any params required
         *
         * @return {@link #name} instance as {@link String}
         **/
        public String getName() {
            return name;
        }

        /**
         * Method to get {@link #topicId} instance <br>
         * No-any params required
         *
         * @return {@link #topicId} instance as long
         **/
        public long getTopicId() {
            return topicId;
        }

        /**
         * Method to get {@link #relationType} instance <br>
         * No-any params required
         *
         * @return {@link #relationType} instance as {@link String}
         **/
        public String getRelationType() {
            return relationType;
        }

    }

}
