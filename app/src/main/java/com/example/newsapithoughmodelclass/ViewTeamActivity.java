package com.example.newsapithoughmodelclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.newsapithoughmodelclass.Adapter.ViewTeamAdapter;
import com.example.newsapithoughmodelclass.FeaturedModel.SuggestedTeam;
import com.example.newsapithoughmodelclass.databinding.ActivityViewTeamBinding;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ViewTeamActivity extends AppCompatActivity {

    ActivityViewTeamBinding binding;
    ViewTeamAdapter viewTeamAdapter;
    String team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewTeamBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setToolbar();

        Intent intent = getIntent();
        team = intent.getStringExtra("Teams");
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<SuggestedTeam>>() {
        }.getType();
        ArrayList<SuggestedTeam> suggestedTeamArrayList = gson.fromJson(team, listType);

        viewTeamAdapter = new ViewTeamAdapter(ViewTeamActivity.this, suggestedTeamArrayList);
        binding.pager.setAdapter(viewTeamAdapter);

        TabLayoutMediator tabLayout = new TabLayoutMediator(binding.tabPool, binding.pager, true, (tab, position) -> {
        });
        tabLayout.attach();
    }

    private void setToolbar() {
        setSupportActionBar(binding.toolbar7);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar7.setNavigationOnClickListener(v -> finish());
    }
}