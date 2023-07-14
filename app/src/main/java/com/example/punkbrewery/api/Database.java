package com.example.punkbrewery.api;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class Database {
    final private static FirebaseDatabase database = FirebaseDatabase.getInstance("https://punkbrew-misis-default-rtdb.europe-west1.firebasedatabase.app");
    private static Map<String, Object> userData;

    public static DatabaseReference getDBRef(String userUid) {
        return database.getReference("favorites/" + userUid);
    }

    public static DatabaseReference getDBRef(String userUid, String beerId) {
        return database.getReference("favorites/" + userUid + "/" + beerId);
    }

    public static DatabaseReference getDBRef() {
        return database.getReference("favorites");
    }

    public static void setUserData(Map<String, Object> userData) {
        Database.userData = userData;
    }

    public static Map<String, Object> getUserData() {
        return userData;
    }
}
