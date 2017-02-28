package com.hortensie.ai_trader.dbTester.model;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.hortensie.ai_trader.xAPI.ListSymbolRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by szczesny on 2017-02-10.
 * Interface between Presenter --> Model
 */

public interface FireBaseModelInterface {

    //method that getData from some source and return Observable object which can be used by Presenter
    Observable<String> getData();
    Flowable<DataSnapshot> getDataFromFireBase(final String childName);

}
