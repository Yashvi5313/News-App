package com.example.newsapithoughmodelclass.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapithoughmodelclass.EventModel.Mch;
import com.example.newsapithoughmodelclass.databinding.EventItemBinding;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {
    Context context;
    List<Mch> mchList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        EventItemBinding binding;

        public MyViewHolder(@NonNull EventItemBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }

    public EventAdapter(Context context, List<Mch> mchList) {
        this.context = context;
        this.mchList = mchList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(EventItemBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        EventItemBinding bind = holder.binding;
        Mch mch = mchList.get(position);

        holder.binding.bbLeague.setText(mch.evt.get(0).EvtName);
        holder.binding.T20.setText(mch.do_leer.typ);
        holder.binding.TeamA.setText(mch.tma.get(0).TmaName);
        holder.binding.TeamB.setText(mch.tmb.get(0).TmbName);
        holder.binding.Date.setText(mch.do_leer.dt);
    }
    
    @Override
    public int getItemCount() {
        return mchList.size();
    }
}

