package com.hortensie.ai_trader.dbTester.model;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * Created by szczesny on 2017-02-09.
 * Data access layer such as database API or remote server API.
 */

public class FireBaseModel implements FireBaseModelInterface {

    DatabaseReference databaseReference;

    public FireBaseModel() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
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
    public Observable<List<String>> getDataFromFireBase(final String childName){

        return Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(final ObservableEmitter<List<String>> e) throws Exception {

                try {
                    databaseReference.child(childName).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            List<String> listObject = (List<String>) dataSnapshot.getValue();
                            e.onNext(listObject);
                            e.onComplete(); // Nothing more to emit
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });

                }
                catch (Exception e1)
                {
                    e1.printStackTrace();
                }
        }
    });
}

}

