package com.tecknobit.githubmanager.reactions.records;

import com.tecknobit.githubmanager.records.parents.InnerClassItem;
import org.json.JSONObject;

/**
 * The {@code Reactions} class is useful to format a GitHub's reactions item
 *
 * @author N7ghtm4r3 - Tecknobit
 * @see InnerClassItem
 **/
public class Reactions extends InnerClassItem {

    /**
     * {@code url} of the reactions
     **/
    private final String url;

    /**
     * {@code totalCount} of the reactions
     **/
    private final int totalCount;

    /**
     * {@code plusOne} plus one reaction
     **/
    private final int plusOne;

    /**
     * {@code minusOne} minus one reaction
     **/
    private final int minusOne;

    /**
     * {@code laugh} reaction
     **/
    private final int laugh;

    /**
     * {@code confused} reaction
     **/
    private final int confused;

    /**
     * {@code heart} reaction
     **/
    private final int heart;

    /**
     * {@code hooray} reaction
     **/
    private final int hooray;

    /**
     * {@code eyes} reaction
     **/
    private final int eyes;

    /**
     * {@code rocket} reaction
     **/
    private final int rocket;

    /**
     * Constructor to init a {@link Reactions}
     *
     * @param url        : url of the reactions
     * @param totalCount :  total count of the reactions
     * @param plusOne    :  plus one reaction
     * @param minusOne   :  minus one reaction
     * @param laugh      :  laugh reaction
     * @param confused   :  confused reaction
     * @param heart      :  heart reaction
     * @param hooray     :  hooray reaction
     * @param eyes       :  eyes reaction
     * @param rocket     : rocket reaction
     **/
    public Reactions(String url, int totalCount, int plusOne, int minusOne, int laugh, int confused,
                     int heart, int hooray, int eyes, int rocket) {
        super(null);
        this.url = url;
        this.totalCount = totalCount;
        this.plusOne = plusOne;
        this.minusOne = minusOne;
        this.laugh = laugh;
        this.confused = confused;
        this.heart = heart;
        this.hooray = hooray;
        this.eyes = eyes;
        this.rocket = rocket;
    }

    /**
     * Constructor to init a {@link Reactions}
     *
     * @param jReactions : reactions details as {@link JSONObject}
     **/
    public Reactions(JSONObject jReactions) {
        super(jReactions);
        url = hItem.getString("url");
        totalCount = hItem.getInt("total_count", 0);
        plusOne = hItem.getInt("+1", 0);
        minusOne = hItem.getInt("-1", 0);
        laugh = hItem.getInt("laugh", 0);
        confused = hItem.getInt("confused", 0);
        heart = hItem.getInt("heart", 0);
        hooray = hItem.getInt("hooray", 0);
        eyes = hItem.getInt("eyes", 0);
        rocket = hItem.getInt("rocket", 0);
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
     * Method to get {@link #totalCount} instance <br>
     * No-any params required
     *
     * @return {@link #totalCount} instance as int
     **/
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * Method to get {@link #plusOne} instance <br>
     * No-any params required
     *
     * @return {@link #plusOne} instance as int
     **/
    public int getPlusOne() {
        return plusOne;
    }

    /**
     * Method to get {@link #minusOne} instance <br>
     * No-any params required
     *
     * @return {@link #minusOne} instance as int
     **/
    public int getMinusOne() {
        return minusOne;
    }

    /**
     * Method to get {@link #laugh} instance <br>
     * No-any params required
     *
     * @return {@link #laugh} instance as int
     **/
    public int getLaugh() {
        return laugh;
    }

    /**
     * Method to get {@link #confused} instance <br>
     * No-any params required
     *
     * @return {@link #confused} instance as int
     **/
    public int getConfused() {
        return confused;
    }

    /**
     * Method to get {@link #heart} instance <br>
     * No-any params required
     *
     * @return {@link #heart} instance as int
     **/
    public int getHeart() {
        return heart;
    }

    /**
     * Method to get {@link #hooray} instance <br>
     * No-any params required
     *
     * @return {@link #hooray} instance as int
     **/
    public int getHooray() {
        return hooray;
    }

    /**
     * Method to get {@link #eyes} instance <br>
     * No-any params required
     *
     * @return {@link #eyes} instance as int
     **/
    public int getEyes() {
        return eyes;
    }

    /**
     * Method to get {@link #rocket} instance <br>
     * No-any params required
     *
     * @return {@link #rocket} instance as int
     **/
    public int getRocket() {
        return rocket;
    }

}
