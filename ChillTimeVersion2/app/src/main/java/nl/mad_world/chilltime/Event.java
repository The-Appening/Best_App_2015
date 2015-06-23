package nl.mad_world.chilltime;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Johan on 12-6-2015.
 */
public class Event extends Activity {
    public String title;
    public Date start;
    public Date end;
    public ParseObject ev;

    public Event(){}

        public Event(String title,Date start, Date end) {
            this.title = title;
            this.start = start;
            this.end = end;
        }


    public String getTitles() {
        EditText name = (EditText) findViewById(R.id.eventName);
        String title = name.getText().toString();

        return title;
    }

    public Date getStart() throws java.text.ParseException {
        EditText startday = (EditText) findViewById(R.id.StartDay);
        int startDay = Integer.parseInt(startday.getText().toString());
        String daystart = startday.getText().toString();

        EditText startmonth = (EditText) findViewById(R.id.StartMonth);
        int startMonth = Integer.parseInt(startmonth.getText().toString());
        String monthstart = startmonth.getText().toString();

        EditText startyear = (EditText) findViewById(R.id.StartYear);
        int startYear = Integer.parseInt(startyear.getText().toString());
        String yearstart = startyear.getText().toString();

        EditText starthour = (EditText) findViewById(R.id.StartHour);
        int startHour = Integer.parseInt(starthour.getText().toString());
        String hourstart = starthour.getText().toString();

        EditText startmin = (EditText) findViewById(R.id.StartMin);
        int startMin = Integer.parseInt(startmin.getText().toString());
        String minstart = startmin.getText().toString();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        df.setTimeZone(TimeZone.getTimeZone("CEST"));

        Date startDate = df.parse(yearstart + "-" + monthstart + "-" + daystart  + " " +  hourstart  + ":" + minstart);


        start = startDate;

        return start;
    }



    public Date getEnd() throws java.text.ParseException {
        EditText endday = (EditText) findViewById(R.id.EndDay);
        int endDay = Integer.parseInt(endday.getText().toString());
        String dayend = endday.getText().toString();

        EditText endmonth = (EditText) findViewById(R.id.EndMonth);
        int endMonth = Integer.parseInt(endmonth.getText().toString());
        String monthend = endmonth.getText().toString();

        EditText endyear = (EditText) findViewById(R.id.EndYear);
        int endYear = Integer.parseInt(endyear.getText().toString());
        String yearend = endyear.getText().toString();

        EditText endhour = (EditText) findViewById(R.id.EndHour);
        String hourend = endhour.getText().toString();

        EditText endmin = (EditText) findViewById(R.id.EndMin);
        String minend = endmin.getText().toString();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        df.setTimeZone(TimeZone.getTimeZone("CEST"));

        Date endDate = df.parse(yearend + "-" + monthend + "-" + dayend + " " + hourend + ":" + minend);

        end = endDate;

        return end;
    }

    /// END OF GETTERS ///


    /// METHOD OM EVENTS OP TE SLAAN IN PARSE.COM
    public void saveDate() throws ParseException, java.text.ParseException {
        try {
            ParseObject ev = new ParseObject("Event");
            ev.put("Title", getTitles());
            ev.put("StartDate", getStart());
            ev.put("EndDate", getEnd());
            ev.saveInBackground();

            Toast.makeText(getApplicationContext(),
                    "Afspraak aangemaakt!", Toast.LENGTH_LONG).show();

            Intent cal = new Intent(this, WeekViewer.class);
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