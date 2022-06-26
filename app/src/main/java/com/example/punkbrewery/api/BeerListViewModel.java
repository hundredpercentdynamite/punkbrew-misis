package com.example.punkbrewery.api;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.punkbrewery.entities.Beer;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeerListViewModel extends ViewModel {
    private MutableLiveData<List<Beer>> beerList = new MutableLiveData<>();

    public LiveData<List<Beer>> getBeerList(int page, int per_page) {
        loadBeerList(page, per_page);
        return beerList;
    }

    public void loadBeerList(int page, int per_page) {
        HashMap<String, String> params = new HashMap<>();
        params.put("page", Integer.toString(page));
        params.put("per_page", Integer.toString(per_page));
        PunkService service = PunkApi.getPunkService();
        Call<List<Beer>> call = service.getBeerList(params);
        call.enqueue(new Callback<List<Beer>>() {
            @Override
            public void onResponse(@NonNull Call<List<Beer>> call, @NonNull Response<List<Beer>> response) {
                List<Beer> list = response.body();
                beerList.setValue(list);
            }

            @Override
            public void onFailure(@NonNull Call<List<Beer>> call, @NonNull Throwable t) {
                System.out.println("ERRORRRRR");
                System.out.println(t.toString());
            }
        });
    }
}