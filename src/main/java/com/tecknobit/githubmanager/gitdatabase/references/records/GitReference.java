package com.tecknobit.githubmanager.gitdatabase.references.records;

import com.tecknobit.githubmanager.records.generic.ShaItem;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code GitReference} class is useful to format a GitHub's git reference
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/git/refs#list-matching-references">
 *             List matching references</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/git/refs#get-a-reference">
 *             Get a reference</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/git/refs#create-a-reference">
 *             Create a reference</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/git/refs#update-a-reference">
 *             Update a reference</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class GitReference extends GitHubResponse {

    /**
     * {@code ref} of the reference
     **/
    private final String ref;

    /**
     * {@code nodeId} node id of the reference
     **/
    private final String nodeId;

    /**
     * {@code url} of the reference
     **/
    private final String url;

    /**
     * {@code object} of the reference
     **/
    private final RefObject object;

    /**
     * Constructor to init a {@link GitReference}
     *
     * @param ref    : ref of the reference
     * @param nodeId : node if of the reference
     * @param url    : url of the reference
     * @param object : object of the reference
     **/
    public GitReference(String ref, String nodeId, String url, RefObject object) {
        super(null);
        this.ref = ref;
        this.nodeId = nodeId;
        this.url = url;
        this.object = object;
    }

    /**
     * Constructor to init a {@link GitReference}
     *
     * @param jGitReference: reference details as {@link JSONObject}
     **/
    public GitReference(JSONObject jGitReference) {
        super(jGitReference);
        ref = hResponse.getString("ref");
        nodeId = hResponse.getString("node_id");
        url = hResponse.getString("url");
        object = new RefObject(hResponse.getJSONObject("object", new JSONObject()));
    }

    /**
     * Method to get {@link #ref} instance <br>
     * No-any params required
     *
     * @return {@link #ref} instance as {@link String}
     **/
    public String getRef() {
        return ref;
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
     * Method to get {@link #url} instance <br>
     * No-any params required
     *
     * @return {@link #url} instance as {@link String}
     **/
    public String getUrl() {
        return url;
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
     * The {@code RefObject} class is useful to format a GitHub's git reference object
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see GitHubResponse
     * @see ShaItem
     **/
    public static class RefObject extends ShaItem {

        /**
         * {@code ObjectType} list of available object types
         **/
        public enum ObjectType {

            /**
             * {@code commit} object type
             **/
            commit,

            /**
             * {@code tree} object type
             **/
            tree,

            /**
             * {@code blob} object type
             **/
            blob

        }

        /**
         * {@code type} of the ref object
         **/
        private final ObjectType type;

        /**
         * Constructor to init a {@link RefObject}
         *
         * @param sha   : sha of the tree
         * @param url   : url of the sha item
         * @param type: type of the ref object
         **/
        public RefObject(String sha, String url, ObjectType type) {
            super(sha, url);
            this.type = type;
        }

        /**
         * Constructor to init a {@link RefObject}
         *
         * @param jRefObject : reference object details as {@link JSONObject}
         **/
        public RefObject(JSONObject jRefObject) {
            super(jRefObject);
            type = ObjectType.valueOf(hResponse.getString("type"));
        }

        /**
         * Method to get {@link #type} instance <br>
         * No-any params required
         *
         * @return {@link #type} instance as {@link String}
         **/
        public ObjectType getType() {
            return type;
        }

    }

}
