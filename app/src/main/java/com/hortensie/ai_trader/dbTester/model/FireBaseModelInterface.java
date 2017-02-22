package com.hortensie.ai_trader.dbTester.model;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by szczesny on 2017-02-10.
 * Interface between Presenter --> Model
 */

public interface FireBaseModelInterface {

    //method that getData from some source and return Observable object which can be used by Presenter
    Observable<String> getData();
    Observable<List<String>> getDataFromFireBase(final String childName);
}
