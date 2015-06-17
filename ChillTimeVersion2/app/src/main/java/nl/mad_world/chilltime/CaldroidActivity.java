package nl.mad_world.chilltime;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@SuppressLint("SimpleDateFormat")
public class CaldroidActivity extends FragmentActivity {
    private boolean undo = false;
    private CaldroidFragment caldroidFragment;
    private CaldroidFragment dialogCaldroidFragment;
    private String title;
    private Date begin;
    private Date end;
    private ListView listView;
    private ArrayList events = new ArrayList();



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_caldroid);

        final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm");

        // Setup caldroid fragment
        // **** If you want normal CaldroidFragment, use below line ****
        caldroidFragment = new CaldroidFragment();


        // Setup arguments

        // If Activity is created after rotation
        if (savedInstanceState != null) {
            caldroidFragment.restoreStatesFromKey(savedInstanceState,
                    "CALDROID_SAVED_STATE");
        }
        // If activity is created from fresh
        else {
            Bundle args = new Bundle();
            Calendar cal = Calendar.getInstance();
            args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
            args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
            args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
            args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, false);

            // Uncomment this line to use dark theme
            //args.putInt(CaldroidFragment.THEME_RESOURCE, com.caldroid.R.style.CaldroidDefaultDark);

            caldroidFragment.setArguments(args);

            // Define a new Adapter
            // First parameter - Context
            // Second parameter - Layout for the row
            // Third parameter - ID of the TextView to which the data is written
            // Forth - the Array of data

            /// Dit maakt de lijst waarin de afspraken in.
            listView = (ListView) findViewById(R.id.list);

            ArrayAdapter adapter = new ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, events);


            // Assign adapter to ListView
            listView.setAdapter(adapter);
        }

        // Attach to the activity
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar1, caldroidFragment);
        t.commit();

        // Setup listener
        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {
            }

            @Override
            public void onChangeMonth(int month, int year) {
            }

            @Override
            public void onLongClickDate(Date date, View view) {
            }

            @Override
            public void onCaldroidViewCreated() {
            }

        };

        // Setup Caldroid
        caldroidFragment.setCaldroidListener(listener);

        Bundle state = savedInstanceState;

        getData();
    }

    /**
     * Save current states of the Caldroid here
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);

        if (caldroidFragment != null) {
            caldroidFragment.saveStatesToKey(outState, "CALDROID_SAVED_STATE");
        }

        if (dialogCaldroidFragment != null) {
            dialogCaldroidFragment.saveStatesToKey(outState,
                    "DIALOG_CALDROID_SAVED_STATE");
        }
    }

    /// Intent om naar Event te gaan.
    public void createEvent(View v) {
        Intent create = new Intent(this, Event.class);
        startActivity(create);
    }

    /// Deze method haalt alle events op van de DB en zet ze in de ArrayList
    public void getData() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Event");
        query.whereExists("ID");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> List, ParseException e) {
                try{
                if (e == null) {

                    Log.d("Afspraak", "Opgehaald " + List.size() + " afspraken");

                    for (int i = 0; i < List.size(); i++) {
                        title = List.get(i).getString("Title");
                        begin = List.get(i).getDate("StartDate");
                        end = List.get(i).getDate("EndDate");

                        SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd MMMM yyyy hh:mm", Locale.getDefault());

                        events.add("Titel: " + title + "\n" + "Start: " + sdf.format(begin) + "\n" + "Eind: " + sdf.format(end));
                        System.out.println(title + begin + end);
                    }

                } else {
                    Log.d("Afspraken", "Error: " + e.getMessage());
                }
            } catch(Exception t){
                    Toast.makeText(getApplicationContext(),
                            "Geen afspraken!", Toast.LENGTH_LONG).show();
                }


        }


        });

    }
}




