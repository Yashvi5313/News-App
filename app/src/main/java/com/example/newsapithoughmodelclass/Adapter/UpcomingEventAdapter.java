package com.example.newsapithoughmodelclass.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapithoughmodelclass.EventModel.Mch;
import com.example.newsapithoughmodelclass.FeaturedModel.SuggestedTeam;
import com.example.newsapithoughmodelclass.databinding.EventItemBinding;
import com.example.newsapithoughmodelclass.databinding.ParentPlayerItemBinding;
import com.example.newsapithoughmodelclass.databinding.UpcomingEventItemBinding;
import com.example.newsapithoughmodelclass.mapModel;

import java.util.ArrayList;

public class UpcomingEventAdapter extends RecyclerView.Adapter<UpcomingEventAdapter.MyViewHolder> {
    Context context;
    ArrayList<mapModel> mapModelArrayList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        UpcomingEventItemBinding binding;

        public MyViewHolder(@NonNull UpcomingEventItemBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }

    public UpcomingEventAdapter(Context context, ArrayList<mapModel> mapModelArrayList) {
        this.context = context;
        this.mapModelArrayList = mapModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(UpcomingEventItemBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        UpcomingEventItemBinding bind = holder.binding;
        mapModel mapModel = mapModelArrayList.get(position);

        EventAdapter eventAdapter = new EventAdapter(context, mapModel.mchList);
        holder.binding.childEventRecyclerview.setAdapter(eventAdapter);
        holder.binding.EventTitle.setText(mapModel.title);

        holder.binding.actionDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bind.childEventRecyclerview.getVisibility() == View.GONE) {
                    bind.childEventRecyclerview.setVisibility(View.VISIBLE);
                } else {
                    bind.childEventRecyclerview.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mapModelArrayList.size();
    }
}


