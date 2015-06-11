package nl.mad_world.chilltime;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.EditText;


public class Agenda extends ActionBarActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


    // BEGIN OF SETTERS //

    public void setName(String name) {
        EditText namer = (EditText) findViewById(R.id.eventName);
        name = namer.getText().toString();
        maakEvent.name = name;
    }

    public void setBhour(int bhour, int textString) {
        EditText hour = (EditText) findViewById(R.id.StartHour);
        textString = Integer.parseInt(hour.getText().toString());
        bhour = textString;
        maakEvent.bhour = bhour;
    }

    public void setBmin(int bmin, int textString) {
        EditText min = (EditText) findViewById(R.id.StartMin);
        textString = Integer.parseInt(min.getText().toString());
        bmin = textString;
        maakEvent.bmin = bmin;
    }

    public void setBday(int bday, int textString) {
        EditText min = (EditText) findViewById(R.id.StartDay);
        textString = Integer.parseInt(min.getText().toString());
        bday = textString;
        maakEvent.bday = bday;
    }

    public void setBmonth(int bmonth, int textString) {
        EditText month = (EditText) findViewById(R.id.StartMonth);
        textString = Integer.parseInt(month.getText().toString());
        bmonth = textString;
        maakEvent.bmonth = bmonth;
    }


    public void setByear(int byear, int textString) {
        EditText year = (EditText) findViewById(R.id.StartYear);
        textString = Integer.parseInt(year.getText().toString());
        byear = textString;
        maakEvent.byear = byear;
    }

    public void setEmin(int emin, int textString) {
        EditText min = (EditText) findViewById(R.id.EndMin);
        textString = Integer.parseInt(min.getText().toString());
        emin = textString;
        maakEvent.emin = emin;
    }

    public void setEhour(int ehour, int textString) {
        EditText hour = (EditText) findViewById(R.id.EndHour);
        textString = Integer.parseInt(hour.getText().toString());
        ehour = textString;
        maakEvent.ehour = ehour;
    }

    public void setEday(int eday, int textString) {
        EditText day = (EditText) findViewById(R.id.EndDay);
        textString = Integer.parseInt(day.getText().toString());
        eday = textString;
        maakEvent.eday = eday;
    }

    public void setEmonth(int emonth, int textString) {
        EditText month = (EditText) findViewById(R.id.EndMonth);
        textString = Integer.parseInt(month.getText().toString());
        emonth = textString;
        maakEvent.emonth = emonth;
    }

    public void setEyear(int eyear, int textString) {
        EditText year = (EditText) findViewById(R.id.EndYear);
        textString = Integer.parseInt(year.getText().toString());
        eyear = textString;
        maakEvent.eyear = eyear;
    }


    /*
    public void setBhour_2(int bhour_2) {maakEvent.bhour_2 = bhour_2;}

    public void setBmin_2(int bmin_2) {maakEvent.bmin_2 = bmin_2;}

    public void setBday_2(int bday_2) {maakEvent.bday_2 = bday_2;}

    public void setBmonth_2(int bmonth_2) {maakEvent.bmonth_2 = bmonth_2;}

    public void setByear_2(int byear_2) {maakEvent.byear_2 = byear_2;}

    public void setEhour_2(int ehour_2) {maakEvent.ehour_2 = ehour_2;}

    public void setEmin_2(int emin_2) {maakEvent.emin_2 = emin_2;}

    public void setEday_2(int eday_2) {maakEvent.eday_2 = eday_2;}

    public void setEmonth_2(int emonth_2) {maakEvent.emonth_2 = emonth_2;}

    public void setEyear_2(int eyear_2) {maakEvent.eyear_2 = eyear_2;}

*/
    // END OF SETTERS //

}
