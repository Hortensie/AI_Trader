import android.app.TimePickerDialog;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.hortensie.ai_trader.CalendarSelector;
import com.hortensie.ai_trader.xApiUiInput;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.mockito.Mockito.mock;

/**
 * Created by Piotr on 2017-01-26.
 * Unit test covering CalendarSelector.class
 */

public class CalendarSelectorTest {

    private int id;
    private CalendarSelector sut;
    private xApiUiInput context;
    private long startTime, endTime;
    @Before
    public void setUp(){
        context= mock(xApiUiInput.class);
        sut= new CalendarSelector(context);
    }


    @Test
    public void validateSetGetID(){
        sut.setId(24);
        Assert.assertEquals(24,sut.getId());
    }

    @Test
    public void validateSetGetEndTime(){
        CalendarSelector.setEndTime(12345678);
        Assert.assertEquals(12345678,CalendarSelector.getEndTime());
    }

    @Test
    public void validateSetGetStartTime(){
        CalendarSelector.setStartTime(12345678);
        Assert.assertEquals(12345678,CalendarSelector.getStartTime());
    }

    @Test
    public void validateDateCalendarInitialization()
    {
        sut.dateCalendarInitialization(4);
        Assert.assertEquals(4,sut.getId());
    }


    @Test
    public void validateOnDateSet(){
        DatePicker mockDatePicker = mock(DatePicker.class);
        Calendar mockCalendar = mock(Calendar.class);
        int year = 2016;
        int month = 2;
        int day = 13;
        TimePickerDialog timePickerDialog= new TimePickerDialog(context,sut,year,month,true);
        sut.onDateSet(mockDatePicker,year,month,day);
        Assert.assertEquals(null,timePickerDialog.getContext());
        int hour =5;
        Assert.assertEquals(5,hour);
        hour=mockCalendar.get(Calendar.HOUR_OF_DAY);
        Assert.assertEquals(0,hour);

    }

    @Test
    public void validateOnTimeSet(){
        TimePicker timePicker = mock(TimePicker.class);
        int hour = 2;
        int minute = 3;
        sut.onTimeSet(timePicker,hour,minute);

        //@Test (expected = IllegalArgumentException.class)
        //CalendarSelector mockSut = mock(CalendarSelector.class);
        //doThrow(new IllegalArgumentException("bad")).when(mockSut).setTimeInMillis();
        //mockSut.onTimeSet(timePicker,hour,minute);
    }

}
