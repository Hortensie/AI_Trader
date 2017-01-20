package com.vaadin.polymer.demo.client.sampler.ai_trader;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Piotr on 2016-12-02.
 * Provides interface to FireBase database by:
 * Saving data
 * Getting data as List<Object> based on various child String
 */

class FireBaseDb
{
    private static List<String> internalCopy= new ArrayList<>();

    static List<String> getInternalCopy() {

        return internalCopy;
    }

    private DatabaseReference databaseReference;

    FireBaseDb() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
    }

    static String EncodeString(String string) {

        return string.replace(".", ",").replace("[","").replace("]","");
    }


    public static String DecodeString(String string) {
        return string.replace(",", ".");
    }

    void saveDataToFireBaseDb(String base, String name, String value){

        databaseReference.child(base).child(name).setValue(value);

    }

    void writeNewSymbol(String symbol, String time, long ctm, double open, double high, double low, double close, double vol) {


        SymbolRecord newSymbolRecord = new SymbolRecord(ctm, open, high, low, close, vol);
        Symbol newSymbol = new Symbol(ctm,newSymbolRecord);

        databaseReference.child(symbol).child(time).setValue(newSymbol);
    }

    void getDataFromFireBaseDb(String childName)
    {
     databaseReference.child(childName).addValueEventListener(new ValueEventListener() {

         @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

             List<Objects> listObject = (List<Objects>) dataSnapshot.getValue();

                    for (int i=0;i<listObject.size();i++)
                    {
                        internalCopy.add(String.valueOf(listObject.get(i)));
                    }
                }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            //on cancel
            }
        });

     }

}

