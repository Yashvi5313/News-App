package com.example.newsapithoughmodelclass.FeaturedModel;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Root {

    @SerializedName("Featured Tips")
    @Expose
    private List<FeaturedTip> featuredTips = null;

    public List<FeaturedTip> getFeaturedTips() {
        return featuredTips;
    }

    public void setFeaturedTips(List<FeaturedTip> featuredTips) {
        this.featuredTips = featuredTips;
    }
}