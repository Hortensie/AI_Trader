package com.hortensie.ai_trader.DbTester.model;

/**
 * Created by szczesny on 2017-02-09.
 * is a data access layer such as database API or remote server API.
 */

public class RxModel {

    private String fetchData(String s) {
        return "RxJava Thread";
    }

    private String fetchData2(String s) {
        return s;
    }

}
