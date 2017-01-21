package com.vaadin.polymer.demo.client.sampler.ai_trader;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import pro.xstore.api.message.records.RateInfoRecord;

/**
 * Created by Piotr on 2016-12-02.
 * Provides interface to FireBase database by:
 * Saving data
 * Getting data as List<Object> based on various child String
 */

public class FireBaseDb
{
    Symbol newSymbol;
    SymbolRecord symbolRecord;

    private static List<String> internalCopy= new ArrayList<>();

    static List<String> getInternalCopy() {

        return internalCopy;
    }

    private DatabaseReference databaseReference;

    FireBaseDb() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
    }

    public static String EncodeString(String string) {

        return string.replace(".", ",").replace("[","").replace("]","");
    }


    public static String DecodeString(String string) {
        return string.replace(",", ".");
    }


    void saveListToFireBaseDataBase (List<RateInfoRecord> records, String base, Context context)
    {
        if(context!=null)
        {
            for (int i = 0; i < records.size(); i++)
            {
                symbolRecord=new SymbolRecord();
                symbolRecord.setCtm(records.get(i).getCtm());
                symbolRecord.setClose(records.get(i).getClose());
                symbolRecord.setLow(records.get(i).getLow());
                symbolRecord.setHigh(records.get(i).getHigh());
                symbolRecord.setOpen(records.get(i).getOpen());
                symbolRecord.setVol(records.get(i).getVol());
                newSymbol = new Symbol();
                newSymbol.setTime(records.get(i).getCtm());
                newSymbol.setSymbolRecord(symbolRecord);

                databaseReference.child(FireBaseDb.EncodeString(base)).child(String.valueOf(records.get(i).getCtm())).setValue(newSymbol);
            }
        }
        else
        {
            Toast toastLogged = Toast.makeText(context,"No context", Toast.LENGTH_SHORT);
            toastLogged.show();
        }
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

