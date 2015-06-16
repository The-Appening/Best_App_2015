package nl.mad_world.chilltime;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

/**
 * Created by Johan on 12-6-2015.
 */
public class Event extends Activity {
    private static String title;
    private static int id;
    private static int startDate;
    private static int endDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
    }


    public Event(){
    }

public Event(int id, String title, int startDate, int endDate) {
            this.id = id;
            this.title = title;
            this.startDate =startDate;
            this.endDate = endDate;
        }

public void setTitle(String Title) {
        EditText name = (EditText) findViewById(R.id.eventName);
        title = name.getText().toString();

        Title = title;
        }

public void setId(int id) {
        id = id;
        }

public void setStartDate(int startDate) {

    EditText starthour = (EditText) findViewById(R.id.StartHour);
    int startHour = Integer.parseInt(starthour.getText().toString());

    EditText startmin = (EditText) findViewById(R.id.StartMin);
    int startMin = Integer.parseInt(startmin.getText().toString());

    EditText startday = (EditText) findViewById(R.id.StartDay);
    int startDay = Integer.parseInt(startday.getText().toString());

    EditText startmonth = (EditText) findViewById(R.id.StartMonth);
    int startMonth = Integer.parseInt(startmonth.getText().toString());

    EditText startyear = (EditText) findViewById(R.id.StartYear);
    int startYear = Integer.parseInt(startyear.getText().toString());

    String StartDate = startDay + "" + startMonth + "" + startYear + "" + startHour + "" + startMin;
    startDate = Integer.parseInt(StartDate);

            this.startDate = startDate;
}

public void setEndDate(int endDate) {
    EditText endhour = (EditText) findViewById(R.id.EndHour);
    int endHour = Integer.parseInt(endhour.getText().toString());

    EditText endmin = (EditText) findViewById(R.id.EndMin);
    int endMin = Integer.parseInt(endmin.getText().toString());

    EditText endday = (EditText) findViewById(R.id.EndDay);
    int endDay = Integer.parseInt(endday.getText().toString());

    EditText endmonth = (EditText) findViewById(R.id.EndMonth);
    int endMonth = Integer.parseInt(endmonth.getText().toString());

    EditText endyear = (EditText) findViewById(R.id.EndYear);
    int endYear = Integer.parseInt(endyear.getText().toString());

    String EndDate = endDay + "" + endMonth + "" + endYear + "" + endHour + "" + endMin;
    endDate = Integer.parseInt(EndDate);

    this.endDate = endDate;
    }

}