package com.tecknobit.githubmanager.repositories.repositories.records;

import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * The {@code RepositoryLanguages} class is useful to format a GitHub's repository languages
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/repos/repos#list-repository-languages">
 * List repository languages</a>
 * @see GitHubResponse
 **/
public class RepositoryLanguages extends GitHubResponse {

    /**
     * {@code languages} list of languages
     **/
    private final HashMap<String, Integer> languages;

    /**
     * Constructor to init a {@link RepositoryLanguages}
     *
     * @param languages : list of languages
     * @param bytes     : list of bytes written in a specific language
     **/
    public RepositoryLanguages(ArrayList<String> languages, ArrayList<Integer> bytes) {
        super(null);
        if (languages.size() != bytes.size())
            throw new IllegalArgumentException("For one language must be one bytes value");
        this.languages = new HashMap<>();
        for (int j = 0; j < languages.size(); j++)
            this.languages.put(languages.get(j).toLowerCase(), bytes.get(j));
    }

    /**
     * Constructor to init a {@link RepositoryLanguages}
     *
     * @param jRepositoryLanguages : repository languages details as {@link JSONObject}
     **/
    public RepositoryLanguages(JSONObject jRepositoryLanguages) {
        super(jRepositoryLanguages);
        languages = new HashMap<>();
        for (String language : jRepositoryLanguages.keySet())
            languages.put(language.toLowerCase(), jRepositoryLanguages.getInt(language));
    }

    /**
     * Method to get languages <br>
     * No-any params required
     *
     * @return languages as {@link Set} of {@link String}
     **/
    public Set<String> getLanguages() {
        return languages.keySet();
    }

    /**
     * Method to get bytes <br>
     * No-any params required
     *
     * @return bytes as {@link Collection} of {@link Integer}
     **/
    public Collection<Integer> getBytes() {
        return languages.values();
    }

    /**
     * Method to get bytes written in a language <br>
     *
     * @param language: the language from fetch the bytes
     * @return bytes written in a language as {@link Integer}
     **/
    public Integer getLanguageBytes(String language) {
        language = language.toLowerCase();
        return languages.get(language);
    }

    /**
     * Method to get bytes written in a language <br>
     *
     * @param language: the language from fetch the bytes
     * @return if a language is contained in a specific {@link Repository}
     **/
    public boolean languageContained(String language) {
        language = language.toLowerCase();
        return languages.containsKey(language);
    }

}
