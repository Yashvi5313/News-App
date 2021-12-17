package com.example.newsapithoughmodelclass.StoryModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StoryRoot {

    @SerializedName("Top Stories")
    @Expose
    private List<TopStory> topStories = null;

    public List<TopStory> getTopStories() {
        return topStories;
    }

    public void setTopStories(List<TopStory> topStories) {
        this.topStories = topStories;
    }
}