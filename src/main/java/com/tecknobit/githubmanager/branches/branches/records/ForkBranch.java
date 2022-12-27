package com.tecknobit.githubmanager.branches.branches.records;

import com.tecknobit.githubmanager.records.basics.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code ForkBranch} class is useful to format a GitHub's fork branch
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://docs.github.com/en/rest/branches/branches#sync-a-fork-branch-with-the-upstream-repository">
 * Sync a fork branch with the upstream repository</a>
 * @see GitHubResponse
 **/
public class ForkBranch extends GitHubResponse {

    /**
     * {@code mergeType} type of the merge
     **/
    private final MergeType mergeType;
    /**
     * {@code baseBranch} base branch of the fork branch
     **/
    private final String baseBranch;

    /**
     * Constructor to init a {@link ForkBranch}
     *
     * @param mergeType  : type of the merge
     * @param baseBranch : base branch of the fork branch
     **/
    public ForkBranch(MergeType mergeType, String baseBranch) {
        super(null);
        this.mergeType = mergeType;
        this.baseBranch = baseBranch;
    }

    /**
     * Constructor to init a {@link ForkBranch}
     *
     * @param jForkBranch : fork branch details as {@link JSONObject}
     **/
    public ForkBranch(JSONObject jForkBranch) {
        super(jForkBranch);
        mergeType = MergeType.valueOf(hResponse.getString("merge_type", "").replace("-", "_"));
        baseBranch = hResponse.getString("base_branch");
    }

    /**
     * Method to get {@link #mergeType} instance <br>
     * Any params required
     *
     * @return {@link #mergeType} instance as {@link MergeType}
     **/
    public MergeType getMergeType() {
        return mergeType;
    }

    /**
     * Method to get {@link #baseBranch} instance <br>
     * Any params required
     *
     * @return {@link #baseBranch} instance as {@link String}
     **/
    public String getBaseBranch() {
        return baseBranch;
    }

    /**
     * {@code MergeType} list of merge types
     **/
    public enum MergeType {

        /**
         * {@code "merge"} type
         **/
        merge("merge"),

        /**
         * {@code "fast-forward"} type
         **/
        fast_forward("fast-forward"),

        /**
         * {@code "none"} type
         **/
        none("none");

        /**
         * {@code type} value
         **/
        private final String type;

        /**
         * Constructor to init a {@link MergeType}
         *
         * @param type : value type
         **/
        MergeType(String type) {
            this.type = type;
        }

        /**
         * Method to get {@link #type} instance <br>
         * Any params required
         *
         * @return {@link #type} instance as {@link String}
         **/
        @Override
        public String toString() {
            return type;
        }

    }

}
