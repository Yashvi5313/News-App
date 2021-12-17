package com.example.newsapithoughmodelclass.StoryModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopStory {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("sub title")
    @Expose
    private String subTitle;
    @SerializedName("full story")
    @Expose
    private String fullStory;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("time")
    @Expose
    private String time;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getFullStory() {
        return fullStory;
    }

    public void setFullStory(String fullStory) {
        this.fullStory = fullStory;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}