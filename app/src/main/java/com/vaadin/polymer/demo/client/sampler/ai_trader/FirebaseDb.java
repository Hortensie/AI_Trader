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
 * Getting data as List<Object> based on child String
 */
//
class FireBaseDb
{
    private static List<String> internalCopy= new ArrayList<>();

    static List<String> getInternalCopy() {

       /* for (int i=0;i<internalCopy.size();i++)
        {
            Log.d("json internal copy", String.valueOf(internalCopy.get(i)));
        }
        */
        return internalCopy;
    }

    private DatabaseReference databaseReference;

    FireBaseDb() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
    }

    void saveDataToFireBaseDb(String base, String name, String value){

        databaseReference.child(base).child(name).setValue(value);

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
                        //Log.d("json from FireBase", String.valueOf(listObject.get(i)));
                    }
                }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

     }

}

