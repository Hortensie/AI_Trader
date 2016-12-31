package com.vaadin.polymer.demo.client.sampler.ai_trader;

import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.TimePicker;


import java.util.Calendar;

class TimeCalendar implements TimePickerDialog.OnTimeSetListener {

    private final xApiTradingInput context;

    TimeCalendar(Context context) {
        this.context = (xApiTradingInput)context;
    }
    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        setTimeInMiliseconds(DateCalendar.getC(),DateCalendar.getYearFinal(),DateCalendar.getMonthFinal(),DateCalendar.getDayFinal(), i, i1);
    }

    void initiateTimePicker() {

        int hour=DateCalendar.getC().get(Calendar.HOUR_OF_DAY);
        int minute=DateCalendar.getC().get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(context,TimeCalendar.this,hour,minute,true);
        timePickerDialog.show();
    }

    private void setTimeInMiliseconds (Calendar cal, int fyear,
                              int fmonth,
                              int fday,
                              int fhourOfDay,
                              int fminute)

    {
        cal.set(fyear, fmonth, fday,
                fhourOfDay, fminute, 0);
        final long startTime = cal.getTimeInMillis();

        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                context.startDate.setText(String.valueOf(startTime));
            }
        });
    }
}