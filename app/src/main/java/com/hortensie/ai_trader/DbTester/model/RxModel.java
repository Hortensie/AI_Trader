package com.hortensie.ai_trader.DbTester.model;

/**
 * Created by szczesny on 2017-02-09.
 * is a data access layer such as database API or remote server API.
 */

public class RxModel implements RxModelInterface {

    public String fetchData(String s) {
        return "RxJava Thread";
    }

    public String fetchData2(String s) {
        return s;
    }

}
