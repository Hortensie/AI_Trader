package com.vaadin.polymer.demo.client.sampler.ai_trader;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by Piotr on 2017-01-10.
 * Calendar selection (Date, Time) so user from UI can select & get data
 * within specific historical date/time range
 */

class CalendarSelector implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    private final xApiUiInput context;
    private int fYear,fMonth,fDay;
    private int fHour,fMinute;
    private View view;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    CalendarSelector(Context context) {
        this.context = (xApiUiInput)context;

    }

    private Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

    void dateCalendarInitialization(View view){

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,CalendarSelector.this,year,month,day);
        datePickerDialog.show();
        setView(view);
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.fYear=year;
        this.fMonth=month;
        this.fDay=dayOfMonth;
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(context,CalendarSelector.this, hour, minute,true);
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        this.fHour=hourOfDay;
        this.fMinute=minute;
        setTimeInMillis();
    }

    private long returnTimeInMillis(View view)
    {
        TextView tv = (TextView) view;
        //Log.d("async, non UI","Rok"+fYear);
        //Log.d("async, non UI","Miesiac"+fMonth);
        //Log.d("async, non UI","Dzien"+fDay);
        //Log.d("async, non UI","Godzina"+fHour);
        //Log.d("async, non UI","Minuta"+fMinute);
        long selectedTime = calendar.getTimeInMillis();
        //Log.d("async, non UI","Milis"+selectedTime);
        tv.setText(String.valueOf(selectedTime));
        Toast toastLogged = Toast.makeText(context,"Calendar value was set", Toast.LENGTH_SHORT);
        toastLogged.show();
        return selectedTime;

    }

    private void setTimeInMillis()
    {
        calendar.set(fYear, fMonth, fDay,
                fHour, fMinute, 0);

        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
            returnTimeInMillis(getView());
            }
        });
    }




}
