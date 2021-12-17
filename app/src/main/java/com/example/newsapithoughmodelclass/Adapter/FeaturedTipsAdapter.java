package com.example.newsapithoughmodelclass.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PostProcessor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsapithoughmodelclass.FeaturedModel.FeaturedTip;
import com.example.newsapithoughmodelclass.databinding.FeaturedTipsItemBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.POST;

public class FeaturedTipsAdapter extends RecyclerView.Adapter<FeaturedTipsAdapter.MyViewHolder> implements Filterable {

    Context context;
    List<FeaturedTip> featuredTipsArrayList;
    List<FeaturedTip> featuredTipsArrayListFull;
    RvClickEvent rvClickEvent;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        FeaturedTipsItemBinding binding;

        public MyViewHolder(FeaturedTipsItemBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }

    public FeaturedTipsAdapter(Context context, ArrayList<FeaturedTip> featuredTipArrayList) {
        this.context = context;
        this.featuredTipsArrayList = featuredTipArrayList;
        featuredTipsArrayListFull = new ArrayList<>(featuredTipArrayList);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(FeaturedTipsItemBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        FeaturedTipsItemBinding bind = holder.binding;
        FeaturedTip featuredTip = featuredTipsArrayList.get(position);

        holder.binding.FtipsTitle.setText(featuredTip.getTitle());
        holder.binding.FtipsTime.setText(featuredTip.getTime());
        Glide.with(context).load(featuredTip.getImage()).into(holder.binding.FTipsImg);
        holder.binding.tipfeedRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvClickEvent.onRootClick(featuredTip, position);
            }
        });
    }

    public void setOnFeaturedTipsClickListener(RvClickEvent rvClickEvent) {
        this.rvClickEvent = rvClickEvent;
    }

    public interface RvClickEvent {
        void onRootClick(FeaturedTip featuredTip, int pos);
    }

    @Override
    public int getItemCount() {
        return featuredTipsArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return featuredTipFilter;
    }

    private Filter featuredTipFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<FeaturedTip> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList = featuredTipsArrayListFull;
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (FeaturedTip item : featuredTipsArrayListFull) {
                    if (item.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            featuredTipsArrayList.clear();
            featuredTipsArrayList.addAll((ArrayList<FeaturedTip>) results.values);
            notifyDataSetChanged();
        }
    };
}
