package com.example.newsapithoughmodelclass.EventModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Doller {
    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("typ")
    @Expose
    public String typ;

    @SerializedName("ord")
    @Expose
    public String ord;

    @SerializedName("dt")
    @Expose
    public String dt;
}
