package com.example.newsapithoughmodelclass;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import com.example.newsapithoughmodelclass.Adapter.FeaturedTipsAdapter;
import com.example.newsapithoughmodelclass.FeaturedModel.FeaturedTip;
import com.example.newsapithoughmodelclass.databinding.ActivityFeaturedTipsBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class FeaturedTipsActivity extends AppCompatActivity {

    ActivityFeaturedTipsBinding binding;
    FeaturedTipsAdapter featuredTipsAdapter;
    String TipsFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFeaturedTipsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setToolbar();

        Intent intent = getIntent();
        TipsFeed = intent.getStringExtra("Tips");
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<FeaturedTip>>() {
        }.getType();
        ArrayList<FeaturedTip> featuredTipsArrayList = gson.fromJson(TipsFeed, listType);

        featuredTipsAdapter = new FeaturedTipsAdapter(FeaturedTipsActivity.this, featuredTipsArrayList);
        binding.tipsRecyclerview.setAdapter(featuredTipsAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FeaturedTipsActivity.this, LinearLayoutManager.VERTICAL, false);
        binding.tipsRecyclerview.setLayoutManager(layoutManager);

        featuredTipsAdapter.setOnFeaturedTipsClickListener(new FeaturedTipsAdapter.RvClickEvent() {
            @Override
            public void onRootClick(FeaturedTip featuredTip, int pos) {
                Gson gson1 = new Gson();
                String titleTipJson = gson1.toJson(featuredTip.getTips());
                Intent intent = new Intent(FeaturedTipsActivity.this, FanTipsActivity.class);
                intent.putExtra("TitleTips", titleTipJson);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.actionSearch);
        SearchView searchView = (SearchView) searchItem.getActionView();


//        if (searchView.onActionViewCollapsed()) {
//            featuredTipsAdapter.getFilter().filter("");
//        }

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                featuredTipsAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    private void setToolbar() {
        setSupportActionBar(binding.toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar2.setNavigationOnClickListener(v -> finish());
    }
}