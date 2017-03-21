package com.hortensie.ai_trader.dbTester.model;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hortensie.ai_trader.xAPI.ListSymbolRecord;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.BackpressureStrategy;

import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

/**
 * Created by szczesny on 2017-02-09.
 * Data access layer such as database API or remote server API.
 */

public class FireBaseModel implements FireBaseModelInterface {

    private DatabaseReference databaseReference;

    public FireBaseModel() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public Observable<String> getData() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                try {
                    e.onNext("Home sweet home"); // Emit the contents of the URL
                    e.onComplete(); // Nothing more to emit
                }
                catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    /*
    @Override
    public Flowable <List<ListSymbolRecord>> getListSymbolRecordFromFireBase(final String childName){
        return Flowable.create(new FlowableOnSubscribe<List<ListSymbolRecord>>() {
            @Override
            public void subscribe(final FlowableEmitter<List<ListSymbolRecord>> e) throws Exception {
                databaseReference.child(childName).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<ListSymbolRecord> list = new LinkedList<>();
                        for (DataSnapshot postSnapshot:dataSnapshot.getChildren())
                        {
                            ListSymbolRecord record = postSnapshot.getValue(ListSymbolRecord.class);
                            Log.d("Rxjava", record.getDescription());
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
        }, BackpressureStrategy.BUFFER);
    }
    */

    @Override
    public Observable<List<ListSymbolRecord>> getSymbolListFromFireBase(final String childName) {
        return Observable.create(new ObservableOnSubscribe<List<ListSymbolRecord>>() {
            @Override
            public void subscribe(final ObservableEmitter<List<ListSymbolRecord>> e) throws Exception {
                databaseReference.child(childName).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<ListSymbolRecord> list = new LinkedList<>();

                        for (DataSnapshot postSnapshot:dataSnapshot.getChildren())
                        {
                            ListSymbolRecord record = postSnapshot.getValue(ListSymbolRecord.class);
                            //Log.d("RxJava model", record.getDescription());
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

