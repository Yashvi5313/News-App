package com.example.newsapithoughmodelclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.newsapithoughmodelclass.Adapter.TopStoryAdapter;
import com.example.newsapithoughmodelclass.FeaturedModel.FeaturedTip;
import com.example.newsapithoughmodelclass.StoryModel.TopStory;
import com.example.newsapithoughmodelclass.databinding.ActivityTopStoryBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TopStoryActivity extends AppCompatActivity {

    ActivityTopStoryBinding binding;
    TopStoryAdapter topStoryAdapter;
    String StoryFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTopStoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        StoryFeed = intent.getStringExtra("Story");
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<TopStory>>() {
        }.getType();
        ArrayList<TopStory> topStoryArrayList = gson.fromJson(StoryFeed, listType);

        topStoryAdapter = new TopStoryAdapter(TopStoryActivity.this, topStoryArrayList);
        binding.storyRecyclerview.setAdapter(topStoryAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(TopStoryActivity.this, LinearLayoutManager.VERTICAL, false);
        binding.storyRecyclerview.setLayoutManager(layoutManager);

        binding.toolbar3.topStoryBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        topStoryAdapter.setItemFullClickListener(new TopStoryAdapter.ItemFullClickListener() {
            @Override
            public void onItemClick(TopStory topStory, int pos) {
                Gson gson1 = new Gson();
                String storyJson = gson1.toJson(topStory);
                Intent intent1 = new Intent(TopStoryActivity.this, FullStoryActivity.class);
                intent1.putExtra("FullStory", storyJson);
                startActivity(intent1);
            }
        });
    }
}