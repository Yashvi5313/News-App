package com.example.newsapithoughmodelclass.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsapithoughmodelclass.StoryModel.TopStory;
import com.example.newsapithoughmodelclass.databinding.TopStoryItemBinding;

import java.util.ArrayList;
import java.util.List;

public class TopStoryAdapter extends RecyclerView.Adapter<TopStoryAdapter.MyViewHolder> {
    Context context;
    List<TopStory> topStoryList;
    ItemFullClickListener itemFullClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TopStoryItemBinding binding;

        public MyViewHolder(TopStoryItemBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }

    public TopStoryAdapter(Context context, List<TopStory> topStoryList) {
        this.context = context;
        this.topStoryList = topStoryList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(TopStoryItemBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        TopStoryItemBinding bind = holder.binding;
        TopStory topStory = topStoryList.get(position);

        Glide.with(context).load(topStory.getImage()).into(holder.binding.sfImg);
        holder.binding.sfTitle.setText(topStory.getTitle());
        holder.binding.sfSubTitle.setText(topStory.getSubTitle());
        holder.binding.sfTime.setText(topStory.getTime());
        holder.binding.sfeedRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemFullClickListener.onItemClick(topStory, position);
            }
        });
    }

    public void setItemFullClickListener(ItemFullClickListener itemFullClickListener) {
        this.itemFullClickListener = itemFullClickListener;
    }

    public interface ItemFullClickListener {
        void onItemClick(TopStory topStory, int pos);
    }

    @Override
    public int getItemCount() {
        return topStoryList.size();
    }
}
