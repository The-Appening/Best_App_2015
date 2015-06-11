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

    private String name = "";
    private String name2 = "";
    private int bhour;
    private int bmin;
    private int bday;
    private int bmonth;
    private int byear;
    private int ehour;
    private int emin;
    private int eday;
    private int emonth;
    private int eyear;
    private int bhour_2;
    private int bmin_2;
    private int bday_2;
    private int bmonth_2;
    private int byear_2;
    private int ehour_2;
    private int emin_2;
    private int eday_2;
    private int emonth_2;
    private int eyear_2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public int getBhour() {
        return bhour;
    }

    public void setBhour(int bhour) {
        this.bhour = bhour;
    }

    public int getBmin() {
        return bmin;
    }

    public void setBmin(int bmin) {
        this.bmin = bmin;
    }

    public int getBday() {
        return bday;
    }

    public void setBday(int bday) {
        this.bday = bday;
    }

    public int getBmonth() {
        return bmonth;
    }

    public void setBmonth(int bmonth) {
        this.bmonth = bmonth;
    }

    public int getEhour() {
        return ehour;
    }

    public void setEhour(int ehour) {
        this.ehour = ehour;
    }

    public int getByear() {
        return byear;
    }

    public void setByear(int byear) {
        this.byear = byear;
    }

    public int getEmin() {
        return emin;
    }

    public void setEmin(int emin) {
        this.emin = emin;
    }

    public int getEday() {
        return eday;
    }

    public void setEday(int eday) {
        this.eday = eday;
    }

    public int getEmonth() {
        return emonth;
    }

    public void setEmonth(int emonth) {
        this.emonth = emonth;
    }

    public int getEyear() {
        return eyear;
    }

    public void setEyear(int eyear) {
        this.eyear = eyear;
    }

    public int getBhour_2() {
        return bhour_2;
    }

    public void setBhour_2(int bhour_2) {
        this.bhour_2 = bhour_2;
    }

    public int getBmin_2() {
        return bmin_2;
    }

    public void setBmin_2(int bmin_2) {
        this.bmin_2 = bmin_2;
    }

    public int getBday_2() {
        return bday_2;
    }

    public void setBday_2(int bday_2) {
        this.bday_2 = bday_2;
    }

    public int getBmonth_2() {
        return bmonth_2;
    }

    public void setBmonth_2(int bmonth_2) {
        this.bmonth_2 = bmonth_2;
    }

    public int getByear_2() {
        return byear_2;
    }

    public void setByear_2(int byear_2) {
        this.byear_2 = byear_2;
    }

    public int getEhour_2() {
        return ehour_2;
    }

    public void setEhour_2(int ehour_2) {
        this.ehour_2 = ehour_2;
    }

    public int getEmin_2() {
        return emin_2;
    }

    public void setEmin_2(int emin_2) {
        this.emin_2 = emin_2;
    }

    public int getEday_2() {
        return eday_2;
    }

    public void setEday_2(int eday_2) {
        this.eday_2 = eday_2;
    }

    public int getEmonth_2() {
        return emonth_2;
    }

    public void setEmonth_2(int emonth_2) {
        this.emonth_2 = emonth_2;
    }

    public int getEyear_2() {
        return eyear_2;
    }

    public void setEyear_2(int eyear_2) {
        this.eyear_2 = eyear_2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

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

                return hour > 11 ? (hour + 1) + ":00": hour == 24 ? (hour = 0)+ ":00": (hour == 0 ? "0:00" : hour + ":00");
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


