package nl.mad_world.chilltime;


import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class maakEvent extends ActionBarActivity implements WeekView.MonthChangeListener,
        WeekView.EventClickListener, WeekView.EventLongPressListener {

    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_THREE_DAY_VIEW = 2;
    private static final int TYPE_WEEK_VIEW = 3;
    private int mWeekViewType = TYPE_THREE_DAY_VIEW;
    private WeekView mWeekView;

    //Variables to create an event//
    public static String name = "";
    public static String name2 = "";
    public static int bhour;
    public static int bmin;
    public static int bday;
    public static int bmonth;
    public static int byear;
    public static int ehour;
    public static int emin;
    public static int eday;
    public static int emonth;
    public static int eyear;
    public static int bhour_2;
    public static int bmin_2;
    public static int bday_2;
    public static int bmonth_2;
    public static int byear_2;
    public static int ehour_2;
    public static int emin_2;
    public static int eday_2;
    public static int emonth_2;
    public static int eyear_2;


    // BEGIN OF GETTERS //

    public String getName() { return name;}

    public String getName2() {return name2;}

    public int getBhour() { return bhour;}

    public int getBmin() {return bmin;}

    public int getBday() {return bday;}

    public int getBmonth() {return bmonth;}

    public int getEhour() {
        return ehour;
    }

    public int getByear() {return byear;}

    public int getEmin() {
        return emin;
    }

    public int getEday() {return eday;}

    public int getEmonth() {
        return emonth;
    }

    public int getEyear() { return eyear;}

    public int getBhour_2() {
        return bhour_2;
    }

    public int getBmin_2() {return bmin_2;}

    public int getBday_2() {
        return bday_2;
    }

    public int getBmonth_2() {return bmonth_2;}

    public int getByear_2() {
        return byear_2;
    }

    public int getEhour_2() {return ehour_2;}

    public int getEmin_2() {
        return emin_2;
    }

    public int getEday_2() {return eday_2;}

    public int getEmonth_2() {
        return emonth_2;
    }

    public int getEyear_2() {return eyear_2;}

    // END OF GETTERS //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        //Back button Title Bar//
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        setupDateTimeInterpreter(id == R.id.action_week_view);
        switch (id) {
            case android.R.id.home:
                finish();
                return true;

            case R.id.action_today:
                if (mWeekViewType != TYPE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(1);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 14, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, getResources().getDisplayMetrics()));
                }
                return true;

            case R.id.addEvent:
                Intent add = new Intent(this, maakEvent.class);

                startActivity(add);

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
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
                String weekday = weekdayNameFormat.format(date.getTime());
                SimpleDateFormat format = new SimpleDateFormat(" d/M", Locale.getDefault());

                // All android api level do not have a standard way of getting the first letter of
                // the week day name. Hence we get the first char programmatically.
                // Details: http://stackoverflow.com/questions/16959502/get-one-letter-abbreviation-of-week-day-of-a-date-in-java#answer-16959657
                if (shortDate)
                    weekday = String.valueOf(weekday.charAt(0));
                return weekday.toUpperCase() + format.format(date.getTime());
            }


           @Override
            public String interpretTime(int hour) {

                return hour > 11 ? (hour + 1) + ":00": hour == 24 ? (hour - 24)+ ":00": (hour == 0 ? "0:00" : hour + ":00");
            }
        });
    }


    @Override
    public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {

        // Populate the week view with some events.
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();



        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, bhour);
        startTime.set(Calendar.MINUTE, bmin);
        startTime.set(Calendar.DAY_OF_MONTH, bday);
        startTime.set(Calendar.MONTH, bmonth-1);
        startTime.set(Calendar.YEAR, byear);
        Calendar endTime = (Calendar) startTime.clone();
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, ehour);
        startTime.set(Calendar.DAY_OF_MONTH, eday);
        endTime.set(Calendar.MINUTE, emin);
        endTime.set(Calendar.MONTH, emonth - 1);
        endTime.set(Calendar.YEAR, eyear);

        WeekViewEvent event = new WeekViewEvent(1, name, startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_01));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, bhour_2);
        startTime.set(Calendar.MINUTE, bmin_2);
        startTime.set(Calendar.DAY_OF_MONTH, bday_2);
        startTime.set(Calendar.MONTH, bmonth_2 - 1);
        startTime.set(Calendar.YEAR, byear_2);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, ehour_2);
        endTime.set(Calendar.MINUTE, emin_2);
        endTime.set(Calendar.DAY_OF_MONTH, eday_2);
        endTime.set(Calendar.MONTH, emonth_2 - 1);
        endTime.set(Calendar.YEAR, eyear_2);
        event = new WeekViewEvent(2, name2, startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_02));
        events.add(event);


        return events;
    }

    private String getEventTitle(Calendar time) {
        return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH)+1, time.get(Calendar.DAY_OF_MONTH));
    }


    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Intent newEvent = new Intent(this, Agenda.class);

        startActivity(newEvent);
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(maakEvent.this, "Long pressed event: " + event.getName(), Toast.LENGTH_SHORT).show();
    }

}


