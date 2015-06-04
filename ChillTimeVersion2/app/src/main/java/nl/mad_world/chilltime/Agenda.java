package nl.mad_world.chilltime;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;

import com.tyczj.extendedcalendarview.CalendarProvider;
import com.tyczj.extendedcalendarview.Event;
import com.tyczj.extendedcalendarview.ExtendedCalendarView;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;


public class Agenda extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        ExtendedCalendarView calendar = (ExtendedCalendarView)findViewById(R.id.calendar);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_agenda, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void createEvent(){

        ContentValues values = new ContentValues();
        values.put(CalendarProvider.COLOR, Event.COLOR_RED);
        values.put(CalendarProvider.DESCRIPTION, "Some Description");
        values.put(CalendarProvider.LOCATION, "Some location");
                values.put(CalendarProvider.EVENT, "Event name");

                        Calendar cal = Calendar.getInstance();


            int startDayYear = 2015;
            int startDayMonth = 6;
            int startDayDay = 5;
            int startTimeHour = 12;
            int startTimeMin = 00;
            int endDayYear = 2015;
            int endDayMonth = 6;
            int endDayDay = 6;
            int endTimeHour = 12;
            int endTimeMin = 00;
            int julianDay = 0;


        cal.set(startDayYear, startDayMonth, startDayDay, startTimeHour, startTimeMin);
        values.put(CalendarProvider.START, cal.getTimeInMillis());
        values.put(CalendarProvider.START_DAY, julianDay);
        TimeZone tz = TimeZone.getDefault();

        cal.set(endDayYear, endDayMonth, endDayDay, endTimeHour, endTimeMin);
        int endDayJulian = Time.getJulianDay(cal.getTimeInMillis(), TimeUnit.MILLISECONDS.toSeconds(tz.getOffset(cal.getTimeInMillis())));

        values.put(CalendarProvider.END, cal.getTimeInMillis());
        values.put(CalendarProvider.END_DAY, endDayJulian);

        Uri uri = getContentResolver().insert(CalendarProvider.CONTENT_URI, values);
    }



}
