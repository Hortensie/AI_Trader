package com.hortensie.ai_trader.dbTester.model;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hortensie.ai_trader.xAPI.ListSymbolRecord;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * New work started (29.12.2017)
 * Created by szczesny on 2017-02-09.
 * Data access layer such as database API or remote server API.
 */

public class FireBaseModel implements FireBaseModelInterface {


    //private native String calculateArea(double radius);
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    public FireBaseModel(DatabaseReference databaseReference, FirebaseDatabase firebaseDatabase) {
        this.databaseReference = databaseReference;
        this.firebaseDatabase = firebaseDatabase;
    }

    @Override
    public Observable<String> getData() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                try {
                    //e.onNext(calculateArea(5.5f)); // Emit the contents of the URL
                    e.onNext("Home");
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

