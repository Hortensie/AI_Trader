package com.hortensie.ai_trader.dbTester.view;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by szczesny on 2017-02-26.
 * Interface between DetailActivityPresenter -> DetailActivityView
 */

public interface DetailActivityViewInterface {

    void updateTitle(DataSnapshot input);

}
