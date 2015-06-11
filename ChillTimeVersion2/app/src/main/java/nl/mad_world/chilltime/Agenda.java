package nl.mad_world.chilltime;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;


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

    public void setName(String name) {maakEvent.name = name;}

    public void setName2(String name2) {maakEvent.name2 = name2;}

    public void setBhour(int bhour) {maakEvent.bhour = bhour;}

    public void setBmin(int bmin) {maakEvent.bmin = bmin;}

    public void setBday(int bday) {maakEvent.bday = bday;}

    public void setBmonth(int bmonth) {maakEvent.bmonth = bmonth;}

    public void setEhour(int ehour) {maakEvent.ehour = ehour;}

    public void setByear(int byear) {maakEvent.byear = byear;}

    public void setEmin(int emin) {maakEvent.emin = emin;}

    public void setEday(int eday) {maakEvent.eday = eday;}

    public void setEmonth(int emonth) {maakEvent.emonth = emonth;}

    public void setEyear(int eyear) {maakEvent.eyear = eyear;}

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

    // END OF SETTERS //

}
