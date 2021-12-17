package com.example.newsapithoughmodelclass.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapithoughmodelclass.EventModel.Mch;
import com.example.newsapithoughmodelclass.databinding.EventItemBinding;

import java.util.List;

public class MainTeamAdapter extends RecyclerView.Adapter<MainTeamAdapter.ViewHolder> {
    Context context;
    List<com.example.newsapithoughmodelclass.EventModel.Mch> mch;

    public MainTeamAdapter(Context context, List<com.example.newsapithoughmodelclass.EventModel.Mch> mch) {
        this.context = context;
        this.mch = mch;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        EventItemBinding binding;

        public ViewHolder(@NonNull EventItemBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(EventItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EventItemBinding bind = holder.binding;
        Mch m = mch.get(position);

        holder.binding.bbLeague.setText(m.evt.get(0).EvtName);
        holder.binding.T20.setText(m.do_leer.typ);
        holder.binding.TeamA.setText(m.tma.get(0).TmaName);
        holder.binding.TeamB.setText(m.tmb.get(0).TmbName);
        holder.binding.Date.setText(m.do_leer.dt);
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
