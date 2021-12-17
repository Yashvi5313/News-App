package com.example.newsapithoughmodelclass.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapithoughmodelclass.FeaturedModel.Player;
import com.example.newsapithoughmodelclass.databinding.PlayerItemBinding;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.MyViewHolder> {
    Context context;
    List<Player> playerArrayList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        PlayerItemBinding binding;

        public MyViewHolder(@NonNull PlayerItemBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }

    public PlayerAdapter(Context context, List<Player> playerArrayList) {
        this.context = context;
        this.playerArrayList = playerArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlayerAdapter.MyViewHolder(PlayerItemBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PlayerItemBinding bind = holder.binding;
        Player player = playerArrayList.get(position);

        //Glide.with(context).load(playerArrayList.getImage()).into(holder.binding.PlayerImg);
        holder.binding.PlayerName.setText(player.getName());
    }

    @Override
    public int getItemCount() {
        return playerArrayList.size();
    }
}
