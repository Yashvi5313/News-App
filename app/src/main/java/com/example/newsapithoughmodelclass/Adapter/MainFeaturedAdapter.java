package com.example.newsapithoughmodelclass.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsapithoughmodelclass.FeaturedModel.FeaturedTip;
import com.example.newsapithoughmodelclass.databinding.FeaturedItemBinding;

import java.util.List;

public class MainFeaturedAdapter extends RecyclerView.Adapter<MainFeaturedAdapter.ViewHolder> {

    Context context;
    List<FeaturedTip> featuredTipsList;
    TipsterClickListener tipsterClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        FeaturedItemBinding binding;

        public ViewHolder(@NonNull FeaturedItemBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }

    public MainFeaturedAdapter(Context context, List<FeaturedTip> featuredTipsList) {
        this.context = context;
        this.featuredTipsList = featuredTipsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(FeaturedItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        FeaturedItemBinding bind = holder.binding;
        FeaturedTip featuredTips = featuredTipsList.get(position);

        Glide.with(context).load(featuredTips.getImage()).into(holder.binding.feImg);
        holder.binding.feTitle.setText(featuredTips.getTitle());
        holder.binding.feTime.setText(featuredTips.getTime());
        holder.binding.relative1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipsterClickListener.onItemClick(featuredTips, position);
            }
        });
    }

    public void setItemClickListener(TipsterClickListener tipsterClickListener) {
        this.tipsterClickListener = tipsterClickListener;
    }

    public interface TipsterClickListener {
        void onItemClick(FeaturedTip featuredTips, int pos);
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
