package com.example.newsapithoughmodelclass.FeaturedModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tip {

    @SerializedName("tipstitle")
    @Expose
    private String tipstitle;
    @SerializedName("tips")
    @Expose
    private String tips;

    public String getTipstitle() {
        return tipstitle;
    }

    public void setTipstitle(String tipstitle) {
        this.tipstitle = tipstitle;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }
}