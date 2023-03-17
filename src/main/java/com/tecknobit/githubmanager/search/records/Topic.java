package com.tecknobit.githubmanager.search.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Topic extends GitHubResponse {

    private final String name;
    private final String displayName;
    private final String shortDescription;
    private final String description;
    private final String createdBy;
    private final String released;
    private final String createdAt;
    private final String updatedAt;
    private final boolean featured;
    private final boolean curated;
    private final int repositoryCount;
    private final String logoUrl;
    private final ArrayList<TopicOccurrence> related;
    private final ArrayList<TopicOccurrence> aliases;

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

    private ArrayList<TopicOccurrence> returnTopicOccurrences(JSONArray jOccurrences) {
        ArrayList<TopicOccurrence> topicOccurrences = new ArrayList<>();
        if (jOccurrences != null)
            for (int j = 0; j < jOccurrences.length(); j++)
                topicOccurrences.add(new TopicOccurrence(jOccurrences.getJSONObject(j)));
        return topicOccurrences;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getReleased() {
        return released;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public boolean isFeatured() {
        return featured;
    }

    public boolean isCurated() {
        return curated;
    }

    public int getRepositoryCount() {
        return repositoryCount;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public ArrayList<TopicOccurrence> getRelated() {
        return related;
    }

    public ArrayList<TopicOccurrence> getAliases() {
        return aliases;
    }

    public static class TopicOccurrence extends InnerClassItem {

        private final long id;
        private final String name;
        private final long topicId;
        private final String relationType;

        public TopicOccurrence(long id, String name, long topicId, String relationType) {
            super(null);
            this.id = id;
            this.name = name;
            this.topicId = topicId;
            this.relationType = relationType;
        }

        public TopicOccurrence(JSONObject jTopicOccurrence) {
            super(jTopicOccurrence);
            id = hItem.getLong("id", 0);
            name = hItem.getString("name");
            topicId = hItem.getLong("topic_id", 0);
            relationType = hItem.getString("relation_type");
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public long getTopicId() {
            return topicId;
        }

        public String getRelationType() {
            return relationType;
        }

    }

}
