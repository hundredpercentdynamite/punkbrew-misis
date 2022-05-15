package com.example.punkbrewery.api;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class DBViewModel extends ViewModel {
    DatabaseReference dbRef;
    private MutableLiveData<Map<String, Boolean>> userData;
    public LiveData<Map<String, Boolean>> getUserData() {
        if (userData == null) {
            userData = new MutableLiveData<Map<String, Boolean>>();
            loadUserData();
        }
        return userData;
    }

    private void loadUserData() {
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        if (currentFirebaseUser != null) {
            dbRef = Database.getDBRef(currentFirebaseUser.getUid());
            dbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Map<String, Boolean> value = (Map<String, Boolean>) dataSnapshot.getValue();
                    if (value != null) {
                        userData.setValue(value);
                    } else {
                        userData.setValue(new HashMap<>());
                    }
                    Log.d("DB", "Value is: " + value);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("DB", "Failed to read value.", error.toException());
                }
            });
        }
    }
}