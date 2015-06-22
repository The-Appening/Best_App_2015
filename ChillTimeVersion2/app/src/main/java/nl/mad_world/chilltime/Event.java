package nl.mad_world.chilltime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Johan on 12-6-2015.
 */
    public class Event extends Activity {

    public static String Title;
    public ParseObject ev;
    public static int StartDay;
    public static int StartMonth;
    public static int StartYear;
    public static int StartHour;
    public static int StartMin;
    public static int EndDay;
    public static int EndMonth;
    public static int EndYear;
    public static int EndHour;
    public static int EndMin;
    public DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");


    /// BEGIN OF CONSTRUCTOR ////
    public Event(String title, int startDay, int startMonth, int startYear, int startHour, int startMin, int endDay, int endMonth, int endYear, int endHour, int endMin) {
        Title = title;
        StartDay = startDay;
        StartMonth = startMonth;
        StartYear = startYear;
        StartHour = startHour;
        StartMin = startMin;
        EndDay = endDay;
        EndMonth = endMonth;
        EndYear = endYear;
        EndHour = endHour;
        EndMin = endMin;
    }

    public Event(){}
    /// END OF CONSTRUCTOR ///


    /// BEGIN OF GETTERS ///
    public String getTitler() {
        return Title;
    }

    public void setTitler() {
        EditText name = (EditText) findViewById(R.id.eventName);
        String title = name.getText().toString();

        this.Title = title;
    }

    public void setStartDay() {
        EditText startday = (EditText) findViewById(R.id.StartDay);
        String daystart = startday.getText().toString();
        StartDay = Integer.parseInt(daystart);

        this.StartDay = StartDay;
    }

    public int getStartDay() {
        return StartDay;
    }

    public void setStartMonth() {
        EditText startmonth = (EditText) findViewById(R.id.StartMonth);
        String monthstart = startmonth.getText().toString();
        StartMonth = Integer.parseInt(monthstart);

        this.StartMonth = StartMonth-1;
    }

    public int getStartMonth() {
        return StartMonth;
    }

    public void setStartYear() {
        EditText startyear = (EditText) findViewById(R.id.StartYear);
        String yearstart = startyear.getText().toString();
        StartYear = Integer.parseInt(yearstart);

        this.StartYear = StartYear;
    }

    public int getStartYear() {
        return StartYear;
    }

    public void setStartHour() {
        EditText starthour = (EditText) findViewById(R.id.StartHour);
        String hourstart = starthour.getText().toString();
        StartHour = Integer.parseInt(hourstart);

        this.StartHour = StartHour;
    }

    public int getStartHour() {
        return StartHour;
    }

    public void setStartMin() {
        EditText startmin = (EditText) findViewById(R.id.StartMin);
        String minstart = startmin.getText().toString();
        StartMin = Integer.parseInt(minstart);

        this.StartMin = StartMin;
    }

    public int getStartMin() {
        return StartMin;
    }

    public void setEndDay() {
        EditText endday = (EditText) findViewById(R.id.EndDay);
        String dayend = endday.getText().toString();
        EndDay = Integer.parseInt(dayend);

        this.EndDay = EndDay;
    }

    public int getEndDay() {
        return EndDay;
    }

    public void setEndMonth() {
        EditText endmonth = (EditText) findViewById(R.id.EndMonth);
        String monthend = endmonth.getText().toString();
        EndMonth = Integer.parseInt(monthend);

        this.EndMonth = EndMonth-1;
    }

    public int getEndMonth() {
        return EndMonth;
    }

    public void setEndYear() {
        EditText endyear = (EditText) findViewById(R.id.EndYear);
        String yearend = endyear.getText().toString();
        EndYear = Integer.parseInt(yearend);

        this.EndYear = EndYear;
    }

    public int getEndYear() {
        return EndYear;
    }

    public void setEndHour() {
        EditText endhour = (EditText) findViewById(R.id.EndHour);
        String hourend = endhour.getText().toString();
        EndHour = Integer.parseInt(hourend);

        this.EndHour = EndHour;
    }

    public int getEndHour() {
        return EndHour;
    }

    public void setEndMin() {
        EditText endmin = (EditText) findViewById(R.id.EndMin);
        String minend = endmin.getText().toString();
        EndMin = Integer.parseInt(minend);

        this.EndMin = EndMin;
    }

    public int getEndMin() {
        return EndMin;
    }

    /// END OF GETTERS ///
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
    }


    /// METHOD OM EVENTS OP TE SLAAN IN PARSE.COM
    public void saveDate() throws ParseException, java.text.ParseException {
        try {
            ParseObject ev = new ParseObject("Event");
            ev.put("Title", getTitler());
            ev.put("StartDay", getStartDay());
            ev.put("StartMonth", getStartMonth());
            ev.put("StartYear", getStartYear());
            ev.put("StartHour", getStartHour());
            ev.put("StartDMin", getStartMin());
            ev.put("EndDay", getEndDay());
            ev.put("EndDMonth", getEndMonth());
            ev.put("EndYear", getEndYear());
            ev.put("EndHour", getEndHour());
            ev.put("EndDMin", getEndMin());
            ev.saveInBackground();

            Toast.makeText(getApplicationContext(),
            "Afspraak aangemaakt!", Toast.LENGTH_LONG).show();

            Intent cal = new Intent(this, CaldroidActivity.class);
            startActivity(cal);

        }   catch(android.net.ParseException e){
            e.printStackTrace();
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),
                    "Het is niet gelukt om een afspraak toe te voegen!", Toast.LENGTH_LONG).show();
        }

    }

    public void goSave(View v) throws ParseException, java.text.ParseException {saveDate();}

}