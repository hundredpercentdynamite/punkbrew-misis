package com.example.punkbrewery;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.punkbrewery.entities.Beer;
import com.example.punkbrewery.databinding.BeerItemBinding;

import java.util.List;


public class BeerRecyclerViewAdapter extends RecyclerView.Adapter<BeerRecyclerViewAdapter.ViewHolder> {
    private static final String ARG_BEER_ID = "beerId";

    private List<Beer> mValues;

    public BeerRecyclerViewAdapter(List<Beer> items) {
        mValues = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(BeerItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(Long.toString(mValues.get(position).id));
        holder.mContentView.setText(mValues.get(position).name);
        Bundle bundle = new Bundle();
        bundle.putString(ARG_BEER_ID, Long.toString(mValues.get(position).id));
        holder.mContentView.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_itemBeer_to_beerCard, bundle));
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setBeerList(List<Beer> beerList) {
        this.mValues = beerList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public Beer mItem;

        public ViewHolder(BeerItemBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}