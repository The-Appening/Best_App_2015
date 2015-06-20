package nl.mad_world.chilltime;

/**
 * Created by Johan on 19-6-2015.
 */

import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


public class WeekViewer extends ActionBarActivity implements WeekView.MonthChangeListener,
        WeekView.EventClickListener, WeekView.EventLongPressListener {

    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_THREE_DAY_VIEW = 2;
    private static final int TYPE_WEEK_VIEW = 3;
    private int mWeekViewType = TYPE_THREE_DAY_VIEW;
    private WeekView mWeekView;
    private List<ParseObject> List;
    private List<WeekViewEvent> events;
    private String title;
    private int startday;
    private int starthour;
    private int startmin;
    private int startmonth;
    private int startyear;
    private int endday;
    private int endhour;
    private int endmin;
    private int endmonth;
    private int endyear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weekview);

        // Get a reference for the week view in the layout.
        mWeekView = (WeekView) findViewById(R.id.weekView);

        // Show a toast message about the touched event.
        mWeekView.setOnEventClickListener(this);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.setMonthChangeListener(this);

        // Set long press listener for events.
        mWeekView.setEventLongPressListener(this);

        // Set up a date time interpreter to interpret how the date and time will be formatted in
        // the week view. This is optional.
        setupDateTimeInterpreter(false);

        getData();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.week, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        setupDateTimeInterpreter(id == R.id.action_week_view);
        switch (id){
            case R.id.eventcreate:
            Intent intent = new Intent(this, Event.class);
                startActivity(intent);
            return true;
            case R.id.action_today:
                mWeekView.goToToday();
                return true;
            case R.id.action_day_view:
                if (mWeekViewType != TYPE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(1);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;
            case R.id.action_three_day_view:
                if (mWeekViewType != TYPE_THREE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_THREE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(3);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;
            case R.id.action_week_view:
                if (mWeekViewType != TYPE_WEEK_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_WEEK_VIEW;
                    mWeekView.setNumberOfVisibleDays(7);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Set up a date time interpreter which will show short date values when in week view and long
     * date values otherwise.
     * @param shortDate True if the date values should be short.
     */
    private void setupDateTimeInterpreter(final boolean shortDate) {
        mWeekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
                String weekday = weekdayNameFormat.format(date.getTime());
                weekdayNameFormat.setTimeZone(TimeZone.getTimeZone("CEST"));
                SimpleDateFormat format = new SimpleDateFormat("dd MMMM", Locale.getDefault());
                format.setTimeZone(TimeZone.getTimeZone("CEST"));

                // All android api level do not have a standard way of getting the first letter of
                // the week day name. Hence we get the first char programmatically.
                // Details: http://stackoverflow.com/questions/16959502/get-one-letter-abbreviation-of-week-day-of-a-date-in-java#answer-16959657

                return weekday + " " + format.format(date.getTime());
            }

            @Override
            public String interpretTime(int hour) {
                if (hour == 24) {
                    hour = 0;
                }
                return hour > 11 ? (hour + 1) + ":00 " : (hour == 0 ? "0:00" : hour + ":00 ");
            }
        });
    }

    public void getData(){

        //Get Event
        Calendar Start;
        Calendar End;
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Event");
        query.whereExists("StartHour");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> List, ParseException e) {
                try {
                    if (e == null) {

                        Log.d("Afspraak", "Opgehaald " + List.size() + " afspraken");

                        for (int i = 0; i < List.size(); i++) {
                            title = List.get(i).getString("Title");
                            startday = List.get(i).getInt("StartDay");
                            startmonth = List.get(i).getInt("StartMonth");
                            startyear = List.get(i).getInt("StartYear");
                            starthour = List.get(i).getInt("StartHour");
                            startmin = List.get(i).getInt("StartMin");
                            endday = List.get(i).getInt("EndDay");
                            endmonth = List.get(i).getInt("EndMonth");
                            endyear = List.get(i).getInt("EndYear");
                            endhour = List.get(i).getInt("EndHour");
                            endmin = List.get(i).getInt("EndMin");
                            }

                    } else {
                        Log.d("Afspraken", "Error: " + e.getMessage());
                    }
                } catch (Exception t) {
                    Toast.makeText(getApplicationContext(),
                            "Kan geen afspraken ophalen!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {

        // Populate the week view with some events.
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
        int id= 0;

        //Create Event
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, Event.StartHour);
        startTime.set(Calendar.MINUTE, Event.StartMin);
        startTime.set(Calendar.MONTH, Event.StartMonth-1);
        startTime.set(Calendar.YEAR, Event.StartYear);
        startTime.set(Calendar.DAY_OF_MONTH, Event.StartDay);
        Calendar endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, Event.EndHour);
        endTime.add(Calendar.MINUTE, Event.EndMin);
        endTime.set(Calendar.MONTH, Event.EndMonth-1);
        endTime.add(Calendar.DAY_OF_MONTH, Event.EndDay);
        endTime.add(Calendar.YEAR, Event.EndYear);

        WeekViewEvent event = new WeekViewEvent(id++, Event.title, startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_01));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, starthour);
        startTime.set(Calendar.MINUTE, startmin);
        startTime.set(Calendar.DAY_OF_MONTH, startday);
        startTime.set(Calendar.MONTH, startmonth-1);
        startTime.set(Calendar.YEAR, startyear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, endhour);
        endTime.set(Calendar.MINUTE, endmin);
        endTime.set(Calendar.DAY_OF_MONTH, endday);
        endTime.set(Calendar.MONTH, endmonth-1);
        endTime.set(Calendar.YEAR, endyear);

        event = new WeekViewEvent(id++, title, startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_02));
        events.add(event);

        for (int i = 0; i < events.size(); i++) {
            System.out.println(events.get(i).getName() + events.get(i).getStartTime() + events.get(i).getEndTime());
        }

        mWeekView.notifyDatasetChanged();
        return events;

    }

    private String getEventTitle(Calendar time) {
        return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH)+1, time.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(WeekViewer.this, "Clicked " + event.getName() + event.getId(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(WeekViewer.this, "Long pressed event: " + event.getName(), Toast.LENGTH_SHORT).show();
    }

    /// Intent om naar Event te gaan.
    public void createEvent(View v) {
        Intent create = new Intent(this, Event.class);
        startActivity(create);
    }
}