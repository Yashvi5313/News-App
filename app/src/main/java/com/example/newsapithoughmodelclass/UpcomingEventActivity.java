package com.example.newsapithoughmodelclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.newsapithoughmodelclass.Adapter.UpcomingEventAdapter;
import com.example.newsapithoughmodelclass.EventModel.Evt;
import com.example.newsapithoughmodelclass.EventModel.Mch;
import com.example.newsapithoughmodelclass.databinding.ActivityUpcomingEventBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UpcomingEventActivity extends AppCompatActivity {

    ActivityUpcomingEventBinding binding;
    UpcomingEventAdapter upcomingEventAdapter;
    String TeamFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpcomingEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setToolbar();

        Intent intent = getIntent();
        TeamFeed = intent.getStringExtra("Team");
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Mch>>() {
        }.getType();
        ArrayList<Mch> mchArrayList = gson.fromJson(TeamFeed, listType);

        HashMap<String, ArrayList<Mch>> map = new HashMap<>();

        for (int i = 0; i < mchArrayList.size(); i++) {
            String title = mchArrayList.get(i).evt.get(0).EvtName;
            if (map.containsKey(title)) {
                ArrayList<Mch> arrayList = new ArrayList<>();
                arrayList = map.get(title);
                arrayList.add(mchArrayList.get(i));
                map.put(title, arrayList);

            } else {
                ArrayList<Mch> tempArrayList = new ArrayList();
                tempArrayList.add(mchArrayList.get(i));
                map.put(title, tempArrayList);
            }
        }

        ArrayList<mapModel> evtArrayList = new ArrayList<>();
        for (Map.Entry<String, ArrayList<Mch>> entry : map.entrySet()) {
            mapModel mapModel = new mapModel();
            mapModel.title = entry.getKey();
            mapModel.mchList = entry.getValue();
            evtArrayList.add(mapModel);
        }

        upcomingEventAdapter = new UpcomingEventAdapter(UpcomingEventActivity.this, evtArrayList);
        binding.EventRecyclerview.setAdapter(upcomingEventAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(UpcomingEventActivity.this, LinearLayoutManager.VERTICAL, false);
        binding.EventRecyclerview.setLayoutManager(layoutManager);
    }

    private void setToolbar() {
        setSupportActionBar(binding.toolbar6);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar6.setNavigationOnClickListener(v -> finish());
    }
}