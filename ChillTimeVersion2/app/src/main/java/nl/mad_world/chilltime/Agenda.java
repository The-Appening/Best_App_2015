package nl.mad_world.chilltime;

import android.os.Bundle;
        import android.support.v7.app.ActionBarActivity;
        import android.view.MenuItem;
        import android.widget.EditText;


public class Agenda extends ActionBarActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        EditText hour = (EditText) findViewById(R.id.StartHour);
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


}
