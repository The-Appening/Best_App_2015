package nl.mad_world.chilltime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;

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


    public Event() {
    }

    public Event(String title, Date start, Date end) {
        this.title = title;
        this.start = start;
        this.end = end;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
    }

    public String getTitles() {
        /// HAALT DE WAARDE VAN DE INPUT VOOR DE TITEL
        EditText name = (EditText) findViewById(R.id.eventName);
        ParseUser currentUser = ParseUser.getCurrentUser();
        String username = currentUser.getUsername();

        String title = username + ": " + name.getText().toString();

        return title;
    }

    //Set a TimeChangedListener for TimePicker widget


    /// METHOD OM EVENTS OP TE SLAAN IN PARSE.COM
    public void saveDate() throws ParseException, java.text.ParseException {
        try {
            ///MAAKT VAN ALLE STRINGS EEN Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

            Intent groups = getIntent();
            String selGroup = groups.getExtras().getString("Groups");

            DatePicker start = (DatePicker) findViewById(R.id.datePickerStart);
            DatePicker end = (DatePicker) findViewById(R.id.datePickerEnd);
            final TimePicker starttime = (TimePicker) findViewById(R.id.timePickerStart);
            TimePicker endtime = (TimePicker) findViewById(R.id.timePickerEnd);

            starttime.setIs24HourView(true);
            endtime.setIs24HourView(true);

            int StartDay = start.getDayOfMonth();
            int StartMonth = start.getMonth();
            int StartYear = start.getYear()-1900;
            int StartHour = starttime.getCurrentHour()+2;
            int StartMin = starttime.getCurrentMinute();

            int EndDay = end.getDayOfMonth();
            int EndMonth = end.getMonth();
            int EndYear = end.getYear()-1900;
            int EndHour = endtime.getCurrentHour()+2;
            int EndMin = endtime.getCurrentMinute();


            Date Start = new Date(StartYear, StartMonth, StartDay, StartHour, StartMin);
            Date End = new Date(EndYear, EndMonth, EndDay, EndHour, EndMin );

            ParseObject ev = new ParseObject("Event");
            ev.put("Title", getTitles());
            ev.put("StartDate", Start);
            ev.put("EndDate", End);
            ev.put("Group", selGroup);
            ev.saveInBackground();

            Toast.makeText(getApplicationContext(),
                    "Afspraak aangemaakt!", Toast.LENGTH_LONG).show();

            Intent cal = new Intent(this, WeekViewer.class);
            cal.putExtra("selectedGroup", selGroup);
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