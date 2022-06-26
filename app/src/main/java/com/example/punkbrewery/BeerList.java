package com.example.punkbrewery;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.punkbrewery.api.BeerListViewModel;
import com.example.punkbrewery.entities.Beer;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class BeerList extends Fragment {

    BeerListViewModel model;
    private List<Beer> beerList = new ArrayList<>();
    private BeerRecyclerViewAdapter beerRecyclerViewAdapter;

    private Button buttonPrev;
    private Button buttonNext;

    private int page = 1;
    private final int per_page = 25;

    public BeerList() {
    }

    public static BeerList newInstance() {
        BeerList fragment = new BeerList();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void getBeers() {
        model.loadBeerList(page, per_page);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.beer_item_list, container, false);

        model = new ViewModelProvider(this).get(BeerListViewModel.class);
        model.getBeerList(page, per_page).observe(getViewLifecycleOwner(), data -> {
            beerList = data;
            updateView();
        });
        // Set the adapter
        beerRecyclerViewAdapter = new BeerRecyclerViewAdapter(beerList);
        RecyclerView recyclerView = view.findViewById(R.id.beer_list);
        recyclerView.setAdapter(beerRecyclerViewAdapter);

        buttonPrev = view.findViewById(R.id.button_prev);
        buttonNext = view.findViewById(R.id.button_next);
        buttonPrev.setVisibility(View.GONE);
        buttonPrev.setOnClickListener(view1 -> {
            if (page != 1) {
                page -= 1;
            }
            getBeers();
        });
        buttonNext.setOnClickListener(view1 -> {
            page += 1;
            getBeers();
        });
        return view;
    }

    void updateView() {
        beerRecyclerViewAdapter.setBeerList(beerList);
        if (beerList.isEmpty()) {
            buttonNext.setVisibility(View.GONE);
        } else {
            buttonNext.setVisibility(View.VISIBLE);
        }
        if (page == 1) {
            buttonPrev.setVisibility(View.GONE);
        } else {
            buttonPrev.setVisibility(View.VISIBLE);
        }
    }
}