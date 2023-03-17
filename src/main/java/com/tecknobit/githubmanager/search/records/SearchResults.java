package com.tecknobit.githubmanager.search.records;

import com.tecknobit.githubmanager.gitdatabase.commits.records.GitCommit;
import com.tecknobit.githubmanager.pulls.pulls.records.PullRequest;
import com.tecknobit.githubmanager.records.parents.GitHubOperation.Label;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import com.tecknobit.githubmanager.records.parents.User;
import com.tecknobit.githubmanager.repositories.repositories.records.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchResults extends GitHubResponse {

    private final int totalCount;
    private final boolean incompleteResults;
    private final ArrayList<SearchItem> items;

    public SearchResults(int totalCount, boolean incompleteResults, ArrayList<SearchItem> items) {
        super(null);
        this.totalCount = totalCount;
        this.incompleteResults = incompleteResults;
        this.items = items;
    }

    public SearchResults(Class itemType, JSONObject jResponse) {
        super(jResponse);
        totalCount = hResponse.getInt("total_count", 0);
        incompleteResults = hResponse.getBoolean("incomplete_results");
        items = new ArrayList<>();
        JSONArray jItems = hResponse.getJSONArray("items", new JSONArray());
        for (int j = 0; j < jItems.length(); j++)
            items.add(new SearchItem(itemType, jItems.getJSONObject(j)));
    }

    public int getTotalCount() {
        return totalCount;
    }

    public boolean areIncompleteResults() {
        return incompleteResults;
    }

    public ArrayList<SearchItem> getItems() {
        return items;
    }

    public static class SearchItem extends InnerClassItem {

        private final Object item;
        private final double score;
        private final ArrayList<TextMatch> textMatches;

        public SearchItem(Object item, double score, ArrayList<TextMatch> textMatches) {
            super(null);
            this.item = item;
            this.score = score;
            this.textMatches = textMatches;
        }

        public SearchItem(Class itemType, JSONObject jSearchItem) {
            super(jSearchItem);
            score = hItem.getDouble("score", 0);
            textMatches = new ArrayList<>();
            JSONArray jTextMatches = hItem.getJSONArray("text_matches", new JSONArray());
            for (int j = 0; j < jTextMatches.length(); j++)
                textMatches.add(new TextMatch(jTextMatches.getJSONObject(j)));
            jSearchItem.remove("score");
            jSearchItem.remove("text_matches");
            String descriptor = itemType.descriptorString();
            if (descriptor.contains("Code"))
                item = new Code(jSearchItem);
            else if (descriptor.contains("GitCommit"))
                item = new GitCommit(jSearchItem);
            else if (descriptor.contains("PullRequest"))
                item = new PullRequest(jSearchItem);
            else if (descriptor.contains("Label"))
                item = new Label(jSearchItem);
            else if (descriptor.contains("Repository"))
                item = new Repository(jSearchItem);
            else if (descriptor.contains("Topic"))
                item = new Topic(jSearchItem);
            else if (descriptor.contains("User"))
                item = new User(jSearchItem);
            else
                item = null;
        }

        public <T> T getItem() {
            return (T) item;
        }

        public double getScore() {
            return score;
        }

        public ArrayList<TextMatch> getTextMatches() {
            return textMatches;
        }

        public static class TextMatch extends InnerClassItem {

            private final String objectUrl;
            private final String objectType;
            private final String property;
            private final String fragment;
            private final ArrayList<Match> matches;

            public TextMatch(String objectUrl, String objectType, String property, String fragment,
                             ArrayList<Match> matches) {
                super(null);
                this.objectUrl = objectUrl;
                this.objectType = objectType;
                this.property = property;
                this.fragment = fragment;
                this.matches = matches;
            }

            public TextMatch(JSONObject jTextMatch) {
                super(jTextMatch);
                objectUrl = hItem.getString("object_url");
                objectType = hItem.getString("object_type");
                property = hItem.getString("property");
                fragment = hItem.getString("fragment");
                matches = new ArrayList<>();
                JSONArray jMatches = hItem.getJSONArray("matches", new JSONArray());
                for (int j = 0; j < jMatches.length(); j++)
                    matches.add(new Match(jMatches.getJSONObject(j)));
            }

            public String getObjectUrl() {
                return objectUrl;
            }

            public String getObjectType() {
                return objectType;
            }

            public String getProperty() {
                return property;
            }

            public String getFragment() {
                return fragment;
            }

            public ArrayList<Match> getMatches() {
                return matches;
            }

            public static class Match extends InnerClassItem {

                private final String text;
                private final ArrayList<Integer> indices;

                public Match(String text, ArrayList<Integer> indices) {
                    super(null);
                    this.text = text;
                    this.indices = indices;
                }

                public Match(JSONObject jMatch) {
                    super(jMatch);
                    text = hItem.getString("text");
                    indices = new ArrayList<>();
                    JSONArray jIndices = hItem.getJSONArray("indices", new JSONArray());
                    for (int j = 0; j < jIndices.length(); j++)
                        indices.add(jIndices.getInt(j));
                }

                public String getText() {
                    return text;
                }

                public ArrayList<Integer> getIndices() {
                    return indices;
                }

            }

        }

    }

}
