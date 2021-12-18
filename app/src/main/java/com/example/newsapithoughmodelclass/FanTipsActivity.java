package com.example.newsapithoughmodelclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.newsapithoughmodelclass.Adapter.FanTipsAdapter;
import com.example.newsapithoughmodelclass.Adapter.ParentPlayerAdapter;
import com.example.newsapithoughmodelclass.FeaturedModel.Player;
import com.example.newsapithoughmodelclass.FeaturedModel.SuggestedTeam;
import com.example.newsapithoughmodelclass.FeaturedModel.Tip;
import com.example.newsapithoughmodelclass.databinding.ActivityFanTipsBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FanTipsActivity extends AppCompatActivity {

    ActivityFanTipsBinding binding;
    FanTipsAdapter fanTipsAdapter;
    ParentPlayerAdapter parentPlayerAdapter;
    String tTips;

    ArrayList<Player> playerArrayList = new ArrayList<>();
    ArrayList<SuggestedTeam> suggestedTeamArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFanTipsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setToolbar();

        Intent intent = getIntent();
        tTips = intent.getStringExtra("TitleTips");
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Tip>>() {
        }.getType();
        ArrayList<Tip> tipsArrayList = gson.fromJson(tTips, listType);

        fanTipsAdapter = new FanTipsAdapter(FanTipsActivity.this, tipsArrayList);
        binding.TitleTipsRecyclerview.setAdapter(fanTipsAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FanTipsActivity.this, LinearLayoutManager.VERTICAL, false);
        binding.TitleTipsRecyclerview.setLayoutManager(layoutManager);

        for (int i = 0; i < tipsArrayList.size(); i++) {
            if (tipsArrayList.get(i).getTipstitle().contains("Suggested Playing XI")) {
                SuggestedTeam suggestedTeam = new SuggestedTeam();

                if (tipsArrayList.get(i).getTips().contains(",")) {
                    String[] strArray = null;
                    strArray = tipsArrayList.get(i).getTips().split(",");

                    playerArrayList = new ArrayList<>();

                    for (int i1 = 0; i1 < strArray.length; i1++) {
                        Player player = new Player();
                        player.setName(strArray[i1]);
                        player.setImage(strArray[i1]);
                        playerArrayList.add(player);
                    }

                    suggestedTeam.setTitle(tipsArrayList.get(i).getTipstitle());
                    suggestedTeam.setPlayerList(playerArrayList);
                    suggestedTeamArrayList.add(suggestedTeam);
                }
            }
        }

        parentPlayerAdapter = new ParentPlayerAdapter(FanTipsActivity.this, suggestedTeamArrayList);
        binding.PlayerRecyclerview.setAdapter(parentPlayerAdapter);

        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(FanTipsActivity.this, LinearLayoutManager.VERTICAL, false);
        binding.PlayerRecyclerview.setLayoutManager(layoutManager1);

        binding.suggestedTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson1 = new Gson();
                String teamJson = gson1.toJson(suggestedTeamArrayList);
                Intent intent = new Intent(FanTipsActivity.this, ViewTeamActivity.class);
                intent.putExtra("Teams", teamJson);
                startActivity(intent);
            }
        });

//        parentPlayerAdapter.setItemClickListener(new ParentPlayerAdapter.ItemClickListener() {
//            @Override
//            public void onItemClick(SuggestedTeam suggestedTeam, int pos) {
//                Gson gson1 = new Gson();
//                String teamJson = gson1.toJson(suggestedTeamArrayList);
//                Intent intent = new Intent(FanTipsActivity.this, ViewTeamActivity.class);
//                intent.putExtra("Teams", teamJson);
//                startActivity(intent);
//            }
//        });

    }
    private void setToolbar() {
        setSupportActionBar(binding.toolbar1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar1.setNavigationOnClickListener(v -> finish());
    }
}