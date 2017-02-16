package com.hortensie.ai_trader.xAPI;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by Piotr on 2017-01-10.
 * Calendar selection (Date, Time) so user from UI can select & get data
 * within specific historical date/time range
 */

public class CalendarSelector implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    private final xApiUiInput context;
    private int fHour,fMinute,fYear,fMonth,fDay,id;
    private static long startTime,endTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static long getStartTime() {
        return startTime;
    }

    public static void setStartTime(long startTime) {
        CalendarSelector.startTime = startTime;
    }

    public static long getEndTime() {
        return endTime;
    }

    public static void setEndTime(long endTime) {
        CalendarSelector.endTime = endTime;
    }

    public CalendarSelector(Context context) {
        this.context = (xApiUiInput)context;

    }

    private Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

    public void dateCalendarInitialization(int id){

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,CalendarSelector.this,year,month,day);
        datePickerDialog.show();
        setId(id);
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.fYear=year;
        this.fMonth=month;
        this.fDay=dayOfMonth;
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        //once date is set time picker pops up
        TimePickerDialog timePickerDialog = new TimePickerDialog(context,CalendarSelector.this, hour, minute,true);
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        this.fHour=hourOfDay;
        this.fMinute=minute;
        //once time is set call function that convert data into time in millis (required by xStore.API)
        setTimeInMillis();
    }

    //method returns time based on id (start time = 1 , end time = 2)
    private long returnTimeInMillis(int id)
    {
        long selectedTime = calendar.getTimeInMillis();
        if(id == 1)
        {
            setStartTime(selectedTime);
        }
        else
        {
            setEndTime(selectedTime);
        }
        Toast toastLogged = Toast.makeText(context,"Calendar value was set", Toast.LENGTH_SHORT);
        toastLogged.show();
        return selectedTime;

    }

    private void setTimeInMillis()
    {
        calendar.set(fYear, fMonth, fDay,
                fHour, fMinute, 0);

        //once time is set sent value back to Ui thread
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
            returnTimeInMillis(getId());
            }
        });
    }




}
