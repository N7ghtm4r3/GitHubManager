package com.tecknobit.githubmanager.gitdatabase.tags.records;

import com.tecknobit.githubmanager.gitdatabase.references.records.GitReference.RefObject;
import com.tecknobit.githubmanager.records.generic.ShaItem;
import com.tecknobit.githubmanager.records.parents.CommitData.CommitProfile;
import com.tecknobit.githubmanager.records.parents.CommitData.Verification;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code Tag} class is useful to format a GitHub's tag
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/git/tags#create-a-tag-object">
 *             Create a tag object</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/git/tags#get-a-tag">
 *             Get a tag</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see ShaItem
 **/
public class Tag extends ShaItem {

    /**
     * {@code nodeId} node id of the tag
     **/
    private final String nodeId;

    /**
     * {@code tag} name of the tag
     **/
    private final String tag;

    /**
     * {@code tagger} who created the tag
     **/
    private final CommitProfile tagger;

    /**
     * {@code object} of the tag
     **/
    private final RefObject object;

    /**
     * {@code verification} of the tag
     **/
    private final Verification verification;

    /**
     * Constructor to init a {@link Tag}
     *
     * @param sha          : sha of the tag
     * @param url          : url of the sha tag
     * @param tag          : name of the tag
     * @param tagger       : who created the tag
     * @param object       : object of the tag
     * @param verification : verification of the tag
     **/
    public Tag(String sha, String url, String nodeId, String tag, CommitProfile tagger, RefObject object,
               Verification verification) {
        super(sha, url);
        this.nodeId = nodeId;
        this.tag = tag;
        this.tagger = tagger;
        this.object = object;
        this.verification = verification;
    }

    /**
     * Constructor to init a {@link Tag}
     *
     * @param jTag : tag details as {@link JSONObject}
     **/
    public Tag(JSONObject jTag) {
        super(jTag);
        nodeId = hResponse.getString("node_id");
        tag = hResponse.getString("tag");
        tagger = new CommitProfile(hResponse.getJSONObject("tagger", new JSONObject()));
        object = new RefObject(hResponse.getJSONObject("object", new JSONObject()));
        verification = new Verification(hResponse.getJSONObject("verification", new JSONObject()));
    }

    /**
     * Method to get {@link #nodeId} instance <br>
     * No-any params required
     *
     * @return {@link #nodeId} instance as {@link String}
     **/
    public String getNodeId() {
        return nodeId;
    }

    /**
     * Method to get {@link #tag} instance <br>
     * No-any params required
     *
     * @return {@link #tag} instance as {@link String}
     **/
    public String getTag() {
        return tag;
    }

    /**
     * Method to get {@link #tagger} instance <br>
     * No-any params required
     *
     * @return {@link #tagger} instance as {@link CommitProfile}
     **/
    public CommitProfile getTagger() {
        return tagger;
    }

    /**
     * Method to get {@link #object} instance <br>
     * No-any params required
     *
     * @return {@link #object} instance as {@link RefObject}
     **/
    public RefObject getObject() {
        return object;
    }

    /**
     * Method to get {@link #verification} instance <br>
     * No-any params required
     *
     * @return {@link #verification} instance as {@link Verification}
     **/
    public Verification getVerification() {
        return verification;
    }

}
