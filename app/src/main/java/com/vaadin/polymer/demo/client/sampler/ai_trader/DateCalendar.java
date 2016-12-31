package com.vaadin.polymer.demo.client.sampler.ai_trader;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.TimeZone;

class DateCalendar implements DatePickerDialog.OnDateSetListener {

    private final xApiTradingInput context;
    DateCalendar(Context context) {
        this.context = (xApiTradingInput)context;
    }
    static private Calendar objectCalendar;
    static private int dayFinal,monthFinal,yearFinal;

    static public Calendar getC() {
        return objectCalendar;
    }

    private void setC(Calendar c) {
        objectCalendar = c;
    }

    static int getDayFinal() {
        return dayFinal;
    }

    private void setDayFinal(int dayFinal) {
        DateCalendar.dayFinal = dayFinal;
}

    static int getMonthFinal() {
        return monthFinal;
    }

    private void setMonthFinal(int monthFinal) {
        DateCalendar.monthFinal = monthFinal;
    }

    static int getYearFinal() {
        return yearFinal;
    }

    private void setYearFinal(int yearFinal) {
        DateCalendar.yearFinal = yearFinal;
    }

    void initiateDatePicker(){
        objectCalendar = java.util.Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        int year=objectCalendar.get(java.util.Calendar.YEAR);
        int month=objectCalendar.get(java.util.Calendar.MONTH);
        int day = objectCalendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context,DateCalendar.this,year,month, day);
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        yearFinal=i;
        setYearFinal(yearFinal);
        monthFinal=i1+1;
        setMonthFinal(monthFinal);
        dayFinal=i2;
        setDayFinal(dayFinal);
        setC(objectCalendar);
        TimeCalendar contextTM = new TimeCalendar(context);
        contextTM.initiateTimePicker();
    }
}
