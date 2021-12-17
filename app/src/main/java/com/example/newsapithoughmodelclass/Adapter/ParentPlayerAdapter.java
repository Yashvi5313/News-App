package com.example.newsapithoughmodelclass.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapithoughmodelclass.FeaturedModel.SuggestedTeam;
import com.example.newsapithoughmodelclass.FeaturedModel.Tip;
import com.example.newsapithoughmodelclass.databinding.ParentPlayerItemBinding;

import java.util.ArrayList;

public class ParentPlayerAdapter extends RecyclerView.Adapter<ParentPlayerAdapter.MyViewHolder> {

    Context context;
    ArrayList<SuggestedTeam> suggestedTeamArrayList;
    //ItemClickListener itemClickListener;

    public ParentPlayerAdapter(Context context, ArrayList<SuggestedTeam> suggestedTeamArrayList) {
        this.context = context;
        this.suggestedTeamArrayList = suggestedTeamArrayList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ParentPlayerItemBinding binding;

        public MyViewHolder(@NonNull ParentPlayerItemBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(ParentPlayerItemBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ParentPlayerItemBinding bind = holder.binding;
        SuggestedTeam suggestedTeam = suggestedTeamArrayList.get(position);
        PlayerAdapter playerAdapter = new PlayerAdapter(context, suggestedTeam.getPlayerList());
        holder.binding.childRecyclerview.setAdapter(playerAdapter);
        holder.binding.parentItemTitle.setText(suggestedTeam.getTitle());
//        holder.binding.ViewAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                itemClickListener.onItemClick(suggestedTeam, position);
//            }
//        });
    }

//    public void setItemClickListener(ItemClickListener itemClickListener) {
//        this.itemClickListener = itemClickListener;
//    }
//
//    public interface ItemClickListener {
//        void onItemClick(SuggestedTeam suggestedTeam, int pos);
//    }

    @Override
    public int getItemCount() {
        return suggestedTeamArrayList.size();
    }

}
