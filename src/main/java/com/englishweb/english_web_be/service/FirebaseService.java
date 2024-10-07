package com.englishweb.english_web_be.service;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Service;

@Service
public class FirebaseService {

    private final DatabaseReference databaseReference;

    public FirebaseService() {
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void saveData(String key, Object data) {
        databaseReference.child(key).setValueAsync(data);
    }
}
