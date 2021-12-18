package com.example.newsapithoughmodelclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.newsapithoughmodelclass.StoryModel.TopStory;
import com.example.newsapithoughmodelclass.databinding.ActivityFullStoryBinding;
import com.google.gson.Gson;

public class FullStoryActivity extends AppCompatActivity {

    ActivityFullStoryBinding binding;
    String Story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFullStoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setToolbar();

        Intent intent = getIntent();
        Story = intent.getStringExtra("FullStory");
        Gson gson = new Gson();
        TopStory topStory = gson.fromJson(Story, TopStory.class);

        Glide.with(FullStoryActivity.this).load(topStory.getImage()).into(binding.fullImg);
        binding.fullTitle.setText(topStory.getTitle());
        binding.fullStory.setText(topStory.getFullStory());
        binding.fullTime.setText(topStory.getTime());
    }

    private void setToolbar() {
        setSupportActionBar(binding.toolbar3);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar3.setNavigationOnClickListener(v -> finish());
    }
}