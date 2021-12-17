package com.example.newsapithoughmodelclass.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapithoughmodelclass.FeaturedModel.SuggestedTeam;
import com.example.newsapithoughmodelclass.databinding.ViewTeamsItemBinding;

import java.util.ArrayList;

public class ViewTeamAdapter extends RecyclerView.Adapter<ViewTeamAdapter.MyViewHolder> {
    Context context;
    ArrayList<SuggestedTeam> suggestedTeamArrayList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ViewTeamsItemBinding binding;

        public MyViewHolder(@NonNull ViewTeamsItemBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }

    public ViewTeamAdapter(Context context, ArrayList<SuggestedTeam> suggestedTeamArrayList) {
        this.context = context;
        this.suggestedTeamArrayList = suggestedTeamArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewTeamAdapter.MyViewHolder(ViewTeamsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ViewTeamsItemBinding bind = holder.binding;
        SuggestedTeam suggestedTeam = suggestedTeamArrayList.get(position);

        holder.binding.PlayerName.setText(suggestedTeam.getPlayerList().get(0).getName());
        holder.binding.PlayerName1.setText(suggestedTeam.getPlayerList().get(1).getName());
        holder.binding.PlayerName2.setText(suggestedTeam.getPlayerList().get(2).getName());
        holder.binding.PlayerName3.setText(suggestedTeam.getPlayerList().get(3).getName());
        holder.binding.PlayerName4.setText(suggestedTeam.getPlayerList().get(4).getName());
        holder.binding.PlayerName5.setText(suggestedTeam.getPlayerList().get(5).getName());
        holder.binding.PlayerName6.setText(suggestedTeam.getPlayerList().get(6).getName());
        holder.binding.PlayerName7.setText(suggestedTeam.getPlayerList().get(7).getName());
        holder.binding.PlayerName8.setText(suggestedTeam.getPlayerList().get(8).getName());
        holder.binding.PlayerName9.setText(suggestedTeam.getPlayerList().get(9).getName());
        holder.binding.PlayerName10.setText(suggestedTeam.getPlayerList().get(10).getName());
    }

    @Override
    public int getItemCount() {
        return suggestedTeamArrayList.size();
    }
}
