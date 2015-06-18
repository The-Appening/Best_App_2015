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

    public String title;
    public int id = 0;
    public Date start;
    public Date end;
    public ParseObject ev;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
    }

    public Event(){}

    public Event(String title, int id, Date start, Date end) {
        this.title = title;
        this.id = id;
        this.start = start;
        this.end = end;
    }

    /// BEGIN OF SETTERS ///

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        for(int i=0 ; i < 100; i++){
            id = 0;
            id++;
        }

        this.id = id;
    }

    public void setStart(Date start) throws java.text.ParseException {
        this.start = start;
    }

    public void setEnd(Date end) throws java.text.ParseException {
        this.end = end;
    }

    /// END OF SETTERS //

    /// BEGIN OF GETTERS //

    public String getEventTitle() {

        EditText name = (EditText) findViewById(R.id.eventName);
        String title = name.getText().toString();

        return title;
    }

    public int getId() {
        id++;
        return id;
    }

    public Date getStart() throws java.text.ParseException {
        EditText startday = (EditText) findViewById(R.id.StartDay);
        String daystart = startday.getText().toString();

        EditText startmonth = (EditText) findViewById(R.id.StartMonth);
        String monthstart = startmonth.getText().toString();

        EditText startyear = (EditText) findViewById(R.id.StartYear);
        String yearstart = startyear.getText().toString();

        EditText starthour = (EditText) findViewById(R.id.StartHour);
        String hourstart = starthour.getText().toString();

        EditText startmin = (EditText) findViewById(R.id.StartMin);
        String minstart = startmin.getText().toString();

        String startseconds = "00";

        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("CEST"));
        Date start = df.parse(yearstart + "/" + monthstart + "/" + daystart + " " + hourstart + ":" + minstart + ":" + startseconds);

        return start;
    }

    public Date getEnd() throws java.text.ParseException {
        EditText endday = (EditText) findViewById(R.id.EndDay);
        String dayend = endday.getText().toString();

        EditText endmonth = (EditText) findViewById(R.id.EndMonth);
        String monthend = endmonth.getText().toString();

        EditText endyear = (EditText) findViewById(R.id.EndYear);
        String yearend = endyear.getText().toString();

        EditText endhour = (EditText) findViewById(R.id.EndHour);
        String hourend = endhour.getText().toString();

        EditText endmin = (EditText) findViewById(R.id.EndMin);
        String minend = endmin.getText().toString();

        String endseconds = "00";

        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("CEST"));
        Date end = df.parse(yearend + "/" + monthend + "/" + dayend + " " + hourend + ":" + minend + ":" + endseconds);

        return end;
    }

    /// END OF GETTERS ///


    /// METHOD OM EVENTS OP TE SLAAN IN PARSE.COM
    public void saveDate() throws ParseException, java.text.ParseException {
        try {
            ParseObject ev = new ParseObject("Event");
            ev.put("ID", getId());
            ev.put("Title", getEventTitle());
            ev.put("StartDate", getStart());
            ev.put("EndDate", getEnd());
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