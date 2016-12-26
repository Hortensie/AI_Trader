package com.vaadin.polymer.demo.client.sampler.ai_trader;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.LinkedList;
import java.util.List;

import pro.xstore.api.message.records.RateInfoRecord;

import static android.content.ContentValues.TAG;

/**
 * Created by Piotr on 2016-12-02.
 */
//mew

class FirebaseDb {

    private final FirebaseDatabase firebaseDatabase;
    private final DatabaseReference databaseReference;

    public FirebaseDb() {
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }

    public void saveData(String baza,String name, String value){

        databaseReference.child(baza).child(name.toString()).setValue(value.toString());

    }



}


