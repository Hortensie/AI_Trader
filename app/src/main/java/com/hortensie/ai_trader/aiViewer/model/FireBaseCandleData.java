package com.hortensie.ai_trader.aiViewer.model;

import android.util.Log;

import com.github.mikephil.charting.data.CandleEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by szczesny on 2017-03-20.
 * This class retrieves List of Candle Chart data from fireBase database
 */

public class FireBaseCandleData implements FireBaseCandleDataInterface {

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    public FireBaseCandleData(DatabaseReference databaseReference, FirebaseDatabase firebaseDatabase) {
        this.databaseReference = databaseReference;
        this.firebaseDatabase = firebaseDatabase;
    }

    /*
    public FireBaseCandleData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    */

    @Override
    public Observable<List<CandleEntryRecord>> getCandleListFromFireBase(final String symbol, final String period) {
        return Observable.create(new ObservableOnSubscribe<List<CandleEntryRecord>>() {
            @Override
            public void subscribe(final ObservableEmitter<List<CandleEntryRecord>> e) throws Exception {
                databaseReference.child(symbol).child(period).addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<CandleEntryRecord> list = new LinkedList<>();
                        for (DataSnapshot postSnapshot:dataSnapshot.getChildren())
                        {
                            CandleEntryRecord record = postSnapshot.getValue(CandleEntryRecord.class);
                            list.add(record);
                        }
                        e.onNext(list);
                        e.onComplete();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
