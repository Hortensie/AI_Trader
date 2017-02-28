package com.hortensie.ai_trader.dbTester.model;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

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
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    @Override
    public Flowable<DataSnapshot> getDataFromFireBase(final String childName) {
        return Flowable.create(new FlowableOnSubscribe<DataSnapshot>() {
            @Override
            public void subscribe(final FlowableEmitter<DataSnapshot> e) throws Exception {
                databaseReference.child(childName).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        /*
                        for (DataSnapshot snapshot:dataSnapshot.getChildren())
                        {
                            Log.d("RxJava Model",snapshot.toString());
                            e.onNext(snapshot);
                        }
                        /*/
                        //Log.d("RxJava Model",dataSnapshot.toString());
                        e.onNext(dataSnapshot);
                        e.onComplete();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }, BackpressureStrategy.BUFFER);

    }

}

