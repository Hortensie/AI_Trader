package com.hortensie.ai_trader.dbTester.model;

import com.google.firebase.database.DataSnapshot;
import com.hortensie.ai_trader.xAPI.ListSymbolRecord;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by szczesny on 2017-02-10.
 * Interface between Presenter --> Model
 */

public interface FireBaseModelInterface {

    //method that getData from some source and return Observable object which can be used by Presenter
    Observable<String> getData();
    //Flowable<List<ListSymbolRecord>> getListSymbolRecordFromFireBase(final String childName);
    Single<List<ListSymbolRecord>> getSymbolRecordListFromFireBase(final String childName);
}
