package com.vaadin.polymer.demo.client.sampler.ai_trader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import pro.xstore.api.message.records.RateInfoRecord;

/**
 * Created by Piotr on 2016-12-02.
 * Provides interface to FireBase database by:
 * Saving data
 * Getting data as List<Object> based on various child String
 */

public class FireBaseHandler
{

    private static List<String> internalCopy= new ArrayList<>();

    static List<String> getInternalCopy() {

        return internalCopy;
    }

    private DatabaseReference databaseReference;

    FireBaseHandler() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
    }

    public static String EncodeString(String string) {

        return string.replace(".", ",").replace("[","+").replace("]","-");
    }


    public static String DecodeString(String string) {

        return string.replace(",", ".").replace("+","[").replace("-","]");
    }


    List<Symbol> saveApiRecordsToSymbolList (List<RateInfoRecord> records)
    {
            List<Symbol> data = new LinkedList<>();
            for (int i = 0; i < records.size(); i++)
            {
                SymbolRecord symbolRecord = new SymbolRecord();
                symbolRecord.setCtm(records.get(i).getCtm());
                symbolRecord.setClose(records.get(i).getClose());
                symbolRecord.setLow(records.get(i).getLow());
                symbolRecord.setHigh(records.get(i).getHigh());
                symbolRecord.setOpen(records.get(i).getOpen());
                symbolRecord.setVol(records.get(i).getVol());
                Symbol newSymbol = new Symbol();
                newSymbol.setTime(records.get(i).getCtm());
                newSymbol.setSymbolRecord(symbolRecord);
                data.add(i,newSymbol);
            }
        return data;
    }

    void saveSymbolListToFireBase(List<Symbol> data, String symbol, String periodCode) {

        for (int i = 0; i < data.size(); i++) {
            databaseReference.child(FireBaseHandler.EncodeString(symbol)).child(FireBaseHandler.EncodeString(periodCode)).setValue(data);
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

