package com.example.newsapithoughmodelclass.EventModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Mch {
    @SerializedName("$")
    @Expose
    public Doller do_leer;
    public List<Evt> evt;
    public List<Tma> tma;
    public List<Tmb> tmb;
}
