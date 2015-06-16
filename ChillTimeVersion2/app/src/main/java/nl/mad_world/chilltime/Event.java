package nl.mad_world.chilltime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Johan on 12-6-2015.
 */
public class Event extends Activity {
    private static String title;
    private static int id;
    private static int startHour;
    private static int startMin;
    private static int startDay;
    private static int startMonth;
    private static int startYear;
    private static int endHour;
    private static int endMin;
    private static int endDay;
    private static int endMonth;
    private static int endYear;

    private static String Title;
    private static int ID;
    private static int StartHour;
    private static int StartMin;
    private static int StartDay;
    private static int StartMonth;
    private static int StartYear;
    private static int EndHour;
    private static int EndMin;
    private static int EndDay;
    private static int EndMonth;
    private static int EndYear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
    }


    public Event(){
    }

public Event(int id, int startHour, String title, int startMin, int startDay,
        int startMonth, int startYear, int endHour, int endDay,
        int endMin, int endMonth, int endYear) {

        this.id = id;
        this.startHour = startHour;
        this.title = title;
        this.startMin = startMin;
        this.startDay = startDay;
        this.startMonth = startMonth;
        this.startYear = startYear;
        this.endHour = endHour;
        this.endDay = endDay;
        this.endMin = endMin;
        this.endMonth = endMonth;
        this.endYear = endYear;
        }

public void setTitle(String Title) {
        EditText name = (EditText) findViewById(R.id.eventName);
        title = name.getText().toString();

        Title = title;
        }

public void setId(int ID) {
        EditText ids = (EditText) findViewById(R.id.ID);
        id = Integer.parseInt(ids.getText().toString());

        ID = id;
        }

public void setStartHour(int StartHour) {
        EditText hour = (EditText) findViewById(R.id.StartHour);
        startHour = Integer.parseInt(hour.getText().toString());

        StartHour = startHour;
        }

public void setStartMin(int StartMin) {
        EditText min = (EditText) findViewById(R.id.StartMin);
        startMin = Integer.parseInt(min.getText().toString());

        StartMin = startMin;
        }

public void setStartDay(int StartDay) {
        EditText day = (EditText) findViewById(R.id.StartDay);
        startDay = Integer.parseInt(day.getText().toString());

        StartDay = startDay;
        }

public void setStartMonth(int StartMonth) {
        EditText month = (EditText) findViewById(R.id.StartMonth);
        startMonth = Integer.parseInt(month.getText().toString());

        StartMonth = startMonth;
        }

public void setStartYear(int startYear) {
        EditText year = (EditText) findViewById(R.id.StartYear);
        startYear = Integer.parseInt(year.getText().toString());

        this.startYear = startYear;
        }

public void setEndHour(int EndHour) {
        EditText hour = (EditText) findViewById(R.id.EndHour);
        endHour = Integer.parseInt(hour.getText().toString());

        EndHour = endHour;
        }

public void setEndMin(int EndMin) {
        EditText min = (EditText) findViewById(R.id.EndMin);
        endMin = Integer.parseInt(min.getText().toString());

        EndMin = endMin;
        }

public void setEndDay(int EndDay) {
        EditText day = (EditText) findViewById(R.id.EndDay);
        endDay = Integer.parseInt(day.getText().toString());

        EndDay = endDay;
        }

public void setEndMonth(int EndMonth) {
        EditText month = (EditText) findViewById(R.id.EndMonth);
        endMonth = Integer.parseInt(month.getText().toString());

        EndMonth = endMonth;
        }

public void setEndYear(int EndYear) {
        EditText year = (EditText) findViewById(R.id.EndYear);
        endYear = Integer.parseInt(year.getText().toString());

        EndYear = endYear;
        }


public static String gettitle() {
        return Title;
        }

public static  int getId() {
        return ID;
        }

public static  int getStartMin() {
        return StartMin;
        }

public static  int getStartHour() {
        return StartHour;
        }

public static  int getStartMonth() {
        return StartMonth;
        }

public static  int getStartDay() {
        return StartDay;
        }

public static  int getEndHour() {
        return EndHour;
        }

public static  int getStartYear() {
        return StartYear;
        }

public static  int getEndDay() {
        return EndDay;
        }

public static  int getEndYear() {
        return EndYear;
        }

public static int getEndMonth() {
        return EndMonth;
        }

public static int getEndMin() {
        return EndMin;
        }

@Override
public String toString() {
        return "maakEvent{" +
        "title='" + Title + '\'' +
        ", id=" + ID +
        ", startHour=" + StartHour +
        ", startMin=" + StartMin +
        ", startDay=" + StartDay +
        ", startMonth=" + StartMonth +
        ", startYear=" + StartYear +
        ", endHour=" + EndHour +
        ", endMin=" + EndMin +
        ", endDay=" + EndDay +
        ", endMonth=" + EndMonth +
        ", endYear=" + EndYear +
        '}';
        }
    public void goToWeek(View v){
        Intent go = new Intent(this, WeekViewer.class);
        startActivity(go);
    }
}