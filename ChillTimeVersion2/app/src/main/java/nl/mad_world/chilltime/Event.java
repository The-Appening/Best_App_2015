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
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Johan on 12-6-2015.
 */
public class Event extends Activity {

    public static String Title;
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
    public Date start;
    public Date end;
    public ParseObject ev;
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

    public Event() {
    }
    /// END OF CONSTRUCTOR ///

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
    }


    public String getTitles() {
        return Title;
    }

    public void setTitles() {
        EditText name = (EditText) findViewById(R.id.eventName);

        this.Title = name.getText().toString();
    }

    public void setStart() throws java.text.ParseException {
        EditText startday = (EditText) findViewById(R.id.StartDay);
        String daystart = startday.getText().toString();
        StartDay = Integer.parseInt(daystart);

        EditText startmonth = (EditText) findViewById(R.id.StartMonth);
        String monthstart = startmonth.getText().toString();
        StartMonth = Integer.parseInt(monthstart);

        EditText startyear = (EditText) findViewById(R.id.StartYear);
        String yearstart = startyear.getText().toString();
        StartYear = Integer.parseInt(yearstart);

        EditText starthour = (EditText) findViewById(R.id.StartHour);
        String hourstart = starthour.getText().toString();
        StartHour = Integer.parseInt(hourstart);

        EditText startmin = (EditText) findViewById(R.id.StartMin);
        String minstart = startmin.getText().toString();
        StartMin = Integer.parseInt(minstart);


        df.setTimeZone(TimeZone.getTimeZone("CEST"));
        start = df.parse(yearstart + "-" + monthstart + "-" + daystart + " " + hourstart + ":" + minstart);


    }

    public Date getStart(){ return start;}

    public void setEnd() throws java.text.ParseException {
        EditText endday = (EditText) findViewById(R.id.EndDay);
        String dayend = endday.getText().toString();
        EndDay = Integer.parseInt(dayend);

        EditText endmonth = (EditText) findViewById(R.id.EndMonth);
        String monthend = endmonth.getText().toString();
        EndMonth = Integer.parseInt(monthend);

        EditText endyear = (EditText) findViewById(R.id.EndYear);
        String yearend = endyear.getText().toString();
        EndYear = Integer.parseInt(yearend);

        EditText endhour = (EditText) findViewById(R.id.EndHour);
        String hourend = endhour.getText().toString();
        EndHour = Integer.parseInt(hourend);

        EditText endmin = (EditText) findViewById(R.id.EndMin);
        String minend = endmin.getText().toString();
        EndMin = Integer.parseInt(minend);

        df.setTimeZone(TimeZone.getTimeZone("CEST"));
        end = df.parse(yearend + "-" + monthend + "-" + dayend + " " + hourend + ":" + minend);
    }

    public Date getEnd(){ return end;}


    /// METHOD OM EVENTS OP TE SLAAN IN PARSE.COM
    public void saveDate() throws ParseException, java.text.ParseException {
        try {
            ParseObject ev = new ParseObject("Event");
            ev.put("Title", getTitles());
            ev.put("StartDate", getStart());
            ev.put("EndDate", getEnd());
            ev.put("Group", "poeppeop");

            ev.saveInBackground();

            Toast.makeText(getApplicationContext(),
                    "Afspraak aangemaakt!", Toast.LENGTH_LONG).show();

            Intent cal = new Intent(this, WeekViewer.class);
            startActivity(cal);

        } catch (android.net.ParseException e) {
            e.printStackTrace();
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),
                    "Het is niet gelukt om een afspraak toe te voegen!", Toast.LENGTH_LONG).show();
        }

    }

    public void goSave(View v) throws ParseException, java.text.ParseException {
        saveDate();
    }

}