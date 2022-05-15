package com.example.punkbrewery;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Menu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Menu extends Fragment {
    private static final String ARG_BEER_ID = "beerId";

    public Menu() {
        // Required empty public constructor
    }

    public static Menu newInstance() {
       return new Menu();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_menu, container, false);
        Button navigationBeerListBtn = fragmentView.findViewById(R.id.navigation_list);
        navigationBeerListBtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_menu_to_beers));
//        Button navigationFavoriteBeerBtn = fragmentView.findViewById(R.id.navigation_favorite);
//        navigationBeerListBtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_menu_to_favorite));
        Button navigationRandomBeerBtn = fragmentView.findViewById(R.id.navigation_random);
        Bundle bundle = new Bundle();
        bundle.putString(ARG_BEER_ID, "random");
        navigationRandomBeerBtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_menu_to_beerCard, bundle));

        return fragmentView;
    }
}