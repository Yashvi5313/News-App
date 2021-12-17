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
import com.example.newsapithoughmodelclass.databinding.StoryItemBinding;

import java.util.List;

public class MainStoryAdapter extends RecyclerView.Adapter<MainStoryAdapter.ViewHolder> {

    Context context;
    List<TopStory> topStoriesList;
    ItemClickListener itemClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        StoryItemBinding binding;

        public ViewHolder(@NonNull StoryItemBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }

    public MainStoryAdapter(Context context, List<TopStory> topStoriesList) {
        this.context = context;
        this.topStoriesList = topStoriesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(StoryItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        StoryItemBinding bind = holder.binding;
        TopStory topStory = topStoriesList.get(position);

        holder.binding.stTitle.setText(topStory.getTitle());
        holder.binding.stSubTitle.setText(topStory.getSubTitle());
        holder.binding.stTime.setText(topStory.getTime());
        Glide.with(context).load(topStory.getImage()).into(holder.binding.stImg);
        holder.binding.relative2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(topStory, position);
            }
        });
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(TopStory topStory, int pos);
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
