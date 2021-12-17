package com.example.newsapithoughmodelclass.FeaturedModel;

import java.util.ArrayList;
import java.util.List;

public class SuggestedTeam {
    private  String title;
    private List<Player> playerList =new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }
}
