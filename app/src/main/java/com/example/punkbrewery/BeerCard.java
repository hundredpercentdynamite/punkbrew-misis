package com.example.punkbrewery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.punkbrewery.api.DBViewModel;
import com.example.punkbrewery.api.Database;
import com.example.punkbrewery.api.PunkApi;
import com.example.punkbrewery.api.PunkService;
import com.example.punkbrewery.entities.Beer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeerCard extends Fragment {

    private final PunkService punkService = PunkApi.getPunkService();
    private final String base = PunkApi.getApiBase();
    TextView header;
    ImageView imageView;
    TextView description;
    Button favBtn;
    Map<String, Boolean> userData;
    DatabaseReference dbRef;
    Boolean isFavorite = false;


    Beer data;
    public BeerCard() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_beer_card, container, false);
        header = view.findViewById(R.id.beer_header);
        imageView = view.findViewById(R.id.beer_image);
        description = view.findViewById(R.id.beer_description);
        favBtn = view.findViewById(R.id.beer_add_favorite);

        Bundle arguments = getArguments();
        String beerId = arguments.getString("beerId");
        Call<List<Beer>> call = punkService.getBeerById(beerId);
        call.enqueue(new Callback<List<Beer>>() {
            @Override
            public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {
                List<Beer> body = response.body();
                Beer beerData = body.get(0);
                data = beerData;
                updateView();
            }

            @Override
            public void onFailure(Call<List<Beer>> call, Throwable t) {

            }
        });
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        if (currentFirebaseUser != null) {
            dbRef = Database.getDBRef(currentFirebaseUser.getUid());
        }
        DBViewModel model = new ViewModelProvider(this).get(DBViewModel.class);
        model.getUserData().observe(getViewLifecycleOwner(), data -> {
            userData = data;
            updateView();
        });

        favBtn.setOnClickListener(v -> {
            dbRef.child(getBeerId()).setValue(!isFavorite);
        });

        return view;
    }

    public void updateView() {
        if (data != null) {
            Glide.with(this)
                    .load(data.image_url)
                    .into(imageView);

            header.setText(data.name);
            description.setText(data.description);
            if (userData != null && userData.containsKey(getBeerId()) && userData.get(getBeerId())) {
                isFavorite = true;
                favBtn.setText("Remove from favorites");
            } else {
                isFavorite = false;
                favBtn.setText("Add to favorites");
            }
        }
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private String getBeerId() {
        return "beer_" + data.id;
    }
}