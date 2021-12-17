package com.example.newsapithoughmodelclass.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapithoughmodelclass.FeaturedModel.Tip;
import com.example.newsapithoughmodelclass.StoryModel.TopStory;
import com.example.newsapithoughmodelclass.databinding.FanTipsItemBinding;

import java.util.ArrayList;

public class FanTipsAdapter extends RecyclerView.Adapter<FanTipsAdapter.MyViewHolder> {

    Context context;
    ArrayList<Tip> tipsArrayList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        FanTipsItemBinding binding;

        public MyViewHolder(@NonNull FanTipsItemBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }

    public FanTipsAdapter(Context context, ArrayList<Tip> tipsArrayList) {
        this.context = context;
        this.tipsArrayList = tipsArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(FanTipsItemBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        FanTipsItemBinding bind = holder.binding;
        Tip tip = tipsArrayList.get(position);

        holder.binding.tipTitle.setText(tip.getTipstitle());
        holder.binding.tipTips.setText(tip.getTips());
    }

    @Override
    public int getItemCount() {
        return tipsArrayList.size();
    }
}
