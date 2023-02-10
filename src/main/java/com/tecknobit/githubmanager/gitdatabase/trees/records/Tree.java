package com.tecknobit.githubmanager.gitdatabase.trees.records;

import com.tecknobit.githubmanager.gitdatabase.references.records.GitReference.RefObject.ObjectType;
import com.tecknobit.githubmanager.records.generic.ShaItem;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.apimanager.trading.TradingTools.roundValue;

/**
 * The {@code Tree} class is useful to format a GitHub's tree
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/git/trees#create-a-tree">
 *             Create a tree</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/git/trees#get-a-tree">
 *             Get a tree</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 * @see ShaItem
 **/
public class Tree extends ShaItem {

    /**
     * {@code truncated} whether the tree is truncated
     **/
    private final boolean truncated;

    /**
     * {@code tree} list of {@link TreeValue}
     **/
    private final ArrayList<TreeValue> tree;

    /**
     * Constructor to init a {@link Tree}
     *
     * @param sha       : sha of the tree
     * @param url       : url of the sha item
     * @param truncated : whether the tree is truncated
     * @param tree      : list of {@link TreeValue}
     **/
    public Tree(String sha, String url, boolean truncated, ArrayList<TreeValue> tree) {
        super(sha, url);
        this.truncated = truncated;
        this.tree = tree;
    }

    /**
     * Constructor to init a {@link Tree}
     *
     * @param jTree : tree details as {@link JSONObject}
     **/
    public Tree(JSONObject jTree) {
        super(jTree);
        truncated = hResponse.getBoolean("truncated");
        tree = new ArrayList<>();
        JSONArray lTree = hResponse.getJSONArray("tree", new JSONArray());
        for (int j = 0; j < lTree.length(); j++)
            tree.add(new TreeValue(lTree.getJSONObject(j)));
    }

    /**
     * Method to get {@link #truncated} instance <br>
     * No-any params required
     *
     * @return {@link #truncated} instance as boolean
     **/
    public boolean isTruncated() {
        return truncated;
    }

    /**
     * Method to get {@link #tree} instance <br>
     * No-any params required
     *
     * @return {@link #tree} instance as {@link Collection} of {@link TreeValue}
     **/
    public Collection<TreeValue> getTree() {
        return tree;
    }

    /**
     * The {@code TreeValue} class is useful to format a GitHub's tree value for {@link Tree}
     *
     * @author N7ghtm4r3 - Tecknobit
     * @see GitHubResponse
     * @see ShaItem
     **/
    public static class TreeValue extends ShaItem {

        /**
         * {@code path} of the tree
         **/
        private final String path;

        /**
         * {@code mode} of the tree
         **/
        private final String mode;

        /**
         * {@code type} of the tree
         **/
        private final ObjectType type;

        /**
         * {@code size} of the tree
         **/
        private final double size;

        /**
         * {@code content} of the tree
         **/
        private final String content;

        /**
         * Constructor to init a {@link TreeValue}
         *
         * @param sha      : sha of the tree
         * @param path     : path of the tree
         * @param mode:    mode of the tree
         * @param type     : type of the tree
         * @param content: content of the tree
         * @apiNote this constructor is useful when you need to create a new {@link Tree} with apposite request
         **/
        public TreeValue(String sha, String path, String mode, ObjectType type, String content) {
            this(sha, null, path, mode, type, 0, content);
        }

        /**
         * Constructor to init a {@link TreeValue}
         *
         * @param sha      : sha of the tree
         * @param url      : url of the tree
         * @param path     : path of the tree
         * @param mode:    mode of the tree
         * @param type     : type of the tree
         * @param size     : size of the tree
         * @param content: content of the tree
         **/
        public TreeValue(String sha, String url, String path, String mode, ObjectType type, double size, String content) {
            super(sha, url);
            this.path = path;
            this.mode = mode;
            this.type = type;
            this.size = size;
            this.content = content;
        }

        /**
         * Constructor to init a {@link TreeValue}
         *
         * @param jTreeValue : tree value details as {@link JSONObject}
         **/
        public TreeValue(JSONObject jTreeValue) {
            super(jTreeValue);
            path = hResponse.getString("path");
            mode = hResponse.getString("mode");
            type = ObjectType.valueOf(hResponse.getString("type"));
            size = hResponse.getDouble("size", 0);
            content = hResponse.getString("content");
        }

        /**
         * Method to get {@link #path} instance <br>
         * No-any params required
         *
         * @return {@link #path} instance as {@link String}
         **/
        public String getPath() {
            return path;
        }

        /**
         * Method to get {@link #mode} instance <br>
         * No-any params required
         *
         * @return {@link #mode} instance as {@link String}
         **/
        public String getMode() {
            return mode;
        }

        /**
         * Method to get {@link #type} instance <br>
         * No-any params required
         *
         * @return {@link #type} instance as {@link ObjectType}
         **/
        public ObjectType getType() {
            return type;
        }

        /**
         * Method to get {@link #size} instance <br>
         * No-any params required
         *
         * @return {@link #size} instance as double
         **/
        public double getSize() {
            return size;
        }

        /**
         * Method to get {@link #size} instance
         *
         * @param decimals: number of digits to round final value
         * @return {@link #size} instance rounded with decimal digits inserted
         * @throws IllegalArgumentException if decimalDigits is negative
         **/
        public double getSize(int decimals) {
            return roundValue(size, decimals);
        }

    }

}
