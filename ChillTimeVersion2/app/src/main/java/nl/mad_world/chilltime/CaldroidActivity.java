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
import java.util.TimeZone;

@SuppressLint("SimpleDateFormat")
public class CaldroidActivity extends FragmentActivity {
    private boolean undo = false;
    private CaldroidFragment caldroidFragment;
    private CaldroidFragment dialogCaldroidFragment;
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
    private static Date begin, end;
    public static ListView listView;
    private ArrayList events = new ArrayList();
    private List<ParseObject> List;

    /// Deze method haalt alle events op van de DB en zet ze in de ArrayList
    public void getData() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Event");
        query.whereExists("objectId");
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

                            String bdate = startyear + "-" + startmonth + "-" + startday  + " " + starthour  + ":" + startmin;
                            String edate = endyear + "-" + endmonth + "-" + endday  + " " + endhour  + ":" + endmin;

                            SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd MMMM yyyy HH:mm", Locale.getDefault());
                            sdf.setTimeZone(TimeZone.getTimeZone("CEST"));

                            begin = sdf.parse(bdate);
                            end = sdf.parse(edate);


                            events.add("Titel: " + title + "\n" + "Van: " + sdf.format(begin) + "\n" + "Tot: " + sdf.format(end));
                            System.out.println(title + begin + end);
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
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_caldroid);

        final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

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
            args.putBoolean(CaldroidFragment.ENABLE_CLICK_ON_DISABLED_DATES, true);
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


}




